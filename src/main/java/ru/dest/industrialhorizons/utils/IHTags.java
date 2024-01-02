package ru.dest.industrialhorizons.utils;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import ru.dest.industrialhorizons.IndustrialHorizons;

public class IHTags {

    public static class Blocks {

        private static Tags.IOptionalNamedTag<Block> createTag(String name) {
            return BlockTags.createOptional(new ResourceLocation(IndustrialHorizons.MOD_ID, name));
        }

        private static Tags.IOptionalNamedTag<Block> createForgeTag(String name) {
            return BlockTags.createOptional(new ResourceLocation("forge", name));
        }
    }

    public static class Items {

//        public static final Tags.IOptionalNamedTag<Item> AMETHYST = createForgeTag("gems/amethyst");
        public static final Tags.IOptionalNamedTag<Item> CPU = createTag("cpu");
        public static final Tags.IOptionalNamedTag<Item> CM = createTag("cm");
        public static final Tags.IOptionalNamedTag<Item> CP = createTag("cp");


        private static Tags.IOptionalNamedTag<Item> createTag(String name) {
            return ItemTags.createOptional(new ResourceLocation(IndustrialHorizons.MOD_ID, name));
        }

        private static Tags.IOptionalNamedTag<Item> createForgeTag(String name) {
            return ItemTags.createOptional(new ResourceLocation("forge", name));
        }
    }

}
