package xyz.tangerie.automation.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import xyz.tangerie.automation.AdvancedAutomation;

import java.util.Arrays;

public class AutomatedUtil {
    public static final NamespacedKey TYPE_KEY = new NamespacedKey(AdvancedAutomation.getPlugin(), "type");
    public static final NamespacedKey RECIPE_KEY = new NamespacedKey(AdvancedAutomation.getPlugin(), "recipe");

    public static ItemStack generateWand() {
        ItemStack stack = new ItemStack(Material.STICK);
        ItemMeta meta = stack.getItemMeta();

        meta.setDisplayName(ChatColor.AQUA + "AA Wand");
        meta.setLore(Arrays.asList(ChatColor.GRAY + "Left click to edit", ChatColor.GRAY + "Right click to view information"));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS);
        meta.addEnchant(Enchantment.WATER_WORKER, 0, true);
        meta.getPersistentDataContainer().set(AutomatedUtil.TYPE_KEY, PersistentDataType.STRING, "wand");

        stack.setItemMeta(meta);

        return stack;
    }

    public static boolean isItemSpecial(ItemStack stack) {
        return stack.getItemMeta().getPersistentDataContainer().has(AutomatedUtil.TYPE_KEY, PersistentDataType.STRING);
    }
}
