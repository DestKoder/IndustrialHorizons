package ru.dest.industrialhorizons.common.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dest.industrialhorizons.IndustrialHorizons;
import ru.dest.industrialhorizons.common.registry.IHRecipes;

import java.util.Arrays;

public class CircuitAssemblyRecipe implements IRecipe<IInventory> {

    public static final ResourceLocation TYPE_ID = new ResourceLocation(IndustrialHorizons.MOD_ID, "circuit_assembly");

    private final ResourceLocation id;

    private final ItemStack output;
    private final NonNullList<Ingredient> ingredients;

    public CircuitAssemblyRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> ingredients) {
        this.id = id;
        this.output = output;

        this.ingredients = ingredients;
    }

    @Override
    public boolean matches(@NotNull IInventory inv, World world) {
        if(inv.getContainerSize() < 5) {
            System.out.println("Not enough container size");
            return false;
        }

        for(int i = 0; i < 5; i ++){
            if(inv.getItem(i).isEmpty()) {
                System.out.println("Empty item in slot " + i);
                return false;
            }
        }

        return ingredients.get(0).test(inv.getItem(1))
                && ingredients.get(1).test(inv.getItem(2))
                && ingredients.get(2).test(inv.getItem(3))
                && ingredients.get(3).test(inv.getItem(4));
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull IInventory inv) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem() {
        return output.copy();
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return id;
    }

    @Override
    public @NotNull IRecipeType<?> getType() {
        return Registry.RECIPE_TYPE.getOptional(TYPE_ID).get();
    }

    @Override
    public @NotNull IRecipeSerializer<?> getSerializer() {
        return IHRecipes.CIRCUIT_ASSEMBLY_SERIALIZER.get();
    }

    public static class RecipeType implements IRecipeType<CircuitAssemblyRecipe> {
        @Override
        public String toString() {
            return TYPE_ID.toString();
        }
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<CircuitAssemblyRecipe> {

        @Override
        public @NotNull CircuitAssemblyRecipe fromJson(@NotNull ResourceLocation recipeId, @NotNull JsonObject json) {
            ItemStack output = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "output"));
            JsonArray ingredients = JSONUtils.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(4, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new CircuitAssemblyRecipe(recipeId, output, inputs);
        }

        @Nullable
        @Override
        public CircuitAssemblyRecipe fromNetwork(@NotNull ResourceLocation recipeId, @NotNull PacketBuffer buffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(4, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buffer));
            }

            ItemStack output = buffer.readItem();

            return new CircuitAssemblyRecipe(recipeId, output, inputs);
        }

        @Override
        public void toNetwork(@NotNull PacketBuffer buffer, @NotNull CircuitAssemblyRecipe recipe) {
            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buffer);
            }
            buffer.writeItemStack(recipe.getResultItem(), false);
        }
    }

}
