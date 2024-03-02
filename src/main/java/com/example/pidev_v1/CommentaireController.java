package com.example.pidev_v1;

import com.example.pidev_v1.entities.Avis;
import com.example.pidev_v1.services.ServiceAvis;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CommentaireController implements Initializable {

    @FXML
    private GridPane grid;

    ServiceAvis sa=new ServiceAvis();


    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        List<Avis> list = sa.readAll();

        int rowIndex = 1;
        int columnIndex = 0;
        int publicationsPerRow = 1;

        for (Avis avis : list) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("comm.fxml"));
                Node postNode = fxmlLoader.load();
                comController postController = fxmlLoader.getController();
                postController.setAvisInit(avis);

                // Accédez aux éléments d'interface utilisateur dans "Post.fxml" en utilisant les méthodes fournies par FXMLLoader et les ID des éléments dans le fichier FXML

                setData(avis , fxmlLoader);

                // Ajoutez le nœud de publication à la GridPane avec l'ID "ClubF"
                grid.add(postNode, columnIndex, rowIndex);



                columnIndex++;
                if (columnIndex >= publicationsPerRow) {
                    columnIndex = 0;
                    rowIndex++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void setData(Avis avis, FXMLLoader fxmlLoader){
        System.out.println("test        "+avis);

        String user =avis.getNom();
        String comme= avis.getCommentaire();
        int note=avis.getNote();

        Image img = new Image("utilisateur.png");
        Image img1 = new Image("etoile.png");//servicePublication.displayPDP(post.getId_user())

        Label nomuser = (Label) fxmlLoader.getNamespace().get("username");
        TextField comment=(TextField) fxmlLoader.getNamespace().get("commentTF");
        ImageView note1 = (ImageView) fxmlLoader.getNamespace().get("noteA");
        ImageView note2 = (ImageView) fxmlLoader.getNamespace().get("noteB");
        ImageView note3 = (ImageView) fxmlLoader.getNamespace().get("noteC");
        ImageView note4 = (ImageView) fxmlLoader.getNamespace().get("noteD");
        ImageView note5 = (ImageView) fxmlLoader.getNamespace().get("noteE");
        ImageView pdp = (ImageView) fxmlLoader.getNamespace().get("pdp");





        nomuser.setText(user);
        comment.setText(comme);
        switch (note)
        {
            case(1):
                note1.setVisible(true);
                break;
            case(2):
                note1.setVisible(true);
                note2.setVisible(true);
                break;
            case(3):
                note1.setVisible(true);
                note2.setVisible(true);
                note3.setVisible(true);
                break;
            case(4):
                note1.setVisible(true);
                note2.setVisible(true);
                note3.setVisible(true);
                note4.setVisible(true);
                break;
            case(5):
                note1.setVisible(true);
                note2.setVisible(true);
                note3.setVisible(true);
                note4.setVisible(true);
                note5.setVisible(true);


        }

        pdp.setImage(img);


    }
}
