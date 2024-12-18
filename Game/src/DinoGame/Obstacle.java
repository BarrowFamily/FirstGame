package DinoGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;

import static DinoGame.GamePanelD.*;

public class Obstacle {

    private static int height = 0, width = 0, xPos = 0, yPos = 0;
    private static Image skin;

    private static int top;
    private static int[] sides = new int[2];

    public Obstacle(int size, int xOffset){
        if (size == 1){
            height = 110;
            width = 90;

            setImage("Game/src/DinoGame/Images/SmallCactus.png");
        } else if (size == 2) {
            height = 150;
            width = 150;

            setImage("Game/src/DinoGame/Images/MedCactus.png");
        } else if (size == 3){
            height = 150;
            width = 200;

            setImage("Game/src/DinoGame/Images/LargeCactus.png");
        }
        xPos = 2500 + xOffset;
        yPos = -150 + SCREEN_HEIGHT - height;

        calculateWalls();
    }
    private void setImage(String fileName){
        Image tempIm;

        try{
            tempIm = ImageIO.read(new File(fileName));
            skin = tempIm.getScaledInstance(width,height,1);
        }
        catch (Exception e){
            System.out.println("error: " + e);
        }

    }

    public void drawImage(Graphics g, ImageObserver x, int speed){

        checkResetPos();
        xPos -= speed;
        g.drawImage(skin, xPos, yPos, x);
    }

    public void checkCollisions(Player dino){
        calculateWalls();
        //checks if left obstacle side is within dino sides
        if (sides[0] >= dino.getDinoSides()[0] && sides[0] <= dino.getDinoSides()[1]){
            if (top <= dino.getDinoBottom()){ //checks if top of obstacle is higher than bottom of dino
                running = false;
            }
        }
        //checks if right obstacle side is within dino sides
        if (sides[1] >= dino.getDinoSides()[0] && sides[1] <= dino.getDinoSides()[1]){
            if (top <= dino.getDinoBottom()){ //checks if top of obstacle is higher than bottom of dino
                running = false;
            }
        }
    }

    private void calculateWalls(){
        top = yPos;
        sides[0] = xPos;
        sides[1] = xPos + width;
    }

    @Override
    public String toString() {
        return "Height: " + height + " Width: " + width;
    }

    private void checkResetPos(){
        if (sides[1] < 0){
            xPos = SCREEN_WIDTH + 5000;
        }
    }



}
