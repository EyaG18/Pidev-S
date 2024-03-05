package com.example.pidev_v1;

import com.example.pidev_v1.API.emailPartenaire;
import com.example.pidev_v1.entities.Panier;
import com.example.pidev_v1.entities.User;
import com.example.pidev_v1.services.Mailing;
import com.example.pidev_v1.services.PanierService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import javax.swing.JOptionPane;
import java.net.URL;
import java.util.ResourceBundle;

public class PanierClient  implements Initializable {

User user;
User currentUser;
    public void setUser(User user) {
        currentUser = user;
        LabelCleintConnecte.setText(currentUser.getNomuser()+""+currentUser.getPrenomuser());
    }

    @FXML
    private Label LabelCleintConnecte;
    @FXML
    private ListView<Panier> ListPanier;
    private final ObservableList<Panier> listP = FXCollections.observableArrayList();
    PanierService sp = new PanierService();
    Panier panier = new Panier();

    @FXML
    private Label total;
    @FXML
    private Button vider;
    @FXML
    private Button pdfP;

    PanierService ps = new PanierService();

    @FXML
    void imprimer(ActionEvent event) {
/*
        try {
            Doc doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\ASUS\\Documents\\Panier.pdf"));
            doc.open();

            Image img = Image.getInstance("C:\\Users\\ASUS\\Documents\\Zoom\\GestionProduitFinalOLD\\src\\IMG\\IMG_9493.jpg");
            img.scaleAbsoluteHeight(91);
            img.scaleAbsoluteWidth(120);
            img.setAlignment(Image.ALIGN_RIGHT);
            doc.open();
            doc.add(img);

            doc.add(new Paragraph(" "));
            Font font = new Font(Font.FontFamily.TIMES_ROMAN, 28, Font.UNDERLINE, BaseColor.BLACK);
            Paragraph p = new Paragraph("Panier ", font);
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));

            PdfPTable tabpdf = new PdfPTable(2);
            tabpdf.setWidthPercentage(100);

            PdfPCell cell;
            cell = new PdfPCell(new Phrase("Produit"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);


            cell = new PdfPCell(new Phrase("Prix"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);

            String requete = "SELECT `nom_produit`, `prix_produit` FROM `panier`";
            PreparedStatement pst = con.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                cell = new PdfPCell(new Phrase(rs.getString(1)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(rs.getDouble(2))));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);
            }
            cell = new PdfPCell(new Phrase("Somme"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);
            String req = "SELECT SUM(prix_produit) FROM panier";
            PreparedStatement pt = con.prepareStatement(req);
            ResultSet rss = pt.executeQuery();
            while (rss.next()) {
                cell = new PdfPCell(new Phrase(String.valueOf(rss.getDouble(1))));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);

            }


            doc.add(tabpdf);

            JOptionPane.showMessageDialog(null, "Success !!");


            doc.close();

            Desktop.getDesktop().open(new File("C:\\Users\\ASUS\\Documents\\Panier.pdf"));
        } catch (DocumentException | HeadlessException | IOException e) {
            System.out.println("ERROR PDF");
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println(e.getMessage());

        }*/
    }


    @FXML
    void supptable(ActionEvent event) {

        sp.truncatePanier();
        JOptionPane.showMessageDialog(null, "Panier vide!");
        //Mailing.send(currentUser.getEmailUsr(), "Bonjour Cher Utilisateur vous venez de Videz Votre Panier !","[Notifications d'état de Panier - Arya ]");
        emailPartenaire.sendEmail(currentUser.getEmailUsr(), "[Notifications d'état de Panier - Arya ]","Bonjour Cher Utilisateur vous venez de Videz Votre Panier !"+currentUser.getNomuser()+""+currentUser.getPrenomuser());
    }

    private void afficherMessage(String titre, String contenu, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }



    private double totalPanier = ps.afficherSuma();



    private void remplirListPanier() {
        listP.addAll(sp.afficherCotenuPanier());
        ListPanier.setCellFactory((ListView<Panier> lv) -> (ListCell<Panier>) new Cell());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        remplirListPanier();
        ListPanier.setItems(listP);
        total.setText("Le Total de votre Wislist est :"+totalPanier + "DT");


    }
    public class Cell extends ListCell<Panier> {

        Button supprimer = new Button("🚮");

        Label Quantite = new Label("");

        Label RéférenceProduit = new Label("");

        Label PrixUnitaireProduit = new Label("");
        GridPane pane = new GridPane();
        AnchorPane aPane = new AnchorPane();

        public Cell() {

            RéférenceProduit.setStyle("-fx-font-weight: bold; -fx-font-size: 1.5em;");
            GridPane.setConstraints(RéférenceProduit, 1, 0, 2, 3);

            GridPane.setConstraints(Quantite, 2, 0, 2, 3);

            GridPane.setConstraints(PrixUnitaireProduit,3,0,2,3);





            supprimer.setStyle("-fx-text-fill : black;-fx-background-color : none;");
            GridPane.setConstraints(supprimer, 4, 0, 4, 3);


            pane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
            pane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
            pane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.CENTER, true));


           /* pane.getColumnConstraints().addAll(
                    new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true),
                    new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true),
                    new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true),
                    new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true)
            );*/

            pane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true));
            pane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true));
            pane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, VPos.CENTER, true));

            /*pane.getRowConstraints().addAll(
                    new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, VPos.CENTER, true)
            );*/



            pane.setHgap(2);
            pane.setVgap(2);
            pane.getChildren().setAll(RéférenceProduit, Quantite,PrixUnitaireProduit, supprimer);
            AnchorPane.setTopAnchor(pane, 0d);
            AnchorPane.setLeftAnchor(pane, 0d);
            AnchorPane.setBottomAnchor(pane, 0d);
            AnchorPane.setRightAnchor(pane, 0d);
            aPane.getChildren().add(pane);
        }

        @Override
        protected void updateItem(Panier item, boolean empty) {
            super.updateItem(item, empty);
//            System.out.println(item);
            setGraphic(null);
            setText(null);
            setContentDisplay(ContentDisplay.LEFT);
            if (!empty && item != null) {

                RéférenceProduit.setText(String.valueOf(item.getId_Produit()));
                Quantite.setText(String.valueOf(item.getQuantiteParProduit()));
                supprimer.setOnMouseEntered((event) ->

                        supprimer.setStyle("-fx-text-fill : white; -fx-background-color : #870505;")
                );

                supprimer.setOnMouseExited((event) ->

                        supprimer.setStyle("-fx-text-fill : black;-fx-background-color : none;")
                );
                supprimer.setOnAction(event -> {

                            Panier f = getListView().getItems().get(getIndex());

                            System.out.print(f);
                            sp.suprimer(f);
                            JOptionPane.showMessageDialog(null, "Produit supprime !");
                            listP.clear();
                            remplirListPanier();
                        }
                );

                setGraphic(aPane);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }
        }


    }}
