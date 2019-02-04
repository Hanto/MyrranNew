package com.myrran.view.ui.customspell.book;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.myrran.view.ui.widgets.DetailedActorI;

/** @author Ivan Delgado Huerta */
public abstract class DetailsTable extends Table implements DetailedActorI
{
    protected Table tableHeader;
    protected Table tableDetails;

    protected boolean detailsVisible = false;
    protected Cell<Actor> cellDetails;

    public DetailsTable()
    {
        tableHeader     = new Table().top().left();
        tableDetails    = new Table().top().left();
    }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    protected void createLayout()
    {
        clear();
        top().left();
        add(tableHeader).bottom().left().row();
        add(tableDetails).top().left().row();
        cellDetails = getCell(tableDetails);
        showDetails();
    }

    // DETAILS:
    //--------------------------------------------------------------------------------------------------------

    @Override public void showDetails(boolean visible)
    {
        cellDetails.setActor(visible ? tableDetails : null);
        detailsVisible = !visible;
    }

    public void showDetails()
    {   showDetails(detailsVisible); }
}
