package walletData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class DBmethods {
    public static int genPubKey(){
        int pubkey = (int)(Math.random()*((999999-100000 + 1))+100000);

        String query = String.format("SELECT * from `users` WHERE `publickey` = '%d'",pubkey);
        try{
            ResultSet set =
                    DBConnect.getStatement()
                            .executeQuery(query);
            if(set.next()){
                return genPubKey();
            }
            set.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
       return pubkey;

    }

    public static int genPriKey(){
        int pubkey = (int)(Math.random()*((999999-100000 + 1))+100000);

        String query = String.format("SELECT * from `users` WHERE `publickey` = '%d'",pubkey);
        try{
            ResultSet set =
                    DBConnect.getStatement()
                            .executeQuery(query);
            if(set.next()){
                return genPubKey();
            }
            set.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return pubkey;
    }


}
