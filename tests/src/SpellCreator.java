import main.com.myrran.spell.SpellSlotKey;
import main.com.myrran.spell.data.templatedata.*;
import main.com.myrran.spell.entity.debuff.SpellDebuffFactory;
import main.com.myrran.spell.entity.form.SpellFormFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class SpellCreator
{
    private static SpellBookTemplates book = new SpellBookTemplates();

    public static void main(String...args) throws JAXBException
    {
        generateSpellForms();
        generateSpellDebuffs();

        marshal(book, SpellBookTemplates.class);
    }

    public static void generateSpellForms()
    {
        SpellFormTemplate spellForm = new SpellFormTemplate()
            .setId("Bolt")
            .setName("Super Bolt")
            .setFactory(SpellFormFactory.BOLT);

        SpellStatTemplate stat1 = new SpellStatTemplate()
            .setID("Cooldown")
            .setName("Cooldown")
            .setBaseValue(50)
            .setBonusPerUpgrade(2)
            .setMaxUpgrades(50)
            .setUpgradeCost(2)
            .setIsUpgradeable(true);

        SpellStatTemplate stat2 = new SpellStatTemplate()
            .setID("Speed")
            .setName("Speed")
            .setBaseValue(100)
            .setBonusPerUpgrade(2)
            .setMaxUpgrades(50)
            .setUpgradeCost(2)
            .setIsUpgradeable(true);

        spellForm.setSpellStats(stat1, stat2);

        SpellSlotTemplate slot1 = new SpellSlotTemplate()
            .setId("Spot 1")
            .setName("Spot 1")
            .setSlotType("impacto")
            .setLock(SpellSlotKey.DEBUFF, SpellSlotKey.PUREDAMAGE);

        SpellSlotTemplate slot2 = new SpellSlotTemplate()
            .setId("Spot 2")
            .setName("Spot 2")
            .setSlotType("aoe")
            .setLock(SpellSlotKey.BUFF);

        SpellSlotTemplate slot3 = new SpellSlotTemplate()
            .setId("Spot 3")
            .setName("Spot 3")
            .setSlotType("ground")
            .setLock(SpellSlotKey.DEBUFF);

        spellForm.setSpellSlots(slot1, slot2, slot3);
        book.addSpellFormTemplate(spellForm);
    }

    public static void generateSpellDebuffs()
    {
        SpellDebuffTemplate debuff = new SpellDebuffTemplate()
            .setId("Super DOT")
            .setName("Hanto Super DOT")
            .setFactory(SpellDebuffFactory.DOT)
            .setBaseCost(30)
            .setKeys(SpellSlotKey.DEBUFF);

        SpellStatTemplate stat1 = new SpellStatTemplate()
            .setID("Cooldown")
            .setName("Cooldown")
            .setBaseValue(50)
            .setBonusPerUpgrade(2)
            .setMaxUpgrades(50)
            .setUpgradeCost(2)
            .setIsUpgradeable(true);

        SpellStatTemplate stat2 = new SpellStatTemplate()
            .setID("Speed")
            .setName("Speed")
            .setBaseValue(100)
            .setBonusPerUpgrade(2)
            .setMaxUpgrades(50)
            .setUpgradeCost(2)
            .setIsUpgradeable(true);

        debuff.setSpellStats(stat1, stat2);
        book.addSpellDebuffTemplate(debuff);
    }

    private static void marshal(Object object, Class classz) throws JAXBException
    {
        JAXBContext context = JAXBContext.newInstance(classz);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(object, new File("core/assets/"+classz.getSimpleName()+".xml"));
    }
}
