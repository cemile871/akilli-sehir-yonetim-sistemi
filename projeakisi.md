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
