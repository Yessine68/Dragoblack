/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Product;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ProductService;

/**
 * FXML Controller class
 *
 * @author Adam baba
 */
public class ModifyProductController implements Initializable {

    ProductService ps = new ProductService();

    private File selectedFile; // Field to store the selected file

    @FXML
    private TextField nameTF;
    @FXML
    private TextField priceTF;
    @FXML
    private TextField dateTF;
    @FXML
    private TextField quantityTF;

    private Product selectedProduct;
    @FXML
    private Label modifLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initData(Product product) {
        selectedProduct = product;
        System.out.println(product);
        // Populate the text fields with voyage data
        nameTF.setText(selectedProduct.getName());
        dateTF.setText(selectedProduct.getDatefabrication());
        priceTF.setText(String.valueOf(selectedProduct.getPrice()));
        quantityTF.setText(String.valueOf(selectedProduct.getQuantite()));

    }

    @FXML
    private void Update(ActionEvent event) {
        String name = nameTF.getText();
        String price = priceTF.getText();
        String date = dateTF.getText();
        String quantity = quantityTF.getText();
        String ProductImage = selectedProduct.getImage();

        if (name.isEmpty() || price.isEmpty() || date.isEmpty() || quantity.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
        } else if (!isValidString(name) || !isValidDateString(date) || !isValidInt(price) || !isValidInt(quantity)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter valid information.");
            alert.showAndWait();
        } else {

            int priceInt = Integer.parseInt(price);
            int quantityInt = Integer.parseInt(quantity);
            int likes = 0;
            selectedProduct.setPrice(priceInt);
            selectedProduct.setQuantite(quantityInt);
            selectedProduct.setLikes(likes);
            selectedProduct.setName(name);
            selectedProduct.setImage(ProductImage);
            selectedProduct.setDatefabrication(date);

            ProductService productService = new ProductService();

            try {
                productService.modifier(selectedProduct);
                // Close the window after successful update
                Stage stage = (Stage) modifLabel.getScene().getWindow();
                stage.close();
                // Refresh the table in GestionVoyageController
                ProductListController.getInstance().refreshTable();

            } catch (SQLException ex) {
                // Handle exception, e.g., display an error message
                System.out.println("Error updating voyage: " + ex.getMessage());
            }

        }

    }

    @FXML
    private void Choose(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        // Show open file dialog
        selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            String imagePath = selectedFile.toURI().toString();
            System.out.println("Selected file: " + imagePath);
            // Now you can save the imagePath to the database or perform any other operation
        }
    }

    private boolean isValidInt(String value) {
        // Check if the value is a valid integer
        return value.matches("-?\\d+");
    }

    private boolean isValidString(String name) {
        // Check if the name contains only letters and has length between 2 and 50
        return name.matches("^[a-zA-Z]{2,50}$");
    }

    private boolean isValidDateString(String dateString) {
        return dateString.matches("^\\d{4}-\\d{2}-\\d{2}$");

    }

}
