package xyz.tangerie.automation;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ComplexRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.jetbrains.annotations.NotNull;
import xyz.tangerie.automation.util.RecipeItemUtil;

public class Recipes {
    public static void registerAll() {
        Bukkit.addRecipe(getEmptyRecipe());
    }

    private static ShapedRecipe getEmptyRecipe() {
        NamespacedKey key = new NamespacedKey(AdvancedAutomation.getPlugin(), "empty_recipe");

        ShapedRecipe recipe = new ShapedRecipe(key, RecipeItemUtil.getEmptyRecipe());
        recipe.shape("PPP", "PCP", "PPP");

        recipe.setIngredient('P', Material.PAPER);
        recipe.setIngredient('C', Material.CRAFTING_TABLE);

        return recipe;
    }
}
