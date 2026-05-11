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
GÖREVİN BURAYA YAPIŞTIRILACAK.

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

Proje için GitHub üzerinden bir repository oluşturuldu. Proje klasör yapısı hazırlandı. Ekip üyeleri repositorye eklenerek projeye erişimleri sağlandı. Böylece ekip üyeleri ortak geliştirme ortamında çalışabilecek hale getirildi.

## Efecan Önal
# 🏙️ Akıllı Şehir Yönetim Sistemi - Proje Özeti

[cite_start]**Proje Amacı:** Şehir yaşamını daha verimli, güvenli ve sürdürülebilir kılmak[cite: 4]. [cite_start]Trafik akışını optimize etmek, enerji tüketimini izlemek, çevresel verileri analiz etmek ve acil durum müdahale sürelerini kısaltmayı hedefler[cite: 5].

## 📦 5 Ana Modül
1. [cite_start]**Veri Toplama ve Analiz:** Çevresel faktörlerin ve trafik verilerinin Python ile simülasyonu, anormallik tespiti[cite: 14, 15, 16].
2. [cite_start]**Trafik Optimizasyonu (DQN):** TensorFlow kullanılarak eğitilmiş yapay zeka ile trafik ışığı yönetimi ve acil araçlar için "yeşil dalga" kurgusu[cite: 17, 18, 19].
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
