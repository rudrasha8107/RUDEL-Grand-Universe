# RUDEL — 43 locked decisions and implementation traceability

**Project:** RUDEL · Your Friends. Your Stories. Your Universe.
**Authority:** The 43 decisions finalized in the conversation, plus one subsequent instruction: **entire application defaults to English; users can select English, Hindi, Marathi, Bengali, or Tamil in Settings and localized UI and supported audio must follow the choice**.
**Implementation authorization:** `READY` was explicitly given on 22 November 2026.
**Current build:** RUDEL Universe V2, 38-screen local-first app + debug-signed experimental Android APK, **not** released public beta. This file is the definitive bridge between the complete intended scope and the code implemented so far.

Status legend: **WORKING LOCALLY** = testable feature within this build; **PARTIAL** = meaningful first step but missing the full chosen function; **PLANNED** = not implemented; **RELEASE GATE** = must be done before a public release.

| # | Final selection / requirement | Current status | Remaining work |
|---|---|---|---|
| 1 | Original interactive Universe Home | WORKING LOCALLY | Live activity and realtime cards |
| 2 | Living Orbit, morphing connected worlds | PARTIAL | Smooth cross-screen animations and measured performance |
| 3 | Original cinematic intro, 4–6s initial, later short, skip/reduced motion | PARTIAL | 4.2-second first-use intro + skip exists; later 1–2s intro not yet implemented |
| 4 | Email, phone OTP, Google sign-in; guest Demo Explore | PARTIAL | Guest local preview exists; secure accounts/Google/OTP need backend; SMS may cost |
| 5 | Chats with photos/video/voice/GIF/replies/reactions/edit/delete, profanity checks on typing & sending, report/block | PARTIAL | Notes-to-Self and per-friend offline drafts; no sending, real filtering, report queue or chat delivery |
| 6 | Multiplayer games with room codes, timers, reconnection, Rudra branding | PARTIAL | Fully local games, turn timers, small Rudra credit; realtime rooms and server validation pending |
| 7 | 20 full original stories, illustrated multi-chapter, choices, narration, page bookmarks and languages | PARTIAL | Original complete eight-chapter short story in all five written languages; choices/clues/two endings; full illustrations, 19 more stories, accurate page bookmarks and quality-reviewed voice narration pending |
| 8 | YouTube official integration, camera/video editing, voice notes and sound | PARTIAL | Photo import/camera input where supported, voice recording via MediaRecorder, original synthesized sound; no licensed player yet |
| 9 | Animated profile/avatar, Memory Vault, mood/achievements, birthday & privacy | PARTIAL | Avatar Forge with avatar choices, glow and bio; private memories, mood journal and achievements; public birthday sharing pending |
| 10 | Smart notifications, friend alerts opt-in, quiet time, moderation and appeals | PARTIAL | Quiet Mode settings and local capsule notifications; push, online presence, safety operations pending |
| 11 | Android & Web first, iPhone later; phone-only ₹0 budget; managed backend | PARTIAL | Responsive offline-first PWA plus experimental debug-signed Android WebView APK; backend comparison still pending |
| 12 | RUDEL original brand, cosmic/royal/vibrant palette, unique emblem | PARTIAL | New original cosmic icon, three themed palettes; trademark review still required |
| 13 | Floating dock, expandable navigation, **section-wise** search; light/dark/reduced motion | PARTIAL | Dock and drawer work; friends/posts section searches; light + three themes; other section searches pending |
| 14 | Private circles and moderated communities; creative posts and social events | PARTIAL | On-device posts, pictures, audiences as metadata, local circles; no network publishing/events/community creation |
| 15 | Friendship Galaxy, Together Studio, Surprise Capsules, creative Quests with cosmetics | PARTIAL | Local Galaxy, drawing studio, scheduled-on-open capsules; realtime collaboration and quests pending |
| 16 | RUDEL **Social Beta** scope and genuine two multiplayer games | PLANNED | This version is an on-device prototype, not the Social Beta |
| 17 | First games Tic Tac Toe (computer/online), Snakes & Ladders (local/online), timer and room codes | PARTIAL | Tic Tac Toe with computer/local, Snakes & Ladders local, 10/15/30s timer; online rooms pending |
| 18 | Chat voice, indicators, receipts, message editing and granular presence/friend controls | PARTIAL | Local self notes and offline unsent drafts; secure realtime chat and permissions need backend |
| 19 | Device management, login alerts, optional 2FA, backup/export/delete/privacy dashboard | PARTIAL | Local data JSON export and local deletion; voice records excluded from JSON and individually downloadable; server controls not active |
| 20 | Universe Home + personal summary; Foundation → Features → Design → Testing; tutorial | PARTIAL | Home summary and 38 navigable screen routes; skippable first intro; interactive tutorial pending |
| 21 | Adaptive motion, offline games/stories/memories/message drafts, data saver | PARTIAL | Local solo games, story, memories, drafts, data saver, SW offline cache; eviction and device compatibility still need testing |
| 22 | User + 3–5 friends testing, screenshot bug reports, strict quality gates | RELEASE GATE | Automated smoke tested; user/device tests and bug reports pending |
| 23 | UI languages Hindi/English/Marathi/Bengali/Tamil, screen readers, accessible controls | PARTIAL | Default English, five language dictionary + story text, accessible labels/reduced motion; human translation QA and accessibility audit pending |
| 24 | PWA first → Android APK; Firebase vs Supabase decision after comparison | PARTIAL | PWA assets exist, experimental Android APK now built/debug-signed (installation not device-tested); backend not chosen |
| 25 | Cosmic Aurora / Royal Nebula / Vibrant Galaxy themes, hybrid icons, adaptive motion | WORKING LOCALLY/PARTIAL | All themes togglable; icon system and animation polish ongoing |
| 26 | New original logo, Stars+Orbit+Light intro, theme-adaptive icon, optional intro audio | PARTIAL | Original glossy cosmic icon and intro; device silent-mode/autoplay-safe sound integration needs testing |
| 27 | Photo or custom avatar, interactive friend constellation, privacy-only-me default | PARTIAL | Emoji avatar and locally added friend stars; avatar photo, full galaxy customization and consented shares pending |
| 28 | Creative posts + videos + templates; advanced circles; approved moderated public communities; feed filters | PARTIAL | Local text/photo posts and circles; public/social/video/template functions need backend and moderation |
| 29 | In-app center, optional push, opt-in friend alerts, quiet hours, concealed notification content | PARTIAL | Local capsule center and manual quiet mode; device push, schedule, locks-screen protections pending |
| 30 | Friend search, invite codes, optional contacts, interest-based discovery, restricted minor defaults | PARTIAL | Search in local friend list, search in local posts; real discovery not implemented |
| 31 | Automatic + risk-based human moderation, Team-approved public communities, appeals | PLANNED / RELEASE GATE | Do not activate public communities without moderation staffing and policies |
| 32 | Email OR Google OR Mobile OTP verification, rate limiting, suspicious activity, recovery | PLANNED / RELEASE GATE | Authentication provider, password resets, secure RLS/authorization, quotas, anti-abuse |
| 33 | Compressed photos/videos/voice and quotas, selective cloud backup, export/deletion flow | PARTIAL | JPEG resize/compress and local JSON export; cloud quota, video processing, backup and server deletion missing |
| 34 | Authoritative multiplayer, grace period, replay, friend leaderboards, matchmaking | PLANNED | All online competitive game systems require realtime trusted backend |
| 35 | Dynamic game arena, cosmetic rewards/quests, rematch, spectators and vote-selected games | PARTIAL | Multiple polished local games incl. Connect Four, 15-puzzle, 2048, RPS, Reaction, Memory; cosmetic achievements/quests, live matching and spectators pending |
| 36 | Illustrated stories, cinematic/comic view, branches, clues, character voices, first full 8–10+ chapter story | PARTIAL | Eight complete text chapters with choices/clues/two endings; rich illustrations/comic pages and character recordings pending |
| 37 | First genre **Sci-Fi + Cosmic Mystery + Adventure**; five languages together after QA; full offline packs; advanced library | PARTIAL | Original eight-chapter multilingual story, clue journal/ending collection, SW offline; human QA, recommendations, robust pack management pending |
| 38 | Direct camera + gallery/fallback; layers, speed, transitions, presets; animated templates; private drafts/download | PARTIAL | Native Android file picker + gallery, photo adjustment/export, original poster/collage/sticker studio, preview-first video segment tools; full realtime video layers and audience-controlled server publishing pending |
| 39 | Official music embeds/redirects + licensed/original sounds; voice trim/drafts/noise reduction; sound modes; private audio | PARTIAL | Original chime and soundscapes, local browser voice note recorder where allowed, quiet mode; clip editing, real audio backup and legal provider integrations pending |
| 40 | Interactive friend constellations + highlights; shared Memory Vault; realtime Together Studio; consent capsules | PARTIAL | Local friend stars, friendship scrapbook, private memories, drawing canvas, time-locked-on-open capsule; realtime sharing/delivery/permissions backend pending |
| 41 | **Balanced Beta**: core Auth, Orbit Home, Friends/Chat, two online games, Posts/Circles, Galaxy and 1 complete story | PLANNED | Significant backend/security/media work before beta; first build deliberately limited to working local foundation |
| 42 | **RUDEL Signature Universe**: cinematic opening, Living Orbit, premium theme adaptation, readable fast UI | PARTIAL | Refined cinematic styles, theme-adaptive original icon, 38 screens; performance audits/device checks still required |
| 43 | Foundation → Working Features → Premium Design → Testing → Beta; **compare** Firebase/Supabase; **PUBLIC BETA after tests**; **Fast beta with core features** | PARTIAL / RELEASE GATE | Current code is foundation, not public launch. Incompatible core/safety features must not ship before audit/testing. |

