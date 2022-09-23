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
 * @author LIKMI
 */
public class FXMLInputCustController implements Initializable {

    @FXML
    private TextField txtid;
    @FXML
    private TextField txtnama;
    @FXML
    private TextField txtalamat;
    @FXML
    private TextField txttotal;
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
    public void execute(CustModel d){
        if(!d.getIdmember().isEmpty()){
          editdata=true;
          txtid.setText(d.getIdmember());
          txtnama.setText(d.getNama());          txtalamat.setText(d.getAlamat());
          txttotal.setText(String.valueOf(d.getTotal()));          
          txtid.setEditable(false);          txtnama.requestFocus();         
        }
    }
    
    

    @FXML
    private void simpanklik(ActionEvent event) {
        CustModel n=new CustModel();        
        n.setIdmember(txtid.getText());
        n.setNama(txtnama.getText());     
        n.setAlamat(txtalamat.getText());  
        n.setTotal(Double.parseDouble(txttotal.getText()));
        FXMLDocumentController.dtcust.setCustModel(n);
        if(editdata){
            if(FXMLDocumentController.dtcust.update()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
               a.showAndWait();   
               txtid.setEditable(true);        
               batalklik(event);              
                           }else if(FXMLDocumentController.dtcust.validasi(n.getIdmember())<=0){
            if(FXMLDocumentController.dtcust.insert()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil disimpan",ButtonType.OK);
               a.showAndWait();            
               batalklik(event);
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
        }else{Alert a=new Alert(Alert.AlertType.ERROR,"Data sudah ada",ButtonType.OK);
            a.showAndWait();
            txtid.requestFocus();
        }
            } else {               
                Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
                a.showAndWait(); 
            }

    }

    @FXML
    private void batalklik(ActionEvent event) {
        txtid.setText("");        
        txtnama.setText("");
        txtalamat.setText("");       
        txttotal.setText("");  
        txtid.requestFocus();
    }

    @FXML
    private void quitklik(ActionEvent event) {
        btnquit.getScene().getWindow().hide();
    }
    
}
