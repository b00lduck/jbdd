package com.nigames.jbdd.rest.dto;

import com.nigames.jbdd.rest.dto.facet.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class Good implements IsDto, Identifiable, HasNameAndDesc, CanBeEnabled, IsStorable, Deletable {

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
		if ((null == o) || (getClass() != o.getClass())) {
			return false;
		}

		final Good good = (Good) o;

		if (!canBeEnabled.equals(good.canBeEnabled)) {
			return false;
		}
		if (!deletable.equals(good.deletable)) {
			return false;
		}
		if (!hasNameAndDesc.equals(good.hasNameAndDesc)) {
			return false;
		}
		return isStorable.equals(good.isStorable);

	}

	@Override
	public int hashCode() {
		int result = hasNameAndDesc.hashCode();
		result = (31 * result) + canBeEnabled.hashCode();
		result = (31 * result) + isStorable.hashCode();
		result = (31 * result) + deletable.hashCode();
		return result;
	}
}
