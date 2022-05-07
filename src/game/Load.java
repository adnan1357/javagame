package game;

import org.jbox2d.common.Vec2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Load{
    private Game game;
    private GameWorld level;

    public Load(Game g){
        game = g;
        level = game.getLevel();
    }
    //reading the file
    public void Loadgame() throws IOException {
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            System.out.println("Reading " + "gameSave.txt" + " ...");
            fr = new FileReader("gameSave.txt");
            reader = new BufferedReader(fr);
            String levelName = reader.readLine(); //the first line of the file is the level name
            //stops current level
            level = game.getLevel();
            level.stop();
            //reassigns level to the level in the file
            if (levelName.equals("Level1")){
                level = new Level1(game);
            }
            else if (levelName.equals("Level2")){
                level = new Level2(game);
            }
            else{
                level = new Level3(game);
            }
            //updates game with all the level details
            level.populate();
            //level now refer to the new level
            game.updateLevel(level);
            game.getView().setWorld(level);
            game.getView().updateWorld(level);
            game.getMouse().update(level);
            Tracker tracker = game.getTracker();
            tracker.setTracker(level);
            game.getGiveFocus().updateFocus(level);
            level.addStepListener(tracker);
            game.getPlayerController().updatePlayer(level.getPlayer());
            //start level
            level.start();

            //player details
            String line = reader.readLine();
            while (line != null) {
                String[] details = line.split(",");
                //sets player position, health and ammo
                if (details[0].equals("Player")) {
                    level.getPlayer().setPosition(new Vec2((int)Double.parseDouble(details[1]),(int)Double.parseDouble(details[2])));
                    level.getPlayer().setHealth(Integer.valueOf(details[3]));
                    level.getPlayer().setAmmo(Integer.valueOf(details[4]));
                }
                line = reader.readLine();

           }

            System.out.println("...done.");
         }finally {
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
    }
        }
