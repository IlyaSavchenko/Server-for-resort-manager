package resortmanager.data;

import org.junit.Test;
import resortmanager.data.models.Client;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: ilyasavchenko
 * DateIOHandler: 11/7/13
 * Time: 12:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class ClientTest {

    @Test
    public void TestMarshall() {
        Client client = new Client();
        client.setId(0);
        client.setName("Ivan");
        client.setSurname("Ivanov");
        client.setMiddlename("Ivanovich");
        client.setBirthday(new Date(21-05-2001));
        client.setPassport(12345);
        //
        System.out.print(client.ToJSON());

    }

    @Test
    public void TestUnmarshall() {
        String json = "{\"id\":0,\"name\":\"Ivan\",\"surname\":\"Ivanov\", \"middlename\":\"Ivanovich\", \"birthday\":21-05-2001,\"passport\":100}";
        Client client = Client.FromJSON(json);
        System.out.print("test");
    }

}
