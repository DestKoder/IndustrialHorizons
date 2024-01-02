package ru.dest.industrialhorizons.client.screen;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import ru.dest.industrialhorizons.client.container.WorkTableContainer;
import ru.dest.industrialhorizons.utils.Offset;

public class WorkTableScreen extends BaseScreen<WorkTableContainer> {

    public WorkTableScreen(WorkTableContainer container, PlayerInventory inventory, ITextComponent title) {
        super(container, inventory, title, "work_table", 197, 176, Offset.of(-1, 102));
        this.titleLabelY = 4;
    }
}
