package com.myrran.view.ui.customspellview.statsview;

import com.badlogic.gdx.utils.Disposable;
import com.myrran.spell.generators.custom.stats.CustomSpellStat;
import com.myrran.spell.generators.custom.stats.CustomSpellStats;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public class SpellStatsView implements Disposable
{
    private List<SpellStatView>stats = new ArrayList<>();

    public List<SpellStatView>getStats()            { return stats; }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SpellStatsView(CustomSpellStats spellStats)
    {   createView(spellStats.values());}

    @Override public void dispose()
    {   stats.forEach(SpellStatView::dispose); }

    // CREATE / UPDATE::
    //--------------------------------------------------------------------------------------------------------

    private void createView(Collection<CustomSpellStat> spellStats)
    {
        stats = spellStats.stream()
            .map(SpellStatView::new)
            .collect(Collectors.toList());
    }

    public void updateView()
    {   stats.forEach(SpellStatView::updateView); }
}
