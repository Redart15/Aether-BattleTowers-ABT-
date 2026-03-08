package redart15.aether_battle.mixin;

import net.minecraft.core.player.inventory.menu.MenuAbstract;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(value = MenuAbstract.class, remap = false)
public abstract class MenuAbstractMixinAddEffect {

	@Inject(method = "handleArmorEquip", )
}
