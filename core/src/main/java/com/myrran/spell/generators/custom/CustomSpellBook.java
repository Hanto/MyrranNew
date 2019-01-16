package com.myrran.spell.generators.custom;

import com.myrran.misc.Identifiable;
import com.myrran.spell.data.templatedata.SpellBookTemplates;
import com.myrran.spell.data.templatedata.SpellDebuffTemplate;
import com.myrran.spell.data.templatedata.SpellFormTemplate;
import com.myrran.spell.generators.custom.debuffslot.CustomDebuffSlot;
import com.myrran.utils.InvalidIDException;
import com.myrran.dataestructures.QuantityMap.QuantityMap;
import com.myrran.dataestructures.QuantityMap.QuantityMapImp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.HashMap;
import java.util.Map;

/** @author Ivan Delgado Huerta */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomSpellBook
{
    private QuantityMap formTeplatesLearned = new QuantityMapImp(HashMap::new);
    private QuantityMap debuffTemplatesLearned = new QuantityMapImp(HashMap::new);
    private Map<String, CustomSpellForm> customSpells = new HashMap<>();

    @XmlTransient
    private SpellBookTemplates templateBook;

    private static final Logger LOG = LogManager.getFormatterLogger(CustomSpellBook.class);

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

            spellForm.setCustomSpellDebuff(spellDebuff, slotID);
            debuffTemplatesLearned.borrow(debuffTemplateID);
        }
        else
            throw new InvalidIDException("SpellDebuff template hasn't been learned, ID: %s", debuffTemplateID);
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

    // HELPER:
    //--------------------------------------------------------------------------------------------------------

    private void setUUID(Identifiable spell)
    {
        int counter = 0;
        String uuid;

        do
        {   uuid = String.format("%s_%02d", spell.getID(), counter++); }
        while
        (   customSpells.containsKey(uuid));

        spell.setID(uuid);
    }

    private CustomSpellForm getCustomSpellForm(String spellID) throws InvalidIDException
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