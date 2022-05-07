package game;
import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;
import java.lang.Math;

/**
 * A player object.
 *
 * A player object contains the parameters and functionality of the player, the user controls
 *
 * @author adnanhabib
 * @version 1.0.0 May 7th, 2022
 */

public class Player extends Walker{
    private static final Shape bodyShape = new CircleShape(1f);
    private static final BodyImage idlegif = new BodyImage("data/idle.gif", 5);
    private static final BodyImage walkgif = new BodyImage("data/walk.gif", 5);
    private static final BodyImage rungif = new BodyImage("data/run.gif", 5);


    private int speed = 3;
    private boolean walk;
    private boolean run;
    private boolean idle;
    AttachedImage img;
    private int health = 10;
    private int ammo;
    private int mag = 30;

    /**
     * Default player constructor
     * @param world -  the gameworld the player is in.
     */
    public Player(World world) {
        super(world, bodyShape);
        //add image to player
        img = new AttachedImage(this, idlegif,1,0,new Vec2((float)-0.25,(float)1));

    }

    /**
     * get the speed of the player
     *
     * @return speed
     */
    public int getSpeed(){
        return speed;
    }

    /**
     *Calculate the angle between the player and a position
     *
     * @param v the position we want to calculate the angle from the player to
     */
    public void getAngle(Vec2 v){
        Vec2 p = this.getPosition(); //get position of player
        Vec2 con = new Vec2(p.x, p.y+5); //control vector
        Vec2 m = v; //mouse vector

        float a  = getDistance(m,p); //distance between mouse and player
        float b = getDistance(p,con); //distance between player and control
        float c = getDistance(m,con); //distance between mouse and top of the screen
        double x = (a*a + b*b - c*c); //numerator of cosine rule
        double y = (2.0 * a * b); //denominator of cosine rule
        double d = x/y; //cosine rule
        double A = Math.toDegrees(Math.acos(d)); //convert angle from radians to degrees
        //if position of mouse is to the left of the player, flip the angle
        if (m.x-p.x > 0){
            A = -A;
        }
        //if A returns a float rotate player by the number
        if (!Float.isNaN((float) A)){
            this.rotateDegrees(this.getAngleDegrees()*-1);
            this.rotateDegrees((float) A);
        }
    }

    /**
     * get the distance between 2 vector positions
     *
     * @param x first vector position
     * @param y second vector position
     * @return the distance as a float
     */
    private float getDistance(Vec2 x, Vec2 y){
        var a = x.x - y.x; //get distance between 2 x axis
        var b = x.y - y.y; //get distance between 2 y axis
        float c  = (float) Math.sqrt( a*a + b*b );
        return c;
    }

    /**
     * change the animation to walk animation
     */
    public void walk(){
        this.removeAllImages(); //remove current animation
        img = new AttachedImage(this, walkgif,1,0,new Vec2((float)-0.25,(float)1)); //add new animation
        walk = true;
        idle = false;
        run = false;
    }

    /**
     * method that changes the animation to the run animation
     */
    public void run(){
        //removes all animations and adds run animation
        this.removeAllImages();
        img = new AttachedImage(this, rungif,1,0,new Vec2((float)-0.25,(float)1));
        run = true;
        walk = false;
        idle = false;
    }

    /**
     * method that changes the animation to the run animation
     */
    public void idle(){
        //removes all animations and adds idle animation
        this.removeAllImages();

        img = new AttachedImage(this, idlegif,1,0,new Vec2((float)-0.25,(float)1));
        idle = true;
        walk = false;
        run = false;
    }

    /**
     * method to check if the player is running
     *
     * @return true if the player is running
     */
    public boolean isRun(){
        return run;
    }

    /**
     * method which shoots a bullet
     */
    public void shoot(){
        //if mag is greater than 0, shoot a projectile, reduce mag by 1
        if (this.mag > 0){
            Bullet bullet = new Bullet(this.getWorld(), this);
            bullet.shoot();
            this.mag --;
        }

    }


    /**
     * method which gets position of the barrel of the gun, so the bullet can be shot from the correct distance
     *
     * @param centre the position of the player
     * @param r the radius where we want the tracker to be from the centre
     * @param angle the angle of the tracker from the centre
     * @param g if the method is used for the gun, this should be set to 0
     * @return the position of the tracker
     */
    public Vec2 getTrackerPos(Vec2 centre, float r, double angle, boolean g){
        //make angle positive and <= 180
        if (angle < 0){
            angle = angle*-1;
        }
        else if(angle == 180){
        }
        else{
            angle = 360 - angle;
        }
        angle = angle * (Math.PI/180); //convert angle to radians
        if(g){
            angle+=0.2; //increment angle to account for the gun which isn't exactly in place
        }
        //calculate the x and y axis of the tracker
        float x = (float) (centre.x + (r * Math.sin(angle)));
        float y = (float) (centre.y + (r * Math.cos(angle)));
        return new Vec2(x,y);
    }

    /**
     * Reduce the health of the player.
     *
     * @param damage the amount we decrement the health by.
     */
    public void reduceHealth(int damage){
        health = health - damage; //decrement health by damage
        if (health <= 0){
            this.destroy(); //if health is less than 0 destroy player
        }
    }

    /**
     * Get the health of the player
     * @return health
     */
    public int getHealth(){
        return health;
    }

    /**
     * Gets the ammo of the player
     * @return how many bullets are left
     */
    public int getAmmo(){
        return mag;
    }

    /**
     * Set the ammo of the player
     * @param ammo the amount of bullets we want to set the bullets to
     */
    public void setAmmo(int ammo){
        mag = ammo;
    }

    /**
     * Set the health of the player
     * @param h the amount we want to set the health to
     */
    public void setHealth(int h){
        health = h;
    }



}
