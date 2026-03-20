package redart15.aether_battle.model;

import jamdoggie.betterbattletowers.model.BlockModelCrumblingStone;
import net.minecraft.client.render.EntityRenderDispatcher;
import net.minecraft.client.render.TileEntityRenderDispatcher;
import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.BlockModelSlab;
import net.minecraft.client.render.block.model.BlockModelStairs;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.core.util.helper.Side;
import redart15.aether_battle.block.AetherBattleBlocks;
import redart15.aether_battle.entity.ProjectileChaotic;
import redart15.aether_battle.item.AetherBattleItems;
import teamport.aether.AetherMod;
import turniplabs.halplibe.helper.ModelHelper;
import turniplabs.halplibe.util.ModelEntrypoint;

import static net.minecraft.client.render.block.model.BlockModelStandard.BLOCK_TEXTURES;
import static net.minecraft.client.render.block.model.BlockModelStandard.OVERBRIGHT_TEXTURES;
import static redart15.aether_battle.AetherBattleMod.MOD_ID;

public class AetherBattleModels implements ModelEntrypoint {
	@Override
	public void initBlockModels(BlockModelDispatcher blockModelDispatcher) {
		blockModelDispatcher.addDispatch((new BlockModelCrumblingStone<>(AetherBattleBlocks.CARVED_STONE_CRUMBLING, MOD_ID + ":block/crumble_blocks/carved/carved_")).setAllTextures(BLOCK_TEXTURES, AetherMod.MOD_ID + ":block/dungeon/carved"));
		blockModelDispatcher.addDispatch((new BlockModelCrumblingStone<>(AetherBattleBlocks.CARVED_ANGELIC_CRUMBLING, MOD_ID + ":block/crumble_blocks/angelic/angelic_")).setAllTextures(BLOCK_TEXTURES, AetherMod.MOD_ID + ":block/dungeon/angelic"));
		blockModelDispatcher.addDispatch((new BlockModelCrumblingStone<>(AetherBattleBlocks.CARVED_HELLFIRE_CRUMBLING, MOD_ID + ":block/crumble_blocks/hellfire/hellfire_")).setAllTextures(BLOCK_TEXTURES, AetherMod.MOD_ID + ":block/dungeon/hellfire"));

		blockModelDispatcher.addDispatch((new BlockModelStairs<>(AetherBattleBlocks.CARVED_STONE_STAIR_CRUMBLING)).setAllTextures(BLOCK_TEXTURES, AetherMod.MOD_ID + ":block/dungeon/carved"));
		blockModelDispatcher.addDispatch((new BlockModelStairs<>(AetherBattleBlocks.CARVED_ANGELIC_STAIR_CRUMBLING)).setAllTextures(BLOCK_TEXTURES, AetherMod.MOD_ID + ":block/dungeon/angelic"));
		blockModelDispatcher.addDispatch((new BlockModelStairs<>(AetherBattleBlocks.CARVED_HELLFIRE_STAIR_CRUMBLING)).setAllTextures(BLOCK_TEXTURES, AetherMod.MOD_ID + ":block/dungeon/hellfire"));

		blockModelDispatcher.addDispatch((new BlockModelSlab<>(AetherBattleBlocks.CARVED_STONE_SLAB_CRUMBLING)).setAllTextures(BLOCK_TEXTURES, AetherMod.MOD_ID + ":block/dungeon/carved"));
		blockModelDispatcher.addDispatch((new BlockModelSlab<>(AetherBattleBlocks.CARVED_ANGELIC_SLAB_CRUMBLING)).setAllTextures(BLOCK_TEXTURES, AetherMod.MOD_ID + ":block/dungeon/angelic"));
		blockModelDispatcher.addDispatch((new BlockModelSlab<>(AetherBattleBlocks.CARVED_HELLFIRE_SLAB_CRUMBLING)).setAllTextures(BLOCK_TEXTURES, AetherMod.MOD_ID + ":block/dungeon/hellfire"));

		blockModelDispatcher.addDispatch((new BlockModelCrumblingOverbright<>(AetherBattleBlocks.CARVED_STONE_LIGHT_CRUMBLING, MOD_ID + ":block/crumble_blocks/carved/carved_"))
			.setAllTextures(BLOCK_TEXTURES, AetherMod.MOD_ID + ":block/dungeon/carved").setAllTextures(OVERBRIGHT_TEXTURES, AetherMod.MOD_ID + ":block/dungeon/carved_overlay"));
		blockModelDispatcher.addDispatch((new BlockModelCrumblingOverbright<>(AetherBattleBlocks.CARVED_ANGELIC_LIGHT_CRUMBLING, MOD_ID + ":block/crumble_blocks/angelic/angelic_"))
			.setAllTextures(BLOCK_TEXTURES, AetherMod.MOD_ID + ":block/dungeon/angelic").setAllTextures(OVERBRIGHT_TEXTURES, AetherMod.MOD_ID + ":block/dungeon/angelic_overlay"));
		blockModelDispatcher.addDispatch((new BlockModelCrumblingOverbright<>(AetherBattleBlocks.CARVED_HELLFIRE_LIGHT_CRUMBLING, MOD_ID + ":block/crumble_blocks/hellfire/hellfire_"))
			.setAllTextures(BLOCK_TEXTURES, AetherMod.MOD_ID + ":block/dungeon/hellfire").setAllTextures(OVERBRIGHT_TEXTURES, AetherMod.MOD_ID + ":block/dungeon/hellfire_overlay"));


		blockModelDispatcher.addDispatch(new BlockModelPie<>(AetherBattleBlocks.MOSSY_CAKE, MOD_ID + ":block/mossy_cake/inner")
			.setTex(BLOCK_TEXTURES, MOD_ID + ":block/mossy_cake/top", Side.TOP)
			.setTex(BLOCK_TEXTURES, MOD_ID + ":block/mossy_cake/bottom", Side.BOTTOM)
			.setTex(BLOCK_TEXTURES, MOD_ID + ":block/mossy_cake/side", Side.NORTH, Side.EAST, Side.SOUTH, Side.WEST));
	}

