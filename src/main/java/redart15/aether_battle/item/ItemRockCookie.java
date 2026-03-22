package redart15.aether_battle.item;

import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemFood;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

public class ItemRockCookie extends ItemFood {
	public ItemRockCookie(String name, String namespaceId, int id, int healAmount, int ticksPerHeal, boolean favouriteWolfMeat, int maxStackSize) {
		super(name, namespaceId, id, healAmount, ticksPerHeal, favouriteWolfMeat, maxStackSize);
	}

	@Override
	public ItemStack onUseItem(ItemStack itemstack, World world, Player entityplayer) {
		if (entityplayer.getHealth() < entityplayer.getMaxHealth() && entityplayer.getHealth() + entityplayer.getTotalHealingRemaining() < entityplayer.getMaxHealth() && itemstack.consumeItem(entityplayer)) {
			entityplayer.eatFood(this);
			world.playSoundAtEntity(entityplayer, entityplayer, "aether_battle:rock.eating", 0.8F + (itemRand.nextFloat() - itemRand.nextFloat()) * 0.1F, 1.1F + (itemRand.nextFloat() - itemRand.nextFloat()) * 0.1F);
		}

		return itemstack;
	}
}
