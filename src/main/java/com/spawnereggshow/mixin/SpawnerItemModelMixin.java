package com.spawnereggshow.mixin;

import com.spawnereggshow.SpawnerRenderState;
import com.spawnereggshow.SpawnerTextures;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemModelManager.class)
public class SpawnerItemModelMixin {

	private static final ThreadLocal<SpawnerRenderState> ACTIVE = new ThreadLocal<>();

	@Inject(method = "update(Lnet/minecraft/client/render/item/ItemRenderState;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemDisplayContext;Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;I)V",
			at = @At("HEAD"))
	private void spawnereggshow$apply(ItemRenderState state, ItemStack stack, ItemDisplayContext ctx,
									 @Nullable World world, @Nullable LivingEntity entity, int seed,
									 CallbackInfo ci) {
		Identifier custom = SpawnerTextures.resolve(stack);
		if (custom == null) return;
		boolean had = stack.contains(DataComponentTypes.ITEM_MODEL);
		Identifier prev = stack.get(DataComponentTypes.ITEM_MODEL);
		ACTIVE.set(new SpawnerRenderState(stack, had, prev));
		stack.set(DataComponentTypes.ITEM_MODEL, custom);
	}

	@Inject(method = "update(Lnet/minecraft/client/render/item/ItemRenderState;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemDisplayContext;Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;I)V",
			at = @At("RETURN"))
	private void spawnereggshow$restore(ItemRenderState state, ItemStack stack, ItemDisplayContext ctx,
									   @Nullable World world, @Nullable LivingEntity entity, int seed,
									   CallbackInfo ci) {
		SpawnerRenderState s = ACTIVE.get();
		if (s == null || s.stack() != stack) return;
		ACTIVE.remove();
		if (s.had()) stack.set(DataComponentTypes.ITEM_MODEL, s.prev());
		else stack.remove(DataComponentTypes.ITEM_MODEL);
	}
}
