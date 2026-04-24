package cz.wz.marysidy.quest.service;

import cz.wz.marysidy.quest.model.Option;
import cz.wz.marysidy.quest.model.Step;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameService {
    private final Map<String, Step> steps = new HashMap<>();

    public GameService() {
        init();
    }

    private void init() {
        Step start = new Step("start", "Do you accept the challenge?",
                List.of(new Option("Yes", "negotiation"), new Option("No", "lose")));
        Step negotiation = new Step("negotiation", "Do you go to negotiations?",
                List.of(new Option("Yes", "win"), new Option("No", "lose")));
        Step win = new Step("win", "You won!", Collections.emptyList());
        Step lose = new Step("lose", "You lost!", Collections.emptyList());

        steps.put(start.getId(), start);
        steps.put(negotiation.getId(), negotiation);
        steps.put(win.getId(), win);
        steps.put(lose.getId(), lose);
    }

    public Step getStepById(String stepId) {
        return steps.get(stepId);
    }

    public Step getStartStep() {
        return steps.get("start");
    }
}
