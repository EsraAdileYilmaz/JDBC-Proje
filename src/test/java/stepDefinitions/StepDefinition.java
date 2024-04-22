package stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import manage.QueryManage;
import org.junit.Assert .*;
import utilities.JDBCReusableMethods;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StepDefinition {
    int rowCount;
    String query;
    ResultSet resultSet;
    PreparedStatement preparedStatement;
    QueryManage queryManage=new QueryManage();//yazdigimiz querylere ulasmak icin QueryManage class'indan objemizi olusturduk

    Faker faker=new Faker();
    int id;
    String version;
    String updateLog;
    int supportMessageID;
    String loanNumber;
    int user_id;
    int plan_id;

    @Given("Database baglantisi kurulur.")
    public void database_baglantisi_kurulur() {
        JDBCReusableMethods.getConnection();

    }
    @Given("Query01 hazirlanir ve execute edilir.")
    public void query01_hazirlanir_ve_execute_edilir()throws SQLException {
    query=queryManage.getQuery01();
    resultSet=JDBCReusableMethods.getStatement().executeQuery(query);
    }
    @Given("ResultSet01 sonuclari islenir.")
    public void result_set01_sonuclari_islenir() throws SQLException {
    resultSet.next();//iteratorumuzu 1 satir ilerlettik
    int actualUserId=resultSet.getInt("user_id");//yani user_id column'da 1.satirdaki degeri getir
    int expectedUserId=1;
    assertEquals(expectedUserId,actualUserId);//burada test ettigimiz sey 1.satirdaki user_id=1.

    }
    @Given("Database baglantisi kapatilir.")
    public void database_baglantisi_kapatilir() {
    JDBCReusableMethods.closeConnection();
    }

    // ------------------------QUERY02--------------------------------------------
    @Given("Query02 hazirlanir ve execute edilir.")
    public void query02_hazirlanir_ve_execute_edilir() throws SQLException {
    query=queryManage.getQuery02();
    resultSet=JDBCReusableMethods.getStatement().executeQuery(query);
    }
    @Given("ResultSet02 sonuclari islenir.")
    public void result_set02_sonuclari_islenir() throws SQLException {
        //resultset icinde 2 kayit var.bunun icin list icine atmaliyiz
        List<String> isimler=new ArrayList<>();
        while(resultSet.next()){
            String isim=resultSet.getString("name");//name column'undakileri getir
            isimler.add(isim);
        }

        //isimler list'indeki verileri assert etmek icinde yine bir list olusturmak lazim
        List<String> expectedName=new ArrayList<>();
        expectedName.add("5 Minutes");
        expectedName.add("10 Minutes");

        for (int i = 0; i < isimler.size() ; i++) {
           assertEquals(expectedName.get(i),isimler.get(i));
            System.out.println(i+". index dogrulandi");
        }

    }

    //------------------------QUERY03-----------------------
    @Given("Query03 hazirlanir ve execute edilir.")
    public void query03_hazirlanir_ve_execute_edilir() throws SQLException {
       query=queryManage.getQuery03();
       resultSet=JDBCReusableMethods.getStatement().executeQuery(query);
    }
    @Given("ResultSet03 sonuclari islenir.")
    public void result_set03_sonuclari_islenir() throws SQLException {
    resultSet.next();
    String expectedName="Mehmet Genç";
    String actualName=resultSet.getString("firstname")+" "+resultSet.getString("lastname");
    assertEquals(expectedName,actualName);
        System.out.println("expected= " + expectedName);
        System.out.println("actual= " + actualName);
    }

    //------------------QUERY04------------------------------------------
    @Given("Query04 hazirlanir ve execute edilir.")
    public void query04_hazirlanir_ve_execute_edilir() throws SQLException {
    query=queryManage.getQuery04();
    resultSet=JDBCReusableMethods.getStatement().executeQuery(query);

    }
    @Given("ResultSet04 sonuclari islenir.")
    public void result_set04_sonuclari_islenir() throws SQLException {
    while(resultSet.next()){
       String kullaniciId=resultSet.getString("user_id");
       String browserOS=resultSet.getString("browser_os");

        System.out.println("kullaniciId= " + kullaniciId);
        System.out.println("Browser & OS= " + browserOS);
    }

    }

    //----------------UPDATE QUERY (statement ile)----------------------

    @Given("Update query01 olusturulur vr execute edilir")
    public void update_query01_olusturulur_vr_execute_edilir() throws SQLException {
        query= queryManage.getUpdateQuery05();
        rowCount= JDBCReusableMethods.updateQuery(query);//actual beklenen row sayisi
    }
    @Given("Sonuclar dogrulanir")
    public void sonuclar_dogrulanir() {
        int expectedRow=18;//bu 18 satiri tabloyu gorunce yazdik
        assertEquals(expectedRow, rowCount);
    }


    //--------------UPDATE QUERY(PREPARED STATEMENTS ILE)---------------------
    @Given("Prepared query02 olusturulur ve execute edilir.")
    public void prepared_query02_olusturulur_ve_execute_edilir() throws SQLException {
    query=queryManage.getPreparedQuery05();
    preparedStatement=JDBCReusableMethods.getConnection().prepareStatement(query);
    preparedStatement.setInt(1,444444444);//burdaki 1 degeri ? isaretinin yerini tutan parametredir.
    preparedStatement.setString(2,"%e_");//burdaki 2 degeri ? isaretinin yerini tutan parametredir.bu sekilde values leri gizli hale getirdik

     rowCount=preparedStatement.executeUpdate();
    }
    @Given("Prepared query02 sonuclar dogrulanir.")
    public void prepared_query02_sonuclar_dogrulanir() {
        assertEquals(18, rowCount);
    }


    //---------------QUERY07 (PREPARED STATEMENTS ILE)----------------------------
    @Given("Prepared query03 olusturulur ve execute edilir.")
    public void prepared_query03_olusturulur_ve_execute_edilir() throws SQLException {
    query=queryManage.getPreparedQuery06();//bununla yazdigimiz sorguyu buraya getiriyoruz
    preparedStatement= JDBCReusableMethods.getConnection().prepareStatement(query);

   // INSERT INTO admin_password_resets (id,email,token,status) VALUES (?,?,?,?);  burada ? isaretlerinin yerine values'lerini giricez.
     preparedStatement.setInt(1,502);//burada 1=id degerini girdik
     preparedStatement.setString(2,"email33@gmail.com");//burada 2=email degerini girdik
     preparedStatement.setString(3,"125478");//burada 3=token degerini girdik
     preparedStatement.setInt(4,1); //burada 4=status degerini girdik
     rowCount=preparedStatement.executeUpdate();//burada update islemimizi yaptik ve bize bir satir sayisi int dondurecek
        /*
        prepareStatement() metodu, JDBC (Java Database Connectivity) API'sinin bir parçasıdır ve
        SQL ifadelerini veritabanına göndermek için kullanılır. Bu metod, SQL ifadelerini
        veritabanı sunucusuna göndermeden önce hazırlar
        ve sorguların daha etkin bir şekilde yürütülmesine olanak tanır.

       prepareStatement() kullanılarak oluşturulan bir PreparedStatement nesnesi,
       parametrelerle doldurulabilir ve birden çok kez kullanılabilir.
       Bu, aynı SQL ifadesini farklı parametre değerleriyle çalıştırmak için kullanışlıdır ve
       aynı zamanda SQL enjeksiyon saldırılarına karşı daha güvenlidir.

      Ayrıca, prepareStatement() kullanarak oluşturulan sorgular,
      veritabanı tarafından önceden derlenebilir ve önbelleğe alınabilir,
      böylece aynı sorguların tekrar tekrar yürütülmesi durumunda performans artışı sağlanır.

     Özetle, prepareStatement() metodu, parametreli SQL sorgularının hazırlanması ve
     veritabanına gönderilmesi için kullanılırken,
     performans artışı ve güvenlik sağlamak için yaygın olarak tercih edilir.
         */
    }
    @Given("Prepared query03 sonuclar dogrulanir.")
    public void prepared_query03_sonuclar_dogrulanir() {
        assertEquals(1,rowCount);//yani sadece 1 satir etkilenecek
    }

    //---------------QUERY08(PreparedStatement)----------------------
    @Given("Prepared query08 olusturulur ve execute edilir.")
    public void prepared_query08_olusturulur_ve_execute_edilir() throws SQLException {
      query=queryManage.getPreparedQuery08();
      preparedStatement= JDBCReusableMethods.getConnection().prepareStatement(query);//olusturdugumuz querymizin calismasi icin statement olusturuyoruz.
      //"UPDATE admin_notifications SET is_read = ? where id = ?";
      preparedStatement.setInt(1,1);
      preparedStatement.setInt(2,779);
      rowCount=preparedStatement.executeUpdate();
    }
    @Given("Prepared query08 sonuclar dogrulanir.")
    public void prepared_query08_sonuclar_dogrulanir() {
    assertEquals(1,rowCount);//1 satir etkilendi yani bunu dogrulamak istiyoruz
    }

    //----------------------QUERY09--------------------------------------------
    @Given("Update_logs tablosuna insert query hazirlanir ve calistirilir")
    public void update_logs_tablosuna_insert_query_hazirlanir_ve_calistirilir() throws SQLException {
     query=queryManage.getPreparedQuery09insert();
     preparedStatement=JDBCReusableMethods.getConnection().prepareStatement(query);

     //insert into update_logs (id,version,update_log,created_at) values (?,?,?,?);
     id=faker.number().numberBetween(400,550);
     version=faker.options().option("Windows 10","MacOs ventura","Linux");
     updateLog=faker.lorem().sentence(1);

     preparedStatement.setInt(1,id);
     preparedStatement.setString(2,version);
     preparedStatement.setString(3,updateLog);
     preparedStatement.setDate(4,Date.valueOf(LocalDate.now()));
     rowCount=preparedStatement.executeUpdate();//1 satir eklendi

        System.out.println("updateLOG: "+ updateLog);
        System.out.println("version: "+ version);
        System.out.println("id: "+ id);

     int flag=0;
     if(rowCount>0){
         flag++;
     }//burada satir eklendigini flag uzerinden test etmek icin flag olusturduk
        rowCount=0;
        assertEquals(1,flag);//yada bunun yerine direk=> assertEquals(1,rowCount); yapilabilir dogrulama icin
    }
    @Given("Update_logs tablosuna insert edilen datanin update log degeri degistirilir")
    public void update_logs_tablosuna_insert_edilen_datanin_update_log_degeri_degistirilir() throws SQLException {
    query=queryManage.getPreparedQuery09update();
    preparedStatement=JDBCReusableMethods.getConnection().prepareStatement(query);

    //UPDATE update_logs SET update_log = ? WHERE version=? AND id=?;
    String updateLogYeni="yeni log";//kendi yazdigimiz "yeni log" ile update isleminin yapildigini dogruladik.yoksa insertteki lorem cumlesi gelirdi.faker dan gelen insert degerini yeni log olarak degistirmek istiyorum.
    preparedStatement.setString(1,updateLogYeni);
    preparedStatement.setString(2,version);//version=faker.options().option("Windows 10","MacOs ventura","Linux"); burdan alicak
    preparedStatement.setInt(3,id);//id=faker.number().numberBetween(400,550); burdan alicak

    rowCount=preparedStatement.executeUpdate();
        System.out.println("id: "+id);

    }
    @Given("update log degerinin degistigi dogrulanir")
    public void update_log_degerinin_degistigi_dogrulanir() {
    assertEquals(1,rowCount);
    }

    //-------------------------QUERY10--------------------------
    @Given("Update_logs tablosuna insert edilen data silinir")
    public void update_logs_tablosuna_insert_edilen_data_silinir() throws SQLException {
      query=queryManage.getPreparedQuery10Delete();
      preparedStatement=JDBCReusableMethods.getConnection().prepareStatement(query);
      //DELETE FROM update_logs WHERE id=?;
      preparedStatement.setInt(1,id);//bir onceki soruda tanimladik.yukarda faker class'i ile eklenen id'yi burda siliyoruz
      rowCount=preparedStatement.executeUpdate();
      System.out.println("silinen id "+id);

    }
    @Given("Satirin silindigi dogrulanir")
    public void satirin_silindigidogrulanir() {
        assertEquals(1,rowCount);//1 satir etkilendi
    }


    //--------------------QUERY11----------------------------------

    @Given("support_attachments tablosuna insert query hazirlanir ve calistirilir.")
    public void support_attachments_tablosuna_insert_query_hazirlanir_ve_calistirilir() throws SQLException {
        //INSERT INTO support_attachments (id,support_message_id,attachment,created_at) VALUES (?,?,?,?);
        query=queryManage.getPreparedQuery11Insert();
        preparedStatement=JDBCReusableMethods.getConnection().prepareStatement(query);
        id=faker.number().numberBetween(400,600);
        supportMessageID=faker.number().numberBetween(250,300);

        preparedStatement.setInt(1,id);
        preparedStatement.setInt(2,supportMessageID);
        preparedStatement.setString(3,"658401a61409c1703149990.png");
        preparedStatement.setDate(4,Date.valueOf(LocalDate.now()));

        rowCount=preparedStatement.executeUpdate();
        System.out.println("id:  "+ id);
        System.out.println("supportMessageID:  "+ supportMessageID);

        assertEquals(1,rowCount);//burada 1 satir eklendigini dogruluyoruz

    }
    @Given("support_attachments tablosuna insert edilen data silinir.")
    public void support_attachments_tablosuna_insert_edilen_data_silinir() throws SQLException {
        //delete from support_attachments where support_message_id = ?
        query=queryManage.getPreparedQuery11Delete();
        preparedStatement=JDBCReusableMethods.getConnection().prepareStatement(query);
        preparedStatement.setInt(1,supportMessageID);
        rowCount=preparedStatement.executeUpdate();
        System.out.println("Sildigimiz datanin support message id'si: "+supportMessageID);

    }

    //------------------QUERY12(select sorgusu)--------------------------------------------
    @Given("Query12 hazirlanir ve execute edilir.")
    public void query12_hazirlanir_ve_execute_edilir() throws SQLException {
        query=queryManage.getQuery12();
        resultSet=JDBCReusableMethods.getStatement().executeQuery(query);

    }
    @Given("ResultSet12 sonuclari islenir.")
    public void result_set12_sonuclari_islenir() throws SQLException {
        resultSet.next();
        String expectedRememberToken="1xUgfVUD1Ggx5CVz7mxLvcye8RXRbeFqSktSIkhya321TqDkLOsqhys4pnJv";
        String actualRememberToken=resultSet.getString("remember_token");
        assertEquals(expectedRememberToken,actualRememberToken);
        System.out.println("expected= " + expectedRememberToken);
        System.out.println("actual= " + actualRememberToken);
    }

    //------------------QUERY13(select sorgusu)--------------------------------------------
    @Given("Query13 hazirlanir ve execute edilir.")
    public void query13_hazirlanir_ve_execute_edilir() throws SQLException {
       query=queryManage.getQuery13();
       resultSet=JDBCReusableMethods.getStatement().executeQuery(query);
    }
    @Given("ResultSet13 sonuclari islenir.")
    public void result_set13_sonuclari_islenir() throws SQLException {
        //resultset icinde 3 kayit var.bunun icin list icine atmaliyiz
        List<Integer> charges=new ArrayList<>();
        while(resultSet.next()){
            int charge=resultSet.getInt("charge");//charge column'undakileri getir
            charges.add(charge);

        }

        //charges list'indeki verileri assert etmek icinde yine bir list olusturmak lazim
        List<Integer> expectedCharges=new ArrayList<>();
        expectedCharges.add(102);
        expectedCharges.add(102);
        expectedCharges.add(102);

        for (int i = 0; i < charges.size() ; i++) {
            assertEquals(expectedCharges.get(i),charges.get(i));
            System.out.println(i+". index dogrulandi");
        }

    }

    //------------------QUERY14(PreparedStatement)--------------------------------------------

    @Given("loans tablosuna insert query hazirlanir ve calistirilir.")
    public void loans_tablosuna_insert_query_hazirlanir_ve_calistirilir() throws SQLException {
       //INSERT INTO loans (loan_number,user_id,plan_id) VALUES (?,?,?)
        query=queryManage.getPreparedQuery14insert();
        preparedStatement=JDBCReusableMethods.getConnection().prepareStatement(query);
        loanNumber="EHMEJ1FXNW45";
        user_id=faker.number().numberBetween(1,99);
        plan_id=faker.number().numberBetween(1,200);

        preparedStatement.setString(1,loanNumber);
        preparedStatement.setInt(2,user_id);
        preparedStatement.setInt(3,plan_id);
        rowCount=preparedStatement.executeUpdate();
        System.out.println("loan number:  "+loanNumber);

        assertEquals(1,rowCount);//burada 1 satir eklendigini dogruluyoruz


    }
    @Given("loans tablosuna insert edilen data silinir.")
    public void loans_tablosuna_insert_edilen_data_silinir() throws SQLException {
       //delete from loans where loan_number= ?
        query=queryManage.getPreparedQuery14Delete();
        preparedStatement=JDBCReusableMethods.getConnection().prepareStatement(query);
        preparedStatement.setString(1,loanNumber);
        rowCount=preparedStatement.executeUpdate();
        System.out.println("Silinen loan number: "+loanNumber);
    }


    //------------------QUERY15(select sorgusu)--------------------------------------------

    @Given("Query15 hazirlanir ve execute edilir.")
    public void query15_hazirlanir_ve_execute_edilir() throws SQLException {
       query=queryManage.getQuery15();
       resultSet=JDBCReusableMethods.getStatement().executeQuery(query);
    }
    @Given("ResultSet15 sonuclari islenir.")
    public void result_set15_sonuclari_islenir() throws SQLException {
        //resultset icinde 3 kayit var.bunun icin list icine atmaliyiz
        List<Integer> supportTicketIdList=new ArrayList<>();
        while(resultSet.next()){
            int supportTicket=resultSet.getInt("support_ticket_id");//charge column'undakileri getir
            supportTicketIdList.add(supportTicket);
        }

        // supportTicketIdList'indeki verileri assert etmek icinde yine bir list olusturmak lazim
        List<Integer> expectedSupportTicketList=new ArrayList<>();
        expectedSupportTicketList.add(48);
        expectedSupportTicketList.add(49);
        expectedSupportTicketList.add(97);


        for (int i = 0; i < supportTicketIdList.size() ; i++) {
            assertEquals(expectedSupportTicketList.get(i),supportTicketIdList.get(i));
            System.out.println(i+". index dogrulandi");
        }
    }





}
