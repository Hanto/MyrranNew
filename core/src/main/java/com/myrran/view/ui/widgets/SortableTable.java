package com.myrran.view.ui.widgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.listeners.ActorMoveListener;
import com.myrran.view.ui.listeners.TouchDownListener;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/** @author Ivan Delgado Huerta */
public abstract class SortableTable<T> extends DetailedTable implements Disposable
{
    // CONTENT:
    //--------------------------------------------------------------------------------------------------------

    private Table tableContent = new Table().top().left();
    private Map<T, Actor> modelToActorMap = new HashMap<>();

    // HEADER:
    //--------------------------------------------------------------------------------------------------------

    private WidgetText name;
    private WidgetImage minimize;
    private WidgetImage maximize;
    private TableSorter<T> tableSorter = new TableSorter<>(modelToActorMap, tableContent);

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    protected void build(String text)
    {   build(text, 0, 0); }

    protected void build(String text, float width, float height)
    {
        name            = new WidgetText(text, Atlas.get().getFont("20"), Color.WHITE, Color.BLACK, 3);
        minimize        = new WidgetImage(Atlas.get().getTexture("TexturasMisc/RebindOff"));
        maximize        = new WidgetImage(Atlas.get().getTexture("TexturasMisc/RebindOff"));

        minimize.addListener(new TouchDownListener(event -> showDetails()));

        tableSorter.createOptionsLayout();
        createHeader();
        createDetails(width, height);
        createLayout();
    }

    @Override public void dispose()
    {
        if (modelToActorMap != null)
            modelToActorMap.values().stream()
                .filter(Disposable.class::isInstance)
                .map(Disposable.class::cast)
                .forEach(Disposable::dispose);
    }

    // OPTIONS LAYOUT:
    //--------------------------------------------------------------------------------------------------------

    private void createHeader()
    {
        Table header = new Table().top().left();

        header.add(name).padLeft(5).left();
        header.add(minimize).expand().right();
        header.add(maximize).right().row();

        tableHeader.add(header).minWidth(250).left().row();
        tableHeader.setTouchable(Touchable.enabled);
        tableHeader.addListener(new ActorMoveListener(this));
        tableHeader.setBackground(Atlas.get().getNinePatchDrawable("TexturasIconos/IconoVacioNine", 0.90f));
    }

    private void createDetails(float width, float height)
    {
        if (width != 0 || height != 0)
        {
            ScrollPane scrollPane = new ScrollPane(tableContent);
            tableDetails.add(tableSorter.getOptionsTable()).expand().fillX().left().row();
            tableDetails.add(scrollPane).size(width, height);
        }
        else
        {
            tableDetails.add(tableSorter.getOptionsTable()).expand().fillX().left().row();
            tableDetails.add(tableContent);
        }
        tableDetails.setBackground(Atlas.get().getNinePatchDrawable("TexturasIconos/IconoVacioNine2", 0.45f));
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(Collection<T>data)
    {
        dispose();
        modelToActorMap.clear();

        if (data != null)
            data.forEach(model -> modelToActorMap.put(model, getActor(model)));

        tableSorter.sortModel();
        tableSorter.setShowDetails(false);
    }

    public void addSortOption(String text, Comparator<T> comparator)
    {   tableSorter.addSortOption(text, comparator); }

    public abstract Actor getActor(T model);
}
