package ru.dest.industrialhorizons.common.tiles;

import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class BaseMachineTile extends TileEntity {

    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(()-> itemHandler);

    public BaseMachineTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public void load(@NotNull BlockState state, @NotNull CompoundNBT compound) {
        itemHandler.deserializeNBT(compound.getCompound("inv"));
        super.load(state, compound);
    }

    @Override
    public @NotNull CompoundNBT   save(@NotNull CompoundNBT nbt) {
        nbt.put("inv", itemHandler.serializeNBT());
        return super.save(nbt);
    }

    @Contract(" -> new")
    private @NotNull ItemStackHandler createHandler(){
        return new ItemStackHandler(getSlots()){
            @Override
            public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                List<Item> valid = getValidItemsPerSlot(slot);
                return valid != null && !valid.isEmpty() && valid.contains(stack.getItem());
            }

            @Override
            public int getSlotLimit(int slot) {
                return getSlotMaxStackSize(slot);
            }
        };
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return handler.cast();
        }

        return super.getCapability(cap, side);
    }

    protected abstract int getSlots();
    protected abstract List<Item> getValidItemsPerSlot(int slot);

    protected int getSlotMaxStackSize(int slot){
        return 64;
    }
}
