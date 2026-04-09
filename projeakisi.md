## Hafta 1

### Nisanur Eltekin
Bu proje süreci iki ana platform üzerinden yürütülmektedir:  
1. **www.ozalyildirim.com** (görev ve ekip yönetimi)  
2. **GitHub** (kod geliştirme ve versiyon kontrolü)

---

# 1. Ozalyildirim Sitesi Üzerinden Proje Yönetimi

Ozalyildirim sitesi proje yönetimi için merkezi bir pano sunar. Bu platformda aşağıdaki işlemler yapılır:

## Görev Takibi
- Her görev oluşturulup ekip üyelerine atanır.  
- Görev durumları “Bekleyen”, “Tamamlanan”, “Gecikme” olarak işaretlenir.  
- Görev tamamlama bildirimleri site üzerinden takip edilebilir.

## Ekip ve İletişim
- Grup üyeleri ve roller (yönetici, üye) site üzerinden tanımlanır.  
- Grup mesajlaşması ile hızlı bilgilendirme yapılabilir.

## Scrum AI Asistanı
- AI asistanı ekip performansını ve görev ilerlemesini analiz eder.  
- Üyelerin görev tamamlama yüzdesi, repo aktiviteleri ve iletişim performansı gösterilir.  
- Bu sayede hem bireysel hem de takım performansı anlık takip edilir.

---

# 2. GitHub Üzerinde Kod Geliştirme ve İşbirliği

Hoca Ozalyildirim üzerinden sizi GitHub repo’suna yönlendirir. GitHub süreci şu şekilde yürütülür:

## Branch ve Pull Request Süreci
- Ana branch: `main`  
- Her üye kendi özellik branch’inde (`akisi-gorev`, `bkisi-gorev` vb.) çalışır.  
- Kod değişiklikleri commitlenir ve Pull Request ile ana branch’e eklenir.  
- Diğer ekip üyeleri PR incelemesi yaptıktan sonra merge işlemi gerçekleştirilir.

## Repo ve Kod Takibi
- GitHub repo’su üzerinden yapılan commit’ler ve branch aktiviteleri, Ozalyildirim ile entegre edilerek AI asistan tarafından takip edilir.  
- Her üyenin kod katkısı ve işbirliği performansı site üzerinde görünür.

## Amaç
- Ekip üyeleri GitHub’da modülleri geliştirirken, Ozalyildirim üzerinden proje ilerlemesini takip eder.  
- Bu yöntem, görevlerin düzenli takibini ve proje sürecinin şeffaf yönetimini sağlar.

---

# 3. Çalışma Süreci Özeti

1. Ozalyildirim üzerinden görevler oluşturulur ve üyelere atanır.  
2. Üyeler kendi görev branch’lerinde GitHub’da çalışır.  
3. Kod değişiklikleri commitlenir ve Pull Request ile ana branch’e eklenir.  
4. Scrum AI asistanı görev ilerlemesini ve repo aktivitelerini analiz eder.  
5. Görevler tamamlandığında site üzerinden onaylanır ve grup performansı güncellenir.
Projenin genel analizi yapıldı. Projenin amacı, kapsamı ve paydaşları belirlendi.

### Abdullah Gümüş
# Akıllı Şehir Yönetim Sistemi - Gereksinimler

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

### Melih Ahmet Kocaman
GÖREVİN BURAYA YAPIŞTIRILACAK.

Geliştirme ortamı kurulumu araştırıldı.

### Cemile Akay
GÖREVİN BURAYA YAPIŞTIRILACAK.

Proje yönetim araçları incelendi.

### Efecan Önal
GÖREVİN BURAYA YAPIŞTIRILACAK.

Fonksiyonel gereksinimler toplanmaya başlandı.. 
