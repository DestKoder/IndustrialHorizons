package ru.dest.industrialhorizons.common.registry;

import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.dest.industrialhorizons.IndustrialHorizons;
import ru.dest.industrialhorizons.common.item.SimpleItem;


public class IHItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, IndustrialHorizons.MOD_ID);

    public static final RegistryObject<Item> SOLDERER = ITEMS.register("solderer", ()-> new SimpleItem(1));
    public static final RegistryObject<Item> SOLDER = ITEMS.register("solder", ()-> new SimpleItem(64));
    public static final RegistryObject<Item> BURNER = ITEMS.register("burner", ()-> new SimpleItem(1));
    public static final RegistryObject<Item> CPU_T1 = ITEMS.register("cpu_t1", ()-> new SimpleItem(64));
    public static final RegistryObject<Item> CPU_T2 = ITEMS.register("cpu_t2", ()-> new SimpleItem(64));
    public static final RegistryObject<Item> CPU_T3 = ITEMS.register("cpu_t3", ()-> new SimpleItem(64));
    public static final RegistryObject<Item> CPU_T4 = ITEMS.register("cpu_t4", ()-> new SimpleItem(64));
    public static final RegistryObject<Item> CPU_T5 = ITEMS.register("cpu_t5", ()-> new SimpleItem(64));

    public static void init(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
