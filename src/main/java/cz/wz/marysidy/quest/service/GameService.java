package cz.wz.marysidy.quest.service;

import cz.wz.marysidy.quest.model.Quest;
import cz.wz.marysidy.quest.model.Step;
import cz.wz.marysidy.quest.repository.QuestRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GameService {
    private static final Map<String, GameService> CACHE = new ConcurrentHashMap<>();
    private final Map<String, Step> steps = new HashMap<>();

    public static GameService getInstance(String questId) {
        return CACHE.computeIfAbsent(questId, GameService::new);
    }

    public GameService(String questId) {
        QuestRepository repository = QuestRepository.getInstance();
        Quest quest = repository.loadQuest(questId);

        for (Step step : quest.getSteps()) {
            steps.put(step.getId(), step);
        }
    }

    public Step getStepById(String stepId) {
        return steps.get(stepId);
    }
}




