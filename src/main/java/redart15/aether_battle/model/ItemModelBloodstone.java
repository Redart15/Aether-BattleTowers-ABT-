package redart15.aether_battle.model;

import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.MathHelper;
import org.lwjgl.opengl.GL11;

import static redart15.aether_battle.AetherBattleMod.MOD_ID;

public class ItemModelBloodstone extends ItemModelStandard implements AetherBattleRenderSpecials {
	public static final float HEIGHT = 0.001f;
	public static final float WINDOW = 60.0F;
	private boolean inventoryRender = false;
	public static final IconCoordinate AURA = TextureRegistry.getTexture(MOD_ID + ":item/bloodstone/aura");

	public ItemModelBloodstone(Item item, String namespace) {
		super(item, namespace);
	}

	@Override
	public void preRenderInventory() {
		this.inventoryRender = true;
	}

	@Override
	public void postRenderInventory() {
		this.inventoryRender = false;
	}

	@Override
	public void renderItemSpecialOnPlayer(Tessellator tessellator, Player player, ItemStack stack, int layer, float partialTick) {
		if (this.inventoryRender) {
			return;
		}
		GL11.glTranslatef(0.0F, player.heightOffset - 0.12f, 0.0F);
		double minU = AURA.getIconUMin();
		double minV = AURA.getIconVMin();
		double maxU = AURA.getIconUMax();
		double maxV = AURA.getIconVMax();
		AURA.parentAtlas.bind();
		float size = getSize(player.tickCount, partialTick);
		GL11.glRotatef(getAngle(player.tickCount, partialTick), 0.0f, 1.0f, 0.0f);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0f, -1.0f, 0.0f);
		tessellator.addVertexWithUV(-size, HEIGHT, -size, maxU, maxV);
		tessellator.addVertexWithUV(-size, HEIGHT, size, maxU, minV);
		tessellator.addVertexWithUV(size, HEIGHT, size, minU, minV);
		tessellator.addVertexWithUV(size, HEIGHT, -size, minU, maxV);
		tessellator.draw();
	}

	private static float getAngle(float tickCount, float partialTick) {
		float time = tickCount + partialTick;
		return (time / WINDOW) * 360.0f;
	}

	private static float getSize(float tickCount, float partialTick) {
		float time = tickCount + partialTick;
		float angleDegrees = (time / WINDOW) * 360.0f;
		float sin = (float) Math.sin(MathHelper.toRadians(angleDegrees));
		float wave = (sin + 1f) * 0.5f;
		return 0.7f + wave * 0.5f;
	}

}
