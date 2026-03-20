package redart15.aether_battle.item;

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

public class ItemStoneArmor extends ItemArmor implements IItemWithModifiers {
	private final int stoneSkinValue;

	public ItemStoneArmor(String name, String namespaceId, int id, int armorPiece, int stoneSkinValue) {
		super(name, namespaceId, id, AetherBattleArmorMaterial.STONE, armorPiece);
		this.stoneSkinValue = stoneSkinValue;
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
