package redart15.aether_battle.model;

import net.minecraft.client.render.EntityRenderDispatcher;
import net.minecraft.client.render.TileEntityRenderDispatcher;
import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelStandard;
import redart15.aether_battle.entity.projectile.ProjectileChaotic;
import redart15.aether_battle.item.AetherBattleItems;
import teamport.aether.AetherMod;
import turniplabs.halplibe.helper.ModelHelper;
import turniplabs.halplibe.util.ModelEntrypoint;

import static redart15.aether_battle.AetherBattleMod.MOD_ID;

public class AetherBattleModels implements ModelEntrypoint {
	@Override
	public void initBlockModels(BlockModelDispatcher blockModelDispatcher) {

	}

	@Override
	public void initItemModels(ItemModelDispatcher itemModelDispatcher) {
		itemModelDispatcher.addDispatch(new ItemModelWand(AetherBattleItems.DICINATOR, (String) null)
			.setOverlay(MOD_ID + ":item/chaotic_wand/overlay")
			.setIcon(MOD_ID + ":item/chaotic_wand/wand")
			.setFull3D()
			.setFullBright());

		itemModelDispatcher.addDispatch(new ItemModelStandard(AetherBattleItems.CONGEALED_BLOOD, null)
			.setIcon(MOD_ID + ":item/congealed_blood"));

		itemModelDispatcher.addDispatch(new ItemModelStandard(AetherBattleItems.CRIMSON_GEM, null)
			.setIcon(MOD_ID + ":item/blood_stone"));
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
