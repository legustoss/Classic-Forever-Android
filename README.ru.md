[English](README.md) | **Русский**

---

```markdown
# Classic Forever Android

Android-порт **WoW Forever Beta (ARM64 Native)** для устройств на Snapdragon (AYN Thor, Odin, Retroid и др.).

Это форк проекта [jaredgei/wow-forever-android](https://github.com/jaredgei/wow-forever-android) с переработанной логикой: приложение больше не использует Battle.net и Blizzard CDN, а подключается к **приватному серверу Classic Forever**.

---

## Что это

**Classic Forever Android** — это Android-обёртка вокруг Wine + DXVK + Box64 + Turnip, которая запускает нативный ARM64-клиент World of Warcraft: Classic Beta и подключает его к приватному серверу через подмену публичного ключа в памяти процесса.

Приложение:

- Запускает `WowB-ARM64.exe` через Wine (Proton 11 ARM64EC).
- Использует Turnip-драйвер для Adreno 7xx/8xx.
- Патчит память клиента через нативную `version.dll`, подменяя ключ группы 8 на публичный ключ сервера Classic Forever.
- Прописывает `SET portal "auth.gpon.com.co"` в `WTF/AndroidConfig.wtf`.

**Не использует:** Battle.net, Blizzard CDN, официальные серверы Blizzard.

---

## Требования

- Android-устройство на **Snapdragon 8-й серии** (Adreno 740 или Adreno 8xx).
- Минимум **8 ГБ ОЗУ** (рекомендуется 12+).
- **~30 ГБ** свободного места (клиент + окружение Wine).
- Android **11+** (рекомендуется Android 13+).
- Права **«All files access»** (Manage external storage).

Тестировалось на **AYN Thor** (Snapdragon 8 Gen 2).

---

## Установка

### 1. Скачай APK

Собери APK из исходников или скачай готовый релиз (если он есть в разделе Releases).

### 2. Установи APK

Разреши установку из неизвестных источников, если Android попросит.

### 3. Дай права на файлы

При первом запуске приложение попросит **«All files access»**. Без этого оно не сможет читать и патчить файлы игры.

### 4. Скачай клиент игры

Клиент скачивается **на ПК** через официальный лаунчер Battle.net.

1. Установи [Battle.net](https://www.blizzard.com/download/) на Windows-ПК.
2. Нажми **Win + R**, вставь команду и нажми Enter:

   ```
   "C:\Program Files (x86)\Battle.net\Battle.net.exe" --game=wow_classic_beta --install
   ```

3. Лаунчер начнёт загрузку **Classic Beta** клиента. Дождись завершения.
4. Скачанный клиент будет лежать в папке установки Battle.net (обычно `C:\Program Files (x86)\World of Warcraft\_classic_beta_\` или рядом с лаунчером).
5. Скопируй папку `_classic_beta_` и файл `.build.info` на Android-устройство, например в `/storage/emulated/0/WoW Forever/`.

**Структура папки на устройстве должна быть такой:**

```
/storage/emulated/0/WoW Forever/
├── .build.info
├── _classic_beta_/
│   └── WowB-ARM64.exe
└── Data/
    ├── ...
