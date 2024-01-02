package ru.dest.industrialhorizons.common.registry;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.Dimension;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraftforge.common.util.Lazy;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public enum IHOre {

    ;

    private final Lazy<Block> block;
    private final int minHeight;
    private final int maxHeight;
    private final int veinSize;
    private final int veinsPerChunk;
    private final RegistryKey<Dimension> dim;

    IHOre(Lazy<Block> block, int minHeight, int maxHeight, int veinSize, int veinsPerChunk) {
        this.block = block;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.veinSize = veinSize;
        this.veinsPerChunk = veinsPerChunk;
        this.dim = Dimension.OVERWORLD;
    }

    IHOre(Lazy<Block> block, int minHeight, int maxHeight, int veinSize, int veinsPerChunk, RegistryKey<Dimension> dim) {
        this.block = block;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.veinSize = veinSize;
        this.veinsPerChunk = veinsPerChunk;
        this.dim = dim;
    }

    @Contract(" -> new")
    public @NotNull OreFeatureConfig createConfig(){
        return new OreFeatureConfig(testRule(), block.get().defaultBlockState(), veinSize);
    }

    private RuleTest testRule(){
        if(dim.equals(Dimension.NETHER)){
            return OreFeatureConfig.FillerBlockType.NETHERRACK;
        }

        if(dim.equals(Dimension.END)){
            return new BlockMatchRuleTest(Blocks.END_STONE);
        }
        return OreFeatureConfig.FillerBlockType.NATURAL_STONE;
    }

    public int getVeinSize() {
        return veinSize;
    }

    public Lazy<Block> getBlock() {
        return block;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public int getVeinsPerChunk() {
        return veinsPerChunk;
    }
}
