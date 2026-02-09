package redart15.aether_battle.entity.projectile;

import com.mojang.nbt.tags.CompoundTag;
import net.minecraft.core.Global;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.entity.projectile.Projectile;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.util.phys.HitResult;
import net.minecraft.core.util.phys.Vec3;
import net.minecraft.core.world.LevelListener;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.NotNull;
import redart15.aether_battle.block.AetherBattleTags;
import teamport.aether.entity.DamageInstance;
import teamport.aether.entity.MobUtil;
import teamport.aether.helper.ParticleMaker;

public class ProjectileChaotic extends Projectile {
	public int samples;
	protected DamageInstance[] damageInstances;

	public ProjectileChaotic(World world) {
		super(world);
		this.setSize(0.25F, 0.25F);
		this.initProjectile();
	}


	public ProjectileChaotic(World world, Mob mob, DamageInstance... instances) {
		super(world, mob);
		this.damageInstances = instances;
	}

	@Override
	public void setHeading(double newMotionX, double newMotionY, double newMotionZ, float speed, float randomness) {
		super.setHeading(newMotionX, newMotionY, newMotionZ, speed * 2.0f, randomness);
	}

	@Override
	protected void initProjectile() {
		this.defaultProjectileSpeed = 1.0F;
		this.samples = (int)Math.ceil(12 * this.defaultProjectileSpeed);
	}

	@Override
	public void tick() {
		super.tick();
		if(this.tickCount > 1) {
			this.spawnBeamParticles();
		}
		if(this.tickCount >= Global.TICKS_PER_SECOND){
			this.doEffect();
			this.remove();
		}
	}

	private void spawnBeamParticles() {
		for (int i = 0; i < samples; i++) {
			float progress = (float) i / samples;
			ParticleMaker.spawnParticle(world, "chaotic",
				MathHelper.lerp(this.x, this.x - this.xd, progress),
				MathHelper.lerp(this.y, this.y - this.yd, progress),
				MathHelper.lerp(this.z, this.z - this.zd, progress),
				0, 0, 0, 0, 256);
		}
	}

	protected void doEffect() {
		if (this.world == null) {
			return;
		}
		for (int j = 0; j < 32; ++j) {
			ParticleMaker.spawnParticle(this.world, "explode", this.x, this.y, this.z, 0.0F, 0.0F, 0.0F, 0, 256);
			ParticleMaker.spawnParticle(this.world, "smoke", this.x, this.y, this.z, 0.0F, 0.0F, 0.0F, 0, 256);
			ParticleMaker.spawnParticle(this.world, "largesmoke", this.x, this.y, this.z, 0.0F, 0.0F, 0.0F, 0, 256);
		}
	}


	@Override
	public void lerpMotion(double xd, double yd, double zd) {
		this.xd = xd;
		this.yd = yd;
		this.zd = zd;
		if (this.xRotO == 0.0F && this.yRotO == 0.0F) {
			float f = MathHelper.sqrt(xd * xd + zd * zd);
			this.yRot = (float) (Math.atan2(xd, zd) * 180.0F / Math.PI);
			this.xRot = (float) (Math.atan2(yd, f) * 180.0F / Math.PI);
			this.xRotO = this.xRot;
			this.yRotO = this.yRot;
			this.moveTo(this.x, this.y, this.z, this.yRot, this.xRot);
		}
	}

	@Override
	public void onHit(HitResult hitResult) {
		if (hitResult.entity != null) {
			MobUtil.multiHit(this.owner, hitResult.entity, this.damageInstances);
			world.playSoundAtEntity(null, hitResult.entity, "fireworks.twinkle1", this.random.nextFloat() * 0.4F + 0.2F, this.random.nextFloat() * 0.8f + 0.2F);
			if(this.damageInstances.length > 1){
			}
		} else {
			Block<?> block = this.world.getBlock(hitResult.x, hitResult.y, hitResult.z);
			if (block != null && block.hasTag(AetherBattleTags.BRIDLE)) {
				world.setBlockWithNotify(hitResult.x, hitResult.y, hitResult.z, 0);
				world.playBlockEvent(LevelListener.EVENT_BLOCK_BREAK, hitResult.x, hitResult.y, hitResult.z, block.id());
			} else {
				this.doEffect();
			}
		}
		this.remove();
	}

	@Override
	public void addAdditionalSaveData(@NotNull CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putInt("instanceSize", this.damageInstances.length);
		for (int i = 0; i < damageInstances.length; i++) {
			DamageInstance instance = damageInstances[i];
			tag.putInt("damage_" + i, instance.getDamage());
			tag.putString("type_" + i, instance.getType().getLanguageKey());
		}
	}

	@Override
	public void readAdditionalSaveData(@NotNull CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.damageInstances = new DamageInstance[tag.getInteger("instanceSize")];
		for (int i = 0; i < damageInstances.length; i++) {
			int damage = tag.getInteger("damage_" + i);
			String type = tag.getString("type_" + i);
			for (DamageType damageType : DamageType.values()) {
				if (damageType.getLanguageKey().equalsIgnoreCase(type)) {
					damageInstances[i] = DamageInstance.inst(damage, damageType);
					break;
				}
			}
		}
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
		return this.world.checkBlockCollisionBetweenPoints(oldPosition, newPosition, false, false, false);
	}
}
