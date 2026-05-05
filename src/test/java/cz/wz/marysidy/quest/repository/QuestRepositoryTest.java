package cz.wz.marysidy.quest.repository;

import cz.wz.marysidy.quest.model.Quest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuestRepositoryTest {
    @Test
    void loadQuest_ReturnsQuestFromJson() {
        QuestRepository repository = QuestRepository.getInstance();
        Quest quest = repository.loadQuest("prague-night");

        assertNotNull(quest);
        assertEquals("prague-night", quest.getId());
        assertFalse(quest.getSteps().isEmpty());
    }

    @Test
    void loadQuest_ThrowExceptionForMissingFile() {
        QuestRepository repository = QuestRepository.getInstance();

        assertThrows(RuntimeException.class, () -> {
            repository.loadQuest("unknown");
        });
    }

    @Test
    void loadQuest_ShouldCacheSameQuest() {
        QuestRepository repo = QuestRepository.getInstance();
        Quest q1 = repo.loadQuest("forest");
        Quest q2 = repo.loadQuest("forest");

        assertSame(q1, q2);
    }
}
