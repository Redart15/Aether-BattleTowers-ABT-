package redart15.aether_battle.item;

import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ArmorMaterial;
import redart15.aether_battle.effect.AetherBattleEffects;
import sunsetsatellite.catalyst.effects.api.effect.IHasEffects;
import sunsetsatellite.catalyst.effects.api.modifier.IItemWithModifiers;
import sunsetsatellite.catalyst.effects.api.modifier.Modifier;
import sunsetsatellite.catalyst.effects.api.modifier.ModifierType;
import sunsetsatellite.catalyst.effects.api.modifier.type.IntModifier;
import teamport.aether.item.accessory.pendant.ItemPendant;

import java.util.HashMap;
import java.util.Map;

public class ItemStonePendant extends ItemPendant implements IItemWithModifiers {
	private final int stoneSkinValue;

	public ItemStonePendant(String translationKey, String namespaceId, int id, ArmorMaterial material) {
		super(translationKey, namespaceId, id, material);
		this.stoneSkinValue = 2;
	}

	@Override
	public Map<Modifier<?>, Boolean> getModifiers(IHasEffects<?> iHasEffects, ItemStack itemStack, int slot) {
		if (slot < 100 || slot > 103) {
			return new HashMap<>();
		}
		Map<Modifier<?>, Boolean> map = new HashMap<>();
		map.put(new IntModifier(AetherBattleEffects.stoneSkinAttributes, ModifierType.ADD, stoneSkinValue), true);
		return map;
	}
}
