import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel {
    private static final int BLOCK_HEIGHT = 10;
    private static final int AXEL_WIDTH = 10;
    private static final int AXEL_HEIGHT = 10;

    private final Axel axel;
    private final Field field;

    public GamePanel(Field field, Axel axel) {
        this.field = field;
        this.axel = axel;

        setPreferredSize(new Dimension(field.width, field.height));
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

    // Dessiner Axel
    g.setColor(Color.RED);
    g.fillRect(axel.getX(), axel.getY(), GamePanel.AXEL_WIDTH, GamePanel.AXEL_HEIGHT);

    // Dessiner les blocks
    g.setColor(Color.BLACK);
    for (Block block : field.getBlocks()) {
        g.fillRect(block.getX(), block.getY(), block.getWidth(), BLOCK_HEIGHT);
    }
}
}
