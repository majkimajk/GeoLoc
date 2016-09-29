import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by michalbaran on 28/09/16.
 */
public class GeoRun extends Application {

    private final String apiKey = "AIzaSyCh1Ps7lceOVSsv4vEWw_aRglCR3t2xj7A";
    private final String baseUrl = "https://maps.googleapis.com/maps/api/geocode/json?";
    //private Button getCoords, getAdr;

    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("GeoLoc v 0.1");


        VBox layoutG = new VBox(20);
        Scene sceneGeo = new Scene(layoutG, 300, 250);

        VBox layoutA = new VBox(20);
        Scene sceneAdr = new Scene(layoutA, 300, 250);

        Button getCoords = new Button("Get coordinates");
        getCoords.setOnAction(event1 -> {
            GeoRun test = new GeoRun();
            CoordScene coordScene = new CoordScene();
            primaryStage.setScene(coordScene.getScene());
            //System.out.println(coordScene.getAdressUrl());
            //test.getCoordinates();
        });

       Button getAdr = new Button("Get adress");
        getAdr.setOnAction(event2 -> {
            GeoRun test = new GeoRun();
            primaryStage.setScene(sceneAdr);
            test.getAdress();
        });

        VBox layout = new VBox(20);
        layout.getChildren().addAll(getCoords, getAdr);
        layout.setAlignment(Pos.CENTER);




        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();



    }

    private void getAdress() {
        String url = baseUrl +"latlng=40.71,-73.96" + "&key=" + apiKey;

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
