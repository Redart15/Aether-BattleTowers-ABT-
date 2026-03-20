package redart15.aether_battle.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicEdible;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.util.phys.AABB;

@Environment(EnvType.CLIENT)
public class BlockModelPie<T extends BlockLogicEdible> extends BlockModelStandard<T> {
	protected IconCoordinate insideTexture;
	public int maxSlices;

	public BlockModelPie(Block<T> block, String path) {
		super(block);
		float f = 0.0625F;
		float f1 = 0.375F;
		this.withCustomItemBounds(f, 0.0F, f, 1.0F - f, f1, 1.0F - f);
		this.insideTexture = TextureRegistry.getTexture(path);
	}

	public void renderSliceSide(Tessellator tessellator, AABB bounds, int x, int y, int z, Side side, boolean overrideTex) {
		if (overrideTex) {
			renderBlocks.overrideBlockTexture = this.insideTexture;
		}

		this.renderSide(tessellator, bounds, x, y, z, side, 0);
		if (overrideTex) {
			renderBlocks.overrideBlockTexture = null;
		}

	}

	private void renderSlice(Tessellator tessellator, AABB bounds, int x, int y, int z, int sliceX, int sliceZ) {
		double onePix = 0.0625F;
		double sliceWidth = onePix * (double) 8.0F;
		double xMin = (double) 0.0F + sliceWidth * (double) sliceX;
		double xMax = xMin + sliceWidth;
		double zMin = (double) 0.0F + sliceWidth * (double) sliceZ;
		double zMax = zMin + sliceWidth;
		double offsetXMin = sliceX == 0 ? onePix : (double) 0.0F;
		double offsetXMax = sliceX == 1 ? onePix : (double) 0.0F;
		double offsetZMin = sliceZ == 0 ? onePix : (double) 0.0F;
		double offsetZMax = sliceZ == 1 ? onePix : (double) 0.0F;
		boolean insideSouth = sliceZ == 0;
		boolean insideNorth = sliceZ == 1;
		boolean insideEast = sliceX == 0;
		boolean insideWest = sliceX == 1;
		this.maxSlices = this.block.getLogic().maxBites;
		bounds.set(xMin + offsetXMin, 0.0F, zMin + offsetZMin, xMax - offsetXMax, 0.375F, zMax - offsetZMax);
		this.renderSide(tessellator, bounds, x, y, z, Side.TOP, 0);
		this.renderSide(tessellator, bounds, x, y, z, Side.BOTTOM, 0);
		this.renderSliceSide(tessellator, bounds, x, y, z, Side.SOUTH, insideSouth);
		this.renderSliceSide(tessellator, bounds, x, y, z, Side.WEST, insideWest);
		renderBlocks.flipTexture = true;
		this.renderSliceSide(tessellator, bounds, x, y, z, Side.NORTH, insideNorth);
		this.renderSliceSide(tessellator, bounds, x, y, z, Side.EAST, insideEast);
		renderBlocks.flipTexture = false;
	}

	public boolean render(Tessellator tessellator, int x, int y, int z) {
		AABB bounds = this.block.getBlockBoundsFromState(renderBlocks.blockAccess, x, y, z);
		int meta = renderBlocks.blockAccess.getBlockMetadata(x, y, z);
		renderBlocks.enableAO = true;
		renderBlocks.cache.setupCache(this.block, renderBlocks.blockAccess, x, y, z);
		int slices = 0;

		for (int xSlice = 0; xSlice < 2; ++xSlice) {
			for (int zSlice = 0; zSlice < 2; ++zSlice) {
				++slices;
				if (meta < slices) {
					this.renderSlice(tessellator, bounds, x, y, z, xSlice, zSlice);
				}
			}
		}

		renderBlocks.enableAO = false;
		return true;
	}
}


