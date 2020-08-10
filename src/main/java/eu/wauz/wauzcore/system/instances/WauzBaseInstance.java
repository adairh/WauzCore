package eu.wauz.wauzcore.system.instances;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for instance data.
 * 
 * @author Wauzmons
 */
public abstract class WauzBaseInstance {
	
	/**
	 * The name of the instance.
	 */
	private String instanceName;
	
	/**
	 * The type of the instance.
	 */
	private WauzInstanceType type;
	
	/**
	 * The maximum players of the instance.
	 */
	private int maxPlayers;
	
	/**
	 * The maximum deaths per player of the instance.
	 */
	private int maxDeaths;
	
	/**
	 * The list of all key ids of the instance.
	 */
	private List<String> keyIds = new ArrayList<>();

	/**
	 * @return The name of the instance.
	 */
	public String getInstanceName() {
		return instanceName;
	}

	/**
	 * @param instanceName The new name of the instance.
	 */
	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	/**
	 * @return The type of the instance.
	 */
	public WauzInstanceType getType() {
		return type;
	}

	/**
	 * @param type The new type of the instance.
	 */
	public void setType(WauzInstanceType type) {
		this.type = type;
	}

	/**
	 * @return The maximum players of the instance.
	 */
	public int getMaxPlayers() {
		return maxPlayers;
	}

	/**
	 * @param maxPlayers The new maximum players of the instance.
	 */
	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	/**
	 * @return The maximum deaths per player of the instance.
	 */
	public int getMaxDeaths() {
		return maxDeaths;
	}

	/**
	 * @param maxDeaths The new maximum deaths per player of the instance.
	 */
	public void setMaxDeaths(int maxDeaths) {
		this.maxDeaths = maxDeaths;
	}

	/**
	 * @return The list of all key ids of the instance.
	 */
	public List<String> getKeyIds() {
		return keyIds;
	}

	/**
	 * @param keyIds The new list of all key ids of the instance.
	 */
	public void setKeyIds(List<String> keyIds) {
		this.keyIds = keyIds;
	}
	
}
