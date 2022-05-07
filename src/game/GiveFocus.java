package game;

import org.jbox2d.common.Vec2;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GiveFocus implements MouseListener {

    private GameView view;
    private GameWorld world;

    public GiveFocus(GameView v, GameWorld g){
        this.view = v;
        this.world = g;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point p = e.getPoint(); //gets point when mouse is pressed
        //System.out.println(view.viewToWorld(p));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (world.isRunning()){
            world.getPlayer().shoot(); //shoots gun when mouse is released
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        view.requestFocus();
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void updateFocus(GameWorld w){
        this.world = w;
    }
}
