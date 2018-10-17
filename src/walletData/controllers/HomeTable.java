package walletData.controllers;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class HomeTable  extends RecursiveTreeObject<HomeTable> {

    public SimpleStringProperty transID = new SimpleStringProperty();
    public SimpleIntegerProperty sender = new SimpleIntegerProperty();
    public SimpleIntegerProperty receiver = new SimpleIntegerProperty();
    public SimpleIntegerProperty amount = new SimpleIntegerProperty();
    public SimpleStringProperty time = new SimpleStringProperty();
    public SimpleStringProperty date = new SimpleStringProperty();

    public String getTransID() {
        return transID.get();
    }

    public SimpleStringProperty transIDProperty() {
        return transID;
    }

    public void setTransID(String transID) {
        this.transID.set(transID);
    }

    public int getSender() {
        return sender.get();
    }

    public SimpleIntegerProperty senderProperty() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender.set(sender);
    }

    public int getReceiver() {
        return receiver.get();
    }

    public SimpleIntegerProperty receiverProperty() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver.set(receiver);
    }

    public int getAmount() {
        return amount.get();
    }

    public SimpleIntegerProperty amountProperty() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
    }

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }
}
