package resortmanager.webservice.service.handlers;

import resortmanager.data.models.Moderator;
import resortmanager.webservice.dal.ConnectionSingleton;

import javax.ws.rs.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA.
 * User: ilyasavchenko
 * DateIOHandler: 11/7/13
 * Time: 12:07 AM
 * To change this template use File | Settings | File Templates.
 */
@Path("/api/moderators")
public class ModeratorsHandler {
    Connection connection = ConnectionSingleton.getConnection();

    @GET
    @Produces("text/plain")
    @Path("auth")
    public String ModeratorById(@QueryParam("login") String login, @QueryParam("pass") String pass){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Moderators WHERE moderator_login = '" + login + "' " +
                    "                                     and moderator_password = '" + pass +"'");
            if (resultSet.next()) {
                Moderator moderator = new Moderator();
              moderator.setId(resultSet.getInt("id_moderator"));
              moderator.setName(resultSet.getString("moderator_name"));
              moderator.setMiddlename(resultSet.getString(("moderator_middlename")));
              moderator.setSurname(resultSet.getString("moderator_surname"));
              moderator.setBirthday(resultSet.getDate("moderator_birthday"));
                moderator.setLogin(resultSet.getString("moderator_login"));
                moderator.setPassword(resultSet.getString("moderator_password"));

                return moderator.ToJSON();
            }
            throw new Exception();
        } catch (Exception e) {
            return "ERROR!";  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @GET
    @Produces("text/plain")
    @Path("register")
    public String ClientRegister(@QueryParam("login") String login, @QueryParam("pass") String pass, @QueryParam("name") String name,
                                 @QueryParam("surname") String surname, @QueryParam("middlename") String middlename, @QueryParam("birthday") String birthday){
        try {
            Statement statment = connection.createStatement();
            statment.executeUpdate("INSERT INTO Moderators(moderator_login, moderator_password, moderator_name, moderator_surname, moderator_middlename, moderator_birthday) " +
                    "VALUES ('" + login + "','" + pass + "', '" + name + "', '" + surname + "','" + middlename +"', '" + birthday + "')");
            //throw new Exception();
            return "register success";
        } catch (Exception e) {

            return "ERROR";  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
