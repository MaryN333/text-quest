package cz.wz.marysidy.quest.model;

import java.util.List;

public class Step {
    private String id;
    private String text;
    private List<Option> options;
    private ResultType result;

    public Step() {}

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public List<Option> getOptions() {
        return options;
    }
    public ResultType getResult() {
        return result;
    }
}
