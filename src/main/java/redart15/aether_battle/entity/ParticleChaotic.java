package redart15.aether_battle.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.particle.Particle;
import net.minecraft.client.render.LightmapHelper;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;
import redart15.aether_battle.mixin.accessor.MinecraftAccessor;

import static redart15.aether_battle.AetherBattleMod.MOD_ID;

public class ParticleChaotic extends Particle {
	protected final float originalScale;
	protected static final int FRAME_SIZE = 30;
	protected static final int[] COLORS = new int[]{
		0x702963, // purple
		0xffcf40, // yellow
		0x6495ED, // blue
	};

	public static class ColorF{
		public final float r;
		public final float g;
		public final float b;
		public ColorF(float r, float g, float b){
			this.r = r;
			this.g = g;
			this.b = b;
		}
	}

	public ParticleChaotic(World world, double x, double y, double z, double xa, double ya, double za) {
		super(world, x, y, z, xa, ya, za);
		this.xd = this.xd * 0.1;
		this.yd = this.yd * 0.1;
		this.zd = this.zd * 0.1;
		this.xd += xa;
		this.yd += ya;
		this.zd += za;
		this.tex = TextureRegistry.getTexture(String.format("%s:particle/chaotic/%d", MOD_ID, this.random.nextInt(12)));
		this.originalScale = this.size;
		this.noPhysics = false;
		this.lifetime = Math.round((10 + this.random.nextInt(12)) / (this.random.nextFloat() + 0.1F));
	}

	@Override
	public void render(Tessellator t, float partialTick, double xOff, double yOff, double zOff, float xa, float ya, float za, float xa2, float za2) {
		float s = (this.age + partialTick) / this.lifetime;
		this.size = this.originalScale * (1.0F - s * s * 0.75F);
		ColorF color = colorLerp();
		this.rCol = color.r;
		this.gCol = color.g;
		this.bCol = color.b;
		if (this.tex == null) {
			return;
		}
		float u0 = (float)this.tex.getIconUMin();
		float u2 = (float)this.tex.getIconUMax();
		float v0 = (float)this.tex.getIconVMin();
		float v2 = (float)this.tex.getIconVMax();
		float r = 0.1F * this.size;
		float x = (float)(this.xo + (this.x - this.xo) * (double)partialTick - xOff);
		float y = (float)(this.yo + (this.y - this.yo) * (double)partialTick - yOff);
		float z = (float)(this.zo + (this.z - this.zo) * (double)partialTick - zOff);
		float br = 1.0F;

		if (LightmapHelper.isLightmapEnabled()) {
			t.setLightmapCoord(LightmapHelper.getLightmapCoord(15, 15));
		}

		t.setColorOpaque_F(this.rCol * br, this.gCol * br, this.bCol * br);
		t.addVertexWithUV(x - xa * r - xa2 * r, y - ya * r, z - za * r - za2 * r, u2, v2);
		t.addVertexWithUV(x - xa * r + xa2 * r, y + ya * r, z - za * r + za2 * r, u2, v0);
		t.addVertexWithUV(x + xa * r + xa2 * r, y + ya * r, z + za * r + za2 * r, u0, v0);
		t.addVertexWithUV(x + xa * r - xa2 * r, y - ya * r, z + za * r - za2 * r, u0, v2);
	}


	@Override
	public void tick() {
		this.xo = this.x;
		this.yo = this.y;
		this.zo = this.z;
		if (this.age++ >= this.lifetime) {
			this.remove();
		}
		this.move(this.xd, this.yd, this.zd);

		this.xd *= 0.87;
		this.yd *= 0.87;
		this.zd *= 0.87;
		if (this.onGround) {
			this.xd *= 0.5;
			this.zd *= 0.5;
		}
	}

	public static ColorF colorLerp(){
		int tickCount = ((MinecraftAccessor) Minecraft.getMinecraft()).getTicksRan();
		int segmentLength = FRAME_SIZE / COLORS.length;
		int currentSegment = (tickCount / segmentLength) % COLORS.length;
		int nextSegment = (currentSegment + 1) % COLORS.length;
		float segmentProgress = (tickCount % segmentLength) / (float) segmentLength;
		return setColors(segmentProgress, COLORS[currentSegment], COLORS[nextSegment]);
	}

	private static ColorF setColors(float segmentProgress, int startingColor, int endingColor) {
		float r = (startingColor >> 16 & 255) / 255.0F;
		float g = (startingColor >> 8 & 255) / 255.0F;
		float b = (startingColor & 255) / 255.0F;
		float nr = (endingColor >> 16 & 255) / 255.0F;
		float ng = (endingColor >> 8 & 255) / 255.0F;
		float nb = (endingColor & 255) / 255.0F;

		return new ColorF(
			MathHelper.lerp(r, nr, segmentProgress),
			MathHelper.lerp(g, ng, segmentProgress),
			MathHelper.lerp(b, nb, segmentProgress)
		);
	}
}
