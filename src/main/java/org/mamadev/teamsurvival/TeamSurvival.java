package org.mamadev.teamsurvival;

import org.bukkit.plugin.java.JavaPlugin;
import org.mamadev.teamsurvival.commands.Setup;

public class TeamSurvival extends JavaPlugin {

    public static TeamSurvival instance;

    public static TeamSurvival getInstance(){
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        getCommand("setup").setExecutor(new Setup());
    }

    @Override
    public void onDisable() {

    }
}
