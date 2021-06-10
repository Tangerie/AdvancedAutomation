package xyz.tangerie.automation.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;

import static org.bukkit.Bukkit.getLogger;

public class RecipeItemUtil {
    public static ItemStack getRecipeFromMaterial(Material mat) {
        ItemStack stack = new ItemStack(Material.PAPER);
        ItemMeta meta = stack.getItemMeta();

        ItemStack recipeStack = new ItemStack(mat);

        meta.setDisplayName(ChatColor.AQUA + recipeStack.getI18NDisplayName()  +  " Recipe");
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS);
        meta.addEnchant(Enchantment.WATER_WORKER, 0, true);
        meta.getPersistentDataContainer().set(AutomatedUtil.TYPE_KEY, PersistentDataType.STRING, "recipe");
        meta.getPersistentDataContainer().set(AutomatedUtil.RECIPE_KEY, PersistentDataType.STRING, mat.name());

        stack.setItemMeta(meta);

        return stack;
    }

    public static ItemStack getEmptyRecipe() {
        ItemStack stack = new ItemStack(Material.PAPER);
        ItemMeta meta = stack.getItemMeta();

        meta.setDisplayName(ChatColor.AQUA + "Empty Recipe");
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS);
        meta.addEnchant(Enchantment.WATER_WORKER, 0, true);
        meta.getPersistentDataContainer().set(AutomatedUtil.TYPE_KEY, PersistentDataType.STRING, "recipe");

        stack.setItemMeta(meta);

        return stack;
    }

    public static ItemStack getItemFromRecipe(ItemStack recipe) {
        PersistentDataContainer cont = recipe.getItemMeta().getPersistentDataContainer();
        if(!cont.has(AutomatedUtil.RECIPE_KEY, PersistentDataType.STRING)) return null;

        Material m = Material.getMaterial(cont.get(AutomatedUtil.RECIPE_KEY, PersistentDataType.STRING));
        if(m == null) return null;

        return new ItemStack(m);
    }
}
