package com.myrran.misc.dataestructures.quantitymap;

import com.myrran.model.components.Identifiable;

/** @author Ivan Delgado Huerta */
public interface QuantifiableI extends Identifiable
{
    Integer getTotal();
    void setAvailable(Integer available);
    void setTotal(Integer total);
    Integer getAvailable();
}
