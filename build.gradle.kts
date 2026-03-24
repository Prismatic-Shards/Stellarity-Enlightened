plugins {
	id("net.fabricmc.fabric-loom")
	id("me.modmuss50.mod-publish-plugin") version "1.1.0"
	id("co.uzzu.dotenv.gradle") version "4.0.0"
	`maven-publish`
}

version = providers.gradleProperty("mod.version").get()
group = providers.gradleProperty("maven.group").get()

base {
	archivesName = providers.gradleProperty("maven.archives_base_name")
}

repositories {
	// Add repositories to retrieve artifacts from in here.
	// You should only use this when depending on other mods because
	// Loom adds the essential maven repositories to download Minecraft and libraries from automatically.
	// See https://docs.gradle.org/current/userguide/declaring_repositories.html
	// for more information about repositories.
}
loom {
	accessWidenerPath = project.file("src/main/resources/${project.property("mod.id")}.ct")
}

fabricApi {
	configureDataGeneration {
		client = true
	}
}

dependencies {
	// To change the versions see the gradle.properties file
	minecraft("com.mojang:minecraft:${providers.gradleProperty("deps.minecraft_versions").get()}")

	implementation("net.fabricmc:fabric-loader:${providers.gradleProperty("deps.fabric_loader").get()}")

	// Fabric API. This is technically optional, but you probably want it anyway.
	implementation("net.fabricmc.fabric-api:fabric-api:${providers.gradleProperty("deps.fabric_api").get()}")

}

tasks.processResources {
	duplicatesStrategy = DuplicatesStrategy.INCLUDE
	
	inputs.property("id", project.property("mod.id"))
	inputs.property("name", project.property("mod.name"))
	inputs.property("version", project.property("mod.version"))
	inputs.property("minecraft", project.property("deps.minecraft"))
	inputs.property("fabric_api", project.property("deps.fabric_api"))

	val props = mapOf(
		"id" to project.property("mod.id"),
		"name" to project.property("mod.name"),
		"version" to project.property("mod.version"),
		"minecraft" to project.property("deps.minecraft"),
		"fabric_api" to project.property("deps.fabric_api"),
	)

	filesMatching("fabric.mod.json") { expand(props) }
}

tasks.withType<JavaCompile>().configureEach {
	options.release = 25
}

java {
	// Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build" task
	// if it is present.
	// If you remove this line, sources will not be generated.
	withSourcesJar()

	sourceCompatibility = JavaVersion.VERSION_25
	targetCompatibility = JavaVersion.VERSION_25
}



tasks.jar {
	inputs.property("archivesName", base.archivesName)

	from("LICENSE") {
		rename { "${it}_${base.archivesName.get()}" }
	}
}

// configure the maven publication
publishing {
	publications {
		register<MavenPublication>("mavenJava") {
			artifactId = base.archivesName.get()
			from(components["java"])
		}
	}

	// See https://docs.gradle.org/current/userguide/publishing_maven.html for information on how to set up publishing.
	repositories {
		// Add repositories to publish to here.
		// Notice: This block does NOT have the same function as the block in the top level.
		// The repositories here will be used for publishing your artifact, not for
		// retrieving dependencies.
	}
}

publishMods {
	file = tasks.jar.map { it.archiveFile.get() }
	displayName = "${property("mod.name")} ${property("mod.version")}"
	version = property("mod.version") as String
	changelog = file("CHANGELOG.md").readText()
	type = STABLE
	modLoaders.add("fabric")

	dryRun = !env.isPresent("MODRINTH_TOKEN")
		|| !env.isPresent("CURSEFORGE_TOKEN")

	modrinth {
		projectId = property("publish.modrinth") as String
		accessToken = env.fetch("MODRINTH_TOKEN", "")
		minecraftVersions.addAll(property("deps.minecraft_versions").toString().split(' '))
		requires("fabric-api")

	}

	curseforge {
		projectId = property("publish.curseforge") as String
		accessToken = env.fetch("CURSEFORGE_TOKEN", "")
		minecraftVersions.addAll(property("deps.minecraft_versions").toString().split(' '))
		requires("fabric-api")
	}
}