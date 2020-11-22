package it.forgottenworld.customtownyupkeep.api.events;

import com.palmergames.bukkit.towny.event.NationUpkeepCalculationEvent;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class CustomNationUpkeepCalculationEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    private final NationUpkeepCalculationEvent event;

    public CustomNationUpkeepCalculationEvent(NationUpkeepCalculationEvent event) {
        this.event = event;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public Nation getNation() {
        return this.event.getNation();
    }

    public double getUpkeep() {
        return this.event.getUpkeep();
    }

    public void setUpkeep(double upkeep) {
        this.event.setUpkeep(upkeep);
    }
}

