package com.nigames.jbdd.domain.entities.subitem.buyable;

import javax.persistence.MappedSuperclass;

/**
 * Abstract database entity for all SubItems.
 *
 * @author Daniel
 */
@MappedSuperclass
public class AbstractBuyableSubItemEntity {

    /**
     * Amount of the subitem.
     */
    private Long amount;

    /**
     * @return Get {@link AbstractBuyableSubItemEntity#amount}
     */
    public Long getAmount() {
        return amount;
    }

    /**
     * @param amount The {@link AbstractBuyableSubItemEntity#amount} to setLang
     */
    public void setAmount(final Long amount) {
        this.amount = amount;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractBuyableSubItemEntity)) {
            return false;
        }

        final AbstractBuyableSubItemEntity that = (AbstractBuyableSubItemEntity) o;

        return amount.equals(that.getAmount());

    }

    @Override
    public int hashCode() {
        return amount.hashCode();
    }
}
