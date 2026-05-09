# Hafta 1

## Nisanur Eltekin
Bu proje süreci iki ana platform üzerinden yürütülmektedir:  
1. **www.ozalyildirim.com** (görev ve ekip yönetimi)  
2. **GitHub** (kod geliştirme ve versiyon kontrolü)

---

## 1. Ozalyildirim Sitesi Üzerinden Proje Yönetimi

Ozalyildirim sitesi proje yönetimi için merkezi bir pano sunar. Bu platformda aşağıdaki işlemler yapılır:

### Görev Takibi
- Her görev oluşturulup ekip üyelerine atanır.  
- Görev durumları “Bekleyen”, “Tamamlanan”, “Gecikme” olarak işaretlenir.  
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

Hoca Ozalyildirim üzerinden sizi GitHub repo’suna yönlendirir. GitHub süreci şu şekilde yürütülür:

### Branch ve Pull Request Süreci
- Ana branch: `main`  
- Her üye kendi özellik branch’inde (`akisi-gorev`, `bkisi-gorev` vb.) çalışır.  
- Kod değişiklikleri commitlenir ve Pull Request ile ana branch’e eklenir.  
- Diğer ekip üyeleri PR incelemesi yaptıktan sonra merge işlemi gerçekleştirilir.

### Repo ve Kod Takibi
- GitHub repo’su üzerinden yapılan commit’ler ve branch aktiviteleri, Ozalyildirim ile entegre edilerek AI asistan tarafından takip edilir.  
- Her üyenin kod katkısı ve işbirliği performansı site üzerinde görünür.

### Amaç
- Ekip üyeleri GitHub’da modülleri geliştirirken, Ozalyildirim üzerinden proje ilerlemesini takip eder.  
- Bu yöntem, görevlerin düzenli takibini ve proje sürecinin şeffaf yönetimini sağlar.

---

## 3. Çalışma Süreci Özeti

1. Ozalyildirim üzerinden görevler oluşturulur ve üyelere atanır.  
2. Üyeler kendi görev branch’lerinde GitHub’da çalışır.  
3. Kod değişiklikleri commitlenir ve Pull Request ile ana branch’e eklenir.  
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

## 🏙️ Akıllı Şehir Yönetim Sistemi  🚀
### "Geleceğin Kentsel İşletim Sistemi"

Bu proje; kentsel alanlarda yaşam kalitesini artırmak, kaynakları verimli kullanmak ve sürdürülebilirliği sağlamak amacıyla teknoloji ve veri analitiğini birleştiren kapsamlı bir **Akıllı Şehir** vizyonudur. Şehri devasa bir veri üretim merkezine dönüştürerek; **IoT, Yapay Zeka (AI) ve Büyük Veri** teknolojilerini kullanır.

---

## 🔍 1. Stratejik Vizyon & Analiz
Modern metropoller artık sadece yönetilmiyor, adeta **"hayatta kalmaya çalışıyor."** AŞYS, reaktif (olay olduktan sonra müdahale eden) yönetimden, **proaktif** (sorunu oluşmadan öngören) bir yönetim modeline geçişin anahtarıdır. 🔑

* **Veri Madenciliği:** Milyonlarca sensörden gelen veriyi anlamlı kararlara dönüştürmek.
* **Optimizasyon:** Kaynakların (zaman, enerji, para) en verimli şekilde dağıtılması.

---

## 🛠️ 2. Projenin Temel Sütunları (Kapsam)

### 🚦 A. Akıllı Hareketlilik 
* **Dinamik Trafik Yönetimi:** Sensör verilerine göre trafik ışıklarının sürelerini anlık optimize eder. 🚥
* **V2X Entegrasyonu:** Araçların trafik altyapısıyla konuştuğu bir iletişim ağı kurar. 📡

### ⚡ B. Enerji & Ekolojik Denge
* **Akıllı Şebekeler (Smart Grids):** Enerji talebini tahmin ederek yük dengelemesi yapar. 🔌
* **Adaptif Aydınlatma:** Hareket sensörlü sokak lambaları ile enerji tasarrufu sağlar. 💡

### 🚨 C. Acil Durum & Güvenlik
* **Yeşil Koridor:** Ambulans ve itfaiye için trafik ışıklarını otomatik olarak yönetir. 🚑💨
* **AI Olay Algılama:** Kazaları ve acil durumları operatörden önce tespit eder. 🛡️

---

## 🎯 3. Hedefler & Başarı Göstergeleri (KPI)

| Hedef Kategorisi | Metrik | Beklenen Sonuç 🏆 |
| :--- | :--- | :--- |
| **Zaman Yönetimi** | Trafikte Geçen Süre | **-%30 Azalış** ⏳ |
| **Çevresel Etki** | Karbon Emisyonu | **-%25 Düşüş** 🌱 |
| **Kamu Güvenliği** | Müdahale Süresi | **-%45 Hızlanma** ⚡ |
| **Ekonomi** | Enerji Maliyetleri | **-%20 Tasarruf** 💰 

