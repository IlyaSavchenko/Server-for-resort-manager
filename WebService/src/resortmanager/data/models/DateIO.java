package resortmanager.data.models;

import org.codehaus.jackson.map.ObjectMapper;
import resortmanager.data.JSONMarshallObject;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: ilyasavchenko
 * DateIOHandler: 11/7/13
 * Time: 12:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class DateIO implements JSONMarshallObject {

    private int idHotel;
    private  int idClient;
    private String in;
    private String out;


    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getIn() {
        return in;
    }

    public void setIn(String in) {
        this.in = in;
    }

    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
    }


    @Override
    public String ToJSON() {
        String json = new String();
        ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return json;
    }

    public static DateIO FromJSON(String jsonString) {
        ObjectMapper mapper = new ObjectMapper();
        DateIO dateIO = new DateIO();
        try {
            dateIO = mapper.readValue(jsonString, DateIO.class);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return dateIO;
    }
}
