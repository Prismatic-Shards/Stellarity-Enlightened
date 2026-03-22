import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
	id("net.fabricmc.fabric-loom")
	id("dev.kikugie.fletching-table.fabric") version "0.1.0-alpha.22"
	id("me.modmuss50.mod-publish-plugin") version "1.1.0"

	kotlin("jvm") version "2.2.10"
	id("com.google.devtools.ksp") version "2.2.10-2.0.2"
	// `maven-publish`
}

kotlin {
	compilerOptions.jvmTarget.set(JvmTarget.JVM_21)
}


version = "${property("mod.version")}+${stonecutter.current.version}"
base.archivesName = property("mod.id") as String

val requiredJava = when {
	stonecutter.eval(stonecutter.current.version, ">=1.20") -> JavaVersion.VERSION_21
	stonecutter.eval(stonecutter.current.version, ">=1.18") -> JavaVersion.VERSION_17
	stonecutter.eval(stonecutter.current.version, ">=1.17") -> JavaVersion.VERSION_16
	else -> JavaVersion.VERSION_1_8
}



repositories {
	/**
	 * Restricts dependency search of the given [groups] to the [maven URL][url],
	 * improving the setup speed.
	 */
	fun strictMaven(url: String, alias: String, vararg groups: String) = exclusiveContent {
		forRepository { maven(url) { name = alias } }
		filter { groups.forEach(::includeGroup) }
	}
	strictMaven("https://www.cursemaven.com", "CurseForge", "curse.maven")
	strictMaven("https://api.modrinth.com/maven", "Modrinth", "maven.modrinth")
	maven("https://maven.blamejared.com")
	maven("https://maven.latvian.dev/releases")
	maven("https://thedarkcolour.github.io/KotlinForForge/")

}

dependencies {
	/**
	 * Fetches only the required Fabric API modules to not waste time downloading all of them for each version.
	 * @see <a href="https://github.com/FabricMC/fabric">List of Fabric API modules</a>
	 */
	fun fapi(vararg modules: String) {
		for (it in modules) implementation(fabricApi.module(it, property("deps.fabric_api") as String))
	}

	minecraft("com.mojang:minecraft:${stonecutter.current.version}")
	implementation("net.fabricmc:fabric-loader:${property("deps.fabric_loader")}")
	implementation("net.fabricmc.fabric-api:fabric-api:${property("deps.fabric_api")}")
}


