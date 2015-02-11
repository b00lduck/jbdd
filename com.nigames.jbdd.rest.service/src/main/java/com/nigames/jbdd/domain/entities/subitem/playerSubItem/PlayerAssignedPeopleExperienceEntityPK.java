package com.nigames.jbdd.domain.entities.subitem.playerSubItem;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Composite primary key for {@link PlayerAssignedPeopleExperienceEntity}.
 */
@SuppressWarnings("DuplicateStringLiteralInspection")
@Embeddable
public class PlayerAssignedPeopleExperienceEntityPK implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The id of the {@link PlayerAssignedPeopleEntity}.
     */
    @Column(name = "player_assigned_people")
    private long playerAssignedPeopleId;

    /**
     * The id of the {@link com.nigames.jbdd.domain.entities.item.JobEntity}.
     */
    @Column(name = "job")
    private long jobId;

    /**
     * @return Get {@link PlayerAssignedPeopleExperienceEntityPK#playerAssignedPeopleId}
     */
    public long getPlayerAssignedPeopleId() {
	    return playerAssignedPeopleId;
    }

    /**
     * @return Get {@link PlayerAssignedPeopleExperienceEntityPK#jobId}
     */
    public long getJobId() {
        return jobId;
    }

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof PlayerAssignedPeopleExperienceEntityPK)) {
			return false;
		}

		final PlayerAssignedPeopleExperienceEntityPK that = (PlayerAssignedPeopleExperienceEntityPK) o;

		if (jobId != that.jobId) {
			return false;
		}
		return playerAssignedPeopleId == that.playerAssignedPeopleId;

	}

	@Override
	public int hashCode() {
		int result = (int) (playerAssignedPeopleId ^ (playerAssignedPeopleId >>> 32));
		result = (31 * result) + (int) (jobId ^ (jobId >>> 32));
		return result;
	}
}
