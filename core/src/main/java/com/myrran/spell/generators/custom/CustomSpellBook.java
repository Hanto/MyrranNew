package com.myrran.spell.generators.custom;

import com.myrran.spell.data.templatedata.SpellBookTemplates;
import com.myrran.spell.data.templatedata.SpellDebuffTemplate;
import com.myrran.spell.data.templatedata.SpellFormTemplate;
import com.myrran.utils.QuantityHashMap;
import com.myrran.utils.QuantityMap;
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
    private QuantityMap spellFormsTemplatesLearned = new QuantityHashMap();
    private QuantityMap spellDebuffTemplatesLearned = new QuantityHashMap();
    private Map<String, CustomSpellForm> customFormMap = new HashMap<>();
    private Map<String, CustomSpellDebuff> customDebuffMap = new HashMap<>();
    private int spellFormUUID = 0;
    private int spellDebuffUUID = 0;

    @XmlTransient
    private SpellBookTemplates templateBook;

    private static final Logger LOG = LogManager.getFormatterLogger(CustomSpellBook.class);

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    public void setSpellBookTemplates(SpellBookTemplates tBook) { this.templateBook = tBook; }

    // -> TEMPLATES:
    //--------------------------------------------------------------------------------------------------------

    public void addSpellFormTemplate(String id)
    {   this.spellFormsTemplatesLearned.add(id); }

    public void addSpellDebuffTemplate(String id)
    {   this.spellDebuffTemplatesLearned.add(id); }

    public void removeSpellFormTemplate(String id)
    {   this.spellFormsTemplatesLearned.remove(id); }

    public void removeSpellDebuffTemplate(String id)
    {   this.spellDebuffTemplatesLearned.remove(id); }

    // TEMPLATES -> CUSTOMSPELL:
    //--------------------------------------------------------------------------------------------------------

    public void addCustomSpellForm(String templateID)
    {
        if (spellFormsTemplatesLearned.borrow(templateID))
        {
            SpellFormTemplate template = templateBook.getSpellFormTemplates().get(templateID);

            if (template != null)
            {
                CustomSpellForm customSpell = new CustomSpellForm();
                customSpell.setSpellFormTemplate(template);
                setUUID(customSpell);
                customFormMap.put(customSpell.getID(), customSpell);
            }
            else
                LOG.warn("SpellForm template doesn't exist, ID: %s", templateID);
        }
    }

    public void addCustomSpellDebuff(String templateID)
    {
        if (spellDebuffTemplatesLearned.borrow(templateID))
        {
            SpellDebuffTemplate template = templateBook.getSpellDebuffTemplates().get(templateID);

            if (template != null)
            {
                CustomSpellDebuff customSpell = new CustomSpellDebuff();
                customSpell.setSpellDebuffTemplate(template);
                setUUID(customSpell);
                customDebuffMap.put(customSpell.getID(), customSpell);
            }
            else
                LOG.warn("SpellDebuff template doesn't exist, ID: %s", templateID);
        }
    }

    public void removeCustomSpellForm(String spellID)
    {
        CustomSpellForm spell = customFormMap.remove(spellID);

        if (spell != null)
            spellFormsTemplatesLearned.returnBack(spell.getTemplateID());
    }

    public void removeSpellDebuff(String spellID)
    {
        CustomSpellDebuff spell = customDebuffMap.remove(spellID);

        if (spell != null)
            spellDebuffTemplatesLearned.returnBack(spell.getTemplateID());
    }

    // CUSTOMSPELL -> CUSTOMSPELL:
    //--------------------------------------------------------------------------------------------------------

    public void assignSpellDebuff(String debuffID, String formID, String slotID)
    {
        CustomSpellForm spellForm = customFormMap.get(formID);
        CustomSpellDebuff spellDebuff = customDebuffMap.get(debuffID);

        if (spellForm != null && spellDebuff != null)
        {
            CustomSpellSlot slot = spellForm.getCustomSpellSlots().get(slotID);

            if (slot != null)
            {
                if (slot.opensLock(spellDebuff.getKeys()))
                {
                    customDebuffMap.remove(debuffID);
                    slot.setCustomSpellDebuff(spellDebuff);
                }
                else
                    LOG.warn("SpellDebuff doesn't fit into this slot");
            }
            else
                LOG.warn("SpellSlot with the following ID doesn't exist: %s", slotID);
        }
        else
            LOG.warn("SpellForm or SpellDebuff doesn't exist, ID: %s, %s", formID, debuffID);
    }

    public void unnasignSpellDebuff(String formID, String slotID)
    {
        CustomSpellForm spellForm = customFormMap.get(formID);

        if (spellForm != null)
        {
            CustomSpellSlot slot = spellForm.getCustomSpellSlots().get(slotID);

            if (slot != null)
            {
                CustomSpellDebuff spellDebuff = slot.getCustomSpellDebuff();

                if (spellDebuff != null)
                    customDebuffMap.put(spellDebuff.getID(), spellDebuff);

                slot.removeCustomSpellDebuff();
            }
            else
                LOG.warn("SpellDebuff doesn't fit into this slot");
        }
        else
            LOG.warn("SpellForm doesn't exist, ID: %s", formID);
    }


    // RELOAD TEMPLATES:
    //--------------------------------------------------------------------------------------------------------

    public void reload()
    {
        for (CustomSpellForm spell: customFormMap.values())
            spell.setSpellFormTemplate(templateBook.getSpellFormTemplates().get(spell.getTemplateID()));

        for (CustomSpellDebuff spell: customDebuffMap.values())
            spell.setSpellDebuffTemplate(templateBook.getSpellDebuffTemplates().get(spell.getTemplateID()));
    }

    // HELPER:
    //--------------------------------------------------------------------------------------------------------

    private void setUUID(CustomSpellForm spellForm)
    {   spellForm.setID(spellForm.getID() + "_" + spellFormUUID++); }

    private void setUUID (CustomSpellDebuff spellDebuff)
    {   spellDebuff.setID(spellDebuff.getID() + "_" + spellDebuffUUID++);}
}
