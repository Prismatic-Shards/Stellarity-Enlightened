# 0.6.0

# Changes

Unless annotated, all changes are considered to be up to date with
the [wiki](https://koharasbasement.wiki.gg/wiki/Stellarity) and the [datapack](https://modrinth.com/datapack/stellarity)

## Items

**[*] Prismatic Pearl**

- Proper visible trail

**[*] Enderite Upgrade**

- Updated so that the upgrade name is correct

**[+] Shulker Helmet**

**[+] Shulker Chestplate**

**[+] Shulker Leggings**

**[+] Shulker Boots**

## Blocks

**[*] Altar of the Accursed**

- Requires mineable AOTAs to now be mined with a diamond pickaxe or better

## Advancements

**[+] Cursed Crafting**

- New criterion `special_craft` is available for use in datapacks
- This criterion is triggered for special crafting invoked by blocks such as AOTA, but will also be used for stuff like
  Cauldron and Altar of Light crafting in the future

**[+] I am Titanium!**

**[*] The End... Again...**

- Updated to use the ingame advancement detector
- Removed criterion `advancement_completed`

## Mechanics

**[*] Fabric API Data Attachment Hotfix**

- Fixed a bug where the data attachment system would desync when setting data attachments via `/data modify`

## Recipes

### AOTA

**[+] Netherite Armor + 4 Shulker Shells + 1 Enderite Upgrade = Shulker Armor**

## Entities

**[+] End Villagers**

- End Armorer and Butcher variants added
- Missing trade for Copper Elektra Shield, will be added in a future update

**[*] Ender Dragon**

- Updated boss bar colors

**[*] End Crystal**

- Can be breakable into item
- Right clicking yields a nice noise like the original end crystal

## Mechanics

**[*] Item Tooltips**

- Uses the colors from the datapack

**[*] Configuration**

- Implemented a basic infrastructure for configuration

## Biomes

**[*] The End**

- Updated the main island to use Stellarity's worldgen placed features

## Structures

**[*] Exit Portal**

- Implemented Stellarity exit portal chest particles

# Developers Note

I hate microslop
