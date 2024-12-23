/* Annotation aus der Gson-Bibliothek, um anzugeben,
*  dass bestimmte Attribute für die JSON-Konvertierung berücksichtigt werden sollen.
*/
import com.google.gson.annotations.Expose;

// Folgende drei Pakete sind für Zeit- und Datumsformatierung zuständig
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Veranstaltung {

    // "@Expose"-Annotation --> nur dieses Attribute bei Serialisierung (in JSON) und Deserialisierung (aus JSON) durch Gson berücksichtigt
    @Expose
    private String name;

    @Expose
    private String dresscode;

    @Expose
    private String typ;

    @Expose
    private String ort;

    @Expose
    private LocalTime uhrzeit;

    @Expose
    private LocalDate datum;

    // Hier wird der Konstruktor initialisiert - wenn Veranstaltungsobjekt erstellt wird, werden folgende Attribute mitgegeben:
    public Veranstaltung(String name, String dresscode, String typ, String ort, LocalDate datum, LocalTime uhrzeit) {
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

    // toString Methode, um Objekt in lesbare Textdarstellung auszugeben
    public String toString () {
       return "Veranstaltungsname:" + name + "\n" + "Dresscode:" + dresscode + "\n" + "Typ:" + typ + "\n" +
      "Ort:" + ort + "\n" + "Datum:" + datum.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "\n" +
      "Uhrzeit:" + uhrzeit.format(DateTimeFormatter.ofPattern("HH:mm"));
    }


}