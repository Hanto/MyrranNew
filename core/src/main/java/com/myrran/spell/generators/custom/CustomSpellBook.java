package com.myrran.spell.generators.custom;

import com.myrran.misc.Identifiable;
import com.myrran.spell.data.templatedata.SpellBookTemplates;
import com.myrran.spell.data.templatedata.SpellDebuffTemplate;
import com.myrran.spell.data.templatedata.SpellFormTemplate;
import com.myrran.utils.InvalidIDException;
import com.myrran.utils.QuantityHashMap;
import com.myrran.utils.QuantityMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomSpellBook
{
    private QuantityMap formTeplatesLearned = new QuantityHashMap();
    private QuantityMap debuffTemplatesLearned = new QuantityHashMap();
    private Map<String, CustomSpellForm> customForms = new HashMap<>();
    private Map<String, CustomSpellDebuff> customDebuffs = new HashMap<>();

    @XmlTransient
    private SpellBookTemplates templateBook;

    private static final Logger LOG = LogManager.getFormatterLogger(CustomSpellBook.class);

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    public void setSpellBookTemplates(SpellBookTemplates tBook) { this.templateBook = tBook; }

    // -> TEMPLATES:
    //--------------------------------------------------------------------------------------------------------

    public void addSpellFormTemplate(String templateID)
    {   this.formTeplatesLearned.add(templateID); }

    public void addSpellDebuffTemplate(String templateID)
    {   this.debuffTemplatesLearned.add(templateID); }

    public void removeSpellFormTemplate(String templateID)
    {   this.formTeplatesLearned.remove(templateID); }

    public void removeSpellDebuffTemplate(String templateID)
    {   this.debuffTemplatesLearned.remove(templateID); }

    // TEMPLATES -> CUSTOMSPELL:
    //--------------------------------------------------------------------------------------------------------

    public void addCustomSpellForm(String templateID) throws InvalidIDException
    {
        if (formTeplatesLearned.borrow(templateID))
        {
            SpellFormTemplate template = getSpellFormTemplate(templateID);

            CustomSpellForm customSpell = new CustomSpellForm();
            customSpell.setSpellFormTemplate(template);
            setUUID(customSpell, customForms);
            customForms.put(customSpell.getID(), customSpell);
        }
        else
            throw new InvalidIDException("SpellForm template hasn't been learned, ID: %s", templateID);
    }

    public void addCustomSpellDebuff(String templateID) throws InvalidIDException
    {
        if (debuffTemplatesLearned.borrow(templateID))
        {
            SpellDebuffTemplate template = getSpellDebuffTemplate(templateID);

            CustomSpellDebuff customSpell = new CustomSpellDebuff();
            customSpell.setSpellDebuffTemplate(template);
            setUUID(customSpell, customDebuffs);
            customDebuffs.put(customSpell.getID(), customSpell);
        }
        else
            throw new InvalidIDException("SpellDebuff template hasn't been learned, ID: %s", templateID);
    }

    public void removeCustomSpellForm(String spellID) throws InvalidIDException
    {
        CustomSpellForm spell = customForms.remove(spellID);

        if (spell != null)
        {
            formTeplatesLearned.returnBack(spell.getTemplateID());

            List<String>customSpellDebuffIDs = spell.getCustomSpellSlots().values().stream()
                .map(CustomSpellSlot::getID)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

            for (String debuffID: customSpellDebuffIDs)
                removeSpellDebuff(debuffID);
        }
        else
            throw new InvalidIDException("CustomSpellForm with the following ID doesn't exist: %s", spellID);
    }

    public void removeSpellDebuff(String spellID) throws InvalidIDException
    {
        CustomSpellDebuff spell = customDebuffs.remove(spellID);

        if (spell != null)
            debuffTemplatesLearned.returnBack(spell.getTemplateID());
        else
            throw new InvalidIDException("CustomSpellDebuff with the following ID doesn't exist: %s", spellID);
    }

    // CUSTOMSPELL -> CUSTOMSPELL:
    //--------------------------------------------------------------------------------------------------------

    public void assignSpellDebuff(String debuffID, String formID, String slotID) throws InvalidIDException
    {
        CustomSpellForm spellForm = getCustomSpellForm(formID);
        CustomSpellDebuff spellDebuff = getCustomSpellDebuff(debuffID);

        if (spellForm.getCustomSpellSlots().get(slotID) != null)
            spellForm.setCustomSpellDebuff(spellDebuff, slotID);

        else
            throw new InvalidIDException("An Slot with the following ID doesn't exist: %s", slotID);
    }

    public void unnasignSpellDebuff(String formID, String slotID) throws InvalidIDException
    {
        CustomSpellForm spellForm = getCustomSpellForm(formID);

        if (spellForm.getCustomSpellSlots().get(slotID) != null)
            spellForm.removeCustomSpellDebuff(slotID);

        else
            throw new InvalidIDException("An Slot with the following ID doesn't exist: %s", slotID);
    }

    // RELOAD TEMPLATES:
    //--------------------------------------------------------------------------------------------------------

    public void reload()
    {
        for (CustomSpellForm spell: customForms.values())
            spell.setSpellFormTemplate(templateBook.getSpellFormTemplates().get(spell.getTemplateID()));

        for (CustomSpellDebuff spell: customDebuffs.values())
            spell.setSpellDebuffTemplate(templateBook.getSpellDebuffTemplates().get(spell.getTemplateID()));
    }

    // HELPER:
    //--------------------------------------------------------------------------------------------------------

    private void setUUID(Identifiable spell, Map<String, ?>map)
    {
        int counter = 0;
        String uuid;

        do
        {   uuid = String.format("%s_%02d", spell.getID(), counter++); }
        while
        (   map.containsKey(uuid));

        spell.setID(uuid);
    }

    private CustomSpellForm getCustomSpellForm(String spellID) throws InvalidIDException
    {
        CustomSpellForm spell = customForms.get(spellID);
        if (spell != null) return spell;
        else throw new InvalidIDException("A CustomSpellForm with the following ID doesn't exist: %s", spellID);
    }

    private CustomSpellDebuff getCustomSpellDebuff(String spellID) throws InvalidIDException
    {
        CustomSpellDebuff spell = customDebuffs.get(spellID);
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