package it.forgottenworld.customtownyupkeep;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class CtuCommand implements CommandExecutor{

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length > 0 && args[0].equalsIgnoreCase("reload")){
            try{
                CustomTownyUpkeep.instance.loadConfig();
                sender.sendMessage(ChatColor.GREEN + "Config reloaded!");
            }catch (IOException e){
                sender.sendMessage(ChatColor.DARK_RED + "Error while reloading the config. See console for further information");
                e.printStackTrace();
            }
        }else{
            sender.sendMessage(ChatColor.DARK_RED + "Use /ctu reload");
        }
        return true;
    }
}
