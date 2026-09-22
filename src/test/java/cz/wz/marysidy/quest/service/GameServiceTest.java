package cz.wz.marysidy.quest.service;

import cz.wz.marysidy.quest.model.Step;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class GameServiceTest {
    @Test
    void getStepById_ReturnsCorrectStepById() {
        GameService service = new GameService("negotiation");
        Step step = service.getStepById("negotiation");

        assertNotNull(step);
        assertEquals("negotiation", step.getId());
    }

    @Test
    void getStepById_ReturnsNullForInvalidStep() {
        GameService service = new GameService("forest");
        Step step = service.getStepById("unknown");

        assertNull(step);
    }

    @Test
    void startStep_FollowCorrectTransition() {
        GameService service = new GameService("negotiation");
        Step start = service.getStepById("start");
        String nextStepId = start.getOptions().get(0).getNextStepId();

        assertNotNull(start);
        assertEquals("negotiation", nextStepId);
    }

    @Test
    void constructor_LoadForestQuest() {
        GameService service = new GameService("forest");
        Step step = service.getStepById("start");

        assertNotNull(step);
        assertEquals("start", step.getId());
    }

    @Test
    void constructor_ThrowExceptionForInvalidQuest() {
        assertThrows(RuntimeException.class, () -> {
            new GameService("unknownQuest");
        });
    }
}
