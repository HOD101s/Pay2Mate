package walletData;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Transactions {
    private static int userBalance;
    private static int senderprivatekey;
    private static int receiverpublickey;
    private static int sendamount;

    public static int verification(int prikey ,String user , int pubkey){

        String priCheck = String.format("SELECT * from `` WHERE `username` = '%s' `Private Key` = '%d'",user,prikey);
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
            set.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static int pubkeyCheck(int pubkey){
        String pubCheck = String.format("SELECT * from `` WHERE `Public Key` = '%d'",pubkey);
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
            set.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
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
    }

    public static boolean removeMoney(){
        String query = String.format("UPDATE `` SET `balance` = '%d' WHERE `Private Key` = '%d'",userBalance-sendamount,senderprivatekey);
        try {
            DBConnect.getStatement().executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static boolean addMoney(){
        String query = String.format("UPDATE `` SET `balance` = 'balance + %d' WHERE `Public Key` = '%d'",sendamount,receiverpublickey);
        try {
            DBConnect.getStatement().executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
