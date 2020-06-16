package SupermarketPackage;

import javafx.geometry.Bounds;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class GUI {
    public static VBox cashiersVBox;
    public static Pane clientsPane;
    public static Pane clientsOnCashiersPane;
    public static VBox supermarketQueueVBox;
    public static VBox cashierQueueVBox;
    public static TextField clientsCountTextField;
    public static TextField cashiersCountTextField;
    public static TextField clientsPerCashierOpenedTextField;
    public static TextField supermarketCapacityTextField;
    public static Label amountOfClientsLabel;


    final static int cashiersSpacing = 30;


    public static Scene drawGUI(){

        final String fontFamily = "Corbel";
        final int fontSize = 25;

        Label supermarketLabel = new Label("Supermarket");
        supermarketLabel.setLayoutX(466);
        supermarketLabel.setLayoutY(92);
        supermarketLabel.setFont(Font.font(fontFamily,fontSize));

        Label cashiersLabel = new Label("Cashiers");
        cashiersLabel.setLayoutX(93);
        cashiersLabel.setLayoutY(14);
        cashiersLabel.setFont(Font.font(fontFamily,fontSize));

        Label clientsCountLabel = new Label("Clients:");
        clientsCountLabel.setLayoutX(280);
        clientsCountLabel.setLayoutY(10);
        clientsCountLabel.setFont(Font.font(fontFamily,18));

        Label clientsSupermarketLabel = new Label("Clients: ");
        clientsSupermarketLabel.setLayoutX(500);
        clientsSupermarketLabel.setLayoutY(120);
        clientsSupermarketLabel.setFont(Font.font(fontFamily,16));

        amountOfClientsLabel = new Label("0");
        amountOfClientsLabel.setLayoutX(551);
        amountOfClientsLabel.setLayoutY(120);
        amountOfClientsLabel.setFont(Font.font(fontFamily,16));


        clientsCountTextField = new TextField(String.valueOf(Main.C));
        clientsCountTextField.setLayoutX(280);
        clientsCountTextField.setLayoutY(30);
        clientsCountTextField.setFont(Font.font(fontFamily,18));
        clientsCountTextField.setMaxWidth(50);

        Label cashiersCountLabel = new Label("Cashiers:");
        cashiersCountLabel.setLayoutX(380);
        cashiersCountLabel.setLayoutY(10);
        cashiersCountLabel.setFont(Font.font(fontFamily,18));

        cashiersCountTextField = new TextField(String.valueOf(Main.M));
        cashiersCountTextField.setLayoutX(380);
        cashiersCountTextField.setLayoutY(30);
        cashiersCountTextField.setFont(Font.font(fontFamily,18));
        cashiersCountTextField.setMaxWidth(50);

        Label supermarketCapacityLabel = new Label("Capacity:");
        supermarketCapacityLabel.setLayoutX(480);
        supermarketCapacityLabel.setLayoutY(10);
        supermarketCapacityLabel.setFont(Font.font(fontFamily,18));

        supermarketCapacityTextField = new TextField(String.valueOf(Main.supermarketCapacity));
        supermarketCapacityTextField.setLayoutX(480);
        supermarketCapacityTextField.setLayoutY(30);
        supermarketCapacityTextField.setFont(Font.font(fontFamily,18));
        supermarketCapacityTextField.setMaxWidth(50);

        Label clientsPerCashierOpenedLabel = new Label("K: ");
        clientsPerCashierOpenedLabel.setLayoutX(580);
        clientsPerCashierOpenedLabel.setLayoutY(10);
        clientsPerCashierOpenedLabel.setFont(Font.font(fontFamily,18));

        clientsPerCashierOpenedTextField = new TextField(String.valueOf(Main.K));
        clientsPerCashierOpenedTextField.setLayoutX(580);
        clientsPerCashierOpenedTextField.setLayoutY(30);
        clientsPerCashierOpenedTextField.setFont(Font.font(fontFamily,18));
        clientsPerCashierOpenedTextField.setMaxWidth(50);

        Separator sep1 = new Separator();
        sep1.setPrefSize(6,80);
        sep1.setLayoutX(250);
        sep1.setLayoutY(0);
        sep1.setOrientation(Orientation.VERTICAL);

        Separator sep3 = new Separator();
        sep3.setPrefSize(550,3);
        sep3.setLayoutX(250);
        sep3.setLayoutY(80);
        sep3.setOrientation(Orientation.HORIZONTAL);

        Rectangle supermarketDoors = new Rectangle();
        supermarketDoors.setWidth(15);
        supermarketDoors.setHeight(75);
        supermarketDoors.setLayoutX(715);
        supermarketDoors.setLayoutY(110);
        supermarketDoors.setStyle("-fx-fill: #f4f4f4");


        Button startButton = new Button();
        startButton.setLayoutX(687);
        startButton.setLayoutY(20);
        startButton.setPrefSize(100,30);
        startButton.setText("START");

        cashiersVBox = new VBox(cashiersSpacing);
        cashiersVBox.setLayoutX(14);
        cashiersVBox.setLayoutY(48);
        cashiersVBox.setPrefSize(227,537);
        cashiersVBox.setStyle("-fx-background-color: rgb(230,230,230,1)");

        cashierQueueVBox = new VBox(10);
        cashierQueueVBox.setLayoutX(250);
        cashierQueueVBox.setLayoutY(91);
        cashierQueueVBox.setPrefSize(50,495);
        cashierQueueVBox.setStyle("-fx-background-color: rgb(229,204,255,0.5)");

        supermarketQueueVBox = new VBox(10);
        supermarketQueueVBox.setLayoutX(740);
        supermarketQueueVBox.setLayoutY(186);
        supermarketQueueVBox.setPrefSize(50,400);
        supermarketQueueVBox.setStyle("-fx-background-color: rgb(237,216,212,1)");

        clientsOnCashiersPane = new Pane();
        clientsOnCashiersPane.setLayoutX(14);
        clientsOnCashiersPane.setLayoutY(48);
        clientsOnCashiersPane.setPrefSize(227,533);

        clientsPane = new Pane();
        clientsPane.setLayoutX(310);
        clientsPane.setLayoutY(91);
        clientsPane.setPrefSize(420,495);
        clientsPane.setStyle("-fx-background-color: rgb(220,220,220,1)");

        startButton.setOnAction(event -> {

            Main.startSimulation();

            clientsCountTextField.setDisable(true);
            clientsPerCashierOpenedTextField.setDisable(true);
            cashiersCountTextField.setDisable(true);
            startButton.setDisable(true);
            supermarketCapacityTextField.setDisable(true);
        });

        Pane root = new Pane();
        root.getChildren().addAll(cashiersLabel,sep1,sep3,startButton, cashiersVBox, clientsPane, supermarketLabel,clientsOnCashiersPane, supermarketQueueVBox,cashierQueueVBox,supermarketDoors,clientsCountLabel,clientsPerCashierOpenedLabel,cashiersCountLabel,clientsSupermarketLabel,supermarketCapacityLabel,amountOfClientsLabel,clientsCountTextField,cashiersCountTextField,supermarketCapacityTextField,clientsPerCashierOpenedTextField);

        Scene supermarketScene = new Scene(root, 800, 600);

        return supermarketScene;
    }


    public static void addCashier(Cashier cashier){
        if(!cashiersVBox.getChildren().contains(cashier.cashierShape))
            cashiersVBox.getChildren().add(cashier.cashierShape);
    }

    public static void addClient(Client client){
        if(!clientsPane.getChildren().contains(client.clientShape))
        clientsPane.getChildren().add(client.clientShape);
    }

    public static void setClientPositionAtCashier(Cashier cashier, Client client){
        clientsPane.getChildren().remove(client);

        Bounds cashierBounds = cashier.cashierShape.getBoundsInParent();

        client.clientShape.setLayoutX(cashierBounds.getMinX() + cashierBounds.getWidth()/2 + 30);
        client.clientShape.setLayoutY(cashierBounds.getMinY());

        clientsOnCashiersPane.getChildren().add(client.clientShape);
    }

    public static void setCashierColor(Cashier cashier, Color color){
        cashier.cashierRect.setFill(color);
    }

    public static void removeClientFromShop(Client client){
        if(clientsPane.getChildren().contains(client.clientShape))
        clientsPane.getChildren().remove(client.clientShape);
        else clientsOnCashiersPane.getChildren().remove(client.clientShape);
    }

    public static void putClientAtCashierQueue(Client client){
        if(clientsPane.getChildren().contains(client.clientShape)){
            clientsPane.getChildren().remove(client.clientShape);
            cashierQueueVBox.getChildren().add(client.clientShape);
        }
    }

    public static void putClientAtSupermarketQueue(Client client){
            if(!supermarketQueueVBox.getChildren().contains(client.clientShape))
            supermarketQueueVBox.getChildren().add(client.clientShape);
        }

    public static void updateAmountOfClients(int amountOfClients){
        amountOfClientsLabel.setText(String.valueOf(amountOfClients));
    }

    public static void updateData(){
        Main.C = Integer.parseInt(clientsCountTextField.getText());
        Main.M = Integer.parseInt(GUI.cashiersCountTextField.getText());
        Main.K = Integer.parseInt(GUI.clientsPerCashierOpenedTextField.getText());
        Main.supermarketCapacity = Integer.parseInt(GUI.supermarketCapacityTextField.getText());
    }

}

