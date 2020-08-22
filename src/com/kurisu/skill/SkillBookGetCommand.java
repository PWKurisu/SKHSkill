package com.kurisu.skill;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SkillBookGetCommand implements CommandExecutor {
    private SkillPlugin skillPlugin;

    public SkillBookGetCommand(SkillPlugin skillPlugin) {
        this.skillPlugin = skillPlugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        Player player = (Player) sender;
        if(args.length == 1 && this.skillPlugin.containsSkill(args[0])) {
            player.getInventory().addItem(getSkillBook(args[0]));
        } else {
            player.sendMessage("사용법: /getb <SkillName>\n  Skills:");
            for(String name : this.skillPlugin.getSkillList()) {
                player.sendMessage("    "+name);
            }
        }
        return true;
    }

    public ItemStack getSkillBook(String name) {
        ItemStack itemStack  = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§f[스킬 부여: <"+name+">]");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
