package DinoGame;

import javax.swing.*;
import java.awt.*;

public class GameFrameD extends JFrame {

    GameFrameD(){

        this.add(new GamePanelD());
        this.setTitle("Dinosaur");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setForeground(Color.black);
        this.setBackground(Color.black);



    }

}
