package ru.dest.industrialhorizons.common.tiles;

import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMachineTile extends TileEntity {

    protected final ItemStackHandler itemHandler = createHandler();
    protected final LazyOptional<IItemHandler> handler = LazyOptional.of(()-> itemHandler);

    protected final List<Integer> outputSlots;

    public BaseMachineTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
        outputSlots = new ArrayList<>();
    }

    @Override
    public void load(@NotNull BlockState state, @NotNull CompoundNBT compound) {
        itemHandler.deserializeNBT(compound.getCompound("inv"));
        super.load(state, compound);
    }

    @Override
    public @NotNull CompoundNBT save(@NotNull CompoundNBT nbt) {
        nbt.put("inv", itemHandler.serializeNBT());
        return super.save(nbt);
    }

    @Contract(" -> new")
    private @NotNull ItemStackHandler createHandler(){
        return new ItemStackHandler(getSlots()){

            @NotNull
            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                if(onItemTake(slot, amount) == ItemActionResult.SUCCESS){
                    return super.extractItem(slot, amount, simulate);
                }else return ItemStack.EMPTY;
            }

            @Override
            public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                List<Item> valid = getValidItemsPerSlot(slot);

                if(valid == null || valid.isEmpty()) return true;

                return valid.contains(stack.getItem());
            }

            @Override
            public int getSlotLimit(int slot) {
                return getSlotMaxStackSize(slot);
            }

            @NotNull
            @Override
            public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
                if(outputSlots.contains(slot)){
                    return stack;
                }
                if(onItemPlace(slot, stack) == ItemActionResult.SUCCESS){
                    return super.insertItem(slot, stack, simulate);
                }else return stack;
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

    protected ItemActionResult onItemPlace(int slot, ItemStack item){
        return ItemActionResult.SUCCESS;
    }

    protected ItemActionResult onItemTake(int slot, int amount) {
        return  ItemActionResult.SUCCESS;
    }

    protected enum ItemActionResult {
        SUCCESS, DENY
    }
}
