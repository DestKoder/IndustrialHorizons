package ru.dest.industrialhorizons.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import org.jetbrains.annotations.NotNull;
import ru.dest.industrialhorizons.IndustrialHorizons;
import ru.dest.industrialhorizons.client.container.BaseMachineContainer;
import ru.dest.industrialhorizons.utils.Offset;

public class BaseScreen<CONTAINER extends BaseMachineContainer> extends ContainerScreen<CONTAINER> {

    private final ResourceLocation texture;

    public BaseScreen(CONTAINER container, PlayerInventory inv, ITextComponent title, String texture, int imageHeight, int imageWidth, @NotNull Offset inventoryLabelOffset) {
        super(container, inv, title);

        this.imageHeight = imageHeight;
        this.imageWidth = imageWidth;

        this.texture = new ResourceLocation(IndustrialHorizons.MOD_ID, "textures/gui/" + texture + ".png");

        if(inventoryLabelOffset.getXOffset() != -1) this.inventoryLabelX = inventoryLabelOffset.getXOffset();
        if(inventoryLabelOffset.getYOffset() != -1) this.inventoryLabelY = inventoryLabelOffset.getYOffset();
    }

    @Override
    public void render(MatrixStack matrix, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrix);
        super.render(matrix, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrix, mouseX, mouseY);
    }

    @Override
    protected void renderBg(MatrixStack matrix, float partialTicks, int mouseX, int mouseY) {

        this.minecraft.getTextureManager().bind(texture);
        int i = this.getGuiLeft();
        int j = this.getGuiTop();

        this.blit(matrix, i, j, 0, 0, getXSize(), getYSize());
    }
}
