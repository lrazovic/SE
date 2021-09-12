package it.sapienza.softeng.complexrestservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/flights")
public class FlightsRepository {

    private Connection conn;
     
        /* Initial simple version, based on main memory
            
            final private Map<Integer, Fligth> fligths = new HashMap<>();
            {
            
            Fligth fl1 = new Fligth();
            Fligth fl2 = new Fligth();
            fl1.setId(1);
            fl1.setName("AZ140");
            fl2.setId(2);
            fl2.setName("LH9120");
            
            fligths.put(1, fl1);
            fligths.put(2, fl2);
            }
            */

    public void setConnection(String pos) {
        try {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FlightsRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
            conn = DriverManager.getConnection("jdbc:sqlite:" + pos);
        } catch (SQLException ex) {
            Logger.getLogger(FlightsRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @GET
    @Path("{flightId}")
    @Produces("application/json")
    public Fligth getFlight(@PathParam("flightId") int fligthId) {
        return findById(fligthId);
    }

    @PUT
    @Path("{flightId}")
    @Consumes("application/json")
    public Response updateFligth(@PathParam("flightId") int flightId, Fligth fligth) {
        Fligth existing = findById(flightId);
        if (existing == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if (existing.equals(fligth)) {
            return Response.notModified().build();
        }
        // fligths.put(fligthId, fligth);
        update(flightId, fligth);
        return Response.ok().build();
    }

    private Fligth findById(int id) {

        PreparedStatement stat = null;
        Fligth fl = null;
        try {
            stat = conn.prepareStatement("select * from fligth where id = ?");
            stat.setString(1, String.valueOf(id));
            ResultSet rs = stat.executeQuery();
            if (rs.next()) {
                fl = new Fligth();
                fl.setId(Integer.parseInt(rs.getString("id")));
                fl.setName(rs.getString("name"));
                Logger.getLogger(FlightsRepository.class.getName()).log(Level.INFO, "Accessed : " + fl);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(FlightsRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        /* simple version 
        for (Map.Entry<Integer, Fligth> fligth : fligths.entrySet()) {
            if (fligth.getKey() == id) {
                return fligth.getValue();
            }
        }
        */
        return fl;
    }

    private void update(int fligthId, Fligth fligth) {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement("update fligth set name = ? where id = ?");
            stat.setString(1, fligth.getName());
            stat.setString(2, String.valueOf(fligthId));

            int affectedRow = stat.executeUpdate();
            if (affectedRow == 1) {
                Logger.getLogger(FlightsRepository.class.getName()).log(Level.FINE, "Updated : " + fligth);
                return;
            } else throw new RuntimeException();
        } catch (Exception ex) {
            Logger.getLogger(FlightsRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
