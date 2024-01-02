package ru.dest.industrialhorizons.common.tiles;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import ru.dest.industrialhorizons.common.registry.IHItems;
import ru.dest.industrialhorizons.common.registry.IHTileEntities;
import ru.dest.industrialhorizons.utils.IHTags;

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
        if(slot == 3)return list(IHItems.ETCHED_CIRCUIT_BOARD.get());
        if(slot == 5)return IHTags.Items.CPU.getValues();
        if(slot == 7)return IHTags.Items.CM.getValues();
        if(slot == 8)return list(IHItems.CAPASITOR.get());
        if(slot == 6)return IHTags.Items.CP.getValues();
        return list();
    }

    @Override
    protected int getSlotMaxStackSize(int slot) {
        return 1;
    }

    private boolean isToolsReady(){
        ItemStack burner = itemHandler.getStackInSlot(2);
        return !itemHandler.getStackInSlot(0).isEmpty() && !itemHandler.getStackInSlot(1).isEmpty() && !burner.isEmpty() && burner.getDamageValue() > 1;
    }


}
