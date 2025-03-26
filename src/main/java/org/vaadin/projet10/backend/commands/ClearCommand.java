package org.vaadin.projet10.backend.commands;

public class ClearCommand implements Command {
    @Override
    public Object execute(String[] args) {
        return "L'historique à bien été nettoyé !";
    }
}
