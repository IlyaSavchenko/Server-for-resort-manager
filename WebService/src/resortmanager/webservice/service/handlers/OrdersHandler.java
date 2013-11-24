package resortmanager.webservice.service.handlers;

import resortmanager.data.models.Event;
import resortmanager.data.models.Order;
import resortmanager.webservice.dal.ConnectionSingleton;

import javax.ws.rs.*;
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
                order.setTime(resultSet.getString("ord_time"));
                return order.ToJSON();
            }
            throw new Exception();
        } catch (Exception e) {
            return "Error";  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @GET
    @Produces("text/plain")
    @Path("all")
    public String OrdersAll(){
        try {
            Statement statment = connection.createStatement();
            ResultSet resultSet = statment.executeQuery("SELECT * FROM Orders");
            String json = "[";
            boolean firstRecord = true;
            while (resultSet.next()){
                if (firstRecord) {
                    firstRecord = false;
                }
                else {
                    json = json.concat(",");
                }
                Order order = new Order();
                order.setName(resultSet.getString("ord_name"));
                json = json.concat(order.ToJSON());
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
    public String OrderByName(@PathParam("name") String name){
        try {
            Statement statment = connection.createStatement();
            ResultSet resultSet = statment.executeQuery("SELECT * FROM Orders WHERE ord_name = '" + name + "'");
            String json = "[";
            boolean firstRecord = true;
            while (resultSet.next()){
                if (firstRecord) {
                    firstRecord = false;
                }
                else {
                    json = json.concat(",");
                }
                Order order = new Order();
                order.setId(resultSet.getInt("id_order"));
                order.setName(resultSet.getString("ord_name"));
                order.setDate(resultSet.getDate("ord_date"));
                order.setTime(resultSet.getString("ord_time"));
                json = json.concat(order.ToJSON());
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
    public String EventByDate(@PathParam("date") String date){
        try {
            Statement statment = connection.createStatement();
            ResultSet resultSet = statment.executeQuery("SELECT * FROM Orders WHERE ord_date = '" + date + "'");
            String json = "[";
            boolean firstRecord = true;
            while (resultSet.next()){
                if (firstRecord) {
                    firstRecord = false;
                }
                else {
                    json = json.concat(",");
                }
                Order order = new Order();
                order.setId(resultSet.getInt("id_order"));
                order.setName(resultSet.getString("ord_name"));
                order.setDate(resultSet.getDate("ord_date"));
                order.setTime(resultSet.getString("ord_time"));
                json = json.concat(order.ToJSON());
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
    @Path("changedate")
    public String ChangeDateOrder(@QueryParam("date") String date, @QueryParam("name") String name){
        try {
            Statement statment = connection.createStatement();
            statment.executeUpdate("UPDATE Orders SET ord_date = '" + date + "' WHERE ord_name = " + name + "");
            //throw new Exception();
            return "register success";
        } catch (Exception e) {

            return "ERROR";  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @GET
    @Produces("text/plain")
    @Path("changetime")
    public String ChangeDateEvent(@QueryParam("time") String time, @QueryParam("name") String name){
        try {
            Statement statment = connection.createStatement();
            statment.executeUpdate("UPDATE Orders SET ord_time= '" + time + "' WHERE ord_name = " + name + "");
            //throw new Exception();
            return "register success";
        } catch (Exception e) {

            return "ERROR";  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @GET
    @Produces("text/plain")
    @Path("make")
    public String MakeOrder(@QueryParam("name") String name, @QueryParam("date") String date, @QueryParam("time") String time){
        try {
            Statement statment = connection.createStatement();
            statment.executeUpdate("INSERT INTO Orders(ord_name, ord_date, ord_time) VALUES ('" + name + "','" + date + "', '" + time + "')");
            //throw new Exception();
            return "register success";
        } catch (Exception e) {

            return "ERROR";  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @GET
    @Produces("text/plain")
    @Path("add")
    public String AddNewOrder(@QueryParam("name") String name, @QueryParam("date") String date, @QueryParam("time") String time){
        try {
            Statement statment = connection.createStatement();
            statment.executeUpdate("INSERT INTO Orders(ord_name) VALUES ('" + name + "'                ");
            //throw new Exception();
            return "register success";
        } catch (Exception e) {

            return "ERROR";  //To change body of catch statement use File | Settings | File Templates.
        }
    }



}
