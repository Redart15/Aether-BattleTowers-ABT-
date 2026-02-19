package redart15.aether_battle.item;

import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.container.ContainerInventory;
import redart15.aether_battle.TriggerOnPickup;

public class ItemCongealedBlood extends Item implements TriggerOnPickup {

	public ItemCongealedBlood(String translationKey, String namespaceId, int id) {
		super(translationKey, namespaceId, id);
	}

	@Override
	public void onPickUp(Player player, EntityItem entityItem, ContainerInventory container, ItemStack itemStack, boolean hotbarOffset) {
		if (!player.getGamemode().canInteract()) {
			return;
		}
		player.heal(itemStack.stackSize);
		entityItem.remove();
	}
}
