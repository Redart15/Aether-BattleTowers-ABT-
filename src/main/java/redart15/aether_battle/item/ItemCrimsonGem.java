package redart15.aether_battle.item;

import com.mojang.nbt.tags.CompoundTag;
import net.minecraft.client.render.LightmapHelper;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.Global;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import org.lwjgl.opengl.GL11;
import redart15.aether_battle.effect.AetherBattleEffects;
import sunsetsatellite.catalyst.effects.api.effect.IHasEffects;
import teamport.aether.item.accessory.IAccessoryEffects;
import teamport.aether.item.accessory.ItemTrinket;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemCrimsonGem extends ItemTrinket implements IAccessoryEffects, AetherBattleRenderSpecials {
	public static final int COOLDOWN = 8 * Global.TICKS_PER_SECOND;
	private static final Random random = new Random();

	public ItemCrimsonGem(String translationKey, String namespaceId, int id, String name) {
		super(translationKey, namespaceId, id, name);
	}

	@Override
	public void inventoryTick(ItemStack itemstack, World world, Entity entity, int slotId, boolean flag) {
		if (!(entity instanceof Player)) {
			return;
		}
		this.applyEffect(itemstack, world, (Player) entity, slotId);
	}

	private void applyEffect(ItemStack itemstack, World world, Player player, int slotId) {
		CompoundTag tag = itemstack.getData();
		if (slotId >= player.inventory.mainInventory.length && slotId - player.inventory.mainInventory.length >= 6) {
			int time = advanceTime(tag);
			if (time <= COOLDOWN) {
				return;
			}
			tag.putInt("time", 0);
			List<Entity> entityList = world.getLoadedEntityList();
			for (Entity victim : new ArrayList<>(entityList)) {
				if (this.canApply(victim, player)) {
					continue;
				}
				AetherBattleEffects.quickStartEffect((IHasEffects<?>) victim, AetherBattleEffects.blood_letting, 1);
				world.playSoundAtEntity(player, player, "aether_battle:bloodstone.cutting", 0.5F + this.random.nextFloat() * 0.4f, 0.5F + this.random.nextFloat() * 0.7f);
			}
			return;
		}
		tag.putInt("time", 0);
	}

	private boolean canApply(Entity victom, Player player) {
		return victom.distanceTo(player) >= 16
			|| !(victom instanceof IHasEffects)
			|| !(victom instanceof Mob)
			|| victom == player
			|| !((Mob) victom).nickname.isEmpty();
	}

	private static int advanceTime(CompoundTag tag) {
		int time = tag.getInteger("time");
		tag.putInt("time", ++time);
		return time;
	}

	@Override
	public void removeEffect(Player player, ItemStack accessory) {
		CompoundTag tag = accessory.getData();
		tag.putInt("time", 0);
	}

	@Override
	public void renderSpecials(Tessellator tessellator, Player player, ItemStack stack, int layer , float partialTick) {
		renderEffect(tessellator, player, layer, partialTick);
	}

	private void renderEffect(Tessellator tessellator, Player player, int layer, float partialTick) {
		IconCoordinate tex = TextureRegistry.getTexture("minecraft:block/stone");
		GL11.glTranslatef(0.0F, 1.5f, 0.0F);
		double minU = tex.getIconUMin();
		double minV = tex.getIconVMin();
		double maxU = tex.getIconUMax();
		double maxV = tex.getIconVMax();
		tex.parentAtlas.bind();
		tessellator.startDrawingQuads();
//		tessellator.setLightmapCoord(LightmapHelper.getLightmapCoord(15, 15));
//		tessellator.setColorRGBA_F(1.0f,1.0f, 1.0f,1.0f);
		tessellator.setNormal(0.0f, 1.0f, 0.0f);


		renderVertex(tessellator, minU, minV, -1, 0.001f, -1);
		renderVertex(tessellator, minU, maxV, -1, 0.001f, 1);
		renderVertex(tessellator, maxU, maxV, 1, 0.001f, 1);
		renderVertex(tessellator, maxU, minV, 1, 0.001f, -1);
//
//		tessellator.addVertexWithUV(-1, 0, 1, minU, maxV);
//		tessellator.addVertexWithUV(1, 0, 1, maxU, maxV);
//		tessellator.addVertexWithUV(1, 0, -1, maxU, minV);
		tessellator.draw();
	}

	private static void renderVertex(Tessellator tessellator, double minU, double minV, double x, double y, double z) {
		if (LightmapHelper.isLightmapEnabled()) {
			tessellator.setLightmapCoord(LightmapHelper.getLightmapCoord(15, 15));
		}
		tessellator.setColorOpaque_F(1.0f, 1.0f, 1.0f);
		tessellator.addVertexWithUV(x, y, z, minU, minV);
	}
}
