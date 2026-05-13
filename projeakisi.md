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
