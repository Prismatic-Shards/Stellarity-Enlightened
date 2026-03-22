package xyz.kohara.stellarity.client.registry.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemFrameRenderer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import xyz.kohara.stellarity.registry.entity.PhantomItemFrame;

//? 1.21.1 {
/*import net.minecraft.client.resources.model.ModelIdentifier;
import net.minecraft.world.item.ItemStack;
import xyz.kohara.stellarity.Stellarity;
*///? }

@Environment(EnvType.CLIENT)
public class PhantomItemFrameRenderer extends ItemFrameRenderer<PhantomItemFrame> {
	public static final StateDefinition<Block, BlockState> FAKE_STATE_DEFINITION = (new StateDefinition.Builder<Block, BlockState>(Blocks.AIR)).add(new Property[]{}).create(Block::defaultBlockState, BlockState::new);

	//? 1.21.1 {
	/*private static final ModelIdentifier MODEL_LOCATION = new ModelIdentifier(Stellarity.id("phantom_item_frame"), "");

	public PhantomItemFrameRenderer(EntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	public ModelIdentifier getFrameModelResourceLoc(PhantomItemFrame itemFrame, ItemStack itemStack) {
		// because the frame turns invis there is no need for a map model cuz it wont be seen anyways

		return MODEL_LOCATION;
	}
	*///? } else {
	public PhantomItemFrameRenderer(EntityRendererProvider.Context context) {
		super(context);
	}
	//? }
}