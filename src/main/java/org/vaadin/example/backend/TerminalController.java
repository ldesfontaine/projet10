package org.vaadin.example.backend;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TerminalController {
    private final FileSystemService fileSystemService = new FileSystemService(); //init du système de fichier
    private final CommandRegistry commandRegistry = new CommandRegistry(); //init des commandes

    @PostMapping("/execute") // recupére la commande et l'envoie au CommandRegistry
    public String executeCommand(@RequestBody String command) {
        return commandRegistry.executeCommand(command, fileSystemService);
    }

    @GetMapping("/current-path")
    public String getCurrentPath() {
        return fileSystemService.getCurrentDirectory().getPath();
    }
}