//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package waterfun.waterwood.compasscommand;

import org.bukkit.Bukkit;
import org.waterwood.plugin.bukkit.BukkitPlugin;


public final class CompassCommand extends BukkitPlugin {
    private static boolean hasPapi = false;
    protected static CompassCommand instance;
    public void onEnable() {
        this.initialization();
        instance = this;
        this.showPluginTitle("CPCCMD");
        this.loadConfig(false);
        Methods.init(this);
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
            logMsg(getPluginMessage("papi-enabled-message"));
            hasPapi  = true;
        }
        logMsg(getPluginMessage("config-load-message"));
        this.checkUpdate("Wcatt","CompassCommand");
        this.getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
        getCommand("CompassCommand").setExecutor(new Commands(this));
        getCommand("CompassCommand").setTabCompleter(new Commands(this));

    }

    public void onDisable() {

    }

    public static CompassCommand getInstance() {
        return instance;
    }

    public static boolean hasPapi(){
        return hasPapi;
    }

}
