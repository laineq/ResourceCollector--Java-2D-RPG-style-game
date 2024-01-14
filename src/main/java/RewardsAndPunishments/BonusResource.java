package RewardsAndPunishments;

import main.GameWindow;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *  class of bonus resources in this game,
 *  extend Object class
 * each bonus reward adds 25 points
 */
public class BonusResource extends main.Object{
    /**
     * Construct a bonus resource item
     *
     */
    public BonusResource(){
        name = "BonusReward";

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        image = toolkit.getImage("src/main/resources/BonusReward.png");

        points = 25;
        collision = true;
    }

}
