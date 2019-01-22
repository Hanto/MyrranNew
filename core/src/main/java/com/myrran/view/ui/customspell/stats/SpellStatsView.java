package com.myrran.view.ui.customspell.stats;

import com.badlogic.gdx.utils.Disposable;
import com.myrran.model.spell.generators.custom.CustomSpellStats;
import com.myrran.view.ui.formview2.SpellStatView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public class SpellStatsView implements Disposable
{
    private CustomSpellStats model;

    private List<SpellStatView>stats = new ArrayList<>();

    public List<SpellStatView>getStats()            { return stats; }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SpellStatsView() {}
    public SpellStatsView(CustomSpellStats spellStats)
    {
        this.model = spellStats;

        createView();
    }

    @Override public void dispose()
    {
        stats.stream()
            .filter(Objects::nonNull)
            .forEach(SpellStatView::dispose);
    }

    // CREATE / UPDATE::
    //--------------------------------------------------------------------------------------------------------

    private void createView()
    {
        stats = model.values().stream()
            .map(SpellStatView::new)
            .collect(Collectors.toList());
    }

    public void updateView()
    {   stats.forEach(SpellStatView::updateView); }

    public void setModel(CustomSpellStats spellStats)
    {
        dispose();
        model = spellStats;
        createView();
    }
}
