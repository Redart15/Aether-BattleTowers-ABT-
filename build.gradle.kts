import com.smushytaco.lwjgl_gradle.Preset
import java.net.URL


plugins {
	alias(libs.plugins.loom)
	alias(libs.plugins.lwjgl)
    java
}
val modVersion: Provider<String> = providers.gradleProperty("mod_version")
val modGroup: Provider<String> = providers.gradleProperty("mod_group")
val modName: Provider<String> = providers.gradleProperty("mod_name")

val javaVersion: Provider<Int> = libs.versions.java.map { it.toInt() }

//val battleTowersVersion = libs.versions.battletowers.get()
//val battleTowersReleaseTag = libs.versions.battletowersReleaseTag.get()
//
//val betterBattleJar = layout.buildDirectory.file("libs/betterbattletowers-$battleTowersVersion.jar")
//val betterBattleUrl =
//	"https://github.com/mayonaka8478/Better-than-Battle-Towers/releases/download/$battleTowersReleaseTag/betterbattletowers-$battleTowersVersion.jar"
//
//// 1️⃣ Download task
//val downloadBetterBattleJar = tasks.register("downloadBetterBattleJar") {
//	outputs.file(betterBattleJar)
//	doLast {
//		val file = betterBattleJar.get().asFile
//		file.parentFile.mkdirs()
//		if (!file.exists()) {
//			println("⬇️ Downloading Better Battle Towers $battleTowersVersion")
//			URL(betterBattleUrl).openStream().use { input ->
//				file.outputStream().use { output ->
//					input.copyTo(output)
//				}
//			}
//		} else {
//			println("✅ Better Battle Towers already downloaded")
//		}
//	}
//}
//
////// 2️⃣ Add as compile-time dependency
////dependencies {
////	implementation(files(betterBattleJar))
////}
//
//// 3️⃣ Copy to run/mods
//val copyBetterBattleJar = tasks.register("copyBetterBattleJar") {
//	dependsOn(downloadBetterBattleJar)
//	doLast {
//		val modsDir = file("run/mods")
//		modsDir.mkdirs()
//		val sourceFile = betterBattleJar.get().asFile
//		if (!sourceFile.exists()) {
//			throw GradleException("Better Battle Towers JAR not found! Download failed?")
//		}
//		sourceFile.copyTo(File(modsDir, sourceFile.name), overwrite = true)
//		println("📦 Copied Better Battle Towers JAR to $modsDir")
//	}
//}
//
//// 4️⃣ Ensure compileJava waits for copy
//tasks.withType<JavaCompile>().configureEach {
//	dependsOn(copyBetterBattleJar)
//}
//
//// 5️⃣ Ensure runClient waits for copy
//tasks.named("runClient") {
//	dependsOn(copyBetterBattleJar)
//}



