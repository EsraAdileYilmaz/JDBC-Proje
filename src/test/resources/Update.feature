Feature:UPDATE(update,delete,insert queryleri icin) QUERY EXECUTE

  Background:Database baglantisi
    * Database baglantisi kurulur.

    @query05
    Scenario: "users" tablosunda sondan bir önceki harfi e olan
               "usernamelerin" "mobile" numarasını update ediniz

      * Update query01 olusturulur vr execute edilir
      * Sonuclar dogrulanir
      * Database baglantisi kapatilir.


     @query06
    Scenario:  "users" tablosunda sondan bir önceki harfi e olan
                "usernamelerin" "mobile" numarasını update ediniz.

      * Prepared query02 olusturulur ve execute edilir.
      * Prepared query02 sonuclar dogrulanir.
      * Database baglantisi kapatilir.

    @query07
    Scenario: admin_password_resets tablosuna yeni (id,email,token,status,created_at datalarla)
              veri girisi yapiniz.
      # "id,email,token,status,created_at" bilgilerini girip insert islemi yapilacak

      * Prepared query03 olusturulur ve execute edilir.
      * Prepared query03 sonuclar dogrulanir.
      * Database baglantisi kapatilir.

      @query08
    Scenario: "id=?" olan kullanıcının "is_read=0" olan bildirimlerini
              '1' Olarak Update edip doğrulayınız.
        #admin_notifications tablosundaki is_read ve id icin update isteniyor

      * Prepared query08 olusturulur ve execute edilir.
      * Prepared query08 sonuclar dogrulanir.
      * Database baglantisi kapatilir.

    @query09
    Scenario: "update_logs" tablosunda "version=? " ve "id=?" olan datanın
               "update_log" değerini update edip doğrulayınız.
      #once insert yapip insert yaptigimiz satiri update edip degerlerini guncellicez

    * Update_logs tablosuna insert query hazirlanir ve calistirilir
    * Update_logs tablosuna insert edilen datanin update log degeri degistirilir
    * update log degerinin degistigi dogrulanir
    * Database baglantisi kapatilir.



      @query10
      Scenario: Update_logs tablosunda "id=?" değerine göre bir datayı siliniz ve
                silindiğini doğrulayınız

        * Update_logs tablosuna insert query hazirlanir ve calistirilir
        * Update_logs tablosuna insert edilen data silinir
        * Satirin silindigi dogrulanir
        * Database baglantisi kapatilir.

      @query11
      Scenario:"support_attachments" tablosunda "support_message_id=?" değerine göre
                bir dosyayı siliniz ve silindiğini doğrulayınız.

        #once insert sonra delete yapariz
        #insert isleminin daha dinamik olmasi icin faker class'indan deger aliriz
        #insert ettigimiz faker degerlerine sahip datayi yine faker degerlerini alarak delete ederiz
        * support_attachments tablosuna insert query hazirlanir ve calistirilir.
        * support_attachments tablosuna insert edilen data silinir.
        * Satirin silindigi dogrulanir
        * Database baglantisi kapatilir.


       @query14
      Scenario: Database üzerinden "loans" tablosunda "loan_number" girerek
                istenen datayı siliniz ve silindiğini doğrulayınız.

        * loans tablosuna insert query hazirlanir ve calistirilir.
        * loans tablosuna insert edilen data silinir.
        * Satirin silindigi dogrulanir
        * Database baglantisi kapatilir.




























