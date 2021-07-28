package eu.wauz.wauzcore.building.shapes;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Biome;

/**
 * A generic brush to paint block structures.
 * 
 * @author Wauzmons
 */
public abstract class WauzBrush {
	
	/**
	 * The id of the brush.
	 */
	private final String uuid;
	
	/**
	 * The shape of the brush.
	 */
	private WauzShape shape;
	
	/**
	 * The material of the brush.
	 */
	private Material material;
	
	/**
	 * The biome of the brush.
	 */
	private Biome biome;
	
	/**
	 * Creates a new instance of this brush.
	 */
	protected WauzBrush() {
		this.uuid = UUID.randomUUID().toString();
	}
	
	/**
	 * @return The id of the brush.
	 */
	public final String getUuid() {
		return uuid;
	}
	
	/**
	 * @return The name of the brush.
	 */
	public abstract String getName();
	
	/**
	 * Gets a new instance of this brush.
     * 
     * @param radius The radius of the brush.
     * @param height The height of the brush.
     * 
     * @return The created instance.
	 */
	public abstract WauzBrush getInstance(int radius, int height);

	/**
	 * Sets the shape of the brush.
	 * 
	 * @param shape The shape of the brush.
	 * 
	 * @return The modified brush.
	 */
	protected final WauzBrush withShape(WauzShape shape) {
		this.shape = shape;
		return this;
	}

	/**
	 * Sets the material of the brush.
	 * 
	 * @param material The material of the brush.
	 * 
	 * @return The modified brush.
	 */
	protected final WauzBrush withMaterial(Material material) {
		this.material = material;
		return this;
	}

	/**
	 * Sets the biome of the brush.
	 * 
	 * @param biome The biome of the brush.
	 * 
	 * @return The modified brush.
	 */
	protected final WauzBrush withBiome(Biome biome) {
		this.biome = biome;
		return this;
	}
	
	/**
	 * Uses the brush at the given location.
	 * 
	 * @param location The location to use the brush at.
	 */
	public void paint(Location location) {
		if(material != null) {
			shape.setMaterial(location, material);
		}
		if(biome != null) {
			shape.setBiome(location, biome);
		}
	}
	
}
