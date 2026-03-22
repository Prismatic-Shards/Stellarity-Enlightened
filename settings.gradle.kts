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
		fun match(version: String, name: String = version) =
			if (stonecutter.eval(version, "> 1.21.11"))
				version("$name", version).buildscript = "build.gradle.kts"
			else
				version("$name", version).buildscript = "build-obf.gradle.kts"


		match("1.21.1")
		match("26.1-rc-2", "latest")

		vcsVersion = "1.21.1"
	}
}

rootProject.name = "Stellarity"