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
@Table(name = "player_assigned_people_experience")
public class PlayerAssignedPeopleExperienceEntity {

    /**
     * The primary key.
     */
    @EmbeddedId
    private PlayerAssignedPeopleExperienceEntityPK id;

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
    @JoinColumn(name = "player_people", referencedColumnName = "id")
    private PlayerAssignedPeopleEntity playerPeople;

    /**
     * The {@link JobEntity} in which the {@link PlayerAssignedPeopleEntity} has experience.
     */
    @ManyToOne
    @MapsId("jobId")
    @JoinColumn(name = "job", referencedColumnName = "id")
    private JobEntity job;

    /**
     * The experience.
     */
    private int experience;

    /**
     * @return Get {@link PlayerAssignedPeopleExperienceEntity#playerPeople}
     */
    public PlayerAssignedPeopleEntity getPlayerPeople() {
        return playerPeople;
    }

    /**
     * @param playerPeople The {@link PlayerAssignedPeopleExperienceEntity#playerPeople} to setLang
     */
    public void setPlayerPeople(final PlayerAssignedPeopleEntity playerPeople) {
        this.playerPeople = playerPeople;
    }

    /**
     * @return Get {@link PlayerAssignedPeopleExperienceEntity#job}
     */
    public JobEntity getJob() {
        return job;
    }

    /**
     * @param job The {@link PlayerAssignedPeopleExperienceEntity#job} to setLang
     */
    public void setJob(final JobEntity job) {
        this.job = job;
    }

    /**
     * @return Get {@link PlayerAssignedPeopleExperienceEntity#id}
     */
    public PlayerAssignedPeopleExperienceEntityPK getId() {
        return id;
    }

    /**
     * @param id The {@link PlayerAssignedPeopleExperienceEntity#id} to setLang
     */
    public void setId(final PlayerAssignedPeopleExperienceEntityPK id) {
        this.id = id;
    }

    /**
     * @return Get {@link PlayerAssignedPeopleExperienceEntity#experience}
     */
    public int getExperience() {
        return experience;
    }

    /**
     * @param experience The {@link PlayerAssignedPeopleExperienceEntity#experience} to setLang
     */
    public void setExperience(final int experience) {
        this.experience = experience;
    }

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof PlayerAssignedPeopleExperienceEntity)) {
			return false;
		}

		final PlayerAssignedPeopleExperienceEntity that = (PlayerAssignedPeopleExperienceEntity) o;

		if (experience != that.experience) {
			return false;
		}
		if (!job.equals(that.job)) {
			return false;
		}
		return playerPeople.equals(that.playerPeople);

	}

	@Override
	public int hashCode() {
		int result = playerPeople.hashCode();
		result = (31 * result) + job.hashCode();
		result = (31 * result) + experience;
		return result;
	}
}
