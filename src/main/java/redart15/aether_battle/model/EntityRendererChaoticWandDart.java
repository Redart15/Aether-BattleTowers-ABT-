package redart15.aether_battle.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.tessellator.Tessellator;
import redart15.aether_battle.entity.ProjectileChaotic;

@Environment(EnvType.CLIENT)
public class EntityRendererChaoticWandDart extends EntityRenderer<ProjectileChaotic> {
	public void render(Tessellator tessellator, ProjectileChaotic dart, double x, double y, double z, float yaw, float partialTick) {
		/* the particles are a better indicator of where the projectile is*/
	}


}
