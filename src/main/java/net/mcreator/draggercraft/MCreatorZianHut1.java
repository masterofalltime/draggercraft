package net.mcreator.draggercraft;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Rotation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Mirror;

import java.util.Random;

@Elementsdraggercraft.ModElement.Tag
public class MCreatorZianHut1 extends Elementsdraggercraft.ModElement {
	public MCreatorZianHut1(Elementsdraggercraft instance) {
		super(instance, 24);
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		Feature<NoFeatureConfig> feature = new Feature<NoFeatureConfig>(NoFeatureConfig::deserialize) {
			@Override
			public boolean place(IWorld world, ChunkGenerator generator, Random random, BlockPos pos, NoFeatureConfig config) {
				int i = pos.getX();
				int k = pos.getZ();
				DimensionType dimensionType = world.getDimension().getType();
				boolean dimensionCriteria = false;
				if (dimensionType == MCreatorZi.type)
					dimensionCriteria = true;
				if (!dimensionCriteria)
					return false;
				if ((random.nextInt(1000000) + 1) <= 50000) {
					i += random.nextInt(16) + 8;
					k += random.nextInt(16) + 8;
					int j = world.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, i, k);
					j -= 1;
					Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
							.getTemplateDefaulted(new ResourceLocation("draggercraft", "zian_hut1_fixed"));
					if (template == null)
						return false;
					Rotation rotation = Rotation.values()[random.nextInt(3)];
					Mirror mirror = Mirror.values()[random.nextInt(2)];
					template.addBlocksToWorldChunk(world, new BlockPos(i, j + -3, k), new PlacementSettings().setRotation(rotation).setRandom(random)
							.setMirror(mirror).setChunk((ChunkPos) null).setIgnoreEntities(false));
					return true;
				}
				return false;
			}
		};
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES,
					Biome.createDecoratedFeature(feature, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
		}
	}
}
