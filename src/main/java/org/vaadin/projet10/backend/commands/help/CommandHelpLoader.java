package org.vaadin.projet10.backend.commands.help;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class CommandHelpLoader {

    private List<CommandHelp> helpList;

    public CommandHelpLoader() {
        loadYaml();
    }

    @SuppressWarnings("unchecked")
    private void loadYaml() {
        Yaml yaml = new Yaml();
        InputStream input = getClass().getClassLoader().getResourceAsStream("commands.yml");
        if (input == null) {
            throw new RuntimeException("Fichier commands.yml introuvable dans les ressources !");
        }

        Map<String, Object> data = yaml.load(input);
        List<Map<String, Object>> commands = (List<Map<String, Object>>) data.get("commands");

        helpList = commands.stream().map(cmd -> {
            String name = (String) cmd.get("name");
            String description = (String) cmd.get("description");
            String usage = (String) cmd.get("usage");
            String example = (String) cmd.get("example");

            List<Map<String, String>> optionsRaw = (List<Map<String, String>>) cmd.get("options");
            List<CommandHelp.Option> options = null;

            if (optionsRaw != null) {
                options = optionsRaw.stream()
                        .map(opt -> new CommandHelp.Option(opt.get("flag"), opt.get("description")))
                        .toList();
            }

            return new CommandHelp(name, description, usage, example, options);
        }).toList();
    }

    public List<CommandHelp> getHelpList() {
        return helpList;
    }

    public CommandHelp findCommand(String name) {
        return helpList.stream()
                .filter(cmd -> cmd.name().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public record CommandHelp(
        String name,
        String description,
        String usage,
        String example,
        List<Option> options
    ) {
        public record Option(String flag, String description) {}
    }
}
