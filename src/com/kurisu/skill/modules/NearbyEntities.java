package com.kurisu.skill.modules;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class NearbyEntities {
    private final int size;

    public NearbyEntities(int size) {
        this.size = size;
    }

    public LivingEntity[] getNearbyLivingEntities(Player player) {
        return this.getNearbyLivingEntities(player.getLocation());
    }

    private LivingEntity[] getNearbyLivingEntities(Location location) {
        List<LivingEntity> entities = new ArrayList<>();
        for(Entity entity : location.getWorld().getEntities()) {
            if(entity instanceof LivingEntity && location.distance(entity.getLocation()) <= size) {
                entities.add((LivingEntity) entity);
            }
        }
        return entities.toArray(new LivingEntity[0]);
    }
}
