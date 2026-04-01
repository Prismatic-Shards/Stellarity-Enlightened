pluginManagement {
	repositories {
		mavenLocal()
		mavenCentral()
		gradlePluginPortal()
		maven("https://maven.fabricmc.net/")
		maven("https://maven.kikugie.dev/snapshots") { name = "KikuGie Snapshots" }
		maven("https://maven.kikugie.dev/releases")
	}
}

plugins {
	id("dev.kikugie.stonecutter") version "0.9"
}

stonecutter {
	create(rootProject) {
		// See https://stonecutter.kikugie.dev/wiki/start/#choosing-minecraft-versions
		versions("26.1", "26.1.1")
		vcsVersion = "26.1.1"
	}
}


rootProject.name = "Stellarity"