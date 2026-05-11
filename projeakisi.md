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
# Akıllı Şehir Yönetim Sistemi: Teknoloji Değerlendirme Raporu

**Proje Hedefi:** Şehirdeki trafik akışını optimize eden, enerji tüketimini izleyen ve acil durum müdahale sürelerini kısaltan; **simüle edilmiş sensör verilerini** analiz ederek yapay zeka ile karar alan bir sistem geliştirmek.

> **Önemli Not:** Bu projede gerçek fiziksel sensörümüz bulunmamaktadır. Tüm sensör verileri (trafik yoğunluğu, hava kalitesi — PM2.5/CO2/NO2, gürültü, enerji tüketimi vb.) Python ile **rastgele (random) üretilerek simüle edilecektir.** Yazılan her kod, ileride gerçek donanıma bağlanmaya hazır şekilde tasarlanacaktır.

---

## 1. Arka Plan (Backend) ve Ana Programlama Dili
Simüle edilen sensör verilerinin üretilmesi, işlenmesi, algoritmaların çalıştırılması ve yapay zeka modelleriyle entegrasyon için bir arka plan diline ihtiyaç vardır.

| Teknoloji | Avantajlar | Dezavantajlar | Karar & Değerlendirme |
| :--- | :--- | :--- | :--- |
| **Python** *(Seçilen)* | Veri bilimi ve yapay zeka (TensorFlow) ile kusursuz entegrasyon. Random veri üretimi için `random`, `numpy.random`, `Faker` gibi zengin kütüphane desteği. | Diğer dillere göre işlem hızı (execution time) daha yavaş olabilir. | **Kesinlikle Uygun.** Hem sensör simülatörünü yazmak hem de DQN modelini eğitip çalıştırmak için en mantıklı seçimdir. API katmanı için **FastAPI** kullanılacaktır. |
| **Go (Golang)** | İnanılmaz hızlı, eşzamanlı (concurrent) işlemlerde çok başarılı. | Makine öğrenmesi ekosistemi zayıf. | Veriler simüle edileceği için milyonlarca eşzamanlı bağlantı gereksinimi yok. Bu projede gerek yoktur. |
| **Node.js** | G/Ç (I/O) işlemlerinde başarılı, asenkron yapısıyla hızlı. | Yoğun CPU ve matematiksel işlemler (AI) için uygun değil. | Python varken ve AI odaklı bir proje iken birincil dil olması önerilmez. |

## 2. Yapay Zeka ve Makine Öğrenmesi
Trafik ışığı sürelerini optimize etme, anormal enerji tüketimi tespiti, acil araç için yeşil dalga gibi "akıllı" özellikler için.

| Teknoloji | Avantajlar | Dezavantajlar | Karar & Değerlendirme |
| :--- | :--- | :--- | :--- |
| **TensorFlow + DQN** *(Seçilen)* | Üretime alma konusunda çok güçlü. **Derin Q-Ağı (DQN)** ile trafik ışığı sürelerini pekiştirmeli öğrenme yoluyla optimize eder. Eğitim sonrası milisaniye düzeyinde karar alır. | Öğrenme eğrisi PyTorch'a göre biraz daha diktir. DQN eğitimi için yeterli miktarda simüle veri gerekir. | **İdeal Seçim.** Sistem başlangıçta kural tabanlı çalışacak, yeterli simüle veri toplandıktan sonra DQN devreye girecek (hibrit yaklaşım). Bu, soğuk başlangıç sorununu çözer. |
| **PyTorch** | Araştırma ve model geliştirme sürecinde daha esnek ve Pythonic. | Üretim/dağıtım TensorFlow kadar olgun değil. | Alternatif olarak değerlendirilebilir ancak canlıya alma kolaylığı için TensorFlow bir adım önde. |
| **Diğer Alternatifler** *(Karşılaştırıldı)* | Kural Tabanlı, Genetik Algoritma, Bulanık Mantık, basit Q-Learning. | Kural tabanlı öğrenemez; genetik algoritma çok yavaş; bulanık mantık kısıtlı; Q-Learning karmaşık durumlarda yetersiz. | Karşılaştırma sonucu **DQN** seçildi. Kural tabanlı yaklaşım yalnızca yedek plan ve soğuk başlangıç için kullanılacak. |

## 3. Veritabanı ve Veri Depolama
Projede hem ilişkisel verilere (kullanıcılar, cihaz bilgileri, alınan kararlar) hem de simülatörden üretilen sensör okumalarına ihtiyaç vardır.

