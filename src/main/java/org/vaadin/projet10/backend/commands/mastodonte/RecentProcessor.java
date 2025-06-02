package org.vaadin.projet10.backend.commands.mastodonte;

import org.vaadin.projet10.backend.model.MastodonPost;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
//import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class RecentProcessor implements Processor {
    @Override
    public List<MastodonPost> process(List<MastodonPost> posts, Options opts) {
        ZonedDateTime threshold = ZonedDateTime.now(ZoneOffset.UTC)
                .minusDays(opts.getDays())
                .minusMinutes(opts.getMinutes());
        return posts.stream().filter(p -> p.getCreatedAt().isAfter(threshold)).sorted((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt())).collect(Collectors.toList());
    }
}