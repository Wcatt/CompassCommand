package waterfun.waterwood.compasscommand;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import me.clip.placeholderapi.PlaceholderAPI;
import org.waterwood.io.FileConfigProcess;
import org.waterwood.plugin.bukkit.BukkitPlugin;

import java.util.ArrayList;

public  class Methods {
    private static ItemStack is;
    private static BukkitPlugin plugin;
    private static FileConfigProcess config;
    public static void init(BukkitPlugin plugin){
        Methods.plugin = plugin;
        config = BukkitPlugin.getConfigs();
        is = new ItemStack(Material.getMaterial(config.getString("material")));
    }

    public static ItemStack getItemStack(){
        return is;
    }
    public static void updateItem(Player player){
        ItemMeta im = is.getItemMeta();
        ArrayList<String> lores = config.getStringList("lores");
        String disPlayerNameText = config.getString("display-name");
        if (CompassCommand.hasPapi()){
            for(String lore: lores){
                lores.set(lores.indexOf(lore),PlaceholderAPI.setPlaceholders(player, lore)) ;
            }
            disPlayerNameText = PlaceholderAPI.setPlaceholders(player,disPlayerNameText);
        }
        im.setDisplayName(disPlayerNameText);
        im.setLore(lores);
        is.setItemMeta(im);
    }

}
