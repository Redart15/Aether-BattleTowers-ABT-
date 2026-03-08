package redart15.aether_battle.item;

import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemArmor;
import net.minecraft.core.item.ItemStack;
import redart15.aether_battle.effect.AetherBattleEffects;
import sunsetsatellite.catalyst.effects.api.effect.IHasEffects;
import sunsetsatellite.catalyst.effects.api.modifier.IItemWithModifiers;
import sunsetsatellite.catalyst.effects.api.modifier.Modifier;
import sunsetsatellite.catalyst.effects.api.modifier.ModifierType;
import sunsetsatellite.catalyst.effects.api.modifier.type.IntModifier;

import java.util.HashMap;
import java.util.Map;

public class ItemStoneArmor extends ItemArmor implements IAccessoryEffectsExtended, IItemWithModifiers {
	public ItemStoneArmor(String name, String namespaceId, int id, int armorPiece) {
		super(name, namespaceId, id, AetherBattleArmorMaterial.STONE, armorPiece);
	}

	@Override
	public void addEffect(Player player, ItemStack accessory) {
//		IHasEffects<?> hasEffects = (IHasEffects<?>) player;
//		EffectContainer<?> container = hasEffects.getContainer();
//		EffectStack stack = new EffectStack(hasEffects, AetherBattleEffects.stoneSkin, 1);
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
//					effectStack.subtract(1, container);
//					return;
//				}
//			}
//		}
	}

	@Override
	public Map<Modifier<?>, Boolean> getModifiers(IHasEffects<?> iHasEffects, ItemStack itemStack, int slot) {
		if (slot < 100 || slot > 103) {
			return new HashMap<>();
		}
		Map<Modifier<?>, Boolean> map = new HashMap<>();
		map.put(new IntModifier(AetherBattleEffects.stoneSkinAttributes, ModifierType.ADD,1),true);
		return map;
	}
}
