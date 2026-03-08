package redart15.aether_battle.item;

import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ArmorMaterial;
import redart15.aether_battle.effect.AetherBattleEffects;
import sunsetsatellite.catalyst.effects.api.effect.EffectContainer;
import sunsetsatellite.catalyst.effects.api.effect.EffectStack;
import sunsetsatellite.catalyst.effects.api.effect.IHasEffects;
import teamport.aether.item.accessory.pendant.ItemPendant;

import java.util.List;

public class ItemStonePendant extends ItemPendant implements IAccessoryEffectsExtended{
	public ItemStonePendant(String translationKey, String namespaceId, int id, ArmorMaterial material) {
		super(translationKey, namespaceId, id, material);
	}

	@Override
	public void addEffect(Player player, ItemStack accessory) {
//		IHasEffects<?> hasEffects = (IHasEffects<?>) player;
//		EffectContainer<?> container = hasEffects.getContainer();
//		EffectStack stack = new EffectStack(hasEffects, AetherBattleEffects.stoneSkin, 3);
//		container.add(stack);
//		stack.start(container);
	}

	@Override
	public void removeEffect(Player player, ItemStack accessory) {
//		IHasEffects<?> hasEffects = (IHasEffects<?>) player;
//		EffectContainer<?> container = hasEffects.getContainer();
//		if (container.hasEffect(AetherBattleEffects.stoneSkin)) {
//			List<EffectStack> listStack = container.getEffects();
//			for (EffectStack effectStack : listStack) {
//				if (effectStack.getEffect() == AetherBattleEffects.stoneSkin) {
//					effectStack.subtract(3, container);
//					return;
//				}
//			}
//		}
	}
}
