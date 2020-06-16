package SupermarketPackage;


import javafx.application.Platform;
import javafx.scene.paint.Color;
import java.util.concurrent.Semaphore;

public class Supermarket
{
    public Cashier[] cashierArray = new Cashier[Main.M];
    public Semaphore supermarketSemaphore;

    public Supermarket() {
        this.supermarketSemaphore = new Semaphore(Main.supermarketCapacity,true);

        for (int i = 0; i < Main.M; i++) {
            if (i == 0 || i == 1) {
                cashierArray[i] = new Cashier("Cashier-" + i);
                cashierArray[i].cashierSemaphore.release();
                cashierArray[i].isOpened=true;
                cashierArray[i].start();
            } else {
                cashierArray[i] = new Cashier("Cashier-" + i);
                cashierArray[i].start();
            }
        }

    }

    public synchronized void updateAmountOfCashiersOpened(){

        if(Main.currentlyInShopClients <= 2*Main.K) {
            Main.N = 2;
        }
        else {
            Main.N = Main.currentlyInShopClients / Main.K;
        }

        int currentlyOpenedCashiers=0;
        for(Cashier c : cashierArray){
            if(c.isOpened){
                currentlyOpenedCashiers++;
            }
        }

        if(currentlyOpenedCashiers < Main.N){
            for(int i=2;i<=Main.M-1;i++){
                if(!this.cashierArray[i].isOpened) {

                    this.cashierArray[i].cashierSemaphore.release();
                    this.cashierArray[i].isOpened = true;

                    final int finalI = i;
                    Platform.runLater(() -> GUI.setCashierColor(this.cashierArray[finalI], Color.GREEN));

                    currentlyOpenedCashiers++;
                }
                if(currentlyOpenedCashiers == Main.N) break;
            }
        }
        if(currentlyOpenedCashiers > Main.N){
            for(int i=Main.M-1;i>=2;i--){
                if(this.cashierArray[i].isOpened) {
                    try {

                        this.cashierArray[i].cashierSemaphore.acquire();
                        this.cashierArray[i].isOpened = false;

                        final int finalI = i;
                        Platform.runLater(() -> GUI.setCashierColor(this.cashierArray[finalI], Color.RED));
                        currentlyOpenedCashiers--;

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(currentlyOpenedCashiers == Main.N) break;
            }
        }
    }

    public synchronized void increaseClientsCount() {
        Main.currentlyInShopClients++;
        Platform.runLater(()-> GUI.updateAmountOfClients(Main.currentlyInShopClients));
    }

    public synchronized void decreaseClientsCount() {
        Main.currentlyInShopClients--;
        Platform.runLater(()-> GUI.updateAmountOfClients(Main.currentlyInShopClients));
    }

    public Cashier getFirstAvailableCashier(){
        Cashier firstOpenedCashier=null;
            for (Cashier c : cashierArray) {
                if (c.cashierSemaphore.availablePermits() > 0) {
                    firstOpenedCashier = c;
                    break;
                }
            }
        return firstOpenedCashier;
    }

    public synchronized void increaseAmountOfOpenedCashiers() {
        Main.N++;
    }

    public synchronized void decreaseAmountOfOpenedCashiers() {
        Main.N--;
    }
}