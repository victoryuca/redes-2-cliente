/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redescliente;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import redescliente.Controladora2;

/**
 *
 * @author Dexter
 */
public class RedesCliente {

    
    public static void main(String[] args) {
        Scanner tienda = new Scanner(System.in);
        String codigo;   
        System.out.println("codigo:");
        codigo = tienda.nextLine();
        Controladora2 con = new Controladora2();
        try {
            con.enviarInfo(codigo, codigo);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RedesCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RedesCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
