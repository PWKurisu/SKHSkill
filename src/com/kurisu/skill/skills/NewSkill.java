package com.kurisu.skill.skills;

import org.bukkit.entity.Player;

public class NewSkill implements Skill {

    public NewSkill() {
        //asd
        int i;
    }

    @Override
    public void castSkill(Player player) {

    }
}

/*
[복수심]: 현재 체력의 70% 를 소모하여 5초간 최종 데미지 증가 15% 버프를 얻는다
 다음 5초간 공격에 상대 최대 체력 1% 에 해당하는 추가 피해를 부여한다. (쿨타임 60초)
 */