# Hafta 1

## Nisanur Eltekin
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Abdullah Gümüş
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Melih Ahmet Kocaman
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Cemile Akay
Proje için GitHub üzerinden bir repository oluşturuldu. Proje klasör yapısı hazırlandı. Ekip üyeleri repositorye eklenerek projeye erişimleri sağlandı. Böylece ekip üyeleri ortak geliştirme ortamında çalışabilecek hale getirildi.

## Efecan Önal
GÖREVİN BURAYA YAPIŞTIRILACAK.

# Hafta 2

## Nisanur Eltekin
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Abdullah Gümüş
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Melih Ahmet Kocaman
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Cemile Akay

🔬 Veri Toplama ve Analiz Modülü – Gereksinim Analizi
🎯 Modülün Amacı
•	Şehirdeki tüm kritik verileri toplamak
•	Verileri standart bir formatta işlemek
•	Gerçek zamanlı analiz için hazır hale getirmek
•	TensorFlow (DQN) algoritmasına veri sağlamak
•	PostgreSQL veritabanına güvenli şekilde kaydetmek
________________________________________
📊 Toplanan Veri Türleri
🚦 Trafik Verileri 
•	Araç sayısı
•	Kuyruk uzunluğu
•	Doluluk oranı
•	Ortalama hız
•	Yol durumu (normal / kaza / kapalı)
📌 Kullanım: DQN algoritması bu verilerle trafik ışığı sürelerini optimize eder.
________________________________________
⚡ Enerji Verileri
•	Anlık enerji tüketimi
•	Bölgesel tüketim
•	Sokak aydınlatma durumu
📌 Kullanım: Enerji yönetimi ve tasarruf modu.
________________________________________
🌫️ Çevresel Veriler
•	PM2.5
•	CO2
•	NO2
•	Gürültü seviyesi
📌 Kullanım: Çevre izleme ve raporlama.
________________________________________
🚑 Acil Durum Verileri (KRİTİK MOD)
•	Ambulans konumu
•	İtfaiye araçları
•	Araç tipi
•	Acil sinyal durumu
📌 Kullanım: Yeşil dalga sistemi (öncelik: 1)
________________________________________
⏱️ Veri Toplama Sıklığı
Veri Türü	Sıklık
Trafik	5 saniye
Çevresel	10–30 saniye
Enerji	1 dakika
Acil Durum	Anlık (event-based)
________________________________________
🔄 Sistem Veri Akışı
Python Simülatör → MQTT → FastAPI Backend → TensorFlow (DQN karar) → PostgreSQL (kayıt) → React Panel & Mobil Uygulama
________________________________________
📦 Veri Formatı
Tüm veriler JSON formatında MQTT ile iletilir.
Örnek Veri:
{ "sensor_id": "TRF_01", "vehicle_count": 120, "avg_speed": 25, "road_status": "normal", "timestamp": "2026-05-11T15:00:00" }
________________________________________
🔐 Veri Güvenliği Gereksinimleri
•	MQTT üzerinden güvenli iletişim (TLS)
•	FastAPI üzerinde JWT doğrulama
•	Veri doğrulama (input validation)
•	Rol bazlı yetkilendirme
•	Veri bütünlüğü kontrolü
________________________________________
🛠️ Kullanılan Teknolojiler (Proje ile Uyumlu)
•	Python → veri simülasyonu
•	MQTT → veri iletişimi
•	FastAPI → backend API
•	PostgreSQL → veri depolama
•	TensorFlow → analiz (DQN)
________________________________________
⚠️ Entegrasyon Zorlukları
•	Simülasyon verisinin gerçek veriye yakınlığı
•	Gerçek zamanlı gecikme (<500ms hedefi)
•	Yüksek veri hacmi yönetimi
•	Veri standardizasyonu
________________________________________
💡 Çözüm Önerileri
•	Ortak JSON veri standardı
•	Veri doğrulama katmanı
•	Asenkron veri işleme (FastAPI async)
•	MQTT QoS kullanımı
•	Gelecekte gerçek sensör entegrasyonu
________________________________________
🎯 Beklenen Sistem Katkısı
•	Trafik optimizasyonu için doğru veri
•	Enerji tasarrufu sağlanması
•	Çevresel izleme
•	Acil durumlara hızlı müdahale




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

