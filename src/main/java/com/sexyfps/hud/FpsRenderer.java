package com.sexyfps.hud;

import com.sexyfps.ModConfig;

import me.shedaniel.autoconfig.AutoConfig;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public class FpsRenderer {
    private static final int HEIGHT = 14;
    private static final int TEXT_PADDING = 5;
    private static final float SCALE = 0.75f;
    private static final int BACKGROUND_COLOR = 0xCC101010;
    private static final int CORNER_COLOR = 0xFF101010;

    private final GuiGraphicsExtractor graphics;
    private final Minecraft instance;
    private final int fps;
    private final String fpsText;
    private final int width;

    public FpsRenderer(GuiGraphicsExtractor graphics, Minecraft instance, int fps) {
        this.graphics = graphics;
        this.instance = instance;
        this.fps = fps;
        this.fpsText = fps + " fps";
        this.width = instance.font.width(fpsText) + TEXT_PADDING;
    }

    public void render() {
        this.drawFrame();
        this.drawContent();
    }

    private int getFpsColor() {
        if (this.fps >= 60) {
            return 0xFF55FF88; // green
        }

        if (this.fps >= 30) {
            return 0xFFFFD166; // yellow
        }

        return 0xFFFF5555; // red
    }

    private void drawFrame() {
        int x = AutoConfig.getConfigHolder(ModConfig.class).getConfig().x;
        int y = AutoConfig.getConfigHolder(ModConfig.class).getConfig().y;

        // main bg
        this.graphics.fill(x + 2, y, x + this.width - 2, y + HEIGHT, BACKGROUND_COLOR);
        this.graphics.fill(x, y + 2, x + this.width, y + HEIGHT - 2, BACKGROUND_COLOR);

        // corner bg
        this.graphics.fill(x + 1, y + 1, x + 3, y + 3, CORNER_COLOR);
        this.graphics.fill(x + this.width - 3, y + 1, x + this.width - 1, y + 3, CORNER_COLOR);
        this.graphics.fill(x + 1, y + HEIGHT - 3, x + 3, y + HEIGHT - 1, CORNER_COLOR);
        this.graphics.fill(x + this.width - 3, y + HEIGHT - 3, x + this.width - 1, y + HEIGHT - 1, CORNER_COLOR);
    }

    private void drawContent() {
        int x = AutoConfig.getConfigHolder(ModConfig.class).getConfig().x;
        int y = AutoConfig.getConfigHolder(ModConfig.class).getConfig().y;
        int color = this.getFpsColor();

        this.graphics.pose().pushMatrix();
        this.graphics.pose().translate(x, y);
        this.graphics.pose().scale(SCALE, SCALE);

        // sexy bar
        float localExtent = HEIGHT / SCALE;
        int barWidth = 2;
        int barHeight = 10;
        int barX = 6;
        float barY = (localExtent - barHeight) / 2.0f;

        this.graphics.fill(barX, Math.round(barY), barX + barWidth, Math.round(barY + barHeight), color);

        // text
        int textX = barX + barWidth + 4;
        float textY = (localExtent - this.instance.font.lineHeight) / 2.0f;

        this.graphics.text(this.instance.font, this.fpsText, textX, Math.round(textY), color, true);

        this.graphics.pose().popMatrix();
    }
}