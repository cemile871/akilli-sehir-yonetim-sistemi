# Hafta 1

## Nisanur Eltekin
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Abdullah Gümüş
## Akıllı Şehir Yönetim Sistemi - Gereksinimler
---

## 1. Projenin Amacı ve Kapsamı
Bu projenin temel amacı; günümüz metropollerinde yaşanan trafik sıkışıklığı, aşırı enerji tüketimi ve acil durumlara geç müdahale gibi problemlere yazılım odaklı çözümler getiren entegre bir "Akıllı Şehir Yönetim Sistemi" geliştirmektir. Proje kapsamında; verilerin toplanması, makine öğrenmesi algoritmalarıyla işlenmesi, belediye yetkilileri tarafından yönetilmesi ve son kullanıcıya (vatandaşlara) anlık olarak ulaştırılmasını sağlamaktır.

Bir üniversite projesi olması sebebiyle, şehrin farklı noktalarından gerçek zamanlı fiziksel sensör verisi toplamak mümkün olamamaktadır. Bu kısıtlamayı aşmak adına, sistemin uçtan uca çalışabilirliğini göstermek için arka planda gerçeğe yakın, randomize sensör verileri üreten bir simülasyon modülü geliştirilmelidir.

## 2. Kullanılan Teknolojiler ve Sistem Mimarisi
Projenin farklı gereksinimleri için en uygun teknolojiler seçilerek modüler bir yapı kurulmalıdır:
* **Veri Bilimi ve Makine Öğrenmesi:** Python, TensorFlow (Trafik ve enerji verisi analizi)
* **Veritabanı Yönetimi:** PostgreSQL (Kapsamlı ve ilişkisel veri tutma)
* **Web Frontend (Yönetim Panelleri):** React (Hızlı ve dinamik arayüzler)
* **Mobil Uygulama:** Native Android / Kotlin (Vatandaşlar için yüksek performanslı yerel deneyim)
* **Backend ve API:** Python FastAPI (Web ve mobil platformlar arası köprü)



## 3. Sistem Modülleri ve Gerçekleştirim

### 3.1. Veri Toplama ve Analiz Modülü (Simülasyon)
Donanımsal sensör eksikliğini gidermek için Python tabanlı bir veri simülatörü kodlanmalıdır. Bu simülatör, günün farklı saatlerine, hava durumuna ve bölgesel özelliklere göre (örneğin sabah iş saatlerinde ana arterlerde trafiği artırıp gece saatlerinde düşürmek) rastgele ancak mantıklı veri setleri üretmelidir. Üretilen bu veriler doğrudan PostgreSQL veritabanına yazılarak sistemin beslenmesi sağlanmalıdır.

### 3.2. Trafik Optimizasyon Algoritması
Simülatörden gelen araç yoğunluğu ve hız verileri, TensorFlow kullanılarak eğitilmiş model üzerinden geçirilmelidir. Algoritma;
* Kavşaklardaki trafik ışıklarının sürelerini yoğunluğa göre dinamik olarak ayarlamalıdır.
* Sisteme düşen bir ambulans veya itfaiye rotası için "yeşil dalga" oluşturarak acil durum müdahale sürelerini kısaltmalıdır.

### 3.3. Yönetim Paneli ve Enerji Yönetimi (React)
Belediye yetkililerinin sistemi kontrol edebilmesi için React ile iki ana web paneli geliştirilmeli:
1.  **Yönetim Paneli:** Şehrin genel sağlık durumunun, trafik yoğunluk haritalarının ve acil durum alarmlarının izlendiği ana merkez.
2.  **Enerji Yönetimi Paneli:** Şehrin sokak aydınlatmaları ve kamu binalarındaki enerji tüketiminin izlendiği alan. Sensörlerden gelen randomize verilerle gereksiz tüketim tespit edildiğinde yetkiliye uyarı vermelidir veya sistem otonom olarak tasarruf moduna geçmelidir.

### 3.4. Vatandaş Mobil Uygulaması (Native)
Vatandaşların şehirle etkileşimini artırmak için cross-platform araçlar yerine, daha akıcı ve cihaza özgü bir deneyim sunması adına Native (Kotlin) veya Python + React bir Android uygulaması kodlanmalıdır bununla beraber kullanıcılar bu uygulama üzerinden trafik durumunu görebilmeli, kendi bölgelerindeki enerji kesintilerini veya belediye duyurularını takip edebilmelidir.

## 4. Platformlar Arası Entegrasyon ve Veri Akışı
Projenin en kritik noktalarından biri web panelleri ile mobil uygulama arasındaki gerçek zamanlı entegrasyondur. Sistemde izole çalışan hiçbir parça olmamalıdır.

**Akış Örneği:**
Belediye yetkilisi, React ile geliştirilen web tabanlı Yönetim Paneli'ne giriş yapıp bir sokağı bakım çalışması nedeniyle trafiğe kapattığında veya Enerji Paneli üzerinden bir bölge için "Planlı Kesinti" uyarısı girdiğinde; backend bu veriyi anında işlemelidir. Değişiklik, mobil uygulama tarafına anlık bildirim (push notification) olarak gönderildikten sonra uygulamanın arayüzündeki harita/duyuru ekranı doğrudan güncellenmelidir. Yani web panelinden girilen her kritik aksiyon, doğrudan vatandaşın cebindeki mobil uygulamaya entegre çalışmalıdır.

