package org.vaadin.projet10.backend;

import org.vaadin.projet10.backend.commands.CdCommand;
import org.vaadin.projet10.backend.commands.Command;
import org.vaadin.projet10.backend.commands.LsCommand;
import org.vaadin.projet10.backend.commands.MastodonteCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandRegistry() {
        commands.put("ls", new LsCommand());
        commands.put("cd", new CdCommand());
        commands.put("mastodonte", new MastodonteCommand());
    }

    public String executeCommand(String input, FileSystemService fileSystem) {
        String[] parts = input.split(" ", 2);
        String commandName = parts[0];
        String[] args = parts.length > 1 ? parts[1].split(" ") : new String[0];

        Command command = commands.get(commandName);
        if (command != null) {
            return (String) command.execute(fileSystem, args);
        }
        return "Unknown command: " + commandName;
    }
}
