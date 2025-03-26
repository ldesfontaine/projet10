package org.vaadin.projet10.backend.commands;

import org.vaadin.projet10.backend.FileSystemService;

public class LsCommand implements Command {
    @Override
    public Object execute(FileSystemService fileSystem, String[] args) {
        return fileSystem.listContents();
    }
}