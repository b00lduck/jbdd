package com.nigames.jbdd.service.rest.helper;

import com.nigames.jbdd.rest.dto.aspects.HasNameAndDesc;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Daniel on 25.12.2014.
 */
public class I18nGenerator {

    public static Map<String, String> getNameMap(int index) {
        Map<String, String> name = new HashMap<>(2);
        name.put("de-DE", "Deutscher Name " + index);
        name.put("en-GB", "English name " + index);
        return name;
    }

    public static Map<String, String> getDescMap(int index) {
        Map<String, String> desc = new HashMap<>(2);
        desc.put("de-DE", "Deutsche Beschreibung " + index);
        desc.put("en-GB", "English description " + index);
        return desc;
    }

    public static Map<String, String> getModifiedNameMap(HasNameAndDesc dto) {
        Map<String, String> name = new HashMap<>(2);
        name.put("de-DE", dto.getName().get("de-DE") + " bearbeitet");
        name.put("en-GB", dto.getName().get("en-GB") + " modified");
        return name;
    }

    public static Map<String, String> getModifiedDescMap(HasNameAndDesc dto) {
        Map<String, String> desc = new HashMap<>(2);
        desc.put("de-DE", dto.getDescription().get("de-DE") + " bearbeitet");
        desc.put("en-GB", dto.getDescription().get("en-GB") + " modified");
        return desc;
    }

}
