package org.vaadin.projet10.backend.commands.mastodonte;

import org.vaadin.projet10.backend.commands.Command;
import org.vaadin.projet10.backend.commands.mastodonte.cache.SessionCache;
import org.vaadin.projet10.backend.model.MastodonPost;

import java.util.List;
import java.util.stream.Collectors;

public class MastodonteCommand implements Command {
    private static final int FETCH_LIMIT = 50;
    private static final int DISPLAY_LIMIT = 5;

    private final OptionsParser parser = new OptionsParser();
    private final MastodonClient client = new MastodonClient();
    private final ProcessorFactory processorFactory = new ProcessorFactory();
    private final OutputFormatter formatter = new OutputFormatter();

    @Override
    public String execute(String[] args) {
        String hashtag = args.length > 0 ? args[0] : "chat";

        Options opts = parser.parse(args);
        if (!opts.isValid()) {
            return opts.getError();
        }

        List<MastodonPost> posts = client.fetchPosts(hashtag, FETCH_LIMIT);

        posts = posts.stream()
            .filter(p -> !opts.hasLikeFilter() || p.getFavouritesCount() >= opts.getMinLikes())
            .filter(p -> !opts.hasReplyFilter() || p.getRepliesCount() >= opts.getMinReplies())
            .collect(Collectors.toList());

        Processor proc = processorFactory.create(opts);
        List<MastodonPost> processed = proc.process(posts, opts);
        if (processed.isEmpty()) {
            return "Aucun résultat trouvé.";
        }
        // Limiter le nombre d'éléments à afficher
        if (processed.size() > DISPLAY_LIMIT) {
            processed = processed.subList(0, DISPLAY_LIMIT);
        }

        // Stockage pour la commande "afficher"
        SessionCache.put(hashtag + ":list", processed);

        return formatter.format(processed, opts, hashtag, DISPLAY_LIMIT);
    }
}