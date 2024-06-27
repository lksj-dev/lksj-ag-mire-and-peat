package dev.lksj.ag.mireandpeat.forge;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

@Mod("mire_and_peat")
public class MireAndPeatMod {

    static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "mire_and_peat");
    static RegistryObject<Block> MIRE = BLOCKS.register("mire",
            () -> new MireBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(0.5F).sound(SoundType.MUD)));

    static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "mire_and_peat");
    static RegistryObject<Item> MIRE_ITEM = ITEMS.register("mire",
            () -> new BlockItem(MIRE.get(), new Item.Properties()));
    static RegistryObject<Item> PEAT = ITEMS.register("peat",
            () -> new Item(new Item.Properties()) {
                @Override
                public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                    return 1200;
                }
            });

    public MireAndPeatMod() {
        var bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);
    }
}
