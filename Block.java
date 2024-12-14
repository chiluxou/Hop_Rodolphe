import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Block {
    private int x, y;

    public final int width, height; // Dimensions of the field
    private final ArrayList<Block> blocks;

    public static final int ALTITUDE_GAP = 80; // Gap between blocks
    public static final int START_ALTITUDE = 40; // Lowest altitude to start placing blocks
    private static final int MAX_BLOCK_WIDTH = 100;
    private static final int MIN_BLOCK_WIDTH = 40;

    public Block(int x, int y, int width, int height,int blocks) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.blocks= new ArrayList<Block>();
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public void setY(int y) {
        this.y = y;
    }

    private void initializeBlocks() {
        Random random = new Random();
        for (int altitude = height - START_ALTITUDE; altitude > 0; altitude -= ALTITUDE_GAP) {
            int blockWidth = MIN_BLOCK_WIDTH + random.nextInt(MAX_BLOCK_WIDTH - MIN_BLOCK_WIDTH + 1); // Random width
            int blockX = random.nextInt(width - blockWidth); // Random x-position
            blocks.add(new Block(blockX, altitude, blockWidth));
        }
    }

    public boolean collidesWith(int axelX, int axelY, int axelWidth, int axelHeight) {
        return axelX < x + width && axelX + axelWidth > x &&
                axelY < y + Field.ALTITUDE_GAP && axelY + axelHeight > y;
    }

}
