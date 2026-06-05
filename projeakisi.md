# Hafta 1

## Nisanur Eltekin
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Abdullah Gümüş
GÖREVİN BURAYA YAPIŞTIRILACAK.

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
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Efecan Önal
GÖREVİN BURAYA YAPIŞTIRILACAK.

# Hafta 2

## Nisanur Eltekin
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Abdullah Gümüş
GÖREVİN BURAYA YAPIŞTIRILACAK.

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
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Efecan Önal
GÖREVİN BURAYA YAPIŞTIRILACAK.

# Hafta 3

## Nisanur Eltekin
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Abdullah Gümüş
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Melih Ahmet Kocaman
# Akıllı Şehir Vatandaş Mobil Uygulaması — Prototip Tasarım Belgesi

> **Bağlam:** Bu belge, Hafta 2'de belirlenen gereksinimleri ve kullanıcı akışlarını Figma tabanlı düşük ve yüksek çözünürlüklü tasarımlara dönüştürme sürecini kapsamaktadır. Tasarım; Hafta 1'de seçilen teknoloji yığını (Native Android, **Android Studio**, Kotlin — XML View tabanlı layout), Hafta 2'de tanımlanan 5 kullanıcı personası ve kullanıcı hikayesi haritasındaki MVP kapsamıyla tam uyumludur.

---

## 1. Tasarım Süreci ve Araçlar

### 1.1. Kullanılan Araçlar

| Araç | Kullanım Amacı |
| :--- | :--- |
| **Figma** | Wireframe ve yüksek çözünürlüklü (hi-fi) ekran tasarımları |
| **Material Design 3 Kit** | Figma'daki resmi Google bileşen kütüphanesi |
| **FigJam** | Kullanıcı akışı şemaları ve ekip tartışma notları |
| **Android Studio (Hedgehog+)** | Geliştirme ortamı; Layout Editor ile XML tasarım önizlemesi |
| **Android Emulator (AVD)** | Tasarımların Pixel 6 / Pixel 4 profilleriyle önizlemesi |

### 1.2. Tasarım Aşamaları

Tasarım süreci üç iterasyon aşamasında yürütülmüştür:

1. **Lo-fi Wireframe:** Yalnızca iskelet yapı; içerik yok, kaba yerleşim planı.
2. **Mid-fi Prototype:** İçerik yerleştirme, tipografi hiyerarşisi, etkileşim akışları.
3. **Hi-fi Mockup:** Gerçek renkler, ikonlar, gerçekçi veri içerikleri, animasyon notları.

---

## 2. Ekran Boyutu ve Grid Sistemi

Tasarımlar iki temel çözünürlük için optimize edilmiştir:

| Cihaz Profili | Çözünürlük | Kullanım |
| :--- | :--- | :--- |
| **Standart Android** | 360 × 800 dp | Birincil tasarım referansı |
| **Büyük Android** | 412 × 915 dp | İkincil önizleme |

Grid sistemi: 4 sütun, 16 dp kenar boşluğu, 8 dp gutter. Tüm dokunma hedefleri minimum **48 × 48 dp** kuralına uyar.

---

## 3. Renk Paleti ve Tipografi (Material You)

### 3.1. Renk Sistemi

Projenin kimliği için **Belediye Mavi** tonu temel alınmış; Material Design 3'ün dinamik renk algoritmasıyla yardımcı renkler türetilmiştir.

| Token | Renk (HEX) | Kullanım |
| :--- | :--- | :--- |
| `primary` | `#1565C0` | Ana butonlar, aktif sekmeler |
| `primaryContainer` | `#D6E4FF` | Kart arka planları, chip'ler |
| `secondary` | `#006874` | İkincil aksiyon öğeleri |
| `error` | `#BA1A1A` | Kritik uyarılar, hata durumları |
| `errorContainer` | `#FFDAD6` | Kırmızı alert banner'ları |
| `surface` | `#FAFCFF` | Sayfa ve kart arka planı |
| `onSurface` | `#1A1C1E` | Birincil metin |
| `outline` | `#74777F` | Sınır çizgileri, ikincil metin |

**Karanlık Mod:** Material You'nun otomatik karanlık tema algoritması uygulanmış; tüm token'lar karanlık arka plan için ters çevrilmiştir.

**Erişilebilirlik:** `onSurface` / `surface` kombinasyonu 14.1:1 kontrast oranına sahiptir (WCAG AAA). Tüm metin-arka plan çiftleri minimum 4.5:1 oranını karşılar.

### 3.2. Tipografi