| Teknoloji | Avantajlar | Dezavantajlar | Karar & Değerlendirme |
| :--- | :--- | :--- | :--- |
| **PostgreSQL** *(Seçilen)* | Çok güvenilir, ACID uyumlu, coğrafi veriler için (PostGIS) mükemmel destek sunar. Simüle veri hacmi PostgreSQL ile rahatlıkla yönetilebilir. | Milyarlarca satırlık gerçek sensör verisinde tek başına hantal kalabilir (bu proje için sorun değil). | **Mükemmel Seçim.** Şehir haritalandırması için **PostGIS** eklentisi kullanılacaktır. İleride gerçek sensörlere geçildiğinde **TimescaleDB** eklentisi eklenebilir; şimdilik gerek yoktur. |
| **MongoDB** | Esnek şema yapısı, verileri JSON formatında hızlı yazma. | Kompleks sorgular ve ilişkisel verilerde zorluk yaratır. | Bu projede PostgreSQL varken gerek yoktur. |

## 4. Frontend (Belediye Yönetim Paneli)
Belediye yetkililerinin şehir durumunu haritalar, grafikler ve alarm panelleri üzerinden izleyeceği dashboard ekranları için.

| Teknoloji | Avantajlar | Dezavantajlar | Karar & Değerlendirme |
| :--- | :--- | :--- | :--- |
| **React** *(Seçilen)* | Bileşen tabanlı mimari, devasa ekosistem. Harita ve veri görselleştirme kütüphaneleri (Deck.gl, React-Leaflet, Recharts) çok güçlü. | Sadece bir UI kütüphanesidir; state yönetimi vb. için ek araçlar gerektirir. | **En İyi Seçim.** Trafik yoğunluk haritası, enerji tüketim grafikleri ve çevresel sensör panelleri için biçilmiş kaftandır. |
| **Angular** | Tam teşekküllü bir framework, büyük kurumsal projelerde katı ve düzenli bir yapı sunar. | Öğrenmesi zor, geliştirme süreci React'e göre daha yavaştır. | React halihazırda seçilmişken değiştirmeye gerek yok. |

## 5. Mobil Uygulama (Vatandaş Uygulaması)
Vatandaşların trafik durumunu harita üzerinde görebildiği, planlı enerji kesintilerini ve belediye duyurularını takip edebildiği uygulama için.

| Teknoloji | Avantajlar | Dezavantajlar | Karar & Değerlendirme |
| :--- | :--- | :--- | :--- |
| **Native Android (Kotlin)** *(Seçilen)* | Android platformunda yüksek performans; Google Maps SDK ve push bildirim (FCM) desteği yerel düzeyde mükemmel çalışır. | Yalnızca Android; iOS için ayrı geliştirme gerekir. | **Uygun Seçim.** Anlık bildirim ve harita performansı kritik olduğundan Native Android tercih edildi. |
| **Flutter / React Native** | Tek kod tabanı ile hem iOS hem Android için geliştirme. | Anlık bildirim ve harita performansında native'in gerisinde kalır. | İleride iOS desteği gerekirse yeniden değerlendirilebilir. |

## 6. İletişim Protokolü (Modüller Arası)
Simülatör → Backend → Panel/Mobil veri akışı için bir iletişim katmanına ihtiyaç vardır.

* **MQTT (örn. Eclipse Mosquitto):** Python simülatörünün ürettiği "sensör" verilerini FastAPI'ye iletmek için kullanılacaktır. Bu sayede ileride gerçek IoT donanımına geçildiğinde aynı protokol kullanılabilir; kod gelecekteki donanım entegrasyonuna **hazır** kalır.
* **WebSocket:** React paneline ve mobil uygulamaya anlık bildirim göndermek, değişen verileri canlı olarak yansıtmak için kullanılacaktır.

> **Not:** Bu projede Apache Kafka gibi yüksek hacimli veri akış sistemlerine gerek yoktur, çünkü veriler gerçek sensörlerden değil Python simülatöründen (random) üretilmektedir. İleride gerçek şehir ölçekli sensörlere geçilirse Kafka yeniden değerlendirilebilir.

---

## 7. Önerilen Sistem Mimarisi Özeti
Seçilen teknoloji yığını ile **simüle veriye dayalı** Akıllı Şehir veri akışı:

