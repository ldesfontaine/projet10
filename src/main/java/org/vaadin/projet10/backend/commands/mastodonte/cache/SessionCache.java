package org.vaadin.projet10.backend.commands.mastodonte.cache;

import org.vaadin.projet10.backend.model.MastodonPost;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessionCache {
    private static final Map<String, List<MastodonPost>> store = new HashMap<>();
    public static void put(String key, List<MastodonPost> posts) {
        store.put(key, posts);
    }
    public static List<MastodonPost> get(String key) {
        return store.getOrDefault(key, Collections.emptyList());
    }
}