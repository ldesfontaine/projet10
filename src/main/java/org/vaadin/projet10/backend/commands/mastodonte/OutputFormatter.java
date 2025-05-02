package org.vaadin.projet10.backend.commands.mastodonte;

import org.vaadin.projet10.backend.model.MastodonPost;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class OutputFormatter {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public String format(List<MastodonPost> posts, Options opts, String tag, int limit) {
        if (posts.isEmpty()) {
            return String.format("Aucun post pour #%s%s", tag, opts.hasFilter() ? " dans les " + opts.getDays() + " jours" : "");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(opts.hasFilter() ? String.format("Posts pour le hashtag %s (derniers %d jours):%n", tag, opts.getDays()) : String.format("Posts pour le hashtag %s (populaires):%n", tag));

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
}