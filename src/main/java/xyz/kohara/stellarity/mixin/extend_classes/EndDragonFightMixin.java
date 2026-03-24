package xyz.kohara.stellarity.mixin.extend_classes;


import net.minecraft.world.level.dimension.end.EnderDragonFight;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import xyz.kohara.stellarity.interface_injection.ExtEndDragonFight;

@Mixin(EnderDragonFight.class)
public class EndDragonFightMixin implements ExtEndDragonFight {
	@Unique
	private boolean portalChestGenerated;

	@Override
	public boolean stellarity$portalChestGenerated() {
		return portalChestGenerated;
	}

	@Override
	public void stellarity$setPortalChestGenerated(boolean generated) {
		this.portalChestGenerated = generated;
	}
}
