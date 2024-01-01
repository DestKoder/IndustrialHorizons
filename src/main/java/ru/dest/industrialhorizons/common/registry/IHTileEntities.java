package ru.dest.industrialhorizons.common.registry;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.dest.industrialhorizons.IndustrialHorizons;
import ru.dest.industrialhorizons.common.tiles.WorkTableTile;

public class IHTileEntities {

    private static DeferredRegister<TileEntityType<?>> TILES =  DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, IndustrialHorizons.MOD_ID);

    public static RegistryObject<TileEntityType<WorkTableTile>> WORK_TABLE_TILE = TILES.register("work_table", () -> TileEntityType.Builder.of(()->new WorkTableTile(), IHBlocks.WORK_TABLE.get()).build(null));

    public static void init(IEventBus eventBus){
        TILES.register(eventBus);
    }
}
