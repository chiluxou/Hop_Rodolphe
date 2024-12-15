public class Axel {
    private int x, y;
    private final Field field;
    private boolean surviving;

    private boolean left, right, jumping;
    
    private static final int LATERAL_SPEED = 5;

    private int velocityY; // Vitesse verticale
    private static final int JUMP_SPEED = -15; // Vitesse initiale du saut (vers le haut)
    private static final int GRAVITY = 1;      // Gravité appliquée en continu
    private static final int MAX_FALL_SPEED = 15; // Vitesse maximale de chute


    public Axel(Field field, int x, int y) {
        this.field = field;
        this.x = x;
        this.y = y;
    }

    

    public void update(Field field) {
        boolean onBlock = false;
    
        // Vérifie la collision vers le bas (Axel touche un bloc par en dessous)
        for (Block block : field.getBlocks()) {
            if (block.collidesWith(x, y + velocityY, GamePanel.getAxelWidth(), GamePanel.getAxelHeight())) {
                if (velocityY > 0) { // Collision en descendant
                    y = block.getY() - GamePanel.getAxelHeight(); // Placer Axel sur le bloc
                    velocityY = 0; // Arrêter la chute
                    onBlock = true;
                } else if (velocityY < 0) { // Collision en montant
                    y = block.getY() + Field.getBlockHeight(); // Placer Axel en dessous du bloc
                    velocityY = MAX_FALL_SPEED; // Inverser la vitesse pour un "rebond"
                }
            }
        }
    
        // Appliquer la gravité si Axel n'est pas sur un bloc
        if (!onBlock) {
            velocityY = Math.min(velocityY + GRAVITY, MAX_FALL_SPEED);
        }
    
        // Gestion du saut
        if (jumping && onBlock) {
            velocityY = JUMP_SPEED;
            jumping = false; // Désactiver le saut après l'initiation
        }
    
        y += velocityY; // Appliquer la vélocité verticale
    
        // Déplacement horizontal
        if (left) x = Math.max(0, x - LATERAL_SPEED);
        if (right) x = Math.min(field.getWidth() - GamePanel.getAxelWidth(), x + LATERAL_SPEED);
    }
    
    
    
    
    
    

    public void setMovingLeft(boolean movingLeft) { this.left = movingLeft; }
    public void setMovingRight(boolean movingRight) { this.right = movingRight; }
    public void setJumping(boolean jumping) { this.jumping = jumping; }

    public int getX() { return x; }
    public int getY() { return y; }

    public boolean isSurviving() {
        return surviving;
    }
}
