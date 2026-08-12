package com.sexyfps;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.AutoConfigClient;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.Screens;

import net.minecraft.resources.Identifier;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sexyfps.hud.FpsHud;

public class SexyFpsDisplay implements ClientModInitializer {
	public static final String MOD_ID = "sexy-fps-display";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitializeClient() {
		LOGGER.info("onInitializeClient called");

		AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);

		HudElementRegistry.addLast(id("fps"), FpsHud::render);

		// chatgpt told me to do this. prob a better way idk never made a mod before lol
		ScreenEvents.AFTER_INIT.register(((client, screen, scaledWidth, scaledHeight) -> {
			if (screen instanceof PauseScreen) {
				Button fpsSettingsBtn = Button.builder(
								Component.literal("⚙"),
								button -> client.gui.setScreen(AutoConfigClient.getConfigScreen(ModConfig.class, screen).get()))
						.bounds(10, 10, 20, 20)
						.build();

				Screens.getWidgets(screen).add(fpsSettingsBtn);
			}
		}));
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
