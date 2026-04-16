package prismatic.shards.stellarity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.MinecraftServer;
import prismatic.shards.stellarity.registry.StellarityDataAttachments;

public record StellarityConfig(boolean joinMessage, boolean enableEndCrystalDrop, boolean enableTotemVoidSaving,
                               boolean alwaysGenerateEgg, int dragonHealth, int empressOfLightHealth,
                               int shulkingHealth) {


	public static final StellarityConfig DEFAULT = new StellarityConfig(true, false, true, false, 500, 750, 900);

	public StellarityConfig(boolean joinMessage, boolean enableEndCrystalDrop, boolean enableTotemVoidSaving, boolean alwaysGenerateEgg, int dragonHealth, int empressOfLightHealth, int shulkingHealth) {
		this.joinMessage = joinMessage;
		this.enableEndCrystalDrop = enableEndCrystalDrop;
		this.enableTotemVoidSaving = enableTotemVoidSaving;
		this.alwaysGenerateEgg = alwaysGenerateEgg;
		this.dragonHealth = dragonHealth;
		this.shulkingHealth = shulkingHealth;
		this.empressOfLightHealth = empressOfLightHealth;

	}

	public static Codec<StellarityConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
		Codec.BOOL.optionalFieldOf("join_message", true).forGetter(StellarityConfig::joinMessage),
		Codec.BOOL.optionalFieldOf("enable_end_crystal_drop", true).forGetter(StellarityConfig::enableEndCrystalDrop),
		Codec.BOOL.optionalFieldOf("enable_totem_void_saving", true).forGetter(StellarityConfig::enableTotemVoidSaving),
		Codec.BOOL.optionalFieldOf("always_generate_egg", true).forGetter(StellarityConfig::alwaysGenerateEgg),
		Codec.INT.optionalFieldOf("dragon_health", 300).forGetter(StellarityConfig::dragonHealth),
		Codec.INT.optionalFieldOf("empress_of_light_health", 750).forGetter(StellarityConfig::empressOfLightHealth),
		Codec.INT.optionalFieldOf("shulking_health", 900).forGetter(StellarityConfig::shulkingHealth)
	).apply(instance, StellarityConfig::new));

	public static StreamCodec<RegistryFriendlyByteBuf, StellarityConfig> STREAM_CODEC = StreamCodec.of(StellarityConfig::toNetwork, StellarityConfig::new);

	public StellarityConfig(RegistryFriendlyByteBuf buf) {
		boolean joinMessage = buf.readBoolean();
		boolean enableEndCrystalDrop = buf.readBoolean();
		boolean enableTotemVoidSaving = buf.readBoolean();
		boolean alwaysGenerateEgg = buf.readBoolean();
		int dragonHealth = buf.readInt();
		int empressOfLightHealth = buf.readInt();
		int shulkingHealth = buf.readInt();


		this(joinMessage, enableEndCrystalDrop, enableTotemVoidSaving, alwaysGenerateEgg, dragonHealth, empressOfLightHealth, shulkingHealth);
	}

	private static void toNetwork(RegistryFriendlyByteBuf buf, StellarityConfig stellarityConfig) {
		buf.writeBoolean(stellarityConfig.joinMessage);
		buf.writeBoolean(stellarityConfig.enableEndCrystalDrop);
		buf.writeBoolean(stellarityConfig.enableTotemVoidSaving);
		buf.writeBoolean(stellarityConfig.alwaysGenerateEgg);
		buf.writeInt(stellarityConfig.dragonHealth);
		buf.writeInt(stellarityConfig.empressOfLightHealth);
		buf.writeInt(stellarityConfig.shulkingHealth);
	}

	public static StellarityConfig get(MinecraftServer server) {
		return server.globalAttachments().getAttachedOrElse(StellarityDataAttachments.CONFIG, DEFAULT);
	}
}
