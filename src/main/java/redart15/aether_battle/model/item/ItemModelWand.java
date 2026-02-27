package redart15.aether_battle.model.item;

import net.minecraft.client.render.Font;
import net.minecraft.client.render.TextureManager;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import org.lwjgl.opengl.GL11;
import redart15.aether_battle.entity.particle.ParticleChaotic;

public class ItemModelWand extends ItemModelStandard {
	private IconCoordinate overlay;

	public ItemModelWand(Item item, String namespace) {
		super(item, namespace);
	}

	public ItemModelStandard setOverlay(String overlay) {
		this.overlay = TextureRegistry.getTexture(overlay);
		return this;
	}

	private IconCoordinate getOverlayIcon(Entity entity, ItemStack itemStack) {
		return this.overlay;
	}

	@Override
	public void renderItemIntoGui(Tessellator tessellator, Font font, TextureManager textureManager, ItemStack itemStack, int x, int y, float brightness, float alpha) {
		if(this.overlay != null){
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glEnable(GL11.GL_CULL_FACE);
			IconCoordinate tex = this.getOverlayIcon(null, itemStack);
			GL11.glDisable(GL11.GL_LIGHTING);
			tex.parentAtlas.bind();
			ParticleChaotic.ColorF color = ParticleChaotic.colorLerp();
			GL11.glColor4f(color.r, color.g, color.b, alpha);
			this.renderTexturedQuad(tessellator, x, y, tex, false, false);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_CULL_FACE);
			GL11.glDisable(GL11.GL_BLEND);
		}
		super.renderItemIntoGui(tessellator, font, textureManager, itemStack, x, y, brightness, alpha);
	}

	@Override
	public void renderItemInWorld(Tessellator tessellator, Entity entity, ItemStack itemStack, float brightness, float alpha, boolean worldTransform) {
		super.renderItemInWorld(tessellator, entity, itemStack, brightness, alpha, worldTransform);
		if(overlay != null){
			this.renderOverlay(tessellator, entity, itemStack, alpha, worldTransform);
		}
	}

	private void renderOverlay(Tessellator tessellator, Entity entity, ItemStack itemStack, float alpha, boolean worldTransform) {
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		ParticleChaotic.ColorF color = ParticleChaotic.colorLerp();
		GL11.glColor4f(color.r, color.g, color.b, alpha);
		IconCoordinate tex = this.getOverlayIcon(entity, itemStack);

		int tileWidth = tex.width;
		float uMin = (float) tex.getIconUMin();
		float uMax = (float) tex.getIconUMax();
		float vMin = (float) tex.getIconVMin();
		float vMax = (float) tex.getIconVMax();
		float uDiff = uMin - uMax;
		float vDiff = vMin - vMax;
		float foon = 0.5F / tex.parentAtlas.getHeight();
		float goon = 0.0625F * (16.0F / tileWidth);
		GL11.glEnable(32826);
		float pixelWidth = 1.0F / tileWidth;
		if (worldTransform) {
			GL11.glTranslatef(-0.5F, -0.5F, 0.03125F);
		}

		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		tessellator.addVertexWithUV(0.0F, 0.0F, 0.0F, uMax, vMax);
		tessellator.addVertexWithUV(1.0F, 0.0F, 0.0F, uMin, vMax);
		tessellator.addVertexWithUV(1.0F, 1.0F, 0.0F, uMin, vMin);
		tessellator.addVertexWithUV(0.0F, 1.0F, 0.0F, uMax, vMin);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1.0F);
		tessellator.addVertexWithUV(0.0F, 1.0F, -0.0625F, uMax, vMin);
		tessellator.addVertexWithUV(1.0F, 1.0F, -0.0625F, uMin, vMin);
		tessellator.addVertexWithUV(1.0F, 0.0F, -0.0625F, uMin, vMax);
		tessellator.addVertexWithUV(0.0F, 0.0F, -0.0625F, uMax, vMax);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1.0F, 0.0F, 0.0F);

		for (int i = 0; i < tileWidth; ++i) {
			float texProgress = i * pixelWidth;
			float u = uMax + uDiff * texProgress - foon;
			float x = texProgress;
			tessellator.addVertexWithUV(x, 0.0F, -0.0625F, u, vMax);
			tessellator.addVertexWithUV(x, 0.0F, 0.0F, u, vMax);
			tessellator.addVertexWithUV(x, 1.0F, 0.0F, u, vMin);
			tessellator.addVertexWithUV(x, 1.0F, -0.0625F, u, vMin);
		}

		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);

		for (int i = 0; i < tileWidth; ++i) {
			float texProgress = i * pixelWidth;
			float u = uMax + uDiff * texProgress - foon;
			float x = texProgress + goon;
			tessellator.addVertexWithUV(x, 1.0F, -0.0625F, u, vMin);
			tessellator.addVertexWithUV(x, 1.0F, 0.0F, u, vMin);
			tessellator.addVertexWithUV(x, 0.0F, 0.0F, u, vMax);
			tessellator.addVertexWithUV(x, 0.0F, -0.0625F, u, vMax);
		}

		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);

		for (int i = 0; i < tileWidth; ++i) {
			float texProgress = i * pixelWidth;
			float v = vMax + vDiff * texProgress - foon;
			float y = texProgress + goon;
			tessellator.addVertexWithUV(0.0F, y, 0.0F, uMax, v);
			tessellator.addVertexWithUV(1.0F, y, 0.0F, uMin, v);
			tessellator.addVertexWithUV(1.0F, y, -0.0625F, uMin, v);
			tessellator.addVertexWithUV(0.0F, y, -0.0625F, uMax, v);
		}

		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1.0F, 0.0F);

		for (int i = 0; i < tileWidth; ++i) {
			float texProgress = i * pixelWidth;
			float v = vMax + vDiff * texProgress - foon;
			float y = texProgress;
			tessellator.addVertexWithUV(1.0F, y, 0.0F, uMin, v);
			tessellator.addVertexWithUV(0.0F, y, 0.0F, uMax, v);
			tessellator.addVertexWithUV(0.0F, y, -0.0625F, uMax, v);
			tessellator.addVertexWithUV(1.0F, y, -0.0625F, uMin, v);
		}

		tessellator.draw();
		GL11.glDisable(32826);
		GL11.glDisable(3042);
	}
}
