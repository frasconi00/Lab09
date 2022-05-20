
package it.polito.tdp.borders;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import it.polito.tdp.borders.model.StatoEGrado;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    @FXML
    private ComboBox<Country> cmbStati;

    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	txtResult.clear();
    	int anno = 0;
    	
    	try {
    		anno = Integer.parseInt(txtAnno.getText());
    	}catch(NumberFormatException e) {
    		txtResult.setText("Devi inserire un anno");
    	}
    	
    	if(anno<1816 || anno>2016) {
    		txtResult.setText("Devi inserire un anno nell'intervallo 1816-2016");
    	}else {
    		this.model.creaGrafo(anno);
    		
    		List<StatoEGrado> lista = this.model.stampa1();
    		if(lista.size()==0) {
    			txtResult.setText("Nessun risultato");
    		}else {
    			for(StatoEGrado s : lista) {
    				txtResult.appendText(s.getCountry().getStateNme()+" - stati confinanti: "+s.getGrado()+"\n");
    			}
    			
    			txtResult.appendText("\nNumero di componenti connesse: "+this.model.stampa2());
    		}
    	}
    	
    }
    
    @FXML
    void handleStatiRaggiungibili(ActionEvent event) {
    	txtResult.clear();
    	
    	int anno=0;
    	Country partenza = cmbStati.getValue();
    	
    	try {
    		anno = Integer.parseInt(txtAnno.getText());
    	}catch(NumberFormatException e) {
    		txtResult.setText("Devi inserire un anno");
    	}
    	
    	if(anno<1816 || anno>2016) {
    		txtResult.setText("Devi inserire un anno nell'intervallo 1816-2016");
    	}else if(partenza==null){
    		txtResult.setText("Devi scegliere un Paese di partenza");
    	}else {
    		this.model.creaGrafo(anno);
    		List<Country> result = this.model.statiRaggiungibili(partenza);
    		if(result==null) {
    			txtResult.setText("Non è possibile raggiungere alcuno stato a partire da quello Stato");
    		}else {
    			txtResult.setText("Stati raggiungibili a partire da "+partenza.getStateNme()+":\n");
    			for(Country c : result) {
    				txtResult.appendText(c.getStateNme()+"\n");
    			}
    		}
    	}

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	assert cmbStati != null : "fx:id=\"cmbStati\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	
    	this.cmbStati.getItems().addAll(this.model.getAllCountries());
    }
}
