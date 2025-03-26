package org.vaadin.projet10.backend.commands;

import org.vaadin.projet10.backend.FileSystemService;

public class CdCommand implements Command {
    @Override
    public Object execute(FileSystemService fileSystem, String[] args) {
        if (args.length == 0) {
            return "No directory specified";
        }
        return fileSystem.changeDirectory(args[0]);
    }
}