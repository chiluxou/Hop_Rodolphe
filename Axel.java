
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
        if (jumping) {
            System.out.println("Jumping!");
            y -= (int) JUMP_SPEED;
            jumping = false; // désactiver le saut après une mise à jour
        } else {
            boolean onBlock = false;
            for (Block block : field.getBlocks()) {
                if (block.collidesWith(x, y + 1, GamePanel.getAxelWidth(), GamePanel.getAxelHeight())) {
                    onBlock = true;
                    break;
                }
            }
            if (!onBlock) {
                System.out.println("Falling...");
                y += (int) GRAVITY;
            }
        }
    
        if (left) {
            System.out.println("Moving Left...");
            x = Math.max(0, x - (int) LATERAL_SPEED);
        }
        if (right) {
            System.out.println("Moving Right...");
            x = Math.min(field.width - GamePanel.getAxelWidth(), x + (int) LATERAL_SPEED);
        }
    }
    
    
    
    
}
