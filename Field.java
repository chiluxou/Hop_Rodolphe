import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Field {
    public final int width, height;
    public static final int BLOCK_HEIGHT = 20;
    
    private final List<Block> blocks = new ArrayList<>();
    private final Random random = new Random();

    private static final int ALTITUDE_GAP = 50; // Espace entre les blocs
    private static final int MIN_BLOCK_WIDTH = 50;
    private static final int MAX_BLOCK_WIDTH = 100;
    
    private static final int START_ALTITUDE = 100; 

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        initializeBlocks();
    }

    public boolean checkCollisions(Axel axel) {
        for (Block block : blocks) { // 'blocks' est une liste de blocs dans Field
            if (block.collidesWith(axel.getX(), axel.getY(), GamePanel.getAxelWidth(), GamePanel.getAxelHeight())) {
                return true; // Collision détectée
            }
        }
        return false; // Aucune collision
    }



    private void initializeBlocks() {
    Random random = new Random();
    Set<Integer> usedAltitudes = new HashSet<>(); // Pour stocker les altitudes déjà utilisées

    for (int altitude = height - START_ALTITUDE; altitude > 0; altitude -= ALTITUDE_GAP) {
        // Vérifier si l'altitude est déjà utilisée
        if (usedAltitudes.contains(altitude)) {
            continue; // Passer cette altitude si elle est déjà occupée
        }

        int blockWidth = MIN_BLOCK_WIDTH + random.nextInt(MAX_BLOCK_WIDTH - MIN_BLOCK_WIDTH + 1);
        int blockX = random.nextInt(width - blockWidth); // Position horizontale aléatoire

        // Ajouter le bloc à la liste
        blocks.add(new Block(blockX, altitude, blockWidth));
        usedAltitudes.add(altitude); // Marquer cette altitude comme utilisée
    }
}

    public void update() {
        // Descendre les blocs
        for (Block block : blocks) {
            block.setY(block.getY() + 2); // Vitesse de défilement
        }

        // Supprimer les blocs hors de l'écran et ajouter de nouveaux blocs en haut
        blocks.removeIf(block -> block.getY() > height);


        while (blocks.size() < 10) {
            int blockWidth = MIN_BLOCK_WIDTH + random.nextInt(MAX_BLOCK_WIDTH - MIN_BLOCK_WIDTH);
            int blockX = random.nextInt(width - blockWidth);
            int newBlockY = blocks.get(0).getY() - ALTITUDE_GAP;
            blocks.add(0, new Block(blockX, newBlockY, blockWidth));
        }
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public static int getBlockHeight() {
        return BLOCK_HEIGHT; // Retourne la hauteur d'un bloc
    }

    
}
