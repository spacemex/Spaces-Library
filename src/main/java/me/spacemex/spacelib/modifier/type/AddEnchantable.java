package me.spacemex.spacelib.modifier.type;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class AddEnchantable extends LootModifier {
    public static final Supplier<Codec<AddEnchantable>> CODEC = Suppliers.memoize(()->
            RecordCodecBuilder.create(inst-> codecStart(inst).and(ForgeRegistries.ITEMS.getCodec()
                    .fieldOf("item").forGetter(m->m.item))
                    .and(Codec.FLOAT.fieldOf("chance")
                            .forGetter(m->m.chance)).apply(inst, AddEnchantable::new)));
    private final Item item;
    private  Float chance;
    public AddEnchantable(LootItemCondition[] conditionsIn, Item item, @Nullable Float chance){
        super(conditionsIn);
        this.item=item;
        this.chance=chance;
    }
    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if (context.getRandom().nextFloat() <=.1F){
            ItemStack stack = new ItemStack(item, Math.min(1,3));
            LootItemFunction enchantRandomlyFunction =  EnchantRandomlyFunction.randomEnchantment().build();
            ItemStack enchantedStack =enchantRandomlyFunction.apply(stack,context);
            generatedLoot.add(enchantedStack);

        } if (chance == null){
            chance = 0.3F;

        }if (context.getRandom().nextFloat() >=chance) {
            ItemStack stack = new ItemStack(item, Math.min(1,3));
            generatedLoot.add(stack);
        }
        return  generatedLoot;
    }
    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}

