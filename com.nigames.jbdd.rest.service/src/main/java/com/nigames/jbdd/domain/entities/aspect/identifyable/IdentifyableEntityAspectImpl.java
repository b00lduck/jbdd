package com.nigames.jbdd.domain.entities.aspect.identifyable;

import javax.persistence.*;

@MappedSuperclass
public abstract class IdentifyableEntityAspectImpl implements IdentifyableEntityAspect {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Version
    private int version;

    @Override
    public long getId() {
        return id;
    }

}
