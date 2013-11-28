package resortmanager.webservice.service.handlers;

import resortmanager.data.models.Rooms;
import resortmanager.webservice.dal.ConnectionSingleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA.
 * User: ilyasavchenko
 * DateIOHandler: 11/7/13
 * Time: 12:06 AM
 * To change this template use File | Settings | File Templates.
 */
@Path("/api/rooms")
public class RoomsHandler {
    Connection connection = ConnectionSingleton.getConnection();

    @GET
    @Produces("text/plain")
    @Path("all")
    public String EventAll(){
        try {
            Statement statment = connection.createStatement();
            ResultSet resultSet = statment.executeQuery("SELECT * FROM Rooms");
            String json = "[";
            boolean firstRecord = true;
            while (resultSet.next()){
                if (firstRecord) {
                    firstRecord = false;
                }
                else {
                    json = json.concat(",");
                }
                Rooms room = new Rooms();
                room.setIdRoom(resultSet.getInt("id_room"));
                room.setIdHotel(resultSet.getInt("id_hotel"));
                room.setNumOfRoom(resultSet.getInt("num_of_room"));
                room.setCountPlace(resultSet.getInt("count_of_place"));
                room.setEmpty(resultSet.getBoolean("is_empty"));
                json = json.concat(room.ToJSON());
            }
            if (!firstRecord) {
                return json.concat("]");
            }
            else {
                throw new Exception();
            }
        } catch (Exception e) {
            return "ERROR";  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @GET
    @Produces("text/plain")
    @Path("by_name/{countplace}")
    public String EventByName(@PathParam("countplace") String countplace){
        try {
            Statement statment = connection.createStatement();
            ResultSet resultSet = statment.executeQuery("SELECT * FROM Rooms WHERE count_of_place = '" + countplace + "'");
            String json = "[";
            boolean firstRecord = true;
            while (resultSet.next()){
                if (firstRecord) {
                    firstRecord = false;
                }
                else {
                    json = json.concat(",");
                }
                Rooms room = new Rooms();
                room.setIdRoom(resultSet.getInt("id_room"));
                room.setIdHotel(resultSet.getInt("id_hotel"));
                room.setNumOfRoom(resultSet.getInt("num_of_room"));
                room.setCountPlace(resultSet.getInt("count_of_place"));
                room.setEmpty(resultSet.getBoolean("is_empty"));
                json = json.concat(room.ToJSON());
            }
            if (!firstRecord) {
                return json.concat("]");
            }
            else {
                throw new Exception();
            }
        } catch (Exception e) {
            return "ERROR";  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}


