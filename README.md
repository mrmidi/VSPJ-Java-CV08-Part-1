# VSPJ-Java-CV08-Part-1

1. Vytvořte program, který do binárního souboru zapíše hodnotu proměnné typu int a typu long. Dále pak z vytvořeného binárního souboru načte údaje v pořadí: int a long, long a int. Jaká je velikost souboru na disku? Diskutujte velikost souboru (odůvodněte).

2. Vytvořte třídy IOFileTool s metodou:

a) int pocetznaku_file(string filename) - metoda určí počet znaků uložených v daném souboru. 

b) void copy_file(string filename1, string filename2) - metoda provede zkopírování souboru filename1 do souboru filename2 tak, že odstraní prázdné řádky a mezery na okrajích. 

Pozn: využijte nástrojů třídy String.

4. Serializace objektu

Definujte třídu Datum s položkami den, mesic a rok typu celé číslo neznaménkové. Metoda .tostring zajistí zobrazení data ve formátu "den. mesic. rok".

Definujte třídu Osoba s položkami jméno, příjmení, věk (číselný údaj) a datumnarozeni typu Datum. 

Zajistětě ošetření chybových stavů.

Vytvořte metodu ve třídě IOFileTool 

a) void osoba_serializace(Osoba osoba, string filename) - provede serilizaci objektu do souboru filename.

b) Osoba osoba_deserializace(string filename) - provede deserializaci objektu Osoba ze souboru filename. 

c) void write_datum_filebin(Datum datum, string filename) - zapíše údaje do binárního souboru. 

d) Datum read_datum_filebin(string filename) - načte položky datumu z binárního souboru a vrátí vytvořenou instanci třídy Datum. 
