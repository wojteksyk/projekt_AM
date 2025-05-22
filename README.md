
Dokumentacja(lepiej sie wyswietla  w notatniku):
1. Opis aplikacji:
Aplikacja OrderTracker jest stworzona przy użyciu javy. Aplikacja umożliwia użytkownikowi na sprawdzanie statusu swoich zamówień. 
Zakładam, że zamówienia są z lososwych sklepów liczą się tylko numery zamowienia, ponieważ OrderTRacker wykorzystuje # zamówienia do trackowania ich.
Apka umożliwia na sprawdzenie statusu zamówienia (od 1-24) z bazy danych. Możliwe są 3 opcje statusu(dostarczone, w drodze, oczekujące).
W przypadku opcji drugiej i trzeciej po kliknięciu w button "szczegóły" przekierowywuje nas do szczegółów danego zamówienia, gdzie możemy znależć kod QR z 
zewnętrznego API. Kod jest generowany po numerze zamówienia więc każdy jest inny(np. zamówienie 1- content w QR: 1, zamówienie 13- content w QR: 13). W tym widoku
również jest button: "jak działa nasz kod QR", przenosi on do widoku z wyjaśnieniem jak działa technologia QR i po co ona jest w apce.
Jeśli status to dostarczone to w szczegółach jest raitings bar, który służy do oceny doznań w apce zamiast kodu QR, który służy do odbioru zamówienia.
W apce też możliwość kontaktu(button kontakt)- przekierowywuje do form z możliwościa wyslania wiadomości.
PS. Polecam sprawdzić również zamówienie 2137 i jego kod QR w szczegółach.

2. Opis podziału pracy:
Sam zrobiłem całość.

3.Opis + diagram bazy danych:
┌─────────────┐
│   orders    │
├─────────────┤ 
│ order_number│
│ status      │
│ details     │
└─────────────┘
Baza ma jedną tabele która posiada numer zamówienia, status i mały opis co się dzieje z przesyłką.

4.

0. SplashActivity Ekran startowy / logo
        1. MainActivity (Lista zamówień)
            Wybranie zamówienia →
               → 2. OrderDetailsActivity (Szczegóły zamówienia)
                    Jeśli status ≠ "Dostarczone"
                          Pokaż QR kod
                          Przycisk: [Jak działa kod QR?]
                               3. qrExplanation (Ekran z wyjaśnieniem)
                     Jeśli status = "Dostarczone"
                          Pokaż: RatingBar + "Wyślij opinię"
                          Po wysłaniu: Reset oceny + Toast
             Przycisk: Kontakt
                 → 4. ContactActivity Ekran kontaktowy
