package resortmanager.webservice.service.handlers;

import resortmanager.data.models.Moderator;
import resortmanager.webservice.dal.ConnectionSingleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
}
