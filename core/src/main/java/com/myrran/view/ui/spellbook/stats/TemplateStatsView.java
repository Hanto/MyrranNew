package com.myrran.view.ui.spellbook.stats;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.myrran.model.spell.templates.TemplateSpellStat;

import java.util.Comparator;
import java.util.List;

/** @author Ivan Delgado Huerta */
public class TemplateStatsView extends Table
{
    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(List<TemplateSpellStat> model)
    {
        clear();
        top().left();
        model.stream()
            .sorted(Comparator.comparing(TemplateSpellStat::getName))
            .map(TemplateStatView::new)
            .forEach(row -> add(row).left().bottom().row());
    }
}
