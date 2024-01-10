package ru.dest.industrialhorizons.common.registry;

import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.dest.industrialhorizons.IndustrialHorizons;
import ru.dest.industrialhorizons.common.item.DamageAbleItem;
import ru.dest.industrialhorizons.common.item.Hammer;
import ru.dest.industrialhorizons.common.item.SimpleItem;


public class IHItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, IndustrialHorizons.MOD_ID);

    public static final RegistryObject<Item> SOLDERER = ITEMS.register("solderer", ()-> new SimpleItem(1));
    public static final RegistryObject<Item> SOLDER = ITEMS.register("solder", ()-> new DamageAbleItem(60));
    public static final RegistryObject<Item> BURNER = ITEMS.register("burner", ()-> new DamageAbleItem(20));
    public static final RegistryObject<Item> CAPASITOR = ITEMS.register("capasitor", ()-> new SimpleItem(64));


    public static final RegistryObject<Item> CPU_T1 = ITEMS.register("cpu_t1", ()-> new SimpleItem(64));
    public static final RegistryObject<Item> CPU_T2 = ITEMS.register("cpu_t2", ()-> new SimpleItem(64));
    public static final RegistryObject<Item> CPU_T3 = ITEMS.register("cpu_t3", ()-> new SimpleItem(64));
    public static final RegistryObject<Item> CPU_T4 = ITEMS.register("cpu_t4", ()-> new SimpleItem(64));
    public static final RegistryObject<Item> CPU_T5 = ITEMS.register("cpu_t5", ()-> new SimpleItem(64));

    public static final RegistryObject<Item> CM_T1 = ITEMS.register("cm_t1", ()-> new SimpleItem(64));
    public static final RegistryObject<Item> CM_T2 = ITEMS.register("cm_t2", ()-> new SimpleItem(64));
    public static final RegistryObject<Item> CM_T3 = ITEMS.register("cm_t3", ()-> new SimpleItem(64));
    public static final RegistryObject<Item> CM_T4 = ITEMS.register("cm_t4", ()-> new SimpleItem(64));
    public static final RegistryObject<Item> CM_T5 = ITEMS.register("cm_t5", ()-> new SimpleItem(64));

    public static final RegistryObject<Item> CP_T1 = ITEMS.register("cp_t1", ()-> new SimpleItem(64));
    public static final RegistryObject<Item> CP_T2 = ITEMS.register("cp_t2", ()-> new SimpleItem(64));
    public static final RegistryObject<Item> CP_T3 = ITEMS.register("cp_t3", ()-> new SimpleItem(64));
    public static final RegistryObject<Item> CP_T4 = ITEMS.register("cp_t4", ()-> new SimpleItem(64));
    public static final RegistryObject<Item> CP_T5 = ITEMS.register("cp_t5", ()-> new SimpleItem(64));

    public static final RegistryObject<Item> CIRCUIT_BOARD = ITEMS.register("circuit_board", ()-> new SimpleItem(64));
    public static final RegistryObject<Item> ETCHED_CIRCUIT_BOARD = ITEMS.register("etched_circuit_board", () -> new SimpleItem(64));

    public static final RegistryObject<Item> ASSEMBLED_CIRCUIT_BOARD_T1 = ITEMS.register("assembled_circuit_board_t1", () -> new SimpleItem(64));
    public static final RegistryObject<Item> ASSEMBLED_CIRCUIT_BOARD_T2 = ITEMS.register("assembled_circuit_board_t2", () -> new SimpleItem(64));
    public static final RegistryObject<Item> ASSEMBLED_CIRCUIT_BOARD_T3 = ITEMS.register("assembled_circuit_board_t3", () -> new SimpleItem(64));
    public static final RegistryObject<Item> ASSEMBLED_CIRCUIT_BOARD_T4 = ITEMS.register("assembled_circuit_board_t4", () -> new SimpleItem(64));
    public static final RegistryObject<Item> ASSEMBLED_CIRCUIT_BOARD_T5 = ITEMS.register("assembled_circuit_board_t5", () -> new SimpleItem(64));

    public static final RegistryObject<Item> INGOT_ALUMINIUM = ITEMS.register("ingot_aluminium", ()-> new SimpleItem(64));
    public static final RegistryObject<Item> INGOT_CHROME = ITEMS.register("ingot_chrome", ()-> new SimpleItem(64));
    public static final RegistryObject<Item> INGOT_COPPER = ITEMS.register("ingot_copper", ()-> new SimpleItem(64));
    public static final RegistryObject<Item> INGOT_ETHERIUM = ITEMS.register("ingot_etherium", ()-> new SimpleItem(64));
    public static final RegistryObject<Item> INGOT_LEAD = ITEMS.register("ingot_lead", ()-> new SimpleItem(64));
    public static final RegistryObject<Item> INGOT_MANGANESE = ITEMS.register("ingot_manganese", ()-> new SimpleItem(64));
    public static final RegistryObject<Item> INGOT_NICKEL = ITEMS.register("ingot_nickel", ()-> new SimpleItem(64));
    public static final RegistryObject<Item> INGOT_SELEN = ITEMS.register("ingot_selen", ()-> new SimpleItem(64));
    public static final RegistryObject<Item> INGOT_SILVER = ITEMS.register("ingot_silver", ()-> new SimpleItem(64));
    public static final RegistryObject<Item> INGOT_TIN = ITEMS.register("ingot_tin", ()-> new SimpleItem(64));
    public static final RegistryObject<Item> INGOT_TITAN = ITEMS.register("ingot_titan", ()-> new SimpleItem(64));
    public static final RegistryObject<Item> INGOT_TUNGSTEN = ITEMS.register("ingot_tungsten", ()-> new SimpleItem(64));
    public static final RegistryObject<Item> INGOT_VANADIUM = ITEMS.register("ingot_vanadium", ()-> new SimpleItem(64));
    public static final RegistryObject<Item> INGOT_ZINC = ITEMS.register("ingot_zinc", ()-> new SimpleItem(64));

    public static final RegistryObject<Item> HAMMER = ITEMS.register("hammer", () -> new Hammer(new Item.Properties()));

    public static final RegistryObject<Item> SULFUR = ITEMS.register("sulfur", ()-> new SimpleItem(64));

    public static void init(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
