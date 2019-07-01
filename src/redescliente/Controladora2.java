/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redescliente;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.sql.ResultSet;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JTextField;
import redescliente.Login;

/**
 *
 * @author Dexter
 */
public class Controladora2 {
    String hostName = "localhost";
  JButton Ingresar;
  JTextField Usuario, Clave;
  Login log;
     public Controladora2(JButton Ingresar) {
        this.Ingresar = Ingresar;
      
    }
    
    public Controladora2 (JTextField Usuario, JTextField Clave){
        this.Usuario = Usuario;
        this.Clave = Clave;
    }
    
     public Controladora2 (){
        
    }
    
    public void iniciaLogin (){
        Usuario.setText(null);
        Clave.setText(null);
    }
    
    public void datosLogin(){
        log.setUsuario(Usuario.getText());
        log.setClave(Clave.getText());
    }
    
    
public void run() {
String prueba = "prueba";
while (true) {
try {
sleep(1000); // 1 segundo (se mide en milisegundos)

} catch (InterruptedException e) {
e.getMessage();
}
System.out.print(prueba);
}
}  
  
public void enviarInfo (String Usuario, String Clave) throws ClassNotFoundException, IOException{

    String hostName = "localhost";
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
    try
    {
    System.out.println("Estableciendo conexion");
    Socket cliente = new Socket(hostName, 30001);              
    oos = new ObjectOutputStream(cliente.getOutputStream());// asociar buffer de envio
    System.out.println("Sending request to Socket Server"); 
    String input = Usuario + ";" + Clave;
    oos.writeObject(input); // mandar la info
    ois = new ObjectInputStream(cliente.getInputStream());// asociar buffer de entrada
    String message = (String) ois.readObject();
    System.out.println(message);
    ois.close();
    oos.close();
    cliente.close();   
        }  
        catch(IOException io)
    {
        System.out.println("Error creando el socket "+io);
    }    
    }

public boolean enviarMensaje (String mensaje,String indice,String persona) throws ClassNotFoundException, IOException{

    String hostName = "localhost";
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
    try
        {
        System.out.println("Estableciendo conexion");
        Socket cliente = new Socket(hostName, 30001);              
        oos = new ObjectOutputStream(cliente.getOutputStream());// asociar buffer de envio
        System.out.println("Sending request to Socket Server"); 
        String input = "clienteMensaje;JDVA;"+mensaje+";JDVA;"+indice+";JDVA;"+persona+"";
        oos.writeObject(input); // mandar la info
        ois = new ObjectInputStream(cliente.getInputStream());// asociar buffer de entrada
        String message = (String) ois.readObject();
        System.out.println(message);
        ois.close();
        oos.close();
        cliente.close();
        return true;
            }  
            catch(IOException io)
        {
            System.out.println("Error creando el socket "+io);
        }    
    return false;
    }
    

public String[][] SolicitarAmigos (String mensaje) throws ClassNotFoundException, IOException{

    String hostName = "localhost";
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
    try
    {  
    System.out.println("Estableciendo conexion");
    Socket cliente = new Socket(hostName, 30001);              
    oos = new ObjectOutputStream(cliente.getOutputStream());// asociar buffer de envio
    System.out.println("Sending request to Socket Server"); 
    String input = "Amigos;JDVA;"+mensaje+"";
    oos.writeObject(input); // mandar la info
    ois = new ObjectInputStream(cliente.getInputStream());// asociar buffer de entrada
    String[][] rs = (String[][]) ois.readObject();
    System.out.println(rs);
    ois.close();
    oos.close();
    cliente.close();   
    return rs;
        }  
        catch(IOException io)
    {
        System.out.println("Error creando el socket "+io);
    }    
    return null;
    }

public String[][] SolicitarChat (String de, String para) throws ClassNotFoundException, IOException{

    String hostName = "localhost";
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
    try
    {  
    System.out.println("Estableciendo conexion");
    Socket cliente = new Socket(hostName, 30001);              
    oos = new ObjectOutputStream(cliente.getOutputStream());// asociar buffer de envio
    System.out.println("Sending request to Socket Server"); 
    String input = "Chat;JDVA;"+de+";JDVA;"+para+"";
    oos.writeObject(input); // mandar la info
    ois = new ObjectInputStream(cliente.getInputStream());// asociar buffer de entrada
    String[][] rs = (String[][]) ois.readObject();
    System.out.println(rs);
    ois.close();
    oos.close();
    cliente.close();   
    return rs;
        }  
        catch(IOException io)
    {
        System.out.println("Error creando el socket "+io);
    }    
    return null;
    }
public String usuario (){
    return "amelia";
}

    //Jose
public String[] getAmigos(String idUsuario) throws ClassNotFoundException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            System.out.println("Estableciendo conexion");
            Socket cliente = new Socket(hostName, 30001);        

            oos = new ObjectOutputStream(cliente.getOutputStream());// asociar buffer de envio
            System.out.println("Sending request to Socket Server");
            
            String accion = "getAmigos";
            
            String input = accion + ";JDVA;" + idUsuario;
            
