package it.forgottenworld.customtownyupkeep.listeners;

import com.palmergames.bukkit.towny.event.NationUpkeepCalculationEvent;
import com.palmergames.bukkit.towny.event.TownUpkeepCalculationEvent;
import it.forgottenworld.customtownyupkeep.CustomTownyUpkeep;
import it.forgottenworld.customtownyupkeep.api.events.CustomUpkeepCalculationEvent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class TownyListener implements Listener {

    @EventHandler
    public void onTownUpkeepsCalculated(TownUpkeepCalculationEvent event) {
        ConfigurationSection townsTaxes = CustomTownyUpkeep.INSTANCE.getCustomUpkeepList().getConfig().getConfigurationSection("towns-list");
        assert townsTaxes != null;
        if (townsTaxes.contains(event.getTown().getName())) {
            ConfigurationSection townSection = townsTaxes.getConfigurationSection(event.getTown().getName());
            event.setUpkeep(event.getUpkeep() * townSection.getDouble("upkeep"));
            if(townSection.getBoolean("override")){
                return;
            }
        }
        Bukkit.getPluginManager().callEvent(new CustomUpkeepCalculationEvent(event.getUpkeep()));
    }

    @EventHandler
    public void onNationUpkeepsCalculated(NationUpkeepCalculationEvent event) {
        ConfigurationSection nationsTaxes = CustomTownyUpkeep.INSTANCE.getCustomUpkeepList().getConfig().getConfigurationSection("nations-list");
        assert nationsTaxes != null;
        if (nationsTaxes.contains(event.getNation().getName())) {
            ConfigurationSection nationSection = nationsTaxes.getConfigurationSection(event.getNation().getName());
            event.setUpkeep(event.getUpkeep() * nationSection.getDouble("upkeep"));
            if(nationSection.getBoolean("override")){
                return;
            }
        }
        Bukkit.getPluginManager().callEvent(new CustomUpkeepCalculationEvent(event.getUpkeep()));
    }
}
