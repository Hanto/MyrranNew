package com.myrran.utils;

import com.myrran.spell.data.templatedata.*;
import com.myrran.spell.entity.debuff.SpellDebuffFactory;
import com.myrran.spell.entity.form.SpellFormFactory;
import com.myrran.spell.generators.custom.CustomSpellSlotKey;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

/** @author Ivan Delgado Huerta */
public class SpellCreator
{
    public static final String COOLDOWN = "Cooldown";
    public static final String SPEED = "Speed";
    private static SpellBookTemplates book = new SpellBookTemplates();

    public static void main(String...args) throws JAXBException
    {
        generateSpellForms();
        generateSpellDebuffs();

        marshal(book, SpellBookTemplates.class);
    }

    public static void generateSpellForms()
    {
        SpellFormTemplate spellForm = new SpellFormTemplate();
        spellForm.setID("Bolt");
        spellForm.setName("Super Bolt");
        spellForm.setFactory(SpellFormFactory.BOLT);

        SpellStatTemplate stat1 = new SpellStatTemplate();
        stat1.setID(COOLDOWN);
        stat1.setName(COOLDOWN);
        stat1.setBaseValue(50);
        stat1.setBonusPerUpgrade(2);
        stat1.setMaxUpgrades(50);
        stat1.setUpgradeCost(2);
        stat1.setIsUpgradeable(true);

        SpellStatTemplate stat2 = new SpellStatTemplate();
        stat2.setID(SPEED);
        stat2.setName(SPEED);
        stat2.setBaseValue(100);
        stat2.setBonusPerUpgrade(2);
        stat2.setMaxUpgrades(50);
        stat2.setUpgradeCost(2);
        stat2.setIsUpgradeable(true);

        SpellSlotTemplate slot1 = new SpellSlotTemplate();
        slot1.setID("Spot 1");
        slot1.setName("Spot 1");
        slot1.setSlotType("impacto");
        slot1.setLock(CustomSpellSlotKey.DEBUFF, CustomSpellSlotKey.PUREDAMAGE);

        SpellSlotTemplate slot2 = new SpellSlotTemplate();
        slot2.setID("Spot 2");
        slot2.setName("Spot 2");
        slot2.setSlotType("aoe");
        slot2.setLock(CustomSpellSlotKey.DEBUFF);

        SpellSlotTemplate slot3 = new SpellSlotTemplate();
        slot3.setID("Spot 3");
        slot3.setName("Spot 3");
        slot3.setSlotType("ground");
        slot3.setLock(CustomSpellSlotKey.DEBUFF);

        spellForm.setSpellStats(stat1, stat2);
        spellForm.setSpellSlots(slot1, slot2, slot3);
        book.addSpellFormTemplate(spellForm);
    }

    public static void generateSpellDebuffs()
    {
        SpellDebuffTemplate debuff = new SpellDebuffTemplate();
        debuff.setID("Super DOT");
        debuff.setName("Hanto Super DOT");
        debuff.setFactory(SpellDebuffFactory.DOT);
        debuff.setBaseCost(30);
        debuff.setKeys(CustomSpellSlotKey.DEBUFF);

        SpellStatTemplate stat1 = new SpellStatTemplate();
        stat1.setID(COOLDOWN);
        stat1.setName(COOLDOWN);
        stat1.setBaseValue(50);
        stat1.setBonusPerUpgrade(2);
        stat1.setMaxUpgrades(50);
        stat1.setUpgradeCost(2);
        stat1.setIsUpgradeable(true);

        SpellStatTemplate stat2 = new SpellStatTemplate();
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

    private static void marshal(Object object, Class classz) throws JAXBException
    {
        JAXBContext context = JAXBContext.newInstance(classz);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(object, new File("assets/"+classz.getSimpleName()+".xml"));
    }
}
