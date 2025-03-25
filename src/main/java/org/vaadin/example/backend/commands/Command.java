package org.vaadin.example.backend.commands;

import org.vaadin.example.backend.FileSystemService;

public interface Command {
    Object execute(FileSystemService fileSystem, String[] args);
}
