package walletData.dbs;

import walletData.Query.Execute;

import java.sql.SQLException;

public class Create {
    public static void create() {

        try {
            if(!DBConnect.getStatement().executeQuery("show databases like 'pay2mate'").next()) {
                DBConnect.getStatement().execute("CREATE DATABASE pay2mate");
                DBConnect.getStatement().execute("USE pay2mate");
                DBConnect.getStatement().execute(Execute.createTransaction);
                DBConnect.getStatement().execute(Execute.createuserData);
                DBConnect.getStatement().execute(Execute.createuserwallet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
