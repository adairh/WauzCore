package eu.wauz.wauzcore.skills;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import eu.wauz.wauzcore.players.calc.ManaCalculator;
import eu.wauz.wauzcore.skills.execution.SkillUtils;
import eu.wauz.wauzcore.skills.execution.WauzPlayerSkill;
import eu.wauz.wauzcore.skills.particles.ParticleSpawner;
import eu.wauz.wauzcore.skills.particles.SkillParticle;

public class SkillTemperance implements WauzPlayerSkill {
	
	public static String SKILL_NAME = "Temperance XIV";

	@Override
	public String getSkillId() {
		return SKILL_NAME;
	}
	
	@Override
	public String getSkillDescriptionType() {
		return "Self";
	}

	@Override
	public String getSkillDescriptionEffect() {
		return "Mana Restore";
	}

	@Override
	public int getCooldownSeconds() {
		return 60;
	}

	@Override
	public int getManaCost() {
		return 0;
	}

	@Override
	public boolean executeSkill(final Player player, ItemStack weapon) {
		player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 1, 1.5f);
		SkillParticle particle = new SkillParticle(Particle.DRIP_WATER);
		ParticleSpawner.spawnParticleSphere(player.getLocation(), particle, 1.5);
		SkillUtils.addPotionEffect(player, PotionEffectType.SLOW, 2, 200);
		SkillUtils.addPotionEffect(player, PotionEffectType.JUMP, 2, 200);
		ManaCalculator.regenerateMana(player, 8);
		
		return true;
	}

}
