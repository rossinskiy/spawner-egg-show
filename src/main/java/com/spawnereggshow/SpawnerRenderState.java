package com.spawnereggshow;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public record SpawnerRenderState(ItemStack stack, boolean had, Identifier prev) {}
