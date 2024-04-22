package manage;

public class QueryManage {

    /*
    Query sorgularimizi bu class'in icine koyuyoruz
     */

    private String query01="select user_id from u168183796_qaloantec.deposits where amount between 100 and 500;";
    //Bu variable'i private yapip, diger class'lardan ulasmak icin getter methoduyla encapsule ettik.
    private String query02="SELECT name FROM u168183796_qaloantec.cron_schedules Limit 2";
    private String query03="select firstname, lastname from users where country_code not like 'TR' and id=11";

    private String query04="select user_id,group_concat(browser,'- ',os) as browser_os from user_logins group by user_id";
    private String updateQuery05= "update users set mobile= 987654321 where username like '%e_'";
    private String preparedQuery05="update users set mobile=? where username like ?";
    private String preparedQuery06="INSERT INTO admin_password_resets (id,email,token,status) VALUES (?,?,?,?)";
    private String preparedQuery08="UPDATE admin_notifications SET is_read = ? where id = ?";
    //UPDATE admin_notifications SET is_read = 1 WHERE is_read = 0 LIMIT 1;     2.yol
    private String preparedQuery09insert="insert into update_logs (id,version,update_log,created_at) values (?,?,?,?);";
    //yukardaki satirda insert into ile yeni 1 satir ekledik ve onu asagidaki satirda update ile update ettik
    private String preparedQuery09update="UPDATE update_logs SET update_log = ? WHERE version=? AND id=?";
    private String preparedQuery10Delete="DELETE FROM update_logs WHERE id=?";
    private String preparedQuery11Insert = "INSERT INTO support_attachments (id,support_message_id,attachment,created_at) VALUES (?,?,?,?);";
    private String preparedQuery11Delete="delete from support_attachments where support_message_id = ?;";
    private String query12="select remember_token from admins where email='info@loantechexper.com'";
    private String query13="select charge from deposits where amount<500000 and trx='4GC9SMZUS69S'";
    private String preparedQuery14insert="INSERT INTO loans (loan_number,user_id,plan_id) VALUES (?,?,?)";
    private String preparedQuery14Delete="delete from loans where loan_number= ?";
    private String query15="select support_ticket_id from support_messages where message='Hello'";
    //--------------------GETTER-----------------------
    public String getQuery01() {
        return query01;
    }//bu method ile query01 sorgusuna ulasilir


    public String getQuery02() {
        return query02;
    }


    public String getQuery03() {
        return query03;
    }

    public String getQuery04() {
        return query04;
    }


    public String getUpdateQuery05() {
        return updateQuery05;
    }

    public String getPreparedQuery05() {
        return preparedQuery05;
    }

    public String getPreparedQuery06() {
        return preparedQuery06;
    }

    public String getPreparedQuery08() {
        return preparedQuery08;
    }

    public String getPreparedQuery09insert() {
        return preparedQuery09insert;
    }

    public String getPreparedQuery09update() {
        return preparedQuery09update;
    }

    public String getPreparedQuery10Delete() {
        return preparedQuery10Delete;
    }

    public String getPreparedQuery11Insert() {
        return preparedQuery11Insert;
    }

    public String getPreparedQuery11Delete() {
        return preparedQuery11Delete;
    }

    public String getQuery12() {
        return query12;
    }

    public String getQuery13() {
        return query13;
    }

    public String getPreparedQuery14insert() {
        return preparedQuery14insert;
    }

    public String getPreparedQuery14Delete() {
        return preparedQuery14Delete;
    }

    public String getQuery15() {
        return query15;
    }
}
