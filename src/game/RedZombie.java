package game;

import city.cs.engine.BodyImage;
import city.cs.engine.World;

public class RedZombie extends Zombie{
    private BodyImage walk = new BodyImage("data/redzombiewalk.gif",4);
    private BodyImage attack = new BodyImage("data/redzombieattack.gif",4);
    public RedZombie(World world) {
        super(world);
        setWalk(walk);
        setAttack(attack);
        setHealth(20);
        setBaseHealth(20);
        setDamage(5);
        setSpeed(2);
        idle();
        setName("RedZombie");
    }

}
