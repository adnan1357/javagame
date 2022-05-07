package game;

import city.cs.engine.BodyImage;
import city.cs.engine.World;

//Creating a greenzombie which extends the zombie class
public class GreenZombie extends Zombie{
    //The images for the zombie
    private static BodyImage walk = new BodyImage("data/zombiewalk.gif", 4);
    private static BodyImage attack = new BodyImage("data/zombieattack.gif", 5);

    //constructor which sets the stats of the green zombie
    public GreenZombie(World world) {
        super(world);
        setWalk(walk);
        setAttack(attack);
        setHealth(10);
        setBaseHealth(10);
        setDamage(2);
        setSpeed(1);
        idle();
        setName("GreenZombie");
    }
}
