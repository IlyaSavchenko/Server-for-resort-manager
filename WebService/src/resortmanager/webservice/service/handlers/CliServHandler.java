package resortmanager.webservice.service.handlers;

import resortmanager.data.models.ClientService;
import resortmanager.data.models.Service;
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
@Path("/api/clserv/")
public class CliServHandler {
    Connection connection = ConnectionSingleton.getConnection();
    @GET
    @Produces("text/plain")
    @Path("allServices")
    public String AllServices(){
//        Connection connection = ConnectionSingleton.get Connection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Client_Service");
            if (resultSet.next()) {
                ClientService cs = new ClientService();
                cs.setClientId(resultSet.getInt("id_client"));
                cs.setServiceId(resultSet.getInt("id_serv"));
                cs.setTime(resultSet.getString("clserv_time"));
                cs.setDate(resultSet.getString("clserv_date"));
                return cs.ToJSON();
            }
            throw new Exception();
        } catch (Exception e) {
            return "Error";  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @GET
    @Produces("text/plain")
    @Path("catalog")
    public String ServicesCatalog(){
        try {
            Statement statment = connection.createStatement();
            ResultSet resultSet = statment.executeQuery("SELECT ord_name FROM Services");
            if (resultSet.next()){
                Service serv = new Service();
                serv.setName(resultSet.getString("serv_name"));
                return serv.ToJSON();
            }
            throw new Exception();
        } catch (Exception e) {
            return "ERROR";  //To change body of catch statement use File | Settings | File Templates.
        }
    }

 @GET
    @Produces("text/plain")
    @Path("make")
    public String MakeOrder(@QueryParam("idCl") int idCl, @QueryParam("idServ") int idServ, @QueryParam("time") String time,@QueryParam("date") String date ){
        try {
            Statement statment = connection.createStatement();
            statment.executeUpdate("INSERT INTO Client_Service (id_client, id_serv, date, time) " +
                    "VALUES ('" + idCl + "','" + idServ + "', '" + date + "', '" + time + "')");
            //throw new Exception();
            return "register success";
        } catch (Exception e) {

            return "ERROR";  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @GET
    @Produces("text/plain")
    @Path("changetime")
    public String ChangeTime(@QueryParam("idCl") int idCl, @QueryParam("idServ") int idServ, @QueryParam("time") String time){
        try {
            Statement statment = connection.createStatement();
            statment.executeUpdate("UPDATE Client_Service SET time = '" + time + "' WHERE id_client = " + idCl + " and  id_serv = " + idServ +"");
            //throw new Exception();
            return "register success";
        } catch (Exception e) {

            return "ERROR";  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @GET
    @Produces("text/plain")
    @Path("changedate")
    public String ChangeDate(@QueryParam("idCl") int idCl, @QueryParam("idServ") int idServ, @QueryParam("date") String date){
        try {
            Statement statment = connection.createStatement();
            statment.executeUpdate("UPDATE Client_Service SET time = '" + date + "' WHERE id_client = " + idCl + " and  id_serv = " + idServ +"");
            //throw new Exception();
            return "register success";
        } catch (Exception e) {

            return "ERROR";  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}





