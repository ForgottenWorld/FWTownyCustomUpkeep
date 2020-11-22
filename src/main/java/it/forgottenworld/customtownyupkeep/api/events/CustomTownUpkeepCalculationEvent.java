package it.forgottenworld.customtownyupkeep.api.events;

import com.palmergames.bukkit.towny.event.TownUpkeepCalculationEvent;
import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class CustomTownUpkeepCalculationEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    private final TownUpkeepCalculationEvent event;

    public CustomTownUpkeepCalculationEvent(TownUpkeepCalculationEvent event) {
        this.event = event;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public Town getTown() {
        return event.getTown();
    }

    public double getUpkeep() {
        return event.getUpkeep();
    }

    public void setUpkeep(double upkeep) {
        this.event.setUpkeep(upkeep);
    }
}
