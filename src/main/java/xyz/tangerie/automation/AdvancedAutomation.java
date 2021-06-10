package xyz.tangerie.automation;

import co.aikar.commands.PaperCommandManager;
import com.google.common.collect.ImmutableList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.tangerie.automation.commands.AACommand;

import java.util.Arrays;
import java.util.stream.Collectors;

public class AdvancedAutomation extends JavaPlugin {
    private static AdvancedAutomation plugin;

    @Override
    public void onEnable() {
        FileConfiguration config = getConfig();
        config.options().copyDefaults(true);
        saveConfig();

        plugin = this;

        PaperCommandManager manager = new PaperCommandManager(this);
        manager.enableUnstableAPI("help");
        manager.registerCommand(new AACommand());

        registerCompletions(manager);

        Recipes.registerAll();

        getServer().getScheduler().runTaskLater(this, new Runnable() {
            @Override
            public void run() {
                getServer().broadcastMessage("Advanced Automation Online");
            }
        }, 0L);
    }

    @Override
    public void onDisable() {
        Bukkit.broadcastMessage("Advanced Automation Shutting Down");
    }

    public static AdvancedAutomation getPlugin() {
        return plugin;
    }

    private void registerCompletions(PaperCommandManager manager) {
        manager.getCommandCompletions().registerAsyncCompletion("items", c -> {
            return Arrays.stream(Material.values()).map(x -> x.name()).collect(Collectors.toList());
        });
    }
}
