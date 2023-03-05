package campusvirtual;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Auxiliar {
    

	/**********************************************************************
	* Method name: ImprimeHistograma
	*
	* Description of the Method: It prints on the screen the histogram of a picture
	* 
	* *********************************************************************/
	public static void ImprimeHistograma(int[] Histograma) {
		for(int i=0;i <= Histograma.length-1; i++) 
			System.out.print(Histograma[i] + " ");
		System.out.println();
	}


	/**********************************************************************
	* Method name: getGrayScale
	*
	* Description of the Method: It computes the grey value of a rgb pixel
	* 
	* *********************************************************************/
	private static int  getGrayScale(int rgb) {
		int r = (rgb >> 16) & 0xff;
		int g = (rgb >> 8) & 0xff;
		int b = (rgb) & 0xff;

		return (( r +  g + b) / 3);   
	}
        
 
	/**********************************************************************
	* Method name: HistogramaImagen
	*
	* Description of the Method: It computes the histograma of a picture. An image histogram is a 
	* distribution showing the frequency of appeareance of each gray-level value.
	* 
	* *********************************************************************/       
	public static int[] HistogramaImagen(String Ruta) throws IOException, InterruptedException{

		int[] Histograma= new int[256];
		for(int i=0; i<=255; i++ )        
			Histograma[i]=0;                    

		File Imagen = new File(Ruta);
		BufferedImage input = ImageIO.read(Imagen);

		int x = input.getWidth();
		int y = input.getHeight();
		
		for (int i = 0; i <= x - 1; i++) 
			for (int j = 0; j <= y - 1; j++) 
				Histograma[getGrayScale(input.getRGB(i, j))]++;

		return Histograma;    
	}   


	/**********************************************************************
	* Method name: GenerarImagenGrises
	*
	* Description of the Method: It transforms a RGB picture into a grayscale one. The new image is stored in
	* a file. 
	* 
	* *********************************************************************/       
	public static void GenerarImagenGrises(String ImagenEntrada, String ImagenSalida) throws IOException, InterruptedException{

		File Imagen = new File(ImagenEntrada);
		BufferedImage input = ImageIO.read(Imagen);          

		int x = input.getWidth();
		int y = input.getHeight();

		for (int i = 0; i <= x - 1; i++) {
			for (int j = 0; j <= y - 1; j++) {
				int grey_rgb = 0;
				int avg=getGrayScale(input.getRGB(i, j));
				for(int k = 0; k < 4; k++) {
					grey_rgb <<= 8;
					grey_rgb |= avg & 0xFF;
				}
				input.setRGB(i, j, grey_rgb);    
			}
		}

		//La imagen en grises se graba en un fichero
		File Imagen2 = new File(ImagenSalida);
		ImageIO.write(input, "png", Imagen2);
	}   

        
        
	/**********************************************************************
	* Method name: GenerarImagenOrdenandoColumnas
	*
	* Description of the Method: It transforms a RGB picture into a grayscale one. Then, 
	* it sorts the gray levels of every pixel in a column of the image in ascending order.
	* Finally, the new image with the sorted columns is stored in a file. 
	* 
	* *********************************************************************/       
	public static void GenerarImagenOrdenandoColumnas(String ImagenEntrada, String ImagenSalida, int Metodo) throws IOException, InterruptedException{
		//Genera una nueva imagen ordenando cada columna de la imagen de manera ascendente (niveles de gris).
		//Metodo 0 se ordena cada fila con el método de la burbuja
		//Metodo 1 se ordena con el método quicksort

		File Imagen = new File(ImagenEntrada);
		BufferedImage input = ImageIO.read(Imagen);          

		int x = input.getWidth();
		int y = input.getHeight();

		int[][] MatrizGrises = new int[x][y];

		for (int i = 0; i < x - 1; i++) {
			for (int j = 0; j < y - 1; j++) {                 
				MatrizGrises[i][j]=getGrayScale(input.getRGB(i, j));             
			}
			if (Metodo==0)
				Ordenar.burbuja(MatrizGrises[i]);    
			else
				Ordenar.quicksortA(MatrizGrises[i]);
		}

		//Una vez ordenado se genera la imagen

		for (int i = 0; i <= x - 1; i++) {
			for (int j = 0; j <= y - 1; j++) {
				int grey_rgb = 0;
				int avg=MatrizGrises[i][j];
				for(int k = 0; k < 4; k++) {
					grey_rgb <<= 8;
					grey_rgb |= avg & 0xFF;
				}
				input.setRGB(i, j, grey_rgb);    
			}
		}

		//La imagen en grises se graba en un fichero
		File Imagen2 = new File(ImagenSalida);
		ImageIO.write(input, "png", Imagen2); 
	}   

}
