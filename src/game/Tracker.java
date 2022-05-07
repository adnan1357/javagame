package game;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import org.jbox2d.common.Vec2;

public class Tracker implements StepListener {
    private Game game;
    private GameWorld world;
    private Player player;
    private Zombie[] zombie;

    public Tracker(GameWorld w, Game g){
        this.world = w;
        this.game = g;
        player = w.getPlayer();
        zombie = w.getZombie();
    }

    @Override
    public void preStep(StepEvent stepEvent) {

    }

    @Override
    public void postStep(StepEvent stepEvent) {
        try{
            if (player.getLinearVelocity().x == 0 && player.getLinearVelocity().y ==0){
                player.idle();
            }
            //for loop which makes zombies follow player
            for (int i = 0; i< zombie.length; i++){
                zombie[i].getAngle(player.getPosition()); //rotate the zombie to face the player
                //set speed of zombie
                Vec2 x = zombie[i].getTrackerPos(zombie[i].getPosition(), zombie[i].getSpeed(), zombie[i].getAngleDegrees());
                zombie[i].setLinearVelocity(x.sub(zombie[i].getPosition()));
            }

            //if all zombies are dead complete level
            if (world.getDead() == world.getZombie().length){
                world.complete();

            }

            //go to next level if level is complete
            if (world.completed()){
                game.goToNextLevel();
            }

            //stop game when player dies
            if (player.getHealth()<=0){
                world.stop();
                world.dead();
            }

        }
        catch(Exception e){
            System.out.println("error");

        }





    }

    public void setTracker(GameWorld level){
        this.world = level;
        player = level.getPlayer();
        zombie = level.getZombie();
    }
}
