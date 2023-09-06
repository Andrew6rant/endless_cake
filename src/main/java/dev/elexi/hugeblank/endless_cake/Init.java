package dev.elexi.hugeblank.endless_cake;

import dev.elexi.hugeblank.endless_cake.block.EndlessCakeBlockEntity;
import dev.elexi.hugeblank.endless_cake.block.EndlessCandleCakeBlock;
import dev.elexi.hugeblank.endless_cake.block.EndlessCakeBlock;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class Init implements ModInitializer {
    public static final String ID = "endless_cake";
    public static final EndlessCakeBlock ENDLESS_CAKE = new EndlessCakeBlock(AbstractBlock.Settings.copy(Blocks.CAKE).luminance((state) -> (Boolean)state.get(Properties.LIT) ? 3 : 0));
    public static BlockEntityType<EndlessCakeBlockEntity> ENDLESS_CAKE_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(ID,"endless_cake_block_entity"),
            FabricBlockEntityTypeBuilder.create(EndlessCakeBlockEntity::new, ENDLESS_CAKE).build());

    private static void registerBlock(String name, Block block, ItemGroup group) {
        Identifier id = new Identifier(ID, name);
        Registry.register(Registries.BLOCK, id, block);
        if (group != null) {
            Item item = new BlockItem(block, new Item.Settings().rarity(Rarity.EPIC));
            Registry.register(Registries.ITEM, id, item);
        }
    }

    private static void registerCakeType(String name, EndlessCakeBlock cake) {
        registerBlock(name, cake, null);
        registerBlock("candle_" + name, new EndlessCandleCakeBlock(Blocks.CANDLE, AbstractBlock.Settings.copy(Blocks.CAKE).luminance((state) -> (Boolean)state.get(Properties.LIT) ? 3 : 0)), null);
        registerBlock("white_candle_" + name, new EndlessCandleCakeBlock(Blocks.WHITE_CANDLE, AbstractBlock.Settings.copy(Blocks.CANDLE_CAKE)), null);
        registerBlock("orange_candle_" + name, new EndlessCandleCakeBlock(Blocks.ORANGE_CANDLE, AbstractBlock.Settings.copy(Blocks.CANDLE_CAKE)), null);
        registerBlock("magenta_candle_" + name, new EndlessCandleCakeBlock(Blocks.MAGENTA_CANDLE, AbstractBlock.Settings.copy(Blocks.CANDLE_CAKE)), null);
        registerBlock("light_blue_candle_" + name, new EndlessCandleCakeBlock(Blocks.LIGHT_BLUE_CANDLE, AbstractBlock.Settings.copy(Blocks.CANDLE_CAKE)), null);
        registerBlock("yellow_candle_" + name, new EndlessCandleCakeBlock(Blocks.YELLOW_CANDLE, AbstractBlock.Settings.copy(Blocks.CANDLE_CAKE)), null);
        registerBlock("lime_candle_" + name, new EndlessCandleCakeBlock(Blocks.LIME_CANDLE, AbstractBlock.Settings.copy(Blocks.CANDLE_CAKE)), null);
        registerBlock("pink_candle_" + name, new EndlessCandleCakeBlock(Blocks.PINK_CANDLE, AbstractBlock.Settings.copy(Blocks.CANDLE_CAKE)), null);
        registerBlock("gray_candle_" + name, new EndlessCandleCakeBlock(Blocks.GRAY_CANDLE, AbstractBlock.Settings.copy(Blocks.CANDLE_CAKE)), null);
        registerBlock("light_gray_candle_" + name, new EndlessCandleCakeBlock(Blocks.LIGHT_GRAY_CANDLE, AbstractBlock.Settings.copy(Blocks.CANDLE_CAKE)), null);
        registerBlock("cyan_candle_" + name, new EndlessCandleCakeBlock(Blocks.CYAN_CANDLE, AbstractBlock.Settings.copy(Blocks.CANDLE_CAKE)), null);
        registerBlock("purple_candle_" + name, new EndlessCandleCakeBlock(Blocks.PURPLE_CANDLE, AbstractBlock.Settings.copy(Blocks.CANDLE_CAKE)), null);
        registerBlock("blue_candle_" + name, new EndlessCandleCakeBlock(Blocks.BLUE_CANDLE, AbstractBlock.Settings.copy(Blocks.CANDLE_CAKE)), null);
        registerBlock("brown_candle_" + name, new EndlessCandleCakeBlock(Blocks.BROWN_CANDLE, AbstractBlock.Settings.copy(Blocks.CANDLE_CAKE)), null);
        registerBlock("green_candle_" + name, new EndlessCandleCakeBlock(Blocks.GREEN_CANDLE, AbstractBlock.Settings.copy(Blocks.CANDLE_CAKE)), null);
        registerBlock("red_candle_" + name, new EndlessCandleCakeBlock(Blocks.RED_CANDLE, AbstractBlock.Settings.copy(Blocks.CANDLE_CAKE)), null);
        registerBlock("black_candle_" + name, new EndlessCandleCakeBlock(Blocks.BLACK_CANDLE, AbstractBlock.Settings.copy(Blocks.CANDLE_CAKE)), null);
    }

    @Override
    public void onInitialize() {
        // Just a single cake, for now...
        registerCakeType("endless_cake", ENDLESS_CAKE);
    }
}
