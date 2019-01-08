import main.com.myrran.spell.SpellSlotKey;
import main.com.myrran.spell.data.templatedata.*;
import main.com.myrran.spell.entity.debuff.SpellDebuffFactory;
import main.com.myrran.spell.entity.form.SpellFormFactory;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CustomSpellFormStatTest
{
    @Test
    public void spellForm() throws JAXBException
    {
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

        List<SpellSlotTemplate> slotList = new ArrayList<>();
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);

        List<SpellStatTemplate>spellStatList = new ArrayList<>();
        spellStatList.add(stat1);
        spellStatList.add(stat2);

        SpellFormTemplate spellForm = new SpellFormTemplate()
            .setId("Super Bolt")
            .setName("Hanto super bolt")
            .setFactory(SpellFormFactory.BOLT)
            .setSpellStats(spellStatList)
            .setSpellSlots(slotList);

        SpellBookTemplates bookData = new SpellBookTemplates();

        Map<String, SpellFormTemplate> spellFormDataMap = new HashMap<>();
        spellFormDataMap.put(spellForm.getId(), spellForm);
        spellFormDataMap.put(spellForm.getId()+"v2", spellForm);


        bookData.setSpellFromTemplates(spellFormDataMap);

        marshal(bookData, SpellBookTemplates.class);
    }

    @Test
    public void spellEffect() throws JAXBException
    {
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

        List<SpellStatTemplate>spellStatList = new ArrayList<>();
        spellStatList.add(stat1);
        spellStatList.add(stat2);

        SpellDebuffTemplate effectTemplate = new SpellDebuffTemplate()
            .setFactory(SpellDebuffFactory.DOT)
            .setId("Super DOT")
            .setName("Hanto Super DOT")
            .setSpellStats(spellStatList)
            .setBaseCost(30)
            .setKeys(SpellSlotKey.DEBUFF);

        marshal(effectTemplate, SpellDebuffTemplate.class);
    }

    private void marshal(Object object, Class classz) throws JAXBException
    {
        JAXBContext context = JAXBContext.newInstance(classz);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(object, System.out);
    }

    private <T extends Object>T unmarshal(Object object, Class<T> classz) throws JAXBException
    {
        JAXBContext context = JAXBContext.newInstance(classz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        unmarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        File file = new File("");
        return (T)unmarshaller.unmarshal(file);
    }
}