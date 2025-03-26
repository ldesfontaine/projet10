package org.vaadin.projet10.backend;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TerminalController {
    private final CommandRegistry commandRegistry = new CommandRegistry();

    @PostMapping(value = "/execute", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String executeCommand(@RequestBody String command) {
        return commandRegistry.executeCommand(command);
    }
}
