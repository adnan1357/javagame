package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Platform extends StaticBody {
    private static final BodyImage t = new BodyImage("data/t.png", 6);

    public Platform(World world, Vec2 position, float width, float height, int rotation){
        super(world);
        Shape shape = new BoxShape(width, height);
        StaticBody ground = new StaticBody(world, shape);
        ground.setPosition(position);
        ground.setAngleDegrees(rotation);
        ground.addImage(t); //add transparent image to hide platform


    }
}
