package ru.dest.industrialhorizons.common.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.Properties;

public class Hammer extends Item {

        public Hammer(Properties he){
            super(he);
        }
        public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {

            player.sendMessage(new StringTextComponent("fu"), player.getUUID());
            return super.use(world, player, hand);

        }
    }
