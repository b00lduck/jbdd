package com.nigames.jbdd.domain.entities.subitem.buyable;

import javax.persistence.MappedSuperclass;

/**
 * Abstract database entity for all SubItems.
 *
 * @author Daniel
 */
@MappedSuperclass
public class AbstractAmountSubItemEntity {

    /**
     * Amount of the subitem.
     */
    private Long amount;

    /**
     * @return Get {@link AbstractAmountSubItemEntity#amount}
     */
    public Long getAmount() {
        return amount;
    }

    /**
     * @param amount The {@link AbstractAmountSubItemEntity#amount} to setLang
     */
    public void setAmount(final Long amount) {
        this.amount = amount;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
	    if (!(o instanceof AbstractAmountSubItemEntity)) {
		    return false;
        }

	    final AbstractAmountSubItemEntity that = (AbstractAmountSubItemEntity) o;

        return amount.equals(that.getAmount());

    }

    @Override
    public int hashCode() {
        return amount.hashCode();
    }
}
