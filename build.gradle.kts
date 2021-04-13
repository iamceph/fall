import xyz.jpenilla.toothpick.gitCmd
import xyz.jpenilla.toothpick.toothpick

plugins {
    `java-library`
    id("xyz.jpenilla.toothpick") version "1.0.0-SNAPSHOT"
}

toothpick {
    forkName = "fall"
    groupId = "cz.iamceph.fall"
    forkUrl = "https://github.com/iamceph/fall"
    val versionTag = System.getenv("BUILD_NUMBER")
            ?: "\"${gitCmd("rev-parse", "--short", "HEAD").output}\""
    forkVersion = "git-$forkName-$versionTag"

    minecraftVersion = "1.16.5"
    nmsPackage = "1_16_R3"
    nmsRevision = "R0.1-SNAPSHOT"

    upstream = "Purpur"
    upstreamBranch = "ver/1.16.5"

    server {
        project = project(":$forkNameLowercase-server")
        patchesDir = rootProject.projectDir.resolve("patches/server")
    }

    api {
        project = project(":$forkNameLowercase-api")
        patchesDir = rootProject.projectDir.resolve("patches/api")
    }
}

subprojects {
    repositories {
        maven("https://nexus.velocitypowered.com/repository/velocity-artifacts-snapshots/")
    }

    java {
        sourceCompatibility = JavaVersion.toVersion(11)
        targetCompatibility = JavaVersion.toVersion(11)
    }
}