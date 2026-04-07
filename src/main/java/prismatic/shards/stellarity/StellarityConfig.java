package prismatic.shards.stellarity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.MinecraftServer;
import prismatic.shards.stellarity.registry.StellarityDataAttachments;

public record StellarityConfig(boolean joinMessage, boolean enableEndCrystalDrop, boolean enableTotemVoidSaving,
                               boolean alwaysGenerateEgg, int dragonHealth) {


	public static final StellarityConfig DEFAULT = new StellarityConfig(true, true, true, true, 300);

	public StellarityConfig(boolean joinMessage, boolean enableEndCrystalDrop, boolean enableTotemVoidSaving, boolean alwaysGenerateEgg, int dragonHealth) {
		this.joinMessage = joinMessage;
		this.enableEndCrystalDrop = enableEndCrystalDrop;
		this.enableTotemVoidSaving = enableTotemVoidSaving;
		this.alwaysGenerateEgg = alwaysGenerateEgg;
		this.dragonHealth = dragonHealth;
	}

	public static Codec<StellarityConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
		Codec.BOOL.optionalFieldOf("join_message", true).forGetter(StellarityConfig::joinMessage),
		Codec.BOOL.optionalFieldOf("enable_end_crystal_drop", true).forGetter(StellarityConfig::enableEndCrystalDrop),
		Codec.BOOL.optionalFieldOf("enable_totem_void_saving", true).forGetter(StellarityConfig::enableTotemVoidSaving),
		Codec.BOOL.optionalFieldOf("always_generate_egg", true).forGetter(StellarityConfig::alwaysGenerateEgg),
		Codec.INT.optionalFieldOf("dragon_hStellarityConfigealth", 300).forGetter(StellarityConfig::dragonHealth)
	).apply(instance, StellarityConfig::new));

	public static StreamCodec<RegistryFriendlyByteBuf, StellarityConfig> STREAM_CODEC = StreamCodec.of(StellarityConfig::toNetwork, StellarityConfig::new);

	public StellarityConfig(RegistryFriendlyByteBuf buf) {
		boolean joinMessage = buf.readBoolean();
		boolean enableEndCrystalDrop = buf.readBoolean();
		boolean enableTotemVoidSaving = buf.readBoolean();
		boolean alwaysGenerateEgg = buf.readBoolean();
		int dragonHealth = buf.readInt();

		this(joinMessage, enableEndCrystalDrop, enableTotemVoidSaving, alwaysGenerateEgg, dragonHealth);
	}

	private static void toNetwork(RegistryFriendlyByteBuf buf, StellarityConfig stellarityConfig) {
		buf.writeBoolean(stellarityConfig.joinMessage);
		buf.writeBoolean(stellarityConfig.enableEndCrystalDrop);
		buf.writeBoolean(stellarityConfig.enableTotemVoidSaving);
		buf.writeBoolean(stellarityConfig.alwaysGenerateEgg);
		buf.writeInt(stellarityConfig.dragonHealth);
	}

	public static StellarityConfig get(MinecraftServer server) {
		return server.globalAttachments().getAttachedOrElse(StellarityDataAttachments.CONFIG, DEFAULT);
	}
}
