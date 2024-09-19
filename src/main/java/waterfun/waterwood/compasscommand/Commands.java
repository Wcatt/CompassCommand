package waterfun.waterwood.compasscommand;

import org.bukkit.command.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import me.waterwood.bukkit.BukkitPlugin;

import java.util.ArrayList;
import java.util.List;

public class Commands implements CommandExecutor, TabCompleter {
    public BukkitPlugin plugin;
    public Commands(BukkitPlugin plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender.hasPermission("compasscommand.admin")){
            if(args.length > 1){
                plugin.logMsg(BukkitPlugin.getPluginMessage("illegal-args-message"));
                return false;
            }
            if(command.getName().equals("compasscommand") ){
                if(args.length == 0) {
                    sender.sendMessage(BukkitPlugin.getPluginInfo());
                }else{
                    CompassCommand.getInstance().reloadConfig();
                    Methods.init(plugin);
                    sender.sendMessage(BukkitPlugin.getPluginMessage("config-reload-message"));
                }
            }
        }else{
            sender.sendMessage(BukkitPlugin.getPluginMessage("no-permission-message"));
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
