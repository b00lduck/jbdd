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
public final class Building implements Identifiable, HasNameAndDesc, CanBeEnabled, Buyable, Deletable {

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
		if (!(o instanceof Building)) {
			return false;
		}

		final Building building = (Building) o;

		if ((null != buyable) ? !buyable.equals(building.buyable) : (null != building.buyable)) {
			return false;
		}
		if ((null != canBeEnabled) ? !canBeEnabled.equals(building.canBeEnabled) : (null != building.canBeEnabled)) {
			return false;
		}
		if ((null != deletable) ? !deletable.equals(building.deletable) : (null != building.deletable)) {
			return false;
		}
		if ((null != hasNameAndDesc) ? !hasNameAndDesc.equals(building.hasNameAndDesc) : (null != building.hasNameAndDesc)) {
			return false;
		}
		return !((null != isIdentifiable) ? !isIdentifiable.equals(building.isIdentifiable) : (null != building.isIdentifiable));

	}

	@Override
	public int hashCode() {
		int result = (null != isIdentifiable) ? isIdentifiable.hashCode() : 0;
		result = (31 * result) + ((null != hasNameAndDesc) ? hasNameAndDesc.hashCode() : 0);
		result = (31 * result) + ((null != canBeEnabled) ? canBeEnabled.hashCode() : 0);
		result = (31 * result) + ((null != buyable) ? buyable.hashCode() : 0);
		result = (31 * result) + ((null != deletable) ? deletable.hashCode() : 0);
		return result;
	}

}
