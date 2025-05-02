package org.vaadin.projet10.backend.commands.mastodonte;

import org.vaadin.projet10.backend.model.MastodonPost;

import java.util.List;
import java.util.stream.Collectors;

public class PopularProcessor implements Processor {
    @Override
    public List<MastodonPost> process(List<MastodonPost> posts, Options opts) {
        return posts.stream().sorted((a, b) -> Integer.compare(b.getFavouritesCount(), a.getFavouritesCount())).collect(Collectors.toList());
    }
}