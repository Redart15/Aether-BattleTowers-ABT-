package redart15.aether_battle.item;

import net.minecraft.core.Global;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import redart15.aether_battle.effect.AetherBattleEffects;
import sunsetsatellite.catalyst.effects.api.effect.EffectContainer;
import sunsetsatellite.catalyst.effects.api.effect.EffectStack;
import sunsetsatellite.catalyst.effects.api.effect.IHasEffects;
import teamport.aether.item.AetherItems;

public class ItemJelly extends Item {

	public ItemJelly(String name, String namespaceId, int id) {
		super(name, namespaceId, id);
		this.maxStackSize = 1;
	}

	@Override
	public ItemStack onUseItem(ItemStack itemstack, World world, Player entityplayer) {
		IHasEffects<?> effectPlayer = (IHasEffects<?>) entityplayer;
		EffectContainer<?> container = effectPlayer.getContainer();
		EffectStack stack = new EffectStack(effectPlayer, AetherBattleEffects.regeneration, 12 * Global.TICKS_PER_SECOND, 1);
		stack.start(container);
		container.add(stack);
		world.playSoundAtEntity(entityplayer, entityplayer, "random.bite", 0.5F + (itemRand.nextFloat() - itemRand.nextFloat()) * 0.1F, 1.1F + (itemRand.nextFloat() - itemRand.nextFloat()) * 0.1F);
		return new ItemStack(AetherItems.BUCKET_SKYROOT);
	}
}

