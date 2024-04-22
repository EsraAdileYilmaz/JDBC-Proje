Feature:SELECT QUERY EXECUTE

  Background:Database baglantisi
    * Database baglantisi kurulur.

  @query01
  Scenario:Database içindeki "deposits" tablosunda
           "amount" değeri 100$ ile 500$ arasında olan user_id’leri doğrulayınız.

    * Query01 hazirlanir ve execute edilir.
    * ResultSet01 sonuclari islenir.
    * Database baglantisi kapatilir.

    @query02
  Scenario:Database içindeki "cron_schedules" tablosunda
           ilk 2 kaydın "name" bilgisini doğrulayınız

    * Query02 hazirlanir ve execute edilir.
    * ResultSet02 sonuclari islenir.
    * Database baglantisi kapatilir.

    @query03
  Scenario: Database üzerinde "Users" tablosunda "country_code=TR" olmayan
            ve "id=11" olan datanın "firstname" ve "lastname" bilgilerini doğrulayınız.

    * Query03 hazirlanir ve execute edilir.
    * ResultSet03 sonuclari islenir.
    * Database baglantisi kapatilir.

   @query04
  Scenario: user_logins tablosunda user_id lere gore sisteme giris yapilan
            browser ve os(operation systems) leri gruplayip ekrana yazdiriniz.

        * Query04 hazirlanir ve execute edilir.
        * ResultSet04 sonuclari islenir.
        * Database baglantisi kapatilir.


   @query12
  Scenario:Database içindeki "admins" tablosunda "email=info@loantechexper.com"  olan datanın
           "remember_token" bilgisini doğrulayınız

      * Query12 hazirlanir ve execute edilir.
      * ResultSet12 sonuclari islenir.
      * Database baglantisi kapatilir.


  @query13
  Scenario: Database içindeki "Deposits" tablosunda "amount" değeri 500.000$ altında olan datalardan
            "trx=4GC9SMZUS69S"olan datanın "charge" değerini doğrulayınız

      * Query13 hazirlanir ve execute edilir.
      * ResultSet13 sonuclari islenir.
      * Database baglantisi kapatilir.


  @query15
  Scenario:Database içindeki "support_messages" tablosunda "message" bilgisi
           "Hello" olan datanın "support_ticket_id" sini doğrulayınız.

    * Query15 hazirlanir ve execute edilir.
    * ResultSet15 sonuclari islenir.
    * Database baglantisi kapatilir.













