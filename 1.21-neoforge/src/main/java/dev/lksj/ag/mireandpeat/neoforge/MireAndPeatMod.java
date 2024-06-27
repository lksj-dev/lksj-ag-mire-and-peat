package dev.lksj.ag.mireandpeat.neoforge;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod("mire_and_peat")
public class MireAndPeatMod {

    static DeferredRegister<Block> BLOCKS = DeferredRegister.Blocks.createBlocks("mire_and_peat");
    static DeferredHolder<Block, MireBlock> MIRE = BLOCKS.register("mire",
            () -> new MireBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(0.5F).sound(SoundType.MUD)));

    static DeferredRegister<Item> ITEMS = DeferredRegister.Items.createItems("mire_and_peat");
    static DeferredHolder<Item, BlockItem> MIRE_ITEM = ITEMS.register("mire",
            () -> new BlockItem(MIRE.get(), new Item.Properties()));
    static DeferredHolder<Item, Item> PEAT = ITEMS.register("peat",
            () -> new Item(new Item.Properties()));

    public MireAndPeatMod(IEventBus bus) {
        BLOCKS.register(bus);
        ITEMS.register(bus);
    }
}