```

### 5. Запусти приложение

1. Открой **Classic Forever**.
2. Нажми **LOCATE GAME FILES** и выбери папку `WoW Forever`.
3. Приложение проверит наличие `.build.info`, `Data/` и `WowB-ARM64.exe`.
4. Нажми **PLAY WORLD OF WARCRAFT**.

При первом запуске приложение распакует Proton, DXVK и Turnip-драйвер, скопирует `version.dll` рядом с `WowB-ARM64.exe` и создаст Wine-контейнер. Это займёт 1–2 минуты.

---

## Регистрация аккаунта

Аккаунт создаётся **через Discord-бота** сервера Classic Forever.

1. Присоединись к Discord-серверу: [https://discord.gg/WXjqX85AE](https://discord.gg/WXjqX85AE)
2. Перейди в канал с ботом: [https://discord.com/channels/1553031052669493359/1553044302291009576](https://discord.com/channels/1553031052669493359/1553044302291009576)
3. Следуй инструкциям бота, чтобы создать аккаунт.
4. Логин и пароль от аккаунта вводи **в клиенте игры** при первом входе (не в приложении Classic Forever — оно не хранит учётные данные).

---

## Как это работает

1. Приложение создаёт Wine-контейнер с Proton 11 ARM64EC, DXVK и Turnip.
2. Копирует `version.dll` (нативную ARM64-библиотеку) рядом с `WowB-ARM64.exe`.
3. Wine загружает `version.dll` в процесс `WowB.exe` через `WINEDLLOVERRIDES="version=n,b"`.
4. `version.dll` при загрузке запускает фоновый поток, который:
   - Ищет в памяти клиента блок из 12 публичных ключей (40 байт на запись).
   - Проверяет структуру блока.
   - Заменяет ключ группы 8 на публичный ключ сервера Classic Forever.
   - Следит за памятью и перепатчивает, если клиент восстановит оригинал.
5. Клиент подключается к `auth.gpon.com.co` вместо серверов Blizzard.

Всё делается **внутри процесса клиента**, без root, без ptrace, без внешних скриптов.

---

## Отличия от оригинала

По сравнению с [jaredgei/wow-forever-android](https://github.com/jaredgei/wow-forever-android):

- **Удалён** `WowClientDownloader.kt` — приложение больше не качает клиент с Blizzard CDN.
- **Удалён** `BattleNetSignIn.kt` — приложение не хранит и не использует учётные данные Battle.net.
- **Удалён** `AppUpdater.kt` — обновления с GitHub отключены.
- **Добавлена** нативная `version.dll` для патча памяти через Wine.
- **Добавлена** AMOLED-тема (чёрный фон, белый неон).
- **Изменён** `portal` на `auth.gpon.com.co`.
- **Изменён** `execArgs` на `-d3d11 -config AndroidConfig.wtf`.

---

## Сборка из исходников

### Требования

- **JDK 21** ([Adoptium](https://adoptium.net/) или Microsoft OpenJDK).
- **Android SDK** с платформой API 36 и NDK 27.3.13750724.
- **MinGW-w64** с ARM64-таргетом для сборки `version.dll`.

### Настройка `local.properties`

Создай в корне проекта файл `local.properties`:

```properties
sdk.dir=C\:\\Users\\<ТВОЙ_ЛОГИН>\\AppData\\Local\\Android\\Sdk
```

### Сборка APK

```powershell
.\gradlew assembleModernDebug
```

APK появится в:

```
app\build\outputs\apk\modern\debug\app-modern-debug.apk
```

### Сборка `version.dll`

```bash
/opt/aarch64-w64-mingw32/bin/clang --target=aarch64-w64-mingw32 \
  --ld-path=/mingw64/bin/ld.lld.exe \
  -shared -o version.dll version.c -O2
```

Скомпилированную `version.dll` положи в:

```
app/src/main/assets/bundled_components/version.dll
```

---

## Известные ограничения

- Работает **только на Adreno 7xx/8xx**. На Mali и старых Adreno не тестировалось.
- Требуется **нативный ARM64-клиент** (`WowB-ARM64.exe`). x86_64-версия не подойдёт.
- Патч памяти может **не сработать** при обновлении клиента Blizzard — в этом случае нужно обновить ключи в `version.c`.
- Приложение не обновляется автоматически — обновления нужно ставить вручную.

---

## Лицензия

Форк [jaredgei/wow-forever-android](https://github.com/jaredgei/wow-forever-android). Соблюдай лицензию оригинала.

Клиент World of Warcraft и его ассеты — собственность **Blizzard Entertainment**. Проект не распространяет клиент и не связан с Blizzard.

---

## Благодарности

- [jaredgei](https://github.com/jaredgei) — оригинальный проект `wow-forever-android`.
- [Winlator](https://github.com/brunodev85/winlator) — Wine + Box64 + DXVK для Android.
- [Turnip](https://gitlab.freedesktop.org/mesa/mesa) — открытый Vulkan-драйвер для Adreno.
- Сообществу Classic Forever — за сервер и поддержку.
```


