import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.deqode.SnakeAndLadderGame;
import java.util.Map;
public class SnakeNLadderGameTest {
    @Test
    void testPlayerPosition() {
        SnakeAndLadderGame.Player player = new SnakeAndLadderGame.Player("John");
        assertEquals(0, player.getPosition());
        player.setPosition(50);
        assertEquals(50, player.getPosition());
    }

    @Test
    void testDiceRoll() {
        SnakeAndLadderGame.Dice dice = new SnakeAndLadderGame.Dice();
        int roll = dice.roll();
        assertTrue(roll >= 1 && roll <= 6);
    }

    @Test
    void testSnakePositions() {
        Map<Integer, Integer> snakePositions = SnakeAndLadderGame.snakePositions;
        assertEquals(10, snakePositions.get(16));
        assertEquals(47, snakePositions.get(57));
        assertEquals(95, snakePositions.get(98));
    }

    @Test
    void testLadderPositions() {
        Map<Integer, Integer> ladderPositions = SnakeAndLadderGame.ladderPositions;
        assertEquals(11, ladderPositions.get(1));
        assertEquals(36, ladderPositions.get(26));
        assertEquals(71, ladderPositions.get(61));
    }
}