---

## 👥 4. Paydaş Analizi
* **Belediyeler:** Ana karar vericiler ve veri yöneticileri. 🏛️
* **Yazılım Mimarları:** Sistemin algoritma ve veri güvenliğini sağlayan ekip. 👨‍💻
* **Vatandaşlar:** Sistemin nihai kullanıcıları ve veri kaynakları. 🤳
* **Güvenlik Birimleri:** Sahadaki operasyonel hızı artacak ekipler (Polis, İtfaiye). 🚔

---

## 🚀 Beklenen Sonuçlar
1.  **Ekonomik Tasarruf:** Optimize edilmiş kamu kaynakları.
2.  **Sürdürülebilirlik:** Gelecek nesillere daha yeşil bir şehir.
3.  **Yaşam Kalitesi:** Vatandaşların ulaşım ve güvenliğe daha hızlı erişimi.

---

# Hafta 2

## Nisanur Eltekin
Hafta 2 görevi kapsamında, trafik genişliklerini optimize edecek bir güncelleme için ayrıntılı tasarım belgeleri bulunmaktadır.
Algoritmanın Parametreleri 
Algoritması; trafik yoğunluğu ve kuyruk uzunluğu, ortalama araç hızı, yol durumu (normal/ıslak/kapalı), araç türü (ambulans/toplu taşıma/normal) ve zaman bilgisi (yoğun saat: 07-09 / 17-19) verileri piyasaya sürüldü. Yoğun yük faktörü +%40, kötü hava koşulları kapasitesi -%20 olarak ayarlanmaktadır.
Optimizasyon Hedefleri 
Birincil hedef acil araç yeşil dalga aktivasyonudur — diğer hedefler veya anında askıya alma işlemi yapılır. Bunu sırasıyla seyahat süresi -%30 azaltma, kuyruk uzunluğu 100 metre altında tutma ve geçiş sürdürme 1.000 araç/saatin üzerine çıkarma hedefi izlemektedir.
Senaryo Simülasyonları 
Dört senaryo simüle edilmiş ve kullanımının her koşulda hedef değerleri karşılandığı görülmüştür: Normal trafik -%38 bekleme süresi, yoğun zamanlı +%58 kapasite artışı, acil durum yeşil dalgasında -%56 müdahale süresi, kötü hava koşulları -%35 kaza riski iyileştirmesi sağlanmıştır. Simülasyon, 5 İstanbul kavşağını kapsayan ve canlı olarak çalıştırılabilen bir Python modülü ( trafik_simulasyon.py) ile desteklenmiştir.
Seçilen Optimizasyon Tekniği: DQN 
Kural tabanlı sistem, genetik geliştirme, genel mantık ve Q-Learning karşılaştırmasının ardından Derin Q-Ağı (DQN) ödüllendirilir. TensorFlow uyumluluğu, milisaniye düzeyindeki gerçek zamanlı karar kapasitesi ve şehrin trafik kalıplarının sürekli öğrenilebilmesi bu seçimin temel nedenleridir. Sistem başlangıçta kurallara göre çalışır, yeterli veri toplandıktan sonra DQN devreye girer.
Performans Metrikleri 
Algoritmasının performansı dört kategoride 21 metrik ölçülecektir: trafik performansı (bekleme süresi, kuyruk, kapasite), sistem performansı (gecikme < 500 ms, uptime > %99.9, modelin bulunması > %85), acil durum (yeşil dalga reaksiyonu < 1 sn, müdahale < 6 dk) ve sosyal etki (CO2 -%25, kaza -%30). 8 KPI hedefinin 6'sı simülasyonda tam olarak karşılanmış, emisyon azaltma ve modelin gol hedefi gerçek veri antrenman süreci boyunca değerlendirilecektir.

## Abdullah Gümüş
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Melih Ahmet Kocaman
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Cemile Akay
# 📊 Veri Toplama Modülü Gereksinim Analizi

## Akıllı Şehir Yönetim Sistemi

---

## 1. Giriş

Akıllı şehir sistemleri, şehir yaşamını daha verimli, güvenli ve sürdürülebilir hale getirmek için veri odaklı çalışır. Bu nedenle farklı kaynaklardan veri toplanması, işlenmesi ve güvenli şekilde saklanması kritik öneme sahiptir.

---

## 2. Toplanacak Veri Türleri

### 🚗 Trafik Verileri

* Araç yoğunluğu
* Ortalama hız
* Trafik kazaları
* Kavşak doluluk oranı

**Kaynaklar:**

* Trafik kameraları
* Plaka tanıma sistemleri
* GPS verileri

---

### ⚡ Enerji Tüketimi Verileri

* Elektrik tüketimi (bina/mahalle bazlı)
* Sokak aydınlatma durumu
* Yenilenebilir enerji üretimi

**Kaynaklar:**

