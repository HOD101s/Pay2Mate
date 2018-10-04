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
            }
            set.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
       // return pubkey;
        System.out.println(pubkey);
        return 0;

    }

    public static int genPriKey(){
        int prikey = (int)(Math.random()*((999999-100000 + 1))+100000);
        String query = String.format("SELECT * from `sessionTable` WHERE `Private Key` = '%d'",prikey);
        try{
            ResultSet set =
                    DBConnect.getStatement()
                            .executeQuery(query);
            if(set.next()){
                return genPubKey();
            }
            //set.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        //return prikey;
        System.out.println(prikey);
        return 0;
    }


}
