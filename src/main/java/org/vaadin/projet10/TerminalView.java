package org.vaadin.projet10;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Route("")
public class TerminalView extends VerticalLayout {

    private final List<String> history = new ArrayList<>();
    private final Div output = new Div();
    private final TextField input = new TextField();
    private final RestTemplate restTemplate = new RestTemplate();

    public TerminalView() {
        setSizeFull();
        addClassName("terminal-view");

        output.addClassName("terminal-output");
        input.addClassName("terminal-input");
        input.setPlaceholder("Entrer une commande ...");
        input.setWidth("100%");

        input.addKeyPressListener(Key.ENTER, e -> processCommand(input.getValue())); // Listener Enter

        add(output, input);
        input.focus();
    }

    private void processCommand(String command) {
        if (command.trim().isEmpty()) return;

        // Envoyer la commande à l'API
        String response = restTemplate.postForObject("http://localhost:8080/api/execute", command, String.class);

        if (command.equals("clear")){
            history.clear();
        }
        
        String prompt = createPromptHtml();
        // Ajouter la commande et la réponse dans l'historique
        history.add(prompt + " " + command);
        history.add(response);
        
        // Met à jour l'affichage
        output.getElement().setProperty("innerHTML", String.join("<br>", history));
        input.clear();
        input.focus();
    }
    private String createPromptHtml() {
        String color = System.getenv("MASTO_PROMPT_COLOR");
        if (color == null || color.isBlank()) {
            color = "#00FF00"; // vert par défaut
        }

        return String.format("""
        <div class='prompt-box' style='border: 1px solid %s; color: %s; padding: 4px; display: inline-block; font-weight: bold;'>~ $</div>
        """, color, color);
    }
}