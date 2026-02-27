package redart15.aether_battle.item;


import net.minecraft.core.item.Item;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.item.tag.ItemTags;
import redart15.aether_battle.config.AetherBattleConfig;
import redart15.aether_battle.item.bloodstone.ItemCongealedBlood;
import redart15.aether_battle.item.bloodstone.ItemCrimsonGem;
import teamport.aether.item.AetherItemTags;
import turniplabs.halplibe.helper.ItemBuilder;

import static redart15.aether_battle.AetherBattleMod.MOD_ID;

public class AetherBattleItems {
	public static Item CONGEALED_BLOOD;
	public static Item CHAOTIC_WAND;
	public static Item BLOODSTONE;

	private static boolean init = false;
	private AetherBattleItems(){}

	public static String itemKey(String string) {
		return MOD_ID + ":item/" + string;
	}

	public static void init(){
		if(init) return;
		init = true;
		CHAOTIC_WAND = new ItemBuilder(MOD_ID)
			.addTags(ItemTags.PREVENT_CREATIVE_MINING, ItemTags.PREVENT_LEFT_CLICK_INTERACTIONS)
			.build(new ItemChaoticWand("chaotic.wand", itemKey("chaotic_wand"), AetherBattleConfig.nextItemID(), ToolMaterial.diamond));

		BLOODSTONE = new ItemBuilder(MOD_ID)
			.build(new ItemCrimsonGem("bloodstone", itemKey("bloodstone"), AetherBattleConfig.nextItemID(), "bloodstone"));

		CONGEALED_BLOOD = new ItemBuilder(MOD_ID)
			.addTags(AetherItemTags.IMMUNE_TO_FIRE_DAMAGE, AetherItemTags.IMMUNE_TO_BLAST_DAMAGE)
			.build(new ItemCongealedBlood("congealed.blood", itemKey("congealed_blood"), AetherBattleConfig.nextItemID()));
	}
}
