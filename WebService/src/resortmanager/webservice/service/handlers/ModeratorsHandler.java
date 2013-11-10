package resortmanager.webservice.service.handlers;

import resortmanager.data.models.Client;
import resortmanager.data.models.Event;
import resortmanager.data.models.Moderator;
import resortmanager.webservice.dal.ConnectionSingleton;

import javax.ws.rs.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA.
 * User: ilyasavchenko
 * Date: 11/7/13
 * Time: 12:07 AM
 * To change this template use File | Settings | File Templates.
 */
@Path("/api/moderators")
public class ModeratorsHandler {
    Connection connection = ConnectionSingleton.getConnection();

    @GET
    @Produces("text/plain")
    @Path("by_id/{id}")
    public String ModeratorById(@PathParam("id") String id){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Moderators WHERE id_moderator = '" + id + "'");
            if (resultSet.next()) {
                Moderator moderator = new Moderator();
                moderator.setId(resultSet.getInt("id_moderator"));
                moderator.setName(resultSet.getString("moderator_name"));
                moderator.setMiddlename(resultSet.getString(("moderator_middlename")));
                moderator.setSurname(resultSet.getString("moderator_surname"));
                moderator.setBirthday(resultSet.getDate("moderator_birthday"));

                return moderator.ToJSON();
            }
            throw new Exception();
        } catch (Exception e) {
            return "ERROR!";  //To change body of catch statement use File | Settings | File Templates.
        }
    }

        @GET
        @Produces("text/plain")
        @Path("by_login/{login}")
        public String ModeratorByLogin(@PathParam("login") String login){
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Moderators WHERE  moderator_login = '" + login + "'");
                if (resultSet.next()) {
                    Moderator moderator = new Moderator();
                    moderator.setId(resultSet.getInt("id_moderator"));
                    moderator.setName(resultSet.getString("moderator_name"));
                    moderator.setMiddlename(resultSet.getString(("moderator_middlename")));
                    moderator.setSurname(resultSet.getString("moderator_surname"));
                    moderator.setBirthday(resultSet.getDate("moderator_birthday"));

                    return moderator.ToJSON();
                }
                throw new Exception();
            } catch (Exception e) {
                return "ERROR!";  //To change body of catch statement use File | Settings | File Templates.
            }

        }

    @GET
    @Produces("text/plain")
    @Path("by_name/{name}")
    public String ClientBySurname(@PathParam("name") String name){
        try {
            Statement statment = connection.createStatement();
            ResultSet resultSet = statment.executeQuery("SELECT * FROM Moderators WHERE moderator_name = '" + name + "'");
            String json = "[";
            boolean firstRecord = true;
            while (resultSet.next()){
                if (firstRecord) {
                    firstRecord = false;
                }
                else {
                    json = json.concat(",");
                }
                Moderator moderator = new Moderator();
                moderator.setId(resultSet.getInt("id_moderator"));
                moderator.setName(resultSet.getString("moderator_name"));
                moderator.setMiddlename(resultSet.getString(("moderator_middlename")));
                moderator.setSurname(resultSet.getString("moderator_surname"));
                moderator.setBirthday(resultSet.getDate("moderator_birthday"));

                json = json.concat(moderator.ToJSON());
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
    @Path("snm")
    public String ModeratorByNSM(@QueryParam("name") String name, @QueryParam("surname") String surname, @QueryParam("middlename") String middlename){
        try {
            Statement statment = connection.createStatement();
            ResultSet resultSet = statment.executeQuery("SELECT * FROM Clients WHERE moderator_surname = '" + surname + "' and " +
                    " moderator_name = '" + name + "' and moderator_middlename = '" + middlename + "'");
            String json = "[";
            boolean firstRecord = true;
            while (resultSet.next()){
                if (firstRecord) {
                    firstRecord = false;
                }
                else {
                    json = json.concat(",");
                }
                Moderator moderator = new Moderator();
                moderator.setId(resultSet.getInt("id_moderator"));
                moderator.setName(resultSet.getString("moderator_name"));
                moderator.setMiddlename(resultSet.getString(("moderator_middlename")));
                moderator.setSurname(resultSet.getString("moderator_surname"));
                moderator.setBirthday(resultSet.getDate("moderator_birthday"));

                json = json.concat(moderator.ToJSON());
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
