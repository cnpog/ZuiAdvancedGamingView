# ZuiAdvancedGamingView
Read this document in other languages (gpt translated): [Deutsch](docs/README.de.md), [English](README.md), [Español](docs/README.es.md), [Français](docs/README.fr.md), [Русский](docs/README.ru.md), [Türkçe](docs/README.tr.md), [Tiếng Việt](docs/README.vi.md), [简体中文](docs/README.zh.md)

## Screenshots

[<img src="docs/images/screenshot1.png" width=399>](docs/images/screenshot1.png)
[<img src="docs/images/screenshot2.png" width=399>](docs/images/screenshot2.png)
[<img src="docs/images/screenshot3.png" width=399>](docs/images/screenshot3.png)
[<img src="docs/images/screenshot4.png" width=399>](docs/images/screenshot4.png)

> [!warning]
> <b>This App will only work on Lenovo ZUI and was tested on ZUI 16.0.336 (CN).</b>
> <b>Not every mode works for every game.</b>

## Features

- Supports all Apps
- Supports all View Modes for all Apps
- Added 16:9 Mode (good for content creators)

## Important Setup Instructions

To ensure that the app functions correctly, you need to grant it the necessary permissions via ADB (ADB). Please run the following command:

```bash
adb shell pm grant io.github.cnpog.gamingview android.permission.WRITE_SECURE_SETTINGS
```

If you're unable to install the APK directly, you can also install it using ADB with the following command:

```bash
adb install --bypass-low-target-sdk-block ZuiAdvancedGamingView.apk
```

This command allows you to bypass the low target SDK block and successfully install the application.

## Work in Progress

Please note that ZuiAdvancedGamingView is currently work in progress. If you encounter any issues or have suggestions for improvements, feel free to open up an issue!

Thank you for your support, and enjoy using the app!

## Credits

Special thanks to [cuongmaz](https://xdaforums.com/m/cuongmaz.12936472/#about) for the technical information
