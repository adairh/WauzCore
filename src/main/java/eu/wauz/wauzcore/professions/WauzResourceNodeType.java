package eu.wauz.wauzcore.professions;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.StringUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import eu.wauz.wauzcore.items.util.EquipmentUtils;
import eu.wauz.wauzcore.players.WauzPlayerDataPool;
import eu.wauz.wauzcore.skills.passive.AbstractPassiveSkill;
import eu.wauz.wauzcore.skills.passive.PassiveHerbalism;
import eu.wauz.wauzcore.skills.passive.PassiveMining;

/**
 * The type of a gatherable resource node.
 * 
 * @author Wauzmons
 */
public enum WauzResourceNodeType {
	
	/**
	 * A mineral that can be mined with a pickaxe.
	 */
	MINERAL("Minerals", "Pickaxes", "_PICKAXE", PassiveMining.PASSIVE_NAME, Sound.BLOCK_STONE_HIT, Sound.BLOCK_STONE_BREAK),
	
	/**
	 * An herb that can be harvested with a spade.
	 */
	HERB("Herbs", "Spades", "_SHOVEL", PassiveHerbalism.PASSIVE_NAME, Sound.BLOCK_GRASS_HIT, Sound.BLOCK_GRASS_BREAK);
	
	/**
	 * The message display name of the node type.
	 */
	private String name;
	
	/**
	 * The message display name of the tool type.
	 */
	private String tool;
	
	/**
	 * The string to look for in the tool material.
	 */
	private String materialString;
	
	/**
	 * The name of the skill needed to to gather the resource.
	 */
	private String skillString;
	
	/**
	 * The sound to play when the node gets damaged.
	 */
	private Sound damageSound;
	
	/**
	 * The sound to play when the node gets broken.
	 */
	private Sound breakSound;
	
	/**
	 * Creates a new resource node type with given parameters.
	 * 
	 * @param name The message display name of the node type.
	 * @param tool The message display name of the tool type.
	 * @param materialString The string to look for in the tool material.
	 * @param skillString The name of the skill needed to to gather the resource.
	 * @param damageSound The sound to play when the node gets damaged.
	 * @param breakSound The sound to play when the node gets broken.
	 */
	WauzResourceNodeType(String name, String tool, String materialString, String skillString, Sound damageSound, Sound breakSound) {
		this.name = name;
		this.tool = tool;
		this.materialString = materialString;
		this.skillString = skillString;
		this.damageSound = damageSound;
		this.breakSound = breakSound;
	}
	
	/**
	 * Checks if the resource node of this type can be gathered.
	 * If not, the given player will receive a message with the reason.
	 * 
	 * @param player The player to receive the fail message.
	 * @param toolItemStack The tool item stack used for gathering.
	 * @param nodeTier The tier of the resource node.
	 * 
	 * @return If the node can be gathered.
	 */
	public boolean canGather(Player player, ItemStack toolItemStack, int nodeTier) {
		if(toolItemStack == null || !StringUtils.contains(toolItemStack.getType().toString(), materialString)) {
			player.sendMessage(ChatColor.RED + name + " can only be gathered with " + tool + "!");
			return false;
		}
		int toolTier = EquipmentUtils.getTier(toolItemStack);
		AbstractPassiveSkill skill = getSkill(player);
		int currentSkillLevel = getSkill(player).getLevel();
		int neededSkillLevel = ((toolTier - 1) * 5) + 1;
		if(currentSkillLevel < neededSkillLevel) {
			player.sendMessage(ChatColor.RED + "Your " + skill.getPassiveName() + " Skill (" + currentSkillLevel + ") needs to be "
					+ neededSkillLevel + " or higher to use this " + tool + "!");
			return false;
		}
		if(toolTier < nodeTier) {
			player.sendMessage(ChatColor.RED + "These " + name + " can only be gathered with T"
					+ nodeTier + " or better " + tool + "!");
			return false;
		}
		return true;
	}
	
	/**
	 * Gets the needed skill to to gather the resource from a player.
	 * 
	 * @param player The player to get the skill of.
	 * 
	 * @return The skill needed to to gather the resource.
	 */
	public AbstractPassiveSkill getSkill(Player player) {
		return WauzPlayerDataPool.getPlayer(player).getSkills().getCachedPassive(skillString);
	}
	
	/**
	 * Returns the message display name of the node type in a title friendly format.
	 * 
	 * @return The message display name of the node type.
	 */
	@Override
	public String toString() {
		return name;
	}

	/**
	 * @return The sound to play when the node gets damaged.
	 */
	public Sound getDamageSound() {
		return damageSound;
	}

	/**
	 * @return The sound to play when the node gets broken.
	 */
	public Sound getBreakSound() {
		return breakSound;
	}

}
