package it.forgottenworld.customtownyupkeep.api.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class CustomUpkeepCalculationEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    private double upkeep;

    public CustomUpkeepCalculationEvent(double upkeep) {
        this.upkeep = upkeep;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public double getUpkeep() {
        return upkeep;
    }

    public void setUpkeep(double upkeep) {
        this.upkeep = upkeep;
    }
}
