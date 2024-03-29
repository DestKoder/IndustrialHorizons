package ru.dest.industrialhorizons.common.item;

import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;
import ru.dest.industrialhorizons.IndustrialHorizons;

import java.util.function.Function;

public class SimpleItem extends Item {

    public SimpleItem(int stackSize) {
        super(new Properties().stacksTo(stackSize).tab(IndustrialHorizons.GROUP));
    }

    public SimpleItem(int stackSize, @NotNull Function<Properties, Properties> func){
        super(func.apply(new Properties().stacksTo(stackSize).tab(IndustrialHorizons.GROUP)));
    }
}
