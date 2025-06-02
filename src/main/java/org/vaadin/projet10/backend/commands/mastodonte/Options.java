package org.vaadin.projet10.backend.commands.mastodonte;

public class Options {
    private int days = -1;
    private boolean verbose = false;
    private int minLikes = -1;
    private int minReplies = -1;
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
    
    public int getMinLikes() {
        return minLikes;
    }

    public void setMinLikes(int minLikes) {
        this.minLikes = minLikes;
    }

    public int getMinReplies() {
        return minReplies;
    }

    public void setMinReplies(int minReplies) {
        this.minReplies = minReplies;
    }

    public boolean hasLikeFilter() {
        return minLikes >= 0;
    }
    public boolean hasReplyFilter() {
        return minReplies >= 0;
    }
}