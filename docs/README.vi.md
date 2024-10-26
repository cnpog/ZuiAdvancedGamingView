
# ZuiAdvancedGamingView
Đọc tài liệu này bằng các ngôn ngữ khác (được dịch bằng gtp): [Deutsch](README.de.md), [English](../README.md), [Español](README.es.md), [Français](README.fr.md), [Русский](README.ru.md), [Türkçe](README.tr.md), [Tiếng Việt](README.vi.md), [简体中文](README.zh.md)

## Ảnh chụp màn hình

[<img src="images/screenshot1.png" width=399>](images/screenshot1.png)
[<img src="images/screenshot2.png" width=399>](images/screenshot2.png)
[<img src="images/screenshot3.png" width=399>](images/screenshot3.png)
[<img src="images/screenshot4.png" width=399>](images/screenshot4.png)

> [!warning]
> <b>Ứng dụng này chỉ hoạt động trên Lenovo ZUI và đã được kiểm tra trên ZUI 16.0.336 (CN).</b>
> <b>Không phải chế độ nào cũng hoạt động với mọi trò chơi.</b>

## Tính năng

- Hỗ trợ tất cả ứng dụng
- Hỗ trợ tất cả chế độ xem cho mọi ứng dụng
- Thêm chế độ 16:9 (tốt cho người tạo nội dung)

## Hướng dẫn thiết lập quan trọng

Để đảm bảo ứng dụng hoạt động chính xác, bạn cần cấp cho ứng dụng các quyền cần thiết thông qua ADB (ADB). Vui lòng chạy lệnh sau:

```bash
adb shell pm grant io.github.cnpog.gamingview android.permission.WRITE_SECURE_SETTINGS
```

Nếu không thể cài đặt APK trực tiếp, bạn cũng có thể cài đặt nó qua ADB với lệnh sau:

```bash
adb install --bypass-low-target-sdk-block ZuiAdvancedGamingView.apk
```

Lệnh này cho phép bạn bỏ qua giới hạn SDK mục tiêu thấp và cài đặt ứng dụng thành công.

## Đang phát triển

Lưu ý rằng ZuiAdvancedGamingView hiện đang trong quá trình phát triển. Nếu bạn gặp bất kỳ vấn đề nào hoặc có đề xuất cải tiến, đừng ngần ngại mở một vấn đề!

Cảm ơn bạn đã ủng hộ và chúc bạn sử dụng ứng dụng vui vẻ!

## Ghi nhận

Đặc biệt cảm ơn [cuongmaz](https://xdaforums.com/m/cuongmaz.12936472/#about) vì thông tin kỹ thuật.
