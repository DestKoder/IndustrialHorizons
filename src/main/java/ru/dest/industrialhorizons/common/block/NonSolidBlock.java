package ru.dest.industrialhorizons.common.block;

import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class NonSolidBlock extends SolidBlock{

    public NonSolidBlock(Material m, int harvestLevel, ToolType tool, float hardness) {
        super(m, harvestLevel, tool, hardness, Properties::noOcclusion);
    }

    public NonSolidBlock(Material m, int harvestLevel, ToolType tool, float hardness, @NotNull Function<Properties, Properties> func) {
        super(m, harvestLevel, tool, hardness, (props)-> func.apply(props.noOcclusion()));
    }
}
