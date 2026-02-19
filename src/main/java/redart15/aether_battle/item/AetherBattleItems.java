package redart15.aether_battle.item;


import net.minecraft.core.item.Item;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.item.tag.ItemTags;
import redart15.aether_battle.config.AetherBattleConfig;
import teamport.aether.item.AetherItemTags;
import turniplabs.halplibe.helper.ItemBuilder;

import static redart15.aether_battle.AetherBattleMod.MOD_ID;

public class AetherBattleItems {
	public static Item CONGEALED_BLOOD;
	public static Item DICINATOR;
	public static Item CRIMSON_GEM;

	private static boolean init = false;
	private AetherBattleItems(){}

	public static String itemKey(String string) {
		return "aether:item/" + string;
	}

	public static void init(){
		if(init) return;
		init = true;
		DICINATOR = new ItemBuilder(MOD_ID)
			.addTags(ItemTags.PREVENT_CREATIVE_MINING, ItemTags.PREVENT_LEFT_CLICK_INTERACTIONS)
			.build(new ItemChaoticWand("chaotic.wand", itemKey("chaotic_wand"), AetherBattleConfig.nextItemID(), ToolMaterial.diamond));

		CRIMSON_GEM = new ItemBuilder(MOD_ID)
			.build(new ItemCrimsonGem("crimson.gem", itemKey("crimson_gem"), AetherBattleConfig.nextItemID(), "blood_gem"));

		CONGEALED_BLOOD = new ItemBuilder(MOD_ID)
			.addTags(AetherItemTags.IMMUNE_TO_FIRE_DAMAGE, AetherItemTags.IMMUNE_TO_BLAST_DAMAGE)
			.build(new ItemCongealedBlood("congealed.blood", itemKey("congealed_blood"), AetherBattleConfig.nextItemID()));
	}
}
