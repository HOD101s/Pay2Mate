package walletData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class DBmethods {
    public static int genPubKey(){
        int pubkey = (int)(Math.random()*((999999-100000 + 1))+100000);

        String query = String.format("SELECT * from `users` WHERE `Public Key` = '%d'",pubkey);
        try{
            ResultSet set =
                    DBConnect.getStatement()
                            .executeQuery(query);
            if(set.next()){
                return genPubKey();
            }else{
                return pubkey;
            }
            set.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static int genPriKey(){
        int prikey = (int)(Math.random()*((999999-100000 + 1))+100000);
        String query = String.format("SELECT * from `users` WHERE `Public Key` = '%d'",prikey);
        try{
            ResultSet set =
                    DBConnect.getStatement()
                            .executeQuery(query);
            if(set.next()){
                return genPubKey();
            }else{
                return prikey;
            }
            set.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


}
