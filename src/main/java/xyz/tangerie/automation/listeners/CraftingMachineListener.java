package xyz.tangerie.automation.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataType;
import xyz.tangerie.automation.util.AutomatedUtil;

public class CraftingMachineListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if(e.getItem() == null) {

        } else {
            if(AutomatedUtil.isItemSpecial(e.getItem())) {
                if(e.getItem().getItemMeta().getPersistentDataContainer().get(AutomatedUtil.TYPE_KEY, PersistentDataType.STRING).equals("recipe")) {
                    Material mat = Material.getMaterial(e.getItem().getItemMeta().getPersistentDataContainer().get(AutomatedUtil.RECIPE_KEY, PersistentDataType.STRING));
                    if(e.getClickedBlock().getType().equals(Material.DROPPER)) {
                        e.getPlayer().sendRawMessage("Applying " + mat.name() + " to Dropper");
                    } else {
                        e.getPlayer().sendRawMessage(ChatColor.RED + "Can only apply recipe to a dropper");
                    }
                    e.setCancelled(true);
                }
            }
        }
    }

    private boolean isBlockMachine(Block block) {

        return false;
    }
}
