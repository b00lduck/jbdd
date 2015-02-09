package com.nigames.jbdd.domain.entities.facet;

import com.nigames.jbdd.domain.entities.facet.identifyable.IdentifyableEntityFacetImpl;

import javax.persistence.*;

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by andersg on 09.02.2015.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "FACET_PLAYER_ASSIGNED_BUYABLE")
public class PlayerAssignedBuyableEntityFacetImpl implements PlayerAssignedBuyableEntityFacet {

    @Id
    private long id;

    @Version
    private int version;

    @JoinColumn(name = "id")
    @MapsId
    @OneToOne
    private IdentifyableEntityFacetImpl identifyableEntityFacet;

    private long remainingBuildtime;

    private PlayerAssignedBuyableEntityFacetImpl() {}

    public PlayerAssignedBuyableEntityFacetImpl(final IdentifyableEntityFacetImpl identifyableEntityFacet) {
        this();
        this.identifyableEntityFacet = identifyableEntityFacet;
    }

    @Override
    public long getRemainingBuildtime() {
        return remainingBuildtime;
    }

    @Override
    public void setRemainingBuildtime(final long remainingBuildtime) {
        this.remainingBuildtime = remainingBuildtime;
    }

    @Override
    public boolean equals(final Object other) {

        if (this == other) {
            return true;
        }

        if (!(other instanceof PlayerAssignedBuyableEntityFacetImpl)) {
            return false;
        }

        final PlayerAssignedBuyableEntityFacetImpl that = (PlayerAssignedBuyableEntityFacetImpl) other;

        if (remainingBuildtime != that.remainingBuildtime) {
            return false;
        }
        return identifyableEntityFacet.equals(that.identifyableEntityFacet);

    }

    @Override
    public int hashCode() {
        int result = identifyableEntityFacet.hashCode();
        return (31 * result) + (int) (remainingBuildtime ^ (remainingBuildtime >>> 32));
    }
}
