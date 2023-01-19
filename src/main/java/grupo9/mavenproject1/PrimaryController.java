package grupo9.mavenproject1;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Math.random;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
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

public class PrimaryController implements Initializable{
    public static ArrayList<Integer> codigos;
    public static ArrayList<int[]> posiciones;
    public static ArrayList<Tarjetas> listaTarjetas;
   
    
    @FXML
    private GridPane tarjetasPane;
    
    int columnas= 6;
    int filas= 4;
    
    public static Tarjetas selected1=null;
    public static Tarjetas selected2=null;
    
    public void initialize(URL url, ResourceBundle rb){
        codigos=new ArrayList();
        posiciones=new ArrayList();
        listaTarjetas=new ArrayList();
        System.out.println("Hello World");
        Runnable task2 = () -> { llenarGridPane(); };
        new Thread(task2).start();
        
    }
   
    
    public void llenarGridPane(){
        
        tarjetasPane.getChildren().clear();
        limpiaListas();
        Random random = new Random();
        
        for(int c:codigos){
            
            Tarjetas btn1= new Tarjetas(c);
            listaTarjetas.add(btn1);
            Tarjetas btn2= new Tarjetas(c);
            listaTarjetas.add(btn2);
          
        }
        Collections.shuffle(listaTarjetas);
        
        for(int i=0;i<posiciones.size(); i++){
           int[] posi=posiciones.get(i);
           Tarjetas tarjeta=listaTarjetas.get(i);
           tarjeta.setPosicion(posi);
           tarjetasPane.add(tarjeta,posi[0],posi[1]);
            
        }   
    }
    
    public void limpiaListas(){
        if(codigos!=null)
            codigos.clear();
        if(posiciones!=null)
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
        
        Collections.shuffle(codigos);
        Collections.shuffle(posiciones);
        
    } 
    
    
    
    public void actualizarTarjetas(Tarjetas tAdd, Tarjetas tDelete){
        int[]posi =tDelete.getPosicion();
        listaTarjetas.remove(tDelete);
        listaTarjetas.add(tAdd);
        tAdd.bocaAbajo();
        tarjetasPane.getChildren().remove(tDelete);
        tarjetasPane.add(tAdd, posi[0], posi[1]);
       
    }
    /*
    public void reLlenarGridPane(){
        
        tarjetasPane.getChildren().clear();
        for(Tarjetas t: listaTarjetas){
            int[]posi =t.getPosicion();
            tarjetasPane.add(t, posi[0], posi[1]);
        } 
    }*/
    
    public final class Tarjetas extends Button {
        private String ruta;
        private int codigo;
        private static final String cardBack="files/cardback.jpg";
        private Estado estado;
        private int[] posicion;

        public Tarjetas(int c){
            super();

            ruta="files/"+c+".jpeg";
            codigo=c;
            this.prefHeight(50);
            this.prefWidth(50);
            this.setText("");
            this.bocaAbajo();
            this.setOnAction(eh -> {manejaBoton();});
        }

        public int[] getPosicion() {
            return posicion;
        }

        public void setPosicion(int[] posicion) {
            this.posicion = posicion;
        }

        public Estado getEstado() {
            return estado;
        }

        public void setEstado(Estado estado) {
            this.estado = estado;
        }


        public String getRuta() {
            return ruta;
        }

        public void setRuta(String ruta) {
            this.ruta = ruta;
        }

        public int getCodigo() {
            return codigo;
        }

        public void setCodigo(int codigo) {
            this.codigo = codigo;
        }

        public void bocaAbajo(){
            if(this.estado==Estado.OUT)
                return;

            this.estado=Estado.OFF;
            ImageView imgv = new ImageView();
             try ( FileInputStream input = new FileInputStream(Tarjetas.cardBack)){
                 Image image = new Image(input);
                 imgv.setImage(image);
                 this.setGraphic(imgv);
             }catch(IOException e){
                 System.out.println(e.getMessage());
             }
        }

        public void bocaArriba(){
            this.estado=Estado.ON;
            ImageView imgv = new ImageView();
             try ( FileInputStream input = new FileInputStream(this.ruta)){
                 Image image = new Image(input);
                 imgv.setImage(image);
                 this.setGraphic(imgv);
             }catch(IOException e){
                 System.out.println(e.getMessage());
             }
        }

        public void manejaBoton(){
        
            if(this.estado==Estado.OFF){
                this.bocaArriba();
                this.verificador();
            }else
                return;
        }

        public void verificador(){
            if(selected1==null){
                selected1=this;
            }else{
                selected2=this;
                if(selected1.equals(selected2)){
                    for(Tarjetas carta: PrimaryController.listaTarjetas){
                        if(selected1.equals(carta)){
                            selected1.setEstado(Estado.OUT);
                            actualizarTarjetas(selected1,carta);
                        }
                        if(selected2.equals(carta)){
                            selected2.setEstado(Estado.OUT);
                            actualizarTarjetas(selected2,carta);
                        }  
                    }
                }else{
                    for(Tarjetas carta: PrimaryController.listaTarjetas){
                        if(selected1.equals(carta)){
                            selected1.bocaAbajo();
                            actualizarTarjetas(selected1,carta);
                            selected1=null;
                        }
                        if(selected2.equals(carta)){
                            selected2.bocaAbajo();
                            actualizarTarjetas(selected2,carta);
                            selected2=null;
                        }  
                    }
                }
            }
        }
        /*
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Tarjetas other = (Tarjetas) obj;
            if (this.codigo != other.codigo) {
                return false;
            }
            return true;
        }*/
   
    }
}

