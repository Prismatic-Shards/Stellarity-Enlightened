package prismatic.shards.stellarity.utils;

public class StellarityConfig {
	private boolean joinMessage = true;
	private boolean enableEndCrystalDrop = true;
	private boolean enableTotemVoidSaving = true;
	private boolean alwaysGenerateEgg = true;
	private int dragonHealth = 200;

	public StellarityConfig() {
	}

	public StellarityConfig(boolean joinMessage, boolean enableEndCrystalDrop, boolean enableTotemVoidSaving, boolean alwaysGenerateEgg, int dragonHealth) {
		this.joinMessage = joinMessage;
		this.enableEndCrystalDrop = enableEndCrystalDrop;
		this.enableTotemVoidSaving = enableTotemVoidSaving;
		this.alwaysGenerateEgg = alwaysGenerateEgg;
		this.dragonHealth = dragonHealth;
	}

	public int getDragonHealth() {
		return dragonHealth;
	}

	public void setDragonHealth(int dragonHealth) {
		this.dragonHealth = dragonHealth;
	}

	public boolean isJoinMessage() {
		return joinMessage;
	}

	public void setJoinMessage(boolean joinMessage) {
		this.joinMessage = joinMessage;
	}

	public boolean isEnableEndCrystalDrop() {
		return enableEndCrystalDrop;
	}

	public void setEnableEndCrystalDrop(boolean enableEndCrystalDrop) {
		this.enableEndCrystalDrop = enableEndCrystalDrop;
	}

	public boolean isEnableTotemVoidSaving() {
		return enableTotemVoidSaving;
	}

	public void setEnableTotemVoidSaving(boolean enableTotemVoidSaving) {
		this.enableTotemVoidSaving = enableTotemVoidSaving;
	}

	public boolean isAlwaysGenerateEgg() {
		return alwaysGenerateEgg;
	}

	public void setAlwaysGenerateEgg(boolean alwaysGenerateEgg) {
		this.alwaysGenerateEgg = alwaysGenerateEgg;
	}
}
