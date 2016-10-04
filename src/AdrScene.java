import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by michalbaran on 04/10/16.
 */
public class AdrScene {

    private final String apiKey = "AIzaSyCh1Ps7lceOVSsv4vEWw_aRglCR3t2xj7A";
    private final String baseUrl = "https://maps.googleapis.com/maps/api/geocode/json?";

    private StringBuilder sb = new StringBuilder();
    private String adressUrl;
    private String outcome;

    public Scene getScene() {

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // adress label
        Label latLabel = new Label("Lattitude");
        GridPane.setConstraints(latLabel, 0, 0);

        //adress texfield
        TextField latText = new TextField();
        latText.setPromptText("Enter the lattitude");
        GridPane.setConstraints(latText, 1, 0);

        // city label
        Label longLabel = new Label("City");
        GridPane.setConstraints(longLabel, 0, 1);

        //city texfield
        TextField longText = new TextField();
        longText.setPromptText("Enter the city name");
        GridPane.setConstraints(longText, 1, 1);


        Button button = new Button("Get the adress!");
        GridPane.setConstraints(button, 1, 3);
        button.setOnAction(e -> {
            if (!latText.getText().isEmpty()) {
                String[] adr = latText.getText().split(" ");
                for (String s : adr) {
                    sb.append(s).append(",");
                }
            }
            if (!longText.getText().isEmpty()) {
                String[] cit = longText.getText().split(" ");
                for (String s : cit) {
                    sb.append(s);
                }
            }

            adressUrl = sb.toString();
            String adress = getAdress(adressUrl);
            Stage stage3 = new Stage();
            stage3.setTitle("Adress");
            Label label = new Label(adress);
            VBox layout3 = new VBox(20);
            layout3.getChildren().addAll(label);
            Scene scene3 = new Scene(layout3, 300, 250);
            stage3.setScene(scene3);
            stage3.show();


        });


        grid.getChildren().addAll(latLabel, latText, longLabel, longText, button);


        Scene scene = new Scene(grid, 300, 250);
        return scene;
    }

    private String getAdress(String adressUrl) {

        String url = baseUrl +"latlng=" + adressUrl + "&key=" + apiKey;
        //System.out.println(url);
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
            String resultString = result.toString();
            //System.out.println(result);
            JSONObject obj = new JSONObject(resultString);


            JSONObject res = obj.getJSONArray("results").getJSONObject(0);
            outcome = res.getString("formatted_address");
           // System.out.println(res.getString("formatted_address"));



        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outcome;

    }
}
