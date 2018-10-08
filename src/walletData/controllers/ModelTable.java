package walletData.controllers;

public class ModelTable{

    String tpublickey, trequest;

    public ModelTable(String tpublickey, String trequest) {
        this.tpublickey = tpublickey;
        this.trequest = trequest;
    }

    public String getTpublickey() {
        return tpublickey;
    }

    public void setTpublickey(String tpublickey) {
        this.tpublickey = tpublickey;
    }

    public String getTrequest() {
        return trequest;
    }

    public void setTrequest(String trequest) {
        this.trequest = trequest;
    }
}