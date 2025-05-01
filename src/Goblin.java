import java.util.Random;

public class Goblin extends Character {
    Goblin(int landSize) {
        super.icon = "(◣_◢)";
        super.landSize = landSize;
    }

    public void move() {
        int[][] directions = {
                {-1, 0}, // up
                {1, 0},  // down
                {0, -1}, // left
                {0, 1},   // right
                {0, 0}  // no move
        };
        while (true) {
            int[] dir = directions[(int)(Math.random() * 5)];
            int newRow = super.position[0] + dir[0];
            int newCol = super.position[1] + dir[1];

            if (newRow >= 0 && newRow < landSize && newCol >= 0 && newCol < landSize) {
                super.position[0] = newRow;
                super.position[1] = newCol;
                break;
            }
        }
    }
}
