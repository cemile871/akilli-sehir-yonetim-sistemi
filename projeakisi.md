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
# Akıllı Şehir Yönetim Sistemi - Veritabanı Mimari Tasarımı

[cite_start]Bu proje, yüksek hacimli sensör verisi, coğrafi konum verisi ve standart kullanıcı/yönetim verisi barındıran Akıllı Şehir Yönetim Sistemi için geliştirilmiş kapsamlı bir veritabanı mimarisini içermektedir[cite: 1, 2]. 

## Veri Modeli ve Teknolojiler

[cite_start]Saniyede binlerce kayıt atan sensör verilerinde tıkanmaları önlemek amacıyla sistemde Hibrit Veri Modeli (İlişkisel + Zaman Serisi + Coğrafi) kullanılmıştır[cite: 2, 3].
* [cite_start]Veritabanı yönetim sistemi olarak PostgreSQL tercih edilmiş ve Table Partitioning (Tablo Bölümleme) kullanılmıştır[cite: 4].
* [cite_start]Coğrafi verilerin işlenmesi için PostGIS uzantısı sisteme entegre edilmiştir[cite: 4].
* [cite_start]Esnek veri yapıları (metrikler) için JSONB veri tipi kullanılmıştır[cite: 4].

## Veritabanı Tabloları ve İlişkiler

Sistem bütünlüğünü sağlamak adına temel mimari dört ana tablo üzerinden şekillendirilmiştir:
* [cite_start]Kullanıcılar (users): Sistemi yönetecek yetkililer ve vatandaşların bilgilerini barındırır[cite: 8, 9].
* [cite_start]Sensörler (sensors): Şehrin farklı noktalarındaki donanımların PostGIS koordinatları ile tanımlandığı tablodur[cite: 11, 12, 13].
* [cite_start]Sensör Okumaları (sensor_readings): Sürekli akan verilerin depolandığı ana zaman serisi tablosudur[cite: 14, 15]. [cite_start]Bu tablo ile sensörler arasında 1:N (Bire Çok) ilişki bulunmaktadır[cite: 22].
* [cite_start]Olaylar (incidents): Sistem tarafından tespit edilen veya vatandaşlarca bildirilen anormalliklerin tutulduğu tablodur[cite: 17, 18]. [cite_start]Kullanıcılar ile olaylar arasında 1:N (Bire Çok) ilişki kurulmuştur[cite: 26].

## Performans Optimizasyonu ve İndeksleme

[cite_start]Büyük veri konseptine uygun olarak yazma performansını en üst seviyede tutmak için spesifik indeksleme yöntemleri kullanılmıştır[cite: 30]:
* [cite_start]BRIN İndeksi: Zaman serileri (sensor_readings) için tercih edilmiş olup, geçmiş verilere yönelik toplu taramaların çok daha hızlı çalışmasını sağlar[cite: 32, 33, 36].
* [cite_start]GiST İndeksi: Coğrafi sınırları filtrelemek ve 2 boyutlu uzaysal sorgular yapabilmek için konum verilerinde (location) kullanılmıştır[cite: 37, 39, 40].
* [cite_start]GIN İndeksi: Esnek JSON belgelerinin içine girerek anahtar-değer ikililerini indekslemek için tercih edilmiştir[cite: 41, 43].
* [cite_start]B-Tree İndeksi: Doğrudan eşleşme aranan foreign key ve benzersiz e-posta gibi alanlarda kullanılmıştır[cite: 45, 47].

## Güvenlik ve Yedekleme Stratejisi

[cite_start]Kritik şehir ve kullanıcı verilerinin gizliliğini ve erişilebilirliğini güvence altına almak için şu adımlar atılmıştır[cite: 49]:
* [cite_start]Erişim Yönetimi: Rol Bazlı Erişim Kontrolü (RBAC) ve Satır Bazlı Güvenlik (RLS) politikaları uygulanmıştır[cite: 51, 57].
* [cite_start]Şifreleme: Veritabanı izole bir ağda (VPC) barındırılmış, TLS/SSL ve AES-256 şifrelemeleri kullanılmıştır[cite: 54, 55, 56].
* [cite_start]İzleme ve Loglama: PostgreSQL üzerinde pgaudit eklentisi aktif edilerek log verileri Güvenlik Operasyon Merkezi'ne (SOC) yönlendirilmiştir[cite: 59, 60].
* [cite_start]Yedekleme: Continuous Archiving ve WAL dosyaları kullanılarak sistemi geçmişteki tam bir saniyeye döndürebilme (PITR) kapasitesi sağlanmıştır[cite: 64, 65].
* [cite_start]Ölçeklenebilirlik: Sistem üzerindeki yükü dengelemek için PgBouncer ile bağlantı havuzlama (Connection Pooling) ve okuma kopyaları (Read Replicas) mimarisi devreye alınmıştır[cite: 69, 71, 73].

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
