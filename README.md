# RUDEL GRAND · Original Offline Universe

**Created for RUDEL · Greetings from Rudra Joshi and Team**

**What this release actually provides:** an upgraded PWA/static web app with the prior 44 client screen areas and their functioning on-device features, 15 new playable games (Snake Classic, Midnight Racer, Turbo Highway, Orbit Hop, Paddle Pong, Neon Breaker, Star Catcher, Pixel Maze, Quick Target, Lights Out, Simon Orbit, Math Sprint, Word Mixer, Minefield Logic, Number Detective), plus the prior local Tic Tac Toe, Snakes & Ladders, Connect Four, Sliding Puzzle, Memory, 2048, Rock Paper Scissors, Reaction Test, Trivia and Word Quest. There are **25 local game / quiz experiences**, not 25 online multiplayer games. 3 original 8-panel illustrated comics, a new 8-chapter original novella, the previous 8-chapter choice story, and Rudra — a real working **offline intent-based help guide**, not a cloud AI model. On-device features include local Moments, Likes, Comments, Saves, Memory Vault, Circle drafts, Avatar Studio, Collage, Photos, Drawing, Stickers, Story bookmarks, Export, Help and FAQ.

**What is NOT implemented:** secure online users and authentication, server-to-server DM delivery, live public social posting, online multiplayer, video streaming, a real generative AI backend, server backups, moderation capacity and a production-signed APK. A previous experimental APK crashed on a real user's phone. No experimental/wrongly signed APK is supplied here, and this source project has not been compiled in Android SDK or installed on a real Android handset during this turn. This is **not a public-ready social network**.

## Single-file edition (easiest to send)

`RUDEL_GRAND_All_In_One.html` is a companion deliverable outside this folder. It embeds the app with the 15 new arcade games, comics, novel and Rudra support into one HTML file; smoke-tested in Chromium. It can be shared as a file, but whether an Android browser accepts locally opened HTML depends on browser security settings. It is NOT an APK and does NOT enable online interaction between users.

## Launch in a browser

Host this entire folder via HTTPS on a static hosting service; the PWA manifest + service-worker will provide offline cached assets after successful loading. For development, open `index.html` with its sibling files in a supported local browser. Direct standalone pages: `playzone.html`, `comicbook.html`, `rudra.html`.

## Android source and cloud build

`android-native/` is the standard Java/Gradle project. Android 12+ (`minSdk 31`), `targetSdk 35`, Java 17 and Android Gradle Plugin 8.7.3. A GitHub Actions workflow is provided at `.github/workflows/android-build.yml` to compile a real Android **debug APK** using Android SDK 35 on GitHub's hosted runners when the repo is uploaded. A production release needs a secure signing key and real device QA. See `docs/BUILD_INSTALL_LAUNCH.md`.

## Interface languages

English default. The original app's core interface supports Hindi, Marathi, Bengali and Tamil for translated strings; the newest Arcade, Books and Rudra sections are English-first, so **full localization is incomplete**. Some Text-to-Speech may be available for English novels subject to browser voices. Full five-language audio and story localization are not complete or verified.

## Privacy & data

User-created content is private on-device localStorage and/or IndexedDB, **not encrypted and not cloud-synced**. Reading progress and game high scores are device-local. Use Export & Backup before clearing or uninstalling. Do not enter passwords or store sensitive personal data in the local edition. No ads, analytics or remote trackers are bundled with these files.

## Tests

Run `python tests/grand-browser.py` where Python Playwright and Chromium are available. The tests exercise navigation, 15 new game modes, comic page-turning, offline Rudra help replies, and existing RUDEL browser functions; this is not physical Android device testing.

## Blueprint

`BLUEPRINT_43_DECISIONS.md` retains all 43 previously finalized product choices. It describes a longer-term ambition, not a claim that every decision has been implemented. The principle is to label current vs planned accurately.
