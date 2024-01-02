package ru.dest.industrialhorizons.common.item;

import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class DamageAbleItem extends SimpleItem{

    public DamageAbleItem(int durability) {
        super(1, (props)-> props.defaultDurability(durability).durability(durability));
    }
}
