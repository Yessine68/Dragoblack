/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Product;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.ProductService;

/**
 * FXML Controller class
 *
 * @author Adam baba
 */
public class ProductListController implements Initializable {

    private static ProductListController instance;

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
        instance = this;

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

        if (!ModifyColumnExists) {
            TableColumn<Product, Void> modifyColumn = new TableColumn<>("Update");
            modifyColumn.setCellFactory(column -> {
                return new TableCell<Product, Void>() {
                    private final Button modifyButton = new Button("Modify");

                    {
                        modifyButton.setOnAction(event -> {
                            Product selectedProduct = getTableView().getItems().get(getIndex());
                            // Navigate to updateVoyage.fxml with the selected voyage
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyProduct.fxml"));
                            Parent root;
                            try {
                                root = loader.load();
                                ModifyProductController controller = loader.getController();
                                controller.initData(selectedProduct);
                                Stage stage = new Stage();
                                stage.setScene(new Scene(root));
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(ProductListController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(modifyButton);
                        }
                    }
                };
            });

            tbProducts.getColumns().add(modifyColumn);
        }

        // Load voyages from the database
        List<Product> list = ps.recuperer();
        System.out.println(list);
        ObservableList<Product> observableList = FXCollections.observableArrayList(list);
        tbProducts.setItems(observableList);

    }

    @FXML
    private void Create(ActionEvent event) {
        try {
            // Load GestionVoyage.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateProduct.fxml"));
            Parent root = loader.load();

            // Get the stage from the event source
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            // Create a new scene with GestionVoyage.fxml content and set it to the stage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshTable() {
        try {
            System.out.println("gui.ProductListController.refreshTable()");
            productsList = new ProductService().recuperer();
            tbProducts.setItems(FXCollections.observableArrayList(productsList));
        } catch (SQLException ex) {
            Logger.getLogger(ProductListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ProductListController getInstance() {
        return instance;
    }
}
