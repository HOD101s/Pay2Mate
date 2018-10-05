package walletData.dbs;

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
        String priCheck = String.format("SELECT * FROM `userdata` JOIN `transaction` WHERE `username` = '%s' ", user);
        ResultSet set =
                DBConnect.getStatement()
                        .executeQuery(priCheck);
        if (set.next()) {
            senderprivatekey = prikey;
            return pubkeyCheck(pubkey);
        } else {
            return 0;
        }
    }

    private static int pubkeyCheck(int pubkey) throws SQLException {
        String pubCheck = String.format("SELECT * from `userdata` WHERE `publickey` = '%d'", pubkey);
        ResultSet set =
                DBConnect.getStatement()
                        .executeQuery(pubCheck);
        if (set.next()) {
            receiverpublickey = pubkey;
            return 1;
        } else {
            return 2;
        }
    }

    public static boolean amountInBalance(int send, int prikey) throws SQLException {
        String query = String.format("SELECT `balance` from `userwallet` WHERE `privatekey` = '%s'", prikey);
        ResultSet myBal = DBConnect.getStatement().executeQuery(query);
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
        String query = String.format("UPDATE `userwallet` SET `balance` = '%d' WHERE `privatekey` = '%d'", userBalance - sendamount, senderprivatekey);
        return (DBConnect.getStatement().executeUpdate(query) > 0);
    }

    public static boolean addMoney() throws SQLException {
        int bal;
        String querybal = String.format("SELECT `balance` FROM `userwallet` WHERE `publickey` = '%s'", receiverpublickey);
        ResultSet mysetbal = DBConnect.getStatement().executeQuery(querybal);
        if (mysetbal.next())
            bal = mysetbal.getInt("balance");
        else
            bal = 0;
        String myquery = String.format("UPDATE `userwallet` SET `balance` = '%d' WHERE `publickey` = '%d'", bal + sendamount, receiverpublickey);
        return (DBConnect.getStatement().executeUpdate(myquery) > 0);
    }

    public static String genTransID() throws SQLException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmm");
        String myTran = sdf.format(timestamp) + randomAlphaNumeric(6);
        if (checkTrans(myTran))
            return genTransID();
        else
            return myTran;
    }

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    private static boolean checkTrans(String s) throws SQLException {
        String query = String.format("SELECT * FROM `transaction` WHERE `transid` = '%s'", s);
        ResultSet tranSet = DBConnect.getStatement().executeQuery(query);
        return tranSet.next();
    }

    public static String getTime() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); //0-11  12-17 18-23
        return String.format("%s", sdf.format(timestamp));
    }
}
