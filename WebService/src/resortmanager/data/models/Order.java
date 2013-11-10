package resortmanager.data.models;

import org.codehaus.jackson.map.ObjectMapper;
import resortmanager.data.JSONMarshallObject;

import java.io.IOException;
import java.sql.Time;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: ilyasavchenko
 * Date: 11/7/13
 * Time: 1:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class Order implements JSONMarshallObject {

    private int id;
    private int clientId;
    private String name;
    private Date date;
    private String time;


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public static Order FromJSON(String jsonString) {
        Order order = new Order();
        ObjectMapper mapper = new ObjectMapper();
        try {
            order = mapper.readValue(jsonString, Order.class);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return order;
    }
}
