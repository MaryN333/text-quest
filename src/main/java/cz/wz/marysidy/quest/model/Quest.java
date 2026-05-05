package cz.wz.marysidy.quest.model;

import java.util.List;

public class Quest {
    private String id;
    private List<Step> steps;

    public String getId() {
        return id;
    }

    public List<Step> getSteps() {
        return steps;
    }
}
