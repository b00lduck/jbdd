package com.nigames.jbdd.service.conversion.i18n;

import com.nigames.jbdd.domain.entities.i18n.I18n;
import com.nigames.jbdd.statics.Languages;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractI18nConversionService<EntityType extends I18n> {

    public static ConcurrentHashMap<String, String> convertToDto(final I18n entity) {
        final ConcurrentHashMap<String, String> dto = new ConcurrentHashMap<>();

        for (final String lang : Languages.getLanguageTagList()) {
	        dto.put(lang, entity.getLang(lang));
        }

        return dto;
    }

    private static String deNull(final String str) {
        if (null == str) {
            return "";
        }
        return str;
    }

    public void updateEntityFromDto(final Map<String, String> dto, final EntityType entity) {
        for (final String lang : Languages.getLanguageTagList()) {
	        if (null == dto) {
		        entity.setLang(lang, "");
	        } else {
		        entity.setLang(lang, deNull(dto.get(lang)));
	        }

        }
    }

    public EntityType convertToEntity(final Map<String, String> dto) {
        final EntityType entity = getNewEntityInstance();
        updateEntityFromDto(dto, entity);
        return entity;
    }

    public abstract EntityType getNewEntityInstance();

}
