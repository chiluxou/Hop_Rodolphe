public class Axel {
    public static final double MAX_FALL_SPEED = -20;
    public static final double JUMP_SPEED = 20;
    public static final double GRAVITY = 1;
    public static final double DIVE_SPEED = 3 * GRAVITY;
    public static final double LATERAL_SPEED = 8;

    private int x, y;

    private boolean falling;
    private boolean jumping;
    private boolean diving;
    private boolean left;
    private boolean right;

    private boolean surviving;

    private final Field field;

    public Axel(Field f, int x, int y) {
        this.field = f;
        this.x = x;
        this.y = y;
        this.surviving = true;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void setMovingLeft(boolean b) {
        this.left = b;
    }
    public void setMovingRight(boolean b) {
        this.right = b;
    }
    public void setJumping(boolean b) {
        this.jumping = b;
    }
    public void setFalling(boolean b) {
        this.falling = b;
    }

    public void setSurviving(boolean surviving) {
        this.surviving = surviving;
    }

    public void update() {
        if (falling) {
            y += Math.max(MAX_FALL_SPEED, y - GRAVITY);
        }
        if (jumping) {
            y -= JUMP_SPEED;
            jumping = false; // désactiver le saut après une mise à jour
        }
        if (left) {
            x -= LATERAL_SPEED;
        }
        if (right) {
            x += LATERAL_SPEED;
        }
    
        // Vérifier les limites du terrain
        if (y > field.height) {
            surviving = false; // Axel est tombé
        }
    }
    
}

