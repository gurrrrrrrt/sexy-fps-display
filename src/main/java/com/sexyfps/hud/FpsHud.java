package com.sexyfps.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.DeltaTracker;

public class FpsHud {
    public static void render(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker) {
        Minecraft instance = Minecraft.getInstance();

        new FpsRenderer(graphics, instance, instance.getFps()).render();
    }
}
