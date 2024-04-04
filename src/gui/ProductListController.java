/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Product;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ProductService;

/**
 * FXML Controller class
 *
 * @author Adam baba
 */
public class ProductListController implements Initializable {

    @FXML
    private TableView<Product> tbProducts;
    @FXML
    private TableColumn<?, ?> cName;
    @FXML
    private TableColumn<?, ?> cPrice;
    @FXML
    private TableColumn<?, ?> cDate;
    @FXML
    private TableColumn<?, ?> cQuantity;
    //  @FXML
    //  private TableColumn<?, ?> cImage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ListeProducts();
        } catch (SQLException ex) {
            Logger.getLogger(ProductListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List<Product> productsList;

    public void ListeProducts() throws SQLException {

        ProductService ps = new ProductService();

        // Initialize table columns
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        cDate.setCellValueFactory(new PropertyValueFactory<>("datefabrication"));
        cQuantity.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        //  cImage.setCellValueFactory(new PropertyValueFactory<>("image"));

        boolean deleteColumnExists = false;
        boolean ModifyColumnExists = false;

        for (TableColumn column : tbProducts.getColumns()) {
            if (column.getText().equals("Action")) {
                deleteColumnExists = true;
                break;
            }
        }
        
        
                if (!deleteColumnExists) {
            TableColumn<Product, Void> deleteColumn = new TableColumn<>("Action");
            deleteColumn.setCellFactory(column -> {
                return new TableCell<Product, Void>() {
                    private final Button deleteButton = new Button("Delete");

                    {
                        deleteButton.setOnAction(event -> {
                            Product u = getTableView().getItems().get(getIndex());
                            ProductService us = new ProductService();
                            Alert alert = new Alert(AlertType.CONFIRMATION);
                            alert.setTitle("Delete Product");
                            alert.setHeaderText("Are you sure you want to delete this Product?");
                            alert.setContentText("This action cannot be undone.");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.isPresent() && result.get() == ButtonType.OK) {
                                try {
                                    System.out.println(u);
                                    us.aaaa(u.getId());

                                    refreshTable();
                                } catch (SQLException ex) {
                                    Logger.getLogger(ProductListController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {

                                alert.close();
                            }

                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(deleteButton);
                        }
                    }
                };
            });

            tbProducts.getColumns().add(deleteColumn);
        }
                
                
                

        
        
        // Load voyages from the database
        List<Product> list = ps.recuperer();
        System.out.println(list);
        ObservableList<Product> observableList = FXCollections.observableArrayList(list);
        tbProducts.setItems(observableList);

    }

    @FXML
    private void Create(ActionEvent event) {
    }
    
        public void refreshTable() {
        try {
            productsList = new ProductService().recuperer();
            tbProducts.setItems(FXCollections.observableArrayList(productsList));
        } catch (SQLException ex) {
            Logger.getLogger(ProductListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
