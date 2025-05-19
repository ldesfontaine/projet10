package org.vaadin.projet10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import jakarta.annotation.PreDestroy;

@Route("")
public class TerminalView extends VerticalLayout {

    private final List<String> history = new ArrayList<>(); // Pour les flèches ↑ ↓
    private final List<String> outputLines = new ArrayList<>(); // Pour l’affichage à l’écran
    private int historyPointer = -1;
    private final String HISTORY_FILE = "data/terminal_history";
    private final Div output = new Div();
    private final TextField input = new TextField();
    private final RestTemplate restTemplate = new RestTemplate();

    public TerminalView() {
        setSizeFull();
        addClassName("terminal-view");

        Path dataDir = Paths.get("data");
        try {
            Files.createDirectories(dataDir);
        } catch (IOException e) {
            e.printStackTrace();
        }

        loadHistoryFromDisk();

        output.addClassName("terminal-output");
        input.addClassName("terminal-input");
        input.setPlaceholder("Entrer une commande ...");
        input.setWidth("100%");

        input.addKeyPressListener(Key.ENTER, e -> processCommand(input.getValue()));
        input.addKeyDownListener(Key.ARROW_UP, e -> {
            if (historyPointer > 0) {
                historyPointer--;
                String cmd = getLastUserCommand(historyPointer);
                if (cmd != null) input.setValue(cmd);
            }
        });

        input.addKeyDownListener(Key.ARROW_DOWN, e -> {
            if (historyPointer < history.size() - 1) {
                historyPointer++;
                String cmd = getLastUserCommand(historyPointer);
                if (cmd != null) input.setValue(cmd);
            } else {
                input.clear();
            }
        });

        add(output, input);
        input.focus();

        // Optionnel : déclenche la sauvegarde à la fermeture de la session UI
        UI.getCurrent().addDetachListener(e -> saveHistoryToDisk());
    }

    private void processCommand(String command) {
        if (command.trim().isEmpty()) return;

        String prompt = "~ $ ";

        // Envoyer la commande à l'API
        String response = restTemplate.postForObject("http://localhost:8080/api/execute", command, String.class);
        
        history.add(prompt + command);
        historyPointer = history.size(); // Réinitialise le pointeur

        if (command.equals("clear")) {
            outputLines.clear();
            output.setText(""); // Vider l’affichage aussi
        } else {
            outputLines.add(prompt + command);
            outputLines.add(response);
            output.setText(String.join("\n", outputLines));
        }
        input.clear();
        input.focus();
    }

    private String getLastUserCommand(int pointer) {
        for (int i = pointer; i >= 0 && i < history.size(); i++) {
            String line = history.get(i);
            if (line.startsWith("~ $ ")) {
                return line.substring(4);
            }
        }
        return null;
    }

    private void loadHistoryFromDisk() {
        try {
            Path path = Paths.get(HISTORY_FILE);
            if (Files.exists(path)) {
                List<String> lines = Files.readAllLines(path);
                history.addAll(lines);
                historyPointer = history.size();
                output.setText(String.join("\n", history));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveHistoryToDisk() {
        try {
            // On ne sauvegarde que les lignes de commandes, pas les réponses
            List<String> filtered = history.stream()
                    .filter(line -> line.startsWith("~ $ "))
                    .toList();
            Files.write(Paths.get(HISTORY_FILE), filtered);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    private void onDestroy() {
        saveHistoryToDisk();
    }
}
