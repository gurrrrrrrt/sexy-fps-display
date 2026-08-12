package com.sexyfps;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "sexy-fps-display")
public class ModConfig implements ConfigData {
    @ConfigEntry.Category("position")
    @ConfigEntry.BoundedDiscrete(min = 5, max = 1000)
    public int x = 5;

    @ConfigEntry.Category("position")
    @ConfigEntry.BoundedDiscrete(min = 5, max = 1000)
    public int y = 5;
}
