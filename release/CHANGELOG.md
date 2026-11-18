# EnderIO 8.2.12 Beta: Iris shader fixes

Unofficial builds of Ender IO 8.2.12-beta (Minecraft 1.21.1, NeoForge 21.1.216 or newer) that fix Travel Anchor rendering with Iris shader packs. See [Team-EnderIO/EnderIO#1062](https://github.com/Team-EnderIO/EnderIO/issues/1062).

Download: `release/enderio-8.2.12-beta-iris-fix.jar`. It's a client-side fix, but you can use the same jar on the server.

## Update 2: icons, backdrops and seeing through walls with shaders

Tag `v8.2.12-beta-iris-fix.2`. Built from commit `38e3cea`. The jar's version reads `8.2.12.5-claude-enderio-source-edit-build-lbnflu+38e3cea853`.

### Fixed
- **Travel Anchor icons now show while a shader pack is active.** Before, only the names showed. The icon under each name, the diamond backdrop behind it, and the block model of an anchor with no icon were all invisible. They also still hid whatever was behind them, so real anchor blocks and parts of other labels could vanish.
- **Travel Anchors show through walls with shaders, as they do without shaders.** This works both when you stand on an anchor and when you hold the Staff of Travelling.

### How it works
Ender IO normally draws these overlays onto a separate image to make them visible through walls. Under Iris, that image never reaches the screen. With an Iris shader pack active, this build draws the icons, backdrops and block models on top of everything instead, the same way the names are drawn. Each backdrop is drawn before its icon so the icon stays in front.

### Not changed
- Without a shader pack, or without Iris installed, anchors render exactly as in the official 8.2.12-beta.
- Iris detection elsewhere in the mod is unchanged. For example, see-through facades still hide while a shader pack is active.

### Known differences with shaders on
- An anchor without an icon shows its block model a little darker than without shaders, because the shader pack lights it differently.

### Tested with
A development client running Iris 1.8.12, Sodium 0.6.13, Complementary Unbound r5.9.3 and Euphoria Patches 1.10.5, under software rendering. Checked standing on an anchor, and behind a stone wall holding the staff, with shaders on and off. It has not been tested in a full modpack.

### Source changes
- `enderio/src/main/java/com/enderio/enderio/client/content/travel/TravelAnchorRenderer.java`: when a shader pack is active, send icons, backdrops and block models to see-through buffers, drawn backdrop first.
- `enderio/src/main/java/com/enderio/enderio/client/foundation/renderer/SeeThroughRenderTypes.java` (new): a render type for those overlays that skips the depth test.

## First release: Travel Anchor names with shaders

Tag `v8.2.12-beta-iris-fix`. Built from commit `97fe948`.

### Fixed
- **Travel Anchor names now show while a shader pack is active.** Before, standing on a Travel Anchor hid the names of the other anchors whenever Iris was running a shader pack.

### Change
In `TravelAnchorRenderer.java`, the Iris check that swapped the order the name text is drawn in is set to `false`, so the text is drawn the same way with or without Iris. This is the source version of the bytecode workaround posted in issue #1062 (comment 6130158244).
