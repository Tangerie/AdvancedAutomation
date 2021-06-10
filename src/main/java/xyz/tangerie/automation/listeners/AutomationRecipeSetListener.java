package xyz.tangerie.automation.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.Nullable;
import xyz.tangerie.automation.util.AutomatedUtil;
import xyz.tangerie.automation.util.RecipeItemUtil;

public class AutomationRecipeSetListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if(e.getItem() == null) return;
        if(AutomatedUtil.isItemSpecial(e.getItem())) {
            PersistentDataContainer cont = e.getItem().getItemMeta().getPersistentDataContainer();

            String type = cont.get(AutomatedUtil.TYPE_KEY, PersistentDataType.STRING);

            switch (type) {
                case "empty_recipe":
                    e.getPlayer().openWorkbench(null, true);
                    e.setCancelled(true);
                    return;
            }
        }
    }

    @EventHandler
    public void onCraftItem(CraftItemEvent e) {
        if(e.getWhoClicked() instanceof Player) {
            Player p = (Player)e.getWhoClicked();
            if(p.getInventory().getItemInMainHand() == null) return;

            PersistentDataContainer cont = p.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer();
            if(cont == null) return;

            String type = cont.get(AutomatedUtil.TYPE_KEY, PersistentDataType.STRING);

            if(!type.equals("empty_recipe")) return;

            e.getInventory().clear();

            ItemStack toCraft = e.getRecipe().getResult();

            ItemStack resultRecipe = RecipeItemUtil.getRecipeFromMaterial(toCraft.getType());

            p.getInventory().remove(RecipeItemUtil.getEmptyRecipe());
            p.getInventory().addItem(resultRecipe);

            p.closeInventory();
        }

    }
}
