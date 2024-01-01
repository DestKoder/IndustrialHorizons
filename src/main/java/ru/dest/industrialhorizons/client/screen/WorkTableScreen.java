package ru.dest.industrialhorizons.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import ru.dest.industrialhorizons.IndustrialHorizons;
import ru.dest.industrialhorizons.client.container.WorkTableContainer;

public class WorkTableScreen extends ContainerScreen<WorkTableContainer> {

    private final ResourceLocation GUI = new ResourceLocation(IndustrialHorizons.MOD_ID, "textures/gui/work_table.png");

    public WorkTableScreen(WorkTableContainer container, PlayerInventory inventory, ITextComponent title) {
        super(container, inventory, title);

        this.imageWidth = 176;
        this.imageHeight = 197;
        this.titleLabelY = 4;

        this.inventoryLabelY = 102;
    }

    @Override
    public void render(MatrixStack matrix, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrix);
        super.render(matrix, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrix, mouseX, mouseY);
    }

    @Override
    protected void renderBg(MatrixStack matrix, float partialTicks, int x, int y) {
//        RenderSystem.color4f(1f, 1f, 1f,1f);
        this.minecraft.getTextureManager().bind(GUI);
        int i = this.getGuiLeft();
        int j = this.getGuiTop();

        this.blit(matrix, i, j, 0, 0, getXSize(), getYSize());
    }
}
