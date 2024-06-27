package dev.lksj.ag.mireandpeat.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class MireAndPeatMod implements ModInitializer {

    static Block MIRE = new MireBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(0.5F).sound(SoundType.MUD));

    static Item MIRE_ITEM = new BlockItem(MIRE, new Item.Properties());
    static Item PEAT = new Item(new Item.Properties());


    @Override
    public void onInitialize() {
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath("mire_and_peat", "mire"), MIRE);

        Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath("mire_and_peat", "mire"), MIRE_ITEM);
        Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath("mire_and_peat", "peat"), PEAT);

        FuelRegistry.INSTANCE.add(PEAT, 1200);
    }
}
