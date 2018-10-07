package walletData.Query;

public class Execute {
    public static final String getKey = "SELECT `publickey` FROM `userdata` WHERE `username` = '%s'";
    public static final String getBal = "SELECT `balance` FROM `userwallet` WHERE `publickey` = '%d'";
    public static final String setTable = "SELECT `transid`,`senderpub`,`receiverpub`,`time`,`amount` FROM `userdata` JOIN `transaction` ON `publickey` = `senderpub` WHERE `senderpub`='%d'";
    public static final String loginQuery = "SELECT * FROM `userdata` WHERE `username` = '%s' && `password` = '%s'";
    public static final String userExists = "SELECT * FROM `userdata` WHERE username = '%s'";
    public static final String insertUserData = "INSERT INTO `userdata` (`publickey`,`username`,`password`) VALUES ('%d','%s','%s')";
    public static final String insertUserWallet = "INSERT INTO `userwallet` (`publickey`,`privatekey`,`balance`) VALUES ('%d','%d','%d')";
    public static final String transTableInsert = "INSERT INTO `transaction` (`transid`,`senderpub`,`receiverpub`,`amount`,`time`,`date`) VALUES ('%s','%s','%d','%d','%s','%s')";
    public static final String getSenderPub = "SELECT * FROM `userwallet` WHERE `privatekey` = '%s'";
    public static final String genPub = "SELECT * from `userwallet` WHERE `publickey` = '%d'";
    public static final String genPri = "SELECT * from `userwallet` WHERE `privatekey` = '%d'";
    public static final String priCheck = "SELECT `username`,`privatekey` FROM `userdata` JOIN `userwallet` ON userdata.publickey = userwallet.publickey WHERE `username` = '%s' && `privatekey` = '%d'";
    public static final String pubCheck = "SELECT * from `userdata` WHERE `publickey` = '%d'";
    public static final String amountInBal = "SELECT `balance` from `userwallet` WHERE `privatekey` = '%s'";
    public static final String removeMoney = "UPDATE `userwallet` SET `balance` = '%d' WHERE `privatekey` = '%d'";
    public static final String addMoney = "UPDATE `userwallet` SET `balance` = balance + %d WHERE `publickey` = '%d'";
    public static final String checkTransID = "SELECT * FROM `transaction` WHERE `transid` = '%s'";

    //admin
    public static final String adminInsert ="INSERT INTO `adminreq`(`publickey`, `request`) VALUES ('%d','%d')";
    public static final String pubinsert = "SELECT `publickey` FROM `adminreq`";
    public static final String reqinsert = "SELECT `request` FROM `adminreq`";
    //setupdb

    public static final String createTransaction = "CREATE TABLE `pay2mate`.`transaction` ( `transid` VARCHAR(255) NOT NULL ,  `senderpub` INT(255) NOT NULL ,  `receiverpub` INT(255) NOT NULL ,  `amount` INT(11) NOT NULL ,  `time` VARCHAR(255) NOT NULL ,  `date` VARCHAR(255) NOT NULL ) ENGINE = InnoDB";
    public static final String createuserData = "CREATE TABLE `pay2mate`.`userdata` ( `publickey` INT(11) NOT NULL , `username` VARCHAR(255) NOT NULL , `password` VARCHAR(255) NOT NULL ) ENGINE = InnoDB";
    public static final String createuserwallet = "CREATE TABLE `pay2mate`.`userwallet` ( `publickey` INT(11) NOT NULL , `privatekey` INT(11) NOT NULL , `balance` INT(255) NOT NULL ) ENGINE = InnoDB";
    public static final String createPay2Mate = "CREATE DATABASE pay2mate";
    public static final String doesPay2MateExist = "show databases like 'pay2mate'";
    public static final String usePay2 = "USE pay2mate";
    public static final String adminReq = "CREATE TABLE `pay2mate`.`adminreq` ( `publickey` INT(11) NOT NULL , `request` TEXT NOT NULL ) ENGINE = InnoDB";
}
