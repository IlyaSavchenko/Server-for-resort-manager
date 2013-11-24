package resortmanager.webservice.service.handlers;

import resortmanager.data.models.ClientOrder;
import resortmanager.data.models.Order;
import resortmanager.webservice.dal.ConnectionSingleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
@Path("/api/clord/")
public class CliOrdHandler {
    Connection connection = ConnectionSingleton.getConnection();
    @GET
    @Produces("text/plain")
    @Path("allOrders")
    public String AllOrders(){
//        Connection connection = ConnectionSingleton.get Connection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Client_Order");
            if (resultSet.next()) {
                ClientOrder co = new ClientOrder();
                co.setClientId(resultSet.getInt("id_client"));
                co.setOrderId(resultSet.getInt("id_order"));
                co.setTime(resultSet.getString("clord_time"));
                return co.ToJSON();
            }
            throw new Exception();
        } catch (Exception e) {
            return "Error";  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @GET
    @Produces("text/plain")
    @Path("catalog")
    public String OrdersCatalog(){
        try {
            Statement statment = connection.createStatement();
            ResultSet resultSet = statment.executeQuery("SELECT ord_name FROM Orders");
            if (resultSet.next()){
                Order ord = new Order();
                ord.setName(resultSet.getString("ord_name"));
                return ord.ToJSON();
            }
            throw new Exception();
        } catch (Exception e) {
            return "ERROR";  //To change body of catch statement use File | Settings | File Templates.
        }
    }

 @GET
    @Produces("text/plain")
    @Path("make")
    public String MakeOrder(@QueryParam("idCl") int idCl, @QueryParam("idOrd") int idOrd, @QueryParam("time") String time){
        try {
            Statement statment = connection.createStatement();
            statment.executeUpdate("INSERT INTO Client_Order (id_client, id_order, time) " +
                    "VALUES ('" + idCl + "','" + idOrd + "', '" + time + "')");
            //throw new Exception();
            return "register success";
        } catch (Exception e) {

            return "ERROR";  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @GET
    @Produces("text/plain")
    @Path("changetime")
    public String ChangeTime(@QueryParam("idCl") int idCl, @QueryParam("idOrd") int idOrd, @QueryParam("time") String time){
        try {
            Statement statment = connection.createStatement();
            statment.executeUpdate("UPDATE Client_Order SET time = '" + time + "' WHERE id_client = " + idCl + " and  id_order = " + idOrd +"");
            //throw new Exception();
            return "register success";
        } catch (Exception e) {

            return "ERROR";  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}





