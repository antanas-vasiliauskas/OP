# Šachmatų žaidimas

## Paskritis
Šachmatai yra strateginis žaidimas dviems žaidėjams, kuriame reikia stengtis įveikti priešininką. Žaidžiama su 32 figūromis ant 64 laukelių lentos, tikslas yra nukirsti priešininko karalių.

## Paleidimas
Paleisti programą vardu `ChessGame.jar`

## Funkcionalumas
- Žaidimas žaidžiamas prieš kompiuterinę programą.
- Galima išsaugoti/užkrauti žaidimą.
- Paspaudus ant figūros, rodomi langeliai, į kuriuos ji gali eiti.
- Galimi visi ėjimai pagal tradicinės šachmatų taisykles.

 ## Pagrindinės klasės
  - `ChessGameGUI` - programos main klasė, kuri atsakinga už grafinę vartotojo sąsają.
  - `GameState` - klasė, sauganti žaidimo būseną.
  - `Piece` - Tėvinė klasė visoms figūrų klasėms, apibrėžianti figūros funkcionalumą.
 
 ## Plėtimo galimybės
  - Žaidimo pradžios/pabaigos ekranas.
  - Figūrų/lentos stiliaus keitimo galimybės.
  - Galimybė žaisti prieš kitą žaidėją.
  - Galimybė analizuoti partijas.

  ## Projektavimo šablonai
  - `PieceFactory` - gamyklos šablonas.
  - `Piece` - strategijos šablonas.