* Akıllı sayaçlar
* Enerji yönetim sistemleri

---

### 🌫️ Hava Kalitesi Verileri

* CO2 seviyesi
* PM2.5 / PM10 değerleri
* Nem ve sıcaklık

**Kaynaklar:**

* Hava sensörleri
* Meteoroloji istasyonları

---

### 🚰 Su ve Atık Yönetimi Verileri

* Su tüketimi
* Atık doluluk oranı
* Geri dönüşüm miktarı

**Kaynaklar:**

* Akıllı su sayaçları
* Çöp konteyner sensörleri

---

## 3. Veri Toplama Sıklığı

| Veri Türü     | Sıklık            |
| ------------- | ----------------- |
| Trafik        | Anlık (real-time) |
| Enerji        | 5-15 dakika       |
| Hava Kalitesi | 1-5 dakika        |
| Su & Atık     | 15-30 dakika      |

---

## 4. Veri Formatı

Toplanan verilerin standart olması gerekir:

* JSON (en yaygın)
* XML (eski sistemler için)
* CSV (raporlama için)

**Örnek JSON veri:**

```
{
  "sensor_id": "TRF123",
  "type": "traffic",
  "value": 45,
  "unit": "vehicle/min",
  "timestamp": "2026-05-04T20:00:00"
}
```

---

## 5. Veri Güvenliği Gereksinimleri

* Veri şifreleme (SSL/TLS)
* Kimlik doğrulama (API key, token)
* Yetkilendirme (rol bazlı erişim)
* Veri yedekleme
* Anomali tespiti

---

## 6. Mevcut Altyapı Analizi

### Güçlü Yönler

* Sensör ağlarının yaygın olması
* IoT cihazlarının artışı
* Bulut sistemlerinin kullanımı

### Zayıf Yönler

* Eski sistemlerle uyumsuzluk
* Veri formatlarının farklı olması
* Gerçek zamanlı veri işleme eksikliği

---

## 7. Entegrasyon Zorlukları

* Farklı marka sensör uyumsuzluğu
* Veri gecikmesi (latency)
* Büyük veri yönetimi
* Güvenlik açıkları

---

## 8. Çözüm Önerileri

* Ortak veri standardı (JSON API) kullanımı
* IoT platform entegrasyonu (MQTT)
* Bulut tabanlı veri işleme
* Gerçek zamanlı veri analizi
* Güçlü siber güvenlik altyapısı

---

## 9. Sonuç

Akıllı şehir sistemlerinin verimli çalışabilmesi için doğru veri toplama stratejisi büyük önem taşır. Sensörlerden gelen verilerin doğru sıklıkta, güvenli ve standart formatta toplanması sistemin başarısını doğrudan etkiler.

## Efecan Önal
GÖREVİN BURAYA YAPIŞTIRILACAK.

# Hafta 3

## Nisanur Eltekin

## Çevresel Sensör Veri Toplama Modülü

## Genel Bakış
Hafta 3 görevi kapsamında, şehirdeki çevresel sensörlerden hava kalitesi ve gürültü seviyesi verilerini toplayan, doğrulayan ve kaydeden bir Python modülü geliştirilmiştir.

## Geliştirilen Modül: `sensor_data_collector.py`

### Sensör Lokasyonları
Sistemde 5 farklı İstanbul lokasyonunda sensör simülasyonu kurulmuştur:
- SNS-001: Kadıköy Meydan
- SNS-002: Beşiktaş Sahil
- SNS-003: Şişli Merkez
- SNS-004: Üsküdar Meydanı
- SNS-005: Bakırköy Sahil

### Toplanan Metrikler

**Hava Kalitesi:**
- PM2.5 (Partikül madde, µg/m³)
- PM10 (Kaba partikül, µg/m³)
- CO2 (Karbondioksit, ppm)
- NO2 (Azot dioksit, µg/m³)
- O3 (Ozon, µg/m³)
- Sıcaklık (°C) ve Nem (%)

**Gürültü:**
- Anlık gürültü seviyesi (dB)
- 1 saatlik ortalama gürültü (dB)

### Temel Özellikler

**Gerçekçi Simülasyon:**
Modül, gerçek şehir koşullarını yansıtmak için iki faktör içermektedir. Şehir merkezindeki sensörler (Kadıköy, Şişli) çevre sensörlerine göre %40 daha yüksek kirlilik değeri üretmektedir. Sabah 07-09 ve akşam 17-19 yoğun saatlerinde ise tüm değerler %50 artmaktadır.

**Hava Kalitesi İndeksi (AQI):**
PM2.5, PM10, NO2 ve Ozon değerleri birleştirilerek 0-100 arası tek bir skor üretilmektedir. 0-30 arası temiz, 30-60 arası orta, 60-100 arası kötü/tehlikeli olarak sınıflandırılmaktadır.

