package com.nigames.jbdd.rest.dto;

import com.nigames.jbdd.rest.dto.facet.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public final class Job implements IsDto, Identifiable, HasNameAndDesc, CanBeEnabled, Deletable {

	private final Identifiable isIdentifiable = new IdentifiableImpl();
	private final HasNameAndDesc hasNameAndDesc = new HasNameAndDescImpl();
	private final CanBeEnabled canBeEnabled = new CanBeEnabledImpl();
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
	public boolean isDeletable() {
		return deletable.isDeletable();
	}

	@Override
	public void setDeletable(final boolean deletable) {
		this.deletable.setDeletable(deletable);
	}

}

