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
public class Rooms implements JSONMarshallObject {

    private int idRoom;
    private int idHotel;
    private int numOfRoom;
    private int countPlace;
    private boolean empty;


    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public int getNumOfRoom() {
        return numOfRoom;
    }

    public void setNumOfRoom(int numOfRoom) {
        this.numOfRoom = numOfRoom;
    }

    public int getCountPlace() {
        return countPlace;
    }

    public void setCountPlace(int countPlace) {
        this.countPlace = countPlace;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
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

    public static Rooms FromJSON(String jsonString) {
        ObjectMapper mapper = new ObjectMapper();
        Rooms rooms = new Rooms();
        try {
            rooms = mapper.readValue(jsonString, Rooms.class);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return rooms;
    }

}
