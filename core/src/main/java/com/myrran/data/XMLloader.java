package com.myrran.data;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/** @author Ivan Delgado Huerta */
public class XMLloader
{
    @SuppressWarnings("unchecked")
    public static <T extends Object>T unmarshal(Class<T> classz) throws Exception
    {
        JAXBContext context = JAXBContext.newInstance(classz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        File file = new File(classz.getSimpleName()+".xml");
        return (T)unmarshaller.unmarshal(file);
    }

    public static <T extends Object>void marshal(T object) throws JAXBException
    {
        File file = new File(object.getClass().getSimpleName()+".xml");
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(object, file);
    }
}
