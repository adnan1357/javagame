package game;

import city.cs.engine.CircleShape;
import city.cs.engine.DynamicBody;
import city.cs.engine.SoundClip;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.lang.Math;

/**
 * A bullet object.
 *
 * A bullet object contains the methods and functionality so the player can shoot
 *
 * @author adnanhabib
 * @version 1.0.0 May 7th, 2022
 */
public class Bullet extends DynamicBody {
    private DynamicBody projectile;
    private Player player;
    private int damage;
    private Vec2 pos;
    private static SoundClip gunShot;

    //creating the sound
    static {
        try {
            gunShot = new SoundClip("data/gunshot.wav");
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * the bullet constructor
     * @param w the world where the bullet is gonna be
     * @param player the player who shot the bullet
     */
    public Bullet(World w, Player player) {
        super(w);
        this.player = player;
        projectile = new DynamicBody(this.getWorld(), new CircleShape(0.2f)); //Create the bullet body
        projectile.setGravityScale(0);
        damage = 2;
        this.pos = player.getPosition(); //getting the initial position
    }

    /**
     * The method to shoot the bullet
     */
    public void shoot(){
        gunShot.play(); //play the sound
        //get the position of the players barrel, where bullet gets shot from
        Vec2 x = player.getTrackerPos(player.getPosition(), 2.5f, player.getAngleDegrees(), true);
        projectile.setPosition(x);

        //work out the position of where the bullet vector points to
        Vec2 y = player.getTrackerPos(x, 3.5f, player.getAngleDegrees(), false);
        projectile.setLinearVelocity((y.sub(x).mul(10)));

        //set the collision listener
        BulletCollision b = new BulletCollision(projectile, this);
        projectile.addCollisionListener(b);

    }

    /**
     * get the damage of the bullet
     * @return the damage
     */
    public int getDamage(){
        return this.damage;
    }

    /**
     * set the position of the bullet
     * @param x vector position
     */
    public void setPosition(Vec2 x){
        projectile.setPosition(x);
    }

    /**
     * get the position of the bullet
     * @return the vector position
     */
    public Vec2 getPos(){
        return pos;
    }







}
