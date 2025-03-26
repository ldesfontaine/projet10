package org.vaadin.projet10.backend.commands;

import org.vaadin.projet10.backend.model.MastodonPost;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class MastodonteCommand implements Command {
    @Override
    public Object execute(String[] args) {
        if (args.length == 0) {
            return "Veuillez spécifier un hashtag ou un mot-clé.";
        }
        String tag = args[0];
        try {
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

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());

            MastodonPost[] posts = mapper.readValue(response.toString(), MastodonPost[].class);
            List<MastodonPost> postList = Arrays.asList(posts);

            if (postList.isEmpty()) {
                return "Aucun post trouvé pour le hashtag: " + tag;
            }

            StringBuilder output = new StringBuilder("Posts pour le hashtag " + tag + ":\n");
            for (MastodonPost post : postList) {
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
