package game;
import org.jbox2d.common.Vec2;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerController implements KeyListener{

    private Player player;
    private int speed;
    private Vec2 v;
    private Game game;


    public PlayerController(Player player, Game g){
        this.player = player;
        this.speed = player.getSpeed();
        game = g;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        v = player.getLinearVelocity(); //get player velocity
        int code = e.getKeyCode();
        //if shift is pressed, make player run
        if (code == KeyEvent.VK_SHIFT){
            player.run();
            speed=6;
        }
        //if A is pressed make player go left
        if (code == KeyEvent.VK_A){
            player.setLinearVelocity(new Vec2(-speed, v.y));
        }
        //if D is pressed make player go right
        if (code == KeyEvent.VK_D){
            player.setLinearVelocity(new Vec2(speed,v.y));
        }
        //if W is pressed make player go forward
        if (code == KeyEvent.VK_W){
            player.setLinearVelocity(new Vec2(v.x,speed));
        }

        //if S is pressed make player go back
        if (code == KeyEvent.VK_S){
            player.setLinearVelocity(new Vec2(v.x,-speed));;
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        //if shift is released player stops running
        if (code == KeyEvent.VK_SHIFT){
            speed = 3;
            player.walk();
        }
        //player returns to original state when W,A,S,D is released
        if (code == KeyEvent.VK_A){
            player.setLinearVelocity(new Vec2(0,v.y));
        }
        if (code == KeyEvent.VK_D){
            player.setLinearVelocity(new Vec2(0,v.y));
        }
        if (code == KeyEvent.VK_W){
            player.setLinearVelocity(new Vec2(v.x,0));
        }

        if (code == KeyEvent.VK_S){
            player.setLinearVelocity(new Vec2(v.x,0));
        }
        if (code == KeyEvent.VK_P){
            game.pause();
        }


    }

    public void updatePlayer(Player player){
        this.player = player;
    }
}
