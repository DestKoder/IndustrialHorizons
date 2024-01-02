package ru.dest.industrialhorizons.common.tiles;

import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import ru.dest.industrialhorizons.common.registry.IHItems;
import ru.dest.industrialhorizons.common.registry.IHTileEntities;

import java.util.List;

import static ru.dest.industrialhorizons.utils.Utils.list;

public class WorkTableTile extends FactoryTile{

    public WorkTableTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public WorkTableTile() {
        this(IHTileEntities.WORK_TABLE_TILE.get());
    }

    @Override
    protected int getSlots() {
        return 9;
    }

    @Override
    protected List<Item> getValidItemsPerSlot(int slot) {
        if(slot == 0)return list(IHItems.SOLDERER.get());
        return list();
    }

    @Override
    protected int getSlotMaxStackSize(int slot) {
        return 1;
    }
}
