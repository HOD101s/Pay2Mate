package walletData.dbs;

import walletData.Query.Execute;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Transactions {

    private static int userBalance;
    private static int senderprivatekey;
    private static int receiverpublickey;
    private static int sendamount;

    public static int verification(int prikey, String user, int pubkey) throws SQLException {
        PreparedStatement priCheck = DBConnect.getConn().prepareStatement(Execute.priCheck);
        priCheck.setString(1,user);
        priCheck.setInt(2,prikey);
        ResultSet set = priCheck.executeQuery();
        if (set.next()) {
            senderprivatekey = prikey;
            return pubkeyCheck(pubkey);
        } else {
            return 0;
        }
    }

    private static int pubkeyCheck(int pubkey) throws SQLException {
        PreparedStatement pubCheck = DBConnect.getConn().prepareStatement(Execute.pubCheck);
        pubCheck.setInt(1,pubkey);
        ResultSet set = pubCheck.executeQuery();
        if (set.next()) {
            receiverpublickey = pubkey;
            return 1;
        } else {
            return 2;
        }
    }

    public static boolean amountInBalance(int send, int prikey) throws SQLException {
        PreparedStatement amtInBal = DBConnect.getConn().prepareStatement(Execute.amountInBal);
        amtInBal.setInt(1,prikey);
        ResultSet myBal = amtInBal.executeQuery();
        myBal.next();
        int balance = myBal.getInt("balance");
        userBalance = balance;
        if (balance < send)
            return false;
        else {
            sendamount = send;
            return true;
        }
    }

    public static boolean removeMoney() throws SQLException {
        PreparedStatement remMoney = DBConnect.getConn().prepareStatement(Execute.removeMoney);
        remMoney.setInt(1,userBalance - sendamount);
        remMoney.setInt(2,senderprivatekey);
        return (remMoney.executeUpdate()>0);
    }

    public static boolean addMoney() throws SQLException {
        PreparedStatement addMoney = DBConnect.getConn().prepareStatement(Execute.addMoney);
        addMoney.setInt(1,sendamount);
        addMoney.setInt(2,receiverpublickey);
        return (addMoney.executeUpdate()>0);
    }

    private static String genTimestamp(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmm");
        return  sdf.format(timestamp);
    }

    private static String tstamp = genTimestamp();

    public static String genTransID() throws SQLException {
        String myTran = tstamp + randomAlphaNumeric();
        if (checkTrans(myTran))
            return genTransID();
        else
            return myTran;
    }

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static String randomAlphaNumeric() {
        StringBuilder builder = new StringBuilder();
        int count = 6;
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    private static boolean checkTrans(String s) throws SQLException {
        PreparedStatement chTrId = DBConnect.getConn().prepareStatement(Execute.checkTransID);
        chTrId.setString(1,s);
        return chTrId.executeQuery().next();
    }

    public static String getTime() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); //0-11  12-17 18-23
        return String.format("%s", sdf.format(timestamp));
    }

    public static String getDate() {
        Timestamp datestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy"); //0-11  12-17 18-23
        return String.format("%s", sdf.format(datestamp));
    }
}