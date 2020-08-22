package com.kurisu.skill;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class SkillBookListener implements Listener {
    private final SkillPlugin skillPlugin;

    public SkillBookListener(SkillPlugin skillPlugin) {
        this.skillPlugin = skillPlugin;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = player.getInventory().getItemInMainHand();
        String displayName = item.getItemMeta().getDisplayName();
        if((action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)
                && event.getHand() == EquipmentSlot.HAND
                && item.getType() == Material.ENCHANTED_BOOK && item.getItemMeta().hasDisplayName()
                && displayName.length() > 13 && displayName.contains("§f[스킬 부여: <")) {
            this.skillPlugin.getSkill(displayName.substring(11, displayName.length() - 2)).castSkill(player);
        }
    }
}
