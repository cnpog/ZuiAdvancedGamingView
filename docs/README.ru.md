
# ZuiAdvancedGamingView
Читать этот документ на других языках (переведено gtp): [Deutsch](README.de.md), [English](../README.md), [Español](README.es.md), [Français](README.fr.md), [Русский](README.ru.md), [Türkçe](README.tr.md), [Tiếng Việt](README.vi.md), [简体中文](README.zh.md)

## Скриншоты

[<img src="images/screenshot1.png" width=399>](images/screenshot1.png)
[<img src="images/screenshot2.png" width=399>](images/screenshot2.png)
[<img src="images/screenshot3.png" width=399>](images/screenshot3.png)
[<img src="images/screenshot4.png" width=399>](images/screenshot4.png)

> [!warning]
> <b>Это приложение будет работать только на Lenovo ZUI и протестировано на ZUI 16.0.336 (CN).</b>
> <b>Не каждый режим работает для каждой игры.</b>

## Возможности

- Поддержка всех приложений
- Поддержка всех режимов просмотра для всех приложений
- Добавлен режим 16:9 (подходит для создателей контента)

## Важные инструкции по настройке

Чтобы приложение работало правильно, необходимо предоставить ему необходимые разрешения через ADB (ADB). Пожалуйста, выполните следующую команду:

```bash
adb shell pm grant io.github.cnpog.gamingview android.permission.WRITE_SECURE_SETTINGS
```

Если вы не можете установить APK напрямую, вы также можете установить его с помощью ADB с помощью следующей команды:

```bash
adb install --bypass-low-target-sdk-block ZuiAdvancedGamingView.apk
```

Эта команда позволяет обойти блокировку низкого уровня SDK и успешно установить приложение.

## В разработке

Обратите внимание, что ZuiAdvancedGamingView находится в разработке. Если вы столкнулись с проблемами или у вас есть предложения по улучшению, не стесняйтесь открыть задачу!

Спасибо за вашу поддержку и наслаждайтесь использованием приложения!

## Благодарности

Особая благодарность [cuongmaz](https://xdaforums.com/m/cuongmaz.12936472/#about) за техническую информацию.
