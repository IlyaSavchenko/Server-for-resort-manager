package resortmanager.webservice.service.handlers;

import resortmanager.data.models.Client;
import resortmanager.webservice.dal.ConnectionSingleton;

import javax.ws.rs.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA.
 * User: ilyasavchenko
 * Date: 11/7/13
 * Time: 12:03 AM
 * To change this template use File | Settings | File Templates.
 */
@Path("/api/clients/")
public class ClientsHandler {
    Connection connection = ConnectionSingleton.getConnection();
    @GET
    @Produces("text/plain")
    @Path("by_id/{id}")
    public String ClientById(@PathParam("id") String id){
//        Connection connection = ConnectionSingleton.get Connection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Clients WHERE id_client = '" + id + "'");
            if (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getInt("id_client"));
                client.setName(resultSet.getString("cl_name"));
                client.setMiddlename(resultSet.getString(("cl_middlename")));
                client.setSurname(resultSet.getString("cl_surname"));
                client.setBirthday(resultSet.getDate("cl_birthday"));
                client.getPassport(resultSet.getInt("cl_passport"));
                return client.ToJSON();
            }
            throw new Exception();
        } catch (Exception e) {
            return "Error";  //To change body of catch statement use File | Settings | File Templates.
        }
    }


    @GET
    @Produces("text/plain")
    @Path("by_passport/{passport}")
    public String ClientByPassport(@PathParam("passport") String passport){
        try {
            Statement statment = connection.createStatement();
            ResultSet resultSet = statment.executeQuery("SELECT * FROM Clients WHERE cl_passport = '" + passport + "'");
            if (resultSet.next()){
                Client client = new Client();
                client.setId(resultSet.getInt("id_client"));
                client.setName(resultSet.getString("cl_name"));
                client.setMiddlename(resultSet.getString(("cl_middlename")));
                client.setSurname(resultSet.getString("cl_surname"));
                client.setBirthday(resultSet.getDate("cl_birthday"));
                client.getPassport(resultSet.getInt("cl_passport"));
                return client.ToJSON();
            }
            throw new Exception();
        } catch (Exception e) {
            return "ERROR";  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @GET
    @Produces("text/plain")
    @Path("by_surname/{surname}")
    public String ClientBySurname(@PathParam("surname") String surname){
        try {
            Statement statment = connection.createStatement();
            ResultSet resultSet = statment.executeQuery("SELECT * FROM Clients WHERE cl_surname = '" + surname + "'");
            String json = "[";
            boolean firstRecord = true;
            while (resultSet.next()){
                if (firstRecord) {
                    firstRecord = false;
                }
                else {
                    json = json.concat(",");
                }
                Client client = new Client();
                client.setId(resultSet.getInt("id_client"));
                client.setName(resultSet.getString("cl_name"));
                client.setMiddlename(resultSet.getString(("cl_middlename")));
                client.setSurname(resultSet.getString("cl_surname"));
                client.setBirthday(resultSet.getDate("cl_birthday"));
                client.getPassport(resultSet.getInt("cl_passport"));
                json = json.concat(client.ToJSON());
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
    @Path("register")
    public String ClientRegister(@QueryParam("name") String name, @QueryParam("surname") String surname, @QueryParam("middlename") String middlename, @QueryParam("passport") String passport){
        try {
            Statement statment = connection.createStatement();
            statment.executeUpdate("INSERT INTO Clients('cl_name, cl_surname, cl_middlename, cl_passport') " +
                    "VALUES ('" + name + "', '" + surname + "','" + middlename +"', " + passport + ")");
            //throw new Exception();
            return "register success";
        } catch (Exception e) {

            return "ERROR";  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}







