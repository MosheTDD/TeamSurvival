package org.mamadev.teamsurvival;

import org.bukkit.plugin.java.JavaPlugin;
import org.mamadev.teamsurvival.utils.Utils;

public final class TeamSurvival extends JavaPlugin {

    @Override
    public void onEnable() {
        new Utils(this);


    }

    @Override
    public void onDisable() {

    }
}
