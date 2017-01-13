![HCT](http://fotos.subefotos.com/919a09a499ec6f205114f1cd482c2c7co.png)

# HCTControl by HCTeam™

[![Platform](http://img.shields.io/badge/platform-android-brightgreen.svg?style=flat)](http://developer.android.com/index.html) [![Language](http://img.shields.io/badge/language-java-orange.svg?style=flat)](http://www.oracle.com/technetwork/java/javase/downloads/index.html) [![Apache 2.0 License](https://img.shields.io/badge/license-Apache%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0.html)

### ● Librerías y dependencias utilizadas en este proyecto

- Android SDK: [Google Inc.](https://developer.android.com/sdk/terms.html)

- RootTools: [Stericson](https://github.com/Stericson/RootTools)

- CustomSettingsForDevs: [Wubydax](https://github.com/wubydax/CustomSettingsForDevs)

- CircleImageView: [Hdodenhof](https://github.com/hdodenhof/CircleImageView)

- Fab Button: [Shell-software](https://github.com/shell-software/fab)

- Calligraphy: [Chrisjenx](https://github.com/chrisjenx/Calligraphy)

- CkChangeLog: [Cketti](https://github.com/cketti/ckChangeLog)

- Build.prop-Editor: [Nathanpc](https://github.com/nathanpc/Build.prop-Editor)

- CPU-Spy: [Bvalosek](https://github.com/bvalosek/cpuspy)

- DPI-Changer: [Grennis](https://github.com/grennis/dpi-changer)

- FasterGPS: [Free-Software-for-Android](https://github.com/Free-Software-for-Android/FasterGPS)

- LicenseAdapter: [Yshrsmz](https://github.com/yshrsmz/LicenseAdapter)

- Catlog: [Nolanlawson](https://github.com/nolanlawson/Catlog)

- Toolbox Controller: [Wubydax](https://github.com/wubydax/ToolboxController)

- Blurry: [Wasabeef](https://github.com/wasabeef/Blurry)

- HexConverter-v2.0: [Wubydax](https://github.com/wubydax/HexConverter-v2.0)

- Cropper: [Edmodo](https://github.com/edmodo/cropper)

- KenBurnsView: [Flavioarfaria](https://github.com/flavioarfaria/KenBurnsView)

- Passcodeview: [Arjun-sna](https://github.com/Arjun-sna/android-passcodeview)

- Okhttp: [Square](https://github.com/square/okhttp)

- Http-request: [Kevinsawicki](https://github.com/kevinsawicki/http-request)

- OtaUpdater: [Grace5921](https://github.com/Grace5921/OtaUpdater)

##### Dar las gracias en nombre del `HCTeam` a todos los desarrolladores arriba mencionados por compartir su código y poder hacer esto más grande...

### ● Configuración para OTA Updater
Actualización de Rom(Unofficial) vía OTA. Aviso mediante una notificación y búsqueda de actualizaciones automáticas o manuales.
Son necesarios dos archivos de configuración, Updater.xml y updater-old-release.json. 
Aquí podrás ver los ejemplos de estos archivos:
- <a href="https://raw.githubusercontent.com/Grace5921/OtaUpdater/master/Updater.xml">Updater.xml</a>  
- <a href="https://raw.githubusercontent.com/Grace5921/OtaUpdater/master/updater-old-release.json">updater-old-release.json</a>  
- Enlace a <a href="https://github.com/Palleiro/HCTControl/tree/master/Xtras/OTA">carpeta</a>

### Configuración archivo .json

#### Versión Stable o Beta ?
configura 	
```
"stable" : "true"
"prerelease" : "false",
```
Si su versión anterior es estable

sino 
```
"prerelease" : "true",
"stable": "false"
```

#### tag_name
```
"tag_name" : "Nombre de la Rom, por ejemplo HCTRom V6.0----"
```

#### name
```
"name" : "Establece el nombre del archivo (incluya la extensión .zip), este será el nombre del archivo que se descargará en el dispositivo"
```

#### release_date
Sabes que hacer aqui :D.

#### browser_download_url
```
"browser_download_url" : "Esto debe ser el enlace directo a su versión de Rom anterior"
```
#### body
```
"body" : "Agregue el changelog de cada nueva versión de Rom aquí"\n" "
```

#### Añadir en el build.prop las siguientes lineas:
```
## Reemplazar el enlace por otro a su Updater.xml
ro.updater.uri=https://raw.githubusercontent.com/Grace5921/OtaUpdater/master/Updater.xml

## Reemplazar el enlace por otro a su updater-old-release.json
ro.updater.oldrelease.url=https://raw.githubusercontent.com/Grace5921/OtaUpdater/master/updater-old-release.json 

## Indicar fecha (Año Mes Día)
ro.rom.version=AAmmdd

## Establezca true si queremos mostrar un toast cuando inicia el servicio
ro.otaupdate.enable_toast=false

## Establezca true si desea que el registro de la aplicación en su logcat sea falso
ro.otaupdate.enable_log=true
```

### ● Configuración Anti-publicidad (ADS)
Copiar los dos siguientes archivos en system/etc/

- <a href="https://raw.githubusercontent.com/Palleiro/HCTControl/master/Xtras/HOST/system/etc/hosts.alt">hosts.alt</a>
- <a href="https://raw.githubusercontent.com/Palleiro/HCTControl/master/Xtras/HOST/system/etc/hosts.og">hosts.og</a>
- Enlace a <a href="https://github.com/Palleiro/HCTControl/tree/master/Xtras/HOST/system/etc">carpeta</a>


### ● Capturas

![HCTControl ][1]

[1]: https://raw.githubusercontent.com/Palleiro/HCTControl/master/HCTControl.png

● License
-------

    Copyright 2016 HCTeam™

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
