package com.xlfrie.stinkyclient;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;

public final class keyInit
{
    private keyInit()
    {

    }
    public static KeyMapping _KeyMapping;
    public static KeyMapping KeyMappingT;

    public static void init()
    {
        _KeyMapping = registerKey("flight", KeyMapping.CATEGORY_MOVEMENT, InputConstants.KEY_B);
        KeyMappingT = registerKey("togglesprint", KeyMapping.CATEGORY_MOVEMENT, InputConstants.KEY_V);
    }

    private static KeyMapping registerKey(String name, String category, int keycode)
    {
        final var key = new KeyMapping("key.stinkyclient." + name, keycode, category);
        ClientRegistry.registerKeyBinding(key);
        return key;
    }
}
