package com.nigames.jbdd.service.conversion;

import com.nigames.jbdd.domain.entities.i18n.AbstractI18nEntity;
import com.nigames.jbdd.domain.entities.i18n.I18n;
import com.nigames.jbdd.service.conversion.i18n.AbstractI18nConversionService;
import org.junit.Test;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Abstract test for the I18n(Long)ConversionService classes.
 *
 * @author Daniel
 */
public abstract class AbstractI18nConversionServiceTest {

    public static Map<String, String> getFilledI18nDto(final String prefix) {
        Map<String, String> dto = new ConcurrentHashMap<String, String>();
        fillI18nDto(dto, prefix);
        return dto;
    }

    public static void fillI18nEntity(final I18n entity, final String prefix) {
        entity.setDe(prefix + "De");
        entity.setEn(prefix + "En");
    }

    public static void checkI18nEntity(final I18n entity, final String prefix) {
        Assert.isTrue(entity.getDe().equals(prefix + "De"), "German I18n Entity comparison");
        Assert.isTrue(entity.getEn().equals(prefix + "En"), "English I18n Entity comparison");
    }

    private static void fillI18nDto(final Map<String, String> dto, final String prefix) {
        dto.put("de-DE", prefix + "De");
        dto.put("en-GB", prefix + "En");
    }

    public static void checkI18nDto(final Map<String, String> dto, final String prefix) {
        Assert.notNull(dto, "dto must not be null");
        Assert.notNull(prefix, "prefix must not be null");
        Assert.isTrue(dto.get("de-DE").equals(prefix + "De"), "German I18n DTO comparison");
        Assert.isTrue(dto.get("en-GB").equals(prefix + "En"), "English I18n DTO comparison");
    }

    @Test
    public void entityToDtoConversionTest() {

        // Construct DTO
        AbstractI18nEntity entity = getConversionService().getNewEntityInstance();
        fillI18nEntity(entity, "Test");

        // Convert
        ConcurrentHashMap<String, String> dto = AbstractI18nConversionService.convertToDto(entity);
        Assert.notNull(dto);

        // Test
        checkI18nDto(dto, "Test");
    }

    @Test
    public void dtoToEntityConversionTest() {

        // Construct Entity
        ConcurrentHashMap<String, String> dto = new ConcurrentHashMap<String, String>();
        fillI18nDto(dto, "Test");

        // Convert
        AbstractI18nEntity entity = getConversionService().convertToEntity(dto);
        Assert.notNull(entity);

        // Test
        checkI18nEntity(entity, "Test");
        Assert.isTrue(entity.getId() == 0);
    }

    @Test
    public void dtoToEntityConversionNullTest() {

        // Construct DTO
        ConcurrentHashMap<String, String> dto = new ConcurrentHashMap<String, String>();

        // Convert
        AbstractI18nEntity entity = getConversionService().convertToEntity(dto);
        Assert.notNull(entity);

        // Test
        Assert.isTrue(entity.getDe().equals(""), "German null string comparison");
        Assert.isTrue(entity.getEn().equals(""), "English null string comparison");
        Assert.isTrue(entity.getId() == 0);
    }

    protected abstract AbstractI18nConversionService<? extends AbstractI18nEntity> getConversionService();

}
