package it.forgottenworld.customtownyupkeep.api.events;

import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class CustomTownUpkeepCalculationEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    private double upkeep;
    private final Town town;

    public CustomTownUpkeepCalculationEvent(Town town, double upkeep) {
        this.town = town;
        this.upkeep = upkeep;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public Town getTown() {
        return town;
    }

    public double getUpkeep() {
        return upkeep;
    }

    public void setUpkeep(double upkeep) {
        this.upkeep = upkeep;
    }
}
