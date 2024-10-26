
# ZuiAdvancedGamingView
以其他语言阅读本文档（gtp翻译）：[Deutsch](README.de.md), [English](../README.md), [Español](README.es.md), [Français](README.fr.md), [Русский](README.ru.md), [Türkçe](README.tr.md), [Tiếng Việt](README.vi.md), [简体中文](README.zh.md)

## 截图

[<img src="images/screenshot1.png" width=399>](images/screenshot1.png)
[<img src="images/screenshot2.png" width=399>](images/screenshot2.png)
[<img src="images/screenshot3.png" width=399>](images/screenshot3.png)
[<img src="images/screenshot4.png" width=399>](images/screenshot4.png)

> [!warning]
> <b>此应用仅在Lenovo ZUI上运行，并在ZUI 16.0.336（CN）上进行了测试。</b>
> <b>并非每个模式都适用于每个游戏。</b>

## 特点

- 支持所有应用程序
- 支持所有应用程序的所有查看模式
- 添加了16:9模式（适合内容创作者）

## 重要的安装说明

为了确保应用程序正常运行，您需要通过ADB授予必要的权限。请运行以下命令：

```bash
adb shell pm grant io.github.cnpog.gamingview android.permission.WRITE_SECURE_SETTINGS
```

如果您无法直接安装APK，您也可以使用以下命令通过ADB安装：

```bash
adb install --bypass-low-target-sdk-block ZuiAdvancedGamingView.apk
```

此命令允许您绕过低目标SDK阻止并成功安装应用程序。

## 正在开发中

请注意，ZuiAdvancedGamingView目前正在开发中。如果您遇到任何问题或有改进建议，请随时打开一个问题！

感谢您的支持，享受使用应用程序！

## 鸣谢

特别感谢[cuongmaz](https://xdaforums.com/m/cuongmaz.12936472/#about) 提供的技术信息。