base.archivesName = modName
group = modGroup.get()
version = modVersion.get()
loom {
    customMinecraftMetadata.set("https://downloads.betterthanadventure.net/bta-client/${libs.versions.btaChannel.get()}/v${libs.versions.bta.get()}/manifest.json")
}
repositories {
    mavenCentral()
	maven("https://jitpack.io")
    maven("https://maven.fabricmc.net/") { name = "Fabric" }
    maven("https://maven.thesignalumproject.net/infrastructure") { name = "SignalumMavenInfrastructure" }
    maven("https://maven.thesignalumproject.net/releases") { name = "SignalumMavenReleases" }
    ivy("https://github.com/Better-than-Adventure") {
        patternLayout { artifact("[organisation]/releases/download/v[revision]/[module].jar") }
        metadataSources { artifact() }
    }
    ivy("https://downloads.betterthanadventure.net/bta-client/${libs.versions.btaChannel.get()}/") {
        patternLayout { artifact("/v[revision]/client.jar") }
        metadataSources { artifact() }
    }
    ivy("https://downloads.betterthanadventure.net/bta-server/${libs.versions.btaChannel.get()}/") {
        patternLayout { artifact("/v[revision]/server.jar") }
        metadataSources { artifact() }
    }
    ivy("https://piston-data.mojang.com") {
        patternLayout { artifact("v1/[organisation]/[revision]/[module].jar") }
        metadataSources { artifact() }
    }
	ivy("https://github.com/") {
		patternLayout { artifact("v1/[organisation]/[revision]/[module].jar") }
		metadataSources { artifact() }
	}
	ivy ("https://github.com/"){
		patternLayout {
			artifact("[organization]/[module]/releases/download/[revision]/[module]-[revision].jar")
		}
		metadataSources { artifact() }
	}
	ivy("https://github.com/") {
		patternLayout {
			artifact("[organization]/[module]/releases/download/[revision]/[module]-[revision]+7.3_04.jar")
		}
		metadataSources { artifact() }
	}
	ivy("https://github.com/mayonaka8478/Better-than-Battle-Towers/") {
		patternLayout {
			artifact("releases/download/[organization]/[module]-[revision].jar")
		}
		metadataSources { artifact() }
	}
}
lwjgl {
	version = libs.versions.lwjgl
	implementation(Preset.MINIMAL_OPENGL)
}
dependencies {
    minecraft("::${libs.versions.bta.get()}")

	implementation(libs.aether)
	implementation(libs.battletowers)

	compileOnly(libs.btwaila)
	implementation(libs.dragonfly)
	implementation(libs.catalyst.core)
	implementation(libs.catalyst.effects)
	implementation(libs.uselessNumerical.get().let { "${it.group}:${it.name}:${it.version}-${libs.versions.bta.get()}" })

	runtimeOnly(libs.clientJar)
	implementation(libs.loader)
	// If you do not need Halplibe you can comment out or delete this line.
	implementation(libs.halplibe)
	implementation(libs.modMenu)
	implementation(libs.legacyLwjgl)

	implementation(libs.slf4jApi)
	implementation(libs.guava)
	implementation(libs.log4j.slf4j2.impl)
	implementation(libs.log4j.core)
	implementation(libs.log4j.api)
	implementation(libs.log4j.api12)
	implementation(libs.gson)

	implementation(libs.commonsLang3)
	include(libs.commonsLang3)
}
java {
	toolchain {
		languageVersion = javaVersion.map { JavaLanguageVersion.of(it) }
		vendor = JvmVendorSpec.ADOPTIUM
	}
	sourceCompatibility = JavaVersion.toVersion(javaVersion.get())
	targetCompatibility = JavaVersion.toVersion(javaVersion.get())
	withSourcesJar()
}
val licenseFile = run {
	val rootLicense = layout.projectDirectory.file("LICENSE")
	val parentLicense = layout.projectDirectory.file("../LICENSE")
	when {
		rootLicense.asFile.exists() -> {
			logger.lifecycle("Using LICENSE from project root: {}", rootLicense.asFile)
			rootLicense
		}
		parentLicense.asFile.exists() -> {
			logger.lifecycle("Using LICENSE from parent directory: {}", parentLicense.asFile)
			parentLicense
		}
		else -> {
			logger.warn("No LICENSE file found in project or parent directory.")
			null
		}
	}
}
tasks {
	withType<JavaCompile>().configureEach {
		options.encoding = "UTF-8"
		sourceCompatibility = javaVersion.get().toString()
		targetCompatibility = javaVersion.get().toString()
		if (javaVersion.get() > 8) options.release = javaVersion
	}
	named<UpdateDaemonJvm>("updateDaemonJvm") {
		languageVersion = libs.versions.gradleJava.map { JavaLanguageVersion.of(it.toInt()) }
		vendor = JvmVendorSpec.ADOPTIUM
	}
	withType<JavaExec>().configureEach { defaultCharacterEncoding = "UTF-8" }
	withType<Javadoc>().configureEach { options.encoding = "UTF-8" }
	withType<Test>().configureEach { defaultCharacterEncoding = "UTF-8" }
	withType<Jar>().configureEach {
		licenseFile?.let {
			from(it) {
				rename { original -> "${original}_${archiveBaseName.get()}" }
			}
		}
	}
	processResources {
		val resourceMap = mapOf(
			"version" to modVersion.get(),
			"fabricloader" to libs.versions.loader.get(),
			"halplibe" to libs.versions.halplibe.get(),
			"java" to libs.versions.java.get(),
			"modmenu" to libs.versions.modMenu.get(),
			"aether" to libs.versions.aether.get(),
			"battletowers" to libs.versions.battletowers.get()
		)
		inputs.properties(resourceMap)
		filesMatching("fabric.mod.json") { expand(resourceMap) }
		filesMatching("**/*.mixins.json") { expand(resourceMap.filterKeys { it == "java" }) }
	}
}
// Removes LWJGL2 dependencies
configurations.configureEach { exclude(group = "org.lwjgl.lwjgl") }
