import main.com.myrran.spell.data.templatedata.SpellBookTemplates;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

class CustomSpellFormStatTest
{
    @Test
    public void spellForm() throws JAXBException
    {
        SpellBookTemplates book = unmarshal(SpellBookTemplates.class);

    }

    private <T extends Object>T unmarshal(Class<T> classz) throws JAXBException
    {
        JAXBContext context = JAXBContext.newInstance(classz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        File file = new File("../core/assets/"+classz.getSimpleName()+".xml");
        return (T)unmarshaller.unmarshal(file);
    }
}