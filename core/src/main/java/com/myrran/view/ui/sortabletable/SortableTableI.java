package com.myrran.view.ui.sortabletable;

import java.util.Comparator;

/** @author Ivan Delgado Huerta */
public interface SortableTableI<T>
{   void createLayout(Comparator<T> comparator, boolean showDetails, boolean reverseOrder); }
