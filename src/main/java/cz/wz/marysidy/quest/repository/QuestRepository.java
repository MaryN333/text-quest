package cz.wz.marysidy.quest.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.wz.marysidy.quest.model.Quest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class QuestRepository {
    private static final QuestRepository INSTANCE = new QuestRepository();
    private static final Map<String, Quest> CACHE = new ConcurrentHashMap<>();
    private final ObjectMapper mapper = new ObjectMapper();
    private static final Logger log = LoggerFactory.getLogger(QuestRepository.class);

    private QuestRepository() {}

    public static QuestRepository getInstance() {
        return INSTANCE;
    }

    public Quest loadQuest(String questId) {
        if (CACHE.containsKey(questId)) {
            log.debug("Quest loaded from cache: {}", questId);
        }

        return CACHE.computeIfAbsent(questId, this::loadFromFile);
    }

    private Quest loadFromFile(String questId) {
        log.info("Loading quest from file: {}", questId);

        try (InputStream is = getClass()
                .getClassLoader()
                .getResourceAsStream(questId + ".json")) {

            if (is == null) {
                throw new RuntimeException("Quest file not found: " + questId);
            }

            return mapper.readValue(is, Quest.class);
        } catch (Exception e) {
            log.error("Failed to load quest: {}", questId, e);
            throw new RuntimeException("Failed to load quest: " + questId, e);
        }
    }
}
