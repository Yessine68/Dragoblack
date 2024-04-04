/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Adam baba
 */
public class PanierItem {
    
    private int id,product_id,panier_id,quantity;

    public PanierItem(int id, int product_id, int panier_id, int quantity) {
        this.id = id;
        this.product_id = product_id;
        this.panier_id = panier_id;
        this.quantity = quantity;
    }

    public PanierItem(int product_id, int panier_id, int quantity) {
        this.product_id = product_id;
        this.panier_id = panier_id;
        this.quantity = quantity;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getPanier_id() {
        return panier_id;
    }

    public void setPanier_id(int panier_id) {
        this.panier_id = panier_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "PanierItem{" + "id=" + id + ", product_id=" + product_id + ", panier_id=" + panier_id + ", quantity=" + quantity + '}';
    }
    
}
