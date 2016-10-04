import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by michalbaran on 28/09/16.
 */
public class GeoRun extends Application {


    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("GeoLoc v 0.1");


        VBox layoutG = new VBox(20);
        //Scene sceneGeo = new Scene(layoutG, 300, 250);

        VBox layoutA = new VBox(20);
        //Scene sceneAdr = new Scene(layoutA, 300, 250);

        Button getCoords = new Button("Get coordinates");
        getCoords.setOnAction(event1 -> {
            CoordScene coordScene = new CoordScene();
            Stage Coor = new Stage();
            Coor.setScene(coordScene.getScene());
            Coor.show();
            //Stage geoResult = new Stage();
            //VBox lay = new VBox(20);



            //System.out.println(coordScene.getAdressUrl());
            //test.getCoordinates();
        });

       Button getAdr = new Button("Get adress");
        getAdr.setOnAction(event2 -> {
            AdrScene adrScene = new AdrScene();
            Stage Adr = new Stage();
            Adr.setScene(adrScene.getScene());
            Adr.show();
        });

        VBox layout = new VBox(20);
        layout.getChildren().addAll(getCoords, getAdr);
        layout.setAlignment(Pos.CENTER);




        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();






    }


}
