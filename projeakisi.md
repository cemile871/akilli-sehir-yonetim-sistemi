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
