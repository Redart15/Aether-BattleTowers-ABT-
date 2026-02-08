package redart15.aether_battle.entity;

import net.minecraft.core.util.collection.NamespaceID;
import redart15.aether_battle.entity.projectile.ProjectileChaotic;
import turniplabs.halplibe.helper.EntityHelper;

import static redart15.aether_battle.AetherBattleMod.MOD_ID;

public class AetherBattleEntities {
	private static boolean hasInit = false;

	public static void init() {
		if (!hasInit) {
			hasInit = true;
			initProjectile();
		}

	}

	public static void initProjectile() {
		EntityHelper.createEntity(ProjectileChaotic.class, NamespaceID.getPermanent(MOD_ID, ":chaotic_wand"), null);
	}
}
