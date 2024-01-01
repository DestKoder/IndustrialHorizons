package ru.dest.industrialhorizons.common.registry;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.dest.industrialhorizons.IndustrialHorizons;
import ru.dest.industrialhorizons.client.container.WorkTableContainer;

public class IHContainers {

    public static DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, IndustrialHorizons.MOD_ID);

    public static final RegistryObject<ContainerType<WorkTableContainer>> WORK_TABLE_CONTAINER = CONTAINERS.register("work_table",
            () -> IForgeContainerType.create((windowId, inv, data)-> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.level;
                return new WorkTableContainer(windowId, world, pos, inv.player);
            }));

    public static void init(IEventBus eventBus){
        CONTAINERS.register(eventBus);
    }
}
