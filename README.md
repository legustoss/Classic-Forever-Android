**English** | [Русский](https://github.com/legustoss/Classic-Forever-Android/blob/main/README.ru.md)

---

# Classic Forever Android

Android port of **WoW Forever Beta (ARM64 Native)** for Snapdragon devices (AYN Thor, Odin, Retroid, etc.).

This is a fork of [jaredgei/wow-forever-android](https://github.com/jaredgei/wow-forever-android) with reworked logic: the app no longer uses Battle.net or the Blizzard CDN, and connects to the **Classic Forever private server**.

---

## What is this

**Classic Forever Android** is an Android wrapper around Wine + DXVK + Box64 + Turnip that runs the native ARM64 client of World of Warcraft: Classic Beta and connects it to a private server by patching the public key in the process memory.

The app:

- Launches `WowB-ARM64.exe` via Wine (Proton 11 ARM64EC).
- Uses the Turnip driver for Adreno 7xx/8xx.
- Patches the client memory through a native `version.dll`, replacing the group 8 key with the Classic Forever server's public key.
- Writes `SET portal "auth.gpon.com.co"` into `WTF/AndroidConfig.wtf`.

**Does not use:** Battle.net, Blizzard CDN, official Blizzard servers.

---

## Requirements

- Android device with a **Snapdragon 8-series** chip (Adreno 740 or Adreno 8xx).
- At least **8 GB RAM** (12+ recommended).
- **~30 GB** free storage (client + Wine environment).
- Android **11+** (Android 13+ recommended).
- **"All files access"** permission (Manage external storage).

Tested on **AYN Thor** (Snapdragon 8 Gen 2).

---

## Installation

### 1. Download the APK

Build the APK from source or download a prebuilt release (if available in Releases).

### 2. Install the APK

Allow installation from unknown sources if Android asks.

### 3. Grant file access

On first launch the app will ask for **"All files access"**. Without it, it cannot read or patch the game files.

### 4. Download the game client

The client is downloaded **on a PC** through the official Battle.net launcher.

1. Install [Battle.net](https://www.blizzard.com/download/) on a Windows PC.
2. Press **Win + R**, paste the command and hit Enter:

"C:\Program Files (x86)\Battle.net\Battle.net.exe" --game=wow_classic_beta --install
text


3. The launcher will start downloading the **Classic Beta** client. Wait for it to finish.
4. The client will be in the Battle.net install folder (usually `C:\Program Files (x86)\World of Warcraft\_classic_beta_\` or next to the launcher).
5. Copy the `_classic_beta_` folder and the `.build.info` file to your Android device, e.g. to `/storage/emulated/0/WoW Forever/`.

**The folder structure on the device must be:**

/storage/emulated/0/WoW Forever/
├── .build.info
├── classic_beta/
│ └── WowB-ARM64.exe
└── Data/
├── ...
text


### 5. Launch the app

1. Open **Classic Forever**.
2. Tap **LOCATE GAME FILES** and pick the `WoW Forever` folder.
3. The app will verify `.build.info`, `Data/`, and `WowB-ARM64.exe`.
4. Tap **PLAY WORLD OF WARCRAFT**.

On first launch the app will unpack Proton, DXVK, and the Turnip driver, copy `version.dll` next to `WowB-ARM64.exe`, and create a Wine container. This takes 1–2 minutes.

---

## Account registration

Accounts are created **through the Discord bot** of the Classic Forever server.

1. Join the Discord server: [https://discord.gg/WXjqX85AE](https://discord.gg/WXjqX85AE)
2. Go to the bot channel: [https://discord.com/channels/1553031052669493359/1553044302291009576](https://discord.com/channels/1553031052669493359/1553044302291009576)
3. Follow the bot instructions to create an account.
4. Enter the login and password **in the game client** on first login (not in the Classic Forever app — it does not store credentials).

---

## How it works

1. The app creates a Wine container with Proton 11 ARM64EC, DXVK, and Turnip.
2. It copies `version.dll` (a native ARM64 library) next to `WowB-ARM64.exe`.
3. Wine loads `version.dll` into the `WowB.exe` process via `WINEDLLOVERRIDES="version=n,b"`.
4. On load, `version.dll` starts a background thread that:
   - Scans the client memory for a block of 12 public keys (40 bytes per entry).
   - Validates the block structure.
   - Replaces the group 8 key with the Classic Forever server's public key.
   - Watches the memory and re-patches if the client restores the original.
5. The client connects to `auth.gpon.com.co` instead of Blizzard servers.

Everything is done **inside the client process** — no root, no ptrace, no external scripts.

---

## Differences from the original

Compared to [jaredgei/wow-forever-android](https://github.com/jaredgei/wow-forever-android):

- **Removed** `WowClientDownloader.kt` — the app no longer downloads the client from the Blizzard CDN.
- **Removed** `BattleNetSignIn.kt` — the app does not store or use Battle.net credentials.
- **Removed** `AppUpdater.kt` — GitHub updates are disabled.
- **Added** a native `version.dll` for memory patching via Wine.
- **Added** an AMOLED theme (black background, white neon).
- **Changed** `portal` to `auth.gpon.com.co`.
- **Changed** `execArgs` to `-d3d11 -config AndroidConfig.wtf`.

---

## Building from source

### Requirements

- **JDK 21** ([Adoptium](https://adoptium.net/) or Microsoft OpenJDK).
- **Android SDK** with API 36 platform and NDK 27.3.13750724.
- **MinGW-w64** with an ARM64 target for building `version.dll`.

### Setting up `local.properties`

Create a `local.properties` file in the project root:

```properties
sdk.dir=C\:\\Users\\<YOUR_USERNAME>\\AppData\\Local\\Android\\Sdk
```

### Building the APK

```powershell
.\gradlew assembleModernDebug
```

The APK will be at:

```
app\build\outputs\apk\modern\debug\app-modern-debug.apk
```

### Building `version.dll`

```bash
/opt/aarch64-w64-mingw32/bin/clang --target=aarch64-w64-mingw32 \
  --ld-path=/mingw64/bin/ld.lld.exe \
  -shared -o version.dll version.c -O2
```

Place the compiled `version.dll` into:

```
app/src/main/assets/bundled_components/version.dll
```

---

## Known limitations

- Works **only on Adreno 7xx/8xx**. Not tested on Mali or older Adreno.
- Requires a **native ARM64 client** (`WowB-ARM64.exe`). The x86_64 version will not work.
- The memory patch may **fail** when Blizzard updates the client — in that case, update the keys in `version.c`.
- The app does not auto-update — new versions must be installed manually.

---

## License

Fork of [jaredgei/wow-forever-android](https://github.com/jaredgei/wow-forever-android). Follow the original project's license.

The World of Warcraft client and its assets are property of **Blizzard Entertainment**. This project does not distribute the client and is not affiliated with Blizzard.

---

## Credits

- [jaredgei](https://github.com/jaredgei) — original `wow-forever-android` project.
- [Winlator](https://github.com/brunodev85/winlator) — Wine + Box64 + DXVK for Android.
- [Turnip](https://gitlab.freedesktop.org/mesa/mesa) — open-source Vulkan driver for Adreno.
- The Classic Forever community — for the server and support.
