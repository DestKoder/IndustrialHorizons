package ru.dest.industrialhorizons.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
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
import net.minecraft.tileentity.TileEntityType;
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
import ru.dest.industrialhorizons.client.container.BaseMachineContainer;
import ru.dest.industrialhorizons.common.tiles.BaseMachineTile;

import java.lang.reflect.InvocationTargetException;

public abstract class BaseMachineBlock<TILE extends BaseMachineTile, CONTAINER extends BaseMachineContainer> extends HorizontalBlock {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    private final String screenId;
    private final Class<CONTAINER> containerClass;

    public BaseMachineBlock(Material mat, ToolType type, String screenId, Class<CONTAINER> containerClass) {
        super(Properties.of(mat).harvestTool(type).harvestLevel(0).strength(2f).noOcclusion());
        this.screenId = screenId;
        this.containerClass = containerClass;
    }

    @Override
    public ActionResultType use(BlockState state, @NotNull World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        if (world.isClientSide) {
            return ActionResultType.SUCCESS;
        } else {
            return showGUI(world, pos, player);
        }
    }

    protected ActionResultType showGUI(@NotNull World world, BlockPos pos, PlayerEntity player){
        TileEntity entity = world.getBlockEntity(pos);

        if(!(entity instanceof BaseMachineTile)){
            LOGGER.error("Not a machine?!?! Tile is missing??? What?! How is it?!");
            return ActionResultType.FAIL;
        }

        INamedContainerProvider provider = new INamedContainerProvider() {
            @Override
            public @NotNull ITextComponent getDisplayName() {
                return new TranslationTextComponent("screen.industrialhorizons."+screenId);
            }

            @Override
            public @NotNull Container createMenu(int windowId, PlayerInventory inv, PlayerEntity player) {
                try {
                    return containerClass.getDeclaredConstructor(Integer.class, World.class, BlockPos.class, PlayerEntity.class).newInstance(windowId, world, pos, player);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        NetworkHooks.openGui(((ServerPlayerEntity) player), provider, entity.getBlockPos());
        return ActionResultType.CONSUME;
    }

    protected abstract TileEntityType<TILE> getTileEntityType();

    @Nullable
    @Override
    public final TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return getTileEntityType().create();
    }

    @Override
    public final boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    protected final void createBlockStateDefinition(StateContainer.@NotNull Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public final BlockState getStateForPlacement(@NotNull BlockItemUseContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

}
