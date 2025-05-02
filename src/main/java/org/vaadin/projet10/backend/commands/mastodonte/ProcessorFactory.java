package org.vaadin.projet10.backend.commands.mastodonte;

public class ProcessorFactory {
    public Processor create(Options opts) {
        if (opts.hasFilter()) {
            return new RecentProcessor();
        } else {
            return new PopularProcessor();
        }
    }
}