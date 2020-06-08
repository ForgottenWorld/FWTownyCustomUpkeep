package it.forgottenworld.customtownyupkeep;

import it.forgottenworld.customtownyupkeep.commands.CtuCommand;
import it.forgottenworld.customtownyupkeep.listeners.TownyListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.Objects;

public final class CustomTownyUpkeep extends JavaPlugin{

    public static CustomTownyUpkeep INSTANCE;
    private Config customUpkeepList;

    @Override
    public void onEnable() {
        INSTANCE = this;
        try {
            loadConfig();
            Bukkit.getPluginManager().registerEvents(new TownyListener(), this);
            Objects.requireNonNull(this.getCommand("ctu")).setExecutor(new CtuCommand());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadConfig() throws IOException {
        customUpkeepList = new Config("custom-upkeep-list.yml", this);
    }

    public Config getCustomUpkeepList() {
        return customUpkeepList;
    }
}
