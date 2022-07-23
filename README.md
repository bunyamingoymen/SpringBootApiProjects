# *<p align="center">Evam Java Bootcamp Final Projesi</p>*

## Bölümler:

[<img src="https://i.ibb.co/XbyGTrP/1-authentication-2-36x36.png" width="28" height="28" /> 1- Projenin Açıklaması](#proje)<br>
[<img src="https://i.ibb.co/XbyGTrP/1-authentication-2-36x36.png" width="28" height="28" /> 2- Veritabanındaki tablo açıklamaları](#tablolar)<br>
[<img src="https://i.ibb.co/XbyGTrP/1-authentication-2-36x36.png" width="28" height="28" /> 3- Path Açıklamaları](#path)<br>
[<img src="https://i.ibb.co/XbyGTrP/1-authentication-2-36x36.png" width="28" height="28" /> 4- Postman Kullanımı](#postman)<br>
[<img src="https://i.ibb.co/XbyGTrP/1-authentication-2-36x36.png" width="28" height="28" /> 5- Kodun Açıklamaları](#kod)<br>

<hr>

### Proje:

● Bir müşteri bilgisi alıp kayıt eden, bir fatura bilgisi kayıt
eden ve bu bilgileri sorgulayan restApi ler olacak.

● Bir de ödenmiş statüsünde gözüken fatura kaydı oluşturalım.
Müşterinin faturası sorgulandığında ödenmemiş faturanın
bulunmadığına dair response code ve mesaj dönülsün. (Fatura
sorgulama faturaId ve müşteri numarası ile yapılmalı)

● Oluşturulan müşteri kaydı ve fatura kaydı için id bilgisi ile
silme işlemleri yapan 2 servis olsun.

● Fatura kaydı oluşturulacak, kayıt sorgulanabilecek.

● Müşteri bilgisi update eden bir servis olacak.

● Bu işlemlerin postgreSql e giden sorgular ile yapacağız.
Respository bağlantısı olmalı.

● Proje bir maven projesi olacak. Springboot framework ü ile ve
SOLID prensiplerine uygun şekilde yazılacak.

3 adet tablo yeterli. Fatura, User, Payment
Payment işlemini doğrudan yapılmış gibi hazır kayıt oluşturulması
yeterli. Servisler ResponseEntity tipinde cevap dönmeli.
Aşağıdaki pathler örnektir, gerektiği kadar servis
oluşturabilirsiniz. Readme dosyasında bu restApi ler için açıklamalı Postman den nasıl
istek atıldığını gösteren örnek Request ve response lar içeren,
GET/PUT/POST/DELETE işlemlerini açıklayan bir açıklama bulunsun.

1. https://localhost:8080/v1/payments/getAllDebts-> list
of debts

2. https://localhost:8080/v1/debts -> create debt -> (the
return type must be Response.class and it should
contains code, date, explanation) and the return code
must be 0, explanation = "The debt is created
succesfully"

3. https://localhost:8080/v1/payments/{debtId} -> update
the debt statu (if it is paid returns 1 and a
explanation like "The debt is allready paid" (the
return type must be Response.class and it should
contains code, date, explanation)

4. https://localhost:8080/v1/payments/{debtId} -> delete
the debt record from table.

5. https://localhost:8080/v1/users/createUser

6. https://localhost:8080/v1/users/queryUserInfo

Class car hangi attribute hakkında ihtiyacınız olan detaylar:

Müşteri için; ad, soyad, abone no

Fatura; fatura no, abone no, fatura tutarı, fatura işlem tarihi

Payment ; amount, date, abone no

<hr>

### Tablolar

*User* : Kullanıcı bilgilerini tutmaktadır. İçerisinde 3 tane Sütun(Column) bulunmaktadır. Bunlar: id(abone_no), name ve surmane.

*Invoice* : Kullanıcının fatura bilgilerini tutmaktadır. Bir kullanıcının birden fazla faturası olabilir. Bir fatura da sadece bir kullanıcıya ait olabilir. Yani User tablosu ile OneToMany ilişkisi bulunmakta. Örneğin kullanıcının internet,elektrik ve su faturalarının'nın tutarları invoice de ayrı satır(Row) olarak tutulmaktadır.
İçerisinde 5 adet Sütun(Column) bulunmaktadır: id(fatura_no), client_id(abone_no), is_pay, total, transaction_date. client_id, oneToMany ilişkisi için tutulmaktadır. is_pay, faturanın ödenip ödenmediğini tutan bir boolean değerdir. total, faturanın tutarıdır. transaction_date, İşlemin yapıldığı tarihtir.

*Payment* : Bir kullanıcının faturalarının toplam tutarını tutmaktadır. Örneğin bir kullanıcının elektrik faturası: 100, su faturası: 150, internet faturası: 120 olursa. Bu kullanıcının ödemesi gereken toplam tutar 320 tl olmaktadır. Bu durumda Payment bunu tutmaktadır. Her bir kullanıcı için bir Payment satırı olmalıdır. Eğer kullanıcının ödenmmeiş bir faturası yoksa da payment'daki totalAmount değeri 0 olmalıdır. Kullanıcıya ait herhangi bir faturadaki değer değişirse(azalır ya da artarsa), silinirse ya da ödenirse payment değeri de ona göre değişecektir. Burası kod kısmında anlatılmaktadır. Her bir kullanıcı için bir Payment değeri olduğu için bu tablo ile kullanıcı arasında OneToOne ilişkisi bulunmaktadır. Aynı zamanda bu tabloya direk kullanıcı tarafından CRUD işlemleri yapılmamalıdır. Invoice ve User tablolarına eklenen veriler ile CRUD işlemi otomatik yapılmalıdır. Örneğin yeni bir fatura eklendiğinde. O faturanın sahibi olan kullanıcının payment değeri değiştirilmelidir. Ya da yeni bir kullanıcı eklendiğinde payment tablosuna da yeni bir değer eklenip totalAmount değeri 0 olarak ayarlanmalıdır (Kullanıcı yeni oluşturulduğu için herhangi bir faturası mevcut değil. Bu sebeple varsayılan totalAmount 0 olarak ayarlı.) İçerisinde 4 adet Sütun(Column) mevcuttur: id, client_id, date, totalAmount.

<hr>

### Path:

*User* :

  - http://localhost:8080/users/get         -> (Get) Bütün kullanıcıları getirir
  - http://localhost:8080/users/get/{id}    -> (Get) Id değeri girilen kullanıcıyı getirir
  - http://localhost:8080/users/createUser  -> (Post) Yeni bir kullanıcı ekler
  - http://localhost:8080/users/delete/{id} -> (Delete) Id değeri girilen kullanıyı veri tabanından siler
  - http://localhost:8080/users/update/{id} -> (Put) Id değeri girilen kullanıcının bilgilerini gelen bilgilere göre günceller.
  
*Invoice* :

  - http://localhost:8080/invoice/get                      -> (Get) Bütün faturaları getirir.
  - http://localhost:8080/invoice/get/{id}                 -> (Get) Id değeri girilen faturayı getirir
  - http://localhost:8080/invoice/get/clientid/{clientId}  -> (Get) Id değeri girilen kullanıcının faturalarını getirir
  - http://localhost:8080/invoice/createInvoice            -> (Post) Yeni Fatura değeri oluşturulur
  - http://localhost:8080/invoice//delete/{id}             -> (Delete) Id değeri girilen faturayı veri tabanından siler
  - http://localhost:8080/invoice//update/{id}             -> (Put) Id değeri girilen faturanın bilgilerini gelen bilgilere göre günceller
  - http://localhost:8080/invoice//pay/{id}                -> (Put) Kullanıcın faturayı ödemesi sağlanır
  
*Payment* :
  - http://localhost:8080/payment/get                      -> (Get) Bütün Payment'ları getirir.
  - http://localhost:8080/payment/get/{id}                 -> (Get) Id değeri girilen payment'ı getirir
  - http://localhost:8080/payment//pay/{id}                -> (Put) Kullanıcıya ait bütün faturaları ödemesini sağlar (Payment butün faturaların tutarlarını tutuyordu.)

<hr>

### Postman

<hr>

### Kod
