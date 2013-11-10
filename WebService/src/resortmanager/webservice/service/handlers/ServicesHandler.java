package resortmanager.webservice.service.handlers;

import resortmanager.data.models.Event;
import resortmanager.data.models.Order;
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
    public String ServiceById(@PathParam("id") String id){
//        Connection connection = ConnectionSingleton.get Connection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Service WHERE id_serv = '" + id + "'");
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

    @GET
    @Produces("text/plain")
    @Path("all")
    public String ServicesAll(){
        try {
            Statement statment = connection.createStatement();
            ResultSet resultSet = statment.executeQuery("SELECT * FROM Services");
            String json = "[";
            boolean firstRecord = true;
            while (resultSet.next()){
                if (firstRecord) {
                    firstRecord = false;
                }
                else {
                    json = json.concat(",");
                }
                Service service = new Service();
                service.setId(resultSet.getInt("id_event"));
                service.setName(resultSet.getString("serv_name"));
                service.setDate(resultSet.getDate("serv_date"));
                service.setMaxNumber(resultSet.getInt("serv_max_number"));
                service.setCurrentNumber(resultSet.getInt("serv_curr_number"));
                json = json.concat(service.ToJSON());
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
    @Path("by_name/{name}")
    public String ServiceByName(@PathParam("name") String name){
        try {
            Statement statment = connection.createStatement();
            ResultSet resultSet = statment.executeQuery("SELECT * FROM Services WHERE serv_name = '" + name + "'");
            String json = "[";
            boolean firstRecord = true;
            while (resultSet.next()){
                if (firstRecord) {
                    firstRecord = false;
                }
                else {
                    json = json.concat(",");
                }
                Service service = new Service();
                service.setId(resultSet.getInt("id_event"));
                service.setName(resultSet.getString("serv_name"));
                service.setDate(resultSet.getDate("serv_date"));
                service.setMaxNumber(resultSet.getInt("serv_max_number"));
                service.setCurrentNumber(resultSet.getInt("serv_curr_number"));
                json = json.concat(service.ToJSON());
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
    @Path("by_date/{date}")
    public String ServiceByDate(@PathParam("date") String date){
        try {
            Statement statment = connection.createStatement();
            ResultSet resultSet = statment.executeQuery("SELECT * FROM Services WHERE serv_date = '" + date + "'");
            String json = "[";
            boolean firstRecord = true;
            while (resultSet.next()){
                if (firstRecord) {
                    firstRecord = false;
                }
                else {
                    json = json.concat(",");
                }
                Service service = new Service();
                service.setId(resultSet.getInt("id_event"));
                service.setName(resultSet.getString("serv_name"));
                service.setDate(resultSet.getDate("serv_date"));
                service.setMaxNumber(resultSet.getInt("serv_max_number"));
                service.setCurrentNumber(resultSet.getInt("serv_curr_number"));
                json = json.concat(service.ToJSON());
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
