package com.nigames.jbdd.domain.entities.item

import com.nigames.jbdd.domain.entities.i18n.I18nLongEntity
import com.nigames.jbdd.domain.entities.i18n.I18nShortEntity
import com.nigames.jbdd.domain.entities.subitem.ProductionEntity
import spock.lang.Specification
import spock.lang.Unroll

import java.lang.reflect.Modifier

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by Daniel on 22.02.2015.
 */
class JobEntitySpec extends Specification {

    @Unroll
    def "#getter/#setter works and is public"() {

        given:
        def testSubject = JobEntity.newInstance()

        when:
        testSubject.invokeMethod(setter, testObject)

        then:
        testSubject.invokeMethod(getter, null) == testObject
        testSubject.getClass().getModifiers() == Modifier.PUBLIC + Modifier.FINAL
        testSubject.getClass().getMethod(getter).getModifiers() == Modifier.PUBLIC + finalFlag
        testSubject.getClass().getMethod(setter, testType).getModifiers() == Modifier.PUBLIC + finalFlag

        where:
        getter              | setter              | testObject                        | testType              | finalFlag
        "getProductionList" | "setProductionList" | new ArrayList<ProductionEntity>() | List.class            | 0
        "getName"           | "setName"           | new I18nShortEntity()             | I18nShortEntity.class | Modifier.FINAL
        "getDescription"    | "setDescription"    | new I18nLongEntity()              | I18nLongEntity.class  | Modifier.FINAL
        "isEnabled"         | "setEnabled"        | false                             | boolean               | Modifier.FINAL
        "isEnabled"         | "setEnabled"        | true                              | boolean               | Modifier.FINAL

    }

}
