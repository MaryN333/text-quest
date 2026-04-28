package cz.wz.marysidy.quest.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.wz.marysidy.quest.model.Quest;

import java.io.InputStream;

public class QuestRepository {
    public Quest loadQuest(String questId) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            InputStream is = getClass()
                    .getClassLoader()
                    .getResourceAsStream(questId + ".json");

            return mapper.readValue(is, Quest.class);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load quest", e);
        }
    }
}
