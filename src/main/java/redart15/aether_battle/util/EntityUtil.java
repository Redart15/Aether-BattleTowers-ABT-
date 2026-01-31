package redart15.aether_battle.util;

import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.util.phys.HitResult;
import net.minecraft.core.util.phys.Vec3;
import net.minecraft.core.world.World;

import java.util.List;

public class EntityUtil {
	private EntityUtil(){}

	public static HitResult checkCollisionBetweenPoints(World world, Player player, float entityReach){
		HitResult hitBlock = checkBlockCollisionBetweenPoints(world, player, entityReach);
		HitResult hitEntity = checkEntityCollisionBetweenPoints(world, player, entityReach);
		if(hitBlock != null && hitEntity == null){
			return hitBlock;
		}
		if(hitBlock == null && hitEntity != null){
			return hitEntity;
		}
		if(hitBlock == null){
			return null;
		}
		double distanceBlock = hitBlock.distanceTo(player);
		double distanceEntity = hitEntity.distanceTo(player);
		if(distanceEntity > distanceBlock){
			return hitBlock;
		}else {
			return hitEntity;
		}
	}

	public static HitResult checkBlockCollisionBetweenPoints(World world, Player player, float entityReach){
		Vec3 pos1 = player.getPosition(1.0f, false);
		Vec3 look = player.getViewVector(1.0f);
		Vec3 pos2 = pos1.add(look.x * entityReach, look.y * entityReach, look.z * entityReach);
		return world.checkBlockCollisionBetweenPoints(pos1, pos2, false, false, true);
	}

	public static HitResult checkEntityCollisionBetweenPoints(World world, Player player, float entityReach) {
		Vec3 pos1 = player.getPosition(1.0f, false);
		Vec3 look = player.getViewVector(1.0f);
		Vec3 pos2 = pos1.add(look.x * entityReach, look.y * entityReach, look.z * entityReach);

		Entity pointedEntity = null;
		HitResult hitResult;

		AABB entitySearchBox = player.bb.expand(look.x * entityReach, look.y * entityReach, look.z * entityReach).grow(1.0F, 1.0F, 1.0F);
		List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(player, entitySearchBox);
		double pointedEntityDistance = 0.0F;

		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			if (!entity.isSelectable()) {
				continue;
			}
			float hitRadius = entity == player.vehicle ? 0.0F : entity.getPickRadius();
			AABB hitBox = entity.bb.grow(hitRadius, hitRadius, hitRadius);
			if (hitBox.contains(pos1)) {
				return new HitResult(entity);
			}
			hitResult = hitBox.clip(pos1, pos2);
			if (hitResult == null) {
				continue;
			}
			double entityDistance = pos1.distanceTo(hitResult.location);
			if (pointedEntity != null && entityDistance >= pointedEntityDistance) {
				continue;
			}
			pointedEntity = entity;
			pointedEntityDistance = entityDistance;
		}
		if (pointedEntity != null) {
			return new HitResult(pointedEntity);
		}
		return null;

	}
}
