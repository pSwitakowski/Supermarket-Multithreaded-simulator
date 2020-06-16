package SupermarketPackage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static int N=2; //amount of opened cashiers ATM
    public static int C = 20; //amount of clients that want to enter the shop
    public static int K = 2; //amount of clients needed to open a new cashier
    public static int M = 4; //sum of all cashiers in the supermarket
    public static int supermarketCapacity=20;
    public static int currentlyInShopClients=0;


    @Override
    public void start(Stage primaryStage) {
        Scene supermarketScene = GUI.drawGUI();

        primaryStage.setTitle("Multithreaded Supermarket Simulation");
        primaryStage.setScene(supermarketScene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


    public static void startSimulation() {

        GUI.updateData();

        Client[] clientsArray = new Client[C];
        Supermarket supermarket = new Supermarket();

        for (int i = 0; i < C; i++) {
            clientsArray[i] = new Client("Client-" + i, supermarket);
            clientsArray[i].start();
        }
    }
}
