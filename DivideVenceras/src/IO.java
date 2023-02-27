import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class IO {
	
	public static void leerCoches(final String pahtname, final List <Coche> coches) throws FileNotFoundException {
		final Scanner input = new Scanner(new File(pahtname));
		StringTokenizer st;
		String token;
		
		while (input.hasNextLine()) {
			try {
				st = new StringTokenizer(input.nextLine(), ",");;
				
				String modelo = st.nextToken();
				
				token = st.nextToken();
				
				Coche.TipoCombustible comb;
				switch (token.toLowerCase()) {
					case "petrol": comb = Coche.TipoCombustible.PETROL; break;
					case "diesel": comb = Coche.TipoCombustible.DIESEL; break;
					default: throw new IllegalArgumentException("Unexpected value: " + token);
				}
				
				int asientos = (int) Double.parseDouble(st.nextToken());
				
				token = st.nextToken();

				Coche.TipoTansmisión trans;
				switch (token.toLowerCase()) {
					case "autoken": trans = Coche.TipoTansmisión.AUTOMÁTICO; break;
					case "manual":  trans = Coche.TipoTansmisión.MANUAL;     break;
					default: throw new IllegalArgumentException("Unexpected value: " + token);
				}
				
				double capacidad = Double.parseDouble(st.nextToken());

				double consumoMedio = Double.parseDouble(st.nextToken());
				
				coches.add(new Coche(modelo, comb, asientos, trans, capacidad, consumoMedio));

			} catch (NumberFormatException e) {
				System.err.println("Error trying to read number: " + e.getMessage());
			} catch (IllegalArgumentException e) {
				System.err.println("Error trying to read enum: " + e.getMessage());
			} catch (NoSuchElementException e) {
				System.err.println("Error trying to read next token.");
			}
		}
		
		input.close();
	}
}
