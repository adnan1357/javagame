package game;
import city.cs.engine.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class GameView extends UserView {
    private Player player;
    private GameWorld world;
    private Image fog;
    Zombie[] z;

    //initialise constructor, get the player, world, zombies
    public GameView(GameWorld world, int width, int height) {
        super(world, width, height);
        player = world.getPlayer();
        this.world = world;
        z = world.getZombie();
        fog = new ImageIcon("data/fog.png").getImage(); //fog of war

    }

    //updating the view in response to new levels
    public void updateWorld(GameWorld world){
        this.world = world;
        player = world.getPlayer();
        z = world.getZombie();
    }



    @Override
    protected void paintBackground(Graphics2D g) {
        //background
        g.drawImage(world.getBackground(), 0, 0, this);

    }

    @Override
    protected void paintForeground(Graphics2D g){
        //creating health bars for each zombie
        for (int i =0; i<z.length; i++){
            try {
                //if the zombie is alive and its health has been reduced
                if (z[i].isAlive() && z[i].getHealth()<z[i].getBaseHealth()){
                    Point2D.Float p = worldToView(z[i].getPosition()); //get coordinates of zombie
                    //create a red rectangle
                    g.setColor(Color.red);
                    g.fillRect((int) p.x-15,(int) p.y-40,30,10);
                    g.setColor(Color.GREEN);
                    //calculating the size of a green rectangle based on zombies health
                    double health = (double) (z[i].getHealth()/z[i].getBaseHealth());
                    g.fillRect((int) p.x-15,(int) p.y-40, (int)(health * 30),10);
                }
            }
            catch (Exception e){
            }






        }
        //drawing the fog of war
        Point2D.Float x = worldToView(player.getPosition()); //get player position
        g.drawImage(fog, (int)x.x-1280, (int)x.y-720, this );
        g.setColor(Color.white);

        //displaying player stats
        g.drawString("Ammo: "+player.getAmmo(), 50,600);
        g.drawString("Health: "+player.getHealth(), 50,650);

        //displays if player died
        if (player.getHealth() <=0){
            g.setColor(Color.red);
            g.drawString("YOU DIED", 500,400);
        }

    }

}


