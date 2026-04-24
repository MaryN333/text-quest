package cz.wz.marysidy.quest.model;

import java.util.List;

public class Step {
    private String id;
    private String text;
    private List<Option> options;

    public Step() {}

    public Step(String id, String text, List<Option> options) {
        this.id = id;
        this.text = text;
        this.options = options;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public List<Option> getOptions() {
        return options;
    }
}
