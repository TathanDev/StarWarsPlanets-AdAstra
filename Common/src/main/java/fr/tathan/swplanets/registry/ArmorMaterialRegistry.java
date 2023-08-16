package fr.tathan.swplanets.registry;

import fr.tathan.swplanets.items.armour.BeskarArmorMaterial;
import fr.tathan.swplanets.items.armour.TrooperArmorMaterial;
import net.minecraft.world.item.ArmorMaterial;

public class ArmorMaterialRegistry {

    public static final ArmorMaterial STORMTROOPER_MATERIAL = new TrooperArmorMaterial();
    public static final ArmorMaterial BESKAR_MATERIAL = new BeskarArmorMaterial();

    public static void init() {
    }

}
