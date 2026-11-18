package com.enderio.enderio.client.foundation.renderer;

import com.enderio.enderio.EnderIO;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlas;

/**
 * Render types that draw on top of everything, used for travel overlays while a shader pack is active.
 * The outline buffer trick that normally makes them visible through walls does not work with shaders.
 */
public final class SeeThroughRenderTypes extends RenderType {

    // Quads using the block atlas (blocks and items), with no depth test or depth write.
    // Quads are not sorted, so they draw in the order they were added.
    public static final RenderType QUADS = create(EnderIO.MOD_ID + "_see_through_quads", DefaultVertexFormat.NEW_ENTITY,
            VertexFormat.Mode.QUADS, 1536, false, false,
            CompositeState.builder()
                    .setShaderState(RENDERTYPE_ENTITY_TRANSLUCENT_CULL_SHADER)
                    .setTextureState(new TextureStateShard(TextureAtlas.LOCATION_BLOCKS, false, false))
                    .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                    .setLightmapState(LIGHTMAP)
                    .setOverlayState(OVERLAY)
                    .setDepthTestState(NO_DEPTH_TEST)
                    .setWriteMaskState(COLOR_WRITE)
                    .createCompositeState(false));

    private SeeThroughRenderTypes(String name, VertexFormat format, VertexFormat.Mode mode, int bufferSize,
            boolean affectsCrumbling, boolean sortOnUpload, Runnable setupState, Runnable clearState) {
        super(name, format, mode, bufferSize, affectsCrumbling, sortOnUpload, setupState, clearState);
    }
}
