package org.mamadev.teamsurvival.listeners;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.mamadev.teamsurvival.TeamSurvival;

public class EntityListener implements Listener {
    private TeamSurvival plugin = TeamSurvival.getInstance();

    Player red1;
    Player red2;
    Player green1;
    Player green2;
    Player blue1;
    Player blue2;
    Player yellow1;
    Player yellow2;

    public void onEntityDamage(EntityDamageByEntityEvent e){
        boolean damaged = e.getEntity() instanceof Player;
        boolean damager = e.getDamager() instanceof Player;
        if (damaged && damager) {
            OfflinePlayer damagerPlayer = (OfflinePlayer) e.getDamager();
            OfflinePlayer damagedPlayer = (OfflinePlayer) e.getEntity();
            if (TeamSurvival.red.contains(damagerPlayer) && TeamSurvival.red.contains(damagedPlayer)){
                e.setCancelled(true);
                red1 = damagedPlayer.getPlayer();
                red2 = damagedPlayer.getPlayer();
                boolean red = true;
            } else if (TeamSurvival.green.contains(damagerPlayer) && TeamSurvival.green.contains(damagedPlayer)){
                e.setCancelled(true);
                green1 = damagedPlayer.getPlayer();
                green2 = damagedPlayer.getPlayer();
                boolean red = true;
            }
            /* if (both player's in the same cached team){
            e.setCancelled(true);
        */
        }
    }
}