🗄️ Veri Toplama Modülü – Veritabanı Şema Tasarımı
📌 Proje Bağlamı
Bu veritabanı tasarımı, Python simülatörü ve MQTT üzerinden gelen sensör verilerinin PostgreSQL’de saklanmasını sağlar. Sistem, gerçek zamanlı veri işleme ve DQN algoritması için optimize edilmiştir.
________________________________________
🎯 Amaç
•	Sensör verilerini merkezi olarak saklamak
•	Gerçek zamanlı sorgulamayı desteklemek
•	TensorFlow analizleri için veri sağlamak
•	Yüksek performans ve ölçeklenebilirlik sağlamak
________________________________________
🧱 Veritabanı Teknolojisi
PostgreSQL
________________________________________
📊 Tablolar
1. sensors
Sensör bilgileri
Alan	Tip
id	SERIAL PK
type	VARCHAR
location	VARCHAR
active	BOOLEAN
________________________________________
2. traffic_data (DQN için kritik)
Alan	Tip
id	SERIAL PK
sensor_id	INT (FK)
vehicle_count	INT
avg_speed	FLOAT
road_status	VARCHAR
timestamp	TIMESTAMP
________________________________________
3. environmental_data
Alan	Tip
id	SERIAL PK
sensor_id	INT (FK)
pm25	FLOAT
co2	FLOAT
no2	FLOAT
noise	FLOAT
timestamp	TIMESTAMP
________________________________________
4. energy_data
Alan	Tip
id	SERIAL PK
sensor_id	INT (FK)
consumption	FLOAT
timestamp	TIMESTAMP
________________________________________
5. emergency_data (KRİTİK MOD)
Alan	Tip
id	SERIAL PK
vehicle_type	VARCHAR
location	VARCHAR
status	VARCHAR
timestamp	TIMESTAMP
________________________________________
🔗 Tablo İlişkileri
•	sensors.id → tüm veri tablolarına bağlanır
•	Her veri kaydı bir sensöre aittir
•	traffic_data → DQN algoritmasının ana veri kaynağıdır
________________________________________
⚡ İndeksleme Stratejisi
•	timestamp üzerine index (real-time sorgular)
•	sensor_id üzerine index
•	traffic_data için: (sensor_id, timestamp) composite index
________________________________________
📈 Performans Optimizasyonu
•	Zaman bazlı partitioning
•	Batch insert (MQTT → DB)
•	Gereksiz veri tekrarının önlenmesi
________________________________________
🔄 Sistem Entegrasyonu
MQTT → FastAPI → PostgreSQL → TensorFlow → Sonuç → Veritabanı
________________________________________
📦 Ölçeklenebilirlik
•	Yatay ölçeklenebilir yapı
•	Büyük veri için partitioned tables
•	Cache sistemi (Redis önerilir)
________________________________________
🔐 Güvenlik
•	Rol bazlı erişim (RBAC)
•	Veritabanı yetkilendirme
•	Yedekleme (backup)
•	Loglama sistemi
________________________________________
🧾 Örnek SQL
CREATE TABLE sensors ( id SERIAL PRIMARY KEY, type VARCHAR(50), location VARCHAR(100), active BOOLEAN );
CREATE TABLE traffic_data ( id SERIAL PRIMARY KEY, sensor_id INT REFERENCES sensors(id), vehicle_count INT, avg_speed FLOAT, road_status VARCHAR(20), timestamp TIMESTAMP );
________________________________________
🎯 Beklenen Sistem Katkısı
•	Hızlı veri erişimi
•	Gerçek zamanlı analiz desteği
•	DQN algoritmasının doğru çalışması
•	Sistem performansının artırılması
________________________________________
📌 Sonuç
Bu veritabanı tasarımı, Akıllı Şehir Yönetim Sistemi’nin veri altyapısını oluşturur. Doğru indeksleme ve yapı sayesinde sistem, yüksek veri yükü altında bile hızlı ve stabil çalışabilir.


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

# Hafta 4: Akıllı Ulaşım Modülü Kullanıcı Arayüzü Geliştirme Raporu

---

### 1. Görev Tanımı ve Kapsamı
Hafta 3'te mimari tasarımları ve veri altyapısı kurgulanan **Akıllı Ulaşım Modülü** için belediye yönetim panelini ve vatandaş geri bildirim mekanizmalarını içeren kullanıcı arayüzü (UI) geliştirilmiştir. Geliştirme sürecinde "Flat Design" felsefesine sadık kalınmış, sistem hızı ve kullanıcı deneyimi (UX) ön planda tutulmuştur.

