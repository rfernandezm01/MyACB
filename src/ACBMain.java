import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

public class ACBMain {

	public static void main(String[] args) throws IOException, SQLException, ParseException {


		ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
		Connection c = connectionFactory.connect();

		TeamController teamController = new TeamController(c);
		PlayerController playerController = new PlayerController(c);

		//Ahora declaramos 3 variables que haran de contadores.

		int region = 0;
		int personaje = 0;
		int arma = 0;

		int valorpersonaje = 0;
		int valorregion = 0;
		int valorarma = 0;

		//Aqui enlazamos lo que esta en TeamController para que este borre toda la base de datos en cada inicio de programa.

		teamController.borrartodo();


		ACBMenu menu = new ACBMenu();


//		Connection conn = null;
//		Identity identity;
//		int option;
//		int intents = 0;
//		DBAccessor dbaccessor = new DBAccessor();
//		dbaccessor.init();
//		while (intents < 3 && conn == null) {
//			identity = menu.authenticate(intents);
//			// prova de test
//			identity.toString();
//
//			conn = dbaccessor.getConnection(identity);
//			intents++;
//		}

		//Aqui enlazamos el menu de mainmenu. Para que nos muestre el menu.
		int option = menu.mainMenu();
		while (option > 0 && option < 17) {
			switch (option) {
				case 1:
					// en esta opcion nos mostrara la tabla region pero eso si esta creada.
					if (region > 0) {
						teamController.mostrarregion();
					}else{
						System.out.println("No se ha encontrado ninguna región");
					}
					// dbaccessor.mostraAutors();
					break;

				case 2:
					// en esta opcion nos mostrara la tabla personaje pero eso si esta creada.
					if(personaje > 0){
						teamController.mostrarpersonaje();
					}else{
						System.out.println("No se ha encontrado ningun personaje creado");
					}

					// dbaccessor.mostraRevistes();
					break;

				case 3:
					// en esta opcion nos mostrara la tabla armas pero eso si esta creada.
					if(arma > 0) {
						teamController.mostrararmas();
					}else{
						System.out.println("No se ha encontrado ninguna arma registrada");
					}
					// dbaccessor.mostraRevistesArticlesAutors();
					break;

				case 4:
					//aqui creamos la tabla region.
					teamController.crearRegion();
					region = 1;

					// dbaccessor.altaAutor();
					break;

				case 5:
					//aqui creamos la tabla personaje.
					teamController.crearPersonaje();
					personaje = 1;
					// dbaccessor.altaRevista();
					break;

				case 6:
					//aqui creamos la tabla armas.
					teamController.crearArma();
					arma = 1;

					// dbaccessor.altaArticle();
					break;

				case 7:
					//aqui creamos los persoanjes que queramos dentro de la tabla personaje.
					if(personaje > 0) {

						teamController.readPersonajesFromCSVAndInsert();
						valorpersonaje =+ 1;

					}else{
						System.out.println("No se puede ejecutar esta opcion");
					}
					// dbaccessor.actualitzarTitolRevistes(conn);
					break;

				case 8:
					//aqui creamos las regiones que queramos dentro de la tabla regiones.
					if(region > 0) {

						teamController.readRegionFromCSVAndInsert();
						valorregion =+ 1;

					}else{
						System.out.println("No se puede ejecutar esta opcion");
					}

					// dbaccessor.afegeixArticleARevista(conn);
					break;

				case 9:
					//aqui creamos las armas que queramos dentro de la tabla armas
					if(arma > 0) {

						teamController.readArmasFromCSVAndInsert();
						valorarma =+ 1;

					}else{
						System.out.println("No se puede ejecutar esta opcion");
					}
					// dbaccessor.desassignaArticleARevista(conn);
					break;

				case 10:
					//aqui borramos los personjes dentro de la tabla personajes
					if(valorpersonaje > 0) {
						teamController.DeletePersonajes();
						valorpersonaje =- 1;

					}else{
						System.out.println("No existe nada para borrar");
					}
					break;

				case 11:
					//aqui borramos las regiones dentro de la tabla regiones
					if(valorregion > 0) {
						teamController.DeleteRegion();
						valorregion =- 1;

					}else{
						System.out.println("No existe nada para borrar");
					}
					break;
				case 12:
					if(valorarma > 0) {
						//aqui borramos las armas dentro de la tabla armas.
						teamController.DeleteArmas();
						valorarma =- 1;
					}else{
						System.out.println("No existe nada para borrar");
					}
					break;
				case 13:
					//aqui borramos la tabla personajes
					if(personaje > 0){
						teamController.borrarPersonaje();
						personaje = 0;
					}else{
						System.out.println("No existe ninguna tabla Personaje");
					}
					break;
				case 14:
					//aqui borramos la tabla regiones
					if(region > 0){
						teamController.borrarRegion();
						region = 0;
					}else{
						System.out.println("No existe ninguna tabla Region");
					}
					break;
				case 15:
					//aqui borramos la tabla armas
					if(arma > 0) {
						teamController.borrarArmas();
						arma = 0;

					}else{
						System.out.println("No existe ninguna tabla Armas");
					}
					break;
				case 16:
					//aqui borramos toda la base de datos.
					teamController.borrartodo();
					region = 0;
					arma = 0;
					personaje = 0;

					valorarma = 0;
					valorregion = 0;
					valorpersonaje = 0;
					break;
				case 17:
					//aqui podremos salir del programa.
					System.exit(0);
					break;
				default:
					//esto nos mostrara un texto por si no elegimos ninguna de las opciones disponibles.
					System.out.println("Introdueixi una de les opcions anteriors");
					break;

			}
			option = menu.mainMenu();
		}

	}

}

