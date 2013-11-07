package resortmanager.webservice.service.handlers;

import resortmanager.data.models.Event;
import resortmanager.data.models.Service;
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
 * Time: 12:08 AM
 * To change this template use File | Settings | File Templates.
 */
@Path("/api/services")
public class ServicesHandler {
    Connection connection = ConnectionSingleton.getConnection();
    @GET
    @Produces("text/plain")
    @Path("by_id/{id}")
    public String EventById(@PathParam("id") String id){
//        Connection connection = ConnectionSingleton.get Connection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Events WHERE id_serv = '" + id + "'");
            if (resultSet.next()) {
                Service service = new Service();
                service.setId(resultSet.getInt("id_event"));
                service.setName(resultSet.getString("serv_name"));
                service.setDate(resultSet.getDate("serv_date"));
                service.setMaxNumber(resultSet.getInt("serv_max_number"));
                service.setCurrentNumber(resultSet.getInt("serv_curr_number"));
                return service.ToJSON();
            }
            throw new Exception();
        } catch (Exception e) {
            return "Error";  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
