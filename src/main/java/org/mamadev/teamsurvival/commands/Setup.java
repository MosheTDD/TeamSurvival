package org.mamadev.teamsurvival.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mamadev.teamsurvival.TeamSurvival;
import static org.mamadev.teamsurvival.utils.Utils.*;

public class Setup implements CommandExecutor {
    private TeamSurvival plugin = TeamSurvival.getInstance();
    private Player[] players;
    public Setup(){
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (cb("setupped")){
            sendRawMsg(sender, "&cAlready setupped");
            return true;
        }
        if (!argsCheck(sender, 1, args)) return true;
        int playerCount = Bukkit.getOnlinePlayers().size();
        players = new Player[playerCount];
        int count = 0;
        for (Player all : Bukkit.getOnlinePlayers()) {
            sendRawMsg(all, "&cStarting setup proccess");
            players[count] = all;
            count++;
        }
        int numOfTeams = playerCount/2;
        //RandomizeTeams.setupTeams(players, numOfTeams);
        return false;
   }
}
