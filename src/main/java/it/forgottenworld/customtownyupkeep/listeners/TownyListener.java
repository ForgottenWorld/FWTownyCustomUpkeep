package it.forgottenworld.customtownyupkeep.listeners;

import com.palmergames.bukkit.towny.event.NationUpkeepCalculationEvent;
import com.palmergames.bukkit.towny.event.TownUpkeepCalculationEvent;
import it.forgottenworld.customtownyupkeep.TownyCustomUpkeep;
import it.forgottenworld.customtownyupkeep.api.events.CustomNationUpkeepCalculationEvent;
import it.forgottenworld.customtownyupkeep.api.events.CustomTownUpkeepCalculationEvent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class TownyListener implements Listener {

    @EventHandler
    public void onTownUpkeepCalculated(TownUpkeepCalculationEvent event) {
        ConfigurationSection townsTaxes = TownyCustomUpkeep.INSTANCE.getCustomUpkeepList().getConfig().getConfigurationSection("towns-list");
        assert townsTaxes != null;
        if (townsTaxes.contains(event.getTown().getName())) {
            ConfigurationSection townSection = townsTaxes.getConfigurationSection(event.getTown().getName());
            event.setUpkeep(event.getUpkeep() * townSection.getDouble("upkeep"));
            if(townSection.getBoolean("override")){
                return;
            }
        }
        Bukkit.getScheduler().callSyncMethod(TownyCustomUpkeep.INSTANCE,()->{
            Bukkit.getPluginManager().callEvent(new CustomTownUpkeepCalculationEvent(event));
            return null;
        });
    }

    @EventHandler
    public void onNationUpkeepCalculated(NationUpkeepCalculationEvent event) {
        ConfigurationSection nationsTaxes = TownyCustomUpkeep.INSTANCE.getCustomUpkeepList().getConfig().getConfigurationSection("nations-list");
        assert nationsTaxes != null;
        if (nationsTaxes.contains(event.getNation().getName())) {
            ConfigurationSection nationSection = nationsTaxes.getConfigurationSection(event.getNation().getName());
            event.setUpkeep(event.getUpkeep() * nationSection.getDouble("upkeep"));
            if(nationSection.getBoolean("override")){
                return;
            }
        }
        Bukkit.getScheduler().callSyncMethod(TownyCustomUpkeep.INSTANCE,()->{
            Bukkit.getPluginManager().callEvent(new CustomNationUpkeepCalculationEvent(event));
            return null;
        });
    }
}
