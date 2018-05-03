package it.polito.tdp.poweroutages;

import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PowerOutagesController {
	
	Model model = new Model();
	

    @FXML
    private ChoiceBox<Nerc> menuNerc;

    @FXML
    private TextField txtYears;

    @FXML
    private TextField txtHours;

    @FXML
    private Button buttonWCA;

    @FXML
    private TextArea txtResult;
    
    
    @FXML
    void doWorstCaseAnalysis(ActionEvent event) {

    }

    @FXML
    void initialize() {
    	menuNerc.getItems().addAll(model.getNercList());
    }

}
