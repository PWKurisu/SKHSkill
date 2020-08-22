package com.kurisu.skill;

import com.kurisu.skill.skills.LeapSkill;
import com.kurisu.skill.skills.PatienceSkill;
import com.kurisu.skill.skills.Skill;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SkillPlugin extends JavaPlugin {
    private final Map<String, Skill> skills = new HashMap<>();
    private final SkillBookListener skillBookListener;

    public SkillPlugin() {
        skills.put("도약", new LeapSkill(10, 5));
        skills.put("끈기", new PatienceSkill());
        this.skillBookListener = new SkillBookListener(this);
    }

    @Override
    public void onEnable() {
        for(String name : this.skills.keySet()) {
            getServer().getPluginManager().registerEvents(this.skills.get(name), this);
        }
        getServer().getPluginManager().registerEvents(this.skillBookListener, this);
        getCommand("getb").setExecutor(new SkillBookGetCommand(this));
    }

    public boolean containsSkill(String name) {
        return this.skills.containsKey(name);
    }
    public Set<String> getSkillList() {
        return this.skills.keySet();
    }
    public Skill getSkill(String name) {
        return this.skills.get(name);
    }

    //끈기 수정+주변 엔티티 수정
    //config 설정
    //git 사용
}
