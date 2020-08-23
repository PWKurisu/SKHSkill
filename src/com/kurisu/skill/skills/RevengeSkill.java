package com.kurisu.skill.skills;

import com.kurisu.skill.buff.BuffManager;
import com.kurisu.skill.modules.CoolDown;
import com.kurisu.skill.modules.HealthManager;
import org.bukkit.entity.Player;

public class RevengeSkill implements Skill {
    private final CoolDown coolDown;
    private final HealthManager healthManager;
    private final BuffManager buffManager;

    public RevengeSkill() {
        this.coolDown = new CoolDown(60);
        this.healthManager = new HealthManager();
        this.buffManager = new BuffManager();
    }

    @Override
    public void castSkill(Player player) {
        //쿨 확인
        this.healthManager.damageEntityOfPercent(player, 70);
        //버프
    }
}

/*
[스킬]: 복수심
 - 현재 체력의 70% 를 소모하여 5초간 최종 데미지 증가 15% 버프를 얻는다
 - 다음 5초간 공격에 상대 최대 체력 1% 에 해당하는 추가 피해를 부여한다.
[발동조건]: 쿨타임 60초
 */