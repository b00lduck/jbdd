package com.nigames.jbdd.domain.entities.facet;

import com.nigames.jbdd.domain.entities.PlayerEntity;
import com.nigames.jbdd.domain.entities.facet.identifyable.IdentifyableEntityFacetImpl;
import com.nigames.jbdd.domain.entities.subitem.playerSubItem.PlayerAssignedPeopleEntity;
import com.nigames.jbdd.domain.entities.subitem.playerSubItem.PlayerAssignedSubItem;

import javax.persistence.*;

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by andersg on 09.02.2015.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "FACET_PLAYER_ASSIGNED")
public class PlayerAssignedEntityFacetImpl implements PlayerAssignedEntityFacet {

    @Id
    private long id;

    @Version
    private int version;

    @JoinColumn(name = "id")
    @MapsId
    @OneToOne
    private IdentifyableEntityFacetImpl identifyableEntityFacet;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player", referencedColumnName = "id", updatable = false, insertable = false)
    private PlayerEntity player;

    private PlayerAssignedEntityFacetImpl() {}

    public PlayerAssignedEntityFacetImpl(final PlayerAssignedSubItem<?> identifyableEntityFacet) {
        this();
        this.identifyableEntityFacet = identifyableEntityFacet;
    }

    @Override
    public PlayerEntity getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(final PlayerEntity player) {
        this.player = player;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PlayerAssignedEntityFacetImpl)) {
            return false;
        }

        final PlayerAssignedEntityFacetImpl that = (PlayerAssignedEntityFacetImpl) o;

        return identifyableEntityFacet.equals(that.identifyableEntityFacet);

    }

    @Override
    public int hashCode() {
        return identifyableEntityFacet.hashCode();
    }
}