| Stil | Yazı Tipi | Boyut | Ağırlık | Kullanım |
| :--- | :--- | :--- | :--- | :--- |
| `displaySmall` | Roboto | 36 sp | 400 | Onboarding başlıkları |
| `headlineMedium` | Roboto | 28 sp | 400 | Ekran başlıkları |
| `titleLarge` | Roboto | 22 sp | 500 | Kart başlıkları |
| `bodyLarge` | Roboto | 16 sp | 400 | Ana içerik metni |
| `bodyMedium` | Roboto | 14 sp | 400 | Açıklama metinleri |
| `labelSmall` | Roboto | 11 sp | 500 | Chip, badge metinleri |

---

## 4. Ekran Tasarımları — Detaylı Açıklamalar

### 4.1. Onboarding Ekranları (3 Ekran)

**Ekran 1 — "Şehrinizi Tanıyın"**
- Üst %60: Şehir silueti ve trafik ikonlarından oluşan illustrasyon (SVG, karanlık modda adapte olur).
- Başlık: "Şehrinizin nabzını tutun" (displaySmall, ortalanmış).
- Açıklama: Trafik, hava kalitesi ve enerji verilerine tek noktadan erişim.
- Alt: İleri butonu (FilledButton, tam genişlik) + atlama linki.

**Ekran 2 — "Anlık Bildirimler"**
- Illustrasyon: Telefon mockup'ı içinde push bildirim örneği.
- Başlık: "Olaylardan ilk siz haberdar olun".
- Açıklama: Acil durum ve planlı kesinti bildirimleri.

**Ekran 3 — "Belediyeye Ulaşın"**
- Illustrasyon: Harita pini ve bildirim formu.
- Başlık: "Sorunları bildirin, şehri güzelleştirin".
- Alt: "Başlayın" butonu (vurgulu, tam genişlik).

**Ortak özellikler:** İlerleme göstergesi (3 nokta, aktif nokta birincil renkte), sayfa geçişi slide animasyonu (300 ms, ease-in-out).

---

### 4.2. Giriş / Kayıt Ekranları

**Giriş Ekranı:**
- Uygulama logosu (48 dp) + başlık üstte.
- E-posta alanı (OutlinedTextField, e-posta klavyesi tetikler).
- Şifre alanı (göster/gizle toggle, trailing icon).
- "Şifremi Unuttum" metin linki (sağa hizalı, `primary` rengi).
- "Giriş Yap" FilledButton (tam genişlik, loading state: CircularProgressIndicator).
- Ayraç: "veya" (yatay çizgi ile).
- "Misafir olarak devam et" OutlinedButton.
- Alt: "Hesabın yok mu? Kayıt ol" linki.

**Hata Durumu:** Alan kenarı kırmızıya döner, altında `supportingText` ile hata mesajı. ("E-posta veya şifre hatalı.")

**Kayıt Ekranı:**
- Ad Soyad, E-posta, Şifre, Şifre (tekrar) alanları.
- Şifre gücü göstergesi (Zayıf / Orta / Güçlü — renk çubuğu).
- KVKK onay checkbox'ı (zorunlu; işaretlenmeden buton aktif olmaz).
- "Kayıt Ol" FilledButton.

---

### 4.3. Ana Sayfa

**Yapı:** CoordinatorLayout + NestedScrollView. Üstte sabit TopAppBar (`surface` rengi, gölge yok — Material You kuralı).

**Bileşenler (yukarıdan aşağıya):**

1. **Konum Başlığı:** "Elazığ, Merkez" + konum değiştir ikonu. Altında son güncelleme zamanı ("2 dakika önce güncellendi").

2. **Trafik Durumu Kartı (ElevatedCard):**
   - Sol: Trafik ışığı ikonu + renk göstergesi (YEŞİL / SARI / KIRMIZI).
   - Sağ: "Ortalama Hız: 38 km/s | Yoğunluk: %62".
   - Alt: "Haritada Gör →" metin butonu.

3. **Hava Kalitesi Kartı (ElevatedCard):**
   - Büyük AQI skoru (örn. "72 — Orta").
   - PM2.5, CO2, NO2 değerleri chip formatında (her biri ilgili renk badge ile).
   - Sağlık önerisi: "Hassas gruplar dış mekânı sınırlasın."

4. **Acil Uyarılar (varsa) — AlertBanner:**
   - Arka plan `errorContainer`, ikon ünlem işareti.
   - Başlık + kısa açıklama + "Detay" butonu.
   - Kritik değilse bu bileşen görünmez.

5. **Son Duyurular (yatay kaydırmalı liste):**
   - Her kart: Kategori chip (Ulaşım / Etkinlik / Acil), başlık, tarih.
   - Son kart: "Tümünü Gör →" linki.

