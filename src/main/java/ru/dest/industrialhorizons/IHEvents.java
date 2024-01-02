package ru.dest.industrialhorizons;


import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.dest.industrialhorizons.common.generation.OreGeneration;

@Mod.EventBusSubscriber(modid = IndustrialHorizons.MOD_ID)
public class IHEvents {
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        OreGeneration.generateOres(event);
    }
}
