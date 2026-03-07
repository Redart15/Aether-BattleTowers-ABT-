package redart15.aether_battle.item;

import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import teamport.aether.item.accessory.IAccessoryEffects;

public interface IAccessoryEffectsExtended extends IAccessoryEffects {
	void addEffect(Player player, ItemStack accessory);
}
