package ru.dest.industrialhorizons.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dest.industrialhorizons.client.container.WorkTableContainer;
import ru.dest.industrialhorizons.common.registry.IHTileEntities;
import ru.dest.industrialhorizons.common.tiles.WorkTableTile;

public class WorkTableBlock extends HorizontalBlock {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public WorkTableBlock() {
        super(Properties.of(Material.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(2f).noOcclusion());
    }


    @Override
    public ActionResultType use(BlockState state, @NotNull World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        if (world.isClientSide) {
            return ActionResultType.SUCCESS;
        } else {
            TileEntity entity = world.getBlockEntity(pos);

            if(!(entity instanceof WorkTableTile)){
                LOGGER.error("WorkTable Tile is missing??? What?! How is it?!");
                return ActionResultType.FAIL;
            }

            INamedContainerProvider provider = new INamedContainerProvider() {
                @Override
                public @NotNull ITextComponent getDisplayName() {
                    return new TranslationTextComponent("screen.industrialhorizons.work_table");
                }

                @Override
                public @NotNull Container createMenu(int windowId, PlayerInventory inv, PlayerEntity player) {
                    return new WorkTableContainer(windowId, world, pos, player);
                }
            };

            NetworkHooks.openGui(((ServerPlayerEntity) player), provider, entity.getBlockPos());
            return ActionResultType.CONSUME;
        }
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return IHTileEntities.WORK_TABLE_TILE.get().create();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.@NotNull Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(@NotNull BlockItemUseContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
}
