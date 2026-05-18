# Hafta 1

## Nisanur Eltekin
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Abdullah Gümüş
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Melih Ahmet Kocaman
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Cemile Akay
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Efecan Önal
# 🏙️ Akıllı Şehir Yönetim Sistemi - Proje Özeti

[cite_start]**Proje Amacı:** Şehir yaşamını daha verimli, güvenli ve sürdürülebilir kılmak[cite: 4]. [cite_start]Trafik akışını optimize etmek, enerji tüketimini izlemek, çevresel verileri analiz etmek ve acil durum müdahale sürelerini kısaltmayı hedefler[cite: 5].

## 📦 5 Ana Modül
1. [cite_start]**Veri Toplama ve Analiz:** Çevresel faktörlerin ve trafik verilerinin Python ile simülasyonu, anormallik tespiti[cite: 14, 15, 16].
2. [cite_start]**Trafik Optimizasyonu (DQN):** TensorFlow kullanılarak eğitilmiş yapay zeka ile trafik ışığı yönetimi ve acil araçlar için "yeşil dalga"kurgusu[cite: 17, 18, 19].
3. [cite_start]**Enerji Yönetimi Paneli:** Sokak aydınlatmaları ve kamu binalarında tüketim izleme ile otomatik tasarruf modlarının yönetimi[cite: 20, 21].
4. [cite_start]**Vatandaş Mobil Uygulaması:** Trafik durumu, enerji kesintileri ve şehir duyuruları için anlık bildirim sistemi[cite: 23, 24, 25].
5. [cite_start]**Belediye Yönetim Paneli:** Şehrin tüm anlık verilerinin tek bir merkezden izlenip kontrol edilmesi[cite: 26, 27, 28].

## 🛠️ Teknoloji Yığını
* [cite_start]**Yapay Zeka & Simülasyon:** Python, TensorFlow [cite: 12]
* [cite_start]**Veritabanı:** PostgreSQL [cite: 12]
* [cite_start]**Web Arayüzü:** React [cite: 12]
* [cite_start]**Backend API:** Python FastAPI [cite: 12]
* [cite_start]**Mobil Uygulama:** Native Android / Kotlin [cite: 12]
* [cite_start]**IoT Haberleşme:** MQTT Protokolü [cite: 12]

## 🎯 Beklenen Kritik Sonuçlar (KPI)
* [cite_start]🚦 **Trafik:** Ortalama bekleme süresinin 28 saniyeye düşürülmesi[cite: 68].
* [cite_start]🚑 **Acil Durum:** Müdahale süresinin %50 iyileşme ile 3.5 dakikaya indirilmesi[cite: 10, 68].
* [cite_start]⚡ **Enerji:** Otomatik sistemlerle tüketimde %20 tasarruf sağlanması[cite: 10].

# Hafta 2

## Nisanur Eltekin
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Abdullah Gümüş
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Melih Ahmet Kocaman
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Cemile Akay
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Efecan Önal
# ⚡ Elazığ Akıllı Şehir - Enerji Yönetimi Paneli

Bu proje, belediye yöneticilerinin şehirdeki enerji tüketimini izlemesi, anormallikleri tespit etmesi ve verimliliği artırmak için hızlı aksiyon alabilmesi amacıyla tasarlanmış web tabanlı bir UI/UX çalışmasıdır.

## 🚀 Proje Kapsamında Neler Yapıldı?

* **Kullanıcı Deneyimi (UX) Araştırması:** Belediye yöneticilerinin ihtiyaçları analiz edilerek, kilit verileri (anormallikler, acil durumlar) ön plana çıkaran bir bilgi hiyerarşisi kurgulandı.
* **Görselleştirme ve Harita Entegrasyonu:** `Leaflet.js` kullanılarak kavşaklardaki anlık tüketim durumları interaktif haritaya taşındı. `Chart.js` ile farklı bölgelerin tüketim kalemlerini kıyaslayan analiz grafikleri eklendi.
* **Aksiyon Odaklı Arayüz (Actionable UI):** Yöneticilerin sadece veriyi izlemesi değil, anında müdahale edebilmesi için (örn. "Tasarruf Modunu Aç", "Ekip Yönlendir") eylem butonları sisteme dahil edildi.
* **Erişilebilirlik (a11y) İyileştirmeleri:** Tüm arayüz semantik HTML5 etiketleri (`<header>`, `<main>`, `<section>`) ve ekran okuyucular için `aria-label` nitelikleri ile erişilebilirlik standartlarına uygun hale getirildi.
* **Duyarlı Tasarım (Responsive Design):** CSS Grid ve Flexbox kullanılarak farklı ekran boyutlarında sorunsuz çalışan, modern ve temiz bir arayüz kodlandı.

## 🛠️ Teknolojiler
* HTML5, CSS3, Vanilla JS
* Chart.js
* Leaflet.js
* Tabler Icons

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
## 🚦 React ile Trafik Optimizasyon ve Simülasyon Modülü (Yeni Özellik)

Bu güncelleme ile projeye, belediye görevlilerinin trafik akışını yapay zeka/algoritmik temellerde test edebileceği **React tabanlı bir simülasyon arayüzü** eklenmiştir.

### 🌟 Eklenen Temel Özellikler:
* **Dinamik Parametre Kontrolü:** Kullanıcılar UI üzerinden "Araç Yoğunluğu Ağırlığı" ve "Maksimum Bekleme Eşiği" gibi algoritma parametrelerini Range (Slider) inputlar ile hassas şekilde ayarlayabilir.
* **Senaryo Simülasyonu:** Trafik kazası, yol çalışması veya normal akış gibi farklı kriz senaryoları seçilerek algoritmanın bu durumlara nasıl tepki vereceği test edilebilir.
* **Harita Üzerinde Görselleştirme:** Seçilen senaryolar ve olay yerleri (örn: kaza noktası) harita arayüzü üzerinde dinamik olarak konumlandırılır ve görselleştirilir.
* **A/B Karşılaştırma Çıktıları:** Simülasyon çalıştırıldıktan sonra sistem; *Trafik Akış Verimi (%), Ortalama Bekleme Süresi (sn) ve CO2 Azaltım Oranını* hesaplayarak yöneticilere anında geri bildirim sunar.

### 💻 Teknolojik Altyapı:
* **React.js (Hooks):** Durum yönetimi için `useState` kullanılarak interaktif bir bileşen (`TrafficOptimizationPanel`) oluşturuldu.
* **Responsive Layout:** Tailwind CSS / Modern CSS yaklaşımları ile ekran boyutundan bağımsız esnek bir ızgara (grid) sistemi kurgulandı.
* **Gelecek Entegrasyona Hazır:** Algoritma fonksiyonu (`runSimulation`), gerçek bir Backend/Python API'sine kolayca bağlanabilecek yapıda asenkron mimariye uygun tasarlanmıştır.

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
