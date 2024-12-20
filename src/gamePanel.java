//import javax.script.ScriptEngine;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.*;

public class gamePanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 600, SCREEN_HEIGHT = 600, UNIT_SIZE = 25, DELAY = 75;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    final int[] x = new int[GAME_UNITS], y = new int[GAME_UNITS];
    int bodyParts = 4, applesEaten = 0, appleX, appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;
    gamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }
    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
               g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
               g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
            }

            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } 
                else {
                    //g.setColor(new Color(45, 180, 0));
                    g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);

                }
            }
            g.setColor(Color.red);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("SCOR: "+applesEaten, (SCREEN_WIDTH - metrics.stringWidth("SCOR: "+applesEaten)) / 2, g.getFont().getSize());
        }
        else gameOver(g);
    }

    public void newApple() {
        appleX = random.nextInt(SCREEN_WIDTH / UNIT_SIZE) * UNIT_SIZE;
        appleY = random.nextInt(SCREEN_HEIGHT / UNIT_SIZE) * UNIT_SIZE;
    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 'U' : y[0] = y[0] - UNIT_SIZE; break;
            case 'D' : y[0] = y[0] + UNIT_SIZE; break;
            case 'L' : x[0] = x[0] - UNIT_SIZE; break;
            case 'R' : x[0] = x[0] + UNIT_SIZE; break;
        }
    }

    public void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }

    public void checkCollisions() {
        // check head & body collisions
        for (int i = bodyParts; i > 0; i--)
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
                break;
            }

        //check if head collides w/ left border
        if (x[0] < 0)
            running = false;
        //check if head collides w/ right border
        if ( x[0] > SCREEN_WIDTH)
            running = false;
        // check if head collides with top
        if (y[0] < 0)
            running = false;
        // check if head collides with bottom
        if (y[0] > SCREEN_HEIGHT)
            running = false;
        if (!running)
            timer.stop();
    }

    public void gameOver(Graphics g) {
        //Scor
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("SCOR: "+applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("SCOR: "+applesEaten)) / 2, g.getFont().getSize());
        //Game over text
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);
        //retry
        g.setColor(Color.white);
        g.setFont(new Font("Ink Free", Font.BOLD, 30));
        FontMetrics metrics3 = getFontMetrics(g.getFont());
        g.drawString("Apasa R pt retry", (SCREEN_WIDTH - metrics3.stringWidth("Apasa R pt retry")) / 2, SCREEN_HEIGHT / 2 + 100);
    }

    public void actionPerformed(ActionEvent e){

        if (running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R')
                        direction = 'L';
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L')
                        direction = 'R';
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D')
                        direction = 'U';
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U')
                        direction = 'D';
                    break;
                case KeyEvent.VK_R:
                    if (!running)
                        resetGame();
                    break;
            }
        }
    }

    public void resetGame() {
        bodyParts = 4;
        applesEaten = 0;
        direction = 'R';
        running = false;
        timer.stop();
        for (int i = 0; i < bodyParts; i++) {
            x[i] = 0;
            y[i] = 0;
        }
        startGame();
        repaint();
    }
}
