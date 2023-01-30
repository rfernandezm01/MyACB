import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ACBMenu {
	private int option;

	public ACBMenu() {
		super();
	}

	public int mainMenu() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		do {

			System.out.println(" \nMENU PRINCIPAL \n");

			System.out.println("1. Mostra Región");
			System.out.println("2. Mostra Personajes");
			System.out.println("3. Mostra Armas");
			System.out.println("4. Crea Tabla Región");
			System.out.println("5. Crea Tabla Personaje");
			System.out.println("6. Crea Tabla Armas");
			System.out.println("7. Mostra Personaje de una Región");
			System.out.println("8. Crear Personaje");
			System.out.println("9. Crear Region");
			System.out.println("10. Crear Arma");
			System.out.println("11. Sortir");
			System.out.println("Esculli opció: ");
			try {
				option = Integer.parseInt(br.readLine());
			} catch (NumberFormatException | IOException e) {
				System.out.println("valor no vàlid");
				e.printStackTrace();

			}

		} while (option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 6 && option != 7
				&& option != 8 && option != 9 && option != 10);

		return option;
	}


}
