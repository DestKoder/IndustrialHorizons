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

    public static void init(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
