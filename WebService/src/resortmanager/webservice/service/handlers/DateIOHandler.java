package resortmanager.webservice.service.handlers;

import resortmanager.data.models.DateIO;
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
 * Time: 12:03 AM
 * To change this template use File | Settings | File Templates.
 */
@Path("/api/dateio/")
public class DateIOHandler {
    Connection connection = ConnectionSingleton.getConnection();

    @GET
    @Produces("text/plain")
    @Path("all")
    public String All(){
        try {
            Statement statment = connection.createStatement();
            ResultSet resultSet = statment.executeQuery("SELECT * FROM DataInOut");
            String json = "[";
            boolean firstRecord = true;
            while (resultSet.next()){
                if (firstRecord) {
                    firstRecord = false;
                }
                else {
                    json = json.concat(",");
                }
                DateIO dateIO = new DateIO();
                dateIO.setIdHotel(resultSet.getInt("id_hotel"));
                dateIO.setIdClient(resultSet.getInt("id_client"));
                dateIO.setIn(resultSet.getString("Data_In"));
                dateIO.setOut(resultSet.getString("Data_Out"));
                json = json.concat(dateIO.ToJSON());
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
    @Path("in/{datein}")
    public String In(@PathParam("datein") String datein){
        try {
            Statement statment = connection.createStatement();
            ResultSet resultSet = statment.executeQuery("SELECT * FROM DataInOut WHERE Date_In = '" + datein +"'");
            String json = "[";
            boolean firstRecord = true;
            while (resultSet.next()){
                if (firstRecord) {
                    firstRecord = false;
                }
                else {
                    json = json.concat(",");
                }
                DateIO dateIO = new DateIO();
                dateIO.setIdHotel(resultSet.getInt("id_hotel"));
                dateIO.setIdClient(resultSet.getInt("id_client"));
                dateIO.setIn(resultSet.getString("Data_In"));
                dateIO.setOut(resultSet.getString("Data_Out"));
                json = json.concat(dateIO.ToJSON());
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
    @Path("out/{dateout}")
    public String Out(@PathParam("dateout") String dateout){
        try {
            Statement statment = connection.createStatement();
            ResultSet resultSet = statment.executeQuery("SELECT * FROM DataInOut WHERE Date_In = '" + dateout +"'");
            String json = "[";
            boolean firstRecord = true;
            while (resultSet.next()){
                if (firstRecord) {
                    firstRecord = false;
                }
                else {
                    json = json.concat(",");
                }
                DateIO dateIO = new DateIO();
                dateIO.setIdHotel(resultSet.getInt("id_hotel"));
                dateIO.setIdClient(resultSet.getInt("id_client"));
                dateIO.setIn(resultSet.getString("Data_In"));
                dateIO.setOut(resultSet.getString("Data_Out"));
                json = json.concat(dateIO.ToJSON());
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





