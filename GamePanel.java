import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class GamePanel extends JPanel implements KeyListener{
    private static final int BLOCK_HEIGHT = 10;
    private static final int AXEL_WIDTH = 10;
    private static final int AXEL_HEIGHT = 10;

    private final Axel axel;
    private final Field field;

    public GamePanel(Field field, Axel axel) {
        this.field = field;
        this.axel = axel;
    
        setPreferredSize(new Dimension(field.width, field.height));
        addKeyListener(this); // Attach the KeyListener
        setFocusable(true);   // Ensure panel is focusable
        requestFocusInWindow(); // Request focus for key events
    }
    
    
    public static int getAxelWidth() {
        return AXEL_WIDTH;
    }
    
    public static int getAxelHeight() {
        return AXEL_HEIGHT;
    }
    



@Override
public void paintComponent(Graphics g) {
    super.paintComponent(g);

    // Draw Axel
    g.setColor(Color.RED);
    g.fillRect(axel.getX(), axel.getY(), GamePanel.getAxelWidth(), GamePanel.getAxelHeight());

    // Draw blocks
    g.setColor(Color.BLACK);
    for (Block block : field.getBlocks()) {
        g.fillRect(block.getX(), block.getY(), block.getWidth(), BLOCK_HEIGHT);
    }
}


@Override
public void keyPressed(KeyEvent e) {
    System.out.println("Key pressed: " + e.getKeyCode()); // Debugging
    switch (e.getKeyCode()) {
        case KeyEvent.VK_LEFT:
            axel.setMovingLeft(true);
            break;
        case KeyEvent.VK_RIGHT:
            axel.setMovingRight(true);
            break;
        case KeyEvent.VK_UP:
            axel.setJumping(true);
            break;
    }
}


    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                axel.setMovingLeft(false);
                break;
            case KeyEvent.VK_RIGHT:
                axel.setMovingRight(false);
                break;
            case KeyEvent.VK_UP:
                // Stop jump logic is handled in Axel
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }
}

