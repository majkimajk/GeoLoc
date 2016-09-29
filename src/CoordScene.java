import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by michalbaran on 29/09/16.
 */
public class CoordScene {

    private final String apiKey = "AIzaSyCh1Ps7lceOVSsv4vEWw_aRglCR3t2xj7A";
    private final String baseUrl = "https://maps.googleapis.com/maps/api/geocode/json?";

    private StringBuilder sb = new StringBuilder();
    private String adressUrl = "nic";

    public Scene getScene() {

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // adress label
        Label adressLabel = new Label("Adress");
        GridPane.setConstraints(adressLabel, 0, 0);

        //adress texfield
        TextField adressText = new TextField();
        adressText.setPromptText("Enter the streetname & nr");
        GridPane.setConstraints(adressText, 1, 0);

        // city label
        Label cityLabel = new Label("City");
        GridPane.setConstraints(cityLabel, 0, 1);

        //city texfield
        TextField cityText = new TextField();
        cityText.setPromptText("Enter the city name");
        GridPane.setConstraints(cityText, 1, 1);

        // country label
        Label countryLabel = new Label("Country");
        GridPane.setConstraints(countryLabel, 0, 2);


        //country texfield
        TextField countryText = new TextField();
        countryText.setPromptText("Enter the country name");
        GridPane.setConstraints(countryText, 1, 2);

        Button button = new Button("Get coordinates!");
        GridPane.setConstraints(button, 1, 3);
        button.setOnAction(e -> {
            if (!adressText.getText().isEmpty()) {
                String[] adr = adressText.getText().split(" ");
                for (String s : adr) {
                    sb.append(s).append("+");
                }
                sb.deleteCharAt(sb.length() - 1).append(",");
            }
            if (!cityText.getText().isEmpty()) {
                String[] cit = cityText.getText().split(" ");
                for (String s : cit) {
                    sb.append(s).append("+");
                }
                sb.deleteCharAt(sb.length() - 1).append(",");
            }
            if (!countryText.getText().isEmpty()) {
                String[] cou = countryText.getText().split(" ");
                for (String s : cou) {
                    sb.append(s).append("+");
                }
                sb.deleteCharAt(sb.length() - 1);
            }
            adressUrl = sb.toString();
            getCoordinates(adressUrl);

            });

        grid.getChildren().addAll(adressLabel, adressText, cityLabel, cityText, countryLabel, countryText, button);


        Scene scene = new Scene(grid, 300, 250);
        return scene;
    }

    private void getCoordinates(String adressUrl) {
        System.out.println(adressUrl);
        String url = baseUrl +"address=" + adressUrl + "&key=" + apiKey;

        HttpGet get = new HttpGet(url);

        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            CloseableHttpResponse response = httpClient.execute(get);
            System.out.println(response.getStatusLine().getStatusCode());
            BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer result = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
            System.out.println(result);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
