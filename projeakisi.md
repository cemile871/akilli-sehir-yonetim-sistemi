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
