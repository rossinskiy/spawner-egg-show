# Spawner Egg Show

A client-side Fabric mod for Minecraft 1.21.8 that gives **spawner items a unique texture for each mob**.

On many servers every spawner is a plain `minecraft:spawner` item and the only thing that tells them apart is the name or lore ("Creeper Spawner", "Спавнер зомби", and so on). This mod reads that lore and swaps the item model for a matching icon, so you can see which spawner is which in your inventory, chests and hotbar without reading tooltips.

## Features

- 20 mob-specific spawner icons plus an icon for empty spawners
- Works on any server, nothing to install server-side
- Matches lore in **English and Russian**, case-insensitive
- Specific names win over general ones (a Stray spawner is not mistaken for a Skeleton one, a Cave Spider is not mistaken for a Spider)
- Does not change the real item data, only how it is drawn on your client

## Supported spawners

Blaze, Breeze, Cave Spider, Cow, Creeper, Drowned, Enderman, Ghast, Guardian, Magma Cube, Panda, Pig, Sheep, Shulker, Silverfish, Skeleton, Slime, Spider, Stray, Zombie, and Empty.

## Requirements

- Minecraft 1.21.8
- Fabric Loader 0.16.0 or newer
- Fabric API
- Java 21

## Installation

1. Install Fabric Loader and Fabric API for 1.21.8.
2. Drop `spawner-egg-show-<version>.jar` into your `mods` folder.
3. Join a server. Spawners whose lore names a supported mob will show the new icon.

## Adding more mobs

Patterns are registered in [`SpawnerEggShowClient.java`](src/main/java/com/spawnereggshow/SpawnerEggShowClient.java):

```java
reg("spawner_creeper", "creeper", "крипер");
```

Add a texture in `assets/spawnereggshow/textures/item/spawners/`, a block model in `models/block/`, an item definition in `items/`, then register the lore keywords. Register specific keywords before general ones, the first match wins.

## Building

```
gradlew build
```

The jar is written to `build/libs/`.

## License

MIT, see [LICENSE](LICENSE).
