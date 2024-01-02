package ru.dest.industrialhorizons.common.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class SolidBlock extends Block {

    public SolidBlock(Material m, int harvestLevel, ToolType tool, float hardness) {
        super(AbstractBlock.Properties.of(m).harvestLevel(harvestLevel).harvestTool(tool).requiresCorrectToolForDrops().strength(hardness));
    }

    public SolidBlock(Material m, int harvestLevel, ToolType tool, float hardness, @NotNull Function<Properties, Properties> func){
        super(func.apply(AbstractBlock.Properties.of(m).harvestLevel(harvestLevel).harvestTool(tool).requiresCorrectToolForDrops().strength(hardness)));
    }

}

