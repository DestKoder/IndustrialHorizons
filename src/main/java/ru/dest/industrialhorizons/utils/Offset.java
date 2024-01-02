package ru.dest.industrialhorizons.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Offset {

    private final int xOffset;
    private final int yOffset;

    public Offset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    @Contract(value = "_, _ -> new", pure = true)
    public static @NotNull Offset of(int x, int y){
        return new Offset(x,y);
    }

    public int getXOffset() {
        return xOffset;
    }

    public int getYOffset() {
        return yOffset;
    }
}
