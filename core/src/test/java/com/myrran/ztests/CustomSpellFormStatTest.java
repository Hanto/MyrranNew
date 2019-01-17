package com.myrran.ztests;

import com.myrran.spell.data.templatedata.SpellBookTemplates;
import com.myrran.spell.generators.custom.CustomSpellBook;
import com.myrran.utils.InvalidIDException;
import com.nitorcreations.junit.runners.NestedRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

@RunWith(NestedRunner.class)
public class CustomSpellFormStatTest
{
    public class pim
    {
        @Test
        public void spellForm() throws JAXBException, FileNotFoundException, TransformerException, InvalidIDException
        {
            SpellBookTemplates book = unmarshal(SpellBookTemplates.class);

            CustomSpellBook cbook = new CustomSpellBook();
            cbook.setSpellBookTemplates(book);

            cbook.addSpellFormTemplate("Bolt");
            cbook.addSpellFormTemplate("Bolt");
            cbook.addSpellFormTemplate("Bolt");
            cbook.addSpellDebuffTemplate("Poison");
            cbook.addSpellDebuffTemplate("Poison");
            cbook.addSpellDebuffTemplate("Slow");

            cbook.addCustomSpellForm("Bolt");
            cbook.addCustomSpellDebuff("Bolt_00", "Spot 2", "Poison");
            cbook.removeCustomSpellForm("Bolt_00");

            cbook.addCustomSpellForm("Bolt");
            cbook.addCustomSpellDebuff("Bolt_00", "Spot 2", "Poison");
            cbook.removeCustomSpellDebuff("Bolt_00", "Spot 2");
            cbook.addCustomSpellDebuff("Bolt_00", "Spot 2", "Poison");
            cbook.addCustomSpellDebuff("Bolt_00", "Spot 1", "Slow");



            cbook.reloadAll();

            marshal(cbook, CustomSpellBook.class);

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

    private static void marshal(Object object, Class classz) throws JAXBException, FileNotFoundException, TransformerException
    {
        JAXBContext context     = JAXBContext.newInstance(classz);
        Marshaller marshaller   = context.createMarshaller();
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        DOMResult domResult     = new DOMResult();
        OutputStream out        = new FileOutputStream(new File("assets/"+classz.getSimpleName()+".xml"));

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(object, domResult);

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transformer.transform(new DOMSource(domResult.getNode()), new StreamResult(out));
    }
}