stonecutter {
	replacements.string(current.parsed.matches(">1.21.11")) {
		replace("GuiGraphics.", "GuiGraphicsExtractor.")
		replace("GuiGraphics ", "GuiGraphicsExtractor ")
		replace("GuiGraphics;", "GuiGraphicsExtractor;")
		replace("net.fabricmc.fabric.api.itemgroup", "net.fabricmc.fabric.api.creativetab")
		replace("ItemGroupEvents", "CreativeModeTabEvents")
		replace("FabricItemGroup", "FabricCreativeModeTab")
		replace("FabricTagProvider.EntityTypeTagProvider", "FabricTagsProvider.EntityTypeTagsProvider")
		replace("FabricTagProvider.BlockTagProvider", "FabricTagsProvider.BlockTagsProvider")
		replace("FabricTagProvider.ItemTagProvider", "FabricTagsProvider.ItemTagsProvider")
		replace("FabricTagProvider", "FabricTagsProvider")
		replace("classTweaker v1 named", "classTweaker v1 official")
		replace("ExtEndDragonFight", "ExtEndDragonFight")
		replace("EndDragonFightMixin", "EndDragonFightMixin")
		replace("EndDragonFightDataMixin", "EndDragonFightDataMixin")
		replace("ExtEndSpike", "ExtEndSpike")
		replace("SpikeFeatureMixin", "SpikeFeatureMixin")
		replace("ExtSpikeFeature", "ExtSpikeFeature")
		replace(
			"net/minecraft/world/level/levelgen/feature/SpikeFeature",
			"net/minecraft/world/level/levelgen/feature/EndSpikeFeature"
		)
		replace(
			"net.minecraft.world.level.levelgen.feature.SpikeFeature",
			"net.minecraft.world.level.levelgen.feature.EndSpikeFeature"
		)
		replace("SpikeFeature", "EndSpikeFeature")
		replace(
			"EndDragonFight",
			"EnderDragonFight",
		)

		replace("ParticleFactoryRegistry", "ParticleProviderRegistry")
		replace("FabricDataOutput", "FabricPackOutput")
		replace("BlockModelGenerators", "BlockModelGenerators")
		replace("FabricBlockLootTableProvider", "FabricBlockLootSubProvider")
		replace("SimpleFabricLootTableProvider", "SimpleFabricLootTableSubProvider")
		replace("net.minecraft.client.renderer.item.BlockModel", "net.minecraft.client.renderer.block.model.BlockModel")

		replace("net.minecraft.advancements.critereon", "net.minecraft.advancements.criterion")
		replace("net/minecraft/advancements/critereon", "net/minecraft/advancements/criterion")

		replace("ResourceLocation", "Identifier")
		replace("projectile.Arrow", "projectile.arrow.Arrow")
		replace("projectile.AbstractArrow", "projectile.arrow.AbstractArrow")
		replace("projectile/Arrow", "projectile/arrow/Arrow")
		replace("projectile/AbstractArrow", "projectile/arrow/AbstractArrow")
		replace(
			"net.minecraft.world.entity.boss.EnderDragonPart",
			"net.minecraft.world.entity.boss.enderdragon.EnderDragonPart"
		)
		replace("net.minecraft.world.entity.animal.Fox", "net.minecraft.world.entity.animal.fox.Fox")

		replace("MobEffects.MOVEMENT_SLOWDOWN", "MobEffects.SLOWNESS")
		replace("MobEffects.DAMAGE_BOOST", "MobEffects.STRENGTH")
		replace("MobEffects.DAMAGE_RESISTANCE", "MobEffects.RESISTANCE")
		replace("MobEffects.MOVEMENT_SPEED", "MobEffects.SPEED")
		replace("MobEffects.DIG_SPEED", "MobEffects.HASTE")
		replace("MobEffects.DIG_SLOWDOWN", "MobEffects.MINING_FATIGUE")
		replace("MobEffects.CONFUSION", "MobEffects.NAUSEA")
		replace("MobEffects.HEALTH_BOOST", "MobEffects.HEALTH_BOOST")
		replace("MobEffects.HEAL", "MobEffects.INSTANT_HEALTH")
		replace("net.minecraft.data.models.", "net.minecraft.client.data.models.")
		replace("noCollission()", "noCollision()")
		replace(
			"net.minecraft.world.entity.projectile.ThrowableItemProjectile",
			"net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile"
		)
	}

}

fletchingTable {
	mixins.register("main") {
		mixin("default", "stellarity.mixins.json") {
			env("DEFAULT")
		}
		mixin("client", "stellarity.client.mixins.json") {
			env("CLIENT")
		}
	}
}


loom {


	mods {
		create(project.property("mod.id") as String) {
			sourceSet(sourceSets["main"])
		}
	}

	fabricModJsonPath = rootProject.file("src/main/resources/fabric.mod.json") // Useful for interface injection
	accessWidenerPath = sc.process(rootProject.file("src/main/resources/stellarity.accesswidener"), "build/dev.aw")
	file("build/generated/stonecutter/main/resources/stellarity.accesswidener").let {
		if (it.exists()) accessWidenerPath = it
	}

	decompilerOptions.named("vineflower") {
		options.put("mark-corresponding-synthetics", "1") // Adds names to lambdas - useful for mixins
	}

	runConfigs["client"].apply {
		programArgs("--username=StellarityDev")
	}

	runConfigs["server"].apply {
		runDir = "./serverrun"
	}


	runConfigs.all {
		ideConfigGenerated(true)
		vmArgs("-Dmixin.debug.export=true -XX:+AllowEnhancedClassRedefinition")
	}


}



