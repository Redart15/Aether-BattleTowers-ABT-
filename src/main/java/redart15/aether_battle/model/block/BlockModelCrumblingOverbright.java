package redart15.aether_battle.model.block;

import jamdoggie.betterbattletowers.block.crumbling_stone.BlockLogicCrumbling;
import jamdoggie.betterbattletowers.model.BlockModelCrumblingStone;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.util.helper.Side;

public class BlockModelCrumblingOverbright<T extends BlockLogicCrumbling> extends BlockModelCrumblingStone<T> {
	private final IconCoordinate[] overbrightTopTex = new IconCoordinate[3];
	private final IconCoordinate[] getOverbrightSideTex = new IconCoordinate[3];

	public BlockModelCrumblingOverbright(Block block, String rootKey) {
		super(block, rootKey);
		this.initOverbrightCrumblingTex(rootKey);
	}

	private void initOverbrightCrumblingTex(String rootKey) {
		this.overbrightTopTex[0] = TextureRegistry.getTexture(rootKey + "overlay_heavy");
		this.overbrightTopTex[1] = TextureRegistry.getTexture(rootKey + "overlay_medium");
		this.overbrightTopTex[2] = TextureRegistry.getTexture(rootKey + "overlay_light");
		this.getOverbrightSideTex[0] = TextureRegistry.getTexture(rootKey + "overlay_heavy");
		this.getOverbrightSideTex[2] = TextureRegistry.getTexture(rootKey + "overlay_light");
		this.getOverbrightSideTex[1] = TextureRegistry.getTexture(rootKey + "overlay_medium");
	}

	@Override
	public IconCoordinate getBlockOverbrightTextureFromSideAndMeta(Side side, int metadata) {
		int texID = BlockLogicCrumbling.getStageFromMetadata(metadata);
		if (texID >= 3) {
			return super.getBlockTextureFromSideAndMetadata(side, metadata);
		} else {
			return side.isVertical() ? this.overbrightTopTex[texID] : this.getOverbrightSideTex[texID];
		}
	}
}
