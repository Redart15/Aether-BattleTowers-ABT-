package redart15.aether_battle.item;


import net.minecraft.core.block.Blocks;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemArmor;
import net.minecraft.core.item.ItemFood;
import net.minecraft.core.item.ItemPlaceable;
import net.minecraft.core.item.material.ArmorMaterial;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.item.tag.ItemTags;
import redart15.aether_battle.block.AetherBattleBlocks;
import redart15.aether_battle.config.AetherBattleConfig;
import turniplabs.halplibe.helper.ItemBuilder;

import static redart15.aether_battle.AetherBattleMod.MOD_ID;

public class AetherBattleItems {

	// WEAPONS & TOOLS
	public static Item CHAOTIC_WAND;
	public static Item ULTIMATIVE_FIST;

	// FOOD
	public static Item STONE_COOKIE;
	public static Item MOSS_BERRY;
	public static Item MOSSY_CAKE;

	// ARMOR
	public static Item STONE_HELM;
	public static Item STONE_PLATEBODY;
	public static Item STONE_PLATELEGS;
	public static Item STONE_BOOTS;
	public static Item STONE_TALISMAN;


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

		ULTIMATIVE_FIST = new ItemBuilder(MOD_ID).build(new ItemFist("ultimative.fist", itemKey("ultimative_fist"), AetherBattleConfig.nextItemID(), ArmorMaterial.DIAMOND, 4));

		STONE_COOKIE = new ItemBuilder(MOD_ID).build(new ItemFood("stone.cookie", itemKey("stone_cookie"), AetherBattleConfig.nextItemID(), 2, 0, false, 8));
		MOSS_BERRY = new ItemBuilder(MOD_ID).build(new ItemFood("moss.berry", itemKey("moss_berry"), AetherBattleConfig.nextItemID(), 1, 16, false, 8));
		MOSSY_CAKE = new ItemBuilder(MOD_ID).setStackSize(1).build(new ItemPlaceable("food.mossy.cake", itemKey("food_mossy_cake"), AetherBattleConfig.nextItemID(), AetherBattleBlocks.MOSSY_CAKE));

		STONE_TALISMAN = new ItemBuilder(MOD_ID).build(new ItemStonePendant("stone.talisman", itemKey("stone_talisman"), AetherBattleConfig.nextItemID(), AetherBattleArmorMaterial.STONE));

		STONE_HELM = new ItemBuilder(MOD_ID).build(new ItemArmor("armor.helmet.stone", itemKey("armor_helmet_stone"), AetherBattleConfig.nextItemID(), AetherBattleArmorMaterial.STONE, 3));
		STONE_PLATEBODY = new ItemBuilder(MOD_ID).build(new ItemArmor("armor.chestplate.stone", itemKey("armor_chestplate_stone"), AetherBattleConfig.nextItemID(), AetherBattleArmorMaterial.STONE, 2));
		STONE_PLATELEGS = new ItemBuilder(MOD_ID).build(new ItemArmor("armor.leggings.stone", itemKey("armor_leggings_stone"), AetherBattleConfig.nextItemID(), AetherBattleArmorMaterial.STONE, 1));
		STONE_BOOTS = new ItemBuilder(MOD_ID).build(new ItemArmor("armor.boots.stone", itemKey("armor_boots_stone"), AetherBattleConfig.nextItemID(), AetherBattleArmorMaterial.STONE, 0));

	}
}
