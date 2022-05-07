package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class ZombieCollision implements CollisionListener {
    private Zombie z;


    public ZombieCollision(Zombie z){
        this.z = z;
    }

    @Override
    public void collide(CollisionEvent e) {
        //If zombie collides with player, reduce players health and excute attack method from zombie class
        if (e.getOtherBody() instanceof Player){
            ((Player) e.getOtherBody()).reduceHealth(z.getDamage());
            z.attack();
        }

    }
}
