package com.nigames.jbdd.domain.entities.facet

import com.nigames.jbdd.domain.entities.item.AbstractItemEntity
import spock.lang.Specification

import javax.persistence.*

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by Daniel on 22.02.2015.
 */
class FieldTestTools extends Specification {

    static void checkIdAndVersionField(final Class clazz) {
        assert clazz.getDeclaredField("id").getAnnotation(Id) != null
        assert clazz.getDeclaredField("version").getAnnotation(Version) != null
    }

    static void checkItemField(final Class clazz) {
        assert clazz.getDeclaredField("item").getAnnotation(MapsId) != null
        assert clazz.getDeclaredField("item").getAnnotation(OneToOne) != null
        assert clazz.getDeclaredField("item").getAnnotation(JoinColumn) != null
        assert clazz.getDeclaredField("item").getAnnotation(JoinColumn).name() == "id"
    }

    static void checkConstructorWithItem(final Class clazz) {
        def item = new AbstractItemEntity() {}
        def testSubject = clazz.getConstructor(AbstractItemEntity.class).newInstance(item)
        assert testSubject.item == item
    }

}
