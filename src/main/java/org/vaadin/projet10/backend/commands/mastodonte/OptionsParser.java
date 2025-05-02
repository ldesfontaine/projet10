package org.vaadin.projet10.backend.commands.mastodonte;

public class OptionsParser {
    public Options parse(String[] args) {
        Options opts = new Options();
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-v":
                    opts.setVerbose(true);
                    break;
                case "-d":
                    if (i + 1 < args.length) {
                        try {
                            opts.setDays(Integer.parseInt(args[++i]));
                        } catch (NumberFormatException e) {
                            opts.setError("Option -d invalide : nombre attendu.");
                        }
                    }
                    break;
                default:
                    // Ignorer
            }
        }
        return opts;
    }
}