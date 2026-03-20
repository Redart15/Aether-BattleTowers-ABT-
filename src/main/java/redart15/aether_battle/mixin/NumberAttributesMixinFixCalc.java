package redart15.aether_battle.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import org.spongepowered.asm.mixin.Mixin;
import sunsetsatellite.catalyst.effects.api.attribute.type.NumberAttribute;
import sunsetsatellite.catalyst.effects.api.modifier.Modifier;
import sunsetsatellite.catalyst.effects.api.modifier.type.NumberModifier;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mixin(value = NumberAttribute.class, remap = false)
public class NumberAttributesMixinFixCalc {

	@WrapMethod(method = "validateModifiers")
	protected List<NumberModifier<? extends Number>> validateModifiers(List<Modifier<?>> modifiers, Operation<List<NumberModifier<? extends Number>>> original){
		NumberAttribute number = (NumberAttribute) (Object) this;
		return modifiers.stream()
			.filter(M -> M.attribute.getKey().equals(number.getKey()))
			.map(M -> {
				if (M instanceof NumberModifier) {
					return ((NumberModifier<? extends Number>) M);
				} else {
					return null;
				}
			})
			.filter(Objects::nonNull)
			.sorted(Comparator.comparing(M -> M.type))
			.collect(Collectors.toList());
	}
}
