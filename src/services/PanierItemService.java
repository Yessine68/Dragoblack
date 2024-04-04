package services;

import entities.PanierItem;
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
public class PanierItemService implements IService<PanierItem> {

    Connection cnx;

    public PanierItemService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(PanierItem t) throws SQLException {
        String req = "INSERT INTO panier_item(product_id, panier_id, quantity) VALUES(?, ?, ?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getProduct_id());
        ps.setInt(2, t.getPanier_id());
        ps.setInt(3, t.getQuantity());
        ps.executeUpdate();
    }

    @Override
    public void modifier(PanierItem t) throws SQLException {
        String req = "UPDATE panier_item SET product_id = ?, panier_id = ?, quantity = ? WHERE id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getProduct_id());
        ps.setInt(2, t.getPanier_id());
        ps.setInt(3, t.getQuantity());
        ps.setInt(4, t.getId());
        ps.executeUpdate();
    }

    @Override
    public void supprimer(PanierItem t) throws SQLException {
        String req = "DELETE FROM panier_item WHERE id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getId());
        ps.executeUpdate();
    }

    @Override
    public List<PanierItem> recuperer() throws SQLException {
        List<PanierItem> panierItems = new ArrayList<>();
        String req = "SELECT * FROM panier_item";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            PanierItem panierItem = new PanierItem(
                    rs.getInt("id"),
                    rs.getInt("product_id"),
                    rs.getInt("panier_id"),
                    rs.getInt("quantity")
            );
            panierItems.add(panierItem);
        }
        return panierItems;
    }
}
