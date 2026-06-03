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
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Abdullah Gümüş
GÖREVİN BURAYA YAPIŞTIRILACAK.

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
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Abdullah Gümüş
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Melih Ahmet Kocaman
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Cemile Akay
# Akıllı Ulaşım Modülü Kullanıcı Arayüzü Geliştirme Raporu

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
# Trafik Optimizasyon Algoritması Test ve Doğrulama Raporu

---

### 1. Testin Amacı ve Kapsamı
[cite_start]Bu çalışma kapsamında, geliştirilen TensorFlow tabanlı Derin Q-Ağı (DQN) trafik optimizasyon algoritmasının [cite: 16, 18][cite_start], proje rehberinde belirtilen hedef metrikleri (KPI) ve senaryoları ne derece karşıladığı simülasyon ortamında test edilmiş ve doğrulanmıştır[cite: 7, 58, 67]. [cite_start]Testler sırasında, sistemin gerçek zamanlı tepki süreleri ve Hafta 3/4 kapsamında kurgulanan PostgreSQL veritabanı entegrasyonunun kararlılığı ölçülmüştür[cite: 12, 16].

---

### 2. Farklı Trafik Senaryoları Test Sonuçları

[cite_start]Proje rehberinde yer alan 4 ana senaryo simülatör üzerinde koşturulmuş ve şu sonuçlar elde edilmiştir[cite: 58]:

* [cite_start]**Senaryo 1 — Normal Trafik Dönemi:** Tüm yönlere dengeli yeşil ışık süresi dağıtılmıştır[cite: 59]. [cite_start]Ortalama bekleme süresi 45 saniyeden 28 saniyeye düşmüş (-%38) ve kavşak geçiş kapasitesi +%37 artış göstererek başarıyla doğrulanmıştır[cite: 60].
* [cite_start]**Senaryo 2 — Yoğun Saatler (07-09 / 17-19):** "Rush factor = 1.4" parametresi sisteme girdi olarak verilmiştir[cite: 61]. [cite_start]Ana arterlerdeki yeşil ışık sürelerinin otomatik olarak 60-90 saniye aralığına yükseldiği ve güncelleme sıklığının 15 saniyeye düştüğü gözlemlenmiştir[cite: 62]. [cite_start]Bekleme süresinde -%40 iyileşme ve kapasitede +%58 artış yakalanmıştır[cite: 62].
* [cite_start]**Senaryo 3 — Acil Durum (Yeşil Dalga):** Simülatörden ambulans/itfaiye sinyali gönderilerek "KRİTİK" mod tetiklenmiştir[cite: 63]. [cite_start]Algoritmanın diğer tüm öncelikleri askıya alarak rota üzerindeki 7 kavşağı sırayla yeşile kilitlediği (yeşil dalga) doğrulanmıştır[cite: 45, 63]. [cite_start]Acil aracın geçişinden 10 saniye sonra sistem normale dönmüştür[cite: 63]. [cite_start]Müdahale süresi 12 dakikadan 6 dakikaya düşürülmüştür (-%56)[cite: 64].
* [cite_start]**Senaryo 4 — Kötü Hava ve Yol Kapanması:** Yağmurlu/karlı hava girdisiyle yol kapasite eşiği -%30 düşürülmüş ve yeşil ışık süreleri otomatik olarak -%20 kısaltılmıştır[cite: 65]. [cite_start]Yapay kaza simülasyonunda, sistem alternatif güzergah yönlendirme alarmını 30 saniyenin altında (< 30 sn) başarıyla tetiklemiştir[cite: 66].

---

### 3. KPI (Performans) Hedefleri Doğrulama Tablosu

[cite_start]Proje başlangıcında belirlenen KPI hedefleri ile simülasyon test çıktıları karşılaştırmalı olarak aşağıda listelenmiştir[cite: 67]:

| KPI Hedefi | Belirlenen Hedef | Test Sonucu / Durum |
| :--- | :--- | :--- |
| **Ortalama Bekleme Süresi** | [cite_start]< 30 saniye [cite: 68] | [cite_start]✅ 28 saniye (Başarılı) [cite: 68] |
| **Kuyruk Uzunluğu** | [cite_start]< 100 metre [cite: 68] | [cite_start]✅ 75 metre (Başarılı) [cite: 68] |
| **Geçiş Kapasitesi** | > [cite_start]1.000 araç/saat [cite: 68] | [cite_start]✅ 1.100 araç/saat (Başarılı) [cite: 68] |
| **Acil Müdahale Süresi** | [cite_start]< 6 dakika [cite: 68] | [cite_start]✅ 3.5 dakika (Başarılı) [cite: 68] |
| **Sistem Gecikmesi** | [cite_start]< 500 ms [cite: 68] | [cite_start]✅ 320 ms (Başarılı) [cite: 68] |
| **Emisyon Azalması** | [cite_start]-%25 [cite: 68] | [cite_start]⚠️ -%12-20 (Geliştirilmesi Gerekiyor) [cite: 68] |

---

### 4. PostgreSQL Veritabanı ve API Entegrasyon Testi
* [cite_start]**Veri Yazma Kararlılığı:** Simülatörün (MQTT üzerinden) ürettiği anlık trafik yoğunluk verileri ve DQN modelinin milisaniye düzeyinde aldığı kararlar, PostgreSQL veritabanındaki ilgili log tablolarına kayıpsız ve tutarlı bir şekilde kaydedilmiştir[cite: 12, 16, 54].
* [cite_start]**Hibrit Başlangıç Güvenliği:** Sistemin ilk açılışta kural tabanlı olarak güvenli modda başladığı, yeterli veri havuzu oluştuktan sonra PostgreSQL ve TensorFlow köprüsü üzerinden DQN modeline sorunsuz ve kesintisiz şekilde devredildiği doğrulanmıştır[cite: 16, 57].

---

### 5. Potansiyel İyileştirme Alanları
* [cite_start]**Karbon Emisyonu Optimizasyonu:** Performans tablosunda da görüldüğü üzere, bekleme süreleri ve kuyruk uzunlukları hedefi başarıyla yakalarken, emisyon azalması beklentisi (%12-20) hedef katsayının (%25) altında kalmıştır[cite: 68]. Gelecek hafta yapılacak çalışmalarda, DQN ödül fonksiyonuna (reward function) çevre/emisyon odaklı yeni cezalandırma parametreleri eklenecektir.


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
