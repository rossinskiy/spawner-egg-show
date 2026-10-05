package com.spawnereggshow;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Maps a lore substring (case-insensitive) to a custom item-model id.
 * First-registered wins, so register more specific patterns first
 * (e.g. "stray" before "skeleton").
 */
public final class SpawnerTextures {
	private record Entry(String needle, Identifier model) {}

	private static final List<Entry> ENTRIES = new ArrayList<>();

	private SpawnerTextures() {}

	public static void register(String loreSubstring, Identifier itemModel) {
		ENTRIES.add(new Entry(loreSubstring.toLowerCase(Locale.ROOT), itemModel));
	}

	public static int size() {
		return ENTRIES.size();
	}

	public static Identifier resolve(ItemStack stack) {
		if (stack == null || stack.isEmpty()) return null;
		if (stack.getItem() != Items.SPAWNER) return null;

		LoreComponent lore = stack.get(DataComponentTypes.LORE);
		if (lore == null) return null;

		StringBuilder sb = new StringBuilder();
		for (Text line : lore.lines()) sb.append(line.getString()).append('\n');
		if (sb.length() == 0) return null;
		String haystack = sb.toString().toLowerCase(Locale.ROOT);

		for (Entry e : ENTRIES) {
			if (haystack.contains(e.needle)) return e.model;
		}
		return null;
	}
}
