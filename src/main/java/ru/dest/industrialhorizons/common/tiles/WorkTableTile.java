package ru.dest.industrialhorizons.common.tiles;

import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import ru.dest.industrialhorizons.common.registry.IHItems;
import ru.dest.industrialhorizons.common.registry.IHTileEntities;

import java.util.List;

import static ru.dest.industrialhorizons.utils.Utils.list;

public class WorkTableTile extends BaseMachineTile {

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
        if(slot == 1)return list(IHItems.SOLDER.get());
        if(slot == 2)return list(IHItems.BURNER.get());
        if(slot == 5)return list(IHItems.CPU_T1.get(), IHItems.CPU_T2.get(), IHItems.CPU_T3.get(), IHItems.CPU_T4.get(), IHItems.CPU_T5.get());
        return list();
    }

    @Override
    protected int getSlotMaxStackSize(int slot) {
        return 1;
    }
}
