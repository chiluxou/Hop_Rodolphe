import java.util.ArrayList;
import java.util.List;
import java.util.Random;    

public class Field {
     // Constants for block generation
     public static final int ALTITUDE_GAP = 80; // Vertical gap between blocks
     public static final int START_ALTITUDE = 40; // Initial altitude for the first block
     public static final int MAX_BLOCK_WIDTH = 100; // Maximum width of a block
     public static final int MIN_BLOCK_WIDTH = 50;  // Minimum width of a block

    

    public final int height; // Field height
    public final int width;  // Field width
    private final List<Block> blocks; // List to store blocks
    //private int bottom, top; // bottom and top altitude

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        this.blocks = new ArrayList<>(); // Initialize the list of blocks
        initializeBlocks(); // Generate blocks when Field is created
    }

    public void update() {
        for (Block block : blocks) {
            block.setY( block.getY()+ ALTITUDE_GAP); // Faire descendre les blocks
            if (block.getY() > height) {
                blocks.remove(block); // Supprimer les blocks hors écran
        }
    }
}

    public void addBlock(Block block) {
        blocks.add(block);
    }

    private void initializeBlocks() {
        Random random = new Random();

        // Générer des blocs à intervalles réguliers d'altitude
        for (int altitude = height - START_ALTITUDE; altitude > 0; altitude -= ALTITUDE_GAP) {
            int blockWidth = MIN_BLOCK_WIDTH + random.nextInt(MAX_BLOCK_WIDTH - MIN_BLOCK_WIDTH + 1); // Largeur aléatoire
            int blockX = random.nextInt(width - blockWidth); // Position horizontale aléatoire
            blocks.add(new Block(blockX, altitude, blockWidth)); // Ajouter le bloc
        }
    }

    public List<Block> getBlocks() {
        return blocks;
    }

}
