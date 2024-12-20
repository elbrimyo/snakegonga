import javax.swing.*;

public class gameFrame extends JFrame {
    gameFrame(){
        this.add(new gamePanel());
        this.setTitle("CRISTIAN DASCALU CRAB");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
