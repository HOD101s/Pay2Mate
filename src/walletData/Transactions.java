package walletData;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Transactions {
    public static int verification(int prikey ,String user , int pubkey){
        String priCheck = String.format("SELECT * from `` WHERE `username` = '%s' `Private Key` = '%d'",user,prikey);
        try{
            ResultSet set =
                    DBConnect.getStatement()
                            .executeQuery(priCheck);
            if(set.next()){
                return 0;
            }else{
              return  pubkeyCheck(pubkey);
            }
            set.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static int pubkeyCheck(int pubkey){
        String priCheck = String.format("SELECT * from `` WHERE ",pubkey);
        try{
            ResultSet set =
                    DBConnect.getStatement()
                            .executeQuery(query);
            if(set.next()){
                return 1;
            }else{
                return 1;
            }
            set.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }




}
