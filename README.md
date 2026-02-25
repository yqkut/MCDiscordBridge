# MCDiscordBridge

Minecraft sunucu olaylarını Discord'a otomatik olarak ileten, çoklu sürüm (1.8.x–1.21.x) destekli bir Bukkit/Spigot/PaperMC eklentisidir.

## Özellikler
- Minecraft sunucusundaki önemli olayları Discord kanalına webhook ile gönderir:
  - Sohbet mesajları
  - Oyuncu giriş/çıkış/kick
  - Oyuncu ölümü
  - Başarı (achievement/advancement)
  - Oyuncu ve konsol komutları
  - Blok kırma/yerleştirme
  - Sunucu başlatma/durdurma
- Discord embed desteği
- Tamamen asenkron ve rate-limit uyumlu gönderim
- 1.8.x–1.21.x arası tüm Bukkit/Spigot/PaperMC sürümleriyle uyumlu
- Kolay yapılandırılabilir YAML config

## Kurulum
1. `MCDiscordBridge-1.0.0.jar` dosyasını `plugins` klasörüne atın.
2. Sunucunuzu başlatın. `plugins/MCDiscordBridge/config.yml` dosyasını düzenleyin:
   - Discord webhook URL'sini ve diğer ayarları girin.
3. Sunucunuzu yeniden başlatın veya `/mdb reload` komutunu kullanın.

## Yapılandırma
- `config.yml` dosyasında hangi eventlerin Discord'a gönderileceğini, mesaj formatlarını ve embed renklerini ayarlayabilirsiniz.
- Her event için embed veya düz mesaj seçebilirsiniz.

## Sürüm Uyumluluğu
- **1.8.x–1.12.x:** Eski Bukkit API (Achievement eventleri desteklenir)
- **1.13.x–1.21.x:** Yeni Bukkit API (Advancement eventleri desteklenir)
- Java 8+ (1.17+ için Java 16+, 1.18+ için Java 17+ gereklidir)

## Derleme
Projeyi derlemek için Maven kullanabilirsiniz:

```
mvn clean package
```

## Katkı ve Lisans
- Katkılarınızı pull request ile gönderebilirsiniz.
- MIT Lisansı ile dağıtılır.

## İletişim
- Sorularınız için: [GitHub Issues](https://github.com/yakut/MCDiscordBridge/issues)