1.  **Veri Üretimi (Simülasyon):** Python sensör simülatörü *(random / numpy.random)* `->` MQTT
2.  **Arka Plan & Yapay Zeka:** Python (FastAPI) + TensorFlow / **DQN** *(MQTT'den okur, kural tabanlı + DQN ile karar alır)*
3.  **Veritabanı:** PostgreSQL *(PostGIS eklentisi ile; haritalama için)*
4.  **Belediye Paneli:** React *(WebSocket ile anlık güncellenir)*
5.  **Vatandaş Uygulaması:** Native Android (Kotlin) *(FCM ile anlık bildirim alır)*

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
# Akıllı Şehir Vatandaş Mobil Uygulaması — Gereksinim Analizi ve Tasarım Belgesi

> **Bağlam:** Bu belge, Akıllı Şehir Yönetim Sistemi projesinin vatandaş mobil uygulamasının gereksinimlerini, platform stratejisini, kullanıcı hikayesi haritasını, kullanıcı akışlarını, etkileşim modelini ve güvenlik & gizlilik politikasını tanımlar. Belge; Hafta 1'de seçilen teknolojiler (Native Android/Kotlin, FastAPI, PostgreSQL/PostGIS, FCM, simüle veri) ve Abdullah Gümüş'ün Hafta 2 veritabanı mimarisi (`users`, `sensors`, `sensor_readings`, `incidents` tabloları; RLS + RBAC) ile uyumludur.

---

## 1. Uygulamanın Amacı ve Kapsamı

Vatandaş mobil uygulaması; şehir sakinlerinin **trafik durumu**, **enerji tüketim/kesinti bilgileri**, **hava kalitesi** ve **acil durum/olay bildirimleri** gibi verilere tek bir kanaldan, **konum bazlı** ve **anlık** olarak erişebileceği bir platformdur. Uygulama aynı zamanda iki yönlü çalışır: vatandaş yalnızca veri tüketmez, kendi gözlemlediği bir kaza, çukur, su baskını, aydınlatma arızası gibi durumları belediyeye **konum ve fotoğraf ile bildirebilir**.

Uygulamanın temel değer önerileri:
- **Şeffaflık:** Belediye yetkililerinin aldığı kararlar (yol kapatma, planlı kesinti, alarm) anında vatandaşa ulaşır.
- **Hız:** Acil durumlar push bildirim olarak < 5 saniyede iletilir.
- **Katılım:** Vatandaş hem bilgi alır hem bildirim yaparak şehir yönetimine katkıda bulunur.

---

## 2. Platform Stratejisi

### 2.1. Faz 1 (MVP) — Native Android / Kotlin

Hafta 1 teknoloji değerlendirmesinde alınan karar doğrultusunda **MVP yalnızca Android için** geliştirilecektir. Gerekçeler:

- Türkiye'de Android pazar payı %75'in üzerindedir; tek platform ile vatandaşların büyük çoğunluğuna ulaşılır.
- Native geliştirme; **Google Maps SDK**, **Firebase Cloud Messaging (FCM)** push bildirim ve **konum servisleri** ile en yüksek performansta entegrasyon sunar.
- Proje takvimi ve ekip kaynağı tek platforma odaklanmayı gerektirir.

**Teknik kısıtlar:**

| Parametre | Değer |
| :--- | :--- |
| Dil | Kotlin |
| UI | Jetpack Compose (modern, deklaratif) |
| Mimari | MVVM + Clean Architecture |
| Minimum SDK | Android 8.0 (API 26) — pazarın ~%95'ini kapsar |
| Hedef SDK | Android 14 (API 34) |
| Harita | Google Maps SDK + Maps Compose |
| Push | Firebase Cloud Messaging (FCM) |
| Ağ | Retrofit + OkHttp (REST), OkHttp WebSocket |
| Yerel depolama | Room (SQLite) + EncryptedSharedPreferences |
| DI | Hilt |

### 2.2. Faz 2 (Yol Haritası) — iOS

iOS desteği projenin **ikinci versiyon hedefidir.** İki seçenek değerlendirilecektir:

- **Native iOS (Swift + SwiftUI):** En yüksek kullanıcı deneyimi, ancak iki ayrı kod tabanı bakım maliyeti.
- **Kotlin Multiplatform (KMP):** İş katmanı paylaşılır, UI native kalır. Android kodunun büyük kısmı yeniden kullanılabilir.

KMP, Faz 1'de yazılan Kotlin altyapısının iOS'a taşınabilmesini sağlayacağı için tercih edilen yaklaşımdır.

---

## 3. Hedef Kullanıcı Personaları

| Persona | Profil | Birincil İhtiyaç |
| :--- | :--- | :--- |
| **Ahmet — Sürücü** | 35, ofise araba ile gidip gelen | Yoğunluk haritası, alternatif rota, kapatılan yollar |
| **Ayşe — Çevreci Vatandaş** | 28, çevre/sağlık duyarlı | Hava kalitesi, gürültü seviyesi, sağlık önerileri |
| **Mehmet — Bölge Sakini** | 55, mahallesindeki kesintilere duyarlı | Planlı enerji kesintileri, belediye duyuruları |
| **İrem — Aktif Bildirici** | 22, gözleyip bildiren tip | Kaza, çukur, su baskını gibi olayları kolayca raporlama |
| **Yetkili Operatör (dolaylı)** | Belediye paneli kullanıcısı | Mobilden gelen bildirimleri panelden yönetir |

---

## 4. Fonksiyonel Gereksinimler (Özellikler)

### 4.1. Trafik Haritası

- Şehir haritası üzerinde **renk kodlu yoğunluk** katmanı (yeşil/sarı/kırmızı/koyu kırmızı).
- Veriler, simülatör tarafından üretilen ve FastAPI üzerinden gelen **`sensor_readings`** tablosundaki `metrics` (JSONB) alanından okunur (örn. `{"speed": 22, "vehicle_count": 145}`).
- **Kapatılan yollar** ve aktif olaylar harita üzerinde özel ikonlarla gösterilir (kaynak: `incidents` tablosu, `status='active'`).
- Kullanıcı bir yol/kavşak ikonuna dokununca **bottom sheet** açılır: ortalama hız, yoğunluk seviyesi, son güncelleme zamanı.
- **Alternatif rota önerisi**: kullanıcı başlangıç-bitiş seçer, sistem yoğunluğa göre en hızlı rotayı işaretler.
- Harita üzerinde toplu taşıma katmanı (opsiyonel, Faz 2).

### 4.2. Enerji Tüketimi ve Kesinti Takibi

- Mahalle/bölge bazlı **anlık enerji tüketim grafiği** (saatlik, günlük, haftalık).
- **Planlı kesintiler:** Belediye panelinden girilen kesintiler liste + harita üzerinde görüntülenir; başlangıç-bitiş, etkilenen bölge, neden alanları gösterilir.
- **Bildirim:** Kullanıcının kayıtlı bölgesinde planlı kesinti girildiğinde push bildirimi gider.
- **Tasarruf önerileri** ve belediye kampanyaları için bilgi kartları.

### 4.3. Acil Durum ve Olay Bildirimleri

İki yönlü modüldür.

**Vatandaşa giden (gelen) bildirimler:**
- Kategori: Trafik kazası, yangın, sel, sağlık, altyapı (su/elektrik), hava durumu uyarısı.
- Konum filtresi: yalnız kullanıcının seçtiği yarıçap içindeki olaylar (örn. 2 km / 5 km / şehir geneli).
- **Önem seviyeleri:** Bilgi (mavi), Uyarı (sarı), Kritik (kırmızı). Kritik bildirimler "high-priority" FCM olarak iletilir ve sessiz modu aşar.

**Vatandaştan gelen (giden) bildirimler:**
- Hızlı bildirim FAB (Floating Action Button) ana ekranda her zaman görünür.
- Kategori seç → konum (otomatik veya manuel pin) → fotoğraf çek/yükle (en fazla 3 adet) → kısa açıklama → gönder.
- Bildirim Abdullah'ın `incidents` tablosuna `reported_by = users.id`, `status = 'pending'` olarak kaydedilir.
- **Anonim bildirim** seçeneği: kullanıcı kimliği gizlenir, `reported_by = NULL` (sadece konum + içerik saklanır).
- Gönderim sonrası kullanıcı bildiriminin durumunu (pending / acknowledged / resolved) takip edebilir.

### 4.4. Hava Kalitesi

- Konum bazlı **AQI (Hava Kalitesi İndeksi)** kart gösterimi.
- Ölçülen metrikler: **PM2.5, PM10, CO2, NO2** (simüle veri).
- Saatlik geçmiş ve günlük tahmin.
- AQI seviyesine göre **sağlık önerileri** (örn. "Hassas gruplar dışarıda yoğun aktiviteden kaçınmalı").

### 4.5. Belediye Duyuruları

- Kronolojik liste; kategoriye göre filtre (Genel, Ulaşım, Etkinlik, Acil).
- Detay ekranında foto/video, link, harita konumu (opsiyonel).
- "Önemli" işaretli duyurular için push bildirim.

### 4.6. Hesap ve Profil

- E-posta + şifre ile kayıt/giriş (`users` tablosu üzerinden, Abdullah'ın şemasıyla).
- Misafir/anonim mod: kayıt olmadan trafik ve hava bilgilerini görüntüleme.
- Profil: ad, kayıtlı bölge(ler), bildirim tercihleri (kategori bazlı toggle), dil (TR/EN), tema (otomatik/aydınlık/karanlık).
- Şifre değiştirme, hesabı silme (KVKK gereği).

---

## 5. Etkileşim Modeli (UX)

### 5.1. Genel Yapı

- **Alt navigasyon (Bottom Navigation)** — 5 sekme:
  1. **Ana Sayfa** — Konum bazlı özet kartlar (trafik, hava, son duyuru, yakındaki olaylar).
  2. **Harita** — Tam ekran trafik + olay haritası.
  3. **Bildir** (merkezde, vurgulu FAB) — Hızlı olay bildirimi.
  4. **Duyurular** — Belediye duyuruları + bildirimler tarihçesi.
  5. **Profil** — Hesap, ayarlar.

### 5.2. Tasarım Dili

- **Material Design 3 (Material You)** — dinamik renk teması.
- Karanlık mod (Android sistem ayarını izler).
- Yazı tipi: Roboto (Android varsayılan), 14-16 sp gövde, 20 sp başlık.
- **Erişilebilirlik:** Talkback uyumu, en az 4.5:1 kontrast oranı, dokunma hedefi minimum 48 dp.

### 5.3. Etkileşim Desenleri

- Harita ve listelerde **pull-to-refresh**.
- Detay ekranları **bottom sheet** ile (haritayı kapatmadan).
- Kritik bildirimler için **uyarı diyaloğu + titreşim**.
- Boş durumlar (no internet, no incidents) için açıklayıcı illüstrasyonlar.

---

## 6. Kullanıcı Hikayesi Haritası (User Story Map)

Yapı: en üstte **kullanıcı etkinlikleri (backbone)**, altında **kullanıcı görevleri**, en altta **release planı**.

```
═══════════════════════════════════════════════════════════════════════════════════════
 BACKBONE        Uygulamaya     Şehri          Bilgi          Olay            Hesabı
 (Etkinlikler)   Giriş          Keşfetme       Alma           Bildirme        Yönetme
═══════════════════════════════════════════════════════════════════════════════════════
 GÖREVLER        • Onboarding   • Haritayı     • Bildirim     • Kategori      • Profil
                 • İzinler        gezme          alma           seçme          • Tercihler
                 • Kayıt/Giriş  • Konuma       • Detay         • Konum         • Dil/Tema
                 • Misafir        gitme          görme          seçme          • Çıkış
                   moda
                                • Yoğunluğu    • Geçmişe      • Foto
                                  okuma          bakma          yükleme
                                • Olayları
                                  görme        • Filtreleme   • Açıklama
                                                              • Gönderme
═══════════════════════════════════════════════════════════════════════════════════════
 MVP            ★ Splash       ★ Harita       ★ Push         ★ Hızlı         ★ E-posta
 (Sürüm 1)        ★ 3 ekran      katmanı        bildirim       bildirim        kayıt/giriş
                 ★ Konum+Bild   ★ Trafik       ★ Bildirim     ★ Kategori +    ★ Bildirim
                   izni           renk           listesi        konum +        tercihleri
                 ★ Giriş ekr.     kodlama        + detay        foto           (kategori
                                ★ Olay                         ★ Pending        bazlı)
                                  pinleri                        durum
                                                                 takibi
─────────────────────────────────────────────────────────────────────────────────────
 Sürüm 1.1      • Misafir      • Alternatif   • Hava         • Anonim        • Karanlık
                   mod            rota           kalitesi       bildirim        mod
                 • OTP                           kartı                          • Dil (EN)
                                                                                 desteği
─────────────────────────────────────────────────────────────────────────────────────
 Sürüm 2        • Sosyal       • Toplu        • Akıllı       • Sesli         • Hesabı
                   giriş          taşıma         tahminler      bildirim        silme
                   (Google)       katmanı                       (mikrofon)      (KVKK)
                 • iOS                          • Widget'lar
═══════════════════════════════════════════════════════════════════════════════════════
```

---

## 7. Kullanıcı Akışları (User Flows)

### 7.1. İlk Açılış / Onboarding Akışı

```
[Splash 1.5 sn]
      │
      ▼
[Onboarding — 3 ekran] ──── kullanıcı "Geç" der ───┐
      │ "Devam"                                    │
      ▼                                            │
[Konum İzni iste]                                  │
      │                                            │
      ├── İzin verdi ──┐                          │
      │                ▼                          │
      │           [Bildirim İzni iste]            │
      │                │                          │
      │                ├── İzin verdi ─────────┐  │
      │                └── Reddetti ───────────┤  │
      ├── Reddetti ─────────────────────────────┤  │
                                                ▼  ▼
                                       [Giriş Ekranı]
                                                │
                                  ┌─────────────┼──────────────┐
                                  ▼             ▼              ▼
                              [Kayıt Ol]    [Giriş Yap]   [Misafir devam]
                                  │             │              │
                                  └─────────────┴──────────────┘
                                                │
                                                ▼
                                          [Ana Sayfa]
```

### 7.2. Trafik Haritası Görüntüleme Akışı

```
[Ana Sayfa] ──► [Harita sekmesi]
                       │
                       ▼
              [Konum alınır, kullanıcıya yakınlaştırılır]
                       │
                       ▼
              [Sensor verisi REST ile çekilir]
                       │
                       ▼
              [Yoğunluk renkleri + olay pinleri render]
                       │
              ┌────────┴────────┐
              ▼                 ▼
       [Yol/kavşağa     [Olay pinine
        dokun]            dokun]
              │                 │
              ▼                 ▼
       [Bottom sheet:    [Bottom sheet:
        hız, yoğunluk,    olay türü, zamanı,
        son güncelleme]   açıklama, foto]
              │                 │
              ▼                 ▼
       [Rota iste]       [Yol tarifi al]
              │                 │
              ▼                 ▼
       [Alternatif        [Harici harita
        rota çizilir]      uygulamasını aç]
```

### 7.3. Acil Olay Bildirme Akışı

```
[Herhangi bir ekran] ──► [FAB "Bildir" butonu]
                                  │
                                  ▼
                         [Kategori seçimi:
                          Kaza / Yangın / Sel /
                          Sağlık / Altyapı / Diğer]
                                  │
                                  ▼
                         [Konum belirle:
                          ◉ Otomatik (GPS)
                          ○ Haritadan pin ile]
                                  │
                                  ▼
                         [Fotoğraf ekle (0-3)]
                          • Kameradan çek
                          • Galeriden seç
                                  │
                                  ▼
                         [Kısa açıklama (max 500 karakter)]
                                  │
                                  ▼
                         [☐ Anonim olarak gönder]
                                  │
                                  ▼
                         [Önizleme + Gönder]
                                  │
                       ┌──────────┴──────────┐
                       ▼                     ▼
                  [Başarılı]            [Hata]
                       │                     │
                       ▼                     ▼
                  [Onay ekranı:         [Tekrar dene /
                   "Bildirimin           Taslak kaydet]
                   gönderildi,
                   takip no: #1234"]
                       │
                       ▼
                  [Bildirimlerim
                   listesinde takip et]
```

### 7.4. Push Bildirim Alma Akışı

```
[FCM Push Geldi] ──► [Sistem bildirimi]
                            │
              ┌─────────────┴─────────────┐
              ▼                           ▼
       [Bildirime tıkla]            [Sürükle - sil]
              │
              ▼
       [Uygulama açılır]
              │
       ┌──────┴──────┐
       ▼             ▼
   [Açıksa]      [Kapalıysa cold start]
       │             │
       └──────┬──────┘
              ▼
       [İlgili detay ekranı:
        • Olay → Harita + bottom sheet
        • Kesinti → Enerji ekranı
        • Duyuru → Duyuru detayı]
              │
              ▼
       [Kullanıcı tepkisi:
        Paylaş / Bildirimi kapat /
        Detayda gez]
```

### 7.5. Enerji Kesinti Sorgulama Akışı

```
[Ana Sayfa] ──► [Enerji kart] ──► [Enerji ekranı]
                                          │
                                          ▼
                                  [Kayıtlı bölge listesi]
                                          │
                                          ▼
                                  [Bölge seç]
                                          │
                                          ▼
                                  [Planlı kesintiler tarihçesi
                                   + canlı tüketim grafiği]
                                          │
                                          ▼
                                  [Yaklaşan kesinti detayı:
                                   tarih/saat aralığı, neden]
                                          │
                                          ▼
                                  [☐ Bu bölgeyi takip et
                                   ☐ Hatırlatıcı kur]
```

---

## 8. Fonksiyonel Olmayan Gereksinimler

| Kategori | Gereksinim |
| :--- | :--- |
| **Performans** | Soğuk başlangıç < 3 sn, sıcak başlangıç < 1 sn. Harita ilk render < 2 sn. |
| **Akıcılık** | UI 60 fps; Compose recomposition gözle görülür gecikmesiz. |
| **Ağ** | Tüm REST çağrılarına 10 sn timeout; başarısız olursa exponential backoff. |
| **Offline** | Son trafik anlık görüntüsü ve son 10 bildirim Room ile cachelenir; offline'da görüntülenir. |
| **Pil** | Konum güncellemesi yalnızca uygulama açık ve harita aktifken; arka planda yalnızca push tetikli. |
| **Veri kullanımı** | Harita tile + sensör verisi günlük < 30 MB hedeflenmeli; Wi-Fi-only modu opsiyonel. |
| **Çökme** | Crash-free user oranı > %99.5 (Firebase Crashlytics ile izlenir). |
| **Erişilebilirlik** | TalkBack tam destek; en az 4.5:1 kontrast; dinamik yazı boyutu. |
| **Lokalizasyon** | TR ana, EN Faz 1.1'de. Tarih/saat cihaz lokalini izler. |
| **Bağımlılıklar** | Backend REST/WebSocket, FCM, Google Maps SDK, PostgreSQL `users` & `incidents` tabloları (Abdullah'ın şeması). |

---

## 9. Güvenlik ve Gizlilik Gereksinimleri

### 9.1. Yasal Çerçeve

- **6698 sayılı KVKK** (Kişisel Verilerin Korunması Kanunu) uyumu zorunludur.
- İlk açılışta açık rıza içeren **Aydınlatma Metni** ve **Gizlilik Politikası** kullanıcıya sunulur; reddedilirse misafir moda geçilir.

### 9.2. Kimlik Doğrulama

- E-posta + şifre ile kayıt (Abdullah'ın `users` tablosundaki `email`, `password_hash` alanları).
- Şifre kuralı: min 8 karakter, en az 1 harf + 1 rakam.
- **JWT erişim token (15 dakika) + refresh token (30 gün)** modeli. Refresh token rotation aktif.
- Şifre sıfırlama e-posta üzerinden tek kullanımlık link.
- Faz 1.1: SMS OTP ile iki faktörlü doğrulama (opsiyonel).

### 9.3. Veri İletimi ve Depolama

- Tüm ağ iletişimi **TLS 1.3** üzerinden HTTPS.
- **Certificate pinning** (OkHttp ile) — sahte sertifikalı MITM saldırılarını engeller.
- Token'lar **Android Keystore + EncryptedSharedPreferences** ile saklanır; düz metin asla depolanmaz.
- Yerel cache (Room) hassas içerik için **SQLCipher** ile şifrelenir.

### 9.4. Veri Minimizasyonu

- Yalnızca uygulamanın çalışması için gerekli veriler toplanır:
  - Konum: yalnız uygulama açık iken ("while in use"), arka plan konumu istenmez.
  - Kamera: yalnız kullanıcı "Bildirim yap → foto ekle" derken aktif olur.
  - Telefon, kişiler, takvim, mikrofon: **istenmez.**
- Reklam kimliği toplanmaz; üçüncü taraf analitik ya hiç kullanılmaz ya da kullanıcı opt-in seçer.

### 9.5. Yetkilendirme (Backend Tarafında)

- Abdullah'ın belirlediği **Row-Level Security (RLS)** politikaları: bir vatandaş yalnızca `incidents` tablosunda `reported_by = current_user.id` olan kayıtlarını detaylı görebilir; diğerlerini anonim kamuya açık alan olarak görür.
- **RBAC:** `role = 'citizen'` rolü; sensör tablolarına yazma yetkisi yoktur, yalnızca okuma; `incidents` tablosuna yalnızca INSERT.

### 9.6. Anonim Bildirim

- Kullanıcı "anonim gönder" seçtiğinde `reported_by` alanı NULL gönderilir; istemcide bu seçimden sonra bildirim geçmişinde bu kayıt görüntülenmez.
- Bildirimin meta verisi (cihaz IP'si, kullanıcı kimliği) backend tarafında loglanmaz; pgaudit konfigürasyonu bu uç noktayı hariç tutar.

### 9.7. Çocuk ve Hassas İçerik Koruması

- Kayıt sırasında yaş beyanı alınır (13+ önerilir). 18 yaş altı kullanıcılar için reklam ve analytics opt-out otomatik aktiftir.
- Vatandaş bildirimlerinde yüklenen fotoğraflar **moderasyon kuyruğuna** girer; uygunsuz/yüz içeren içerik otomatik blur veya manuel inceleme ile kontrol edilir.

### 9.8. Veri Saklama Süreleri ve Silme

- Kullanıcı "Hesabımı sil" dediğinde 30 gün içinde tüm kişisel veriler kalıcı silinir.
- Anonim ve agregeli bildirim verileri (istatistik amaçlı) saklanmaya devam edebilir.
- Konum geçmişi cihazda **yalnızca son 7 gün** tutulur; backend uzun süreli konum geçmişi tutmaz.

### 9.9. Olay Yanıt Planı

- Veri ihlali tespit edildiğinde KVKK gereği **72 saat içinde** Kişisel Verileri Koruma Kurulu'na bildirilir, etkilenen kullanıcılara push + e-posta ile bilgi verilir.
- Tüm güvenlik logları Abdullah'ın belirttiği **SOC/SIEM** platformuna yönlendirilir.

---

## 10. Bağımlılıklar ve Riskler

| Bağımlılık / Risk | Etki | Önlem |
| :--- | :--- | :--- |
| FastAPI backend ve WebSocket altyapısı hazır olmalı | Yüksek | Mock servisle paralel geliştirme yap |
| FCM proje konfigürasyonu | Yüksek | Hafta 3 başında tamamlanmalı |
| Google Maps API kotası | Orta | Aylık 28.500 ücretsiz yükleme; aşılırsa fallback OSM (OpenStreetMap) |
| Simüle verinin gerçekçi dağılımı | Orta | Hafta 1'de tanımlanan saat/hava modeline sadık kalın |
| Veritabanı şema değişiklikleri | Düşük | Abdullah'ın tablolarına versiyonlu migration ile uyum |

---

## 11. Sonuç ve Sonraki Adımlar

Bu belge ile uygulamanın **ne yapacağı, kimin için olduğu, nasıl davranacağı ve hangi sınırlar içinde kalacağı** netleştirilmiştir. Hafta 3 ve sonrasında:

1. Düşük çözünürlüklü ekran (wireframe) ve yüksek çözünürlüklü (mockup) tasarımlar Figma'da çıkarılacak.
2. REST/WebSocket sözleşmeleri (OpenAPI şeması) backend ekibiyle netleştirilecek.
3. Kotlin proje iskeleti (modüller, Hilt setup, navigasyon iskeleti) kurulacak.
4. MVP özellik listesinden başlanarak iteratif sprint planı çıkarılacak.

## Cemile Akay
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Efecan Önal
GÖREVİN BURAYA YAPIŞTIRILACAK.

# Hafta 3

## Nisanur Eltekin
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Abdullah Gümüş
# Akilli Sehir Yonetim Sistemi - API Entegrasyon ve Iletisim Semasi

Belediye yonetim panelinin arka uc sistemleriyle kesintisiz ve guvenli iletisim kurabilmesi icin modern bir RESTful API mimarisi kurgulanmistir. Arka uc catisi olarak asenkron yapisi ve yerlesik Swagger destegi nedeniyle FastAPI tercih edilmistir.

## Uc Nokta (Endpoint) Tasarimlari ve Veri Akisi

API uc noktalari sistemin temel modullerine gore versiyonlandirilarak tasarlanmistir. Tum veri alisverisi JSON formatinda yapilacaktir.
* Kimlik Dogrulama (Auth): Belediye yetkililerinin sisteme guvenli girisi icin JWT uretilir.
* Sensor Verileri: Yonetim panelindeki haritalari ve grafikleri besleyen gercek zamanli ve gecmis sensor okumalarini getirir.
* Yapay Zeka Ongoruleri: TensorFlow modelini tetikleyerek trafik yogunlugu tahmini ve sinyalizasyon optimizasyonu onerisi sunar.
* Acil Durum Yonetimi: Bekleyen olaylari listeler ve durum guncellemelerini saglar.

## API Guvenlik Stratejisi

* JWT Yetkilendirmesi: Basarili giristen sonra alinan token ile her istekte rol kontrolu yapilir.
* CORS: API, yalnizca resmi yonetim paneli domain'lerinden gelen istekleri kabul eder.
* Hiz Sinirlandirma: Saldirilari onlemek icin IP basina yapilabilecek maksimum API cagrisi sinirlandirilir.

## Performans Optimizasyonu

* Veri Onbellekleme: Sik sorgulanan gecmis veriler icin Redis onbelleklemesi kullanilir.
* Sayfalandirma: Liste donduren tum GET istekleri sayfalalandirilir.
* Asenkron Islemler: Uzun suren TensorFlow hesaplamalari asenkron olarak calistirilir.

## Standart Hata Yonetimi ve Dokumantasyon

* Hata Yonetimi: Tum hatalar HTTP durum kodlariyla JSON formatinda dondurulur.
* Swagger UI: Gelistirici ekiplerin senkronizasyonu icin interaktif Swagger paneli sunulur.

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
