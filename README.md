# ZuiAdvancedGamingView

## Warning

This App will only work on Lenovo ZUI and was tested on ZUI 16.0.336.

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
