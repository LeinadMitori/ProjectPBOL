/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package project.penjualan;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author leina
 */
public class FXMLInputBrgController implements Initializable {

    @FXML
    private TextField txtkode;
    @FXML
    private TextField txtnama;
    @FXML
    private TextField txttarif;
    @FXML
    private Button btnsimpan;
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnquit;
    boolean editdata;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void execute(BrgModel d){
        if(!d.getKodebrg().isEmpty()){
          editdata=true;
          txtkode.setText(d.getKodebrg());
          txtnama.setText(d.getNamabrg());
          txttarif.setText(String.valueOf(d.getTarif()));          
          txtkode.setEditable(false);          txtnama.requestFocus();         
        }
    }
    
    

    @FXML
    private void simpanklik(ActionEvent event) {
        BrgModel n=new BrgModel();        
        n.setKodebrg(txtkode.getText());
        n.setNamabrg(txtnama.getText());     
        n.setTarif(Double.parseDouble(txttarif.getText()));
        FXMLDocumentController.dtbrg.setBrgModel(n);
        if(editdata){
            if(FXMLDocumentController.dtbrg.update()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
               a.showAndWait();   
               txtkode.setEditable(true);        
               batalklik(event);              
                           }else if(FXMLDocumentController.dtbrg.validasi(n.getKodebrg())<=0){
            if(FXMLDocumentController.dtbrg.insert()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil disimpan",ButtonType.OK);
               a.showAndWait();            
               batalklik(event);
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
        }else{Alert a=new Alert(Alert.AlertType.ERROR,"Data sudah ada",ButtonType.OK);
            a.showAndWait();
            txtkode.requestFocus();
        }
            } else {               
                Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
                a.showAndWait(); 
            }

    }

    @FXML
    private void batalklik(ActionEvent event) {
        txtkode.setText("");        
        txtnama.setText("");
        txttarif.setText("");         
        txtkode.requestFocus();
    }

    @FXML
    private void quitklik(ActionEvent event) {
        btnquit.getScene().getWindow().hide();
    }
    
}
