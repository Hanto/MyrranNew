package com.myrran.spell.generators.custom;

import com.myrran.dataestructures.QuantityMap.QuantityMap;
import com.myrran.dataestructures.QuantityMap.QuantityMapI;
import com.myrran.misc.Identifiable;
import com.myrran.spell.data.templatedata.SpellBookTemplates;
import com.myrran.spell.data.templatedata.SpellDebuffTemplate;
import com.myrran.spell.data.templatedata.SpellFormTemplate;
import com.myrran.spell.generators.custom.debuffslot.CustomDebuffSlot;
import com.myrran.utils.InvalidIDException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomSpellBook
{
    private QuantityMapI formTeplatesLearned = new QuantityMap(HashMap::new);
    private QuantityMapI debuffTemplatesLearned = new QuantityMap(HashMap::new);
    private Map<String, CustomSpellForm> customSpells = new HashMap<>();

    @XmlTransient
    private SpellBookTemplates templateBook;

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    public void setSpellBookTemplates(SpellBookTemplates tBook) { this.templateBook = tBook; }

    // TEMPLATES -> LEARNED
    //--------------------------------------------------------------------------------------------------------

    public void addSpellFormTemplate(String templateID)
    {   this.formTeplatesLearned.add(templateID); }

    public void addSpellDebuffTemplate(String templateID)
    {   this.debuffTemplatesLearned.add(templateID); }

    public void removeSpellFormTemplate(String templateID)
    {   this.formTeplatesLearned.remove(templateID); }

    public void removeSpellDebuffTemplate(String templateID)
    {   this.debuffTemplatesLearned.remove(templateID); }

    // LEARNED -> CUSTOMSPELL:
    //--------------------------------------------------------------------------------------------------------

    public void addCustomSpellForm(String formTemplateID) throws InvalidIDException
    {
        if (formTeplatesLearned.containsKey(formTemplateID))
        {
            SpellFormTemplate template = getSpellFormTemplate(formTemplateID);
            CustomSpellForm customSpell = new CustomSpellForm(template);
            setUUID(customSpell);

            customSpells.put(customSpell.getID(), customSpell);
            formTeplatesLearned.borrow(formTemplateID);
        }
        else
            throw new InvalidIDException("SpellForm template hasn't been learned, ID: %s", formTemplateID);
    }

    public void addCustomSpellDebuff(String customFormID, String slotID, String debuffTemplateID) throws InvalidIDException
    {
        if (debuffTemplatesLearned.containsKey(debuffTemplateID))
        {
            CustomSpellForm spellForm = getCustomSpellForm(customFormID);
            SpellDebuffTemplate template = getSpellDebuffTemplate(debuffTemplateID);
            CustomSpellDebuff spellDebuff = new CustomSpellDebuff(template);
            setUUID(spellDebuff);

            spellForm.setCustomSpellDebuff(spellDebuff, slotID);
            debuffTemplatesLearned.borrow(debuffTemplateID);
        }
        else
            throw new InvalidIDException("SpellDebuffDeco template hasn't been learned, ID: %s", debuffTemplateID);
    }

    public void removeCustomSpellForm(String customFormID) throws InvalidIDException
    {
        CustomSpellForm spellForm = getCustomSpellForm(customFormID);

        for (CustomDebuffSlot slot: spellForm.getDebuffSlots().values())
            removeCustomSpellDebuff(customFormID, slot.getID());

        customSpells.remove(customFormID);
        formTeplatesLearned.returnBack(spellForm.getTemplateID());
    }

    public void removeCustomSpellDebuff(String customFormID, String slotID) throws InvalidIDException
    {
        CustomSpellForm spellForm = getCustomSpellForm(customFormID);
        CustomSpellDebuff spellDebuff = spellForm.getCustomSpellDebuff(slotID);

        if (spellDebuff != null)
        {
            spellForm.removeCustomSpellDebuff(slotID);
            debuffTemplatesLearned.returnBack(spellDebuff.getTemplateID());
        }
    }

    // RELOAD TEMPLATES:
    //--------------------------------------------------------------------------------------------------------

    public void reloadAll() throws InvalidIDException
    {
        for (CustomSpellForm spell: customSpells.values())
            reloadSpellForm(spell);
    }

    private void reloadSpellForm(CustomSpellForm spellForm) throws InvalidIDException
    {
        spellForm.setSpellFormTemplate(getSpellFormTemplate(spellForm.getTemplateID()));

        for (CustomDebuffSlot slot: spellForm.getDebuffSlots().values())
            reloadSpellDebuff(slot.getCustomSpellDebuff());
    }

    private void reloadSpellDebuff(CustomSpellDebuff spellDebuff) throws InvalidIDException
    {
        if (spellDebuff != null)
            spellDebuff.setSpellDebuffTemplate(getSpellDebuffTemplate(spellDebuff.getTemplateID()));
    }

    // FORM of any ID:
    //--------------------------------------------------------------------------------------------------------

    public CustomSpellForm getSpellFormWithTheStats(String customSpellStatsID) throws InvalidIDException
    {
        if (customSpells.containsKey(customSpellStatsID))
            return customSpells.get(customSpellStatsID);

        for (CustomSpellForm form: customSpells.values())
        {
            if (form.getDebuffSlots().values().stream()
                .filter(slot -> slot.getCustomSpellDebuff() != null)
                .map(CustomDebuffSlot::getCustomSpellDebuff)
                .map(CustomSpellDebuff::getID)
                .collect(Collectors.toList())
                .contains(customSpellStatsID))

                return form;
        }

        throw new InvalidIDException("The following ID isn't assigned to any SpellForm: %s", customSpellStatsID);
    }

    // HELPER:
    //--------------------------------------------------------------------------------------------------------

    private void setUUID(Identifiable spell)
    {
        String uuid = UUID.randomUUID().toString();
        spell.setID(uuid);
    }

    public CustomSpellForm getCustomSpellForm(String spellID) throws InvalidIDException
    {
        CustomSpellForm spell = customSpells.get(spellID);
        if (spell != null) return spell;
        else throw new InvalidIDException("A CustomSpellForm with the following ID doesn't exist: %s", spellID);
    }

    private SpellFormTemplate getSpellFormTemplate(String spellID) throws InvalidIDException
    {
        SpellFormTemplate spell = templateBook.getSpellFormTemplate(spellID);
        if (spell != null) return spell;
        else throw new InvalidIDException("A SpellFormTemplate with the following ID doesn't exist: %s", spellID);
    }

    private SpellDebuffTemplate getSpellDebuffTemplate(String spellID) throws InvalidIDException
    {
        SpellDebuffTemplate spell = templateBook.getSpellDebuffTemplate(spellID);
        if (spell != null) return spell;
        else throw new InvalidIDException("A SpellDebuffTemplate with the following ID doesn't exist: %s", spellID);
    }
}