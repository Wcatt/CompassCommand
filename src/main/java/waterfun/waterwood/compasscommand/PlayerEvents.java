package waterfun.waterwood.compasscommand;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import me.clip.placeholderapi.PlaceholderAPI;
import org.waterwood.plugin.bukkit.BukkitPlugin;

public class PlayerEvents implements Listener{
    @EventHandler
    public void onPlayJoin(PlayerJoinEvent evt){
        Player player = evt.getPlayer();
        Inventory inv = player.getInventory();
        Methods.updateItem(player);
        if(BukkitPlugin.getConfigs().getBoolean("give-on-join")){
            if(!(inv.contains(Methods.getItemStack()))){
                inv.addItem(Methods.getItemStack());
            }
        }
    }
    @EventHandler
    public void onClick(PlayerInteractEvent evt) {
        if (evt.getMaterial() == Material.getMaterial(BukkitPlugin.getConfigs().getString("material"))) {
            if (((evt.getAction().equals(Action.RIGHT_CLICK_AIR) || evt.getAction().equals(Action.RIGHT_CLICK_BLOCK) )&& BukkitPlugin.getConfigs().getBoolean("right-click"))
                    || ((evt.getAction().equals(Action.LEFT_CLICK_AIR)||evt.getAction().equals(Action.LEFT_CLICK_BLOCK)) && BukkitPlugin.getConfigs().getBoolean("left-click"))) {
                evt.setCancelled(true);
                Player player = evt.getPlayer();
                String command = BukkitPlugin.getConfigs().getString("player-command");
                if (! command.isEmpty()) {
                    if (CompassCommand.hasPapi()) command = PlaceholderAPI.setPlaceholders(player, command);
                    evt.getPlayer().chat("/" + command);
                }
                command = BukkitPlugin.getConfigs().getString("server-command");
                if (! command.isEmpty()){
                    CommandSender sender = Bukkit.getConsoleSender();
                    if (CompassCommand.hasPapi()) command = PlaceholderAPI.setPlaceholders(player,command);
                    sender.getServer().dispatchCommand(sender,command);
                }
                String message = BukkitPlugin.getConfigs().getString("command-message");
                if (! message.isEmpty()) {
                    if (CompassCommand.hasPapi()) message = PlaceholderAPI.setPlaceholders(player,message);
                    evt.getPlayer().sendMessage(message);
                }

            }
        }
    }
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent evt){
        if(!(BukkitPlugin.getConfigs().getBoolean("allow-player-drop"))) {
            ItemStack LIS = evt.getItemDrop().getItemStack();
            if (Methods.getItemStack().equals(LIS)) {
                evt.getItemDrop().remove();
                evt.getPlayer().getInventory().addItem(LIS);
            }
        }
    }
    @EventHandler
    public void onPlayerDeath(PlayerRespawnEvent evt){
        Player player = evt.getPlayer();
        Inventory inv = player.getInventory();
        Methods.updateItem(player);
        if (!(inv.contains(Methods.getItemStack()))){
            inv.addItem(Methods.getItemStack());
        }
    }
}

