# Elazığ Akıllı Şehir Yönetim Sistemi — Yerel Kurulum Kılavuzu

Bu kılavuz, projenin backend servislerini (FastAPI), web yönetim panelini (React/Vite) ve mobil uygulamasını (Native Kotlin) yerel ortamınızda nasıl kurup çalıştıracağınızı adım adım açıklamaktadır.

---

## 📋 1. Sistem Gereksinimleri

Projeyi sorunsuz çalıştırabilmek için bilgisayarınızda aşağıdaki araçların kurulu olması gerekmektedir:

*   **Python:** v3.10 veya üzeri (Backend için)
*   **PostgreSQL:** v15 veya üzeri (Veritabanı için)
*   **Node.js:** v18 veya üzeri (Web Paneli için)
*   **Android Studio:** Jellyfish/Koala veya üzeri + Android SDK (Mobil Uygulama için)

---

## 🗄️ 2. Veritabanı ve Arka Uç (Backend) Kurulumu

Backend sunucusu, PostgreSQL veritabanı ile haberleşen FastAPI tabanlı bir RESTful API'dir.

### Adım 1: PostgreSQL Servisini Başlatın
PostgreSQL sunucunuzun port **5432** üzerinde çalıştığından ve şifresinin `.env` dosyasındaki şifreyle eşleştiğinden emin olun.
*   **Windows için (Manuel Başlatma):**
    ```powershell
    # PostgreSQL kurulum dizininize göre aşağıdaki komutla başlatabilirsiniz:
    C:\Program Files\PostgreSQL\<version>\bin\pg_ctl.exe start -D "C:\Program Files\PostgreSQL\<version>\data"
    ```

### Adım 2: Python Sanal Ortamı Oluşturun ve Kütüphaneleri Yükleyin
`backend` klasörüne gidin, bir sanal ortam oluşturun ve bağımlılıkları yükleyin:
```bash
cd backend
python -m venv venv

# Sanal ortamı aktifleştirin:
# Windows (PowerShell):
.\venv\Scripts\Activate.ps1
# Windows (CMD):
.\venv\Scripts\activate.bat
# macOS / Linux:
source venv/bin/activate

# Kütüphaneleri yükleyin:
pip install -r requirements.txt
```

### Adım 3: Çevre Değişkenleri (.env) Yapılandırması
`backend` klasöründe `.env` dosyasını oluşturun veya düzenleyin:
```env
DATABASE_URL=postgresql://postgres:123@127.0.0.1:5432/akillisehir?client_encoding=utf8
JWT_SECRET=8f45a76e289bf443fde44d2d4d9b23bfae6bb2a9d8bb1a4b49463cb92b512e02
JWT_ALGORITHM=HS256
ACCESS_TOKEN_EXPIRE_MINUTES=30
```

### Adım 4: Veritabanını Oluşturun ve Tohumlayın (Seeding)
Veritabanını oluşturup başlangıç sensör, duyuru, olay ve yönetici hesap verilerini eklemek için sırasıyla şu betikleri çalıştırın:
```bash
# Veritabanını oluşturur:
python create_db.py

# Tabloları oluşturur ve varsayılan verileri yükler:
python scripts/init_db.py
```
*   **Varsayılan Yönetici Hesabı:** `admin@belediye.gov`
*   **Varsayılan Yönetici Şifresi:** `securepass`

### Adım 5: Sunucuyu Başlatın
```bash
uvicorn app.main:app --host 127.0.0.1 --port 8000
```
API başarıyla başladığında Swagger dokümantasyonuna [http://127.0.0.1:8000/docs](http://127.0.0.1:8000/docs) adresinden erişebilirsiniz.

---

## 💻 3. Web Yönetim Paneli (React / Vite) Kurulumu

Yönetim paneli, belediye çalışanlarının sensörleri izlediği ve olayları yönettiği React projesidir.

### Adım 1: Bağımlılıkları Yükleyin
`web/belediye-panel` klasörüne gidin ve npm paketlerini kurun:
```bash
cd web/belediye-panel
npm install
```

### Adım 2: Geliştirici Sunucusunu Başlatın
```bash
npm run dev
```
Sunucu başladığında tarayıcınızdan [http://127.0.0.1:5173/](http://127.0.0.1:5173/) adresine giderek yönetim paneline erişebilir ve `admin@belediye.gov` / `securepass` bilgileriyle giriş yapabilirsiniz.

---

## 📱 4. Vatandaş Mobil Uygulaması (Android / Kotlin) Kurulumu

Mobil uygulama, vatandaşların olay bildiriminde bulunduğu ve şehir durumunu takip ettiği Kotlin tabanlı native Android uygulamasıdır.

### Adım 1: Android Studio ile Projeyi Açın
1.  Android Studio'yu açın.
2.  **Open** seçeneğine tıklayın.
3.  `mobil/mobiluygulama` klasörünü seçip projeyi içe aktarın (Gradle senkronizasyonunun tamamlanmasını bekleyin).

### Adım 2: API Yapılandırması (Gerektiğinde)
Uygulama, yerel bilgisayarınızda çalışan FastAPI sunucusuyla iletişim kurar. Android emülatörü üzerinden yerel bilgisayarınıza erişmek için `RetrofitClient.kt` içerisindeki adres `http://10.0.2.2:8000/api/v1/` olarak yapılandırılmıştır. Gerçek cihazda test etmek isterseniz, bilgisayarınızın yerel IP adresini (örn. `http://192.168.1.X:8000/api/v1/`) buraya girmelisiniz.

### Adım 3: Çalıştırın
1.  Bir Android sanal cihazı (Emulator) başlatın veya USB hata ayıklaması açık gerçek bir cihaz bağlayın.
2.  Android Studio üst barında yer alan yeşil **Run (Çalıştır)** butonuna basarak uygulamayı cihazınıza yükleyin.
