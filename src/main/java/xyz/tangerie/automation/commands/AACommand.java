package xyz.tangerie.automation.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.google.common.collect.Multimap;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.Nullable;
import xyz.tangerie.automation.AdvancedAutomation;
import xyz.tangerie.automation.util.AutomatedUtil;
import xyz.tangerie.automation.util.RecipeItemUtil;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.bukkit.Bukkit.getLogger;

@CommandAlias("aa|advancedautomation")
public class AACommand extends BaseCommand {
    @Subcommand("item")
    @Description("Gets info of current item")
    public static void getItemInfo(Player p) {
        ItemStack stack = p.getInventory().getItemInMainHand();
        if(stack != null) {
            ItemMeta meta = stack.getItemMeta();
            if(meta == null) return;

            PersistentDataContainer cont = meta.getPersistentDataContainer();

            if(!cont.has(AutomatedUtil.TYPE_KEY, PersistentDataType.STRING)) return;

            p.sendRawMessage(ChatColor.AQUA + "AA Item Info");

            String type = cont.get(AutomatedUtil.TYPE_KEY, PersistentDataType.STRING);

            p.sendRawMessage(ChatColor.YELLOW + "Type - " + ChatColor.RESET + type);

            switch (type) {
                case "recipe":
                    p.sendRawMessage(ChatColor.YELLOW + "Recipe - " + ChatColor.RESET + cont.get(AutomatedUtil.RECIPE_KEY, PersistentDataType.STRING));
                    break;
            }
        }
    }

    @Subcommand("wand")
    @Description("Get a wand")
    public static void giveWand(Player p) {
        p.getInventory().addItem(AutomatedUtil.generateWand());
    }

    @Subcommand("recipe")
    @Description("Get an empty recipe")
    public static void giveEmptyRecipe(Player p) {
        p.getInventory().addItem(RecipeItemUtil.getEmptyRecipe());
    }

    @Subcommand("recipe")
    @Description("Get a recipe of type")
    @CommandCompletion("@items")
    public static void giveRecipe(Player p, String itemName) {
        Material mat = Material.matchMaterial(itemName);
        if(mat == null) {
            p.sendRawMessage(ChatColor.RED + "Item not found");
            return;
        }

        p.getInventory().addItem(RecipeItemUtil.getRecipeFromMaterial(mat));
    }
}
