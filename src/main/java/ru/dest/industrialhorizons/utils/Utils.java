package ru.dest.industrialhorizons.utils;

import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class Utils {
    
    @Contract(pure = true)
    @SafeVarargs
    public static <T> @NotNull List<T> list(T... t){
        return Arrays.asList(t);
    }

    public static int getDurability(@NotNull ItemStack item){
        return item.getMaxDamage() - item.getDamageValue();
    }

    public static boolean tryDamage(ItemStack item, int damage){
        if(getDurability(item) - damage > 0){
            item.setDamageValue(item.getDamageValue() + damage);
            return true;
        }else return false;
    }
}
