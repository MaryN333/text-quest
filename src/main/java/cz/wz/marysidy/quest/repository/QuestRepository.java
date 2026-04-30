package cz.wz.marysidy.quest.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.wz.marysidy.quest.model.Quest;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class QuestRepository {
    private static final Map<String, Quest> CACHE = new ConcurrentHashMap<>();
    private final ObjectMapper mapper = new ObjectMapper();

    public Quest loadQuest(String questId) {
        return CACHE.computeIfAbsent(questId, this::loadFromFile);
    }

    private Quest loadFromFile(String questId) {
        try {
            InputStream is = getClass()
                    .getClassLoader()
                    .getResourceAsStream(questId + ".json");

            if (is == null) {
                throw new RuntimeException("Quest file not found: " + questId);
            }
            return mapper.readValue(is, Quest.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load quest: " + questId, e);
        }
    }
}
