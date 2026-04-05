## HAFTA 1

# Nisanur Eltekin
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

# Abdullah Gümüş
GÖREVİN BURAYA YAPIŞTIRILACAK.

# Melih Ahmet Kocaman
GÖREVİN BURAYA YAPIŞTIRILACAK.

# Cemile Akay
GÖREVİN BURAYA YAPIŞTIRILACAK.

# Efecan Önal
GÖREVİN BURAYA YAPIŞTIRILACAK.

## HAFTA 2
Grup üyelerine görev tanımlanmadı.

## HAFTA 3

# Nisanur Eltekin
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

# Abdullah Gümüş
GÖREVİN BURAYA YAPIŞTIRILACAK.

# Melih Ahmet Kocaman
GÖREVİN BURAYA YAPIŞTIRILACAK.

# Cemile Akay
GÖREVİN BURAYA YAPIŞTIRILACAK.

# Efecan Önal
GÖREVİN BURAYA YAPIŞTIRILACAK.
