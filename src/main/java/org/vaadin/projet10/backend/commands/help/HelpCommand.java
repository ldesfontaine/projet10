package org.vaadin.projet10.backend.commands.help;

import org.vaadin.projet10.backend.commands.Command;

public class HelpCommand implements Command {

    private final CommandHelpLoader loader = new CommandHelpLoader();

    @Override
    public String execute(String[] args) {
        StringBuilder sb = new StringBuilder("Liste des commandes disponibles :\n\n");

        if (args.length > 0) {
            var command = loader.findCommand(args[0]);
            if (command == null) {
                return "Commande inconnue : " + args[0];
            }
            return formatCommand(command);
        }

        for (var cmd : loader.getHelpList()) {
            sb.append("- ").append(cmd.name()).append("\n");
            sb.append("   -> ").append(cmd.description()).append("\n");
        }
        return sb.toString();
    }

    private String formatCommand(CommandHelpLoader.CommandHelp cmd) {
        StringBuilder sb = new StringBuilder("Commande : ").append(cmd.name()).append("\n");
        sb.append("Description : ").append(cmd.description()).append("\n");
        sb.append("Usage : `").append(cmd.usage()).append("`\n");

        if (cmd.options() != null && !cmd.options().isEmpty()) {
            sb.append("Options :\n");
            for (var opt : cmd.options()) {
                sb.append("  - ").append(opt.flag()).append(" : ").append(opt.description()).append("\n");
            }
        }
        sb.append("Exemple : `").append(cmd.example()).append("`\n");
        return sb.toString();
    }
}
