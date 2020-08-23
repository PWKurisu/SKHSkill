package com.kurisu.skill.skills;

import com.kurisu.skill.modules.CoolDown;
import com.kurisu.skill.modules.HealthManager;
import com.kurisu.skill.modules.NearbyEntities;
import com.kurisu.skill.util.NumberInsert;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class LeapSkill implements Skill {
    private final CoolDown coolDown;
    private final NearbyEntities nearbyEntities;
    private final HealthManager healthManager;
    private final Set<Player> jumping = new HashSet();

    public LeapSkill(int damage, int coolDown) {
        this.coolDown = new CoolDown(coolDown);
        this.nearbyEntities = new NearbyEntities(5);
        this.healthManager = new HealthManager(damage);
    }

    public boolean isJumping(Player player){
        return this.jumping.contains(player);
    }

    @Override
    public void castSkill(Player player) {
        UUID uuid = player.getUniqueId();
        if(!this.coolDown.isCoolDown(uuid)) {
            this.coolDown.setCoolDown(uuid);
            Vector v = player.getLocation().getDirection();
            player.setVelocity(v.normalize().multiply(2).setY(1));
            if(player.getGameMode() == GameMode.SURVIVAL || player.getGameMode() == GameMode.ADVENTURE) {
                this.jumping.add(player);
            }
        } else {
            player.sendMessage(this.coolDown.getCoolDownFormatString(uuid, "쿨타임 %s초 남았습니다."));
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event){
        if (event.getCause() != EntityDamageEvent.DamageCause.FALL || !(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        if (this.jumping.isEmpty()) return;
        if (!this.jumping.remove(player)) return;
        event.setCancelled(true);
        this.healthManager.damageEntities(this.nearbyEntities.getNearbyLivingEntities(player, false));
    }
}

/*
[스킬]: 도약
[발동조건]: 쿨타임
 */