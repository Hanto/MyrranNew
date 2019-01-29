package com.myrran.misc;

import com.myrran.model.spell.templates.*;
import com.myrran.model.spell.entities.debuff.SpellDebuffFactory;
import com.myrran.model.spell.entities.form.SpellFormFactory;
import com.myrran.model.spell.generators.CustomSpellSlotKey;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

/** @author Ivan Delgado Huerta */
public class SpellCreator
{
    public static final String COOLDOWN = "Cooldown";
    public static final String SPEED = "Speed";
    private static TemplateSpellBook book = new TemplateSpellBook();

    public static void main(String...args) throws JAXBException
    {
        generateSpellForms();
        generateSpellDebuffs();
        generateSpellDebuffs2();

        marshal(book, TemplateSpellBook.class);
    }

    public static void generateSpellForms()
    {
        TemplateSpellForm spellForm = new TemplateSpellForm();
        spellForm.setID("Bolt");
        spellForm.setName("Super Bolt");
        spellForm.setFactory(SpellFormFactory.BOLT);

        TemplateSpellStat stat1 = new TemplateSpellStat();
        stat1.setID(COOLDOWN);
        stat1.setName(COOLDOWN);
        stat1.setBaseValue(50);
        stat1.setBonusPerUpgrade(2);
        stat1.setMaxUpgrades(50);
        stat1.setUpgradeCost(2);
        stat1.setIsUpgradeable(true);

        TemplateSpellStat stat2 = new TemplateSpellStat();
        stat2.setID(SPEED);
        stat2.setName(SPEED);
        stat2.setBaseValue(100);
        stat2.setBonusPerUpgrade(2);
        stat2.setMaxUpgrades(50);
        stat2.setUpgradeCost(2);
        stat2.setIsUpgradeable(true);

        TemplateSpellStat stat3 = new TemplateSpellStat();
        stat3.setID("Damage");
        stat3.setName("Damage");
        stat3.setBaseValue(100);
        stat3.setBonusPerUpgrade(2);
        stat3.setMaxUpgrades(50);
        stat3.setUpgradeCost(2);
        stat3.setIsUpgradeable(true);

        TemplateSpellSlot slot1 = new TemplateSpellSlot();
        slot1.setID("Slot1");
        slot1.setName("Slot1");
        slot1.setSlotType("impact");
        slot1.setLock(CustomSpellSlotKey.DEBUFF, CustomSpellSlotKey.PUREDAMAGE);

        TemplateSpellSlot slot2 = new TemplateSpellSlot();
        slot2.setID("Slot2");
        slot2.setName("Slot2");
        slot2.setSlotType("aoe");
        slot2.setLock(CustomSpellSlotKey.DEBUFF);

        TemplateSpellSlot slot3 = new TemplateSpellSlot();
        slot3.setID("Slot3");
        slot3.setName("Slot3");
        slot3.setSlotType("ground");
        slot3.setLock(CustomSpellSlotKey.DEBUFF);

        spellForm.setSpellStats(stat1, stat2, stat3);
        spellForm.setSpellSlots(slot1, slot2, slot3);
        book.addSpellFormTemplate(spellForm);
    }

    public static void generateSpellDebuffs()
    {
        TemplateSpellDebuff debuff = new TemplateSpellDebuff();
        debuff.setID("Poison");
        debuff.setName("Poison");
        debuff.setFactory(SpellDebuffFactory.DOT);
        debuff.setBaseCost(30);
        debuff.setKeys(CustomSpellSlotKey.DEBUFF);

        TemplateSpellStat stat1 = new TemplateSpellStat();
        stat1.setID(COOLDOWN);
        stat1.setName(COOLDOWN);
        stat1.setBaseValue(50);
        stat1.setBonusPerUpgrade(2);
        stat1.setMaxUpgrades(50);
        stat1.setUpgradeCost(2);
        stat1.setIsUpgradeable(true);

        TemplateSpellStat stat2 = new TemplateSpellStat();
        stat2.setID(SPEED);
        stat2.setName(SPEED);
        stat2.setBaseValue(100);
        stat2.setBonusPerUpgrade(2);
        stat2.setMaxUpgrades(50);
        stat2.setUpgradeCost(2);
        stat2.setIsUpgradeable(true);

        debuff.setSpellStats(stat1, stat2);
        book.addSpellDebuffTemplate(debuff);
    }

    public static void generateSpellDebuffs2()
    {
        TemplateSpellDebuff debuff = new TemplateSpellDebuff();
        debuff.setID("Slow");
        debuff.setName("Slow");
        debuff.setFactory(SpellDebuffFactory.DOT);
        debuff.setBaseCost(30);
        debuff.setKeys(CustomSpellSlotKey.DEBUFF);

        TemplateSpellStat stat1 = new TemplateSpellStat();
        stat1.setID(COOLDOWN);
        stat1.setName(COOLDOWN);
        stat1.setBaseValue(50);
        stat1.setBonusPerUpgrade(2);
        stat1.setMaxUpgrades(50);
        stat1.setUpgradeCost(2);
        stat1.setIsUpgradeable(true);

        TemplateSpellStat stat2 = new TemplateSpellStat();
        stat2.setID(SPEED);
        stat2.setName(SPEED);
        stat2.setBaseValue(100);
        stat2.setBonusPerUpgrade(2);
        stat2.setMaxUpgrades(50);
        stat2.setUpgradeCost(2);
        stat2.setIsUpgradeable(true);

        TemplateSpellStat stat3 = new TemplateSpellStat();
        stat3.setID("Slow Magnitude");
        stat3.setName("Slow Magnitude");
        stat3.setBaseValue(100);
        stat3.setBonusPerUpgrade(2);
        stat3.setMaxUpgrades(50);
        stat3.setUpgradeCost(2);
        stat3.setIsUpgradeable(true);

        debuff.setSpellStats(stat1, stat2, stat3);
        book.addSpellDebuffTemplate(debuff);
    }

    private static void marshal(Object object, Class classz) throws JAXBException
    {
        File file = new File("assets/"+classz.getSimpleName()+".xml");
        JAXBContext context = JAXBContext.newInstance(classz);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(object, file);
    }
}