## 5. Sonuç
Bu proje ile farklı yazılım disiplinlerinin (Makine Öğrenmesi, Web Geliştirme, Native Mobil Geliştirme ve Veritabanı Yönetimi) birbiriyle uyumlu ve gerçek zamanlı olarak nasıl haberleşebileceği başarılı bir şekilde gösterilmelidir. Simülasyon verileriyle de olsa, kurulan mimari gerçek bir şehre entegre edilmeye hazır, ölçeklenebilir bir yapıda olmalıdır.

Akıllı şehir uygulamalarında kullanılan teknolojiler araştırıldı.


## Melih Ahmet Kocaman
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Cemile Akay
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Efecan Önal
GÖREVİN BURAYA YAPIŞTIRILACAK.

# Hafta 2

## Nisanur Eltekin
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Abdullah Gümüş
# Akilli Sehir Yonetim Sistemi - Veritabani Mimari Tasarimi

Bu proje, yuksek hacimli sensor verisi, cografi konum verisi ve standart kullanici/yonetim verisi barindiran Akilli Sehir Yonetim Sistemi icin gelistirilmis kapsamli bir veritabani mimarisini icermektedir.

## Veri Modeli ve Teknolojiler

Saniyede binlerce kayit atan sensor verilerinde tikanmalari onlemek amaciyla sistemde Hibrit Veri Modeli (Iliskisel + Zaman Serisi + Cografi) kullanilmistir.
* Veritabani yonetim sistemi olarak PostgreSQL tercih edilmis ve Table Partitioning (Tablo Bolumleme) kullanilmistir.
* Cografi verilerin islenmesi icin PostGIS uzantisi sisteme entegre edilmistir.
* Esnek veri yapilari (metrikler) icin JSONB veri tipi kullanilmistir.

## Veritabani Tablolari ve Iliskiler

Sistem butunlugunu saglamak adina temel mimari dort ana tablo uzerinden sekillendirilmistir:
* Kullanicilar (users): Sistemi yonetecek yetkililer ve vatandaslarin bilgilerini barindirir.
* Sensorler (sensors): Sehrin farkli noktalarindaki donanimlarin PostGIS koordinatlari ile tanimlandigi tablodur.
* Sensor Okumalari (sensor_readings): Surekli akan verilerin depolandigi ana zaman serisi tablosudur. Bu tablo ile sensorler arasinda 1:N (Bire Cok) iliski bulunmaktadir.
* Olaylar (incidents): Sistem tarafindan tespit edilen veya vatandaslarca bildirilen anormalliklerin tutuldugu tablodur. Kullanicilar ile olaylar arasinda 1:N (Bire Cok) iliski kurulmustur.

## Performans Optimizasyonu ve Indeksleme

Buyuk veri konseptine uygun olarak yazma performansini en ust seviyede tutmak icin spesifik indeksleme yontemleri kullanilmistir:
* BRIN Indeksi: Zaman serileri (sensor_readings) icin tercih edilmis olup, gecmis verilere yonelik toplu taramalarin cok daha hizli calismasini saglar.
* GiST Indeksi: Cografi sinirlari filtrelemek ve 2 boyutlu uzaysal sorgular yapabilmek icin konum verilerinde (location) kullanilmistir.
* GIN Indeksi: Esnek JSON belgelerinin icine girerek anahtar-deger ikililerini indekslemek icin tercih edilmistir.
* B-Tree Indeksi: Dogrudan eslesme aranan foreign key ve benzersiz e-posta gibi alanlarda kullanilmistir.

## Guvenlik ve Yedekleme Stratejisi

Kritik sehir ve kullanici verilerinin gizliligini ve erisilebilirligini guvence altina almak icin su adimlar atilmistir:
* Erisim Yonetimi: Rol Bazli Erisim Kontrolu (RBAC) ve Satir Bazli Guvenlik (RLS) politikalari uygulanmistir.
* Sifreleme: Veritabani izole bir agda (VPC) barindirilmis, TLS/SSL ve AES-256 sifrelemeleri kullanilmistir.
* Izleme ve Loglama: PostgreSQL uzerinde pgaudit eklentisi aktif edilerek log verileri Guvenlik Operasyon Merkezi'ne (SOC) yonlendirilmistir.
* Yedekleme: Continuous Archiving ve WAL dosyalari kullanilarak sistemi gecmisteki tam bir saniyeye dondurebilme (PITR) kapasitesi saglanmistir.
* Olceklenebilirlik: Sistem uzerindeki yuku dengelemek icin PgBouncer ile baglanti havuzlama (Connection Pooling) ve okuma kopyalari (Read Replicas) mimarisi devreye alinmistir.

## Melih Ahmet Kocaman
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Cemile Akay
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Efecan Önal
GÖREVİN BURAYA YAPIŞTIRILACAK.

# Hafta 3

## Nisanur Eltekin
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Abdullah Gümüş
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Melih Ahmet Kocaman
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Cemile Akay
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Efecan Önal
GÖREVİN BURAYA YAPIŞTIRILACAK.

# Hafta 4

## Nisanur Eltekin
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Abdullah Gümüş
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Melih Ahmet Kocaman
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Cemile Akay
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Efecan Önal
GÖREVİN BURAYA YAPIŞTIRILACAK.

# Hafta 5

## Nisanur Eltekin
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Abdullah Gümüş
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Melih Ahmet Kocaman
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Cemile Akay
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Efecan Önal
GÖREVİN BURAYA YAPIŞTIRILACAK.

# Hafta 6

## Nisanur Eltekin
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Abdullah Gümüş
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Melih Ahmet Kocaman
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Cemile Akay
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Efecan Önal
GÖREVİN BURAYA YAPIŞTIRILACAK.
