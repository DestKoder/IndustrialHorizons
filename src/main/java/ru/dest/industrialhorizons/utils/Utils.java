package ru.dest.industrialhorizons.utils;

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
}
