package cz.wz.marysidy.quest.model;

public class Option {
    private String text;
    private  String nextStepId;

    public Option() {}

    public Option(String text, String nextStepId) {
        this.text = text;
        this.nextStepId = nextStepId;
    }

    public String getNextStepId() {
        return nextStepId;
    }

    public String getText() {
        return text;
    }
}
