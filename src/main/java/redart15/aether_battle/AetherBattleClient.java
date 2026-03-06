package redart15.aether_battle;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.entity.particle.ParticleDispatcher;
import net.minecraft.client.render.texture.stitcher.AtlasStitcher;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.client.sound.SoundRepository;
import redart15.aether_battle.entity.particle.ParticleChaotic;
import turniplabs.halplibe.helper.TextureHelper;
import turniplabs.halplibe.util.ClientStartEntrypoint;

import static redart15.aether_battle.AetherBattleMod.MOD_ID;

public class AetherBattleClient implements ClientModInitializer, ClientStartEntrypoint {
	@Override
	public void onInitializeClient() {
		AetherBattleClient.registerTextures();
	}

	@Override
	public void beforeClientStart() {
		ParticleDispatcher dispatcher = ParticleDispatcher.getInstance();
		dispatcher.addDispatch("chaotic", (world, x, y, z, xa, ya, za, id) -> new ParticleChaotic(world, x, y, z, xa, ya, za));
		SoundRepository.registerNamespace(AetherBattleMod.MOD_ID);
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
