
import java.time.LocalDate; /* Hier werdem die Klassen LocalDate und LocalTime aus dem Java.time packet importiert
                            *  Dadurch wird später bei den Datums und Zeit Eingabe gewährleistet dass alles richtig
                            *  formatiert ist*/
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Veranstaltung {
    private String name;
    private String dresscode;
    private String typ;
    private String ort;
    private LocalTime uhrzeit;
    private LocalDate datum;

 // Hier wird der Konstruktor initialisiert - wenn Veranstaltungsobjekt erstellt wird, werden folgende Attribute mitgegeben:

    public Veranstaltung(String name, String dresscode,String typ,String ort,LocalDate datum, LocalTime uhrzeit) {
        this.name = name;
        this.dresscode = dresscode;
        this.typ = typ;
        this.ort = ort;
        this.uhrzeit = uhrzeit;
        this.datum = datum;
    }

   // getter Methoden um auf die Werte zugreifen zu können, da Modifikator private

    public String getName () {
        return name;
    }

    public String getDresscode () {
        return dresscode;
    }

    public String getTyp() {
        return typ;
    }
    public String getOrt() {
        return ort;
    }

    public LocalTime getUhrzeit() {
        return uhrzeit;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public String toString () {
       return "Veranstaltungsname:" + name + "\n" + "Dresscode:" + dresscode + "\n" + "Typ:" + typ + "\n" +
      "Ort:" + ort + "\n" + "Datum:" + datum.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "\n" +
      "Uhrzeit:" + uhrzeit.format(DateTimeFormatter.ofPattern("HH:mm"));
    }


}