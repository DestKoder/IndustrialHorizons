package ru.dest.industrialhorizons.client.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dest.industrialhorizons.common.registry.IHBlocks;
import ru.dest.industrialhorizons.common.registry.IHContainers;

public class WorkTableContainer extends Container {

    private final TileEntity tileEntity;
    private final PlayerEntity playerEntity;
    private final IItemHandler playerInventory;

    public WorkTableContainer(int windowId, @NotNull World world, BlockPos pos, @NotNull PlayerEntity player) {
        super(IHContainers.WORK_TABLE_CONTAINER.get(), windowId);
        this.tileEntity = world.getBlockEntity(pos);
        this.playerEntity = player;
        this.playerInventory = new InvWrapper(player.inventory);

        this.layoutPlayerInventorySlots(8, 115);
        if(tileEntity != null){
            tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
                addSlot(new SlotItemHandler(h, 0, 7, 23));
                addSlot(new SlotItemHandler(h, 1, 7, 45));
                addSlot(new SlotItemHandler(h, 2, 7, 68));
                addSlot(new SlotItemHandler(h, 3, 145, 33));
                addSlot(new SlotItemHandler(h, 4, 145, 56));

                //components
                addSlot(new SlotItemHandler(h, 5, 46, 27));
                addSlot(new SlotItemHandler(h, 6, 90, 31));
                addSlot(new SlotItemHandler(h, 7, 54, 54));
                addSlot(new SlotItemHandler(h, 8, 89, 65));
            });
        }
    }


    @Override
    public boolean stillValid(@NotNull PlayerEntity player) {
        return stillValid(IWorldPosCallable.create(tileEntity.getLevel(), tileEntity.getBlockPos()), player, IHBlocks.WORK_TABLE.get());
    }

    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }

        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }

        return index;
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }
}
