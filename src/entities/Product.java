/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author Adam baba
 */
public class Product {
    
    private int id,price,quantite,likes,panier_id ;
    private String name,image;
    private Date datefabrication;

    public Product(int id, int price, int quantite, int likes, int panier_id, String name, String image, Date datefabrication) {
        this.id = id;
        this.price = price;
        this.quantite = quantite;
        this.likes = likes;
        this.panier_id = panier_id;
        this.name = name;
        this.image = image;
        this.datefabrication = datefabrication;
    }

    public Product(int price, int quantite, int likes, int panier_id, String name, String image, Date datefabrication) {
        this.price = price;
        this.quantite = quantite;
        this.likes = likes;
        this.panier_id = panier_id;
        this.name = name;
        this.image = image;
        this.datefabrication = datefabrication;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getPanier_id() {
        return panier_id;
    }

    public void setPanier_id(int panier_id) {
        this.panier_id = panier_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDatefabrication() {
        return datefabrication;
    }

    public void setDatefabrication(Date datefabrication) {
        this.datefabrication = datefabrication;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", price=" + price + ", quantite=" + quantite + ", likes=" + likes + ", panier_id=" + panier_id + ", name=" + name + ", image=" + image + ", datefabrication=" + datefabrication + '}';
    }
    
    
}