            oos.writeObject(input); // mandar la info
            ois = new ObjectInputStream(cliente.getInputStream());// asociar buffer de entrada
            String message = (String) ois.readObject();
            
            System.out.println(message);
            ois.close();
            oos.close();
            cliente.close();
            
            String[] parts = message.split(";JDVA;");
            if(parts[0].equals(accion)) {
                if(parts[1].equals("error")) {
                    String msgError = parts[2];
                    System.err.println("Error: "+ msgError);
                } else {
                    String[] result;
                    if(parts.length > 2) {
                        result = new String[parts.length-2];
                        for(int i = 2; i < parts.length; i++) {
                            result[i-2] = parts[i];
                        }
                    } else {
                        result = new String[0];
                    }                    
                    return result;
                }
            } else {
                System.err.println("No se especificó accion de retorno en: ");
                System.err.println(message);
            }
            
        } catch(IOException io) {
            System.err.println("Error creando el socket "+io);
        }    
        return null;
    }
    public String enviarLogin (String Usuario, String Clave) throws ClassNotFoundException, IOException{

        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            System.out.println("Estableciendo conexion");
            Socket cliente = new Socket(hostName, 30001);        

            oos = new ObjectOutputStream(cliente.getOutputStream());// asociar buffer de envio
            System.out.println("Sending request to Socket Server");
            
            String accion = "login";
            
            String input = accion + ";JDVA;" + Usuario + ";JDVA;" + Clave;
            
            oos.writeObject(input); // mandar la info
            ois = new ObjectInputStream(cliente.getInputStream());// asociar buffer de entrada
            String message = (String) ois.readObject();
            
            System.out.println(message);
            ois.close();
            oos.close();
            cliente.close();
            
            String[] parts = message.split(";JDVA;");
            String estatus = parts[1];
            if(parts[0].equals("login")) {
                if(estatus.equals("success")) {
                    return parts[2];
                } else {
                    String msgError = parts[2];
                    System.err.println("Error: "+ msgError);
                }
            }
            
        } catch(IOException io) {
            System.err.println("Error creando el socket "+io);
        }    
        return "";
    }



    public String login(String usuario, String clave) {
        if(usuario.isEmpty() || clave.isEmpty()) {
            System.err.println("Debe ingresar su nombre de usuario y contraseña");
            return "";
        } else {
            // Validar que el usuario no tenga ;
            try {
                return enviarLogin(usuario, clave);
            } catch(Exception e) {
                System.err.println("Error al iniciar sesion " + e.getMessage());
                return "";
            }
        }
    }




    
    public boolean agregarAmigo (String idUsuario, String idAmigo) throws ClassNotFoundException, IOException{

        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            System.out.println("Estableciendo conexion");
            Socket cliente = new Socket(hostName, 30001);        

            oos = new ObjectOutputStream(cliente.getOutputStream());// asociar buffer de envio
            System.out.println("Sending request to Socket Server");
            
            String accion = "agregarAmigo";
            
            String input = accion + ";JDVA;" + idUsuario + ";JDVA;" + idAmigo;
            
            oos.writeObject(input); // mandar la info
            ois = new ObjectInputStream(cliente.getInputStream());// asociar buffer de entrada
            String message = (String) ois.readObject();
            
            System.out.println(message);
            ois.close();
            oos.close();
            cliente.close();
            
            String[] parts = message.split(";JDVA;");
            if(parts[0].equals(accion)) {
                if(parts[1].equals("error")) {
                    String msgError = parts[2];
                    System.err.println("Error: "+ msgError);
                } else {
                    return true;
                }
            } else {
                System.err.println("No se especificó accion de retorno en: ");
                System.err.println(message);
            }
            
        } catch(IOException io) {
            System.err.println("Error creando el socket "+io);
        }    
        return false;
    }
            
    public String[] getUsuarios() throws ClassNotFoundException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            System.out.println("Estableciendo conexion");
            Socket cliente = new Socket(hostName, 30001);        

            oos = new ObjectOutputStream(cliente.getOutputStream());// asociar buffer de envio
            System.out.println("Sending request to Socket Server");
            
            String accion = "getUsuarios";
            
            String input = accion;
            
            oos.writeObject(input); // mandar la info
            ois = new ObjectInputStream(cliente.getInputStream());// asociar buffer de entrada
            String message = (String) ois.readObject();
            
            System.out.println(message);
            ois.close();
            oos.close();
            cliente.close();
            
            String[] parts = message.split(";JDVA;");
            if(parts[0].equals(accion)) {
                if(parts[1].equals("error")) {
                    String msgError = parts[2];
                    System.err.println("Error: "+ msgError);
                } else {
                    String[] result = new String[parts.length-1];
                    for(int i = 1; i < parts.length; i++) {
                        result[i-1] = parts[i];
                    }
                    return result;
                }
            } else {
                System.err.println("No se especificó accion de retorno en: ");
                System.err.println(message);
            }
            
        } catch(IOException io) {
            System.err.println("Error creando el socket "+io);
        }    
        return null;
    }
 

}
