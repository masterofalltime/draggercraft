package net.mcreator.draggercraft;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

@Elementsdraggercraft.ModElement.Tag
public class MCreatorQuantumCoreOnBlockRightClicked extends Elementsdraggercraft.ModElement {
	public MCreatorQuantumCoreOnBlockRightClicked(Elementsdraggercraft instance) {
		super(instance, 18);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure MCreatorQuantumCoreOnBlockRightClicked!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem() == new ItemStack(
				Items.LAVA_BUCKET, (int) (1)).getItem())) {
			if (entity instanceof PlayerEntity)
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), new ItemStack(MCreatorZi_Portal_Block.block, (int) (14)));
			if (entity instanceof PlayerEntity)
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), new ItemStack(MCreatorZi.block, (int) (1)));
		}
	}
}
