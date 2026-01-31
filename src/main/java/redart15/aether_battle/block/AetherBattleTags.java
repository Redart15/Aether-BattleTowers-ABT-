package redart15.aether_battle.block;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.data.tag.Tag;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.tag.ItemTags;
import redart15.aether_battle.AetherBattleMod;
import teamport.aether.item.AetherItemTags;

import java.lang.reflect.Field;

public class AetherBattleTags {
	public static final Tag<Block<?>> BRIDLE = Tag.of("bridle");

	private static boolean init = false;
	private AetherBattleTags(){}
	public static void init(){
		if(init) return;
		init = true;
		AetherBattleTags.initTags();
		AetherBattleTags.addToVanilla();
	}

	private static void addToVanilla() {
		chaoticWandBridle();
	}

	private static void chaoticWandBridle() {
		Blocks.GLASS.withTags(BRIDLE);
		Blocks.GLASS_TINTED.withTags(BRIDLE);
		Blocks.GLASS_STEEL.withTags(BRIDLE);

		Blocks.DOOR_GLASS_TOP.withTags(BRIDLE);
		Blocks.DOOR_GLASS_BOTTOM.withTags(BRIDLE);

		Blocks.TRAPDOOR_GLASS.withTags(BRIDLE);
		Blocks.JAR_GLASS.withTags(BRIDLE);

		Blocks.LAMP_ACTIVE.withTags(BRIDLE);
		Blocks.LAMP_IDLE.withTags(BRIDLE);
		Blocks.LAMP_INVERTED_ACTIVE.withTags(BRIDLE);
		Blocks.LAMP_INVERTED_IDLE.withTags(BRIDLE);

		Blocks.LEAVES_OAK_RETRO.withTags(BRIDLE);
		Blocks.LEAVES_OAK.withTags(BRIDLE);
		Blocks.LEAVES_CACAO.withTags(BRIDLE);
		Blocks.LEAVES_PALM.withTags(BRIDLE);
		Blocks.LEAVES_SHRUB.withTags(BRIDLE);
		Blocks.LEAVES_PINE.withTags(BRIDLE);
		Blocks.LEAVES_BIRCH.withTags(BRIDLE);
		Blocks.LEAVES_CHERRY_FLOWERING.withTags(BRIDLE);
		Blocks.LEAVES_CHERRY.withTags(BRIDLE);
		Blocks.LEAVES_THORN.withTags(BRIDLE);
		Blocks.LEAVES_EUCALYPTUS.withTags(BRIDLE);
		Blocks.LAYER_LEAVES_OAK.withTags(BRIDLE);

		Blocks.SUGARCANE.withTags(BRIDLE);
	}


	private static void initTags(){
		for(Field field : AetherItemTags.class.getDeclaredFields()) {
			if (field.getType().equals(Tag.class)) {
				try {
					Tag<Item> tag = (Tag)field.get(null);
					ItemTags.TAG_LIST.add(tag);
				} catch (Exception e) {
					AetherBattleMod.LOGGER.error("Failed to add tag '{}'!", field.getName(), e);
				}
			}
		}
	}
}
