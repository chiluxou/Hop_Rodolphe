import java.util.ArrayList;
import java.util.List;    

public class Field {
    public static final int ALTITUDE_GAP = 80;
    public static final int START_ALTITUDE = 40;

    public final int width, height;
    private int bottom, top; // bottom and top altitude

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
    }

    private final List<Block> blocks = new ArrayList<>();

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

public List<Block> getBlocks() {
    return blocks;
}

}
