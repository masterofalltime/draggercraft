package net.mcreator.draggercraft;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.material.Material;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import java.util.List;
import java.util.Collections;

@Elementsdraggercraft.ModElement.Tag
public class MCreatorBurntOakStairs extends Elementsdraggercraft.ModElement {
	@ObjectHolder("draggercraft:burntoakstairs")
	public static final Block block = null;

	public MCreatorBurntOakStairs(Elementsdraggercraft instance) {
		super(instance, 23);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(MCreatorDraggerStuff.tab)).setRegistryName(block.getRegistryName()));
	}

	public static class CustomBlock extends StairsBlock {
		public CustomBlock() {
			super(new Block(Block.Properties.create(Material.ROCK)).getDefaultState(), Block.Properties.create(Material.WOOD).sound(SoundType.WOOD)
					.hardnessAndResistance(1f, 10f).lightValue(0).harvestLevel(1).harvestTool(ToolType.AXE));
			setRegistryName("burntoakstairs");
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public BlockRenderLayer getRenderLayer() {
			return BlockRenderLayer.CUTOUT;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			return Collections.singletonList(new ItemStack(this, 1));
		}
	}
}
