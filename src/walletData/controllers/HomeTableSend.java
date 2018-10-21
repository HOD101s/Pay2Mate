package walletData.controllers;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class HomeTableSend extends RecursiveTreeObject<HomeTableSend> {
    public SimpleStringProperty transIDsend = new SimpleStringProperty();
    public SimpleIntegerProperty sendersend = new SimpleIntegerProperty();
    public SimpleIntegerProperty receiversend = new SimpleIntegerProperty();
    public SimpleStringProperty amountsend = new SimpleStringProperty();
    public SimpleStringProperty timesend = new SimpleStringProperty();
    public SimpleStringProperty datesend = new SimpleStringProperty();

    public String getTransIDsend() {
        return transIDsend.get();
    }

    public SimpleStringProperty transIDsendProperty() {
        return transIDsend;
    }

    public void setTransIDsend(String transIDsend) {
        this.transIDsend.set(transIDsend);
    }

    public int getSendersend() {
        return sendersend.get();
    }

    public SimpleIntegerProperty sendersendProperty() {
        return sendersend;
    }

    public void setSendersend(int sendersend) {
        this.sendersend.set(sendersend);
    }

    public int getReceiversend() {
        return receiversend.get();
    }

    public SimpleIntegerProperty receiversendProperty() {
        return receiversend;
    }

    public void setReceiversend(int receiversend) {
        this.receiversend.set(receiversend);
    }

    public String getAmountsend() {
        return amountsend.get();
    }

    public SimpleStringProperty amountsendProperty() {
        return amountsend;
    }

    public void setAmountsend(String amountsend) {
        this.amountsend.set(amountsend);
    }

    public String getTimesend() {
        return timesend.get();
    }

    public SimpleStringProperty timesendProperty() {
        return timesend;
    }

    public void setTimesend(String timesend) {
        this.timesend.set(timesend);
    }

    public String getDatesend() {
        return datesend.get();
    }

    public SimpleStringProperty datesendProperty() {
        return datesend;
    }

    public void setDatesend(String datesend) {
        this.datesend.set(datesend);
    }


}
