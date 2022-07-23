# EvamJavaBootcamp_FinalProjects

**```Toplam iki tane bitirme projesi ödevi mevcuttur. İstenilen proje dosyasına girilerek dosya içerisindeki Readme.md dosyasını inceleyebilir ve o projedeki kodlara ve nasıl çalıştığına bakabilirsiniz. Aşağıda sadece tasarlanması istenilen projelerin açıklamaları mevcuttur:```**

[Deneme]#denemekismi

### 1.SpringBoot ile final projesi:

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

Payment ; amount, date, fatura no, abone no

### 2.SOS Oyunu:

Kare şeklinde nxn kutulardan oluşan bir panelde iki kişi tarafından oynanan bir oyundur. Oyun sırasında her bir kutuya s yada o harflerinden biri yerleştirilir. Oyunun başında hangi oyuncunun hangi harfi yerleştireceği ve kimin başlayacağına karar verilir. Amaç bir harf koyduğumuzda yatayda, dikeyde yada çaprazda SOS kelimesini oluşturmaktır. SOS kelimesini oluşturan oyuncu 1 puan alır ve tekrar harf ekleme hakkı kazanır. Panel üzerinde hiç boş kutu kalmayana kadar oyun oynanır, sonunda en çok puanı alan oyunu kazanır.

#### Kurallar:

- Panel büyüklüğü oyunun en başında konsoldan girdi olarak alınır. Minimum 3x3 maksimum 7x7 büyüklüğünde olmalıdır.

- Oyun bir kullanıcı tarafından bilgisayara karşı oynanır.

- Hangi oyuncunun hangi harfi alacağına ve kimin başlayacağına random karar verilir.

- Oyuncu hangi kutuya harfini girmek istediğini satır ve sütun numarasını konsoldan girerek belli eder.

- Bilgisayar hangi kutuya harf yazacağına panel üzerindeki boş kutulardan birini random seçerek karar verir.

- Panele her karakter girişinde panelin güncel hali satır ve sütun numaraları ile birlikte ve oyuncuların puan durumu ekranda gösterilir.

- Dolu kutulara harf yazılmasına izin verilmez.

##denemekismi
