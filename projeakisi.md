# Hafta 1

## Nisanur Eltekin

Bu proje süreci iki ana platform üzerinden yürütülmektedir:
1. **[www.ozalyildirim.com](https://www.ozalyildirim.com)** (görev ve ekip yönetimi)
2. **GitHub** (kod geliştirme ve versiyon kontrolü)

---

## 1. Ozalyildirim Sitesi Üzerinden Proje Yönetimi

Ozalyildirim sitesi proje yönetimi için merkezi bir pano sunar. Bu platformda aşağıdaki işlemler yapılır:

### Görev Takibi
- Her görev oluşturulup ekip üyelerine atanır.
- Görev durumları "Bekleyen", "Tamamlanan", "Gecikme" olarak işaretlenir.
- Görev tamamlama bildirimleri site üzerinden takip edilebilir.

### Ekip ve İletişim
- Grup üyeleri ve roller (yönetici, üye) site üzerinden tanımlanır.
- Grup mesajlaşması ile hızlı bilgilendirme yapılabilir.

### Scrum AI Asistanı
- AI asistanı ekip performansını ve görev ilerlemesini analiz eder.
- Üyelerin görev tamamlama yüzdesi, repo aktiviteleri ve iletişim performansı gösterilir.
- Bu sayede hem bireysel hem de takım performansı anlık takip edilir.

---

## 2. GitHub Üzerinde Kod Geliştirme ve İşbirliği

### Branch ve Pull Request Süreci
- Ana branch: `main`
- Her üye kendi özellik branch'inde (`nisanur-gorev`, `melih-gorev` vb.) çalışır.
- Kod değişiklikleri commitlenir ve Pull Request ile ana branch'e eklenir.
- Merge yetkisi proje yöneticisindedir.

### Repo ve Kod Takibi
- GitHub repo'su üzerinden yapılan commit'ler ve branch aktiviteleri, Ozalyildirim ile entegre edilerek AI asistan tarafından takip edilir.
- Her üyenin kod katkısı ve işbirliği performansı site üzerinde görünür.

---

## 3. Çalışma Süreci Özeti

1. Ozalyildirim üzerinden görevler oluşturulur ve üyelere atanır.
2. Üyeler kendi görev branch'lerinde GitHub'da çalışır.
3. Kod değişiklikleri commitlenir ve Pull Request ile ana branch'e eklenir.
4. Scrum AI asistanı görev ilerlemesini ve repo aktivitelerini analiz eder.
5. Görevler tamamlandığında site üzerinden onaylanır ve grup performansı güncellenir.

Projenin genel analizi yapıldı. Projenin amacı, kapsamı ve paydaşları belirlendi.

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

**Proje Hedefi:** Şehirdeki trafik akışını optimize eden, enerji tüketimini izleyen ve acil durum müdahale sürelerini kısaltan; sensör verilerini analiz ederek gerçek zamanlı karar alan bir sistem geliştirmek.

---

## 1. Arka Plan (Backend) ve Ana Programlama Dili
Sensör verilerinin işlenmesi, algoritmaların çalıştırılması ve yapay zeka modelleriyle entegrasyon için bir arka plan diline ihtiyaç vardır.

| Teknoloji | Avantajlar | Dezavantajlar | Karar & Değerlendirme |
| :--- | :--- | :--- | :--- |
| **Python** *(Seçilen)* | Veri bilimi ve yapay zeka (TensorFlow) ile kusursuz entegrasyon. Zengin kütüphane desteği (Pandas, NumPy). | Diğer dillere göre işlem hızı (execution time) daha yavaş olabilir. | **Kesinlikle Uygun.** Makine öğrenmesi modelleri sisteme entegre edileceği için en mantıklı seçimdir. API'ler için **FastAPI** veya **Django** kullanılabilir. |
| **Go (Golang)** | İnanılmaz hızlı, eşzamanlı (concurrent) işlemlerde (milyonlarca sensör verisi) çok başarılı. | Makine öğrenmesi ekosistemi zayıf. | Mikroservis mimarisinde, sadece sensör verilerini karşılayan API gateway kısmında Python'a destek olarak eklenebilir. |
| **Node.js** | G/Ç (I/O) işlemlerinde başarılı, asenkron yapısıyla hızlı. | Yoğun CPU ve matematiksel işlemler (AI) için uygun değil. | Python varken ve AI odaklı bir proje iken birincil dil olması önerilmez. |

## 2. Yapay Zeka ve Makine Öğrenmesi
Trafik akışını tahmin etme, enerji anormalliklerini tespit etme gibi "akıllı" özellikler için.

| Teknoloji | Avantajlar | Dezavantajlar | Karar & Değerlendirme |
| :--- | :--- | :--- | :--- |
| **TensorFlow** *(Seçilen)* | Üretime (production) alma konusunda çok güçlü. Edge cihazlarda (IoT) çalışmak için TF Lite desteği var. | Öğrenme eğrisi PyTorch'a göre biraz daha diktir. | **İdeal Seçim.** Sensör tabanlı IoT cihazlarında (kameralar, trafik ışıkları vb.) model çalıştırmak (Edge AI) için çok avantajlı. |
| **PyTorch** | Araştırma ve model geliştirme sürecinde daha esnek ve Pythonic. | Edge AI (IoT) dağıtımları TensorFlow kadar olgun değil. | Alternatif olarak değerlendirilebilir ancak canlı sistemler ve IoT için TensorFlow bir adım önde. |

## 3. Veritabanı ve Veri Depolama
Akıllı şehir projelerinde hem ilişkisel verilere (kullanıcılar, cihaz bilgileri) hem de zaman serisi verilerine (her saniye gelen sensör okumaları) ihtiyaç vardır.

| Teknoloji | Avantajlar | Dezavantajlar | Karar & Değerlendirme |
| :--- | :--- | :--- | :--- |
| **PostgreSQL** *(Seçilen)* | Çok güvenilir, ACID uyumlu, coğrafi veriler için (PostGIS) mükemmel destek sunar. | Milyarlarca satırlık anlık sensör verisinde (zaman serisi) tek başına hantal kalabilir. | **Mükemmel Seçim.** Ancak sensör verileri için **TimescaleDB** eklentisi kullanılmalıdır. Şehir haritalandırması için **PostGIS** şarttır. |
| **MongoDB** | Esnek şema yapısı, verileri JSON formatında hızlı yazma. | Kompleks sorgular ve ilişkisel verilerde zorluk yaratır. | Bu projede PostgreSQL + TimescaleDB kombinasyonu varken gerek yoktur. |

## 4. Frontend (Kullanıcı ve Yönetim Arayüzü)
Operatörlerin şehir durumunu haritalar ve grafikler üzerinden gerçek zamanlı izleyeceği dashboard ekranları için.

| Teknoloji | Avantajlar | Dezavantajlar | Karar & Değerlendirme |
| :--- | :--- | :--- | :--- |
| **React** *(Seçilen)* | Bileşen tabanlı mimari, devasa ekosistem. Harita ve canlı veri kütüphaneleri (Deck.gl, React-Leaflet) çok güçlü. | Sadece bir UI kütüphanesidir, state yönetimi vb. için ek araçlar gerektirir. | **En İyi Seçim.** Gerçek zamanlı trafik ısı haritaları ve enerji gösterge panelleri için biçilmiş kaftandır. |
| **Angular** | Tam teşekküllü bir framework, büyük kurumsal projelerde katı ve düzenli bir yapı sunar. | Öğrenmesi zor, geliştirme süreci React'e göre daha yavaştır. | React halihazırda seçilmişken değiştirmeye gerek yok. |

## 5. Eksik Olan ve Mutlaka Eklenmesi Gereken Teknolojiler
"Gerçek zamanlı sensör verisi" işlerken sistemin çökmemesi için veriyi sıraya sokacak ve yönlendirecek araçlara ihtiyaç vardır.

* **Apache Kafka:** Şehirdeki binlerce sensörden gelen milyonlarca veriyi kayıpsız bir şekilde alıp Python'a iletmek için sistemin "sinir sistemi" olmalıdır.
* **MQTT (örn. Eclipse Mosquitto):** IoT cihazlarının (sensörlerin) düşük internet hızlarında bile merkeze veri gönderebilmesi için gereken hafif iletişim protokolüdür.

---

## 6. Önerilen Sistem Mimarisi Özeti
Seçilen teknoloji yığını ile tam ve ölçeklenebilir bir "Akıllı Şehir" veri akışı şu şekilde olmalıdır:

1.  **Veri Toplama (IoT):** Sensörler `->` MQTT `->` Apache Kafka
2.  **Arka Plan & Yapay Zeka:** Python (FastAPI) + TensorFlow *(Kafka'dan okur, analiz eder)*
3.  **Veritabanı:** PostgreSQL *(PostGIS ve TimescaleDB eklentileri ile)*
4.  **Arayüz:** React *(WebSocket ile anlık bildirimler alarak güncellenir)*


## Cemile Akay
Proje için GitHub üzerinden bir repository oluşturuldu. Proje klasör yapısı hazırlandı. Ekip üyeleri repositorye eklenerek projeye erişimleri sağlandı. Böylece ekip üyeleri ortak geliştirme ortamında çalışabilecek hale getirildi.

## Efecan Önal
GÖREVİN BURAYA YAPIŞTIRILACAK.

# Hafta 2

## Nisanur Eltekin

Trafik akışını optimize edecek bir algoritma için detaylı tasarım belgesi oluşturuldu. Belge; algoritmanın parametrelerini, optimizasyon hedeflerini, senaryo simülasyonlarını, teknik seçim gerekçesini ve performans metriklerini kapsamaktadır.

**1. Algoritma Tasarım Belgesi**
Sistem tanımı, bileşenler ve mimari belgelendi. Veri toplama, veri işleme, karar verme ve kullanıcı arayüzü olmak üzere dört katmanlı modüler bir yapı tasarlandı. Gerçek sensör verisi yerine Kaggle veri setleri ve Python simülatörü kullanılmaktadır.

**2. Algoritma Parametreleri**
Algoritma karar verirken şu parametreleri kullanmaktadır: trafik yoğunluğu ve kuyruk uzunluğu, ortalama araç hızı, yol durumu (normal/ıslak/kapalı/kaza), araç türü (ambulans/toplu taşıma/normal araç), günün saati ve hava koşulları. Yoğun saatlerde (07:00-09:00 ve 17:00-19:00) yük faktörü +%40, yağmur veya kar durumunda kapasite -%20 olarak otomatik ayarlanmaktadır.

**3. Optimizasyon Hedefleri**
Hedefler öncelik sırasına göre belirlendi. En yüksek öncelik acil araç yeşil dalga aktivasyonudur; bu durum tetiklendiğinde tüm diğer hedefler askıya alınır. Bunu sırasıyla seyahat süresini -%30 azaltma, kuyruk uzunluğunu 100 metrenin altında tutma, geçiş kapasitesini 1.000 araç/saatin üzerine çıkarma ve emisyon azaltma hedefleri izlemektedir.

**4. Senaryo Simülasyonları**
Dört farklı senaryo simüle edildi ve algoritmanın her koşulda hedef değerleri karşıladığı görüldü:
- Normal trafik: Bekleme süresi -%38, geçiş kapasitesi +%37
- Yoğun saat: Bekleme süresi -%40, geçiş kapasitesi +%58
- Acil durum (Yeşil Dalga): Ambulans müdahale süresi -%56, 7 kavşak sırayla yeşile alındı
- Kötü hava / kaza: Kaza riski -%35, otomatik alarm 30 saniyede tetiklendi

Bu senaryolar Python ile geliştirilen `trafik_simulasyon.py` modülü ile canlı olarak gösterilmektedir. Modül Elazığ'daki 5 kavşağı (Çarşı, Tofaş, İzzet Paşa, Palu Yolu, Üniversite Kavşağı) gerçek zamanlı simüle eder. A tuşu ile acil araç yeşil dalga, Y ile yoğun saat, K ile kaza, H ile hava değişimi tetiklenebilmektedir.

**5. Optimizasyon Tekniği Seçimi**
Kural tabanlı sistem, genetik algoritma, bulanık mantık, Q-Learning ve DQN olmak üzere beş teknik araştırıldı ve karşılaştırıldı. TensorFlow uyumluluğu, milisaniye düzeyinde gerçek zamanlı karar kapasitesi, şehrin trafik örüntülerini zamanla öğrenebilmesi ve ölçeklenebilirliği nedeniyle **DQN (Derin Q-Ağı)** seçildi. Sistem başlangıçta kural tabanlı çalışır, yeterli veri toplandıktan sonra DQN devreye girer.

**6. Performans Metrikleri**
Algoritmanın başarısını ölçmek için 4 kategoride toplam 21 metrik belirlendi. Trafik performansı (bekleme süresi < 30 sn, kuyruk < 150 m, kapasite > 1.000 araç/saat), sistem performansı (gecikme < 500 ms, model doğruluğu > %85, uptime > %99.9), acil durum metrikleri (yeşil dalga tepki < 1 sn, müdahale süresi < 6 dk) ve sosyal etki metrikleri (CO2 -%25, kaza -%30, sürücü memnuniyeti > 4.0/5.0) olarak sınıflandırıldı. 8 KPI hedefinin 6'sı simülasyonda tam olarak karşılandı.

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

 Bağlam Bu belge, Akıllı Şehir Yönetim Sistemi projesinin vatandaş mobil uygulamasının gereksinimlerini, platform stratejisini, kullanıcı hikayesi haritasını, kullanıcı akışlarını, etkileşim modelini ve güvenlik & gizlilik politikasını tanımlar. Belge; Hafta 1'de seçilen teknolojiler (Native AndroidKotlin, FastAPI, PostgreSQLPostGIS, FCM, simüle veri) ve Abdullah Gümüş'ün Hafta 2 veritabanı mimarisi (`users`, `sensors`, `sensor_readings`, `incidents` tabloları; RLS + RBAC) ile uyumludur.

---

## 1. Uygulamanın Amacı ve Kapsamı

Vatandaş mobil uygulaması; şehir sakinlerinin trafik durumu, enerji tüketimkesinti bilgileri, hava kalitesi ve acil durumolay bildirimleri gibi verilere tek bir kanaldan, konum bazlı ve anlık olarak erişebileceği bir platformdur. Uygulama aynı zamanda iki yönlü çalışır vatandaş yalnızca veri tüketmez, kendi gözlemlediği bir kaza, çukur, su baskını, aydınlatma arızası gibi durumları belediyeye konum ve fotoğraf ile bildirebilir.

Uygulamanın temel değer önerileri
- Şeffaflık Belediye yetkililerinin aldığı kararlar (yol kapatma, planlı kesinti, alarm) anında vatandaşa ulaşır.
- Hız Acil durumlar push bildirim olarak  5 saniyede iletilir.
- Katılım Vatandaş hem bilgi alır hem bildirim yaparak şehir yönetimine katkıda bulunur.

---

## 2. Platform Stratejisi

### 2.1. Faz 1 (MVP) — Native Android  Kotlin

Hafta 1 teknoloji değerlendirmesinde alınan karar doğrultusunda MVP yalnızca Android için geliştirilecektir. Gerekçeler

- Türkiye'de Android pazar payı %75'in üzerindedir; tek platform ile vatandaşların büyük çoğunluğuna ulaşılır.
- Native geliştirme; Google Maps SDK, Firebase Cloud Messaging (FCM) push bildirim ve konum servisleri ile en yüksek performansta entegrasyon sunar.
- Proje takvimi ve ekip kaynağı tek platforma odaklanmayı gerektirir.

Teknik kısıtlar

 Parametre  Değer 
 ---  --- 
 Dil  Kotlin 
 UI  Jetpack Compose (modern, deklaratif) 
 Mimari  MVVM + Clean Architecture 
 Minimum SDK  Android 8.0 (API 26) — pazarın ~%95'ini kapsar 
 Hedef SDK  Android 14 (API 34) 
 Harita  Google Maps SDK + Maps Compose 
 Push  Firebase Cloud Messaging (FCM) 
 Ağ  Retrofit + OkHttp (REST), OkHttp WebSocket 
 Yerel depolama  Room (SQLite) + EncryptedSharedPreferences 
 DI  Hilt 

### 2.2. Faz 2 (Yol Haritası) — iOS

iOS desteği projenin ikinci versiyon hedefidir. İki seçenek değerlendirilecektir

- Native iOS (Swift + SwiftUI) En yüksek kullanıcı deneyimi, ancak iki ayrı kod tabanı bakım maliyeti.
- Kotlin Multiplatform (KMP) İş katmanı paylaşılır, UI native kalır. Android kodunun büyük kısmı yeniden kullanılabilir.

KMP, Faz 1'de yazılan Kotlin altyapısının iOS'a taşınabilmesini sağlayacağı için tercih edilen yaklaşımdır.

---

## 3. Hedef Kullanıcı Personaları

 Persona  Profil  Birincil İhtiyaç 
 ---  ---  --- 
 Ahmet — Sürücü  35, ofise araba ile gidip gelen  Yoğunluk haritası, alternatif rota, kapatılan yollar 
 Ayşe — Çevreci Vatandaş  28, çevresağlık duyarlı  Hava kalitesi, gürültü seviyesi, sağlık önerileri 
 Mehmet — Bölge Sakini  55, mahallesindeki kesintilere duyarlı  Planlı enerji kesintileri, belediye duyuruları 
 İrem — Aktif Bildirici  22, gözleyip bildiren tip  Kaza, çukur, su baskını gibi olayları kolayca raporlama 
 Yetkili Operatör (dolaylı)  Belediye paneli kullanıcısı  Mobilden gelen bildirimleri panelden yönetir 

---

## 4. Fonksiyonel Gereksinimler (Özellikler)

### 4.1. Trafik Haritası

- Şehir haritası üzerinde renk kodlu yoğunluk katmanı (yeşilsarıkırmızıkoyu kırmızı).
- Veriler, simülatör tarafından üretilen ve FastAPI üzerinden gelen `sensor_readings` tablosundaki `metrics` (JSONB) alanından okunur (örn. `{speed 22, vehicle_count 145}`).
- Kapatılan yollar ve aktif olaylar harita üzerinde özel ikonlarla gösterilir (kaynak `incidents` tablosu, `status='active'`).
- Kullanıcı bir yolkavşak ikonuna dokununca bottom sheet açılır ortalama hız, yoğunluk seviyesi, son güncelleme zamanı.
- Alternatif rota önerisi kullanıcı başlangıç-bitiş seçer, sistem yoğunluğa göre en hızlı rotayı işaretler.
- Harita üzerinde toplu taşıma katmanı (opsiyonel, Faz 2).

### 4.2. Enerji Tüketimi ve Kesinti Takibi

- Mahallebölge bazlı anlık enerji tüketim grafiği (saatlik, günlük, haftalık).
- Planlı kesintiler Belediye panelinden girilen kesintiler liste + harita üzerinde görüntülenir; başlangıç-bitiş, etkilenen bölge, neden alanları gösterilir.
- Bildirim Kullanıcının kayıtlı bölgesinde planlı kesinti girildiğinde push bildirimi gider.
- Tasarruf önerileri ve belediye kampanyaları için bilgi kartları.

### 4.3. Acil Durum ve Olay Bildirimleri

İki yönlü modüldür.

Vatandaşa giden (gelen) bildirimler
- Kategori Trafik kazası, yangın, sel, sağlık, altyapı (suelektrik), hava durumu uyarısı.
- Konum filtresi yalnız kullanıcının seçtiği yarıçap içindeki olaylar (örn. 2 km  5 km  şehir geneli).
- Önem seviyeleri Bilgi (mavi), Uyarı (sarı), Kritik (kırmızı). Kritik bildirimler high-priority FCM olarak iletilir ve sessiz modu aşar.

Vatandaştan gelen (giden) bildirimler
- Hızlı bildirim FAB (Floating Action Button) ana ekranda her zaman görünür.
- Kategori seç → konum (otomatik veya manuel pin) → fotoğraf çekyükle (en fazla 3 adet) → kısa açıklama → gönder.
- Bildirim Abdullah'ın `incidents` tablosuna `reported_by = users.id`, `status = 'pending'` olarak kaydedilir.
- Anonim bildirim seçeneği kullanıcı kimliği gizlenir, `reported_by = NULL` (sadece konum + içerik saklanır).
- Gönderim sonrası kullanıcı bildiriminin durumunu (pending  acknowledged  resolved) takip edebilir.

### 4.4. Hava Kalitesi

- Konum bazlı AQI (Hava Kalitesi İndeksi) kart gösterimi.
- Ölçülen metrikler PM2.5, PM10, CO2, NO2 (simüle veri).
- Saatlik geçmiş ve günlük tahmin.
- AQI seviyesine göre sağlık önerileri (örn. Hassas gruplar dışarıda yoğun aktiviteden kaçınmalı).

### 4.5. Belediye Duyuruları

- Kronolojik liste; kategoriye göre filtre (Genel, Ulaşım, Etkinlik, Acil).
- Detay ekranında fotovideo, link, harita konumu (opsiyonel).
- Önemli işaretli duyurular için push bildirim.

### 4.6. Hesap ve Profil

- E-posta + şifre ile kayıtgiriş (`users` tablosu üzerinden, Abdullah'ın şemasıyla).
- Misafiranonim mod kayıt olmadan trafik ve hava bilgilerini görüntüleme.
- Profil ad, kayıtlı bölge(ler), bildirim tercihleri (kategori bazlı toggle), dil (TREN), tema (otomatikaydınlıkkaranlık).
- Şifre değiştirme, hesabı silme (KVKK gereği).

---

## 5. Etkileşim Modeli (UX)

### 5.1. Genel Yapı

- Alt navigasyon (Bottom Navigation) — 5 sekme
  1. Ana Sayfa — Konum bazlı özet kartlar (trafik, hava, son duyuru, yakındaki olaylar).
  2. Harita — Tam ekran trafik + olay haritası.
  3. Bildir (merkezde, vurgulu FAB) — Hızlı olay bildirimi.
  4. Duyurular — Belediye duyuruları + bildirimler tarihçesi.
  5. Profil — Hesap, ayarlar.

### 5.2. Tasarım Dili

- Material Design 3 (Material You) — dinamik renk teması.
- Karanlık mod (Android sistem ayarını izler).
- Yazı tipi Roboto (Android varsayılan), 14-16 sp gövde, 20 sp başlık.
- Erişilebilirlik Talkback uyumu, en az 4.51 kontrast oranı, dokunma hedefi minimum 48 dp.

### 5.3. Etkileşim Desenleri

- Harita ve listelerde pull-to-refresh.
- Detay ekranları bottom sheet ile (haritayı kapatmadan).
- Kritik bildirimler için uyarı diyaloğu + titreşim.
- Boş durumlar (no internet, no incidents) için açıklayıcı illüstrasyonlar.

---

## 6. Kullanıcı Hikayesi Haritası (User Story Map)

Yapı en üstte kullanıcı etkinlikleri (backbone), altında kullanıcı görevleri, en altta release planı.

```
═══════════════════════════════════════════════════════════════════════════════════════
 BACKBONE        Uygulamaya     Şehri          Bilgi          Olay            Hesabı
 (Etkinlikler)   Giriş          Keşfetme       Alma           Bildirme        Yönetme
═══════════════════════════════════════════════════════════════════════════════════════
 GÖREVLER        • Onboarding   • Haritayı     • Bildirim     • Kategori      • Profil
                 • İzinler        gezme          alma           seçme          • Tercihler
                 • KayıtGiriş  • Konuma       • Detay         • Konum         • DilTema
                 • Misafir        gitme          görme          seçme          • Çıkış
                   moda
                                • Yoğunluğu    • Geçmişe      • Foto
                                  okuma          bakma          yükleme
                                • Olayları
                                  görme        • Filtreleme   • Açıklama
                                                              • Gönderme
═══════════════════════════════════════════════════════════════════════════════════════
 MVP            ★ Splash       ★ Harita       ★ Push         ★ Hızlı         ★ E-posta
 (Sürüm 1)        ★ 3 ekran      katmanı        bildirim       bildirim        kayıtgiriş
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

### 7.1. İlk Açılış  Onboarding Akışı

```
[Splash 1.5 sn]
      │
      ▼
[Onboarding — 3 ekran] ──── kullanıcı Geç der ───┐
      │ Devam                                    │
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
       [Yolkavşağa     [Olay pinine
        dokun]            dokun]
              │                 │
              ▼                 ▼
       [Bottom sheet    [Bottom sheet
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
[Herhangi bir ekran] ──► [FAB Bildir butonu]
                                  │
                                  ▼
                         [Kategori seçimi
                          Kaza  Yangın  Sel 
                          Sağlık  Altyapı  Diğer]
                                  │
                                  ▼
                         [Konum belirle
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
                  [Onay ekranı         [Tekrar dene 
                   Bildirimin           Taslak kaydet]
                   gönderildi,
                   takip no #1234]
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
       [İlgili detay ekranı
        • Olay → Harita + bottom sheet
        • Kesinti → Enerji ekranı
        • Duyuru → Duyuru detayı]
              │
              ▼
       [Kullanıcı tepkisi
        Paylaş  Bildirimi kapat 
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
                                  [Yaklaşan kesinti detayı
                                   tarihsaat aralığı, neden]
                                          │
                                          ▼
                                  [☐ Bu bölgeyi takip et
                                   ☐ Hatırlatıcı kur]
```

---

## 8. Fonksiyonel Olmayan Gereksinimler

 Kategori  Gereksinim 
 ---  --- 
 Performans  Soğuk başlangıç  3 sn, sıcak başlangıç  1 sn. Harita ilk render  2 sn. 
 Akıcılık  UI 60 fps; Compose recomposition gözle görülür gecikmesiz. 
 Ağ  Tüm REST çağrılarına 10 sn timeout; başarısız olursa exponential backoff. 
 Offline  Son trafik anlık görüntüsü ve son 10 bildirim Room ile cachelenir; offline'da görüntülenir. 
 Pil  Konum güncellemesi yalnızca uygulama açık ve harita aktifken; arka planda yalnızca push tetikli. 
 Veri kullanımı  Harita tile + sensör verisi günlük  30 MB hedeflenmeli; Wi-Fi-only modu opsiyonel. 
 Çökme  Crash-free user oranı  %99.5 (Firebase Crashlytics ile izlenir). 
 Erişilebilirlik  TalkBack tam destek; en az 4.51 kontrast; dinamik yazı boyutu. 
 Lokalizasyon  TR ana, EN Faz 1.1'de. Tarihsaat cihaz lokalini izler. 
 Bağımlılıklar  Backend RESTWebSocket, FCM, Google Maps SDK, PostgreSQL `users` & `incidents` tabloları (Abdullah'ın şeması). 

---

## 9. Güvenlik ve Gizlilik Gereksinimleri

### 9.1. Yasal Çerçeve

- 6698 sayılı KVKK (Kişisel Verilerin Korunması Kanunu) uyumu zorunludur.
- İlk açılışta açık rıza içeren Aydınlatma Metni ve Gizlilik Politikası kullanıcıya sunulur; reddedilirse misafir moda geçilir.

### 9.2. Kimlik Doğrulama

- E-posta + şifre ile kayıt (Abdullah'ın `users` tablosundaki `email`, `password_hash` alanları).
- Şifre kuralı min 8 karakter, en az 1 harf + 1 rakam.
- JWT erişim token (15 dakika) + refresh token (30 gün) modeli. Refresh token rotation aktif.
- Şifre sıfırlama e-posta üzerinden tek kullanımlık link.
- Faz 1.1 SMS OTP ile iki faktörlü doğrulama (opsiyonel).

### 9.3. Veri İletimi ve Depolama

- Tüm ağ iletişimi TLS 1.3 üzerinden HTTPS.
- Certificate pinning (OkHttp ile) — sahte sertifikalı MITM saldırılarını engeller.
- Token'lar Android Keystore + EncryptedSharedPreferences ile saklanır; düz metin asla depolanmaz.
- Yerel cache (Room) hassas içerik için SQLCipher ile şifrelenir.

### 9.4. Veri Minimizasyonu

- Yalnızca uygulamanın çalışması için gerekli veriler toplanır
  - Konum yalnız uygulama açık iken (while in use), arka plan konumu istenmez.
  - Kamera yalnız kullanıcı Bildirim yap → foto ekle derken aktif olur.
  - Telefon, kişiler, takvim, mikrofon istenmez.
- Reklam kimliği toplanmaz; üçüncü taraf analitik ya hiç kullanılmaz ya da kullanıcı opt-in seçer.

### 9.5. Yetkilendirme (Backend Tarafında)

- Abdullah'ın belirlediği Row-Level Security (RLS) politikaları bir vatandaş yalnızca `incidents` tablosunda `reported_by = current_user.id` olan kayıtlarını detaylı görebilir; diğerlerini anonim kamuya açık alan olarak görür.
- RBAC `role = 'citizen'` rolü; sensör tablolarına yazma yetkisi yoktur, yalnızca okuma; `incidents` tablosuna yalnızca INSERT.

### 9.6. Anonim Bildirim

- Kullanıcı anonim gönder seçtiğinde `reported_by` alanı NULL gönderilir; istemcide bu seçimden sonra bildirim geçmişinde bu kayıt görüntülenmez.
- Bildirimin meta verisi (cihaz IP'si, kullanıcı kimliği) backend tarafında loglanmaz; pgaudit konfigürasyonu bu uç noktayı hariç tutar.

### 9.7. Çocuk ve Hassas İçerik Koruması

- Kayıt sırasında yaş beyanı alınır (13+ önerilir). 18 yaş altı kullanıcılar için reklam ve analytics opt-out otomatik aktiftir.
- Vatandaş bildirimlerinde yüklenen fotoğraflar moderasyon kuyruğuna girer; uygunsuzyüz içeren içerik otomatik blur veya manuel inceleme ile kontrol edilir.

### 9.8. Veri Saklama Süreleri ve Silme

- Kullanıcı Hesabımı sil dediğinde 30 gün içinde tüm kişisel veriler kalıcı silinir.
- Anonim ve agregeli bildirim verileri (istatistik amaçlı) saklanmaya devam edebilir.
- Konum geçmişi cihazda yalnızca son 7 gün tutulur; backend uzun süreli konum geçmişi tutmaz.

### 9.9. Olay Yanıt Planı

- Veri ihlali tespit edildiğinde KVKK gereği 72 saat içinde Kişisel Verileri Koruma Kurulu'na bildirilir, etkilenen kullanıcılara push + e-posta ile bilgi verilir.
- Tüm güvenlik logları Abdullah'ın belirttiği SOCSIEM platformuna yönlendirilir.

---

## 10. Bağımlılıklar ve Riskler

 Bağımlılık  Risk  Etki  Önlem 
 ---  ---  --- 
 FastAPI backend ve WebSocket altyapısı hazır olmalı  Yüksek  Mock servisle paralel geliştirme yap 
 FCM proje konfigürasyonu  Yüksek  Hafta 3 başında tamamlanmalı 
 Google Maps API kotası  Orta  Aylık 28.500 ücretsiz yükleme; aşılırsa fallback OSM (OpenStreetMap) 
 Simüle verinin gerçekçi dağılımı  Orta  Hafta 1'de tanımlanan saathava modeline sadık kalın 
 Veritabanı şema değişiklikleri  Düşük  Abdullah'ın tablolarına versiyonlu migration ile uyum 

---

## 11. Sonuç ve Sonraki Adımlar

Bu belge ile uygulamanın ne yapacağı, kimin için olduğu, nasıl davranacağı ve hangi sınırlar içinde kalacağı netleştirilmiştir. Hafta 3 ve sonrasında

1. Düşük çözünürlüklü ekran (wireframe) ve yüksek çözünürlüklü (mockup) tasarımlar Figma'da çıkarılacak.
2. RESTWebSocket sözleşmeleri (OpenAPI şeması) backend ekibiyle netleştirilecek.
3. Kotlin proje iskeleti (modüller, Hilt setup, navigasyon iskeleti) kurulacak.
4. MVP özellik listesinden başlanarak iteratif sprint planı çıkarılacak.


## Cemile Akay
# 🔬 Veri Toplama ve Analiz Modülü – Gereksinim Analizi

## 📌 Proje Bağlamı

Bu modül, Akıllı Şehir Yönetim Sistemi’nin temel bileşenlerinden biridir. Sistem; trafik, enerji ve çevresel verileri gerçek zamanlı olarak analiz ederek karar mekanizmalarını çalıştırır.

Projede fiziksel sensör bulunmadığından, tüm veriler Python tabanlı simülasyon ile üretilmektedir. Ancak mimari, gerçek IoT sensörlerine bağlanabilecek şekilde tasarlanmıştır.

---

## 🎯 Modülün Amacı

* Şehirdeki tüm kritik verileri toplamak
* Verileri standart bir formatta işlemek
* Gerçek zamanlı analiz için hazır hale getirmek
* TensorFlow (DQN) algoritmasına veri sağlamak
* PostgreSQL veritabanına güvenli şekilde kaydetmek

---

## 📊 Toplanan Veri Türleri

### 🚦 Trafik Verileri (Kritik)

* Araç sayısı
* Kuyruk uzunluğu
* Doluluk oranı
* Ortalama hız
* Yol durumu (normal / kaza / kapalı)

📌 Kullanım:
DQN algoritması bu verilerle trafik ışığı sürelerini optimize eder.

---

### ⚡ Enerji Verileri

* Anlık enerji tüketimi
* Bölgesel tüketim
* Sokak aydınlatma durumu

📌 Kullanım:
Enerji yönetimi ve tasarruf modu.

---

### 🌫️ Çevresel Veriler

* PM2.5
* CO2
* NO2
* Gürültü seviyesi

📌 Kullanım:
Çevre izleme ve raporlama.

---

### 🚑 Acil Durum Verileri (KRİTİK MOD)

* Ambulans konumu
* İtfaiye araçları
* Araç tipi
* Acil sinyal durumu

📌 Kullanım:
Yeşil dalga sistemi (öncelik: 1)

---

## ⏱️ Veri Toplama Sıklığı

| Veri Türü  | Sıklık              |
| ---------- | ------------------- |
| Trafik     | 5 saniye            |
| Çevresel   | 10–30 saniye        |
| Enerji     | 1 dakika            |
| Acil Durum | Anlık (event-based) |

---

## 🔄 Sistem Veri Akışı

Python Simülatör
→ MQTT
→ FastAPI Backend
→ TensorFlow (DQN karar)
→ PostgreSQL (kayıt)
→ React Panel & Mobil Uygulama

---

## 📦 Veri Formatı

Tüm veriler JSON formatında MQTT ile iletilir.

### Örnek Veri:

{
"sensor_id": "TRF_01",
"vehicle_count": 120,
"avg_speed": 25,
"road_status": "normal",
"timestamp": "2026-05-11T15:00:00"
}

---

## 🔐 Veri Güvenliği Gereksinimleri

* MQTT üzerinden güvenli iletişim (TLS)
* FastAPI üzerinde JWT doğrulama
* Veri doğrulama (input validation)
* Rol bazlı yetkilendirme
* Veri bütünlüğü kontrolü

---

## 🛠️ Kullanılan Teknolojiler (Proje ile Uyumlu)

* Python → veri simülasyonu
* MQTT → veri iletişimi
* FastAPI → backend API
* PostgreSQL → veri depolama
* TensorFlow → analiz (DQN)

---

## ⚠️ Entegrasyon Zorlukları

* Simülasyon verisinin gerçek veriye yakınlığı
* Gerçek zamanlı gecikme (<500ms hedefi)
* Yüksek veri hacmi yönetimi
* Veri standardizasyonu

---

## 💡 Çözüm Önerileri

* Ortak JSON veri standardı
* Veri doğrulama katmanı
* Asenkron veri işleme (FastAPI async)
* MQTT QoS kullanımı
* Gelecekte gerçek sensör entegrasyonu

---

## 🎯 Beklenen Sistem Katkısı

* Trafik optimizasyonu için doğru veri
* Enerji tasarrufu sağlanması
* Çevresel izleme
* Acil durumlara hızlı müdahale

---

## 📌 Sonuç

Veri Toplama Modülü, sistemin en kritik bileşenidir. Toplanan verilerin doğruluğu, DQN algoritmasının başarısını ve genel sistem performansını doğrudan etkiler.


## Efecan Önal
GÖREVİN BURAYA YAPIŞTIRILACAK.

# Hafta 3

## Nisanur Eltekin

Hafta 3 görevi kapsamında, Elazığ Akıllı Şehir Yönetim Sistemi'nin enerji yönetimi bileşeni için belediye yetkililerinin kullanacağı tam kapsamlı bir web tabanlı yönetim paneli mockup tasarımı oluşturuldu. Panel; gerçek zamanlı enerji tüketimi izleme, anomali tespiti, kavşak bazlı karşılaştırma, acil durum modülü ve interaktif filtreleme özellikleriyle projenin enerji yönetimi gereksinimlerini karşılamaktadır. Tasarım React ile geliştirilen nihai panelin temel referans belgesi olarak kullanılacaktır.

---

**Elazığ Kavşak Lokasyonları:**
Panel, Elazığ şehir merkezindeki 5 kritik kavşağı kapsamaktadır. Bu kavşaklar; Çarşı Kavşağı, Tofaş Kavşağı, İzzet Paşa Kavşağı, Palu Yolu Kavşağı ve Üniversite Kavşağı olarak belirlenmiştir. Her kavşak için sokak aydınlatma, kamu binaları ve trafik sistemi bazında ayrı enerji tüketim verisi izlenmekte, kavşaklar arası tüketim karşılaştırması anlık olarak yapılmaktadır. Bu lokasyonlar aynı zamanda `trafik_simulasyon.py` modülündeki kavşak yapısıyla tam uyumludur.

---

**Metrik Kartlar:**
Panelin üst bölümünde belediye yetkililerinin tek bakışta şehrin enerji durumunu görebileceği 4 ana metrik kart tasarlanmıştır.

- **Toplam tüketim:** Tüm kavşaklar ve kaynaklar genelinde anlık enerji tüketimi (4.820 kWh). Geçen haftaya göre +%8 artış tespit edilmiş olup sistem otomatik uyarı üretmektedir.
- **Sokak aydınlatma:** Akıllı kararma ve sensör optimizasyonu sayesinde -%20 tasarruf sağlanmıştır. Bu değer projenin -%20 enerji tasarrufu KPI hedefiyle tam uyumludur.
- **Kamu binaları:** Mesai saatleri dışındaki tüketim artışı (+%4) sistem tarafından anomali olarak işaretlenmektedir.
- **Toplam tasarruf:** Optimizasyon algoritmaları sayesinde elde edilen toplam enerji tasarrufu ve buna karşılık gelen maliyet düşüşü (-%20) görüntülenmektedir.

Tüm metrik kartlar seçilen zaman dilimine göre otomatik olarak güncellenmektedir.

---

**Görselleştirmeler:**
Panelde üç farklı grafik türü birlikte kullanılarak enerji verisi farklı boyutlarda görselleştirilmiştir.

- **Saatlik enerji tüketimi trendi (Çizgi Grafik):** 24 saatlik süreçte sokak aydınlatma, kamu binaları ve trafik sistemleri bazında enerji tüketiminin saatlik değişimi ayrı çizgilerle gösterilmektedir. Sabah ve akşam yoğun saatlerindeki tüketim artışı grafikte açıkça izlenebilmektedir. Her kaynak için farklı çizgi stili kullanılarak renk körü kullanıcılar için de okunabilirlik sağlanmıştır.

- **Kaynak bazlı dağılım (Halka Grafik):** Toplam enerji tüketimi içindeki kaynak payları görselleştirilmiştir. Sokak aydınlatma %44, kamu binaları %41 ve trafik sistemleri %15 oranında tüketim gerçekleştirmektedir. Bu dağılım optimizasyon önceliklerini belirlemede yönlendirici bir rol oynamaktadır.

- **Kavşak bazlı tüketim karşılaştırması (Yatay Bar Grafik):** 5 Elazığ kavşağının günlük ortalama enerji tüketimleri karşılaştırmalı olarak gösterilmektedir. Çarşı Kavşağı 1.320 kWh ile en yüksek tüketime sahipken Palu Yolu Kavşağı 440 kWh ile en düşük seviyededir. Bu grafik sayesinde hangi kavşağa öncelikli müdahale yapılması gerektiği kolayca tespit edilebilmektedir.

---

**Acil Durum Modülü:**
Projenin en kritik KPI hedefi olan ambulans müdahale süresini 6 dakikanın altında tutma gereksinimiyle tam uyumlu bir acil araç izleme modülü tasarlanmıştır. Bu modül, Hafta 2'de geliştirilen trafik optimizasyon algoritmasının yeşil dalga bileşeniyle entegre çalışmaktadır.

Aktif acil durum tespit edildiğinde panel üst kısmında kırmızı uyarı banner'ı otomatik devreye girmektedir. Banner; aktif aracın konumunu, güzergahını, yeşil dalga durumunu ve anlık müdahale süresini göstermektedir.

Acil araç izleme tablosunda şu veriler yer almaktadır: araç tipi (ambulans / itfaiye), mevcut konum, güzergah (başlangıç kavşağı → hedef kavşak), yeşil dalga aktivasyon durumu ve müdahale süresi. Simülasyon sonuçlarıyla uyumlu olarak ambulans müdahale süresi 3.5 dakikaya düşürülmüştür. Bu değer KPI hedefinin (%50 iyileşme, <6 dakika) çok üzerinde bir performansa işaret etmektedir. Tamamlanan müdahaleler de geçmiş kayıt olarak tabloda tutulmaktadır.

---

**Anomali Tespiti ve Optimizasyon Fırsatları:**
Sistem, belirlenen eşik değerlerin dışına çıkan enerji tüketimlerini otomatik olarak tespit etmekte ve belediye yetkilisine öneri sunmaktadır. Anomaliler üç seviyede sınıflandırılmaktadır: Yüksek (kırmızı), Orta (sarı), Normal (yeşil).

Tespit edilen anomaliler şunlardır:
- **Çarşı Kavşağı — Sokak aydınlatma:** Gündüz saatlerinde beklenen 120 kWh yerine 210 kWh tüketim tespit edilmiştir. Sistem sensör arızası olabileceğini bildirmekte ve kontrol yapılmasını önermektedir. Durum: Yüksek.
- **Üniversite Kavşağı — Kamu binası:** Mesai saatleri dışında beklenen 40 kWh yerine 95 kWh tüketim gerçekleşmektedir. Sistem otomatik tasarruf moduna geçilmesini önermektedir. Durum: Orta.
- **Tofaş Kavşağı — Trafik sistemi:** Normal aralıkta tüketim devam etmektedir. Herhangi bir müdahale gerekmemektedir. Durum: Normal.
- **Palu Yolu Kavşağı — Sokak aydınlatma:** Tüketim hedef değerin biraz altında seyretmektedir. Akıllı kararma özelliğinin aktive edilmesiyle ek tasarruf sağlanabileceği tespit edilmiştir. Durum: Normal.

---

**İnteraktif Filtreler ve Karşılaştırma Özellikleri:**
Panelde kullanıcıların farklı zaman dilimlerini, bölgeleri ve enerji kaynaklarını karşılaştırabilmesi için üç bağımsız filtre tasarlanmıştır.

- **Zaman dilimi filtresi:** Son 24 saat, Son 7 gün, Son 30 gün ve Bu yıl seçenekleri mevcuttur. Seçim değiştirildiğinde metrik kartlar otomatik güncellenmekte, toplam tüketim ve tasarruf değerleri seçilen periyoda göre yeniden hesaplanmaktadır.
- **Kavşak filtresi:** 5 Elazığ kavşağından herhangi biri seçilerek yalnızca o kavşağa ait veriler incelenebilmektedir. Tüm kavşaklar seçeneği varsayılan görünümdür.
- **Kaynak filtresi:** Sokak aydınlatma, kamu binaları ve trafik sistemleri ayrı ayrı filtrelenebilmektedir. Bu sayede örneğin yalnızca sokak aydınlatma tüketimindeki anomaliler izole edilerek incelenebilmektedir.

Bu üç filtre birlikte kullanılarak örneğin "İzzet Paşa Kavşağı'nın son 7 günlük sokak aydınlatma tüketimi" gibi spesifik karşılaştırmalar yapılabilmektedir.

---

**Teknik Detaylar:**
Panel, HTML5, CSS3 ve JavaScript kullanılarak geliştirilmiştir. Grafik kütüphanesi olarak Chart.js 4.4.1 kullanılmıştır. Tasarım mobil uyumludur (responsive). Nihai sistem React ile yeniden geliştirilecek olup bu mockup temel referans belgesi işlevi görmektedir.

Panel `web/enerji_yonetim_paneli_nisanur_hafta3gorev.html` dosyası olarak GitHub reposunun `web` klasörüne eklenmiştir.


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
# 🗄️ Veri Toplama Modülü – Veritabanı Şema Tasarımı

## 📌 Proje Bağlamı

Bu veritabanı tasarımı, Python simülatörü ve MQTT üzerinden gelen sensör verilerinin PostgreSQL’de saklanmasını sağlar. Sistem, gerçek zamanlı veri işleme ve DQN algoritması için optimize edilmiştir.

---

## 🎯 Amaç

* Sensör verilerini merkezi olarak saklamak
* Gerçek zamanlı sorgulamayı desteklemek
* TensorFlow analizleri için veri sağlamak
* Yüksek performans ve ölçeklenebilirlik sağlamak

---

## 🧱 Veritabanı Teknolojisi

PostgreSQL

---

## 📊 Tablolar

### 1. sensors

Sensör bilgileri

| Alan     | Tip       |
| -------- | --------- |
| id       | SERIAL PK |
| type     | VARCHAR   |
| location | VARCHAR   |
| active   | BOOLEAN   |

---

### 2. traffic_data (DQN için kritik)

| Alan          | Tip       |
| ------------- | --------- |
| id            | SERIAL PK |
| sensor_id     | INT (FK)  |
| vehicle_count | INT       |
| avg_speed     | FLOAT     |
| road_status   | VARCHAR   |
| timestamp     | TIMESTAMP |

---

### 3. environmental_data

| Alan      | Tip       |
| --------- | --------- |
| id        | SERIAL PK |
| sensor_id | INT (FK)  |
| pm25      | FLOAT     |
| co2       | FLOAT     |
| no2       | FLOAT     |
| noise     | FLOAT     |
| timestamp | TIMESTAMP |

---

### 4. energy_data

| Alan        | Tip       |
| ----------- | --------- |
| id          | SERIAL PK |
| sensor_id   | INT (FK)  |
| consumption | FLOAT     |
| timestamp   | TIMESTAMP |

---

### 5. emergency_data (KRİTİK MOD)

| Alan         | Tip       |
| ------------ | --------- |
| id           | SERIAL PK |
| vehicle_type | VARCHAR   |
| location     | VARCHAR   |
| status       | VARCHAR   |
| timestamp    | TIMESTAMP |

---

## 🔗 Tablo İlişkileri

* sensors.id → tüm veri tablolarına bağlanır
* Her veri kaydı bir sensöre aittir
* traffic_data → DQN algoritmasının ana veri kaynağıdır

---

## ⚡ İndeksleme Stratejisi

* timestamp üzerine index (real-time sorgular)
* sensor_id üzerine index
* traffic_data için:
  (sensor_id, timestamp) composite index

---

## 📈 Performans Optimizasyonu

* Zaman bazlı partitioning
* Batch insert (MQTT → DB)
* Gereksiz veri tekrarının önlenmesi

---

## 🔄 Sistem Entegrasyonu

MQTT
→ FastAPI
→ PostgreSQL
→ TensorFlow
→ Sonuç → Veritabanı

---

## 📦 Ölçeklenebilirlik

* Yatay ölçeklenebilir yapı
* Büyük veri için partitioned tables
* Cache sistemi (Redis önerilir)

---

## 🔐 Güvenlik

* Rol bazlı erişim (RBAC)
* Veritabanı yetkilendirme
* Yedekleme (backup)
* Loglama sistemi

---

## 🧾 Örnek SQL

CREATE TABLE sensors (
id SERIAL PRIMARY KEY,
type VARCHAR(50),
location VARCHAR(100),
active BOOLEAN
);

CREATE TABLE traffic_data (
id SERIAL PRIMARY KEY,
sensor_id INT REFERENCES sensors(id),
vehicle_count INT,
avg_speed FLOAT,
road_status VARCHAR(20),
timestamp TIMESTAMP
);

---

## 🎯 Beklenen Sistem Katkısı

* Hızlı veri erişimi
* Gerçek zamanlı analiz desteği
* DQN algoritmasının doğru çalışması
* Sistem performansının artırılması

---

## 📌 Sonuç

Bu veritabanı tasarımı, Akıllı Şehir Yönetim Sistemi’nin veri altyapısını oluşturur. Doğru indeksleme ve yapı sayesinde sistem, yüksek veri yükü altında bile hızlı ve stabil çalışabilir.


## Efecan Önal
GÖREVİN BURAYA YAPIŞTIRILACAK.

# Hafta 4

## Nisanur Eltekin

Hafta 4 görevi kapsamında, Hafta 3'te geliştirilen `sensor_data_collector.py` modülü aracılığıyla Elazığ'daki 5 kavşaktan toplanan çevresel sensör verileri kapsamlı bir şekilde analiz edildi. Hava kalitesi, gürültü seviyesi, partikül madde yoğunluğu, karbondioksit ve nem gibi kritik çevresel metrikler görselleştirilerek anlamlı raporlar oluşturuldu. Tüm çıktılar interaktif bir web dashboard'u olarak geliştirildi.

---

**Veri Kaynağı ve Hazırlık**

Hafta 3'te geliştirilen sensör simülatörü Elazığ'ın 5 gerçek kavşağını kapsamaktadır: Çarşı Kavşağı, Tofaş Kavşağı, İzzet Paşa Kavşağı, Palu Yolu Kavşağı ve Üniversite Kavşağı. Her kavşak için ayrı bir sensör ID'si tanımlanmış olup (SNS-001'den SNS-005'e) gerçekçi veri üretimi için şehir merkezindeki kavşaklara yüksek kirlilik faktörü, yoğun saatlerde (07:00-09:00 ve 17:00-19:00) ise ek yük faktörü uygulanmaktadır.

Simülatör çalıştırılarak her 5 saniyede bir veri toplanmış ve `sensor_data.json` dosyasına kaydedilmiştir. Toplanan veriler şu metrikleri içermektedir: PM2.5 (partikül madde), PM10 (kaba partikül), CO2 (karbondioksit), NO2 (azot dioksit), O3 (ozon), sıcaklık (°C), nem (%) ve gürültü seviyesi (dB). Her okuma için ayrıca 0-100 arası Hava Kalitesi İndeksi (AQI) hesaplanmış, gürültü seviyeleri Düşük / Orta / Yüksek / Tehlikeli olarak etiketlenmiş ve geçersiz sensör okumaları doğrulama katmanı tarafından otomatik olarak filtrelenmiştir.

---

**Görselleştirmeler**

Dashboard'da beş farklı grafik türü kullanılarak veriler farklı boyutlarda görselleştirildi.

**1. Kavşak Bazlı AQI Karşılaştırması (Yatay Bar Grafik)**
Her kavşağın ortalama Hava Kalitesi İndeksi yatay bar grafiğiyle karşılaştırmalı olarak sunuldu. Renkler anlam taşımaktadır: kırmızı kötü (AQI > 60), sarı orta (30-60 arası), yeşil ise iyi (< 30) seviyeyi temsil etmektedir. Bu grafik sayesinde hangi kavşağa öncelikli çevre müdahalesi yapılması gerektiği tek bakışta anlaşılabilmektedir.

**2. Saatlik Hava Kalitesi Trendi (Çizgi Grafik)**
Çarşı, Tofaş ve İzzet Paşa kavşaklarının 24 saatlik AQI değişimi çizgi grafiğiyle gösterildi. Her kaynak için farklı çizgi stili kullanılarak okunabilirlik artırıldı. Sabah ve akşam yoğun saatlerinde kirlilik değerlerinin belirgin biçimde yükseldiği, gece saatlerinde ise düştüğü grafikte açıkça izlenebilmektedir.

**3. Gürültü Seviyesi Dağılımı (Bar Grafik)**
5 kavşağın ortalama gürültü seviyeleri bar grafiğiyle karşılaştırıldı. Renk kodlaması uygulandı: kırmızı rahatsız edici (> 70 dB), yeşil kabul edilebilir (< 65 dB) seviyeyi göstermektedir. Referans çizgileri ile WHO gürültü standartları görsel olarak işaretlendi.

**4. PM2.5 Partikül Madde Dağılımı (Bar Grafik)**
Kavşak bazlı PM2.5 yoğunlukları görselleştirildi. Güvenli (< 12 µg/m³), orta (12-35 µg/m³) ve yüksek (> 35 µg/m³) seviyeleri renk kodlu olarak ayrıştırıldı. Bu grafik, hava kalitesini doğrudan etkileyen partikül madde kirliliğinin hangi kavşaklarda yoğunlaştığını ortaya koymaktadır.

**5. CO2 ve Nem Korelasyonu (Çift Eksenli Çizgi Grafik)**
Çarşı Kavşağı için 24 saatlik CO2 (ppm) ve nem (%) değişimi çift eksenli çizgi grafiğiyle analiz edildi. Sol eksen CO2 değerlerini, sağ eksen ise nem yüzdesini göstermektedir. Gün içinde CO2'nin sabah saatlerinde düştüğü, öğle ve akşam yoğun saatlerinde ise yükseldiği gözlemlendi. Nem ile CO2 arasında ters korelasyon olduğu tespit edildi.

---

**İnteraktif Özellikler**

Dashboard iki filtre içermektedir. Kavşak filtresi ile 5 Elazığ kavşağından herhangi biri seçildiğinde ortalama AQI değeri otomatik güncellenmektedir. Metrik filtresi ile AQI, PM2.5, gürültü ve CO2 arasında geçiş yapılabilmektedir.

---

**Analiz Sonuçları ve Değerlendirme**

Elde edilen veriler incelendiğinde şehir merkezine yakın kavşaklarda kirlilik değerlerinin belirgin biçimde daha yüksek olduğu doğrulandı.

Çarşı Kavşağı tüm metriklerde en kötü değerleri sergiledi: AQI 72.1 (kötü), gürültü 74.2 dB, PM2.5 28.4 µg/m³, CO2 512 ppm. Genel durum değerlendirmesi: Kötü.

İzzet Paşa Kavşağı da yüksek kirlilik değerleri gösterdi: AQI 68.4, gürültü 71.5 dB, PM2.5 25.2 µg/m³. Genel durum: Kötü.

Tofaş ve Üniversite kavşakları orta seviyede kaldı. AQI değerleri sırasıyla 45.3 ve 42.0 olarak ölçüldü.

Palu Yolu Kavşağı tüm metriklerde en iyi performansı sergiledi: AQI 38.2, gürültü 52.1 dB, PM2.5 yalnızca 9.3 µg/m³ ile güvenli sınırın altında kaldı. Genel durum: İyi.

---

**Özet Rapor**

Tüm kavşakların AQI, PM2.5, gürültü, CO2 ve sıcaklık verilerini bir arada gösteren genel durum değerlendirme tablosu oluşturuldu. Her kavşak İyi, Orta ve Kötü olarak sınıflandırıldı. Bu rapor belediye yetkililerinin hangi bölgelere öncelikli çevre müdahalesi yapması gerektiğini göstermekte olup ilerleyen haftalarda geliştirilecek enerji yönetimi ve trafik optimizasyon modülleri için de temel referans belgesi işlevi görmektedir.

---

**Dosyalar**
- `NE_H4_web_cevresensoranalizi.html` — İnteraktif çevresel sensör analiz dashboard'u

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
