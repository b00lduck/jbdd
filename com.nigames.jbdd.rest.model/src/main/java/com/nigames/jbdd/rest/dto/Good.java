package com.nigames.jbdd.rest.dto;

import com.nigames.jbdd.rest.dto.facet.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public final class Good implements IsDto, Identifiable, HasNameAndDesc, CanBeEnabled, IsStorable, Deletable {

	private final Identifiable isIdentifiable = new IdentifiableImpl();
	private final HasNameAndDesc hasNameAndDesc = new HasNameAndDescImpl();
	private final CanBeEnabled canBeEnabled = new CanBeEnabledImpl();
	private final IsStorable isStorable = new IsStorableImpl();
	private final Deletable deletable = new DeletableImpl();

	@Override
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
	public int getWeight() {
		return isStorable.getWeight();
	}

	@Override
	public void setWeight(final int weight) {
		isStorable.setWeight(weight);
	}

	@Override
	public int getDensity() {
		return isStorable.getDensity();
	}

	@Override
	public void setDensity(final int density) {
		isStorable.setDensity(density);
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
		if (!(o instanceof Good)) {
			return false;
		}

		final Good good = (Good) o;

		if ((null != canBeEnabled) ? !canBeEnabled.equals(good.canBeEnabled) : (null != good.canBeEnabled)) {
			return false;
		}
		if ((null != deletable) ? !deletable.equals(good.deletable) : (null != good.deletable)) {
			return false;
		}
		if ((null != hasNameAndDesc) ? !hasNameAndDesc.equals(good.hasNameAndDesc) : (null != good.hasNameAndDesc)) {
			return false;
		}
		if ((null != isIdentifiable) ? !isIdentifiable.equals(good.isIdentifiable) : (null != good.isIdentifiable)) {
			return false;
		}
		return !((null != isStorable) ? !isStorable.equals(good.isStorable) : (null != good.isStorable));

	}

	@Override
	public int hashCode() {
		int result = (null != isIdentifiable) ? isIdentifiable.hashCode() : 0;
		result = (31 * result) + ((null != hasNameAndDesc) ? hasNameAndDesc.hashCode() : 0);
		result = (31 * result) + ((null != canBeEnabled) ? canBeEnabled.hashCode() : 0);
		result = (31 * result) + ((null != isStorable) ? isStorable.hashCode() : 0);
		result = (31 * result) + ((null != deletable) ? deletable.hashCode() : 0);
		return result;
	}

}