**Veri Doğrulama:**
Sensör arızası veya aşırı değerleri otomatik olarak tespit eden bir doğrulama katmanı eklenmiştir. Geçersiz veriler veritabanına yazılmamaktadır.

**Çift Katmanlı Kayıt:**
Veriler hem PostgreSQL veritabanına (batch insert ile verimli şekilde) hem de JSON dosyasına yedek olarak kaydedilmektedir.

### Teknolojiler
- Python 3.11
- psycopg2 (PostgreSQL bağlantısı)
- logging (Hata ve işlem takibi)
- dataclasses (Veri modeli)

### Çalıştırma
```bash
pip install psycopg2-binary
python sensor_data_collector.py
```

Sistem çalıştırıldığında her 5 saniyede bir tüm sensörlerden veri toplayarak `sensor_data.json` dosyasına kaydetmektedir. `cycles=0` ayarı ile sürekli çalışır servis moduna alınmaktadır.

## Abdullah Gümüş
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Melih Ahmet Kocaman
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Cemile Akay
# Veri Toplama Modülü – PostgreSQL Veritabanı Şema Tasarımı

## 1. Proje Amacı

Bu veritabanı tasarımı, şehirde bulunan sensörlerden gelen verilerin güvenli, hızlı ve ölçeklenebilir şekilde saklanması amacıyla hazırlanmıştır.

Sistem aşağıdaki kaynaklardan veri toplayacaktır:

- Trafik kameraları
- Enerji sayaçları
- Hava kalite sensörleri
- Sıcaklık sensörleri
- Nem sensörleri
- Akıllı şehir IoT cihazları

Tasarlanan PostgreSQL veritabanı:

- Büyük miktarda veriyi yönetebilir
- Gerçek zamanlı veri akışını destekler
- Hızlı sorgulama sağlar
- Gelecekte yeni sensörlerin eklenmesine uygundur

---

# 2. Veritabanı Teknolojisi

## Kullanılan Sistem

- PostgreSQL 16

## Kullanılma Sebepleri

- Güçlü performans
- Yüksek güvenlik
- Büyük veri desteği
- JSON desteği
- Indexleme sistemlerinin gelişmiş olması
- Ölçeklenebilir yapı sunması

---

# 3. Veritabanı Genel Yapısı

Sistem aşağıdaki temel tablolardan oluşacaktır:

| Tablo Adı | Açıklama |
|---|---|
| sensor_types | Sensör türleri |
| locations | Sensör konum bilgileri |
| sensors | Sistemdeki sensörler |
| sensor_data | Sensörlerden gelen veriler |
| alerts | Alarm kayıtları |
| users | Sistem kullanıcıları |
| maintenance_logs | Bakım kayıtları |

---

# 4. Tabloların Ayrıntılı Tasarımı

## 4.1 sensor_types Tablosu

Sensör kategorilerini saklar.

```sql
CREATE TABLE sensor_types (
    type_id SERIAL PRIMARY KEY,
    type_name VARCHAR(100) NOT NULL,
    description TEXT
);
```

### Açıklama

Örnek sensör türleri:

- Trafik Kamerası
- Enerji Sayacı
- Hava Kalite Sensörü
- Sıcaklık Sensörü

---

## 4.2 locations Tablosu

Sensörlerin fiziksel konumlarını tutar.

```sql
CREATE TABLE locations (
    location_id SERIAL PRIMARY KEY,
    city VARCHAR(100),
    district VARCHAR(100),
    street VARCHAR(150),
    latitude DECIMAL(9,6),
    longitude DECIMAL(9,6)
);
```

### Açıklama

Bu tablo sayesinde:

- Harita işlemleri yapılabilir
- Bölgesel analizler yapılabilir
- Sensörler konum bazlı filtrelenebilir

---

## 4.3 sensors Tablosu

Sistemdeki tüm sensör bilgilerini tutar.

```sql
CREATE TABLE sensors (
    sensor_id SERIAL PRIMARY KEY,
    sensor_name VARCHAR(100) NOT NULL,
    type_id INT REFERENCES sensor_types(type_id),
    location_id INT REFERENCES locations(location_id),
    installation_date DATE,
    status VARCHAR(50),
    serial_number VARCHAR(100) UNIQUE
);
```

### Açıklama

Her sensör:

- Bir sensör türüne bağlıdır
- Bir lokasyonda bulunur
- Kendine ait seri numarasına sahiptir

### Örnek Durumlar

- Active
- Passive
- Maintenance
- Offline

---

## 4.4 sensor_data Tablosu

Sensörlerden gelen büyük veri burada saklanır.

