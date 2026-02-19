package redart15.aether_battle.block;

import jamdoggie.betterbattletowers.block.BattleTowerBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.data.tag.Tag;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.tag.ItemTags;
import redart15.aether_battle.AetherBattleMod;
import teamport.aether.block.AetherBlocks;

import java.lang.reflect.Field;

public class AetherBattleBlockTags {
	public static final Tag<Block<?>> BRIDLE = Tag.of("bridle");

	private static boolean init = false;
	private AetherBattleBlockTags(){}
	public static void init(){
		if(init) return;
		init = true;
		AetherBattleBlockTags.initTags();
		AetherBattleBlockTags.chaoticWandBridle();
	}

	private static void chaoticWandBridle() {
		addToVanilla();
		addToAether();
		addToBattleTowers();
		addToThis();
	}

	private static void addToVanilla() {
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
		Blocks.LANTERN_FIREFLY_BLUE.withTags(BRIDLE);
		Blocks.LANTERN_FIREFLY_GREEN.withTags(BRIDLE);
		Blocks.LANTERN_FIREFLY_ORANGE.withTags(BRIDLE);
		Blocks.LANTERN_FIREFLY_RED.withTags(BRIDLE);

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
		Blocks.FLOWER_YELLOW.withTags(BRIDLE);
		Blocks.FLOWER_RED.withTags(BRIDLE);
		Blocks.FLOWER_PURPLE.withTags(BRIDLE);
		Blocks.FLOWER_PINK.withTags(BRIDLE);
		Blocks.FLOWER_LIGHT_BLUE.withTags(BRIDLE);
		Blocks.FLOWER_ORANGE.withTags(BRIDLE);
		Blocks.SAPLING_CACAO.withTags(BRIDLE);
		Blocks.SAPLING_BIRCH.withTags(BRIDLE);
		Blocks.SAPLING_CHERRY.withTags(BRIDLE);
		Blocks.SAPLING_OAK.withTags(BRIDLE);
		Blocks.SAPLING_EUCALYPTUS.withTags(BRIDLE);
		Blocks.SAPLING_OAK_RETRO.withTags(BRIDLE);
		Blocks.SAPLING_PALM.withTags(BRIDLE);
		Blocks.SAPLING_PINE.withTags(BRIDLE);
		Blocks.SAPLING_SHRUB.withTags(BRIDLE);
		Blocks.SAPLING_THORN.withTags(BRIDLE);
		Blocks.TALLGRASS.withTags(BRIDLE);
		Blocks.TALLGRASS_FERN.withTags(BRIDLE);
		Blocks.CACTUS.withTags(BRIDLE);
		Blocks.PUMPKIN.withTags(BRIDLE);
		Blocks.PUMPKIN_PIE.withTags(BRIDLE);
		Blocks.PUMPKIN_CARVED_IDLE.withTags(BRIDLE);
		Blocks.CROPS_PUMPKIN.withTags(BRIDLE);
		Blocks.CROPS_WHEAT.withTags(BRIDLE);
	}

	private static void addToAether() {
		AetherBlocks.LEAVES_OAK_GOLDEN.withTags(BRIDLE);
		AetherBlocks.LEAVES_SKYROOT.withTags(BRIDLE);

		AetherBlocks.GLASS_QUICKSOIL.withTags(BRIDLE);
		AetherBlocks.DOOR_GLASS_QUICKSOIL_BOTTOM.withTags(BRIDLE);
		AetherBlocks.DOOR_GLASS_QUICKSOIL_TOP.withTags(BRIDLE);
		AetherBlocks.DOOR_GLASS_QUICKSOIL_BOTTOM.withTags(BRIDLE);
		AetherBlocks.LANTERN_FIREFLY_SILVER.withTags(BRIDLE);

		AetherBlocks.FLOWER_PURPLE.withTags(BRIDLE);
		AetherBlocks.FLOWER_WHITE.withTags(BRIDLE);
		AetherBlocks.TALLGRASS_AETHER.withTags(BRIDLE);
		AetherBlocks.SAPLING_SKYROOT.withTags(BRIDLE);
		AetherBlocks.SAPLING_OAK_GOLDEN.withTags(BRIDLE);
	}

	private static void addToBattleTowers() {
		BattleTowerBlocks.PRISON_BAR_FENCE.withTags(BRIDLE);
		BattleTowerBlocks.PRISON_BAR.withTags(BRIDLE);
		BattleTowerBlocks.CRUMBLING_STONE.withTags(BRIDLE);
		BattleTowerBlocks.STAIRS_CRUMBLING_STONE.withTags(BRIDLE);
		BattleTowerBlocks.SLAB_CRUMBLING_STONE.withTags(BRIDLE);
	}

	private static void addToThis() {
	}


	private static void initTags(){
		for(Field field : AetherBattleBlockTags.class.getDeclaredFields()) {
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
