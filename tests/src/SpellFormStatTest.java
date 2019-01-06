import main.com.myrran.spell.SpellSlotKey;
import main.com.myrran.spell.data.SpellBookTemplates;
import main.com.myrran.spell.data.SpellFormTemplate;
import main.com.myrran.spell.data.SpellSlotDataTemplate;
import main.com.myrran.spell.data.SpellStatTemplate;
import main.com.myrran.spell.spellform.generators.SpellFormType;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SpellFormStatTest
{
    @Test
    public void pim() throws JAXBException
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

        SpellSlotDataTemplate slot1 = new SpellSlotDataTemplate()
            .setId("Spot 1")
            .setName("Spot 1")
            .setType("impacto")
            .setLock(SpellSlotKey.DEBUFF, SpellSlotKey.PUREDAMAGE);

        SpellSlotDataTemplate slot2 = new SpellSlotDataTemplate()
            .setId("Spot 2")
            .setName("Spot 2")
            .setType("aoe")
            .setLock(SpellSlotKey.BUFF);

        SpellSlotDataTemplate slot3 = new SpellSlotDataTemplate()
            .setId("Spot 3")
            .setName("Spot 3")
            .setType("ground")
            .setLock(SpellSlotKey.DEBUFF);

        List<SpellSlotDataTemplate> slotList = new ArrayList<>();
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);

        List<SpellStatTemplate>spellStatMap = new ArrayList<>();
        spellStatMap.add(stat1);
        spellStatMap.add(stat2);

        SpellFormTemplate spellForm = new SpellFormTemplate()
            .setId("Super Bolt")
            .setName("Hanto super bolt")
            .setType(SpellFormType.BOLT)
            .setSpellStats(spellStatMap)
            .setSpellSlots(slotList);

        SpellBookTemplates bookData = new SpellBookTemplates();

        Map<String, SpellFormTemplate> spellFormDataMap = new HashMap<>();
        spellFormDataMap.put(spellForm.getId(), spellForm);
        spellFormDataMap.put(spellForm.getId()+"v2", spellForm);


        bookData.setSpellFromTemplates(spellFormDataMap);

        marshal(bookData, SpellBookTemplates.class);

    }

    private void marshal(Object object, Class classz) throws JAXBException
    {
        JAXBContext context = JAXBContext.newInstance(classz);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(object, System.out);
    }
}