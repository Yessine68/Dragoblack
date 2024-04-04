/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Product;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Adam baba
 */
public class AfficherProductController implements Initializable {

    @FXML
    private ImageView productImageView;
    @FXML
    private Label NameLB;
    @FXML
    private Label priceLB;
    @FXML
    private Label quantityLB;
    @FXML
    private Label dataLB;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    // Method to initialize data in the controller
    public void initData(Product product) {
        // Set product name
        NameLB.setText(product.getName());

        // Set product price
        priceLB.setText(String.valueOf(product.getPrice()));

        // Set product quantity
        quantityLB.setText(String.valueOf(product.getQuantite()));

        // Set product date
        dataLB.setText(product.getDatefabrication());

        // Set product image (assuming you have a method to set images in ImageView)
        // For example:
          if (product.getImage() != null) 
        productImageView.setImage(new Image(product.getImage()));
    }

}
