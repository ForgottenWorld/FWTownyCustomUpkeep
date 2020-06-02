package it.forgottenworld.customtownyupkeep;

import com.palmergames.bukkit.towny.event.NationUpkeepCalculationEvent;
import com.palmergames.bukkit.towny.event.TownUpkeepCalculationEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class CustomTownyUpkeep extends JavaPlugin implements Listener {

    private Config customUpkeepList;

    @Override
    public void onEnable() {
        try {
            customUpkeepList = new Config("custom-upkeep-list.yml", this);
            Bukkit.getPluginManager().registerEvents(this, this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @EventHandler
    public void onTownUpkeepsCalculated(TownUpkeepCalculationEvent event) {
        if (customUpkeepList.getConfig().getStringList("towns-list").contains(event.getTown().getName())) {
            event.setUpkeep(event.getUpkeep() * customUpkeepList.getConfig().getDouble(event.getTown().getName()));
        }
    }

    @EventHandler
    public void onNationUpkeepsCalculated(NationUpkeepCalculationEvent event) {
        if (customUpkeepList.getConfig().getStringList("nations-list").contains(event.getNation().getName())) {
            event.setUpkeep(event.getUpkeep() * customUpkeepList.getConfig().getDouble(event.getNation().getName()));
        }
    }

}
