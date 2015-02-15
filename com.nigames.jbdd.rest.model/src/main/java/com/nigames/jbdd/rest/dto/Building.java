package com.nigames.jbdd.rest.dto;

import com.nigames.jbdd.rest.dto.facet.*;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@ApiModel("building")
public class Building implements Identifiable, HasNameAndDesc, CanBeEnabled, Buyable, Deletable {

	private final Identifiable isIdentifiable = new IdentifiableImpl();
	private final HasNameAndDesc hasNameAndDesc = new HasNameAndDescImpl();
	private final CanBeEnabled canBeEnabled = new CanBeEnabledImpl();
	private final Buyable buyable = new BuyableImpl();
	private final Deletable deletable = new DeletableImpl();

	@Override
	@ApiModelProperty("enabled or not")
	public boolean isEnabled() {
		return canBeEnabled.isEnabled();
	}

	@Override
	public void setEnabled(final boolean enabled) {
		canBeEnabled.setEnabled(enabled);
	}

	@Override
	public Map<String, String> getName() {
		return hasNameAndDesc.getName();
	}

	@Override
	public void setName(final Map<String, String> name) {
		hasNameAndDesc.setName(name);
	}

	@Override
	public Map<String, String> getDescription() {
		return hasNameAndDesc.getDescription();
	}

	@Override
	public void setDescription(final Map<String, String> description) {
		hasNameAndDesc.setDescription(description);
	}

	@Override
	public long getId() {
		return isIdentifiable.getId();
	}

	@Override
	public void setId(final long id) {
		isIdentifiable.setId(id);
	}

	@Override
	public int getBuildtime() {
		return buyable.getBuildtime();
	}

	@Override
	public void setBuildtime(final int buildtime) {
		buyable.setBuildtime(buildtime);
	}

	@Override
	public boolean isDeletable() {
		return deletable.isDeletable();
	}

	@Override
	public void setDeletable(final boolean deletable) {
		this.deletable.setDeletable(deletable);
	}

	@SuppressWarnings("AccessingNonPublicFieldOfAnotherObject")
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if ((null == o) || (getClass() != o.getClass())) {
			return false;
		}

		final Building building = (Building) o;

		if (!buyable.equals(building.buyable)) {
			return false;
		}
		if (!canBeEnabled.equals(building.canBeEnabled)) {
			return false;
		}
		if (!deletable.equals(building.deletable)) {
			return false;
		}
		return hasNameAndDesc.equals(building.hasNameAndDesc);

	}

	@Override
	public int hashCode() {
		int result = hasNameAndDesc.hashCode();
		result = (31 * result) + canBeEnabled.hashCode();
		result = (31 * result) + buyable.hashCode();
		result = (31 * result) + deletable.hashCode();
		return result;
	}

}
