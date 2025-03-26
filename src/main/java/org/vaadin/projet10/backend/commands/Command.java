package org.vaadin.projet10.backend.commands;

import org.vaadin.projet10.backend.FileSystemService;

public interface Command {
    Object execute(FileSystemService fileSystem, String[] args);
}
