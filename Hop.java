import javax.swing.*;

public class Hop {
    private final Field field;
    private final Axel axel;
    private final GamePanel gamePanel;
    public int score = 0;
    private Timer timer;

    public Hop() {
        field = new Field(400, 600);
        Block firstBlock = field.getBlocks().get(0);
        axel = new Axel(field, firstBlock.getX() + firstBlock.getWidth() / 2, firstBlock.getY() - GamePanel.getAxelHeight());

        gamePanel = new GamePanel(field, axel);
        JFrame frame = new JFrame("Hop!");
        frame.add(gamePanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Timer timer = new Timer(16, e -> round());
        timer.start();
    }

    public void round() {
        axel.update(); // Mettre à jour Axel (déplacement, saut, gravité)
        field.update(); // Mettre à jour les blocs (défilement et génération)
    
        // Vérifier si le jeu est terminé
        if (over()) {
            timer.stop();
            System.out.println("Game Over! Score final : " + score);
        }
    
        gamePanel.repaint(); // Rafraîchir l'affichage
        score++;
    }
    

    public static void main(String[] args) {
        new Hop();
    }

    public boolean over() {
        return !axel.isSurviving(); // Retourne true si Axel n'est plus en vie
    }
}