6. **Yakındaki Olaylar (dikey liste, max 3):**
   - Her öğe: Olay ikonu + kategori + açıklama + mesafe ("1.2 km uzakta").

---

### 4.4. Harita Ekranı

**Tam Ekran Harita (Google Maps Compose):**

- **Katmanlar (toggle toolbar):**
  - Trafik yoğunluğu (varsayılan açık)
  - Aktif olaylar (pin'ler)
  - Hava kalitesi (renk haritası — opsiyonel)

- **Renk Kodlama:**
  | Renk | Anlam | Hız Aralığı |
  | :--- | :--- | :--- |
  | Koyu Yeşil | Serbest akış | > 50 km/s |
  | Sarı | Yavaşlama | 30–50 km/s |
  | Turuncu | Yoğun | 15–30 km/s |
  | Kırmızı | Tıkanık | < 15 km/s |

- **Olay Pin'leri:** Her kategori için farklı ikon (kaza: ünlem, yangın: alev, su baskını: dalga, yol çalışması: kask). Pin'e dokunulduğunda **BottomSheet** açılır.

- **BottomSheet (Olay Detayı):**
  - Kategori chip + başlık.
  - Açıklama + fotoğraf (varsa, yatay kaydırmalı carousel).
  - Konum + "Yol Tarifi Al" OutlinedButton.
  - Bildiren: "Vatandaş tarafından bildirildi — 14 dk önce".
  - Durum: pending / acknowledged / resolved (renk + metin).

- **Rota Planlama Modu:**
  - Sağ altta FAB "Rota Planla" (FilledButton, harita ikonu).
  - Dokunulduğunda iki TextField belirir: Başlangıç / Bitiş.
  - Hesapla butonuna basılınca yoğunluk rengi en az olan rota mavi çizgiyle çizilir.
  - Tahmini süre ve mesafe BottomSheet'te gösterilir.

- **Konum Butonu:** Sağ altta, FAB'ın üstünde. Kullanıcının mevcut konumuna yakınlaştırır.

---

### 4.5. Bildir Ekranı (FAB → Modal BottomSheet)

Ana navigasyondaki **"Bildir"** FAB'ına basılınca tam ekran modal açılır.

**Adım 1 — Kategori Seçimi:**
Grid (2 sütun × 3 satır), her hücre: büyük ikon + kategori adı.
- Kaza, Yangın, Su Baskını, Altyapı Arızası, Yol Hasarı, Diğer.
- Seçilen hücre `primaryContainer` arka planıyla vurgulanır.

**Adım 2 — Konum:**
- Küçük harita önizlemesi (interaktif olmayan, yalnızca görüntü).
- "GPS Konumumu Kullan" FilledTonalButton (varsayılan öneri).
- "Haritadan Pin Seç" OutlinedButton (dokunulunca harita tam ekran açılır, pin yerleştirme modu).
- Seçilen koordinat metin olarak gösterilir: "Elazığ Merkez, Çarşı Kavşağı yakını".

**Adım 3 — Fotoğraf (Opsiyonel):**
- Yatay kaydırmalı fotoğraf alanı; ilk kutu "+" ikonu (kamera veya galeri seçimi — BottomSheet ile).
- Maksimum 3 fotoğraf. Yüklenenlerin sağ üstünde "×" silme butonu.
- Her fotoğraf yüklenirken CircularProgressIndicator overlay görünür.

**Adım 4 — Açıklama ve Gönder:**
- OutlinedTextField, max 500 karakter, karakter sayacı gösterilir.
- Checkbox: "Anonim olarak gönder" (varsayılan: işaretli değil).
- "Gönder" FilledButton. Yükleme sırasında buton disabled + loading state.
- Başarılı sonuç: Animasyonlu onay ikonu (Lottie) + "Bildirim alındı, Takip No: #5821" mesajı.

---

### 4.6. Duyurular Ekranı

- **Filter Chips (yatay kaydırmalı):** Tümü, Ulaşım, Etkinlik, Acil, Genel.
- **Duyuru Listesi (LazyColumn):**
  - Her öğe: Kategori chip (renkli), Başlık, Kısa açıklama (max 2 satır, ellipsize), Tarih + okunmamış badge.
  - Okunmamış duyurular `surfaceVariant` arka planında, okunmuşlar beyaz.
- **Detay Ekranı:** Başlık, kategori, tarih, uzun açıklama, fotoğraf/video (tam genişlik), harita (opsiyonel), "Paylaş" ikonu (TopAppBar sağında).

---

### 4.7. Profil Ekranı

- Kullanıcı adı + e-posta + profil fotoğrafı (placeholder: baş harfi).
- **Kayıtlı Bölgeler:** Chip listesi + "Bölge Ekle" butonu (max 3 bölge, kayıtlı bölgede acil bildirim gelir).
- **Bildirim Tercihleri:** Her kategori için Switch (Trafik, Hava Kalitesi, Enerji Kesintileri, Acil Durum).
- **Görünüm:** Segment: Sistem / Aydınlık / Karanlık.
- **Dil:** TR / EN RadioButton grubu.
- **Hesap:** Şifre Değiştir, Hesabı Sil (kırmızı metin, onay diyaloğu gerektirir).
- **Uygulama Hakkında:** Sürüm, Gizlilik Politikası, KVKK Aydınlatma Metni.

---

## 5. Animasyon ve Geçiş Tasarımı

| Geçiş | Animasyon | Süre |
| :--- | :--- | :--- |
| Ekranlar arası navigasyon | Shared element transition (Compose) | 300 ms |
| BottomSheet açılış | Slide up, ease-out | 250 ms |
| FAB → Bildir modalı | Container transform | 350 ms |
| Olay pin'i seçimi | Scale + BottomSheet reveal | 200 ms |
| Loading → İçerik | Shimmer placeholder → fade-in | 400 ms |
| Onay animasyonu | Lottie check (120 frame) | ~1.5 sn |

---

## 6. Boş Durum (Empty State) Tasarımları

Her liste ekranı için boş durum tasarlandı:

| Durum | Illustrasyon | Başlık | Alt Metin |
| :--- | :--- | :--- | :--- |
| İnternet yok | Bağlantısız telefon | "Bağlantı Yok" | Son önbellek verisi gösteriliyor |
| Olay yok | Harita + tik işareti | "Her şey yolunda!" | Bölgenizde aktif olay bulunmuyor |
| Duyuru yok | Boş zarf | "Yeni duyuru yok" | Tüm duyurular okundu |
| Bildirim geçmişi boş | Boş liste illüstrasyonu | "Henüz bildirim yapmadınız" | Bir şey fark edince bildirin |

---

## 7. Figma Prototip Akışları

Figma'da aşağıdaki interaktif akışlar tıklanabilir prototip olarak hazırlanmıştır:

1. **Onboarding → Giriş → Ana Sayfa** (konum + bildirim izni diyalogları dahil)
2. **Ana Sayfa → Harita → Olay BottomSheet → Yol Tarifi**
3. **FAB → Bildir → Kategori → GPS Konum → Fotoğraf → Gönder → Onay**
4. **Duyurular → Filtre → Detay → Paylaş**
5. **Profil → Bildirim Tercihleri → Kayıtlı Bölge Ekle**

Her akış "Happy Path" (başarılı senaryo) + en az bir "Error State" (hata senaryosu) içermektedir.

---

## 8. Kullanılabilirlik Kontrol Listesi

Prototip, aşağıdaki kriterler açısından gözden geçirilmiştir:

| Kriter | Durum |
| :--- | :--- |
| Tüm dokunma hedefleri ≥ 48 dp | ✅ |
| Metin-arka plan kontrast ≥ 4.5:1 | ✅ |
| TalkBack erişilebilirlik etiketleri eklenmiş | ✅ |
| Kritik eylemler onay adımı içeriyor | ✅ |
| Tüm loading durumları tasarlandı | ✅ |
| Tüm hata durumları tasarlandı | ✅ |
| Karanlık mod uyumluluğu kontrol edildi | ✅ |
| Büyük metin boyutunda layout bozulmuyor | ✅ |

---

## 9. Sonraki Adımlar

Bu prototip belgesi ve Figma dosyaları esas alınarak Hafta 4 ve sonrasında:

1. **Kotlin proje iskeleti** kurulacak: modül yapısı, Hilt dependency injection, Navigation Compose iskeleti.
2. **OpenAPI şeması** backend ekibiyle netleştirilecek (trafik, enerji, bildirim endpoint'leri).
3. **MVP sprint planı** çıkarılacak: her sprint 1 ekran grubu hedefler.
4. **Kullanılabilirlik testi** için prototip link'i 3 farklı kullanıcı profiline gönderilecek (Hafta 6 raporu için veri toplanacak).

---

## 10. Güncelleme — Harita Tabanlı Konum Seçici Ekranı (MapPickerActivity)

> **Not:** Bu ekran prototip tasarımının ardından Hafta 5 geliştirme sürecinde kullanılabilirlik geri bildirimi doğrultusunda tasarıma eklendi.

### 10.1. Tasarım Gerekçesi

Olay bildirimi akışının Konum adımında yalnızca GPS koordinatı alınması, kullanıcıların olayın tam konumunu belirleyememesine yol açıyordu. Bu nedenle tam ekran, etkileşimli bir harita konum seçici ekranı eklendi.

### 10.2. Ekran Yapısı

```
┌─────────────────────────────────┐
│  Haritaya dokunarak olay        │
│  konumunu seçin          [mavi] │
├─────────────────────────────────┤
│                                 │
│         Google Maps             │
│       (Elazığ odaklı)          │
│                                 │
│   [Kullanıcı dokunuyor]        │
│         📍 Pin                  │
│                                 │
├─────────────────────────────────┤
│  38.67512, 39.22341             │
├─────────────────────────────────┤
│  [İptal]   [Bu Konumu Seç ✓]  │
└─────────────────────────────────┘
```

### 10.3. Etkileşim Akışı

Kullanıcı haritaya dokunduğunda turuncu pin yerleşir, koordinat alt çubukta gösterilir ve **"Bu Konumu Seç"** butonu aktif hale gelir. Onaylanınca koordinat `ReportActivity`'ye döner ve Konum adımı tamamlanır.

| Bileşen | Açıklama |
| :--- | :--- |
| Harita | Google Maps SDK — kaydırma ve zoom destekli |
| Pin | Haritaya tıklanınca yerleşir; ikinci tıklamada güncellenir |
| Koordinat gösterimi | Alt çubukta `enlem, boylam` formatında |
| GPS butonu | Mevcut konum varsa otomatik zoom yapar |
| Sonuç | `lat` + `lng` değerleri `ActivityResult` ile `ReportActivity`'ye iletilir |

### 10.4. Kullanılabilirlik Notları

- Minimum dokunma hedefi: haritanın tamamı
- "Bu Konumu Seç" butonu konum seçilmeden aktif olmaz — hatalı gönderimi önler
- "İptal" butonu konum adımına döner, seçim sıfırlanmaz


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
# Akıllı Şehir Vatandaş Mobil Uygulaması — Bildirim ve Acil Durum Özellikleri Geliştirme Belgesi

> **Bağlam:** Bu belge, Hafta 3 prototip tasarımı ve Hafta 2 gereksinim analizinde tanımlanan bildirim ve acil durum modülünün **Android Studio** ortamında **Kotlin** (XML View / ViewBinding tabanlı) ile gerçeklenmesini kapsamaktadır. Geliştirme sürecinde tespit edilen kullanılabilirlik sorunları giderilmiş; konum seçimi harita tabanlı hale getirilmiş, yerel olay deposu eklenmiş ve karanlık mod kalıcı hale getirilmiştir.

---

## 1. Geliştirilen ve Güncellenen Özellikler

Bu hafta kapsamında dört ana modül geliştirilmiş, iki modül düzeltilmiştir:

1. **Vatandaştan gelen bildirim (Citizen Reporting)** — 4 adımlı akış: kategori → harita konum seçimi → fotoğraf → özet/gönder.
2. **Harita Tabanlı Konum Seçici (MapPickerDialog)** — Kullanıcı GPS veya haritaya dokunarak olay konumunu belirler.
3. **Yerel Olay Deposu (IncidentStore)** — Bildirilen olaylar bellekte saklanır; haritada ve duyurularda anlık görünür.
4. **FCM Push Bildirimi** — Firebase Cloud Messaging entegrasyonu.
5. **WebSocket** — Canlı olay akışı altyapısı.
6. **Karanlık Mod** — SharedPreferences'a kaydedilen tercih uygulama yeniden başlatılsa da korunur.

---

## 2. Android Studio Proje Yapısı

```
app/
├── manifests/
│   └── AndroidManifest.xml
├── java/com.akillisehirapp/
│   ├── data/
│   │   ├── api/
│   │   │   ├── ApiService.kt
│   │   │   └── RetrofitClient.kt
│   │   ├── model/
│   │   │   ├── Models.kt
│   │   │   └── IncidentStore.kt        ← YENİ: yerel olay deposu
│   │   ├── repository/
│   │   └── websocket/
│   │       └── WebSocketManager.kt
│   ├── service/
│   │   └── AkilliSehirMessagingService.kt
│   └── ui/
│       ├── report/
│       │   ├── ReportActivity.kt       ← GÜNCELLENDİ
│       │   ├── ReportViewModel.kt      ← GÜNCELLENDİ
│       │   └── MapPickerDialog.kt      ← YENİ
│       ├── map/
│       │   └── MapFragment.kt          ← GÜNCELLENDİ: gerçek zamanlı konum + olaylar
│       ├── announcements/
│       │   └── AnnouncementsFragment.kt ← GÜNCELLENDİ: olaylar duyurularda görünür
│       └── profile/
│           └── ProfileFragment.kt      ← GÜNCELLENDİ: karanlık mod ve bildirim tercihleri
```

---

## 3. Harita Tabanlı Konum Seçici (MapPickerDialog)

### 3.1. Tasarım Gerekçesi

Önceki sürümde yalnızca GPS koordinatı alınıyordu. Kullanılabilirlik testlerinde kullanıcıların "olay nerede tam olarak?" sorusunu yanıtlayamadığı görüldü. Bu nedenle kullanıcının haritaya dokunarak pin bırakabileceği tam ekran bir konum seçici eklendi.

İlk implementasyon ayrı bir `Activity` (`MapPickerActivity`) olarak yazılmıştı; ancak test sürecinde bazı cihazlarda `SupportMapFragment`'ın `supportFragmentManager` üzerinden başlatılmasında kararlılık sorunu yaşandı. Bu nedenle konum seçici, `DialogFragment` tabanlı `MapPickerDialog`'a dönüştürüldü. Bu yaklaşım, uygulamanın normal harita ekranı (`MapFragment`) ile aynı `childFragmentManager` mekanizmasını kullandığından tüm ortamlarda kararlı çalışmaktadır.

### 3.2. Akış

```
[ReportActivity — Konum Adımı]
        │
        ├── [GPS Konumumu Kullan] → FusedLocationClient → koordinat
        │
        └── [Haritadan Seç] ──► MapPickerDialog (tam ekran DialogFragment) açılır
                                        │
                                   Tam ekran harita
                                   (Elazığ'a odaklı)
                                        │
                                   Haritaya dokunulur
                                        │
                                   Turuncu pin yerleşir
                                        │
                                   Koordinat alt çubukta gösterilir
                                        │
                                   [Bu Konumu Seç] butonu aktif
                                        │
                                   onLocationPicked(LatLng) callback
                                        │
                              [Dialog kapanır, ReportActivity'de kalır]
                                        │
                              viewModel.konumGuncelle(LatLng)
```

### 3.3. MapPickerDialog — Temel Kod

```kotlin
map.setOnMapClickListener { latLng ->
    map.clear()
    map.addMarker(MarkerOptions().position(latLng).title("Seçilen Konum"))
    seciliKonum = latLng
    tvKonumRef?.text = "Enlem: %.5f\nBoylam: %.5f".format(latLng.latitude, latLng.longitude)
    btnSecRef?.isEnabled = true
}
```

Seçilen koordinat lambda callback ile `ReportActivity`'ye iletilir — ayrı Activity başlatmaya ve `ActivityResult` mekanizmasına gerek kalmaz:

```kotlin
// ReportActivity.kt
binding.btnHaritadanSec.setOnClickListener {
    MapPickerDialog().also { dialog ->
        dialog.onLocationPicked = { latLng ->
            viewModel.konumGuncelle(latLng)
        }
        dialog.show(supportFragmentManager, "map_picker")
    }
}
```

---

## 4. Yerel Olay Deposu (IncidentStore)

Backend bağlantısı olmadan bildirilen olayların haritada ve duyurularda anlık görünmesi için `IncidentStore` singleton nesnesi tasarlandı.

```kotlin
object IncidentStore {
    private val _incidents = MutableLiveData<List<LocalIncident>>(emptyList())
    val incidents: LiveData<List<LocalIncident>> = _incidents

    fun ekle(incident: LocalIncident) {
        val liste = _incidents.value?.toMutableList() ?: mutableListOf()
        liste.add(0, incident)
        _incidents.postValue(liste)
    }
}
```

**Veri akışı:**

```
ReportViewModel.gonderi()
        │
        ▼
IncidentStore.ekle(LocalIncident)
        │
        ├──► MapFragment gözlemler → turuncu marker eklenir
        │
        └──► AnnouncementsFragment gözlemler → "Vatandaş Bildirimi" olarak listelenir
```

### 4.1. MapFragment entegrasyonu

```kotlin
IncidentStore.incidents.observe(viewLifecycleOwner) { liste ->
    liste.forEach { incident ->
        map.addMarker(
            MarkerOptions()
                .position(LatLng(incident.lat, incident.lng))
                .title(incident.kategori)
                .snippet("${incident.takipNo} · ${incident.zaman}")
                .icon(BitmapDescriptorFactory.defaultMarker(
                    BitmapDescriptorFactory.HUE_ORANGE))
        )
    }
}
```

### 4.2. AnnouncementsFragment entegrasyonu

```kotlin
IncidentStore.incidents.observe(viewLifecycleOwner) { incidents ->
    val incidentDuyurular = incidents.map { inc ->
        Announcement(
            id = inc.id.hashCode(),
            title = "Vatandaş Bildirimi: ${inc.kategori}",
            description = inc.aciklama.ifEmpty {
                "Konum: %.4f, %.4f".format(inc.lat, inc.lng)
            },
            category = AnnouncementCategory.EMERGENCY,
            isImportant = false,
            createdAt = inc.zaman
        )
    }
    adapter.submitList(incidentDuyurular + allAnnouncements)
}
```

---

## 5. Karanlık Mod — Kalıcı Tercih

### 5.1. Sorun

İlk implementasyonda `AppCompatDelegate.getDefaultNightMode()` çağrısı Activity yeniden oluşturulduğunda (tema değişiminden sonra) her zaman doğru değeri döndürmüyordu. Ayrıca `isChecked` atanması listener'ı tekrar tetikleyerek sonsuz döngüye yol açıyordu.

### 5.2. Çözüm

Tema tercihi `SharedPreferences`'a kaydedilir. `AkilliSehirApp.onCreate()` içinde uygulama başlarken okunur ve uygulanır. Listener atanmadan önce sıfırlanarak döngü engellenir.

```kotlin
// AkilliSehirApp.kt
override fun onCreate() {
    super.onCreate()
    val isDark = getSharedPreferences("app_prefs", MODE_PRIVATE)
        .getBoolean("dark_mode", false)
    AppCompatDelegate.setDefaultNightMode(
        if (isDark) AppCompatDelegate.MODE_NIGHT_YES
        else AppCompatDelegate.MODE_NIGHT_NO
    )
    createNotificationChannels()
}
```

```kotlin
// ProfileFragment.kt
private fun setupDarkMode() {
    val appPrefs = requireContext()
        .getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    binding.switchDarkMode.setOnCheckedChangeListener(null)   // döngü önleme
    binding.switchDarkMode.isChecked = appPrefs.getBoolean("dark_mode", false)
    binding.switchDarkMode.setOnCheckedChangeListener { _, checked ->
        appPrefs.edit().putBoolean("dark_mode", checked).apply()
        AppCompatDelegate.setDefaultNightMode(
            if (checked) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
    }
}
```

---

## 6. Bildirim Tercihleri — 4 Kategori

`ProfileFragment` içinde `include` etiketiyle eklenen dört `ItemPrefSwitchBinding` nesnesine doğrudan ViewBinding üzerinden erişildi; `findViewById` yerine `binding.prefX.tvPrefLabel` şeklinde kullanıldı.

```kotlin
private fun setupNotificationPrefs() {
    val prefs = requireContext()
        .getSharedPreferences("notif_prefs", Context.MODE_PRIVATE)
    listOf(
        Triple(binding.prefTraffic,    "Trafik Bildirimleri",         "notif_traffic"),
        Triple(binding.prefAirQuality, "Hava Kalitesi Bildirimleri",  "notif_air"),
        Triple(binding.prefEnergy,     "Enerji Kesinti Bildirimleri", "notif_energy"),
        Triple(binding.prefEmergency,  "Acil Durum Bildirimleri",     "notif_emergency")
    ).forEach { (pref, label, key) ->
        pref.tvPrefLabel.text = label
        pref.switchPref.isChecked = prefs.getBoolean(key, true)
        pref.switchPref.setOnCheckedChangeListener { _, checked ->
            prefs.edit().putBoolean(key, checked).apply()
        }
    }
}
```

---

## 7. Kamera URI — ViewModel'de Saklama

Activity yeniden oluşturulduğunda (kamera uygulaması ön plana geçince) `geciciFotoUri` alanı kayboluyordu. ViewModel'e taşındı:

```kotlin
// ReportViewModel.kt
var geciciFotoUri: Uri? = null   // Activity recreate'den etkilenmez

// ReportActivity.kt
private val kameraLauncher = registerForActivityResult(
    ActivityResultContracts.TakePicture()
) { basarili ->
    if (basarili) viewModel.geciciFotoUri?.let { viewModel.fotografEkle(it) }
}

private fun kameraAc() {
    try {
        val dosya = File.createTempFile("foto_", ".jpg", cacheDir)
        val uri = FileProvider.getUriForFile(this, "${packageName}.provider", dosya)
        viewModel.geciciFotoUri = uri
        kameraLauncher.launch(uri)
    } catch (e: Exception) {
        Toast.makeText(this, "Kamera açılamadı", Toast.LENGTH_SHORT).show()
    }
}
```

---

## 8. Yerel Kimlik Doğrulama (Local Auth)

Backend hazır olmadan uygulamanın tam akışıyla test edilebilmesi için yerel kimlik doğrulama mekanizması eklendi.

### 8.1. Kayıt Akışı

Kullanıcı kayıt olduğunda bilgileri `SharedPreferences`'a kaydedilir:

```kotlin
// RegisterActivity.kt — catch bloğu (backend yokken)
authPrefs.edit()
    .putString("registered_email",    email)
    .putString("registered_password", password)
    .putString("access_token",        "local_$email")
    .apply()
userPrefs.edit()
    .putString("user_name",  name)
    .putString("user_email", email)
    .apply()
```

### 8.2. Giriş Akışı

```kotlin
// LoginActivity.kt
when {
    // Kayıtlı kullanıcı eşleşiyor → giriş başarılı
    savedEmail != null && email == savedEmail && password == savedPass -> goMain()
    // Şifre yanlış
    savedEmail != null && email == savedEmail -> binding.tilPassword.error = "Şifre hatalı"
    // Hiç kayıt yok → demo mod
    savedEmail == null -> goMain()
    else -> binding.tilEmail.error = "Bu e-posta kayıtlı değil"
}
```

Backend bağlandığında `catch` bloğundaki yerel kayıt kaldırılıp gerçek API yanıtı kullanılacak.

---

## 9. Karanlık Mod — Tam Tema Desteği

### 9.1. Layout Renkleri

Tüm layout dosyalarında sabit renk referansları tema attribute'larıyla değiştirildi:

| Eski | Yeni |
| :--- | :--- |
| `android:background="@color/surface"` | `android:background="?attr/colorSurface"` |
| `android:textColor="@color/on_surface"` | `android:textColor="?attr/colorOnSurface"` |
| `android:textColor="@color/on_surface_variant"` | `android:textColor="?attr/colorOnSurfaceVariant"` |

Bu sayede sistem tema değiştiğinde arka plan ve metin renkleri otomatik güncellenir.

### 9.2. Gece Teması (`values-night/themes.xml`)

```xml
<style name="Theme.AkilliSehir" parent="Theme.MaterialComponents.DayNight.NoActionBar">
    <item name="colorPrimary">#90CAF9</item>
    <item name="colorSurface">#1A1C1E</item>
    <item name="colorOnSurface">#E3E2E6</item>
    <item name="colorOnSurfaceVariant">#C4C6CF</item>
    <item name="colorSurfaceVariant">#44474F</item>
    <item name="android:colorBackground">#1A1C1E</item>
    <item name="android:statusBarColor">#1A1C1E</item>
    <item name="android:navigationBarColor">#1A1C1E</item>
    <item name="android:windowLightStatusBar">false</item>
</style>
```

`colorBackground` (attr olmayan) yerine `android:colorBackground` kullanıldı — aksi hâlde `resource linking failed` hatası oluşuyordu.

---

## 10. Test Sonuçları

| Senaryo | Sonuç |
| :--- | :--- |
| Kategori seç → haritadan konum → fotoğraf → gönder | ✅ |
| Haritadan Seç → MapPickerDialog tam ekran açılır | ✅ |
| GPS ile konum al → pin yerleşir | ✅ |
| Kamera açılır, fotoğraf çekilir, Activity dönünce URI kaybolmaz | ✅ |
| Bildirim sonrası turuncu marker haritada görünür | ✅ |
| Bildirim duyurular listesinde "Vatandaş Bildirimi" olarak görünür | ✅ |
| Karanlık modda arka plan, yazılar ve kartlar doğru renkte görünür | ✅ |
| Karanlık mod toggle → uygulama yeniden açılınca tercih korunur | ✅ |
| 4 bildirim kategorisi ayrı ayrı toggle edilebilir | ✅ |
| Kayıt ol → giriş yap → profil adı/e-posta doğru gösterilir | ✅ |
| Yanlış şifre → hata mesajı gösterilir | ✅ |

---

## 11. Sonraki Adımlar

1. Backend hazır olunca `IncidentStore` yerine gerçek API çağrısı bağlanacak.
2. Yerel auth (`SharedPreferences`) yerine JWT token akışı kullanılacak.
3. `MapPickerDialog`'da mahalle/adres gösterimi (Geocoder) eklenecek.
4. Hafta 6 kullanılabilirlik testleri bu versiyonla gerçekleştirilecek.


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
 
