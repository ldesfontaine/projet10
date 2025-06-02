package org.vaadin.projet10.backend.commands.mastodonte;

public class Options {
    private Integer days = null;
    private boolean verbose = false;
    private int minLikes = -1;
    private int minReplies = -1;
    private String error;
    private Integer minutes = null;

    public boolean hasFilter() {
        return hasDays() || hasMinutes();
    }

    public Integer getDays() {
        return days != null ? days : 0;
    }

    public void setDays(Integer d) {
        this.days = d;
    }

    public boolean hasDays(){
        return days != null;
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
    public Integer getMinutes(){
        return minutes != null ? minutes : 0;
    }
    public void setMinutes(Integer minutes){
        this.minutes = minutes;
    }

    public boolean hasMinutes(){
        return minutes != null;
    }
}