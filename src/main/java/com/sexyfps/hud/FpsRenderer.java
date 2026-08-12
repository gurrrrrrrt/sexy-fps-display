package com.sexyfps.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public class FpsRenderer {
    private static final int HEIGHT = 14;
    private static final int TEXT_PADDING = 5;
    private static final float SCALE = 0.75f;
    private static final int BACKGROUND_COLOR = 0xCC101010;
    private static final int CORNER_COLOR = 0xFF101010;
    private static final int COLOR_GREEN = 0xFF55FF88;
    private static final int COLOR_YELLOW = 0xFFFFD166;
    private static final int COLOR_RED = 0xFFFF5555;

    private final GuiGraphicsExtractor graphics;
    private final Minecraft instance;
    private final int fps;
    private final String fpsText;
    private final int width;
    private final int x;
    private final int y;

    public FpsRenderer(GuiGraphicsExtractor graphics, Minecraft instance, int fps, int x, int y) {
        this.graphics = graphics;
        this.instance = instance;
        this.fps = fps;
        this.fpsText = fps + " fps";
        this.width = instance.font.width(fpsText) + TEXT_PADDING;
        this.x = x;
        this.y = y;
    }

    public void render() {
        this.drawFrame();
        this.drawContent();
    }

    private int getFpsColor() {
        return this.fps >= 60 ? COLOR_GREEN : this.fps >= 30 ? COLOR_YELLOW : COLOR_RED;
    }

    private void drawFrame() {
        // main bg
        this.graphics.fill(this.x + 2, this.y, this.x + this.width - 2, this.y + HEIGHT, BACKGROUND_COLOR);
        this.graphics.fill(this.x, this.y + 2, this.x + this.width, this.y + HEIGHT - 2, BACKGROUND_COLOR);

        // corner bg
        this.graphics.fill(this.x + 1, this.y + 1, this.x + 3, this.y + 3, CORNER_COLOR);
        this.graphics.fill(this.x + this.width - 3, this.y + 1, this.x + this.width - 1, this.y + 3, CORNER_COLOR);
        this.graphics.fill(this.x + 1, this.y + HEIGHT - 3, this.x + 3, this.y + HEIGHT - 1, CORNER_COLOR);
        this.graphics.fill(this.x + this.width - 3, this.y + HEIGHT - 3, this.x + this.width - 1, this.y + HEIGHT - 1, CORNER_COLOR);
    }

    private void drawContent() {
        int color = this.getFpsColor();

        this.graphics.pose().pushMatrix();
        this.graphics.pose().translate(this.x, this.y);
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