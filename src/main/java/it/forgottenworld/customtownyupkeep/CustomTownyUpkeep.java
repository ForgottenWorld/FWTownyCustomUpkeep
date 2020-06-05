package it.forgottenworld.customtownyupkeep;

import com.palmergames.bukkit.towny.event.NationUpkeepCalculationEvent;
import com.palmergames.bukkit.towny.event.TownUpkeepCalculationEvent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.Objects;

public final class CustomTownyUpkeep extends JavaPlugin implements Listener {

    public static CustomTownyUpkeep instance;
    private Config customUpkeepList;

    @Override
    public void onEnable() {
        instance = this;
        try {
            loadConfig();
            Bukkit.getPluginManager().registerEvents(this, this);
            Objects.requireNonNull(this.getCommand("ctu")).setExecutor(new CtuCommand());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @EventHandler
    public void onTownUpkeepsCalculated(TownUpkeepCalculationEvent event) {
        ConfigurationSection townsTaxes = customUpkeepList.getConfig().getConfigurationSection("towns-list");
        assert townsTaxes != null;
        if (townsTaxes.contains(event.getTown().getName())) {
            event.setUpkeep(event.getUpkeep() * townsTaxes.getDouble(event.getTown().getName()));
        }
    }

    @EventHandler
    public void onNationUpkeepsCalculated(NationUpkeepCalculationEvent event) {
        ConfigurationSection nationsTaxes = customUpkeepList.getConfig().getConfigurationSection("nations-list");
        assert nationsTaxes != null;
        if (nationsTaxes.contains(event.getNation().getName())) {
            event.setUpkeep(event.getUpkeep() * nationsTaxes.getDouble(event.getNation().getName()));
        }
    }

    public void loadConfig() throws IOException {
        customUpkeepList = new Config("custom-upkeep-list.yml", this);
    }
}
