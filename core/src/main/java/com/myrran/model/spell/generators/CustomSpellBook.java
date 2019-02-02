package com.myrran.model.spell.generators;

import com.myrran.misc.InvalidIDException;
import com.myrran.misc.dataestructures.quantitymap.QuantityMap;
import com.myrran.misc.dataestructures.quantitymap.QuantityMapI;
import com.myrran.model.components.Identifiable;
import com.myrran.model.spell.templates.TemplateSpellBook;
import com.myrran.model.spell.templates.TemplateSpellDebuff;
import com.myrran.model.spell.templates.TemplateSpellForm;
import com.myrran.model.spell.templates.TemplateSpellSubform;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomSpellBook
{
    private QuantityMapI<TemplateSpellForm> formTemplatesLearned = new QuantityMap<>(new HashMap<>());
    private QuantityMapI<TemplateSpellDebuff> debuffTemplatesLearned = new QuantityMap<>(new HashMap<>());
    private QuantityMapI<TemplateSpellSubform> subformsLearned = new QuantityMap<>(new HashMap<>());
    private Map<String, CustomSpellForm> customSpells = new HashMap<>();

    @XmlTransient private TemplateSpellBook templateBook;

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    public void setSpellBookTemplates(TemplateSpellBook tBook)          { this.templateBook = tBook; }
    public Collection<TemplateSpellDebuff> getDebuffsTemplatesLearned() { return debuffTemplatesLearned.values(); }
    public Collection<TemplateSpellForm> getFormTemplatesLearned()      { return formTemplatesLearned.values(); }
    public Collection<TemplateSpellSubform> getSubformTemplatesLearned(){ return subformsLearned.values(); }
    public Collection<CustomSpellForm> getCustomSpellForms()            { return customSpells.values(); }

    // TEMPLATES -> LEARNED
    //--------------------------------------------------------------------------------------------------------

    public void addSpellFormTemplate(String templateID) throws InvalidIDException
    {
        TemplateSpellForm template = templateBook.getSpellFormTemplate(templateID);
        formTemplatesLearned.add(template);
    }

    public void addSpellDebuffTemplate(String templateID) throws InvalidIDException
    {
        TemplateSpellDebuff template = templateBook.getSpellDebuffTemplate(templateID);
        debuffTemplatesLearned.add(template);
    }

    public void addSpellSubformTemplate(String templateID) throws InvalidIDException
    {
        TemplateSpellSubform template = templateBook.getSpellSubformTemplate(templateID);
        subformsLearned.add(template);
    }

    public void removeSpellFormTemplate(String templateID)
    {   this.formTemplatesLearned.remove(templateID); }

    public void removeSpellDebuffTemplate(String templateID)
    {   this.debuffTemplatesLearned.remove(templateID); }

    public void removeSpellSubformTemplate(String templateID)
    {   this.subformsLearned.remove(templateID); }

    // LEARNED -> CUSTOMSPELL:
    //--------------------------------------------------------------------------------------------------------

    public void addCustomSpellForm(String formTemplateID) throws InvalidIDException
    {
        if (formTemplatesLearned.isAvailable(formTemplateID))
        {
            TemplateSpellForm template = templateBook.getSpellFormTemplate(formTemplateID);
            CustomSpellForm customSpell = new CustomSpellForm(template);
            setUUID(customSpell);

            customSpells.put(customSpell.getID(), customSpell);
            formTemplatesLearned.borrow(formTemplateID);
        }
        else
            throw new InvalidIDException("SpellForm templates not available, templateID: %s", formTemplateID);
    }

    public void addCustomSpellSubform(CustomSubformSlot slot, String subformTemplateID) throws InvalidIDException
    {
        if (subformsLearned.isAvailable(subformTemplateID))
        {
            if (!slot.hasData())
            {
                TemplateSpellSubform template = templateBook.getSpellSubformTemplate(subformTemplateID);

                if (slot.setCustomSpellSubform(template))
                    subformsLearned.borrow(subformTemplateID);
            }
        }
        else
            throw new InvalidIDException("SpellSubForm template not available, templateID: %s", subformTemplateID);
    }

    public void addCustomSpellSubform(String customFormID, String slotID, String subformTemplateID) throws InvalidIDException
    {
        if (subformsLearned.isAvailable(subformTemplateID))
        {
            CustomSpellForm spellForm = getCustomSpellForm(customFormID);
            CustomSubformSlot slot = spellForm.getSubformSlots().getCustomSubformSlot(slotID);
            addCustomSpellSubform(slot, subformTemplateID);
        }
        else
            throw new InvalidIDException("SpellSubForm template not available, templateID: %s", subformTemplateID);
    }

    public void removeCustomSpellSubform(CustomSubformSlot slot)
    {
        if (slot.hasData())
        {
            CustomSpellSubform spellSubform = slot.getContent();

            for (CustomDebuffSlot debuffSlot: spellSubform.getDebuffSlots().getCustomDebuffSlots())
                removeCustomSpellDebuff(debuffSlot);

            String templateID = spellSubform.getTemplateID();
            slot.removeCustomSpellSubform();
            subformsLearned.returnBack(templateID);
        }
    }

    public void addCustomSpellDebuff(CustomDebuffSlot slot, String debuffTemplateID) throws InvalidIDException
    {
        if (debuffTemplatesLearned.isAvailable(debuffTemplateID))
        {
            if (!slot.hasData())
            {
                TemplateSpellDebuff template = templateBook.getSpellDebuffTemplate(debuffTemplateID);

                if (slot.setCustomSpellDebuff(template))
                    debuffTemplatesLearned.borrow(debuffTemplateID);
            }
        }
        else
            throw new InvalidIDException("SpellDebuffDeco templates not available, templateID: %s", debuffTemplateID);
    }

    public void addCustomSpellDebuff(String customFormID, String slotID, String debuffTemplateID) throws InvalidIDException
    {
        if (debuffTemplatesLearned.isAvailable(debuffTemplateID))
        {
            CustomSpellForm spellForm = getCustomSpellForm(customFormID);
            CustomDebuffSlot slot = spellForm.getCustomDebuffSlot(slotID);
            addCustomSpellDebuff(slot, debuffTemplateID);
        }
        else
            throw new InvalidIDException("SpellDebuffDeco templates not available, templateID: %s", debuffTemplateID);
    }

    public void removeCustomSpellForm(String customFormID) throws InvalidIDException
    {
        CustomSpellForm spellForm = getCustomSpellForm(customFormID);

        for (CustomDebuffSlot slot: spellForm.getDebuffSlots().getCustomDebuffSlots())
            removeCustomSpellDebuff(customFormID, slot.getID());

        customSpells.remove(customFormID);
        formTemplatesLearned.returnBack(spellForm.getTemplateID());
    }

    public void removeCustomSpellDebuff(CustomDebuffSlot slot)
    {
        if (slot.hasData())
        {
            CustomSpellDebuff spellDebuff = slot.getContent();
            String templateID = spellDebuff.getTemplateID();
            slot.removeCustomSpellDebuff();
            debuffTemplatesLearned.returnBack(templateID);
        }
    }

    public void removeCustomSpellDebuff(String customFormID, String slotID) throws InvalidIDException
    {
        CustomSpellForm spellForm = getCustomSpellForm(customFormID);
        CustomDebuffSlot slot = spellForm.getCustomDebuffSlot(slotID);
        removeCustomSpellDebuff(slot);
    }

    // RELOAD TEMPLATES:
    //--------------------------------------------------------------------------------------------------------

    public void reloadAll() throws InvalidIDException
    {
        customSpells.clear();
        formTemplatesLearned.values().forEach(template -> template.setAvailable(template.getTotal()));
        debuffTemplatesLearned.values().forEach(template -> template.setAvailable(template.getTotal()));
    }

    // FORM of any ID:
    //--------------------------------------------------------------------------------------------------------

    public CustomSpellForm getSpellFormWithTheStats(CustomSpellStatsI stats) throws InvalidIDException
    {   return getSpellFormWithTheStats(stats.getID()); }

    public CustomSpellForm getSpellFormWithTheStats(String customSpellStatsID) throws InvalidIDException
    {
        if (customSpells.containsKey(customSpellStatsID))
            return customSpells.get(customSpellStatsID);

        for (CustomSpellForm form: customSpells.values())
        {
            if (form.getDebuffSlots().getCustomDebuffSlots().stream()
                .filter(CustomDebuffSlot::hasData)
                .map(CustomDebuffSlot::getContent)
                .map(CustomSpellDebuff::getID)
                .collect(Collectors.toList())
                .contains(customSpellStatsID))

                return form;

            List<CustomSpellSubform> subforms = form.getSubformSlots().getCustomSubformSlots().stream()
                .filter(CustomSubformSlot::hasData)
                .map(CustomSubformSlot::getContent)
                .collect(Collectors.toList());

            for (CustomSpellSubform subform : subforms)
            {
                if (subform.getID().equals(customSpellStatsID))
                    return form;

                if (subform.getDebuffSlots().getCustomDebuffSlots().stream()
                    .filter(CustomDebuffSlot::hasData)
                    .map(CustomDebuffSlot::getContent)
                    .map(CustomSpellDebuff::getID)
                    .collect(Collectors.toList())
                    .contains(customSpellStatsID))

                    return form;
            }

        }

        throw new InvalidIDException("The following ID isn't assigned to any SpellForm: %s", customSpellStatsID);
    }

    // HELPER:
    //--------------------------------------------------------------------------------------------------------

    private void setUUID(Identifiable spell)
    {
        int counter = 0;
        String uuid;

        do
        {   uuid = String.format("SpellForm_%02d", counter++); }
        while
        (   customSpells.containsKey(uuid));

        spell.setID(uuid);
    }

    public CustomSpellForm getCustomSpellForm(String spellID) throws InvalidIDException
    {
        CustomSpellForm spell = customSpells.get(spellID);
        if (spell != null) return spell;
        else throw new InvalidIDException("A CustomSpellForm with the following ID doesn't exist: %s", spellID);
    }
}