package DinoGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;

import static DinoGame.GamePanelD.SCREEN_HEIGHT;

public class Player {


    // width of the image
    static final int DINO_WIDTH = 150;
    // height of the image
    static final int DINO_HEIGHT = 150;

    //starting y pos of dino
    static final int STARTING_DINO_Y = -150 + SCREEN_HEIGHT - DINO_HEIGHT;
    static final int DINO_X = 200;

    //top dino pos
    static final int TOP_DINO_Y = 100;
    //value used to make dino jump
    int dinoY = STARTING_DINO_Y;
    //max speed the dino can move while jumping
    static final int DINO_MAX_SPEED = 25;

    //if reached the top or not
    boolean tiredDino = false;
    //the speed of the dino
    double dinoSpeed = 2;
    double dinoAcc = 1.5;


    BufferedImage image = null;
    Image scaledImage;


    public Player(){

        try{
            File input_file = new File("Game/src/DinoGame/Images/Dino.jpg");

            image = new BufferedImage(DINO_WIDTH, DINO_HEIGHT, BufferedImage.TYPE_INT_ARGB);

            // Reading input file
            image = ImageIO.read(input_file);


            scaledImage = image.getScaledInstance(DINO_WIDTH,DINO_HEIGHT,1);
        }
        catch(Exception e){
            System.out.println("error: " + e);
        }
    }



    public void drawPlayer(Graphics g, ImageObserver io){
        g.drawImage(scaledImage, DINO_X, dinoY, io);
    }

    public void move(char direction){
        switch (direction){
            case 'U':
                if (tiredDino){
                    slowDown(dinoSpeed);
                }
                if (dinoY > TOP_DINO_Y && !tiredDino) {
                    dinoY -= DINO_MAX_SPEED;
                }
                if (dinoY <= TOP_DINO_Y){
                    tiredDino = true;
                }
                break;
            case 'S':
                tiredDino = true;
                slowDown(dinoSpeed);
                break;
        }
    }

    private void slowDown(double speed){

        if (Math.abs(speed) <= DINO_MAX_SPEED) {
            dinoSpeed = speed + (dinoAcc * -1);
        }


        if (dinoY + 10 >= STARTING_DINO_Y){
            dinoY = STARTING_DINO_Y;
        }
        else{
            dinoY -= (int)dinoSpeed;
        }
        if (dinoY == STARTING_DINO_Y){
            tiredDino = false;
            dinoSpeed = 2;
        }
    }

    public int getDinoBottom(){
        return dinoY + DINO_HEIGHT;
    }
    public int[] getDinoSides(){
        return new int[]{DINO_X, DINO_X + DINO_WIDTH};
    }

}
