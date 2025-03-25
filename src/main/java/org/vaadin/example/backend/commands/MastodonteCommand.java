package org.vaadin.example.backend.commands;

import org.vaadin.example.backend.model.MastodonPost;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.vaadin.example.backend.FileSystemService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MastodonteCommand implements Command {
    @Override
    public Object execute(FileSystemService fileSystem, String[] args) {
        // Vérifier qu'un hashtag ou un mot-clé est fourni
        if (args.length == 0) {
            return "Veuillez spécifier un hashtag ou un mot-clé.";
        }

        String tag = args[0];
        try {
            // Encoder le tag au cas où il contient des caractères spéciaux
            String encodedTag = URLEncoder.encode(tag, StandardCharsets.UTF_8);
            String apiUrl = "https://mastodon.social/api/v1/timelines/tag/" + encodedTag + "?limit=5";

            URL url = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Configurer ObjectMapper avec le module JavaTimeModule
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());

            MastodonPost[] posts = mapper.readValue(response.toString(), MastodonPost[].class);
            List<MastodonPost> postList = Arrays.stream(posts).toList();

            // Formater la réponse pour l'afficher dans le terminal
            if (postList.isEmpty()) {
                return "Aucun post trouvé pour le hashtag: " + tag;
            }

            StringBuilder output = new StringBuilder("Posts pour le hashtag " + tag + ":\n");
            for (MastodonPost post : postList) {
                // On affiche l'id et le début du contenu (limité à 100 caractères)
                String cleanContent = post.getContent().replaceAll("<[^>]*>", "");
                if (cleanContent.length() > 100) {
                    cleanContent = cleanContent.substring(0, 100) + "...";
                }
                output.append("- [").append(post.getId()).append("] ").append(cleanContent).append("\n");
            }
            return output.toString();
        } catch (Exception e) {
            return "Erreur lors de la récupération des posts : " + e.getMessage();
        }
    }
}
