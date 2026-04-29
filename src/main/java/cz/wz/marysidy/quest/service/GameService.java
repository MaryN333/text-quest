package cz.wz.marysidy.quest.service;

import cz.wz.marysidy.quest.model.Quest;
import cz.wz.marysidy.quest.model.Step;
import cz.wz.marysidy.quest.repository.QuestRepository;

import java.util.HashMap;
import java.util.Map;

public class GameService {
    private final Map<String, Step> steps = new HashMap<>();

    public GameService(String questId) {
        QuestRepository repository = new QuestRepository();
        Quest quest = repository.loadQuest(questId);

        for (Step step : quest.getSteps()) {
            steps.put(step.getId(), step);
        }
    }

    public Step getStepById(String stepId) {
        return steps.get(stepId);
    }
}




