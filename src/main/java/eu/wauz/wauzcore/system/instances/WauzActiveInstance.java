package eu.wauz.wauzcore.system.instances;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 * An instance data to save session scoped instance information.
 * 
 * @author Wauzmons
 */
public class WauzActiveInstance {
	
	/**
	 * The world of the instance.
	 */
	private World world;
	
	/**
	 * A map of the amount of times a player has died in the instance so far, indexed by player.
	 */
	private Map<Player, Integer> playerDeathMap = new HashMap<>();
	
	/**
	 * A map of the statuses of the instance's keys, indexed by key id.
	 */
	private Map<String, WauzInstanceKeyStatus> keyStatusMap = new HashMap<>();
	
	/**
	 * @return The world of the instance.
	 */
	public World getWorld() {
		return world;
	}

	/**
	 * @param player The player to get the death count for.
	 * 
	 * @return The amount of times a player has died in the instance so far.
	 */
	public int getPlayerDeaths(Player player) {
		Integer count = playerDeathMap.get(player);
		return count != null ? count : 0;
	}
	
	/**
	 * Increases the amount of times a player has died in the instance by one.
	 * 
	 * @param player The player to increase the deaths for.
	 */
	public void addPlayerDeath(Player player) {
		int count = getPlayerDeaths(player);
		playerDeathMap.put(player, count + 1);
	}
	
	/**
	 * @param keyId The id of the key to get the status for.
	 * 
	 * @return The status of the key.
	 */
	public WauzInstanceKeyStatus getKeyStatus(String keyId) {
		WauzInstanceKeyStatus keyStatus = keyStatusMap.get(keyId);
		return keyStatus != null ? keyStatus : WauzInstanceKeyStatus.UNOBTAINED;
	}
	
	/**
	 * Updates the status of a key.
	 * 
	 * @param keyId The id of the key to update the status for.
	 * @param status The new key status.
	 */
	public void setKeyStatus(String keyId, WauzInstanceKeyStatus status) {
		keyStatusMap.put(keyId, status);
	}

}
