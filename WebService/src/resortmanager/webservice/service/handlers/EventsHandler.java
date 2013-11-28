package resortmanager.webservice.service.handlers;

import resortmanager.data.models.Event;
import resortmanager.webservice.dal.ConnectionSingleton;

import javax.ws.rs.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA.
 * User: ilyasavchenko
 * DateIOHandler: 11/7/13
 * Time: 12:06 AM
 * To change this template use File | Settings | File Templates.
 */
@Path("/api/events")
public class EventsHandler {
    Connection connection = ConnectionSingleton.getConnection();
    @GET
    @Produces("text/plain")
    @Path("by_id/{id}")
    public String EventById(@PathParam("id") String id){
//        Connection connection = ConnectionSingleton.get Connection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Events WHERE id_event = '" + id + "'");
            if (resultSet.next()) {
                Event event = new Event();
                event.setId(resultSet.getInt("id_event"));
                event.setName(resultSet.getString("ev_name"));
                event.setDate(resultSet.getDate("ev_date"));
                event.setPlace(resultSet.getString("ev_time"));
                event.setTime(resultSet.getTime("ev_time"));
                return event.ToJSON();
            }
            throw new Exception();
        } catch (Exception e) {
            return "Error";  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @GET
    @Produces("text/plain")
    @Path("all")
    public String EventAll(){
        try {
            Statement statment = connection.createStatement();
            ResultSet resultSet = statment.executeQuery("SELECT * FROM Events");
            String json = "[";
            boolean firstRecord = true;
            while (resultSet.next()){
                if (firstRecord) {
                    firstRecord = false;
                }
                else {
                    json = json.concat(",");
                }
                Event event = new Event();
                event.setId(resultSet.getInt("id_event"));
                event.setName(resultSet.getString("ev_name"));
                event.setDate(resultSet.getDate("ev_date"));
                event.setPlace(resultSet.getString("ev_time"));
                event.setTime(resultSet.getTime("ev_time"));
                json = json.concat(event.ToJSON());
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
    public String EventByName(@PathParam("name") String name){
        try {
            Statement statment = connection.createStatement();
            ResultSet resultSet = statment.executeQuery("SELECT * FROM Events WHERE ev_name = '" + name + "'");
            String json = "[";
            boolean firstRecord = true;
            while (resultSet.next()){
                if (firstRecord) {
                    firstRecord = false;
                }
                else {
                    json = json.concat(",");
                }
                Event event = new Event();
                event.setId(resultSet.getInt("id_event"));
                event.setName(resultSet.getString("ev_name"));
                event.setDate(resultSet.getDate("ev_date"));
                event.setPlace(resultSet.getString("ev_place"));
                event.setTime(resultSet.getTime("ev_time"));
                json = json.concat(event.ToJSON());
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
            ResultSet resultSet = statment.executeQuery("SELECT * FROM Events WHERE ev_date = '" + date + "'");
            String json = "[";
            boolean firstRecord = true;
            while (resultSet.next()){
                if (firstRecord) {
                    firstRecord = false;
                }
                else {
                    json = json.concat(",");
                }
                Event event = new Event();
                event.setId(resultSet.getInt("id_event"));
                event.setName(resultSet.getString("ev_name"));
                event.setDate(resultSet.getDate("ev_date"));
                event.setPlace(resultSet.getString("ev_time"));
                event.setTime(resultSet.getTime("ev_time"));
                json = json.concat(event.ToJSON());
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
        @Path("add")
        public String AddEvent(@QueryParam("name") String name, @QueryParam("place") String place, @QueryParam("date") String date, @QueryParam("time") String time){
            try {
                Statement statment = connection.createStatement();
                statment.executeUpdate("INSERT INTO Events(ev_name, ev_place, ev_date, ev_time) " +
                        "VALUES ('" + name + "', '" + place + "','" + date +"', " + time + ")");
                //throw new Exception();
                return "register success";
            } catch (Exception e) {

                return "ERROR";  //To change body of catch statement use File | Settings | File Templates.
            }
        }

    @GET
    @Produces("text/plain")
    @Path("changedate")
    public String ChangeDateEvent(@QueryParam("date") String date, @QueryParam("name") String name){
        try {
            Statement statment = connection.createStatement();
            statment.executeUpdate("UPDATE Events SET ev_date = '" + date + "' WHERE ev_name = " + name + "");
            //throw new Exception();
            return "register success";
        } catch (Exception e) {

            return "ERROR";  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @GET
    @Produces("text/plain")
    @Path("changetime")
    public String ChangeTimeEvent(@QueryParam("time") String time, @QueryParam("name") String name){
        try {
            Statement statment = connection.createStatement();
            statment.executeUpdate("UPDATE Events SET ev_time = '" + time + "' WHERE ev_name = " + name + "");
            //throw new Exception();
            return "register success";
        } catch (Exception e) {

            return "ERROR";  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    }


