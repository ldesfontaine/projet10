package org.vaadin.projet10.backend.commands;

public class HelpCommand implements Command {
    @Override
    public String execute(String[] args) {
        return String.format("""
        Help Command :
        - search <hashtag> : récupère le plus populaire. (alias : mastodonte)
            args :  -v        : affiche les dates des posts.
                    -d <int>  : récupère les post dans les <int> jours.
        - clear : Permet d'effacer le terminal.
        - mshow <hashtag> <numéro de post>  : Permet d'afficher le post en entier.
    """);
    }
}
