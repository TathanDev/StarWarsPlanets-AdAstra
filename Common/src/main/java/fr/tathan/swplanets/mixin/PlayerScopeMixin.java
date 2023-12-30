package fr.tathan.swplanets.mixin;

import fr.tathan.swplanets.Constants;
import fr.tathan.swplanets.common.items.Blaster;
import fr.tathan.swplanets.common.registry.ItemsRegistry;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Player.class)
public class PlayerScopeMixin {

    @Inject(at = @At(value = "RETURN"), method = "isScoping", cancellable = true)
    public void isScoping(CallbackInfoReturnable<Boolean> cir) {
        Player player = (Player) ((Object) this);
        boolean haveZoom = false;
        if(player.getItemInHand(InteractionHand.MAIN_HAND).is(ItemsRegistry.BLASTER.get())) {
            Blaster blaster = (Blaster) player.getItemInHand(InteractionHand.MAIN_HAND).getItem();
            if(blaster.zoom) {
                haveZoom = true;
            }
        }

        boolean isUsingBlaster = haveZoom && player.isCrouching() && player.getItemInHand(InteractionHand.MAIN_HAND).is(ItemsRegistry.BLASTER.get());
        boolean isUsingSpyglass = player.isUsingItem() && player.getUseItem().is(Items.SPYGLASS);

        boolean isScoping = isUsingBlaster || isUsingSpyglass;
        cir.setReturnValue(isScoping);


    }

}
