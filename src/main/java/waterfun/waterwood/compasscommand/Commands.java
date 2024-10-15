package waterfun.waterwood.compasscommand;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.waterwood.plugin.bukkit.BukkitPlugin;

import java.util.ArrayList;
import java.util.List;

public class
Commands implements CommandExecutor, TabCompleter {
    public BukkitPlugin plugin;
    public Commands(BukkitPlugin plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender.hasPermission("compasscommand.admin")){
            if(args.length >= 1){
                plugin.logMsg("illegal-args-message");
            }
            if(command.getName().equals("CompassCommand") ){
                if(args.length == 0) {
                    sender.sendMessage(BukkitPlugin.getPluginInfo());
                }else{
                    CompassCommand.getInstance().reloadConfig();
                    Methods.init(plugin);
                    plugin.logMsg(BukkitPlugin.getPluginMessage("config-reload-message"));
                }
            }
        }else{
            plugin.logMsg(BukkitPlugin.getPluginMessage("no-permission-message"));
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> suggestions = new ArrayList<>();
        if (args.length == 1) {
            suggestions.add("reload");
        }
        return suggestions;
    }
}
