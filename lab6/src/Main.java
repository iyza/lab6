import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;



public class Main extends Application{

    private ImageView imageView;
    private Image image0 = new Image("file:default.jpg");
    private Image image1 = new Image("file:image1.jpg");
    private Image image2 = new Image("file:image2.jpg");
    private Image image3 = new Image("file:image3.jpg");

    private Label bottom;
    private Slider luminositeS, contrasteS, teinteS, saturationS;
    private ColorAdjust colorAdjust = new ColorAdjust();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        bottom = new Label("Bienvenue dans le modificateur d'images!");
        ContextMenu contextMenu = new ContextMenu();

        imageView = new ImageView(image0);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(500);

        BorderPane bp =  new BorderPane();
        bp.setTop(menu());
        bp.setCenter(centre());
        bp.setBottom(bottom);
        bp.getCenter().setOnContextMenuRequested(event -> contextMenu.show(imageView, event.getScreenX(), event.getScreenY()));

        primaryStage.setScene(new Scene(bp));
        primaryStage.setTitle("Laboratore 6");
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    private MenuBar menu() {
        Menu fichiers = new Menu("Fichiers");
        Menu actions = new Menu("Actions");
        Menu image = new Menu("Charger une image");

        MenuItem photo1 = new MenuItem("Image #1");
        MenuItem photo2 = new MenuItem("Image #2");
        MenuItem photo3 = new MenuItem("Image #3");
        MenuItem reinitialiser = new MenuItem("Reinitialiser");

        fichiers.getItems().addAll(image);
        image.getItems().addAll(photo1, photo2, photo3);
        actions.getItems().addAll(reinitialiser);

        photo1.setOnAction(event -> {imageView.setImage(image1);
            bottom.setText("Image 1 chargée");});
        photo2.setOnAction(event -> {imageView.setImage(image2);
            bottom.setText("Image 2 chargée");});
        photo3.setOnAction(event -> {imageView.setImage(image3);
            bottom.setText("Image 3 chargée");});
        reinitialiser.setOnAction(event -> reset());

        return new MenuBar(fichiers, actions);
    }

    private ContextMenu contextMenu() {
        Menu fichiers = new Menu("Fichiers");
        Menu actions = new Menu("Actions");
        Menu image = new Menu("Charger une image");

        MenuItem photo1 = new MenuItem("Image #1");
        MenuItem photo2 = new MenuItem("Image #2");
        MenuItem photo3 = new MenuItem("Image #3");
        MenuItem reinitialiser = new MenuItem("Reinitialiser");

        fichiers.getItems().addAll(image);
        image.getItems().addAll(photo1, photo2, photo3);
        actions.getItems().addAll(reinitialiser);

        photo1.setOnAction(event -> {imageView.setImage(image1);
            bottom.setText("Image 1 chargée");});
        photo2.setOnAction(event -> {imageView.setImage(image2);
            bottom.setText("Image 2 chargée");});
        photo3.setOnAction(event -> {imageView.setImage(image3);
            bottom.setText("Image 3 chargée");});
        reinitialiser.setOnAction(event -> reset());

        return new ContextMenu(fichiers, actions);
    }

    private void reset() {
        colorAdjust.setBrightness(0);
        colorAdjust.setContrast(0);
        colorAdjust.setHue(0);
        colorAdjust.setSaturation(0);
        luminositeS.setValue(0);
        contrasteS.setValue(0);
        teinteS.setValue(0);
        saturationS.setValue(0);

        bottom.setText("Réinitialisation des ajustements de couleurs");
    }

    private HBox centre() {
        Label luminositeL = new Label("Luminosité:");
        Label contrasteL = new Label("Contraste:");
        Label teinteL = new Label("Teinte:");
        Label saturationL = new Label("Saturation:");

        luminositeS = new Slider(-1, 1, 0);
        luminositeS.setOnMouseDragged(event -> colorAdjust.setBrightness(luminositeS.getValue()));
        Tooltip lumonisteT = new Tooltip("Rend l'image plus claire ou plus sombre");
        luminositeS.setTooltip(lumonisteT);

        contrasteS = new Slider(-1, 1, 0);
        contrasteS.setOnMouseDragged(event -> colorAdjust.setContrast(contrasteS.getValue()));
        Tooltip contrasteT = new Tooltip("Rend l'image plus claire ou plus sombre");
        contrasteS.setTooltip(contrasteT);

        teinteS = new Slider(-1, 1, 0);
        teinteS.setOnMouseDragged(event -> colorAdjust.setHue(teinteS.getValue()));
        Tooltip teinteT = new Tooltip("Rend l'image plus claire ou plus sombre");
        teinteS.setTooltip(teinteT);

        saturationS = new Slider(-1, 1, 0);
        saturationS.setOnMouseDragged(event -> colorAdjust.setSaturation(saturationS.getValue()));
        Tooltip saturationT = new Tooltip("Rend l'image plus claire ou plus sombre");
        saturationS.setTooltip(saturationT);

        imageView.setEffect(colorAdjust);

        VBox vbucks = new VBox(luminositeL, luminositeS, contrasteL, contrasteS, teinteL, teinteS, saturationL, saturationS);
        vbucks.setAlignment(Pos.CENTER);
        HBox hbox = new HBox(imageView, vbucks);
        hbox.setAlignment(Pos.CENTER);

        return hbox;
    }
}
