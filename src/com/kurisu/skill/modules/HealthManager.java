package com.kurisu.skill.modules;

import org.bukkit.entity.LivingEntity;

public class HealthManager {
    private final int heal;
    private final int damage;

    public HealthManager() {
        this.heal = 0;
        this.damage = 0;
    }
    public HealthManager(int amount) {
        this.heal = amount;
        this.damage = amount;
    }
    public HealthManager(int heal, int damage) {
        this.heal = heal;
        this.damage = damage;
    }

    public void damageEntities(LivingEntity... entities) {
        for(LivingEntity entity : entities) {
            entity.damage(this.damage);
        }
    }
    public void healEntities(LivingEntity... entities) {
        for(LivingEntity entity : entities) {
            //heal
        }
    }
}
