package SupermarketPackage;

import javafx.application.Platform;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import java.util.concurrent.ThreadLocalRandom;


public class Client extends Thread{

    private Supermarket supermarket;

    public StackPane clientShape;
    public Circle clientCircle;
    public Text clientNameText;
    public static int clientRadius = 10;
    public LinearGradient clientGradient;
    Stop[] stops;

    public Client(String name, Supermarket supermarket) {
        super(name);
        this.supermarket = supermarket;
        this.clientCircle = new Circle(clientRadius);
        this.stops = new Stop[]{new Stop(0, new Color(ThreadLocalRandom.current().nextFloat(), ThreadLocalRandom.current().nextFloat(), ThreadLocalRandom.current().nextFloat(), 1)),new Stop(1, new Color(ThreadLocalRandom.current().nextFloat(), ThreadLocalRandom.current().nextFloat(), ThreadLocalRandom.current().nextFloat(), 1))};
        this.clientGradient = new LinearGradient(0,0,1,1,true, CycleMethod.REFLECT,stops);
        this.clientCircle.setFill(clientGradient);
        this.clientNameText = new Text(name);
        this.clientShape = new StackPane(clientCircle,clientNameText);
    }

    public void run() {
        waitBeforeEnteringSupermarket();
        try {
                tryToEnterSupermarket();

                supermarket.increaseClientsCount();
                supermarket.updateAmountOfCashiersOpened();


                System.out.println(this.getName() + " has entered the shop. Number of clients: " + Main.currentlyInShopClients);

                buyThings();

                goToCashiers();

                supermarket.supermarketSemaphore.release();
                supermarket.decreaseClientsCount();
                supermarket.updateAmountOfCashiersOpened();

                System.out.println(this.getName() + " has left the shop. Number of clients: " + Main.currentlyInShopClients);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private synchronized void goToCashiers() throws InterruptedException {

        Cashier firstOpenedCashier = supermarket.getFirstAvailableCashier();

        if(firstOpenedCashier==null) {

            Platform.runLater(()-> GUI.putClientAtCashierQueue(this));
            Thread.sleep(500);
            goToCashiers();

        }else{
                firstOpenedCashier.cashierSemaphore.acquire();
                supermarket.decreaseAmountOfOpenedCashiers();

                firstOpenedCashier.handleClient(this);

                Platform.runLater(() -> GUI.removeClientFromShop(this));

                firstOpenedCashier.cashierSemaphore.release();
                supermarket.increaseAmountOfOpenedCashiers();

                System.out.println(this.getName() + " has been handled by " + firstOpenedCashier.getName() + ". Exiting the shop...");
        }
    }

    private void waitBeforeEnteringSupermarket(){
        int waitingTime = ThreadLocalRandom.current().nextInt(1000,10001);
        try{
            Thread.sleep(waitingTime);
        }  catch(Exception e) {
            System.out.println(e.getMessage());}
    }

    private void tryToEnterSupermarket() throws InterruptedException {
        if(supermarket.supermarketSemaphore.availablePermits()>0){

            supermarket.supermarketSemaphore.acquire();

            Platform.runLater(()->{
                this.clientShape.setLayoutX(ThreadLocalRandom.current().nextDouble(this.clientCircle.getRadius() * 4, GUI.clientsPane.getWidth()-50));
                this.clientShape.setLayoutY(ThreadLocalRandom.current().nextDouble(this.clientCircle.getRadius() * 4, GUI.clientsPane.getHeight()-50));
                GUI.addClient(this);
            });

        }else{
            Platform.runLater(()-> GUI.putClientAtSupermarketQueue(this));

            Thread.sleep(5000);
            tryToEnterSupermarket();
        }
    }

    private void buyThings()
    {
        int shoppingTime = ThreadLocalRandom.current().nextInt(1000,10001);
        System.out.println(this.getName() + " will be shopping for " + shoppingTime/1000 + " seconds");

        try{
            Thread.sleep(shoppingTime);
        }  catch(Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(this.getName() + " is done shopping. Going to cashiers now...");
    }

}

