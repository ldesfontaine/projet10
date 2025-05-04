package org.vaadin.projet10.backend.commands;

import org.vaadin.projet10.backend.commands.mastodonte.cache.SessionCache;
import org.vaadin.projet10.backend.model.MastodonPost;
import java.util.List;

public class MshowCommand implements Command {
    @Override
    public String execute(String[] args) {
        if (args.length != 2) {
            return "Usage : mshow <hashtag> <numéro de post>";
        }

        String hashtag = args[0];
        int index;
        try {
            index = Integer.parseInt(args[1]) - 1; // L'utilisateur donne 1 à 5, liste = 0 à 4
        } catch (NumberFormatException e) {
            return "Erreur : le numéro doit être un entier.";
        }

        List<MastodonPost> posts = SessionCache.get(hashtag + ":list");

        if (index < 0 || index >= posts.size()) {
            return "Erreur : numéro hors limites. Choisissez entre 1 et " + posts.size();
        }

        MastodonPost post = posts.get(index);

        return formatFullPost(post);
    }

    private String formatFullPost(MastodonPost post) {
        return String.format("""
            📝 Post complet :
            👤 Auteur : %s
            🕒 Date : %s
            ❤️ Favoris : %d
            🔁 Partages : %d

            📄 Contenu :
            %s
            """,
            post.getAccount().getDisplayName(),
            post.getCreatedAt(),
            post.getFavouritesCount(),
            post.getReblogsCount(),
            post.getContent().replaceAll("<[^>]*>", "") // Enlève le HTML si nécessaire
        );
    }
}