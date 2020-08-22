package com.kurisu.skill.modules;

import com.kurisu.skill.util.NumberInsert;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CoolDown {
    private final int coolDown;
    private final Map<UUID, Long> coolDownMap = new HashMap<>();

    public CoolDown(int coolDown) {
        this.coolDown = coolDown;
    }

    public boolean isCoolDown(UUID uuid) {
        return getCoolDown(uuid) > 0;
    }
    public void setCoolDown(UUID uuid) {
        this.coolDownMap.put(uuid, System.currentTimeMillis());
    }
    public long getCoolDown(UUID uuid) {
        return this.coolDownMap.containsKey(uuid)
                ? ((this.coolDownMap.get(uuid)) + this.coolDown*1000) - System.currentTimeMillis() : 0L;
    }
    public String getCoolDownFormatString(UUID uuid, String format) {
        return String.format(format, NumberInsert.insert(getCoolDown(uuid), 3));
    }
}