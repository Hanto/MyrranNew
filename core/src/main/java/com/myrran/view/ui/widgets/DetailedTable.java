package com.myrran.view.ui.widgets;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.myrran.view.ui.listeners.TouchDownListener;

/** @author Ivan Delgado Huerta */
public abstract class DetailedTable extends Table implements DetailedActorI
{
    protected Table rootTable;
    protected Table tableHeader;
    protected Table tableDetails;

    protected boolean detailsVisible = false;
    protected Cell<Actor> cellDetails;

    public DetailedTable()
    {
        rootTable       = new Table().top().left();
        tableHeader     = new Table().top().left();
        tableDetails    = new Table().top().left();

        rootTable.setTouchable(Touchable.enabled);
        rootTable.addListener(new TouchDownListener(e -> this.toFront()));
    }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    protected void createLayout()
    {
        clear();
        top().left();

        rootTable.add(tableHeader).bottom().left().row();
        rootTable.add(tableDetails).top().left().row();
        cellDetails = rootTable.getCell(tableDetails);

        add(rootTable);
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
