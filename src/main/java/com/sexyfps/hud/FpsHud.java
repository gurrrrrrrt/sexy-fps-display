package com.sexyfps.hud;

import com.sexyfps.ModConfig;

import me.shedaniel.autoconfig.AutoConfig;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.DeltaTracker;

public class FpsHud {
    public static void render(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker) {
        Minecraft instance = Minecraft.getInstance();
        ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

        new FpsRenderer(graphics, instance, instance.getFps(), config.x, config.y).render();
    }
}
