package redart15.aether_battle;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.entity.particle.ParticleDispatcher;
import net.minecraft.client.render.texture.stitcher.AtlasStitcher;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import redart15.aether_battle.entity.particle.ParticleChaotic;
import teamport.aether.AetherMod;
import turniplabs.halplibe.helper.TextureHelper;
import turniplabs.halplibe.util.ClientStartEntrypoint;

import static redart15.aether_battle.AetherBattleMod.MOD_ID;

public class AetherBatlleClient implements ClientModInitializer, ClientStartEntrypoint {
	@Override
	public void onInitializeClient() {
		AetherBatlleClient.registerTextures();
	}

	@Override
	public void beforeClientStart() {
		ParticleDispatcher dispatcher = ParticleDispatcher.getInstance();
		dispatcher.addDispatch("chaotic", (world, x, y, z, xa, ya, za, id) -> new ParticleChaotic(world, x, y, z, xa, ya, za));
	}

	@Override
	public void afterClientStart() {

	}

	public static void registerTextures() {
		for(AtlasStitcher stitcher : TextureRegistry.stitcherMap.values()) {
			try {
				TextureHelper.initializeAllFiles(MOD_ID, stitcher, Integer.MAX_VALUE);
			} catch (Exception e) {
				AetherBattleMod.LOGGER.error("Failed to initialize texture files!", e);
			}
		}

	}
}
