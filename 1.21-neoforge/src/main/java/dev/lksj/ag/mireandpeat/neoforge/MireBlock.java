package dev.lksj.ag.mireandpeat.neoforge;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class MireBlock extends Block {

    private static final MapCodec<MireBlock> CODEC = simpleCodec(MireBlock::new);

    private static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    public MireBlock(Properties prop) {
        super(prop);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(AGE);
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < 3;
    }

    @Override
    protected void randomTick(BlockState state , ServerLevel level, BlockPos pos, RandomSource random) {
        // Prevent accidental chunk loading
        if (!level.isAreaLoaded(pos, 2)) {
            return;
        }
        if (isHydrated(level, pos) && random.nextInt(4) == 0) {
            int age = state.getValue(AGE);
            if (age < 3) {
                level.setBlock(pos, state.setValue(AGE, age + 1), Block.UPDATE_ALL);
            }
        }
    }

    private static boolean isHydrated(LevelReader level, BlockPos center) {
        BlockPos.MutableBlockPos check = new BlockPos.MutableBlockPos();
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                check.set(center.getX() + x, center.getY(), center.getZ() + z);
                if (level.getFluidState(check).is(FluidTags.WATER)) {
                    return true;
                }
            }
        }
        return false;
    }
}
