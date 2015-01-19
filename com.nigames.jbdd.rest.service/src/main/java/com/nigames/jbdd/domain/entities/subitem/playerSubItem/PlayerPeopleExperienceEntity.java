package com.nigames.jbdd.domain.entities.subitem.playerSubItem;

import com.nigames.jbdd.domain.entities.item.JobEntity;

import javax.persistence.*;

/**
 * Database entity for {@link PlayerAssignedPeopleEntity} experience. This represents the experience a
 * specific {@link PlayerAssignedPeopleEntity} has in a certain {@link JobEntity}.
 *
 * @author Daniel
 */
@Entity
@Table(name = "player_people_experience")
public class PlayerPeopleExperienceEntity {

    /**
     * The primary key.
     */
    @EmbeddedId
    private PlayerPeopleExperienceEntityPK id;

    /**
     * The JPA version field.
     */
    @Version
    private Integer version;

    /**
     * The {@link PlayerAssignedPeopleEntity} object who has experience.
     */
    @ManyToOne
    @MapsId("playerPeopleId")
    @JoinColumn(name = "player_people_id", referencedColumnName = "id")
    private PlayerAssignedPeopleEntity playerPeople;

    /**
     * The {@link JobEntity} in which the {@link PlayerAssignedPeopleEntity} has experience.
     */
    @ManyToOne
    @MapsId("jobId")
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    private JobEntity job;

    /**
     * The experience.
     */
    private Integer experience;

    /**
     * @return Get {@link PlayerPeopleExperienceEntity#playerPeople}
     */
    public PlayerAssignedPeopleEntity getPlayerPeople() {
        return playerPeople;
    }

    /**
     * @param playerPeople The {@link PlayerPeopleExperienceEntity#playerPeople} to set
     */
    public void setPlayerPeople(final PlayerAssignedPeopleEntity playerPeople) {
        this.playerPeople = playerPeople;
    }

    /**
     * @return Get {@link PlayerPeopleExperienceEntity#job}
     */
    public JobEntity getJob() {
        return job;
    }

    /**
     * @param job The {@link PlayerPeopleExperienceEntity#job} to set
     */
    public void setJob(final JobEntity job) {
        this.job = job;
    }

    /**
     * @return Get {@link PlayerPeopleExperienceEntity#id}
     */
    public PlayerPeopleExperienceEntityPK getId() {
        return id;
    }

    /**
     * @param id The {@link PlayerPeopleExperienceEntity#id} to set
     */
    public void setId(final PlayerPeopleExperienceEntityPK id) {
        this.id = id;
    }

    /**
     * @return Get {@link PlayerPeopleExperienceEntity#experience}
     */
    public Integer getExperience() {
        return experience;
    }

    /**
     * @param experience The {@link PlayerPeopleExperienceEntity#experience} to set
     */
    public void setExperience(final Integer experience) {
        this.experience = experience;
    }

    // TODO: equals, hashCode and toString

}
