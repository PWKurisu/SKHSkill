package com.kurisu.skill.skills;

import com.kurisu.skill.modules.Charge;
import com.kurisu.skill.modules.HealthManager;
import com.kurisu.skill.modules.NearbyEntities;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PatienceSkill implements Skill {
    private final Charge charge;
    private final NearbyEntities nearbyEntities;
    private final HealthManager healthManager;

    public PatienceSkill() {
        this.charge = new Charge(15, 1, 4);
        this.nearbyEntities = new NearbyEntities(6);
        this.healthManager = new HealthManager(0, 1000);
    }

    @Override
    public void castSkill(Player player) {
        UUID uuid = player.getUniqueId();
        if(charge.isCharge(uuid, 1)) {
            this.charge.useCharge(uuid, 1);
            player.sendMessage(this.charge.getChargeFormatString(uuid, "끈기 발동! [%s/%s]", true));
            this.healthManager.healEntities(this.nearbyEntities.getNearbyLivingEntities(player, true));
        } else {
            player.sendMessage(this.charge.getChargeFormatString(uuid, "충전이 필요합니다! [%s/%s]", true));
        }
    }
}

/*
[스킬]: 끈기
 - 즉시 체력 1000과 최대 체력의 10%를 회복한다.
 - 시전자 주위의 플레이어(6칸 이내)들도 해당 플레이어들도 체력 1000을 회복한다.
[발동조건]: 15초마다 1회 충전되며 최대 4회까지 충전된다.
 */