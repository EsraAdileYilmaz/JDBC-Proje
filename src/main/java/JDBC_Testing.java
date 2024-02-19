import java.sql.*;

public class JDBC_Testing {

    /*
    By class'i acmamizdaki amac
    jdbc testing de yapacagimiz tum adimlari tek bir class ta gormektir.
    sonrasinda cucumber yapisinda calisilacaktir.
     */


    //Sizden JDBC sorgusu yapmaniz istendiginizde yapacaginiz ilk is;
    //Database yoneticisi ile iletisime gecerek Access information'lari edinmek

    /*
    * type: jdbc:mysql
      host/ip: 45.87.83.5
      port: 3306
      database_name: u168183796_qaloantec
      username: u168183796_qaloantecuser
      password: 0&vG1A/MuWN

     * URL olustur=>
     * url=jdbc:mysql://45.87.83.5/u168183796_qaloantec
     * username: u168183796_qaloantecuser
     * password: 0&vG1A/MuWN
      */

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

         //1.ADIM= DOGRU SURUCUYU EKLE

        Class.forName("com.mysql.cj.jdbc.Driver");//mysql workbench ile calisan driver eklendi

        //2.ADIM= VERI TABANI ILE ILETISIMI BASLAT

        Connection connection=DriverManager.getConnection("jdbc:mysql://45.87.83.5/u168183796_qaloantec",
                                                          "u168183796_qaloantecuser",
                                                          "0&vG1A/MuWN");
        //burada driver yoluyla connection saglandi.
        // getConnection() methodu Connection dondurdugu icin connection'a atama yaptik.

        //3.ADIM= SQL QUERY'LERI OLUSTUR

        String query="SELECT * FROM u168183796_qaloantec.users;";// sql de yazdigimiz query. bu query bize tum tabloyu getirdi

        Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //Statement olusturulmasinin amaci sql kodlarinin java ya adapte olmasini saglar
        //NOT:Statement olusturmak icin mutlaka bir connection olmasi gerekli.
        //Create edilen statement bir query calistirilacaksa atama yapilmadan kullanilabilir,
        //Ancak defalarca kullanmak istiyorsak atama yapilip kullanilabilir.


        //4. ADIM= QUERY EXECUTE ET

          /*
           statement.executeQuery(query); =
          connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(query);

         Yukaridaki iki ifade de esittir,aynidir.
       */

        //Yazdigimiz query'nin(String query="SELECT * FROM u168183796_qaloantec.users;";) calismasi icin
        // statement uzerinden executeQuery cagrilmalidir.
        ResultSet resultSet=statement.executeQuery(query);//Users tablosundaki herseyi bana bir result set olarak dondurcek.

        //5.ADIM= SONUCLARI ISLE

        //Result set indexi olmayan, iterator ile calisir.
        // iterator yapisinda next() methodu ile 1 adim ilerlenir.
        resultSet.next();// iterator'i ilk  satira gonderdik yani bir adim ilerledi.header'dan bir alt satira indi
        System.out.println(resultSet.getString("firstname"));//Mehmet (ilk satirin firstname bilgisi)

        resultSet.next();//2.satirda (iterator 1 adim daha ilerleyerek 2.satira geldi)
        System.out.println(resultSet.getString("firstname"));//Test (2. satirdaki firstname i getirdi)

        resultSet.next();//3.satirda (iterator 1 adim daha ilerleyerek 3.satira geldi)
        System.out.println(resultSet.getString("lastname"));//Genc (3. satirdaki lastname i getirdi)
        System.out.println(resultSet.getString("email"));//mcenkk@gmail.com (bu email 3. satirdaki emaildir)

        // absolute() methodu ile iterator'u istedigimiz row'a gondeririz
        resultSet.absolute(10);//10.satir (iterator 10. satirda)
        System.out.println(resultSet.getString("firstname"));//Ahmet

        //first() methodu ile iterator'u 1. satira dondurebiliyoruz
        resultSet.first();//iterator 1. satira geldi
        System.out.println(resultSet.getString("email"));//elff931@gmail.com (1. satirin emaili)


    }


}
