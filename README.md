📈 Investment Service (Yatırım Yönetim Servisi)

Bu proje, bir şirketin yaptığı yatırımları yönetmek, analiz etmek ve raporlamak amacıyla geliştirilmiş Spring Boot tabanlı bir RESTful servistir.
Uygulama, MongoDB kullanarak yatırım verilerini saklar ve gelişmiş raporlama & analiz özellikleri sunar.
-----------------------------------------------------------------------------------------------------------------------------------------------------
🚀 Projenin Amacı

Bu servisin amacı:

Şirket yatırımlarını kayıt altına almak

Yatırımlar üzerinde CRUD işlemleri gerçekleştirmek

Aylık, yıllık ve kategori bazlı raporlar üretmek

Yatırım trendlerini analiz etmek

Genişletilebilir ve ölçeklenebilir bir backend mimarisi sunmak
-----------------------------------------------------------------------------------------------------------------------------------------------------
🛠️ Kullanılan Teknolojiler

Java 11

Spring Boot

Spring Web

Spring Data MongoDB

MongoDB

Maven

REST API

Git & GitHub
-----------------------------------------------------------------------------------------------------------------------------------------------------
🧱 Proje Mimarisi

Proje, katmanlı mimari prensiplerine uygun olarak geliştirilmiştir:

Controller  →  Service  →  Repository  →  MongoDB

Katmanlar:

Controller: REST API uç noktalarını içerir

Service: İş mantığını barındırır

Repository: Veritabanı erişim işlemlerini yönetir

Model (Entity): MongoDB koleksiyonlarını temsil eder
-----------------------------------------------------------------------------------------------------------------------------------------------------
📦 Özellikler
🔹 CRUD İşlemleri

Yatırım ekleme

Yatırım listeleme

Yatırım güncelleme

Yatırım silme

🔹 Gelişmiş Raporlama

📅 Aylık yatırım raporu

📆 Yıllık yatırım raporu

🏷️ Kategoriye göre yatırım raporu

📊 Zaman bazlı yatırım trendleri

🔹 Arama & Filtreleme

Açıklamaya göre arama

Tarih aralığına göre filtreleme
-----------------------------------------------------------------------------------------------------------------------------------------------------
🔌 REST API Uç Noktaları
Yatırımlar
GET    /api/investments
GET    /api/investments/{id}
POST   /api/investments
PUT    /api/investments/{id}
DELETE /api/investments/{id}

Raporlama
GET /api/investments/report/monthly
GET /api/investments/report/yearly
GET /api/investments/report/trends
GET /api/investments/report/category?category=Emlak

Arama
GET /api/investments/search?description=Hisse&startDate=2023-01-01&endDate=2023-12-31
-----------------------------------------------------------------------------------------------------------------------------------------------------
🗄️ Örnek Yatırım JSON
{
  "description": "Teknoloji Hisseleri",
  "category": "Borsa",
  "amount": 15000,
  "date": "2024-01-15"
}
-----------------------------------------------------------------------------------------------------------------------------------------------------
▶️ Projeyi Çalıştırma

MongoDB’nin çalıştığından emin olun

Projeyi klonlayın:

git clone https://github.com/kullanici-adi/proje-adi.git


Proje dizinine girin:

cd proje-adi


Uygulamayı çalıştırın:

mvn spring-boot:run


API adresi:

http://localhost:8080
-----------------------------------------------------------------------------------------------------------------------------------------------------
📈 Gelecekte Eklenebilecek Özellikler

🔐 Kullanıcı kimlik doğrulama ve yetkilendirme (JWT / Spring Security)

📊 Dashboard ve grafik entegrasyonu

📤 Excel / PDF rapor çıktıları

🧮 ROI (Yatırım Getirisi) hesaplamaları

☁️ Docker & Kubernetes desteği

👨‍💻 Geliştirici

Bu proje, Spring Boot ve MongoDB öğrenme ve kurumsal backend geliştirme pratiği amacıyla geliştirilmiştir.
