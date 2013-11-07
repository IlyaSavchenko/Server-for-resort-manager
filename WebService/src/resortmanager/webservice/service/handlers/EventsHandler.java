package resortmanager.webservice.service.handlers;

import resortmanager.data.models.Client;
import resortmanager.data.models.Event;
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
 * Date: 11/7/13
 * Time: 12:06 AM
 * To change this template use File | Settings | File Templates.
 */
@Path("/api/events")
public class EventsHandler {
    Connection connection = ConnectionSingleton.getConnection();
    @GET
    @Produces("text/plain")
    @Path("by_id/{id}")
    public String EventById(@PathParam("id") String id){
//        Connection connection = ConnectionSingleton.get Connection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Events WHERE id_event = '" + id + "'");
            if (resultSet.next()) {
                Event event = new Event();
                event.setId(resultSet.getInt("id_event"));
                event.setName(resultSet.getString("ev_name"));
                event.setDate(resultSet.getDate("ev_date"));
                event.setPlace(resultSet.getString("ev_time"));
                event.setTime(resultSet.getTime("ev_time"));
                return event.ToJSON();
            }
            throw new Exception();
        } catch (Exception e) {
            return "Error";  //To change body of catch statement use File | Settings | File Templates.
        }
    }


}
