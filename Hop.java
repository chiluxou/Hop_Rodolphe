import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Hop {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 600;
    public static final int DELAY = 40;

    private final JFrame frame;
    private final Field field;
    private final Axel axel;
    private Timer timer;
    private GamePanel gamePanel;

    public Hop() {
        this.field = new Field(WIDTH, HEIGHT);
        this.axel = new Axel(field, WIDTH / 2, field.START_ALTITUDE);
        this.gamePanel = new GamePanel(field, axel);

        this.frame = new JFrame("Hop!");
        frame.add(gamePanel);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public boolean over() {
        return false;
    }

    public static void main(String[] args) {
        Hop game = new Hop();

        game.timer = new Timer(DELAY, (ActionEvent e) -> {
            game.round();
            if (game.over()) {
                game.timer.stop();
                game.frame.remove(game.gamePanel);
            }
        });
        game.timer.start();
    }

    public void round() {
        axel.update();
        field.update();

        // Vérifier les collisions
        for (Block block : field.getBlocks()) {
            if (block.collidesWith(axel.getX(), axel.getY(), GamePanel.getAxelWidth(), GamePanel.getAxelHeight())) {
                axel.setSurviving(false); // Collision détectée
            }
        }

        frame.repaint();
    }

}
