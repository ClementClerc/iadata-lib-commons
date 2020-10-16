package fr.toulouse.iadata.datamodels.models.ngsi;

import fr.toulouse.iadata.datamodels.models.management.Application;
import fr.toulouse.iadata.datamodels.service.EntityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest( classes = {Application.class})
@RunWith( SpringRunner.class )
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
