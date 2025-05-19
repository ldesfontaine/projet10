package org.vaadin.projet10.backend.commands;

import org.vaadin.projet10.backend.commands.mastodonte.cache.SessionCache;
import org.vaadin.projet10.backend.model.MastodonPost;
import java.util.List;

public class MshowCommand implements Command {
    @Override
    public String execute(String[] args) {
        if (args.length != 2 && args.length != 3) {
            return "Usage : mshow <hashtag> <numéro de post> [-o]";
        }

        String hashtag = args[0];
        int index;
        try {
            index = Integer.parseInt(args[1]) - 1; // L'utilisateur donne 1 à 5, liste = 0 à 4
        } catch (NumberFormatException e) {
            return "Erreur : le numéro doit être un entier.";
        }

        //Récupère la liste des postes à partir du cache de session
        List<MastodonPost> posts = SessionCache.get(hashtag + ":list");

        //Vérifie si l'index est dans les limites de la liste
        if (index < 0 || index >= posts.size()) {
            return "Erreur : numéro hors limites. Choisissez entre 1 et " + posts.size();
        }

        MastodonPost post = posts.get(index);

        //Si l'option -o est présente, tente d'ouvrir l'URL du poste dans le navigateur
        if (args.length == 3 && args[2].equals("-o")) {
           String url = post.getUrl();
           try{
            String os = System.getProperty("os.name").toLowerCase();
            if(os.contains("win")){
                Runtime.getRuntime().exec(new String[]{"rundll32", "url.dll,FileProtocolHandler", url});
            }
            else if (os.contains("mac")){
                Runtime.getRuntime().exec(new String[]{"open", url});
            }
            else if (os.contains("nix") || os.contains("nux")){
                Runtime.getRuntime().exec(new String[]{"xdg-open",url});
            }
            else{
                return "Système non supporté pour l'ouverture automatique. Voici l'URL : \n" + url;
            }
            return "Ouverture du post dans le navigateur...\n";
           } catch (Exception e){
            return "Erreur lors de l'ouverture du navigateur : " + e.getMessage();
           }
        }


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