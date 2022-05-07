package game;

import java.io.FileWriter;
import java.io.IOException;

public class Save {
    private String fileName;
    private GameWorld level;

    public Save(String fileName, GameWorld l) {
        this.fileName = fileName;
        level = l;
    }

    public void save()
            throws IOException {
        System.out.println("saving");
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName);
            writer.write(level.getLevelName()+"\n"); //write level name
            //for loop which writes the players and zombies, attributes
            for (int i =0; i<level.getDynamicBodies().size(); i++){
                if (level.getDynamicBodies().get(i) instanceof Player){
                    writer.write("Player" + "," + level.getPlayer().getPosition().x + ","+ level.getPlayer().getPosition().y +"," + level.getPlayer().getHealth()+","+level.getPlayer().getAmmo()+"\n");
                }
                if (level.getDynamicBodies().get(i) instanceof Zombie){
                    writer.write( level.getDynamicBodies().get(i).getName()+ "," + level.getDynamicBodies().get(i).getPosition().x +","+level.getDynamicBodies().get(i).getPosition().y +","+ ((Zombie) level.getDynamicBodies().get(i)).getHealth() +"\n");
                }

            }
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}