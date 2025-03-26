package org.vaadin.projet10.backend;

import org.vaadin.projet10.backend.commands.ClearCommand;
import org.vaadin.projet10.backend.commands.Command;
import org.vaadin.projet10.backend.commands.MastodonteCommand;
import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandRegistry() {
        // Ajoutez ici d'autres commandes si besoin
        commands.put("mastodonte", new MastodonteCommand());
        commands.put("clear", new ClearCommand());
    }

    public String executeCommand(String input) {
        String[] parts = input.split(" ", 2);
        String commandName = parts[0];
        String[] args = parts.length > 1 ? parts[1].split(" ") : new String[0];

        Command command = commands.get(commandName);
        if (command != null) {
            return (String) command.execute(args);
        }
        return "Unknown command: " + commandName;
    }
}