fabricApi {
	configureDataGeneration {
		client = true
	}

	sourceSets["main"].apply {
		kotlin {
			if (stonecutter.eval(stonecutter.current.version, "> 1.21.1")) {
				exclude("dev/aaronhowser/mods/patchoulidatagen/**")
			}
		}
	}

}








tasks.withType<ProcessResources> {
	duplicatesStrategy = DuplicatesStrategy.INCLUDE
	inputs.property("id", project.property("mod.id"))
	inputs.property("name", project.property("mod.name"))
	inputs.property("version", project.property("mod.version"))
	inputs.property("minecraft", project.property("mod.mc_dep"))
	inputs.property("fabric_api", project.property("deps.fabric_api"))


	val props = mapOf(
		"id" to project.property("mod.id"),
		"name" to project.property("mod.name"),
		"version" to project.property("mod.version"),
		"minecraft" to project.property("mod.mc_dep"),
		"fabric_api" to project.property("deps.fabric_api"),
	)

	filesMatching("fabric.mod.json") { expand(props) }


	val mixinJava = "JAVA_${requiredJava.majorVersion}"
	filesMatching("*.mixins.json") { expand("java" to mixinJava) }

	print("start try rename")
	if (stonecutter.eval(stonecutter.current.version, "< 1.21")) {

		filesMatching("data/stellarity/structure/**/*") {
			relativePath = RelativePath(true, relativePath.replace(Regex("structure/"), "structures/"))
		}
	}
}


java {
	withSourcesJar()
	targetCompatibility = requiredJava
	sourceCompatibility = requiredJava
}

tasks {

	// Builds the version into a shared folder in `build/libs/${mod version}/`
	register<Copy>("buildAndCollect") {
		group = "build"
		from(jar.map { it.archiveFile }, jar.map { it.archiveFile })
		into(rootProject.layout.buildDirectory.file("libs/${project.property("mod.version")}"))
		dependsOn("build")
	}

	build {
		dependsOn("validateAccessWidener")
	}

	stonecutterGenerate {
		dependsOn("validateAccessWidener")
	}


}


// Publishes builds to Modrinth and Curseforge with changelog from the CHANGELOG.md file
// Publishing using publishMods task
publishMods {
	file = tasks.jar.map { it.archiveFile.get() }
	displayName = "${property("mod.name")} ${property("mod.version")} for ${property("mod.mc_title")}"
	version = property("mod.version") as String
	changelog = rootProject.file("CHANGELOG.md").readText()
	type = STABLE
	modLoaders.add("fabric")

	dryRun = !env.isPresent("MODRINTH_TOKEN")
		|| !env.isPresent("CURSEFORGE_TOKEN")

	modrinth {
		projectId = property("publish.modrinth") as String
		accessToken = env.fetch("MODRINTH_TOKEN", "")
		minecraftVersions.addAll(property("mod.mc_targets").toString().split(' '))
		requires("fabric-api")
		if (stonecutter.eval(stonecutter.current.version, "<= 1.21.1")) {
			optional("patchouli")
		}

	}

	curseforge {
		projectId = property("publish.curseforge") as String
		accessToken = env.fetch("CURSEFORGE_TOKEN", "")
		minecraftVersions.addAll(property("mod.mc_targets").toString().split(' '))
		requires("fabric-api")
		if (stonecutter.eval(stonecutter.current.version, "<= 1.21.1")) {
			optional("patchouli")
		}
	}
}


/*
// Publishes builds to a maven repository under `com.example:template:0.1.0+mc`
publishing {
    repositories {
        maven("https://maven.example.com/releases") {
            name = "myMaven"
            // To authenticate, create `myMavenUsername` and `myMavenPassword` properties in your Gradle home properties.
            // See https://stonecutter.kikugie.dev/wiki/tips/properties#defining-properties
            credentials(PasswordCredentials::class.java)
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }

    publications {
        create<MavenPublication>("mavenJava") {
            groupId = "${property("mod.group")}.${property("mod.id")}"
            artifactId = property("mod.id") as String
            version = project.version

            from(components["java"])
        }
    }
}
*/

