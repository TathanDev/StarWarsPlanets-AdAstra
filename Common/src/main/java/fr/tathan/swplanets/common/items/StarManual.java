package fr.tathan.swplanets.common.items;


import fr.tathan.swplanets.Constants;
import fr.tathan.swplanets.common.platform.Services;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import vazkii.patchouli.api.PatchouliAPI;


public class StarManual extends Item {


    public static final Component PATCHOULI_ERROR = tl("patchouli");

    public StarManual(Properties properties) {
        super(properties);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {
        if (!Services.PLATFORM.isModLoaded("patchouli")) {
            if (level.isClientSide) {
                playerIn.displayClientMessage(PATCHOULI_ERROR, false);

            }
        } else {
            if (level.isClientSide) {
                PatchouliAPI.get().openBookGUI(new ResourceLocation(Constants.MODID, "star_manual"));
            }

        }


        return new InteractionResultHolder<>(InteractionResult.SUCCESS, playerIn.getItemInHand(handIn));

    }

    public static Component tl(String text) {
        return Component.translatable("message." + Constants.MODID + ".error." + text);
    }



}
