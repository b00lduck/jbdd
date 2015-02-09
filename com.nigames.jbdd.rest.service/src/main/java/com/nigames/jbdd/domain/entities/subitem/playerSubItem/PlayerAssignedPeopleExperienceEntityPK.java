package com.nigames.jbdd.domain.entities.subitem.playerSubItem;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Composite primary key for {@link PlayerAssignedPeopleExperienceEntity}.
 */
@Embeddable
public class PlayerAssignedPeopleExperienceEntityPK implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The id of the {@link PlayerAssignedPeopleEntity}.
     */
    @Column(name = "player_people_id")
    private Long playerPeopleId;

    /**
     * The id of the {@link com.nigames.jbdd.domain.entities.item.JobEntity}.
     */
    @Column(name = "job_id")
    private Long jobId;

    /**
     * @return Get {@link PlayerAssignedPeopleExperienceEntityPK#playerPeopleId}
     */
    public Long getPlayerPeopleId() {
        return playerPeopleId;
    }

    /**
     * @return Get {@link PlayerAssignedPeopleExperienceEntityPK#jobId}
     */
    public Long getJobId() {
        return jobId;
    }

    @Override
    public boolean equals(final Object other) {

        if (this == other) {
            return true;
        }

        if (!(other instanceof PlayerAssignedPeopleExperienceEntityPK)) {
            return false;
        }

        final PlayerAssignedPeopleExperienceEntityPK that = (PlayerAssignedPeopleExperienceEntityPK) other;

        if (!jobId.equals(that.jobId)) {
            return false;
        }

        return playerPeopleId.equals(that.playerPeopleId);

    }

    @Override
    public int hashCode() {
        int result = playerPeopleId.hashCode();
        result = (31 * result) + jobId.hashCode();
        return result;
    }
}
