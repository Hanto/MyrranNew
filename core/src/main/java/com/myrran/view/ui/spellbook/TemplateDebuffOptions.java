package com.myrran.view.ui.spellbook;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.myrran.model.spell.templates.TemplateSpellDebuff;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.listeners.TemplateDebuffOrderListener;
import com.myrran.view.ui.listeners.TemplateDebuffShowListener;
import com.myrran.view.ui.listeners.TemplateDebuffSortListener;
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
    private boolean reverseOrder = false;
    private WidgetText showDetailsText;
    private WidgetText reverseOrderText;

    private Map<SortDebuffsBy, WidgetText> sortMap = new EnumMap<>(SortDebuffsBy.class);
    private static final Color selectedSort = Color.ORANGE;
    private static final Color unselectedSort = Color.GRAY;

    public boolean getShowDetails()             { return showDetails; }
    public SortDebuffsBy getSortBy()            { return sortBy; }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public TemplateDebuffOptions(SpellBookDebuffView spellBookDebuffView)
    {
        view = spellBookDebuffView;

        reverseOrderText= new WidgetText("Desc", Atlas.get().getFont("10"), Color.WHITE, Color.BLACK, 1);
        showDetailsText = new WidgetText("Show Details", Atlas.get().getFont("14"), Color.WHITE, Color.BLACK, 1);
        showDetailsText.addListener(new TemplateDebuffShowListener(this));
        reverseOrderText.addListener(new TemplateDebuffOrderListener(this));

        Arrays.asList(SortDebuffsBy.values()).forEach(this::createSortWidget);

        createLayout();
    }

    private void createLayout()
    {
        top().left();
        add(showDetailsText).minWidth(80).bottom().left();
        sortMap.values().forEach(this::add);
        add(reverseOrderText);
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
        view.createLayout(sortBy, showDetails, reverseOrder);
    }

    public void sortBy(SortDebuffsBy sort)
    {
        if (sortBy == sort)
            reverseOrder = !reverseOrder;
        reverseOrderText.setText(reverseOrder ? "Asc" : "Desc");

        sortMap.get(sortBy).setTextColor(unselectedSort);
        sortBy = sort;
        sortMap.get(sortBy).setTextColor(selectedSort);

        view.createLayout(sortBy, showDetails, reverseOrder);
    }

    // SORT OPTIONS:
    //--------------------------------------------------------------------------------------------------------

    public enum SortDebuffsBy
    {
        NAME(Comparator.comparing(TemplateSpellDebuff::getName)),
        TYPE(Comparator.comparing(TemplateSpellDebuff::getFactory)),
        COST(Comparator.comparing(TemplateSpellDebuff::getBaseCost)),
        AVAILABLE(Comparator.comparing(TemplateSpellDebuff::getAvailable));

        Comparator<TemplateSpellDebuff> comparator;

        SortDebuffsBy(Comparator<TemplateSpellDebuff> comparator)
        {   this.comparator = comparator; }
    }
}
