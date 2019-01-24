package com.myrran.view.ui.spellbook;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.myrran.controller.TemplateDebuffShowListener;
import com.myrran.controller.TemplateDebuffSortListener;
import com.myrran.model.spell.templates.TemplateSpellDebuff;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.widgets.WidgetText;

import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.Map;

/** @author Ivan Delgado Huerta */
public class TemplateDebuffOptions extends Table
{
    private SpellBookDebuffView view;

    private SortDebuffsBy sortBy = SortDebuffsBy.NAME;
    private boolean showDetails = false;
    private WidgetText showDetailsText;

    private Map<SortDebuffsBy, WidgetText> sortMap = new EnumMap<>(SortDebuffsBy.class);
    private static final Color selectedSort = Color.WHITE;
    private static final Color unselectedSort = Color.GRAY;

    public boolean getShowDetails()            { return showDetails; }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public TemplateDebuffOptions(SpellBookDebuffView spellBookDebuffView)
    {
        view = spellBookDebuffView;

        showDetailsText = new WidgetText("Show Details", Atlas.get().getFont("14"), Color.WHITE, Color.BLACK, 1);
        showDetailsText.addListener(new TemplateDebuffShowListener(this));

        Arrays.asList(SortDebuffsBy.values()).forEach(this::createSortWidget);

        createLayout();
    }

    private void createLayout()
    {
        top().left();
        add(showDetailsText).minWidth(80).bottom().left();

        sortMap.values().forEach(this::add);
        row();
    }

    private void createSortWidget(SortDebuffsBy sortType)
    {
        WidgetText widget = new WidgetText(Atlas.get().getFont("10"), unselectedSort, Color.BLACK, 1);

        if (sortBy == sortType)
            widget.setTextColor(selectedSort);

        widget.setText(sortType.toString().toLowerCase());
        widget.addListener(new TemplateDebuffSortListener(this, sortType));

        sortMap.put(sortType, widget);
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public void setShowDetails()
    {
        showDetails = !showDetails;
        showDetailsText.setText(showDetails? "Hide Details": "Show Details");
        view.createLayout(sortBy, showDetails);
    }

    public void sortBy(SortDebuffsBy sort)
    {
        sortMap.get(sortBy).setTextColor(unselectedSort);
        sortBy = sort;
        sortMap.get(sortBy).setTextColor(selectedSort);

        view.createLayout(sortBy, showDetails);
    }

    // SORT OPTIONS:
    //--------------------------------------------------------------------------------------------------------

    public enum SortDebuffsBy
    {
        NAME(Comparator.comparing(TemplateSpellDebuff::getName)),
        TYPE(Comparator.comparing(TemplateSpellDebuff::getFactory)),
        COST(Comparator.comparing(TemplateSpellDebuff::getBaseCost));

        Comparator<TemplateSpellDebuff> comparator;

        SortDebuffsBy(Comparator<TemplateSpellDebuff> comparator)
        {   this.comparator = comparator; }
    }
}
