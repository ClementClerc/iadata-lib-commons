package fr.toulouse.iadata.datamodels.models.ngsi;

import fr.toulouse.iadata.datamodels.models.management.Application;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
