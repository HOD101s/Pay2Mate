package walletData.controllers;


import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

public class ModelTable {
    public SimpleStringProperty publickey = new SimpleStringProperty();
    public SimpleStringProperty request = new SimpleStringProperty();
    public CheckBox update = new CheckBox();

    public CheckBox getUpdate() {
        return update;
    }

    public void setUpdate(CheckBox update) {
        this.update = update;
    }


    public String getPublickey() {
        return publickey.get();
    }

    public SimpleStringProperty publickeyProperty() {
        return publickey;
    }

    public void setPublickey(String publickey) {
        this.publickey.set(publickey);
    }

    public String getRequest() {
        return request.get();
    }

    public SimpleStringProperty requestProperty() {
        return request;
    }

    public void setRequest(String request) {
        this.request.set(request);
    }
}