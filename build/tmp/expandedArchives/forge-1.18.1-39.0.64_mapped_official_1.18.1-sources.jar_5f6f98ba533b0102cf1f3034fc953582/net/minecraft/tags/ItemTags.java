package net.minecraft.tags;

import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;

public final class ItemTags {
   protected static final StaticTagHelper<Item> HELPER = StaticTags.create(Registry.ITEM_REGISTRY, "tags/items");
   public static final Tag.Named<Item> WOOL = bind("wool");
   public static final Tag.Named<Item> PLANKS = bind("planks");
   public static final Tag.Named<Item> STONE_BRICKS = bind("stone_bricks");
   public static final Tag.Named<Item> WOODEN_BUTTONS = bind("wooden_buttons");
   public static final Tag.Named<Item> BUTTONS = bind("buttons");
   public static final Tag.Named<Item> CARPETS = bind("carpets");
   public static final Tag.Named<Item> WOODEN_DOORS = bind("wooden_doors");
   public static final Tag.Named<Item> WOODEN_STAIRS = bind("wooden_stairs");
   public static final Tag.Named<Item> WOODEN_SLABS = bind("wooden_slabs");
   public static final Tag.Named<Item> WOODEN_FENCES = bind("wooden_fences");
   public static final Tag.Named<Item> WOODEN_PRESSURE_PLATES = bind("wooden_pressure_plates");
   public static final Tag.Named<Item> WOODEN_TRAPDOORS = bind("wooden_trapdoors");
   public static final Tag.Named<Item> DOORS = bind("doors");
   public static final Tag.Named<Item> SAPLINGS = bind("saplings");
   public static final Tag.Named<Item> LOGS_THAT_BURN = bind("logs_that_burn");
   public static final Tag.Named<Item> LOGS = bind("logs");
   public static final Tag.Named<Item> DARK_OAK_LOGS = bind("dark_oak_logs");
   public static final Tag.Named<Item> OAK_LOGS = bind("oak_logs");
   public static final Tag.Named<Item> BIRCH_LOGS = bind("birch_logs");
   public static final Tag.Named<Item> ACACIA_LOGS = bind("acacia_logs");
   public static final Tag.Named<Item> JUNGLE_LOGS = bind("jungle_logs");
   public static final Tag.Named<Item> SPRUCE_LOGS = bind("spruce_logs");
   public static final Tag.Named<Item> CRIMSON_STEMS = bind("crimson_stems");
   public static final Tag.Named<Item> WARPED_STEMS = bind("warped_stems");
   public static final Tag.Named<Item> BANNERS = bind("banners");
   public static final Tag.Named<Item> SAND = bind("sand");
   public static final Tag.Named<Item> STAIRS = bind("stairs");
   public static final Tag.Named<Item> SLABS = bind("slabs");
   public static final Tag.Named<Item> WALLS = bind("walls");
   public static final Tag.Named<Item> ANVIL = bind("anvil");
   public static final Tag.Named<Item> RAILS = bind("rails");
   public static final Tag.Named<Item> LEAVES = bind("leaves");
   public static final Tag.Named<Item> TRAPDOORS = bind("trapdoors");
   public static final Tag.Named<Item> SMALL_FLOWERS = bind("small_flowers");
   public static final Tag.Named<Item> BEDS = bind("beds");
   public static final Tag.Named<Item> FENCES = bind("fences");
   public static final Tag.Named<Item> TALL_FLOWERS = bind("tall_flowers");
   public static final Tag.Named<Item> FLOWERS = bind("flowers");
   public static final Tag.Named<Item> PIGLIN_REPELLENTS = bind("piglin_repellents");
   public static final Tag.Named<Item> PIGLIN_LOVED = bind("piglin_loved");
   public static final Tag.Named<Item> IGNORED_BY_PIGLIN_BABIES = bind("ignored_by_piglin_babies");
   public static final Tag.Named<Item> PIGLIN_FOOD = bind("piglin_food");
   public static final Tag.Named<Item> FOX_FOOD = bind("fox_food");
   public static final Tag.Named<Item> GOLD_ORES = bind("gold_ores");
   public static final Tag.Named<Item> IRON_ORES = bind("iron_ores");
   public static final Tag.Named<Item> DIAMOND_ORES = bind("diamond_ores");
   public static final Tag.Named<Item> REDSTONE_ORES = bind("redstone_ores");
   public static final Tag.Named<Item> LAPIS_ORES = bind("lapis_ores");
   public static final Tag.Named<Item> COAL_ORES = bind("coal_ores");
   public static final Tag.Named<Item> EMERALD_ORES = bind("emerald_ores");
   public static final Tag.Named<Item> COPPER_ORES = bind("copper_ores");
   public static final Tag.Named<Item> NON_FLAMMABLE_WOOD = bind("non_flammable_wood");
   public static final Tag.Named<Item> SOUL_FIRE_BASE_BLOCKS = bind("soul_fire_base_blocks");
   public static final Tag.Named<Item> CANDLES = bind("candles");
   public static final Tag.Named<Item> DIRT = bind("dirt");
   public static final Tag.Named<Item> TERRACOTTA = bind("terracotta");
   public static final Tag.Named<Item> BOATS = bind("boats");
   public static final Tag.Named<Item> FISHES = bind("fishes");
   public static final Tag.Named<Item> SIGNS = bind("signs");
   public static final Tag.Named<Item> MUSIC_DISCS = bind("music_discs");
   public static final Tag.Named<Item> CREEPER_DROP_MUSIC_DISCS = bind("creeper_drop_music_discs");
   public static final Tag.Named<Item> COALS = bind("coals");
   public static final Tag.Named<Item> ARROWS = bind("arrows");
   public static final Tag.Named<Item> LECTERN_BOOKS = bind("lectern_books");
   public static final Tag.Named<Item> BEACON_PAYMENT_ITEMS = bind("beacon_payment_items");
   public static final Tag.Named<Item> STONE_TOOL_MATERIALS = bind("stone_tool_materials");
   public static final Tag.Named<Item> STONE_CRAFTING_MATERIALS = bind("stone_crafting_materials");
   public static final Tag.Named<Item> FREEZE_IMMUNE_WEARABLES = bind("freeze_immune_wearables");
   public static final Tag.Named<Item> AXOLOTL_TEMPT_ITEMS = bind("axolotl_tempt_items");
   public static final Tag.Named<Item> OCCLUDES_VIBRATION_SIGNALS = bind("occludes_vibration_signals");
   public static final Tag.Named<Item> CLUSTER_MAX_HARVESTABLES = bind("cluster_max_harvestables");

   private ItemTags() {
   }

   public static Tag.Named<Item> bind(String p_13195_) {
      return HELPER.bind(p_13195_);
   }

   public static net.minecraftforge.common.Tags.IOptionalNamedTag<Item> createOptional(net.minecraft.resources.ResourceLocation name) {
       return createOptional(name, null);
   }

   public static net.minecraftforge.common.Tags.IOptionalNamedTag<Item> createOptional(net.minecraft.resources.ResourceLocation name, @javax.annotation.Nullable java.util.Set<java.util.function.Supplier<Item>> defaults) {
      return HELPER.createOptional(name, defaults);
   }

   public static TagCollection<Item> getAllTags() {
      return HELPER.getAllTags();
   }
}
