package com.nigames.jbdd.domain.entities.subitem.playerSubItem;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Composite primary key for {@link com.nigames.jbdd.domain.entities.subitem.playerSubItem.PlayerPeopleExperienceEntity}.
 */
@Embeddable
public class PlayerPeopleExperienceEntityPK implements Serializable {

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
     * @return Get {@link PlayerPeopleExperienceEntityPK#playerPeopleId}
     */
    public Long getPlayerPeopleId() {
        return playerPeopleId;
    }

    /**
     * @return Get {@link PlayerPeopleExperienceEntityPK#jobId}
     */
    public Long getJobId() {
        return jobId;
    }

    // TODO: equal, hashCode and toString

}
