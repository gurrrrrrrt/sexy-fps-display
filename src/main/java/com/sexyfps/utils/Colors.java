package com.sexyfps.utils;

public final class Colors {
    // convert rgba to minecrafts hex color shit
    public static int rgba(int r, int g, int b, int a) {
        return (a << 24) | (r << 16) | (g << 8) | b;
    }

    public static int rgb(int r, int g, int b) {
        return rgba(r, g, b, 255);
    }
}
