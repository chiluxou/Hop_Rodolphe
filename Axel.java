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

    

    public void update() {
        boolean onBlock = false;
    
        // Vérifier si Axel est sur un bloc (collision par le bas)
        for (Block block : field.getBlocks()) {
            if (block.collidesWith(x, y + GamePanel.getAxelHeight(), GamePanel.getAxelWidth(), 1)) {
                onBlock = true;
                y = block.getY() - GamePanel.getAxelHeight(); // Se poser sur le bloc
                velocityY = 0; // Réinitialiser la vitesse verticale
                break;
            }
        }
    
        // Appliquer le saut uniquement si Axel est sur un bloc
        if (jumping && onBlock) {
            velocityY = JUMP_SPEED; // Vitesse initiale du saut
            jumping = false;        // Désactiver le saut
        }
    
        // Vérifier les collisions vers le haut (pendant la montée)
        if (velocityY < 0) { // Si Axel monte
            for (Block block : field.getBlocks()) {
                if (block.collidesWith(x, y + velocityY, GamePanel.getAxelWidth(), GamePanel.getAxelHeight())) {
                    velocityY = 0; // Arrêter la montée
                    y = block.getY() + Field.BLOCK_HEIGHT; // Ajuster la position sous le bloc
                    break;
                }
            }
        }
    
        // Appliquer la gravité si Axel n'est pas sur un bloc
        if (!onBlock) {
            velocityY += GRAVITY; // Ajouter la gravité à la vitesse verticale
            velocityY = Math.min(velocityY, MAX_FALL_SPEED); // Limiter la vitesse de chute
        }
    
        // Mettre à jour la position verticale
        y += velocityY;
    
        // Gérer les déplacements horizontaux
        if (left) {
            x = Math.max(0, x - LATERAL_SPEED);
        }
        if (right) {
            x = Math.min(field.width - GamePanel.getAxelWidth(), x + LATERAL_SPEED);
        }
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
