package walletData.dbs;

import walletData.Query.Execute;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBmethods {

    public static int genPubKey() throws SQLException{
        int pubkey = (int)(Math.random()*((999999-100000 + 1))+100000);
        PreparedStatement genPub = DBConnect.getConn().prepareStatement(Execute.genPub);
        genPub.setInt(1,pubkey);
        ResultSet set = genPub.executeQuery();
            if(set.next()){
                return genPubKey();
            }
            set.close();

       return pubkey;
    }

    public static int genPriKey() throws SQLException{
        int prikey = (int)(Math.random()*((999999-100000 + 1))+100000);
        PreparedStatement genPri = DBConnect.getConn().prepareStatement(Execute.genPri);
        genPri.setInt(1,prikey);
        ResultSet set = genPri.executeQuery();
            if(set.next()){
                return genPriKey();
            }
            set.close();
        return prikey;
    }
}
