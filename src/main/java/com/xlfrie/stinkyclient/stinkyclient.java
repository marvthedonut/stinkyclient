package com.xlfrie.stinkyclient;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.main.GameConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.client.gui.screens.VideoSettingsScreen;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("stinkyclient")
public class stinkyclient
{
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    boolean flightOn = false;
    boolean toggleSprintOn = false;



    public stinkyclient()
    {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        keyInit.init();

    }

    boolean flightKeyBoolDown = false;
    boolean togglesprintkeyBoolDown = false;



    @SubscribeEvent
    public void keyMap(ClientTickEvent event)
    {
        if (flightKeyBoolDown != keyInit._KeyMapping.isDown())
        {
            flightKeyBoolDown = keyInit._KeyMapping.isDown();
            if (!keyInit._KeyMapping.isDown())
            {
                LOGGER.info("Toggled flight");
                flightOn = !flightOn;
                Minecraft.getInstance().player.getAbilities().mayfly = flightOn;
            }
        }
        if (togglesprintkeyBoolDown != keyInit.KeyMappingT.isDown())
        {
            togglesprintkeyBoolDown = keyInit.KeyMappingT.isDown();
            if (!keyInit.KeyMappingT.isDown())
            {
                toggleSprintOn = !toggleSprintOn;
            }
        }
    }

    @SubscribeEvent
    public void autoSprint(PlayerTickEvent event)
    {
        if (toggleSprintOn)
        {
            event.player.setSprinting(true);
        }
    }

}
