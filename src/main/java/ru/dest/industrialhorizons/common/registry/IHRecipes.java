package ru.dest.industrialhorizons.common.registry;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.dest.industrialhorizons.IndustrialHorizons;
import ru.dest.industrialhorizons.common.recipe.CircuitAssemblyRecipe;

public class IHRecipes {

    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZER =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, IndustrialHorizons.MOD_ID);

    public static final RegistryObject<CircuitAssemblyRecipe.Serializer> CIRCUIT_ASSEMBLY_SERIALIZER = RECIPE_SERIALIZER.register("circuit_assembly", CircuitAssemblyRecipe.Serializer::new);

    public static IRecipeType<CircuitAssemblyRecipe> CIRCUIT_ASSEMBLY = new CircuitAssemblyRecipe.RecipeType();

    public static void init(IEventBus eventBus) {
        RECIPE_SERIALIZER.register(eventBus);

        System.out.println(CircuitAssemblyRecipe.TYPE_ID);
        Registry.register(Registry.RECIPE_TYPE, CircuitAssemblyRecipe.TYPE_ID, CIRCUIT_ASSEMBLY);
    }

}
