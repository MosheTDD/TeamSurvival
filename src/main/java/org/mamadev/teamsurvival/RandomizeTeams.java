package org.mamadev.teamsurvival;

import org.bukkit.*;
import org.bukkit.entity.Player;
import static org.mamadev.teamsurvival.utils.Utils.*;
import java.util.*;

public class RandomizeTeams {

    public static void setupTeams(ArrayList<Player> players, int numOfTeams){
        HashMap<String, ArrayList<Player>> teams = new HashMap<>();
        Random rnd = new Random();

        for(int i = 0; i <= numOfTeams; i++) {
            int player1 = rnd.nextInt(players.size() - 1);
            int player2 = rnd.nextInt(players.size() - 1);
            while (player1 == player2){
                player2 = rnd.nextInt(players.size() - 1);
            }
            ArrayList<Player> team = new ArrayList<>();
            team.add(players.get(player1));
            team.add(players.get(player2));
            players.remove(player1);
            players.remove(player2);
            String teamName = csl("teams").get(i);
            teams.put(teamName, team);
            for(Player p : Bukkit.getOnlinePlayers()){
                p.sendMessage(colorize("&7" + teamName + "&f: &c" + team.get(0).getName() + "&f, &c" + team.get(1).getName()));
            }
            tpTeam(team);
        }
    }

    public static void tpTeam(ArrayList<Player> team){
        genCoord();
        for(Player teamPlayer : team){
            teamPlayer.teleport(TP);
        }
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

    private static Location TP;

}
