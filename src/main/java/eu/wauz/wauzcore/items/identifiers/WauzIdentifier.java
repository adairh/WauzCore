package eu.wauz.wauzcore.items.identifiers;

import org.bukkit.event.inventory.InventoryClickEvent;

public class WauzIdentifier {
	
	public static void identify(InventoryClickEvent event, String itemName) {
		if(itemName.contains("Item"))
			WauzEquipmentIdentifier.identifyItem(event);
		else if(itemName.contains("Rune"))
			new WauzRuneIdentifier().identifyRune(event);
		else if(itemName.contains("Skillgem"))
			new WauzSkillgemIdentifier().identifySkillgem(event);
		else if(itemName.contains("Map"))
			new WauzShrineIdentifier().identifyShrine(event);
	}

}
