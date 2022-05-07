package game;
import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * A zombie.
 *
 * A zombie object contains the parameters and functionality of the zombie
 *
 * @author adnanhabib
 * @version 1.0.0 May 7th, 2022
 */

public class Zombie extends DynamicBody implements ActionListener {
    private static final Shape bodyShape = new CircleShape(1f);
    private static BodyImage walkgif = new BodyImage("data/zombiewalk.gif", 4);
    private static BodyImage attackgif = new BodyImage("data/zombieattack.gif", 5);
    private int health;
    private int baseHealth;
    private int damage;
    private boolean alive;
    private int speed;
    AttachedImage img;
    private Timer timer;
    private String name;
    private static SoundClip attacksfx;

    //zombie attack sound effect
    static {
        try {
            attacksfx = new SoundClip("data/zombieattack.wav");
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Default zombie constructor
     * @param world the world the zombie is placed in
     */
    public Zombie(World world){
        super(world, bodyShape);
        addImage(walkgif);
        this.alive = true;
        baseHealth = health;
    }

    /**
     *Calculate the angle between the player and a position
     *
     * @param v the position we want to calculate the angle from the player to
     */
    public void getAngle(Vec2 v){
        Vec2 p = this.getPosition();
        Vec2 con = new Vec2(p.x, p.y+5);
        Vec2 m = v;

        float a  = getDistance(m,p); //distance between mouse and player
        float b = getDistance(p,con); //distance between player and control
        float c = getDistance(m,con); //distance between mouse and top of the screen
        double x = (a*a + b*b - c*c); //numerator of cosine rule
        double y = (2.0 * a * b); //denominator of cosine rull
        double d = x/y; //cosine rule
        //convert angle to degrees
        double A = Math.toDegrees(Math.acos(d));
        if (m.x-p.x > 0){
            A = -A;
        }
        //if A is a valid number, rotate zombie
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
        var a = x.x - y.x;
        var b = x.y - y.y;
        float c  = (float) Math.sqrt( a*a + b*b );
        return c;
    }

    /**
     * method which gets position of the barrel of the gun, so the bullet can be shot from the correct distance
     *
     * @param centre the position of the player
     * @param r the radius where we want the tracker to be from the centre
     * @param angle the angle of the tracker from the centre
     * @return the position of the tracker
     */
    public Vec2 getTrackerPos(Vec2 centre, float r, double angle){
        //make angle a positive number <= 18-
        if (angle < -1){
            angle = angle*-1;
        }
        else if(angle == 180){
        }
        else{
            angle = 360 - angle;
        }
        angle = angle * (Math.PI/180);

        //get position of tracker x and y coordinates
        float x = (float) (centre.x + (r * Math.sin(angle)));
        float y = (float) (centre.y + (r * Math.cos(angle)));
        return new Vec2(x,y);
    }

    /**
     * Get the health of the player
     * @return health
     */
    public float getHealth(){
        return health;
    }

    /**
     * Set the health of the player
     * @param x the amount we want to set the health to
     */
    public void setHealth(int x){
        this.health = x;
    }

    /**
     * destroy the zombie
     */
    @Override
    public void destroy(){
        this.alive = false;
        super.destroy();
    }

    /**
     * checks if the zombie is alive
     * @return true if zombie is alive
     */
    public boolean isAlive(){
        return alive;
    }

    /**
     * method which removes all animation and plays the attack animation
     */
    public void attack(){
        this.removeAllImages();
        //add attack animation gif
        img = new AttachedImage(this, attackgif,1,0, new Vec2(0,0));
        timer = new Timer(400, this);
        timer.start();
        attacksfx.play();


    }

    /**
     * method which makes zombie idle
     */
    public void idle(){
        this.removeAllImages();

        img = new AttachedImage(this, walkgif,1,0, new Vec2(0,0));
    }

    /**
     * method to make the zombie idle
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        idle();
    }

    /**
     * method to set the walk animation
     * @param image
     */
    public void setWalk(BodyImage image){
        walkgif = image;
    }

    /**
     * method to set the attack animation
     * @param image
     */
    public void setAttack(BodyImage image){
        attackgif = image;
    }

    /**
     * method to set the damage of the zombie
     *
     * @param damage the number we want the damage to be
     */
    public void setDamage(int damage){
        this.damage = damage;
    }

    /**
     * Check the damage
     * @return the damage
     */
    public int getDamage(){
        return damage;
    }

    /**
     * get the base health
     * @return the basehealth
     */
    public int getBaseHealth(){
        return baseHealth;
    }

    /**
     * set the basehealth
     * @param health the number we want the base health to be
     */
    public void setBaseHealth(int health){
        baseHealth = health;
    }

    /**
     * set the speed of the zombie
     * @param speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * get the speed of zombie
     * @return
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * set the name of the zombie
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * get the name of the zombie
     * @return the zombie
     */
    public String getName(){
        return name;
    }
}
