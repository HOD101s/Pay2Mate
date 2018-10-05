package walletData.dbs;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBmethods {

    public static int genPubKey() throws SQLException{
        int pubkey = (int)(Math.random()*((999999-100000 + 1))+100000);
        String query = String.format("SELECT * from `userdata` WHERE `publickey` = '%d'",pubkey);
            ResultSet set =
                    DBConnect.getStatement()
                            .executeQuery(query);
            if(set.next()){
                return genPubKey();
            }
            set.close();

       return pubkey;
    }

    public static int genPriKey() throws SQLException{
        int pubkey = (int)(Math.random()*((999999-100000 + 1))+100000);
        String query = String.format("SELECT * from `userdata` WHERE `publickey` = '%d'",pubkey);
            ResultSet set =
                    DBConnect.getStatement()
                            .executeQuery(query);
            if(set.next()){
                return genPubKey();
            }
            set.close();
        return pubkey;
    }
}
