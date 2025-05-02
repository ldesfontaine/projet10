package org.vaadin.projet10.backend.commands.mastodonte;

import org.vaadin.projet10.backend.model.MastodonPost;

import java.util.List;

public interface Processor {
    List<MastodonPost> process(List<MastodonPost> posts, Options opts);
}