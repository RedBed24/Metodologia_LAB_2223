package profes;
/** Clase para poder leer del teclado cualquier dato de tipo Entero, Cadena, Carácter */

import java.util.*;

public class leer{

	private static final Scanner TECLADO=new Scanner(System.in);
   
	/**********************************************************************
	* Method name: entero
	*
	* Description of the Method: It provides message s and then it reads an integer from the keyboard
	* 
	* *********************************************************************/
  	public static int entero(String s){
		int res=0;
		boolean vale=true;
		do{
           	   vale=true;       
           	   System.out.println(s);
           try{
        	   res=TECLADO.nextInt();
        	   System.out.println(res+"");
           }    	                           
           catch (Exception e) {
        	   vale=false; 
               TECLADO.next();
           }
		}while(!vale);
        TECLADO.nextLine();
 		return res;
	}

  
  	/**********************************************************************
  	* Method name: entero
  	*
  	* Description of the Method: It reads an integer from the keyboard
  	* 
  	* *********************************************************************/
  	public static int entero(){
		return entero("");
	}


  	/**********************************************************************
  	* Method name: entero
  	*
  	* Description of the Method: It reads an integer between min and max from the keyboard
  	* 
  	* *********************************************************************/
	public static int entero(String s, int min, int max){
		int res;
		do{
			res=entero(s+" ["+min+","+max+"]");
		}while(res<min || res>max);
		return res;
	}

 
	/**********************************************************************
	* Method name: cadena
	*
	* Description of the Method: It provides message s and then it reads an String from the keyboard
	* 
	* *********************************************************************/
    public static String cadena(String s){
        String res="";
        boolean vale=true;
        do{
        	System.out.println(s);
            vale=true;
            try{
                 res=res+TECLADO.nextLine();
                 System.out.println(res);
			} catch (Exception e){
			     vale=false; 
			     TECLADO.next();
			}
        }while (!vale);
//        teclado.nextLine();
        return res;
   
    }
    

    /**********************************************************************
	* Method name: cadena
	*
	* Description of the Method: It reads an String from the keyboard
	* 
	* *********************************************************************/
    public static String cadena(){
    	return cadena("");
    }
    

    /**********************************************************************
	* Method name: caracter
	*
	* Description of the Method: It provides message s and then it reads a char from the keyboard
	* 
	* *********************************************************************/
	public static char caracter(String cad){
		return cadena(cad).charAt(0);
	 }

	
	/**********************************************************************
		* Method name: caracter
		*
		* Description of the Method: It reads a char from the keyboard
		* 
		* *********************************************************************/
    public static char caracter(){
	    return caracter("");
	}
}

