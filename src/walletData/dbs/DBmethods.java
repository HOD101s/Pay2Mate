package walletData.dbs;

import walletData.Query.Execute;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBmethods {

    public static int genPubKey() throws SQLException{
        int pubkey = (int)(Math.random()*((999999-100000 + 1))+100000);
            ResultSet set =
                    DBConnect.getStatement()
                            .executeQuery(String.format(Execute.genPub,pubkey));
            if(set.next()){
                return genPubKey();
            }
            set.close();

       return pubkey;
    }

    public static int genPriKey() throws SQLException{
        int prikey = (int)(Math.random()*((999999-100000 + 1))+100000);
            ResultSet set =
                    DBConnect.getStatement()
                            .executeQuery(String.format(Execute.genPri,prikey));
            if(set.next()){
                return genPriKey();
            }
            set.close();
        return prikey;
    }
}
