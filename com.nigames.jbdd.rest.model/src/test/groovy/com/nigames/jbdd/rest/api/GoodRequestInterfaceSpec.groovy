package com.nigames.jbdd.rest.api

import com.nigames.jbdd.rest.dto.Good
import com.nigames.jbdd.rest.dto.Job
import spock.lang.Specification
import spock.lang.Unroll

import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.PUT
import javax.ws.rs.Path

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by Daniel on 12.02.2015.
 */
class GoodRequestInterfaceSpec extends Specification {

    @Unroll
    def "mehtod #name must have annotation #annotation"() {

        when:
        def method;
        if (param2 != null) {
            method = clazz.getDeclaredMethod(name, param1, param2)
        } else if (param1 != null) {
            method = clazz.getDeclaredMethod(name, param1)
        } else {
            method = clazz.getDeclaredMethod(name)
        }

        then:
        method.getAnnotation(annotation) != null

        where:
        clazz                | name      | param1 | param2 | annotation
        GoodRequestInterface | "getById" | long   | null   | GET
        GoodRequestInterface | "getById" | long   | null   | Path
        GoodRequestInterface | "update"  | long   | Good   | PUT
        GoodRequestInterface | "create"  | Good   | null   | POST
        JobRequestInterface  | "getById" | long   | null   | GET
        JobRequestInterface  | "getById" | long   | null   | Path
        JobRequestInterface  | "update"  | long   | Job    | PUT
        JobRequestInterface  | "create"  | Job    | null   | POST
    }

}
