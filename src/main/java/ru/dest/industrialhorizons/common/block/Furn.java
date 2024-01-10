package ru.dest.industrialhorizons.common.block;


import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.ToolType;
import ru.dest.industrialhorizons.client.container.WorkTableContainer;
import ru.dest.industrialhorizons.common.registry.IHTileEntities;
import ru.dest.industrialhorizons.common.tiles.WorkTableTile;

public class Furn extends BaseMachineBlock<WorkTableTile, WorkTableContainer> {

    public Furn() {
        super(Material.WOOD, ToolType.AXE, "furn", WorkTableContainer.class);
    }

    @Override
    protected TileEntityType<WorkTableTile> getTileEntityType() {
        return IHTileEntities.WORK_TABLE_TILE.get();
    }


}
