package org.vaadin.projet10.backend.commands.mastodonte;

public class Options {
    private int days = -1;
    private boolean verbose = false;
    private String error;

    public boolean hasFilter() {
        return days > 0;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int d) {
        this.days = d;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public void setVerbose(boolean v) {
        this.verbose = v;
    }

    public boolean isValid() {
        return error == null;
    }

    public String getError() {
        return error;
    }

    public void setError(String msg) {
        this.error = msg;
    }
}