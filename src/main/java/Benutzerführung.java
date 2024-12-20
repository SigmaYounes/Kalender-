import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;

public class Benutzerführung {
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Veranstaltung> veranstaltungen = new ArrayList<>();
    private static final String DATEINAME = "C:\\Users\\Younes\\IdeaProjects\\KalenderGson\\src\\main\\resources\\veranstaltungen.json";
    public static final JsonSerializer<LocalDate> localDateSerializer = (src, typeOfSrc, context) ->
            context.serialize(src.toString());

    public static final JsonDeserializer<LocalDate> localDateDeserializer = (json, typeOfT, context) ->
            LocalDate.parse(json.getAsString());

    public static final JsonSerializer<LocalTime> localTimeSerializer = (src, typeOfSrc, context) ->
            context.serialize(src.toString());

    public static final JsonDeserializer<LocalTime> localTimeDeserializer = (json, typeOfT, context) ->
            LocalTime.parse(json.getAsString());




    public void start() {
        while (true) {
            System.out.println("Benutzerführung");
            System.out.println("1. Neue Veranstaltung hinzufügen");
            System.out.println("2. Veranstaltungsübersicht anzeigen");
            System.out.println("3. Programm beenden");
            System.out.print("Wählen Sie eine Option: ");

            String auswahl = scanner.nextLine();

            if (auswahl.equals("1")) {
                veranstaltungHinzufuegen();
            } else if (auswahl.equals("2")) {
                veranstaltungsübersicht();
            } else if (auswahl.equals("3")) {
                System.out.println("Programm beendet.");
                return;
            } else {
                System.out.println("Ungültige Auswahl. Bitte erneut versuchen.");
            }
        }
    }

    private void veranstaltungHinzufuegen() {
        try {
            System.out.print("Geben Sie den Namen der Veranstaltung an: ");
            String name = scanner.nextLine();

            System.out.print("Gibt es einen Dresscode für diese Veranstaltung: ");
            String dresscode = scanner.nextLine();

            System.out.print("Geben Sie den Typ dieser Veranstaltung an: ");
            String typ = scanner.nextLine();

            System.out.print("Geben Sie den Ort an: ");
            String ort = scanner.nextLine();

            System.out.print("Geben Sie die Uhrzeit an (HH:mm): ");
            LocalTime uhrzeit = LocalTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("HH:mm"));

            System.out.print("Geben Sie das Datum an (dd.MM.yyyy): ");
            LocalDate datum = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));

            Veranstaltung veranstaltung = new Veranstaltung(name, dresscode, typ, ort, datum, uhrzeit);
            veranstaltungen.add(veranstaltung);

            System.out.print("Möchten Sie diese Veranstaltung speichern? (Ja/Nein): ");
            String eingabe = scanner.nextLine();
            if (eingabe.equalsIgnoreCase("Ja")) {
                speichernVeranstaltungen();
                System.out.println("Die Veranstaltung wurde gespeichert.");
            } else {
                System.out.println("Die Veranstaltung wurde nicht gespeichert.");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Ungültiges Datum- oder Zeitformat. Bitte verwenden Sie das Format dd.MM.yyyy für das Datum und HH:mm für die Uhrzeit.");
        } catch (Exception e) {
            System.out.println("Fehler beim Hinzufügen der Veranstaltung: " + e.getMessage());
        }
    }

    private void veranstaltungsübersicht() {
        if (veranstaltungen.isEmpty()) {
            System.out.println("Es wurden noch keine Veranstaltungen hinzugefügt.");
        } else {
            System.out.println("Veranstaltungsübersicht:");
            for (Veranstaltung v : veranstaltungen) {
                System.out.println(v);
                System.out.println(); // Leerzeile zwischen Veranstaltungen
            }
        }
    }

    private void speichernVeranstaltungen() {
        try (FileWriter writer = new FileWriter(DATEINAME)) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .excludeFieldsWithoutExposeAnnotation()
                    .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (src, typeOfSrc, context) ->
                            context.serialize(src.toString()))
                    .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, typeOfT, context) ->
                            LocalDate.parse(json.getAsString()))
                    .registerTypeAdapter(LocalTime.class, (JsonSerializer<LocalTime>) (src, typeOfSrc, context) ->
                            context.serialize(src.toString()))
                    .registerTypeAdapter(LocalTime.class, (JsonDeserializer<LocalTime>) (json, typeOfT, context) ->
                            LocalTime.parse(json.getAsString()))
                    .create();

            gson.toJson(veranstaltungen, writer);
        } catch (IOException e) {
            System.out.println("Fehler beim Speichern der Veranstaltungen: " + e.getMessage());
        }
    }
}
