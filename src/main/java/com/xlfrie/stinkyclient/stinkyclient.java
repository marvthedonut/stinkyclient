package com.xlfrie.stinkyclient;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
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
        LOGGER.info("Initializing Stinky Client!");
        keyInit.init();
    }

    boolean flightKeyBoolDown = false;

    @SubscribeEvent
    public void flightKeyMap(ClientTickEvent event)
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
    }


    @SubscribeEvent
    public void autoSprint(PlayerTickEvent event)
    {
        event.player.setSprinting(true);
    }

    @SubscribeEvent
    public void  noFall(LivingFallEvent event)
    {
        if (event.getEntity() instanceof Player)
        {
            event.setCanceled(true);
        }
    }

}