	@Override
	public void initItemModels(ItemModelDispatcher itemModelDispatcher) {
		itemModelDispatcher.addDispatch(new ItemModelWand(AetherBattleItems.CHAOTIC_WAND, (String) null)
			.setOverlay(MOD_ID + ":item/chaotic_wand/overlay")
			.setIcon(MOD_ID + ":item/chaotic_wand/wand")
			.setFull3D()
			.setFullBright());


		itemModelDispatcher.addDispatch(new ItemModelStandard(AetherBattleItems.STONE_BOOTS, (String) null).setIcon(MOD_ID + ":item/armor_boots_stone"));
		itemModelDispatcher.addDispatch(new ItemModelStandard(AetherBattleItems.STONE_PLATEBODY, (String) null).setIcon(MOD_ID + ":item/armor_chestplate_stone"));
		itemModelDispatcher.addDispatch(new ItemModelStandard(AetherBattleItems.STONE_HELM, (String) null).setIcon(MOD_ID + ":item/armor_helmet_stone"));
		itemModelDispatcher.addDispatch(new ItemModelStandard(AetherBattleItems.STONE_PLATELEGS, (String) null).setIcon(MOD_ID + ":item/armor_leggings_stone"));
		itemModelDispatcher.addDispatch(new ItemModelStandard(AetherBattleItems.STONE_TALISMAN, (String) null).setIcon(MOD_ID + ":item/armor_pendant_stone"));

		itemModelDispatcher.addDispatch(new ItemModelStandard(AetherBattleItems.MOSSY_CAKE, (String) null).setIcon(MOD_ID + ":item/food_mossy_cake"));
		itemModelDispatcher.addDispatch(new ItemModelStandard(AetherBattleItems.MOSS_BERRY, (String) null).setIcon(MOD_ID + ":item/food_moss_berry1"));
	}

	@Override
	public void initEntityModels(EntityRenderDispatcher entityRenderDispatcher) {
		ModelHelper.setEntityModel(ProjectileChaotic.class, EntityRendererChaoticWandDart::new);
	}

	@Override
	public void initTileEntityModels(TileEntityRenderDispatcher tileEntityRenderDispatcher) {
		/* no need */
	}

	@Override
	public void initBlockColors(BlockColorDispatcher blockColorDispatcher) {
		/* no need */
	}
}
