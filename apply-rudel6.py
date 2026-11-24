from pathlib import Path
p=Path('android-native/app/src/main/assets/index.html'); s=p.read_text(); marker='<!-- RUDEL6 -->'
if marker not in s:
 s=s.replace('</body>',marker+'<script src="universe6.js" defer></script></body>')
 p.write_text(s)
