package com.scrypto.blockfeet;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockFeetPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        event.setDropItems(false); // Disable default item drop behavior
        Block block = event.getBlock();
        Material material = block.getType();
        ItemStack itemStack = new ItemStack(material); // Convert material to ItemStack
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(material.name()); // Optional: Set display name to material name
            itemStack.setItemMeta(meta);
        }
        // Drop the item under the player's feet
        Player player = event.getPlayer();
        Location playerLocation = player.getLocation().clone(); // Clone the player's location to prevent modifying the
                                                                // original
        playerLocation.setY(playerLocation.getY() - 1); // Adjust the Y-coordinate to drop the item under the player's
                                                        // feet
        player.getWorld().dropItem(playerLocation, itemStack);
        block.setType(Material.AIR); // Set block to air after dropping
    }
}