## Required distinctions
- **No real account**: Profile here is local device state, not verified login.
- **No delivered message or online friend**: Notes-to-Self work; friend entries are local contact representations, and friend chat is explicitly an **unsent offline draft**.
- **No public feed or online games**: Posts, circles and games run locally only. No web backend is secretly active.
- **Default privacy for minors**: New content is device-only and private by default; audience labels for future sharing do not cause an upload.
- **No reliable push/delivery**: Capsules unlock if/when the app is opened after the selected time. They do not deliver to another user or trigger a server notification.
- **Voice support is device dependent**: Speech synthesis for story narration needs an available voice for the chosen language. Browser camera/microphone permissions vary by OS and secure origin.
- **Not all five translations have had independent human review**: They are authored in five languages but must be proofread before release. Do not claim language QA is done.

## Public launch must not occur without
Real authentication and per-user authorization, confidential and validated user media access, chat and friend permissions, moderated public discovery, anti-abuse controls, data retention/deletion policies, working multiplayer trust boundaries, real-device performance and failure-mode tests, and budget/capacity checks. Fast Beta != unsafe Beta.

## V2 delivery (after the 43 choices)

- **38 navigable UI screens**, including 10 new V2 pages: Avatar Forge, Connect Four, Sliding Puzzle, Word Quest, Universe Trivia, Collage Studio, Video Studio, Friendship Board, Sticker Lab and Personal Task Board.
- **10 playable local game/quiz experiences**, not remote matchmaking.
- **Original app icon in PNG sizes 72–512** and a debug-signed `.apk` with native Android file selection, built with manually generated DEX, binary XML and resource table because SDK was unavailable. APK is unverified on physical Android.
- **Honest completion rule:** A local mock contact or draft is *not* an online friend or a delivered chat. A playable local game is *not* a network multiplayer game. A gallery picker is *not* an advanced native camera API. A working APK archive is *not* a security-audited public release.
- **Five-language rule:** English is default. Core navigation/story have written translations; V2 advanced screens still contain English sublabels, and audio/localization QA remains incomplete. These were not omitted from the Final Product target.
