package it.forgottenworld.customtownyupkeep.commands;

import com.palmergames.bukkit.towny.Towny;
import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Town;
import it.forgottenworld.customtownyupkeep.Config;
import it.forgottenworld.customtownyupkeep.CustomTownyUpkeep;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class  CtuCommand implements CommandExecutor{

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length > 0){
            if(args[0].equalsIgnoreCase("reload")){
                try{
                    CustomTownyUpkeep.INSTANCE.loadConfig();
                    sender.sendMessage(ChatColor.GREEN + "Config reloaded!");
                }catch (IOException e){
                    sender.sendMessage(ChatColor.DARK_RED + "Error while reloading the config. See console for further information");
                    e.printStackTrace();
                }
            }else if(args[0].equalsIgnoreCase("set")){
                if(args.length >= 4){
                    if(args[1].equalsIgnoreCase("town")){
                        try {
                            Town town = TownyAPI.getInstance().getDataSource().getTown(args[2]);
                            Config config = CustomTownyUpkeep.INSTANCE.getCustomUpkeepList();
                            ConfigurationSection townConfig = config.getConfig().getConfigurationSection("towns-list").createSection(town.getName());
                            townConfig.set("upkeep", Double.parseDouble(args[3]));
                            if(args.length > 4){
                                townConfig.set("override", Boolean.parseBoolean(args[4]));
                            }
                        } catch (NotRegisteredException e) {
                           sender.sendMessage(ChatColor.RED + "Invalid town name");
                        }
                    }else if(args[1].equalsIgnoreCase("nation")){
                        try{
                            Nation nation = TownyAPI.getInstance().getDataSource().getNation(args[2]);
                            Config config = CustomTownyUpkeep.INSTANCE.getCustomUpkeepList();
                            ConfigurationSection nationConfig = config.getConfig().getConfigurationSection("nations-list").createSection(nation.getName());
                            nationConfig.set("upkeep", Double.parseDouble(args[3]));
                            if(args.length > 4){
                                nationConfig.set("override", Boolean.parseBoolean(args[4]));
                            }
                        }catch (NotRegisteredException e){
                            sender.sendMessage(ChatColor.RED + "Invalid nation name");
                        }
                    }else{
                        if(sender instanceof Player) sendHelp((Player)sender);
                    }
                }
            }else{
                if(sender instanceof Player) sendHelp((Player)sender);
            }
        }else{
            if(sender instanceof Player) sendHelp((Player)sender);
        }
        return true;
    }

    private void sendHelp(Player player){
        player.sendMessage(ChatColor.DARK_GREEN + "------{ CustomTownyUpkeep }------");
        player.sendMessage(ChatColor.GREEN + "" + ChatColor.ITALIC + "/ctu reload" + ChatColor.RESET + "" +
                ChatColor.GREEN + " reload the config");
        player.sendMessage(ChatColor.GREEN + "" + ChatColor.ITALIC + "/ctu set town <town> <multiplier>" + ChatColor.RESET + "" +
                ChatColor.GREEN + " set a custom upkeep");
        player.sendMessage(ChatColor.GREEN + "" + ChatColor.ITALIC + "/ctu set nation <nation> <multiplier>" + ChatColor.RESET + "" +
                ChatColor.GREEN + " set a custom upkeep");
        player.sendMessage(ChatColor.DARK_GREEN + "---------------------------------");
    }
}
