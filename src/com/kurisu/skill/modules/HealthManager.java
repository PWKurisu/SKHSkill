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
            this.damageEntities(entity);
        }
    }
    public void healEntities(LivingEntity... entities) {
        for(LivingEntity entity : entities) {
            this.healEntity(entity);
        }
    }
    public void damageEntity(LivingEntity entity) {
        entity.damage(this.damage);
    }
    public void healEntity(LivingEntity entity) {
        entity.setHealth(entity.getHealth()+this.heal);
    }
    public void damageEntityOfPercent(LivingEntity entity, int percent) {
        entity.damage(entity.getHealth()*(percent/100.0));
    }
}
