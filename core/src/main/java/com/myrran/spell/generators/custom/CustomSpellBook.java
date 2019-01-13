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
import java.util.ArrayList;
import java.util.List;

/** @author Ivan Delgado Huerta */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomSpellBook
{
    private QuantityMap spellFormsTemplatesLearned = new QuantityHashMap();
    private QuantityMap spellDebuffTemplatesLearned = new QuantityHashMap();

    private List<CustomSpellForm> customSpellFormList = new ArrayList<>();
    private List<CustomSpellDebuff> customSpellDebuffList = new ArrayList<>();

    @XmlTransient
    private SpellBookTemplates spellBookTemplates;

    private static final Logger LOG = LogManager.getFormatterLogger(CustomSpellBook.class);

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    public void setSpellBookTemplates(SpellBookTemplates tBook) { this.spellBookTemplates = tBook; }
    public void addSpellFormTemplate(String id)                 { this.spellFormsTemplatesLearned.add(id); }
    public void addSpellDebuffTemplate(String id)               { this.spellDebuffTemplatesLearned.add(id); }
    public void removeSpellFormTemplate(String id)              { this.spellFormsTemplatesLearned.remove(id); }
    public void removeSpellDebuffTemplate(String id)            { this.spellDebuffTemplatesLearned.remove(id); }
    public CustomSpellForm getCustomSpellForm(int slot)         { return this.customSpellFormList.get(slot); }
    public CustomSpellDebuff getCustomSpellDebuff(int slot)     { return this.customSpellDebuffList.get(slot); }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public void addCustomSpellForm(String templateID)
    {
        if (spellFormsTemplatesLearned.borrow(templateID))
        {
            SpellFormTemplate template = spellBookTemplates.getSpellFormTemplates().get(templateID);

            if (template != null)
            {
                CustomSpellForm customSpellForm = new CustomSpellForm();
                customSpellForm.setSpellFormTemplate(template);
                customSpellFormList.add(customSpellForm);
            }
            else
                LOG.error("SpellForm template doesn't exist, ID: %s", templateID);
        }
    }

    public void addCustomSpellDebuff(String templateID)
    {
        if (spellDebuffTemplatesLearned.borrow(templateID))
        {
            SpellDebuffTemplate template = spellBookTemplates.getSpellDebuffTemplates().get(templateID);

            if (template != null)
            {
                CustomSpellDebuff customSpellDebuff = new CustomSpellDebuff();
                customSpellDebuff.setSpellDebuffTemplate(template);
                customSpellDebuffList.add(customSpellDebuff);
            }
            else
                LOG.error("SpellDebuff template doesn't exist, ID: %s", templateID);
        }
    }

    public void removeCustomSpellForm(int slot)
    {
        CustomSpellForm spellForm = customSpellFormList.remove(slot);
        spellFormsTemplatesLearned.returnBack(spellForm.getTemplateID());
    }

    public void removeCustomSpellDebuff(int slot)
    {
        CustomSpellDebuff spellDebuff = customSpellDebuffList.remove(slot);
        spellDebuffTemplatesLearned.returnBack(spellDebuff.getTemplateID());
    }

    // RELOAD TEMPLATES:
    //--------------------------------------------------------------------------------------------------------

    public void reload()
    {
        for (CustomSpellForm spellForm: customSpellFormList)
            spellForm.setSpellFormTemplate(spellBookTemplates.getSpellFormTemplates().get(spellForm.getTemplateID()));

        for (CustomSpellDebuff spellDebuff: customSpellDebuffList)
            spellDebuff.setSpellDebuffTemplate(spellBookTemplates.getSpellDebuffTemplates().get(spellDebuff.getTemplateID()));
    }
}
