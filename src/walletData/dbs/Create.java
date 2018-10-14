package walletData.dbs;

import walletData.Query.Execute;

import java.sql.SQLException;

public class Create {
    public static void create() {

        try {
            if (!DBConnect.getStatement().executeQuery(Execute.doesPay2MateExist).next()) {
                DBConnect.getStatement().execute(Execute.createPay2Mate);
                DBConnect.getStatement().execute(Execute.usePay2);
                DBConnect.getStatement().execute(Execute.createTransaction);
                DBConnect.getStatement().execute(Execute.createuserData);
                DBConnect.getStatement().execute(Execute.createuserwallet);
                DBConnect.getStatement().execute(Execute.adminReq);
                DBConnect.getStatement().execute(Execute.admin);
            } else{
                DBConnect.getStatement().execute(Execute.usePay2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
