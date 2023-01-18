package grupo9.mavenproject1;

import grupo9.modelo.*;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Math.random;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import java.util.Random;

public class PrimaryController {
    private ArrayList<Integer> codigos;
    private ArrayList<int[]> posiciones;
   
    @FXML
    private FlowPane panelJugador;
    @FXML
    private GridPane tarjetasPane;
    
    int columnas= tarjetasPane.getColumnCount();
    int filas= tarjetasPane.getRowCount();
    
    
    
    
    public void initialize(){
        
    }
    /*
    public void llenarFlowPane(int cant){
        GridPane gridPane = new GridPane();
        codigos.clear();
        posiciones.clear();
        
        
        
        int cantCodes=(cant*cant)/2;
        for(int i=0; i<cantCodes;i++){
            Random random = new Random();
            int codigo= random.nextInt();
            codigos.add(codigo);
        }
        
        for(int i=0; i<cant;i++){
           ColumnConstraints col= new ColumnConstraints();
           RowConstraints row = new RowConstraints();
           gridPane.getColumnConstraints().add(col);
           gridPane.getRowConstraints().add(row);
           
        }
        
        for(int i=0; i<cant;i++){
            for(int a=0;a<cant;a++){
                Button tarjeta= new Button();
                gridPane.add(tarjeta, i, a);
                int[] posicion={i,a};
                posiciones.add(posicion);
            }
           
        }
        panelJugador.getChildren().add(gridPane);
        
        
    }*/
    
    public void llenarGridPane(){
        
        tarjetasPane.getChildren().clear();
        limpiaListas();
        Random random = new Random();
        
        while(!codigos.isEmpty()){
            
            int indexC= random.nextInt(codigos.size());
            int codigo=codigos.get(indexC);
            codigos.remove(indexC);
            
            int indexP1= random.nextInt(posiciones.size());
            int[]posi1=posiciones.get(indexP1);
            posiciones.remove(indexP1);
            
            int indexP2= random.nextInt(posiciones.size());
            int[]posi2=posiciones.get(indexP1);
            posiciones.remove(indexP2);
            
            Tarjetas btn1= new Tarjetas(codigo);
            Tarjetas btn2= new Tarjetas(codigo);
            
            tarjetasPane.add(btn1,posi1[0],posi1[1]);
            tarjetasPane.add(btn2,posi2[0],posi2[1]);
            
        }
        
        
        
    }
    
    public void limpiaListas(){
        codigos.clear();
        posiciones.clear();
        
        for(int i=0; i<(columnas*filas/2); i++){
            codigos.add(i);
        }
        for(int c=0; c<columnas; c++){
            for(int f=0; f<filas; f++){
                int[] posi={c,f};
                posiciones.add(posi);
            
            }

        }
        
    }
    
}

