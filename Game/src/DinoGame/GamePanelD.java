package DinoGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GamePanelD extends JPanel implements ActionListener {


    static final int SCREEN_WIDTH = 1500;
    static final int SCREEN_HEIGHT = 800;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY = 10;
    final int[] x = new int[GAME_UNITS];
    final int[] y = new int[GAME_UNITS];
    char direction = 'R';
    public static boolean running = false;
    javax.swing.Timer timer;
    Timer gameTimer = new Timer();
    Random random;

    int frames = 0;
    final int FPS = 600 / DELAY;

    final int STARTING_OBJ_SPEED = 15;
    int objSpeed;


    ImageObserver io = (img, infoflags, x, y, width, height) -> {
        return false;
    };

    Obstacle smallObs;
    Obstacle medObs;

    Player dino = new Player();



    GamePanelD(){
        random = new Random();

        smallObs = new Obstacle(1, 0);
        System.out.println(smallObs);
        medObs = new Obstacle(2, random.nextInt(500,1000));
        System.out.println(medObs);

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        this.setForeground(Color.green);
        startGame();
    }

    public void startGame(){
        running = true;
        timer = new javax.swing.Timer(DELAY,this);
        timer.start();



    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){

        if(running) {


            frames++;
            objSpeed = (frames / (FPS * 5)) + STARTING_OBJ_SPEED;

            dino.drawPlayer(g,io);
            runObstacle(this.smallObs, g);
            runObstacle(medObs, g);


        }
        else{
            gameOver(g);
        }
    }
    public void movePlayer() {
        dino.move(direction);
    }

    public void gameOver(Graphics g){
    //game over screen
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.ITALIC, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running){
            movePlayer();
        }
        repaint();

    }

    public void runObstacle(Obstacle x, Graphics g){
        x.drawImage(g,io, objSpeed);
        x.checkCollisions(dino);
    }




    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            switch (e.getKeyCode()){
                case KeyEvent.VK_SPACE, KeyEvent.VK_W, KeyEvent.VK_UP:
                    direction = 'U';
                    break;
                case KeyEvent.VK_S, KeyEvent.VK_DOWN:
                    direction = 'D';
                    break;
                default:
                    direction = 'S';
                    break;

            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()){
                case KeyEvent.VK_SPACE, KeyEvent.VK_W, KeyEvent.VK_UP, KeyEvent.VK_S, KeyEvent.VK_DOWN:
                    direction = 'S';
                    break;
            }
        }
    }


}
