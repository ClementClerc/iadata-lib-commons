package fr.toulouse.iadata.datamodels.models;

import fr.toulouse.iadata.commons.beans.management.Application;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest( classes = {Application.class})
public class ApplicationTest
{
    @Test
    public void testApplication()
    {
        Application app = new Application();
        app.setDataType( "data ");
        app.setDirection( "direction" );
    }

}
