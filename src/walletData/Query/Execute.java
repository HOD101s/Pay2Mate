package walletData.Query;

public class Execute {
    public static final String getKey = "SELECT `publickey` FROM `userdata` WHERE `username` = ?";
    public static final String getBal = "SELECT `balance` FROM `userwallet` WHERE `publickey` = ?";
    public static final String homeTableReceive = "SELECT * FROM `transaction` WHERE `receiverpub` = ?";
    public static final String homeTableSend = "SELECT * FROM `transaction` WHERE `senderpub` = ? ";
    public static final String loginQuery = "SELECT * FROM `userdata` WHERE `username` = ? && `password` = ?";
    public static final String userExists = "SELECT * FROM `userdata` WHERE username = ?";
    public static final String insertUserData = "INSERT INTO `userdata` (`accType`,`publickey`,`username`,`password`) VALUES (\'0\',?,?,?)";
    public static final String insertUserWallet = "INSERT INTO `userwallet` (`publickey`,`privatekey`,`balance`) VALUES (?,?,?)";
    public static final String transTableInsert = "INSERT INTO `transaction` (`transid`,`senderpub`,`receiverpub`,`amount`,`time`,`date`) VALUES (?,?,?,?,?,?)";
    public static final String getSenderPub = "SELECT * FROM `userwallet` WHERE `privatekey` = ?";
    public static final String genPub = "SELECT * from `userwallet` WHERE `publickey` = ?";
    public static final String genPri = "SELECT * from `userwallet` WHERE `privatekey` = ?";
    public static final String priCheck = "SELECT `username`,`privatekey` FROM `userdata` JOIN `userwallet` ON userdata.publickey = userwallet.publickey WHERE `username` = ? && `privatekey` = ?";
    public static final String pubCheck = "SELECT * from `userdata` WHERE `publickey` = ?";
    public static final String amountInBal = "SELECT `balance` from `userwallet` WHERE `privatekey` = ?";
    public static final String removeMoney = "UPDATE `userwallet` SET `balance` = ? WHERE `privatekey` = ?";
    public static final String addMoney = "UPDATE `userwallet` SET `balance` = balance + ? WHERE `publickey` = ?";
    public static final String checkTransID = "SELECT * FROM `transaction` WHERE `transid` = ?";

    //admin
    public static final String adminInsert ="INSERT INTO `adminreq`(`publickey`, `request`) VALUES (?,?)";
    public static final String pubreqinsert = "SELECT * FROM `adminreq`";
    public static final String delReq = "DELETE FROM `adminreq` WHERE `publickey` = ? && `request` = ? LIMIT 1";
    public static final String getAdminKey = "SELECT `publickey` FROM `userdata` WHERE `accType` = 1 && `username` = ? LIMIT 1";

    //setupdbs
    public static final String createTransaction = "CREATE TABLE `pay2mate`.`transaction` ( `transid` VARCHAR(255) NOT NULL ,  `senderpub` INT(255) NOT NULL ,  `receiverpub` INT(255) NOT NULL ,  `amount` INT(11) NOT NULL ,  `time` VARCHAR(255) NOT NULL ,  `date` VARCHAR(255) NOT NULL ) ENGINE = InnoDB";
    public static final String createuserData = "CREATE TABLE `pay2mate`.`userdata` ( `accType` INT(11) NOT NULL ,`publickey` INT(11) NOT NULL , `username` VARCHAR(255) NOT NULL , `password` VARCHAR(255) NOT NULL ) ENGINE = InnoDB";
    public static final String createuserwallet = "CREATE TABLE `pay2mate`.`userwallet` ( `publickey` INT(11) NOT NULL , `privatekey` INT(11) NOT NULL , `balance` INT(255) NOT NULL ) ENGINE = InnoDB";
    public static final String createPay2Mate = "CREATE DATABASE pay2mate";
    public static final String doesPay2MateExist = "show databases like 'pay2mate'";
    public static final String usePay2 = "USE pay2mate";
    public static final String adminReq = "CREATE TABLE `pay2mate`.`adminreq` ( `requestID` INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT ,`publickey` VARCHAR(255) NOT NULL , `request` VARCHAR(255) NOT NULL ) ENGINE = InnoDB";
    public static final String admin = "INSERT INTO `userdata` (`accType`, `publickey`, `username`, `password`) VALUES (\'1\', \'000000\', \'admin\', \'b4402e4c8d7c08b508f1bf77ae62b9c4\')";
}
