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
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Cemile Akay
GÖREVİN BURAYA YAPIŞTIRILACAK.

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
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Melih Ahmet Kocaman
GÖREVİN BURAYA YAPIŞTIRILACAK.

## Cemile Akay
GÖREVİN BURAYA YAPIŞTIRILACAK.

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
