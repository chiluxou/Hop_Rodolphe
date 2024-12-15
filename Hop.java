import javax.swing.JFrame;
import javax.swing.Timer;

public class Hop {
    private final Timer timer; // Le Timer principal
    private final Timer startDelay; // Le Timer de délai

    public Hop() {
        Field field = new Field(400, 600);

        if (field.getBlocks().isEmpty()) {
            System.err.println("Erreur : Aucun bloc généré !");
            System.exit(1);
        }

        Block firstBlock = field.getBlocks().get(0);
        Axel axel = new Axel(field, firstBlock.getX() + firstBlock.getWidth() / 2,
                             firstBlock.getY() - GamePanel.getAxelHeight());
        GamePanel gamePanel = new GamePanel(field, axel);

        JFrame frame = new JFrame("Hop!");
        frame.add(gamePanel);
        frame.setResizable(false);
        frame.pack();
        frame.setSize(field.getWidth(), field.getHeight());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Initialise le Timer principal
        timer = new Timer(16, e -> {
            axel.update(field);
            field.update();
            gamePanel.repaint();
        });

        // Timer pour le délai de 3 secondes
        startDelay = new Timer(3000, e -> {
            timer.start(); // Démarre le jeu après le délai
            ((Timer) e.getSource()).stop();
        });

        startDelay.start(); // Démarre le Timer de délai
    }

    public static void main(String[] args) {
        new Hop();
    }
}
