package xyz.kohara.stellarity.registry;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.interface_injection.ExtEndCrystal;
import xyz.kohara.stellarity.interface_injection.ExtItemEntity;
import xyz.kohara.stellarity.registry.entity.ThrownPrismaticPearl;

import java.util.Map;

public class StellarityDataAttachments {
	public static final AttachmentType<Integer> GLOW_COLOR = AttachmentRegistry.create(Stellarity.id("glow_color"), builder -> builder.
		syncWith(ByteBufCodecs.VAR_INT, AttachmentSyncPredicate.all()).persistent(Codec.INT)
	);

	public static final AttachmentType<Map<Identifier, Integer>> ENCHANTMENTS = AttachmentRegistry.create(Stellarity.id("enchantments"), builder -> builder.persistent(Codec.unboundedMap(Identifier.CODEC, Codec.INT))
	);

	public static final AttachmentType<ExtEndCrystal.Type> END_CRYSTAL_TYPE = AttachmentRegistry.create(Stellarity.id("end_crystal_type"), builder -> builder.persistent(ExtEndCrystal.Type.CODEC).syncWith(ExtEndCrystal.Type.STREAM_CODEC, AttachmentSyncPredicate.all())
	);

	public static final AttachmentType<Boolean> VOID_FISHING_BUFF = AttachmentRegistry.create(Stellarity.id("buff_void_fishing"), builder -> builder.persistent(Codec.BOOL).syncWith(ByteBufCodecs.BOOL, AttachmentSyncPredicate.all())
	);

	public static final AttachmentType<ExtItemEntity.ItemMode> ITEM_MODE = AttachmentRegistry.create(Stellarity.id("item_mode"), builder -> builder.persistent(ExtItemEntity.ItemMode.CODEC).syncWith(ExtItemEntity.ItemMode.STREAM_CODEC, AttachmentSyncPredicate.all())
	);

	public static final AttachmentType<ThrownPrismaticPearl.Trail> PRISMATIC_PEARL_TRAIL = AttachmentRegistry.create(Stellarity.id("prismatic_pearl_trail"), builder -> builder.persistent(ThrownPrismaticPearl.Trail.CODEC).syncWith(ThrownPrismaticPearl.Trail.STREAM_CODEC, AttachmentSyncPredicate.all())
	);

	public static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Data Attachments");
	}
}
