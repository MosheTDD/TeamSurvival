package org.mamadev.teamsurvival.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.mamadev.teamsurvival.TeamSurvival;

public class EntityListener implements Listener {
    private TeamSurvival plugin = TeamSurvival.getInstance();

    public void onEntityDamage(EntityDamageByEntityEvent e){
        boolean damaged = e.getEntity() instanceof Player;
        boolean damager = e.getDamager() instanceof Player;
        if (damaged && damager) {
            /* if (both player's in the same cached team){
            e.setCancelled(true);
        */
        }
    }
}
