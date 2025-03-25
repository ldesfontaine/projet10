package org.vaadin.example.backend.commands;

import org.vaadin.example.backend.FileSystemService;

public class LsCommand implements Command {
    @Override
    public Object execute(FileSystemService fileSystem, String[] args) {
        return fileSystem.listContents();
    }
}