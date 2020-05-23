package org.mamadev.teamsurvival;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.mamadev.teamsurvival.commands.Setup;
import org.mamadev.teamsurvival.file.TeamData;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.mamadev.teamsurvival.utils.Utils.*;

public class TeamSurvival extends JavaPlugin {
    public static ArrayList<OfflinePlayer> red = new ArrayList<>();
    public static ArrayList<OfflinePlayer> yellow = new ArrayList<>();
    public static ArrayList<OfflinePlayer> green = new ArrayList<>();
    public static ArrayList<OfflinePlayer> blue = new ArrayList<>();

    public static TeamSurvival instance;

    public static TeamSurvival getInstance(){
        return instance;
    }
    public static int numOfTeams;

    @Override
    public void onEnable() {
        instance = this;
        getCommand("setup").setExecutor(new Setup());
        TeamData.setup();
        if (cb("setupped")){
            numOfTeams = getTeams().size();
            for (Map.Entry<String, ArrayList<UUID>> entry : getTeams().entrySet()){
                OfflinePlayer player1 = Bukkit.getOfflinePlayer(entry.getValue().get(0));
                OfflinePlayer player2 = Bukkit.getOfflinePlayer(entry.getValue().get(1));
                if (red.size() == 2){
                    if (yellow.size() == 2){
                        if (green.size() == 2){
                            blue.add(player1);
                            blue.add(player2);
                        } else {
                            green.add(player1);
                            green.add(player2);
                        }
                    } else {
                        yellow.add(player1);
                        yellow.add(player2);
                    }
                }else {
                    red.add(player1);
                    red.add(player2);
                }
            }
        }
    }

    @Override
    public void onDisable() {
        TeamData.save();
    }

    private HashMap<String, ArrayList<UUID>> getTeams(){
        return TeamData.teams;
    }
}
