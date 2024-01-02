package ru.dest.industrialhorizons.client.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
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
import ru.dest.industrialhorizons.utils.Offset;

import static ru.dest.industrialhorizons.utils.Utils.list;

public class WorkTableContainer extends BaseMachineContainer {

    public WorkTableContainer(Integer windowId, @NotNull World world, BlockPos pos, @NotNull PlayerEntity player) {
        super(IHContainers.WORK_TABLE_CONTAINER.get(), windowId, world, pos, player, Offset.of(8, 115), list(
                Offset.of(7, 23),
                Offset.of(7, 45),
                Offset.of(7, 68),
                Offset.of(145, 33),
                Offset.of(145, 56),
                Offset.of(46, 27),
                Offset.of(90, 31),
                Offset.of(54, 54),
                Offset.of(89, 65)
        ));
    }
}
