package com.kurisu.skill.modules;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Charge {
    private final int seconds;
    private final int amount;
    private final int maximum;
    private final Map<UUID, Long> coolDownMap = new HashMap<>();
    private final Map<UUID, Integer> chargeMap = new HashMap<>();

    public Charge(int seconds, int amount, int maximum) {
        this.seconds = seconds;
        this.amount = amount;
        this.maximum = maximum;
    }

    private void updateCharge(UUID uuid) {
        if(this.chargeMap.containsKey(uuid)) {
            if(this.coolDownMap.containsKey(uuid)) {
                int i = this.chargeMap.get(uuid);
                long point = System.currentTimeMillis();
                this.coolDownMap.put(uuid, point);
                long result = (point - this.coolDownMap.get(uuid))/(this.seconds*1000);
                while(result > 0 && i < this.maximum) {
                    result -= this.seconds*1000;
                    i = Math.min(i + this.amount, this.maximum);
                    System.out.println(result+" "+i);
                }
                this.chargeMap.put(uuid, i);
            }
        } else {
            this.chargeMap.put(uuid, this.maximum);
        }
    }
    public int getCharge(UUID uuid) {
        updateCharge(uuid);
        return this.chargeMap.get(uuid);
    }
    public boolean isCharge(UUID uuid, int amount) {
        return this.maximum >= amount && getCharge(uuid) >= amount;
    }
    public void useCharge(UUID uuid, int amount) {
        this.chargeMap.put(uuid, this.chargeMap.getOrDefault(uuid, this.maximum)-amount);
        this.coolDownMap.put(uuid, System.currentTimeMillis());
    }
    public String getChargeFormatString(UUID uuid, String format, boolean b) {
        return b ? String.format(format, getCharge(uuid), this.maximum) : String.format(format, getCharge(uuid));
    }
}
