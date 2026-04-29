package cz.wz.marysidy.quest.repository;

import cz.wz.marysidy.quest.model.Quest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuestRepositoryTest {
    @Test
    void loadQuest_ReturnsQuestFromJson() {
        QuestRepository repository = new QuestRepository();
        Quest quest = repository.loadQuest("quest1");

        assertNotNull(quest);
        assertEquals("quest1", quest.getId());
        assertFalse(quest.getSteps().isEmpty());
    }

    @Test
    void loadQuest_ThrowExceptionForMissingFile() {
        QuestRepository repository = new QuestRepository();

        assertThrows(RuntimeException.class, () -> {
            repository.loadQuest("unknown");
        });
    }
}
