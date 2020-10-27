package it.forgottenworld.customtownyupkeep.api.events;

import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class CustomNationUpkeepCalculationEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    private double upkeep;
    private final Nation nation;

    public CustomNationUpkeepCalculationEvent(Nation nation, double upkeep) {
        this.nation = nation;
        this.upkeep = upkeep;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public Nation getNation() {
        return nation;
    }

    public double getUpkeep() {
        return upkeep;
    }

    public void setUpkeep(double upkeep) {
        this.upkeep = upkeep;
    }
}

