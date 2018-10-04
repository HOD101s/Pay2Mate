package walletData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Transactions {
    public static int userBalance;
    private static int senderprivatekey;
    private static int receiverpublickey;
    private static int sendamount;

    public static int verification(int prikey ,String user , int pubkey){
        String priCheck = String.format("SELECT * from `users` WHERE `username` = '%s' && `privatekey` = '%d'",user,prikey);
        try{
            ResultSet set =
                    DBConnect.getStatement()
                            .executeQuery(priCheck);
            if(set.next()){
                senderprivatekey = prikey;
                return  pubkeyCheck(pubkey);
            }else{
              return 0;
            }
            //set.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public static int pubkeyCheck(int pubkey){
        String pubCheck = String.format("SELECT * from `users` WHERE `publickey` = '%d'",pubkey);
        try{
            ResultSet set =
                    DBConnect.getStatement()
                            .executeQuery(pubCheck);
            if(set.next()){
                receiverpublickey = pubkey;
                return 1;
            }else{
                return 2;
            }
            //set.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 2;
    }

    public static boolean amountInBalance(int send,String user){
        try {
            String query = String.format("SELECT `balance` from `users` WHERE `username` = '%s'",user);
            ResultSet myBal = DBConnect.getStatement().executeQuery(query);
            myBal.next();
            int balance = myBal.getInt("balance");
            userBalance = balance;
            if(balance<send)
                return false;
            else {
                sendamount = send;
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sendamount = send;
        return true;
    }

    public static boolean removeMoney(){
        String query = String.format("UPDATE `users` SET `balance` = '%d' WHERE `privatekey` = '%d'",userBalance-sendamount,senderprivatekey);
        try {
            DBConnect.getStatement().executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean addMoney(){
        int bal;
        try{
            String querybal = String.format("SELECT `balance` FROM `users` WHERE `publickey` = '%s'", receiverpublickey);
            ResultSet mysetbal = DBConnect.getStatement().executeQuery(querybal);
            mysetbal.next();
            bal = mysetbal.getInt("balance");
        }catch(SQLException e){
            e.printStackTrace();
            bal = 00;
        }
        String myquery = String.format("UPDATE `users` SET `balance` = '%d' WHERE `publickey` = '%d'",bal+sendamount,receiverpublickey);
        try {
            DBConnect.getStatement().executeUpdate(myquery);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String genTransID(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("MMddyyHHmmss"); //0-11  12-17 18-23
        return String.format("%s%s%s%d", sdf.format(timestamp),senderprivatekey,receiverpublickey,sendamount);
    }
}