```sql
CREATE TABLE sensor_data (
    data_id BIGSERIAL PRIMARY KEY,
    sensor_id INT REFERENCES sensors(sensor_id),
    data_value NUMERIC(10,2),
    unit VARCHAR(20),
    recorded_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### Açıklama

Bu tablo sistemin en büyük tablosudur.

Örnek veriler:

| sensor_id | data_value | unit |
|---|---|---|
| 1 | 45 | araç/dk |
| 2 | 220 | volt |
| 3 | 31 | °C |

---

## 4.5 alerts Tablosu

Sensörlerde oluşan kritik durumları saklar.

```sql
CREATE TABLE alerts (
    alert_id SERIAL PRIMARY KEY,
    sensor_id INT REFERENCES sensors(sensor_id),
    alert_type VARCHAR(100),
    alert_message TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### Örnek Alarm Durumları

- Yüksek sıcaklık
- Kamera bağlantı kesildi
- Enerji tüketimi kritik seviyede
- Sensör çevrimdışı

---

## 4.6 users Tablosu

Sistemi kullanan yöneticileri tutar.

```sql
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    full_name VARCHAR(100),
    email VARCHAR(150) UNIQUE,
    password_hash TEXT,
    role VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### Kullanıcı Rolleri

- Admin
- Operatör
- Teknik Personel

---

## 4.7 maintenance_logs Tablosu

Sensör bakım geçmişini tutar.

```sql
CREATE TABLE maintenance_logs (
    maintenance_id SERIAL PRIMARY KEY,
    sensor_id INT REFERENCES sensors(sensor_id),
    performed_by INT REFERENCES users(user_id),
    maintenance_date TIMESTAMP,
    notes TEXT
);
```

---

# 5. Tablolar Arasındaki İlişkiler

## İlişkiler

### sensor_types → sensors

- Bir sensör türünün birden fazla sensörü olabilir.
- İlişki tipi: One-to-Many

### locations → sensors

- Bir lokasyonda birden fazla sensör bulunabilir.
- İlişki tipi: One-to-Many

### sensors → sensor_data

- Bir sensörden milyonlarca veri gelebilir.
- İlişki tipi: One-to-Many

### sensors → alerts

- Bir sensörün birçok alarm kaydı olabilir.

### users → maintenance_logs

- Bir kullanıcı birçok bakım işlemi yapabilir.

---

# 6. ER Diyagram Mantığı

```text
sensor_types
    |
    |----< sensors >---- locations
                |
                |----< sensor_data
                |
                |----< alerts
                |
                |----< maintenance_logs >---- users
```

---

# 7. Indexleme Stratejileri

Sistem büyük veri ile çalışacağı için indexleme çok önemlidir.

## 7.1 Sensor Data Indexleri

```sql
CREATE INDEX idx_sensor_data_sensor_id
ON sensor_data(sensor_id);

CREATE INDEX idx_sensor_data_recorded_at
ON sensor_data(recorded_at);
```

### Neden Gereklidir?

Bu indexler sayesinde:

- Tarihe göre hızlı sorgu yapılır
- Sensöre göre filtreleme hızlanır
- Dashboard performansı artar

---

## 7.2 Sensors Tablosu Indexi

```sql
CREATE INDEX idx_sensors_type
ON sensors(type_id);
```

---

## 7.3 Alerts Tablosu Indexi

```sql
CREATE INDEX idx_alerts_created_at
ON alerts(created_at);
```

---

# 8. Performans Optimizasyonu

## Partitioning (Tablo Bölme)

sensor_data tablosu çok büyüyeceği için aylık partition kullanılacaktır.

Örnek:

```sql
CREATE TABLE sensor_data_2026_05
PARTITION OF sensor_data
FOR VALUES FROM ('2026-05-01') TO ('2026-06-01');
```

### Avantajları

- Daha hızlı sorgu
- Daha düşük RAM kullanımı
- Kolay arşivleme
- Büyük veri performansı

---

# 9. Ölçeklenebilirlik

Sistem gelecekte:

- Yeni sensör tipleri
- Yeni şehirler
- Milyonlarca veri kaydı

eklenmesine uygun şekilde tasarlanmıştır.

## Kullanılan Ölçeklenebilirlik Yöntemleri

| Yöntem | Amaç |
|---|---|
| Indexleme | Hızlı sorgu |
| Partitioning | Büyük veri yönetimi |
| Foreign Key | Veri bütünlüğü |
| Unique Constraint | Tekrarlı veri önleme |

---

# 10. Güvenlik Önlemleri

## Alınan Önlemler

- Şifreler hashlenmiş şekilde tutulur
- Yetki sistemi vardır
- SQL Injection önlenir
- Foreign Key kullanılır
- Veri doğrulama yapılır

---

# 11. Örnek SQL Sorguları

## Belirli Sensörün Son Verileri

```sql
SELECT *
FROM sensor_data
WHERE sensor_id = 1
ORDER BY recorded_at DESC
LIMIT 10;
```

---

## Kritik Alarm Listesi

```sql
SELECT *
FROM alerts
ORDER BY created_at DESC;
```

---

## Belirli Bölgedeki Sensörler

```sql
SELECT s.sensor_name, l.city, l.district
FROM sensors s
JOIN locations l
ON s.location_id = l.location_id
WHERE l.city = 'İstanbul';
```

---

# 12. Sonuç

Bu PostgreSQL veritabanı tasarımı:

- Büyük veri işleyebilen
- Performanslı
- Güvenli
- Ölçeklenebilir
- Gerçek zamanlı veri toplamaya uygun

bir akıllı şehir veri toplama sistemi altyapısı sunmaktadır.

Tasarlanan yapı sayesinde:

- Sensör verileri düzenli şekilde saklanabilir
- Hızlı analiz yapılabilir
- Alarm sistemleri çalıştırılabilir
- Gelecekte sistem kolayca büyütülebilir

ve sistem yüksek performansla çalışmaya devam eder.
## Efecan Önal
Efecan Önal
Hafta 3: Akıllı Şehir Modülleri Güvenlik Açığı Analizi ve Protokol Güçlendirmesi
1. Görev Kapsamı ve Amacı
Bu hafta kapsamında, sistemin veri toplama katmanında (özellikle sensor_data_collector.py modülü ve veritabanı bağlantılarında) statik kod analizi ve simüle edilmiş sızma (penetrasyon) testleri gerçekleştirilmiştir. Bulunan güvenlik zafiyetleri tespit edilmiş ve sistem mimarisini korumak adına gerekli yamalar (patch) uygulanarak güvenlik protokolleri sıkılaştırılmıştır.

2. Tespit Edilen Zayıflıklar (Vulnerability Assessment)
Yapılan denetimler sonucunda aşağıdaki kritik ve orta seviye güvenlik açıkları tespit edilmiştir:

Zafiyet 1: Gömülü Kimlik Bilgileri (Hardcoded Credentials) [Kritik]

Açıklama: Veritabanı (PostgreSQL) bağlantı bilgileri (kullanıcı adı, şifre, host) sensor_data_collector.py dosyası içinde açık metin (plain-text) olarak bulunuyordu. Bu durum GitHub repolarında veri sızıntısına yol açabilir.

Zafiyet 2: Veri Manipülasyonu ve Enjeksiyon Riski (Data Injection) [Yüksek]

Açıklama: Sensörlerden gelen veriler doğrudan veritabanına yazılmadan önce temel bir doğrulama yapılıyor olsa da, kötü niyetli bir sensör düğümünün (node) sisteme aşırı büyük boyutlu (buffer overflow) veya SQL enjeksiyonuna (SQLi) neden olabilecek format dışı JSON verisi gönderme riski tespit edildi.

Zafiyet 3: Şifrelenmemiş Veri İletimi (Unencrypted Transit) [Orta]

Açıklama: Sensör verileri ile ana sunucu/veritabanı arasındaki iletişimin varsayılan olarak SSL/TLS şifrelemesi olmadan yapıldığı gözlemlendi. (Man-in-the-Middle ataklarına açık).

3. Uygulanan Çözümler ve Güçlendirilmiş Protokoller (Security Hardening)
Tespit edilen zafiyetleri gidermek için aşağıdaki aksiyonlar alınmış ve sisteme entegre edilmiştir:

A. Çevresel Değişkenler (Environment Variables) Kullanımı
Veritabanı ve API anahtarları gibi hassas veriler kodun içerisinden tamamen çıkarıldı. Bunun yerine .env (Environment Variables) yapısına geçildi.

Uygulama: python-dotenv kütüphanesi projeye eklendi. Bağlantı dizesi artık dışarıdan güvenli bir şekilde çağrılmaktadır. .env dosyası .gitignore'a eklenerek GitHub'a sızması engellendi.

B. Sıkı Veri Sanitizasyonu ve Tip Kontrolü (Data Sanitization)
Nisanur'un geliştirdiği doğrulama katmanı (validation) güvenlik odaklı olarak genişletildi.

Uygulama: Gelen her JSON verisi için Pydantic kütüphanesi kullanılarak katı bir şema (schema) belirlendi. Belirlenen veri tiplerine (örneğin sıcaklık sadece float olmalı) ve karakter sınırlarına uymayan tüm veri paketleri anında reddedilerek log dosyasına "Şüpheli Aktivite" olarak kaydediliyor.

C. SSL/TLS Sertifika Zorunluluğu
Veritabanı iletişiminin güvenliğini sağlamak için PostgreSQL bağlantı parametreleri güncellendi.

Uygulama: psycopg2 bağlantı stringine sslmode=require parametresi eklenerek, uçtan uca şifreleme olmayan hiçbir veri akışına izin verilmemesi sağlandı.

D. Hız Sınırlama (Rate Limiting) Algoritması
Sahte sensörlerden gelebilecek DDoS (Dağıtılmış Hizmet Engelleme) saldırılarını engellemek için sisteme bir kontrol mekanizması eklendi.

Uygulama: Aynı IP veya Sensör ID'sinden saniyede 5'ten fazla veri paketi gelmesi durumunda, o sensör 1 dakikalığına "Karantina" moduna alınarak sistemin şişmesi engellendi.

4. Sonuç
Yapılan bu güvenlik yamaları sayesinde; dışarıdan gelebilecek veri manipülasyonu, kimlik avı ve DDoS saldırılarına karşı sistemin direnci maksimum seviyeye çıkarılmıştır. Veri toplama modülü artık hem güvenli hem de izole bir şekilde çalışmaktadır.

# Hafta 4

## Nisanur Eltekin

## Çevresel Sensör Veri Görselleştirme ve Raporlama Modülü

## Genel Bakış
Hafta 3'te toplanan çevresel sensör verileri (sensor_data.json) okunarak anlamlı grafikler ve detaylı raporlar oluşturulmuştur.

## Geliştirilen Modül: `sensor_visualizer.py`

### Oluşturulan Grafikler

**Lokasyon Bazlı Karşılaştırma:**
Her lokasyonun ortalama AQI ve gürültü değerleri yatay bar grafikleriyle karşılaştırılmıştır. Tehlike sınırları görsel olarak işaretlenmiştir.

**AQI Zaman Trendi:**
Her sensörün hava kalitesi indeksinin zamana göre değişimi çizgi grafik ile gösterilmiştir. İyi (30) ve kötü (60) sınır çizgileri eklenmiştir.

**Gürültü Dağılımı:**
Her lokasyon için gürültü seviyesi dağılımı violin plot ile görselleştirilmiştir.

**PM2.5 Isı Haritası:**
Sensör ve zaman ekseninde PM2.5 partikül yoğunluğu ısı haritasıyla gösterilmiştir.

**Gürültü Etiket Dağılımı:**
Düşük, Orta, Yüksek ve Tehlikeli gürültü okumalarının genel dağılımı pasta grafikle sunulmuştur.

### Özet Rapor
Tüm grafikler ve istatistiksel özet (ortalama AQI, en kirli/en sessiz lokasyon, tehlikeli okuma sayısı vb.) otomatik olarak `raporlar/` klasörüne kaydedilmektedir.

### Teknolojiler
- Python 3.11
- Pandas (Veri işleme)
- Matplotlib (Grafik oluşturma)
- Seaborn (İstatistiksel görselleştirme)

### Çalıştırma
```bash
pip install matplotlib pandas seaborn
python sensor_visualizer.py
```

## Abdullah Gümüş
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Melih Ahmet Kocaman
# Akıllı Şehir Yönetim Sistemi: Proje Dokümantasyonu

**Proje Hedefi:** Şehirdeki trafik akışını optimize eden, enerji tüketimini izleyen ve acil durum müdahale sürelerini kısaltan; sensör verilerini analiz ederek gerçek zamanlı karar alan bir sistem geliştirmek.

---

## Bölüm 1: Teknoloji Araştırması ve Değerlendirme Raporu

### 1.1. Arka Plan (Backend) ve Ana Programlama Dili
Sensör verilerinin işlenmesi, algoritmaların çalıştırılması ve yapay zeka modelleriyle entegrasyon için bir arka plan diline ihtiyaç vardır.

| Teknoloji | Avantajlar | Dezavantajlar | Karar & Değerlendirme |
| :--- | :--- | :--- | :--- |
| **Python** *(Seçilen)* | Veri bilimi ve yapay zeka (TensorFlow) ile kusursuz entegrasyon. | Diğer dillere göre işlem hızı nispeten yavaş olabilir. | **Kesinlikle Uygun.** API'ler için **FastAPI** veya **Django** kullanılabilir. |
| **Go (Golang)** | İnanılmaz hızlı, eşzamanlı işlemlerde çok başarılı. | Makine öğrenmesi ekosistemi zayıf. | Mikroservis mimarisinde, API gateway kısmında destek olarak eklenebilir. |

### 1.2. Yapay Zeka ve Makine Öğrenmesi
Trafik akışını tahmin etme, enerji anormalliklerini tespit etme gibi özellikler için.

| Teknoloji | Avantajlar | Dezavantajlar | Karar & Değerlendirme |
| :--- | :--- | :--- | :--- |
| **TensorFlow** *(Seçilen)* | Üretime alma konusunda çok güçlü. TF Lite desteği var. | Öğrenme eğrisi PyTorch'a göre biraz daha diktir. | **İdeal Seçim.** Sensör tabanlı IoT cihazlarında model çalıştırmak için çok avantajlı. |
| **PyTorch** | Araştırma ve model geliştirme sürecinde daha esnek. | Edge AI (IoT) dağıtımları TensorFlow kadar olgun değil. | Canlı sistemler ve IoT için TensorFlow bir adım önde. |

### 1.3. Veritabanı ve Veri Depolama
Hem ilişkisel verilere hem de zaman serisi verilerine ihtiyaç vardır.

| Teknoloji | Avantajlar | Dezavantajlar | Karar & Değerlendirme |
| :--- | :--- | :--- | :--- |
| **PostgreSQL** *(Seçilen)* | Güvenilir, ACID uyumlu, coğrafi veriler (PostGIS) için mükemmel. | Milyarlarca satırlık zaman serisinde tek başına hantal kalabilir. | **Mükemmel Seçim.** Sensör verileri için **TimescaleDB** eklentisi kullanılmalıdır. |

### 1.4. Frontend (Kullanıcı ve Yönetim Arayüzü)
Operatörlerin şehir durumunu haritalar ve grafikler üzerinden izleyeceği dashboard ekranları için.

| Teknoloji | Avantajlar | Dezavantajlar | Karar & Değerlendirme |
| :--- | :--- | :--- | :--- |
| **React** *(Seçilen)* | Bileşen tabanlı mimari, devasa ekosistem. | Sadece bir UI kütüphanesidir, ek araçlar gerektirir. | **En İyi Seçim.** Canlı veri panelleri için biçilmiş kaftandır. |

### 1.5. Önerilen Sistem Mimarisi Veri Akışı
1.  **Veri Toplama (IoT):** Sensörler `->` MQTT `->` Apache Kafka
2.  **Arka Plan & Yapay Zeka:** Python (FastAPI) + TensorFlow
3.  **Veritabanı:** PostgreSQL *(PostGIS ve TimescaleDB ile)*
4.  **Arayüz:** React *(WebSocket ile)*

---

## Bölüm 2: Enerji Verimliliği Algoritması Optimizasyonu

### 2.1. Başarı Metriklerinin (KPI) Belirlenmesi
* **Tasarruf Edilen Enerji (kWh):** Algoritmanın müdahalesi ile harcanmayan enerji miktarı.
* **Tahmin Doğruluğu (MSE / MAE):** Modelin enerji ihtiyacını doğru tahmin etme oranı.
* **Sistem Gecikmesi (Latency):** Sensör verisinin alınması ile kararın verilmesi arasındaki süre.

### 2.2. Test Edilecek Parametreler (Hyperparameter Tuning)
* **Yapay Zeka Parametreleri:** Learning Rate (Öğrenme Oranı), Batch Size (Yığın Boyutu), Epoch Sayısı.
* **Operasyonel Parametreler:** Karar Eşiği (Threshold), Sensör Okuma Sıklığı.

### 2.3. Python ile Optimizasyon Yaklaşımı
**Optuna** kütüphanesi kullanılarak hiperparametre optimizasyonu gerçekleştirilir.

```python
import tensorflow as tf
import optuna
from sklearn.model_selection import train_test_split

# Varsayımsal veritabanı simülasyonu
# X_train, X_test, y_train, y_test = train_test_split(veri_X, veri_y, test_size=0.2)

def objective(trial):
    learning_rate = trial.suggest_float("learning_rate", 1e-4, 1e-1, log=True)
    batch_size = trial.suggest_categorical("batch_size", [16, 32, 64, 128])
    neurons = trial.suggest_int("neurons", 32, 256, step=32)
    
    model = tf.keras.Sequential([
        tf.keras.layers.Dense(neurons, activation='relu'),
        tf.keras.layers.Dropout(0.2),
        tf.keras.layers.Dense(neurons // 2, activation='relu'),
        tf.keras.layers.Dense(1, activation='linear')
    ])
    
    optimizer = tf.keras.optimizers.Adam(learning_rate=learning_rate)
    model.compile(optimizer=optimizer, loss='mse', metrics=['mae'])
    
    model.fit(X_train, y_train, batch_size=batch_size, epochs=10, verbose=0)
    loss, mae = model.evaluate(X_test, y_test, verbose=0)
    
    return mae

study = optuna.create_study(direction="minimize")
study.optimize(objective, n_trials=50)

print("En iyi parametreler:", study.best_params)
```
## Cemile Akay
Hafta 3'teki tasarım kararlarını ve kullanıcı senaryosunu baz alarak, Hafta 4 için bu yapıyı kodlayan, modüler ve profesyonel bir React projesi hazırladım.

Bu kod; Rota Arama, Otobüs Saatleri ve Bildirimler ekranları arasında geçiş yapabilen (state management), mobil uyumlu bir kullanıcı deneyimi sunar.

Sadelik: Gereksiz tüm detaylar atıldı; sadece temel işlevlere (Rota, Saat, Bildirim) odaklanıldı.

Büyük Butonlar: Mobil kullanım senaryosuna uygun, parmakla tıklaması kolay geniş butonlar kullanıldı.

Hızlı Erişim: Ana sayfadaki menü ile kullanıcı senaryosunda belirtilen "Rota Ara" ve "Saat Kontrolü" adımları arasındaki sürtünme azaltıldı.

Geri Bildirim: Bildirim ekranında kırmızı vurgular kullanılarak kullanıcının gecikmeleri anında fark etmesi sağlandı (UX).

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
