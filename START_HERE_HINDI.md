# RUDEL GRAND — फोन पर कैसे खोलें

**यह अभी एक पूरी Public Social Network APK नहीं है।** इसमें असली 25 स्थानीय Game/Quiz Experiences, नई Comics/Novel, Rudra Offline Assistant और पहले वाले RUDEL Tools काम करते हैं। असली Online Accounts/Chats/Multiplayer/Hosting अब भी नहीं है। पुरानी Crash वाली Experimental APK न बाँटें।

**एक आसान नया विकल्प:** `RUDEL_GRAND_All_In_One.html` डाउनलोड करें। इसमें मुख्य RUDEL, 15 नए गेम, Comics और Rudra Guide एक ही फ़ाइल में हैं। अलग-अलग JavaScript/CSS फाइलें नहीं निकालनी पड़ेंगी। Android Browser यदि स्थानीय HTML नहीं खोलता तो इसे HTTPS पर Host करना होगा। यह APK नहीं है।

## सीधे फोन पर इस्तेमाल

1. `RUDEL_GRAND_Web_Files.zip` डाउनलोड करें। उसे अपने Android के Files ऐप से Extract करें।
2. `index.html` खोलें; सुनिश्चित करें कि साथ में JS, CSS, assets और बाकी HTML Pages उसी Folder में हैं। कुछ Chrome/Android Builds स्थानीय HTML खोलने से रोक सकते हैं। ऐसे में HTTPS Hosting आवश्यक है।
3. Games के लिए `playzone.html`; Comics के लिए `comicbook.html`; Rudra Offline Guide के लिए `rudra.html` खोलें।
4. **एक ही File चाहें तो** `RUDEL_15_Games_Standalone.html` डाउनलोड करें: केवल 15 नए गेम एक HTML फाइल में चलते हैं, अन्य RUDEL Features इसमें नहीं हैं।

## दोस्तों को एक Public Link देना

Static Files को अपने GitHub Pages, Cloudflare Pages, Netlify या किसी समान HTTPS Host पर Publish करें; `index.html` का HTTPS URL भेजें। Browser से PWA Install संभव हो सकता है। RUDEL की सभी Local Features हर व्यक्ति के फोन पर **अलग-अलग**, निजी रूप से काम करेंगी; Users एक-दूसरे को Online Messages नहीं भेज पाएँगे।

## असली Android APK कैसे बनाएँ

`RUDEL_GRAND_Source.zip` में `android-native/` नाम का Standard Gradle Java Project और `.github/workflows/android-build.yml` है। Android Studio/Android SDK 35 या GitHub Actions से वास्तविक `.apk` Build करना होगा। इस Chat के वातावरण में SDK/Emulator नहीं है, इसलिए **इस पैकेज में कोई अप्रमाणित APK नहीं दिया गया है**। Full Guidance `docs/BUILD_INSTALL_LAUNCH.md` में है।

## क्या वर्तमान में नहीं चलता?

Real Google/Email Login, Public Social Feed, Instagram जैसी Real Reels Distribution, Chat Delivery, Real Online Multiplayer, Cloud AI, Server Backups, Human Moderation, Auto Notifications। इन Features को चालू बताए बिना सही Backend, Privacy और Real-Device Testing के बाद ही Launch किया जा सकता है।
