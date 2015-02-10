package com.nigames.jbdd.domain.entities.subitem.playerSubItem;

import com.nigames.jbdd.domain.entities.PlayerEntity;
import com.nigames.jbdd.domain.entities.facet.AssignableToPlayerEntityFacet;
import com.nigames.jbdd.domain.entities.item.GoodEntity;

import javax.persistence.*;

/**
 * Database entity for Players. This represents a game character.
 *
 * @author Daniel
 */
@Entity
@Table(name = "player_assigned_good")
public class PlayerAssignedGoodEntity implements AssignableToPlayerEntityFacet {

    @EmbeddedId
    private PlayerAssignedGoodEntityPK id = new PlayerAssignedGoodEntityPK();

	@Version
	private int version;

	@MapsId("goodId")
	@JoinColumn(name = "good", referencedColumnName = "id", updatable = false, insertable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private GoodEntity good;

	@MapsId("playerId")
	@JoinColumn(name = "player", referencedColumnName = "id", updatable = false, insertable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private PlayerEntity player;

    /**
     * The amount.
     */
    private Long amount;

    public PlayerAssignedGoodEntityPK getId() {
        return id;
    }

    public void setId(final PlayerAssignedGoodEntityPK id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(final Long amount) {
        this.amount = amount;
    }

	public GoodEntity getGood() {
		return good;
	}

	public void setGood(final GoodEntity good) {
		this.good = good;
		id.setGoodId(good.getId());
	}

	@Override
	public PlayerEntity getPlayer() {
		return player;
	}

	@Override
	public void setPlayer(final PlayerEntity player) {
		this.player = player;
		id.setPlayerId(player.getId());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof PlayerAssignedGoodEntity)) return false;

		PlayerAssignedGoodEntity that = (PlayerAssignedGoodEntity) o;

		if (!amount.equals(that.amount)) return false;
		if (!good.equals(that.good)) return false;
		if (!player.equals(that.player)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = good.hashCode();
		result = 31 * result + player.hashCode();
		result = 31 * result + amount.hashCode();
		return result;
	}

}
