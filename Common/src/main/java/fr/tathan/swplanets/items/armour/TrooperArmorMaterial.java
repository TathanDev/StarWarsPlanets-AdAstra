package fr.tathan.swplanets.items.armour;
import fr.tathan.swplanets.registry.ItemsRegistry;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

public class TrooperArmorMaterial implements ArmorMaterial {
    private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};
    private static final int[] PROTECTION_VALUES = new int[]{2, 5, 8, 3};

    public TrooperArmorMaterial() {
    }

    public int getDurabilityForSlot(EquipmentSlot slot) {
        return BASE_DURABILITY[slot.getIndex()] * 25;
    }

    public int getDefenseForSlot(EquipmentSlot slot) {
        return PROTECTION_VALUES[slot.getIndex()];
    }

    public int getEnchantmentValue() {
        return 14;
    }

    public SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_LEATHER;
    }

    public Ingredient getRepairIngredient() {
        return Ingredient.of(ItemsRegistry.PLASTIC_PLATE.get());
    }

    public String getName() {
        return "stormtrooper";
    }

    public float getToughness() {
        return 0.0F;
    }

    public float getKnockbackResistance() {
        return 0.0F;
    }
}
