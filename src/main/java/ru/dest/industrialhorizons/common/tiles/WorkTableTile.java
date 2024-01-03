package ru.dest.industrialhorizons.common.tiles;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import ru.dest.industrialhorizons.common.recipe.CircuitAssemblyRecipe;
import ru.dest.industrialhorizons.common.registry.IHItems;
import ru.dest.industrialhorizons.common.registry.IHRecipes;
import ru.dest.industrialhorizons.common.registry.IHTileEntities;
import ru.dest.industrialhorizons.utils.IHTags;

import java.util.List;
import java.util.Optional;

import static ru.dest.industrialhorizons.utils.Utils.*;

public class WorkTableTile extends BaseMachineTile implements ITickableTileEntity {

    private static final int soldererSlot = 0;
    private static final int solderSlot = 1;
    private static final int burnerSlot = 2;

    public WorkTableTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
        outputSlots.add(4);
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
        if(slot == soldererSlot)return list(IHItems.SOLDERER.get());
        if(slot == solderSlot)return list(IHItems.SOLDER.get());
        if(slot == burnerSlot)return list(IHItems.BURNER.get());
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

    private boolean hasSolderedComponents(){
        if(itemHandler.getStackInSlot(3).isEmpty()) return false;

        return !itemHandler.getStackInSlot(5).isEmpty() && !itemHandler.getStackInSlot(6).isEmpty() && !itemHandler.getStackInSlot(7).isEmpty() && !itemHandler.getStackInSlot(8).isEmpty();
    }

    private boolean canUnsolder(){
        ItemStack burner = itemHandler.getStackInSlot(2);
        return !itemHandler.getStackInSlot(3).isEmpty() && !itemHandler.getStackInSlot(0).isEmpty() && !burner.isEmpty() && getDurability(burner) > 1;
    }

    private boolean canSolder(){
        ItemStack burner = itemHandler.getStackInSlot(2);
        return !itemHandler.getStackInSlot(3).isEmpty() && !itemHandler.getStackInSlot(0).isEmpty() && !itemHandler.getStackInSlot(1).isEmpty() && !burner.isEmpty() && getDurability(burner) > 1;
    }

    private void craft(ItemStack result){
        itemHandler.setStackInSlot(3, ItemStack.EMPTY);

        itemHandler.setStackInSlot(5, ItemStack.EMPTY);
        itemHandler.setStackInSlot(6, ItemStack.EMPTY);
        itemHandler.setStackInSlot( 7, ItemStack.EMPTY);
        itemHandler.setStackInSlot(8, ItemStack.EMPTY);

        itemHandler.setStackInSlot(4, result);
    }

    @Override
    public void tick() {
        if(!componentsFilled()) return;
        validateRecipe();
    }

    private boolean componentsFilled(){
        return !itemHandler.getStackInSlot(3).isEmpty()
                && !itemHandler.getStackInSlot(5).isEmpty()
                && !itemHandler.getStackInSlot(6).isEmpty()
                && !itemHandler.getStackInSlot(7).isEmpty()
                && !itemHandler.getStackInSlot(8).isEmpty();
    }

    private void validateRecipe(){
        Inventory inv = new Inventory(5);

        inv.setItem(0, itemHandler.getStackInSlot(3));
        inv.setItem(1, itemHandler.getStackInSlot(5));
        inv.setItem(2, itemHandler.getStackInSlot(6));
        inv.setItem(3, itemHandler.getStackInSlot(7));
        inv.setItem(4, itemHandler.getStackInSlot(8));

        assert level != null;
        System.out.println(level.getRecipeManager().getAllRecipesFor(IHRecipes.CIRCUIT_ASSEMBLY));
        Optional<CircuitAssemblyRecipe> recipe = level.getRecipeManager().getRecipeFor(IHRecipes.CIRCUIT_ASSEMBLY, inv, level);

        recipe.ifPresent((r)-> {
            System.out.println("Recipe found! " + r.getIngredients().toString());
            this.craft(r.getResultItem());
            setChanged();
        });
    }


    @Override
    protected ItemActionResult onItemTake(int slot, int amount) {
        if(slot == 3) return hasSolderedComponents() ? ItemActionResult.DENY : ItemActionResult.SUCCESS;
        if(slot != 5 && slot != 6 && slot !=7 && slot !=8) return ItemActionResult.SUCCESS;
        if(!canUnsolder()) return ItemActionResult.DENY;

        ItemStack burner = itemHandler.getStackInSlot(burnerSlot);

        if(!tryDamage(burner, 1)) {
            itemHandler.extractItem(burnerSlot, 1, false);
        }

        return ItemActionResult.SUCCESS;
    }

    @Override
    protected ItemActionResult onItemPlace(int slot, ItemStack item) {
        if(slot != 5 && slot != 6 && slot !=7 && slot !=8) return ItemActionResult.SUCCESS;
        if(!canSolder()) return ItemActionResult.DENY;
        ItemStack solder = itemHandler.getStackInSlot(solderSlot);
        ItemStack burner = itemHandler.getStackInSlot(burnerSlot);


        if(!tryDamage(solder, 1)) {
            itemHandler.extractItem(solderSlot, 1, false);
        }

        if(!tryDamage(burner, 1)) {
            itemHandler.extractItem(burnerSlot, 1, false);
        }

        return ItemActionResult.SUCCESS;
//        itemHandler.getStackInSlot(soldererSlot).setDamageValue();
    }
}