---

### 2. Bot Kullanıcılardan Gelen Geri Bildirimler (UX Veri Analizi)
Arayüz tasarlanmadan önce, sistem tarafından simüle edilen bot kullanıcı test gruplarından (Vatandaş Mobil Uygulaması ve Belediye Kontrol Odası botları) aşağıdaki kritik geri bildirimler toplanmıştır:

* **Geri Bildirim 1 (Vatandaş Botu):** "Haritada sadece trafik yoğunluğunu görmek yeterli değil. Ambulans veya itfaiye geçtiğinde yolun neden kapatıldığını veya ne kadar süre kapalı kalacağını anlık olarak bilmek istiyoruz."
* **Geri Bildirim 2 (Belediye Operatör Botu):** "Yapay zeka (DQN) ışık sürelerini değiştiriyor ancak arka planda sistemin işlem gecikmesini göremiyoruz. Bir yoğunluk anında sistemin stabil kalıp kalmadığını (milisaniye cinsinden) ekranda anlık izlemeliyiz."
* **Geri Bildirim 3 (Saha Ekibi Botu):** "Kötü hava koşullarında (yağmur, kar) sistemin algoritmik olarak kapasiteyi düşürdüğünü panelden teyit edemiyoruz. Hava durumunun simülasyona etkisini gösteren bir indikatör şart."

---

### 3. Geri Bildirimlere Göre Arayüze Eklenen Yeni Özellikler

Bot kullanıcılardan alınan geri bildirimler doğrultusunda Hafta 4 arayüz mimarisine şu dinamik bileşenler entegre edilmiştir:

#### A. Canlı Sistem Performans ve Algoritma Göstergeleri (Topbar)
* **DQN Algoritma Modu:** TensorFlow tabanlı Derin Q-Ağı modelinin aktiflik durumunu (`DQN: Aktif (Hibrit)`) gösteren canlı durum rozeti eklendi.
* **Sistem Gecikme Sayacı (Latency):** Sistem başlangıç hedeflerinde yer alan milisaniye düzeyinde karar mekanizmasını doğrulamak için `Gecikme: 320 ms` indikatörü eklendi.
* **Hava Durumu Katsayı Entegrasyonu:** Yağmurlu ve karlı havalarda sistem kapasitesinin otomatik olarak düşürüldüğünü (`Yağmurlu - Kapasite -%20`) gösteren dinamik katsayı alanı eklendi.

#### B. Geliştirilmiş Canlı Harita ve "Yeşil Dalga" Rota Belirteci
* **Acil Durum Önceliği:** Üst barda aktif bir acil durum (`🚑 1 aktif acil durum`) tetiklendiğinde, Leaflet.js haritası üzerinde ambulans/itfaiye güzergahı kırmızı kesikli çizgilerle (`dashArray`) **Yeşil Dalga Rotası** olarak görselleştirildi.
* **Kavşak Pop-up Geliştirmesi:** Haritadaki kavşaklara tıklandığında sadece enerji tüketimi değil, o anki anlık araç sayısı ve metre cinsinden kuyruk uzunluğu verileri de eklendi.

#### C. Çok Boyutlu Bölge Karşılaştırma Grafiği
* **Kuyruk Analizi:** Chart.js grafiği güncellenerek, seçilen iki farklı kavşağın (Örn: Çarşı Kavşağı vs Üniversite Kavşağı) sadece enerji yükleri değil, trafik sıkışıklığını doğrudan gösteren **Ortalama Kuyruk Uzunlukları (Metre)** yan yana kıyaslanabilir hale getirildi.

---

### 4. Teknik Teknoloji Yığını ve Erişilebilirlik (a11y)
* **Frontend:** React mimarisine uygun temiz HTML5 ve modern CSS3 Grid/Flexbox yapısı.
* **Grafik ve Harita:** Chart.js (v4.4.1) ve Leaflet.js (v1.9.4) kütüphaneleriyle asenkron veri görselleştirme.
* **Erişilebilirlik:** Ekran okuyucular ve bot analiz araçları için form elemanlarına eksiksiz `aria-label` tanımlamaları eklenmiştir.
* **Veri Köprüsü:** Buton aksiyonları ve anomali yönetim tablosu, PostgreSQL veritabanına log üretecek tetikleyicilerle (`onclick` yapılarıyla) simüle edilmiştir.

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
 
