package com.kurisu.skill.skills;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public interface Skill extends Listener {
    void castSkill(Player player);
}
