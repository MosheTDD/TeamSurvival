package org.mamadev.teamsurvival;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.mamadev.teamsurvival.file.TeamData;

import static org.mamadev.teamsurvival.utils.Utils.*;
import java.util.*;

public class RandomizeTeams {
    private static Location TP;

    // Cache teams on plugin startup if teams exist already
    // Check if teams are setup already using a setupped boolean
    // Loop thru the map and get the entrys to split them into teams
    public static void setupTeams(ArrayList<Player> players, int numOfTeams){
        Random rnd = new Random();

        for(int i = 0; i <= numOfTeams; i++) {
            int player1 = rnd.nextInt(players.size() - 1);
            int player2 = rnd.nextInt(players.size() - 1);
            while (player1 == player2){
                player2 = rnd.nextInt(players.size() - 1);
            }
            ArrayList<UUID> team = new ArrayList<>();
            team.add(players.get(player1).getUniqueId());
            team.add(players.get(player2).getUniqueId());
            Player playerObj1 = players.get(player1);
            Player playerObj2 = players.get(player2);
            players.remove(player1);
            players.remove(player2);
            String teamName = csl("teams").get(i);
            getTeams().put(teamName, team);
            for(Player p : Bukkit.getOnlinePlayers()){
                p.sendMessage(colorize("&7" + teamName + "&f: &c" + playerObj1.getName() + "&f, &c" + playerObj2.getName()));
            }
            genCoord();
            tpPlayer(playerObj1);
            tpPlayer(playerObj2);
        }
    }

    public static void tpPlayer(Player teamPlayer){
        teamPlayer.teleport(TP);
    }

    private static void genCoord() {
        World world = Bukkit.getWorld("world");
        Random rnd = new Random();
        int rndX = rnd.nextInt(2500);
        int rndZ = rnd.nextInt(2500);
        int y = world.getHighestBlockYAt(rndX, rndZ);
        TP = new Location(world, rndX + 0.5, y, rndZ + 0.5);
        final Location TPy = new Location(TP.getWorld(), TP.getX(), TP.getY() - 1.0, TP.getZ());
        if (TP.getWorld().getBlockAt(TP).getType().equals(Material.LAVA) || TPy.getWorld().getBlockAt(TPy).getType().equals(Material.LAVA) || TPy.getWorld().getBlockAt(TPy).getType().equals(Material.WATER) || TPy.getWorld().getBlockAt(TPy).getType().equals(Material.AIR)) {
            genCoord();
            return;
        }
    }

    private static HashMap<String, ArrayList<UUID>> getTeams(){
        return TeamData.teams;
    }
}
