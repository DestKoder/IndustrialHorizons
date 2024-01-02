package ru.dest.industrialhorizons.common.generation;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import org.jetbrains.annotations.NotNull;
import ru.dest.industrialhorizons.common.registry.IHOre;

public class OreGeneration {

    public static void generateOres(final BiomeLoadingEvent event){
        for(IHOre ore : IHOre.values()){
            OreFeatureConfig oreFeatureConfig = ore.createConfig();

            ConfiguredPlacement<TopSolidRangeConfig> configuredPlacement = Placement.RANGE.configured(
                    new TopSolidRangeConfig(ore.getMinHeight(),ore.getMaxHeight()- ore.getMinHeight(), ore.getMaxHeight()));

            ConfiguredFeature<?, ?> feature = registerOreFeature(ore, oreFeatureConfig, configuredPlacement);

            event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, feature);
        }
    }

    private static @NotNull ConfiguredFeature<?, ?> registerOreFeature(@NotNull IHOre ore, OreFeatureConfig oreFeatureConfig,
                                                                       ConfiguredPlacement<?> configuredPlacement) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, ore.getBlock().get().getRegistryName(),
                Feature.ORE.configured(oreFeatureConfig).decorated(configuredPlacement).squared().count(ore.getVeinsPerChunk()));
    }
}
