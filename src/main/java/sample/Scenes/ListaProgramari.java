package sample.Scenes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.JSONArray;
import sample.Users.Doctor;
import sample.Users.Programari;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ListaProgramari {
    public static Scene inregis(Stage primaryStage, Scene scene, String numeUtilizator) {


        TableColumn<Programari, String> nameColumn = new TableColumn<>("Nume doctor");
        nameColumn.setMinWidth(160);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("numeDoctor"));

        TableColumn<Programari, String> oraColumn = new TableColumn<>("Ora");
        oraColumn.setMinWidth(160);
        oraColumn.setCellValueFactory(new PropertyValueFactory<>("ora"));

        TableColumn<Programari, String> ziColumn = new TableColumn<>("Zi");
        ziColumn.setMinWidth(220);
        ziColumn.setCellValueFactory(new PropertyValueFactory<>("zi"));

        TableColumn<Programari, String> lunaColumn = new TableColumn<>("Luna");
        lunaColumn.setMinWidth(260);
        lunaColumn.setCellValueFactory(new PropertyValueFactory<>("luna"));

        TableColumn<Programari, String> detaliuColumn = new TableColumn<>("ALte Detalii");
        detaliuColumn.setMinWidth(260);
        detaliuColumn.setCellValueFactory(new PropertyValueFactory<>("alte_detalii"));

        TableColumn<Programari, String> mesajColumn = new TableColumn<>("Mesajul Doctorului");
        mesajColumn.setMinWidth(360);
        mesajColumn.setCellValueFactory(new PropertyValueFactory<>("mesaj_doctor"));

        TableColumn<Programari, String> statusColumn = new TableColumn<>("Statusul Programării");
        statusColumn.setMinWidth(260);
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableView<Programari> table = new TableView<>();
        table.setItems(getProgramari(numeUtilizator));
        //table.setItems(getDoctor());
        table.getColumns().addAll(nameColumn, oraColumn, ziColumn, lunaColumn, detaliuColumn, mesajColumn, statusColumn);

        Button inapoi = new Button("Înapoi");
        inapoi.setOnAction(e -> {
            Scene scene2 = SignInClient.inregis(primaryStage, scene, numeUtilizator);
            primaryStage.setScene(scene2);
        });

        Label l1 = new Label("                                                                               ");
        Label l2 = new Label(" ");

        HBox hBox = new HBox();
        hBox.getChildren().addAll(l1, inapoi, l2);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, hBox);

        Scene scene2 = new Scene(vBox, 750, 400);

        return scene2;
    }

    public static ObservableList<Programari> getProgramari(String numeUtilizator) {
        ObservableList<Programari> programari = FXCollections.observableArrayList();
        String first = "src\\main\\resources\\Programari.json";
        String contents = null;
        try {
            contents = new String((Files.readAllBytes(Paths.get(first))));

            JSONArray Lista = new JSONArray(contents);
            for (int i = 0; i < Lista.length(); i++) {
                if (Lista.getJSONObject(i).getString("nume_de_utilizator").equals(numeUtilizator)) {
                    String a = Lista.getJSONObject(i).getString("nume_doctor");
                    String b = Lista.getJSONObject(i).getString("ora");
                    String c = Lista.getJSONObject(i).getString("ziua");
                    String d = Lista.getJSONObject(i).getString("luna");
                    String e = Lista.getJSONObject(i).getString("alte_detalii");
                    String f = Lista.getJSONObject(i).getString("mesaj_doctor");
                    String g = Lista.getJSONObject(i).getString("status");
                    programari.add(new Programari(a, b, c, d, e, f, g));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return programari;
    }
}
