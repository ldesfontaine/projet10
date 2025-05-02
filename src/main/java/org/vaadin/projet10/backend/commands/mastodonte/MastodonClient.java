package org.vaadin.projet10.backend.commands.mastodonte;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.vaadin.projet10.backend.model.MastodonPost;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MastodonClient {
    public List<MastodonPost> fetchPosts(String tag, int limit) {
        try {
            String apiUrl = String.format("https://mastodon.social/api/v1/timelines/tag/%s?limit=%d", URLEncoder.encode(tag, StandardCharsets.UTF_8), limit);

            HttpURLConnection conn = (HttpURLConnection) new URL(apiUrl).openConnection();
            conn.setRequestMethod("GET");

            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String json = br.lines().collect(Collectors.joining());
                ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
                MastodonPost[] arr = mapper.readValue(json, MastodonPost[].class);
                return Arrays.asList(arr);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erreur API Mastodon: " + e.getMessage(), e);
        }
    }
}