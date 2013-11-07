package resortmanager.webservice.service.handlers;

import resortmanager.data.models.Event;
import resortmanager.data.models.Order;
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
@Path("/api/orders")
public class OrdersHandler {
    Connection connection = ConnectionSingleton.getConnection();
    @GET
    @Produces("text/plain")
    @Path("by_id/{id}")
    public String EventById(@PathParam("id") String id){
//        Connection connection = ConnectionSingleton.get Connection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Orders WHERE id_order = '" + id + "'");
            if (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id_order"));
                order.setName(resultSet.getString("ord_name"));
                order.setDate(resultSet.getDate("ord_date"));
                return order.ToJSON();
            }
            throw new Exception();
        } catch (Exception e) {
            return "Error";  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
