package redart15.aether_battle.entity.projectile;

import com.mojang.nbt.tags.CompoundTag;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.projectile.Projectile;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.util.phys.HitResult;
import net.minecraft.core.util.phys.Vec3;
import net.minecraft.core.world.LevelListener;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.NotNull;
import redart15.aether_battle.block.AetherBattleTags;
import teamport.aether.entity.DamageInstance;
import teamport.aether.entity.MobUtil;
import teamport.aether.helper.ParticleMaker;

public class ProjectileChaoticWand extends Projectile {
	private final DamageInstance[] damageInstances;
	private int shake;

	public ProjectileChaoticWand(World world, Mob entityliving, DamageInstance ... instances) {
		super(world, entityliving);
		this.shake = 0;
		this.noPhysics = true;
		this.damageInstances = instances;
	}

	@Override
	protected void initProjectile() {
		this.defaultProjectileSpeed = 2.0F;
	}

	@Override
	public void lerpMotion(double xd, double yd, double zd) {
		this.xd = xd;
		this.yd = yd;
		this.zd = zd;
		if (this.xRotO == 0.0F && this.yRotO == 0.0F) {
			float f = MathHelper.sqrt(xd * xd + zd * zd);
			this.yRot = (float)(Math.atan2(xd, zd) * 30.0F / Math.PI);
			this.xRot = (float)(Math.atan2(yd, f) * 30.0F / Math.PI);
			this.xRotO = this.xRot;
			this.yRotO = this.yRot;
			this.moveTo(this.x, this.y, this.z, this.yRot, this.xRot);
			this.ticksInGround = 0;
		}
	}
	@Override
	public void tick() {
		if (this.shake > 0) {
			--this.shake;
		}

		if (this.xRotO == 0.0F && this.yRotO == 0.0F) {
			float f = MathHelper.sqrt(this.xd * this.xd + this.zd * this.zd);
			this.yRotO = this.yRot = (float)(Math.atan2(this.xd, this.zd) * 30.0F / Math.PI);
			this.xRotO = this.xRot = (float)(Math.atan2(this.yd, f) * 30.0F / Math.PI);
		}
		super.tick();
	}

	@Override
	public void onHit(HitResult hitResult) {
		if (hitResult.entity != null) {
			MobUtil.multiHit(this.owner, hitResult.entity, this.damageInstances);
		} else {
			Block<?> block = this.world.getBlock(hitResult.x, hitResult.y, hitResult.z);
			if (block != null && block.hasTag(AetherBattleTags.BRIDLE)) {
				world.setBlockWithNotify(hitResult.x, hitResult.y, hitResult.z, 0);
				world.playBlockEvent(LevelListener.EVENT_BLOCK_BREAK, hitResult.x, hitResult.y, hitResult.z, block.id());
				this.remove();
			}
		}
	}

	@Override
	public void addAdditionalSaveData(@NotNull CompoundTag tag) {
		super.addAdditionalSaveData(tag);
	}

	@Override
	public void readAdditionalSaveData(@NotNull CompoundTag tag) {
		super.readAdditionalSaveData(tag);
	}

	@Override
	public void waterTick() {
		super.waterTick();
		this.projectileSpeed = this.defaultProjectileSpeed;
	}

	@Override
	public HitResult getHitResult() {
		Vec3 oldPosition = Vec3.getTempVec3(this.x, this.y, this.z);
		Vec3 newPosition = Vec3.getTempVec3(this.x + this.xd, this.y + this.yd, this.z + this.zd);
		return this.world.checkBlockCollisionBetweenPoints(oldPosition, newPosition, false, false, true);
	}
}
