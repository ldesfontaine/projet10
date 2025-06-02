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
                case "-l":  // min likes
                    if (i + 1 < args.length) {
                        try {
                            opts.setMinLikes(Integer.parseInt(args[++i]));
                        } catch (NumberFormatException e) {
                            opts.setError("Option -l invalide : nombre attendu.");
                        }
                    }
                    break;
                case "-r":  // min replies
                    if (i + 1 < args.length) {
                        try {
                            opts.setMinReplies(Integer.parseInt(args[++i]));
                        } catch (NumberFormatException e) {
                            opts.setError("Option -r invalide : nombre attendu.");
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