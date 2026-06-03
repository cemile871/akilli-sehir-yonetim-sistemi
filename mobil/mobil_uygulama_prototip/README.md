# Akıllı Şehir — Android Uygulaması (Aşama 1 / Hafta 3)

## İçerik
Bu aşama temel proje iskeletini ve tüm UI ekranlarını kapsar.

## Kurulum
1. Android Studio'da "Open existing project" ile bu klasörü aç
2. `app/src/main/AndroidManifest.xml` içindeki `BURAYA_GOOGLE_MAPS_API_KEY_YAZIN` yerine Maps API anahtarını yaz
3. Backend hazır olduğunda `RetrofitClient.kt` içindeki `BASE_URL` güncelle
4. `Sync Project with Gradle Files` çalıştır → Run

## Ekranlar
| Ekran | Sınıf |
|---|---|
| Splash | SplashActivity |
| Onboarding (3 sayfa) | OnboardingActivity |
| Giriş | LoginActivity |
| Kayıt | RegisterActivity |
| Ana Sayfa | HomeFragment |
| Harita | MapFragment |
| Duyurular | AnnouncementsFragment |
| Profil | ProfileFragment |

## Aşama 2'de Eklenecekler (Hafta 5)
- Olay Bildir akışı (4 adım: Kategori → Konum → Fotoğraf → Gönder)
- FCM push bildirimleri
- WebSocket canlı olay akışı
- Enerji/kesinti ekranı
