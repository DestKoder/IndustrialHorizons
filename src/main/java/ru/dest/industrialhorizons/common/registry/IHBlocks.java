package ru.dest.industrialhorizons.common.registry;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.dest.industrialhorizons.IndustrialHorizons;
import ru.dest.industrialhorizons.common.block.Furn;
import ru.dest.industrialhorizons.common.block.WorkTableBlock;

import java.util.function.Supplier;

public class IHBlocks {

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, IndustrialHorizons.MOD_ID);

    public static final RegistryObject<Block> WORK_TABLE = register("work_table", WorkTableBlock::new);
    public static final RegistryObject<Block> FURN = register("furn", Furn::new);
    public static final RegistryObject<Block> FURN_SUPP = register("furn_supp", Furn::new);

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);

        regBlockItem(name, toReturn);

        return toReturn;
    }

    private static <T extends Block> void regBlockItem(String name, RegistryObject<T> block){
        IHItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(IndustrialHorizons.GROUP).stacksTo(64)));
    }

    public static void init(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
