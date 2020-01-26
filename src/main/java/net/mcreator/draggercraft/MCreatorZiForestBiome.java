package net.mcreator.draggercraft;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.SphereReplaceConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Rotation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Mirror;
import net.minecraft.block.Blocks;
import net.minecraft.block.Block;

import java.util.Set;
import java.util.Random;

import com.google.common.collect.Lists;

@Elementsdraggercraft.ModElement.Tag
public class MCreatorZiForestBiome extends Elementsdraggercraft.ModElement {
	@ObjectHolder("draggercraft:ziforestbiome")
	public static final CustomBiome biome = null;

	public MCreatorZiForestBiome(Elementsdraggercraft instance) {
		super(instance, 41);
	}

	@Override
	public void initElements() {
		elements.biomes.add(() -> new CustomBiome());
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
	}

	static class CustomBiome extends Biome {
		public CustomBiome() {
			super(new Biome.Builder()
					.downfall(0f)
					.depth(0.1f)
					.scale(0.1f)
					.temperature(0.5f)
					.precipitation(Biome.RainType.NONE)
					.category(Biome.Category.NONE)
					.waterColor(4159204)
					.waterFogColor(329011)
					.surfaceBuilder(
							SurfaceBuilder.DEFAULT,
							new SurfaceBuilderConfig(MCreatorZiEarth.block.getDefaultState(), MCreatorZiEarth.block.getDefaultState(),
									MCreatorZiEarth.block.getDefaultState())));
			setRegistryName("ziforestbiome");
			DefaultBiomeFeatures.addCarvers(this);
			DefaultBiomeFeatures.addStructures(this);
			DefaultBiomeFeatures.addMonsterRooms(this);
			DefaultBiomeFeatures.addOres(this);
			addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.DEAD_BUSH,
					IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(2)));
			addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(new CustomTreeFeature(),
					IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(4, 0.1F, 1)));
			addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(
					Feature.DISK,
					new SphereReplaceConfig(Blocks.GRAVEL.getDefaultState(), 6, 2, Lists.newArrayList(Blocks.DIRT.getDefaultState(),
							Blocks.GRASS_BLOCK.getDefaultState())), Placement.COUNT_TOP_SOLID, new FrequencyConfig(1)));
		}
	}

	static class CustomTreeFeature extends AbstractTreeFeature<NoFeatureConfig> {
		CustomTreeFeature() {
			super(NoFeatureConfig::deserialize, false);
		}

		@Override
		public boolean place(Set<BlockPos> changedBlocks, IWorldGenerationReader worldgen, Random random, BlockPos pos, MutableBoundingBox bbox) {
			if (!(worldgen instanceof IWorld))
				return false;
			IWorld world = (IWorld) worldgen;
			Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
					.getTemplateDefaulted(new ResourceLocation("draggercraft", "burnt_tree1"));
			if (template == null)
				return false;
			Block ground = world.getBlockState(pos).getBlock();
			Block ground2 = world.getBlockState(pos.add(0, -1, 0)).getBlock();
			if (!(ground == MCreatorZiEarth.block.getDefaultState().getBlock() || ground == MCreatorZiEarth.block.getDefaultState().getBlock()
					|| ground2 == MCreatorZiEarth.block.getDefaultState().getBlock() || ground2 == MCreatorZiEarth.block.getDefaultState().getBlock()))
				return false;
			Rotation rotation = Rotation.values()[random.nextInt(3)];
			Mirror mirror = Mirror.values()[random.nextInt(2)];
			BlockPos placepos = pos.add(template.getSize().getX() / -2, 0, template.getSize().getZ() / -2);
			template.addBlocksToWorldChunk(world, placepos, new PlacementSettings().setRotation(rotation).setRandom(random).setMirror(mirror)
					.setChunk((ChunkPos) null).setIgnoreEntities(false));
			bbox.expandTo(new MutableBoundingBox(placepos.getX(), placepos.getY(), placepos.getZ(), placepos.add(template.getSize()).getX(), placepos
					.add(template.getSize()).getY(), placepos.add(template.getSize()).getZ()));
			return true;
		}
	}
}
