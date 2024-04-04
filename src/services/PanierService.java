package services;

import entities.Panier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author Adam baba
 */
public class PanierService implements IService<Panier> {

    Connection cnx;

    public PanierService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(Panier t) throws SQLException {
        String req = "INSERT INTO panier(id) VALUES(?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getId());
        ps.executeUpdate();
    }

    @Override
    public void modifier(Panier t) throws SQLException {
        // For the Panier entity, there's no modification needed.
        // The Panier entity only has an ID field, which typically wouldn't be modified.
        // If your application requires modification logic, it should be added here.
    }

    @Override
    public void supprimer(Panier t) throws SQLException {
        String req = "DELETE FROM panier WHERE id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getId());
        ps.executeUpdate();
    }

    @Override
    public List<Panier> recuperer() throws SQLException {
        List<Panier> paniers = new ArrayList<>();
        String req = "SELECT * FROM panier";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Panier panier = new Panier(
                    rs.getInt("id")
            );
            paniers.add(panier);
        }
        return paniers;
    }
}
