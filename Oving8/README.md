# Oving 8

## Requirements

### Lister
- Man skal kunne opprette nye lister (som vil typisk være for type lister, som "dagligvarer", "hytteutstyr", "handle på nett" osv)
- Man skal kunne slette eksisterende lister
- Øverst skal man ha en oversikt over listene, f.eks. ved bruk av tabs eller annen måte du finner hensiktsmessig.
- Plassert under oversikten over listene, skal man ha ett input-felt for å kunne legge ny innslag inn i den stående lista. Dette skal bare være en tekststreng.

### Task
- Når setter fokus på feltet, skal keyboardet dukke opp. Man skal kunne avslutte inntasting og legge innslaget i lista ved å trykke på "enter". Keyboardet skal bli stående oppe og fokus skal gå tilbake til tekstfeltet, slik at man kan legge inn flere innslag på rad.
- Under input-feltet skal man ha listeinnslagene. Disse skal være organisert slik at øverst har man ugjorte ("ukjøpte") innslag øverst, mens nederst er innslagene som er markert som ferdig ("kjøpt").
- Man skal kunne markere et innslag ferdig/"kjøpt" ved å tappe (korttrykk) på det. Det skal da flyttes fra uferdig til ferdig ("ukjøpt" til "kjøpt")
- Listene skal lagres til fil på JSON-format, fortrinnsvis ei fil pr. liste, men det er ikke et absolutt krav. Hver gang man legger inn et innslag, skal fila oppdateres.
- Om du rekker det, implementer å ha mulighet til å omorganisere innslag i en liste vha. long press. Dvs. man har long press på et innslag, som skal visuelt indikere at det er registrert etter en liten stund, og når det har skjedd, skal man kunne dra innslaget opp eller ned for å flytte det over/under de andre innslagene. Når man så løfter fingeren vekk, skal innslaget bli liggende hvor enn det er plassert. JSON-fila må oppdateres i henhold til dette.
