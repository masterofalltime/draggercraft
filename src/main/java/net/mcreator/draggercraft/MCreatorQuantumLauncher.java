package net.mcreator.draggercraft;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

@Elementsdraggercraft.ModElement.Tag
public class MCreatorQuantumLauncher extends Elementsdraggercraft.ModElement {
	@ObjectHolder("draggercraft:quantumlauncher")
	public static final Item block = null;

	public MCreatorQuantumLauncher(Elementsdraggercraft instance) {
		super(instance, 35);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(MCreatorDraggerStuff.tab).maxStackSize(1));
			setRegistryName("quantumlauncher");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}
	}
}
