package resortmanager.webservice.service.handlers;

import resortmanager.data.models.Client;
import resortmanager.data.models.Hotel;
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
 * Time: 12:07 AM
 * To change this template use File | Settings | File Templates.
 */
@Path("/api/hotel")
public class HotelHandler {
    Connection connection = ConnectionSingleton.getConnection();
    @GET
    @Produces("text/plain")
    @Path("by_id/{id}")
    public String HotelById(@PathParam("id") String id){
//        Connection connection = ConnectionSingleton.get Connection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Hotel WHERE id_hotel = '" + id + "'");
            if (resultSet.next()) {
                Hotel hotel = new Hotel();
                hotel.setId(resultSet.getInt("id_hotel"));
                hotel.setName(resultSet.getString("name_hotel"));
                return hotel.ToJSON();
            }
            throw new Exception();
        } catch (Exception e) {
            return "Error";  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @GET
    @Produces("text/plain")
    @Path("by_name/{name}")
    public String HotelByName(@PathParam("name") String name){
//        Connection connection = ConnectionSingleton.get Connection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Hotel WHERE name_hotel = '" + name + "'");
            if (resultSet.next()) {
                Hotel hotel = new Hotel();
                hotel.setId(resultSet.getInt("id_hotel"));
                hotel.setName(resultSet.getString("name_hotel"));
                return hotel.ToJSON();
            }
            throw new Exception();
        } catch (Exception e) {
            return "Error";  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @GET
    @Produces("text/plain")
    @Path("all")
    public String HotelAll(){
//        Connection connection = ConnectionSingleton.get Connection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Hotel");
            if (resultSet.next()) {
                Hotel hotel = new Hotel();
                hotel.setId(resultSet.getInt("id_hotel"));
                hotel.setName(resultSet.getString("name_hotel"));
                return hotel.ToJSON();
            }
            throw new Exception();
        } catch (Exception e) {
            return "Error";  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
