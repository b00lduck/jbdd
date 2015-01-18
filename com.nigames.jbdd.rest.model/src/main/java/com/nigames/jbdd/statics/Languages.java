package com.nigames.jbdd.statics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.stream.Collectors;

@SuppressWarnings("UtilityClass")
public final class Languages {

	private static final Collection<Locale> LOCALE_LIST = new ArrayList<>();
	private static final Collection<String> LANGUAGE_TAG_LIST = new ArrayList<>();

	static {
		LOCALE_LIST.add(new Locale("de", "de"));
		LOCALE_LIST.add(new Locale("en", "gb"));

		LANGUAGE_TAG_LIST.addAll(LOCALE_LIST.stream().map(Locale::toLanguageTag).collect(Collectors.toList()));
	}

	private Languages() {
	}

	public static Iterable<Locale> getLocaleList() {
		return LOCALE_LIST;
	}

	public static Iterable<String> getLanguageTagList() {
		return LANGUAGE_TAG_LIST;
	}

}
