package com.nigames.jbdd.domain.entities.facet.identifyable;

import javax.persistence.*;

@MappedSuperclass
public abstract class IdentifyableEntityFacetImpl implements IdentifyableEntityFacet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Version
    private int version;

    @Override
    public Long getId() {
        return id;
    }

}
