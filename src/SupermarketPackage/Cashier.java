package SupermarketPackage;

import javafx.application.Platform;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import java.util.concurrent.Semaphore;

public class Cashier extends Thread {
    public Semaphore cashierSemaphore;
    public boolean isOpened;

    public StackPane cashierShape;
    public Rectangle cashierRect;
    public Text cashierNameText;
    private static int cashierWidth=75;
    private static int cashierHeight=25;

    public Cashier(String name) {
        super(name);
        this.cashierSemaphore = new Semaphore(0,true);
        this.isOpened = false;

        this.cashierRect = new Rectangle(cashierWidth,cashierHeight);
        this.cashierNameText = new Text(name);
        this.cashierShape = new StackPane(cashierRect, cashierNameText);
    }

    public void run() {
        try {

            Platform.runLater(()->{
                if(this.isOpened)
                    GUI.setCashierColor(this, Color.GREEN);
                else GUI.setCashierColor(this, Color.RED);
                GUI.addCashier(this);
            });

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



    public synchronized void handleClient(Client client) throws InterruptedException {
        System.out.println(client.getName() + " is being handled by " + this.getName() + " for 3 seconds...");

            Platform.runLater(() -> {
                GUI.setCashierColor(this, Color.PEACHPUFF);
                GUI.setClientPositionAtCashier(this, client);
            });

            Thread.sleep(3000);

            Platform.runLater(() -> GUI.setCashierColor(this, Color.GREEN));
    }
}

