package com.myrran.ztests;

import com.myrran.spell.data.templatedata.SpellBookTemplates;
import com.nitorcreations.junit.runners.NestedRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

@RunWith(NestedRunner.class)
public class CustomSpellFormStatTest
{
    public class pim
    {
        @Test
        public void spellForm() throws JAXBException
        {
            SpellBookTemplates book = unmarshal(SpellBookTemplates.class);

            Assert.assertTrue(true);
        }
    }

    @SuppressWarnings("unchecked")
    private <T extends Object>T unmarshal(Class<T> classz) throws JAXBException
    {
        JAXBContext context = JAXBContext.newInstance(classz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        File file = new File("assets/"+classz.getSimpleName()+".xml");
        return (T)unmarshaller.unmarshal(file);
    }
}