package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.DynamicBody;
import city.cs.engine.StaticBody;


public class BulletCollision implements CollisionListener {
    private DynamicBody projectile;
    private Bullet bullet;


    public BulletCollision(DynamicBody projectile, Bullet bullet){
        this.projectile= projectile;
        this.bullet = bullet;
    }

    @Override
    public void collide(CollisionEvent e) {
        //check if bullet collides with a zombie
        if (e.getOtherBody() instanceof Zombie){
            //reduce zombie health
           ((Zombie) e.getOtherBody()).setHealth((int)(((Zombie) e.getOtherBody()).getHealth()-(bullet.getDamage())));

           //if zombies health is less than or equal to 0, destroy body
           if (((Zombie) e.getOtherBody()).getHealth() <= 0){
               e.getOtherBody().destroy();
           }
        }
        //destroy bullet on collision with anything
        projectile.destroy();
    }
}
