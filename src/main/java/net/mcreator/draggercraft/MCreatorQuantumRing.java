package net.mcreator.draggercraft;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

@Elementsdraggercraft.ModElement.Tag
public class MCreatorQuantumRing extends Elementsdraggercraft.ModElement {
	@ObjectHolder("draggercraft:quantumring")
	public static final Item block = null;

	public MCreatorQuantumRing(Elementsdraggercraft instance) {
		super(instance, 6);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(MCreatorDraggerStuff.tab).maxStackSize(64));
			setRegistryName("quantumring");
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
