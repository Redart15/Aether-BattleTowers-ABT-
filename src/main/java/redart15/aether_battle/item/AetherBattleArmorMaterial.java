package redart15.aether_battle.item;

import net.minecraft.core.item.material.ArmorMaterial;
import net.minecraft.core.util.collection.NamespaceID;
import net.minecraft.core.util.helper.DamageType;
import teamport.aether.AetherMod;

import static redart15.aether_battle.AetherBattleMod.MOD_ID;

public class AetherBattleArmorMaterial {
	public static final ArmorMaterial STONE;

	static{
		STONE = new ArmorMaterial(NamespaceID.getPermanent(MOD_ID, "stone"), 350)
			.withProtectionPercentage(DamageType.COMBAT, 30.0f)
			.withProtectionPercentage(DamageType.BLAST, -30.0f)
			.withProtectionPercentage(DamageType.FIRE, 50.0f)
			.withProtectionPercentage(DamageType.FALL, -30.0f)
			.withProtectionPercentage(AetherMod.HOLY, 50.0f)
			.withProtectionPercentage(AetherMod.LIGHTNING, 50.0f);
	}
}
