# Spaces-Library
A Simple Yet Effective Library Mod

This Library Add Many Useful Features To Make Modding Easier

Hammers Paxels Tree Fellers Excavators Mass Miners And Some Data Gen Stuff 

Find It Here https://www.curseforge.com/minecraft/mc-mods/spaces-library

How To Intgrate Into Your Own Project 

Add The Following To Your build.gradle

```java
repositories {
    maven{
        url "https://cursemaven.com/"
        content {
            includeGroup("curse.maven")
        }
    }
}
```

The Add  This To Your Dependencies

```java
dependencies {
    implementation fg.deobf('curse.maven:spacelib-858858:<fileVersion>')
}
```
