package game;

import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Mouse extends JPanel implements MouseMotionListener {
    Player player;
    GameView view;

    public Mouse(Player p, GameView v) {
        this.player = p;
        view = v;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //moves player position so it faces the mouse when mouse is dragged
        if (player.getWorld().isRunning()){
            Point p = e.getPoint();
            Vec2 p2 = view.viewToWorld(p);
            player.getAngle(p2);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //moves player position so it faces the mouse when mouse is moved
        if (player.getWorld().isRunning()){
            Point p = e.getPoint(); //get position of mouse in respect to window
            Vec2 p2 = view.viewToWorld(p); //convert window coordinates to game coordinates
            player.getAngle(p2);
        }

    }

    public void update(GameWorld world){
        this.player = world.getPlayer();
    }
}
