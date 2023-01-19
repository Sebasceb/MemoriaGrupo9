/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo9.modelo;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author sebas
 */
public final class Tarjetas extends Button {
    private String ruta;
    private int codigo;
    private static final String cardBack="files/cardback.jpg";
    private Estado estado;
    
    public Tarjetas(int c){
        super();
        ruta="files/"+c+".jpg";
        codigo=c;
        this.prefHeight(50);
        this.prefWidth(50);
        this.setText("");
        this.bocaAbajo();
        this.setOnAction(eh -> manejaBoton());
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
        if(this.estado==Estado.OUT)
            return;
        if(this.estado==Estado.OFF)
            this.bocaArriba();
        if(this.estado==Estado.ON)
            return;          
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
