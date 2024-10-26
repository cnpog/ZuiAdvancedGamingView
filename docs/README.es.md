
# ZuiAdvancedGamingView
Lea este documento en otros idiomas (gtp traducido): [Deutsch](README.de.md), [English](../README.md), [Español](README.es.md), [Français](README.fr.md), [Русский](README.ru.md), [Türkçe](README.tr.md), [Tiếng Việt](README.vi.md), [简体中文](README.zh.md)

## Capturas de pantalla

[<img src="images/screenshot1.png" width=399>](images/screenshot1.png)
[<img src="images/screenshot2.png" width=399>](images/screenshot2.png)
[<img src="images/screenshot3.png" width=399>](images/screenshot3.png)
[<img src="images/screenshot4.png" width=399>](images/screenshot4.png)

> [!warning]
> <b>Esta aplicación solo funcionará en Lenovo ZUI y fue probada en ZUI 16.0.336 (CN).</b>
> <b>No todos los modos funcionan para todos los juegos.</b>

## Características

- Soporta todas las aplicaciones
- Soporta todos los modos de vista para todas las aplicaciones
- Se agregó el modo 16:9 (bueno para creadores de contenido)

## Instrucciones de configuración importantes

Para asegurar que la aplicación funcione correctamente, es necesario otorgarle los permisos necesarios a través de ADB (ADB). Por favor, ejecute el siguiente comando:

```bash
adb shell pm grant io.github.cnpog.gamingview android.permission.WRITE_SECURE_SETTINGS
```

Si no puede instalar el APK directamente, también puede instalarlo usando ADB con el siguiente comando:

```bash
adb install --bypass-low-target-sdk-block ZuiAdvancedGamingView.apk
```

Este comando permite evitar el bloqueo de SDK de bajo nivel y permite instalar la aplicación con éxito.

## En desarrollo

Tenga en cuenta que ZuiAdvancedGamingView está en desarrollo. Si encuentra algún problema o tiene sugerencias de mejora, ¡no dude en abrir un problema!

Gracias por su apoyo y disfrute de la aplicación.

## Créditos

Agradecimientos especiales a [cuongmaz](https://xdaforums.com/m/cuongmaz.12936472/#about) por la información técnica.
