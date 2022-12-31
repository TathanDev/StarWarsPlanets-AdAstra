package fr.tathan.swplanets.items;
/**
import fr.tathan.swplanets.Constants;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;

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
        }


        return new InteractionResultHolder<>(InteractionResult.SUCCESS, playerIn.getItemInHand(handIn));

    }

    public static Component tl(String text) {
        return Component.translatable("message." + Constants.MODID + ".error." + text);
    }



}
*/