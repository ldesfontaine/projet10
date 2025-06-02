package org.vaadin.projet10.backend.commands.mastodonte;

import org.vaadin.projet10.backend.model.MastodonPost;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OutputFormatter {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public String format(List<MastodonPost> posts, Options opts, String tag, int limit) {
        if (posts.isEmpty()) {
            return String.format("Aucun post pour #%s%s", tag, opts.hasFilter() ? " dans les " + formatMessageDateMinute(opts) : "");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(opts.hasFilter()
                ? String.format("Posts pour le hashtag %s (dans les %s):%n", tag, formatMessageDateMinute(opts))
                : String.format("Posts pour le hashtag %s (populaires):%n", tag));

        for (int i = 0; i < posts.size() && i < limit; i++) {
            MastodonPost p = posts.get(i);
            String text = p.getContent().replaceAll("<[^>]*>", "");
            if (text.length() > 100) text = text.substring(0, 100) + "...";
            sb.append(String.format("%d. %s", i + 1, text));
            if (opts.isVerbose()) {
                sb.append(String.format(" — %s — ♥%d", p.getCreatedAt().format(FMT), p.getFavouritesCount()));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private String formatMessageDateMinute(Options opts){
        boolean hasD = opts.hasDays();
        boolean hasM = opts.hasMinutes();

        int d = opts.getDays();
        int m = opts.getMinutes();

        //N'affiche que les jours quand on utilise -d
        if (hasD && !hasM){
            return d + " jour" + (d > 1 ? "s" : "");
        }
        //N'affiche que les minutes quand on utilise -m
        if (!hasD && hasM){
            int heures = m / 60;
            int minutes = m % 60;
            List<String> parts = new ArrayList<>();
            if (heures > 0) parts.add(heures + " heure" + (heures > 1 ? "s" : ""));
            if (minutes > 0) parts.add(minutes + " minute" + (minutes > 1 ? "s" : ""));
            return String.join(" et ", parts);
        }

        int totalMinutes = opts.getDays() * 1440 + opts.getMinutes();

        int jours = totalMinutes / 1440;
        int heures = (totalMinutes % 1440) / 60;
        int minutes = totalMinutes % 60;

        List<String> parts = new ArrayList<>();
        if (jours > 0) parts.add(jours + " jour" + (jours > 1 ? "s" : ""));
        if (heures > 0) parts.add(heures + " heure" + (heures > 1 ? "s" : ""));
        if (minutes > 0) parts.add(minutes + " minute" + (minutes > 1 ? "s" : ""));
        if (parts.isEmpty()) return "";
        return String.join(" , ", parts);
    }
}