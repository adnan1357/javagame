package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ControlPanel {
    private JPanel mainPanel;
    private JButton resumeButton;
    private JButton restartButton;
    private JButton muteButton;
    private JButton saveButton;
    private JButton loadButton;
    private Game game;
    private GameWorld level;


    public ControlPanel(Game g){
        game = g;


        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.pause();
            }
        });
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                level = game.getLevel();
                level.stop();
                if (level instanceof Level1){
                    level = new Level1(game);
                }
                else if (level instanceof Level2){
                    level = new Level2(game);
                }
                else{
                    level = new Level3(game);
                }
                level.populate();
                //level now refer to the new level
                game.getView().setWorld(level);
                game.getView().updateWorld(level);
                game.getMouse().update(level);
                Tracker tracker = game.getTracker();
                tracker.setTracker(level);
                game.getGiveFocus().updateFocus(level);
                level.addStepListener(tracker);
                game.getPlayerController().updatePlayer(level.getPlayer());
                game.updateLevel(level);
                level.start();
            }
        });
        muteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(game.isMute()){
                    game.unmuteMusic();
                    System.out.println("unmuting");

                }
                else if(!game.isMute()){
                    game.muteMusic();
                    System.out.println("muting");
                }
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                level = game.getLevel();
                Save fw = new Save("gameSave.txt", level);
                try {
                    fw.save();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Load level = new Load(game);
                try {
                    level.Loadgame();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
