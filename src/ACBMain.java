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

		int region = 0;
		int personaje = 0;
		int arma = 0;

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

		int option = menu.mainMenu();
		while (option > 0 && option < 12) {
			switch (option) {
			case 1:
				if (region < 0) {
					teamController.mostrarregion();
				}else{
					System.out.println("No se ha encontrado ninguna región");
				}
				// dbaccessor.mostraAutors();
				break;

			case 2:
				if(personaje < 0){
					teamController.mostrarpersonaje();
				}else{
					System.out.println("No se ha encontrado ningun personaje creado");
				}

				// dbaccessor.mostraRevistes();
				break;

			case 3:
				if(arma < 0) {
					teamController.mostrararmas();
				}else{
					System.out.println("No se ha encontrado ninguna arma registrada");
				}
				// dbaccessor.mostraRevistesArticlesAutors();
				break;

			case 4:
				teamController.crearRegion();
				// dbaccessor.altaAutor();
				break;

			case 5:
				teamController.crearPersonaje();
				// dbaccessor.altaRevista();
				break;

			case 6:
				teamController.crearArma();
				// dbaccessor.altaArticle();
				break;

			case 7:
				teamController.readPersonajesFromCSVAndInsert();
				// dbaccessor.actualitzarTitolRevistes(conn);
				break;

			case 8:
				teamController.readRegionFromCSVAndInsert();
				// dbaccessor.afegeixArticleARevista(conn);
				break;

			case 9:
				teamController.readArmasFromCSVAndInsert();
				// dbaccessor.desassignaArticleARevista(conn);
				break;

			case 10:
				// dbaccessor.carregaAutors(conn);
				break;

			case 11:
				// dbaccessor.sortir();
				break;
				case 12:
					// dbaccessor.sortir();
					break;
				case 13:
					teamController.borrarPersonaje();
					break;
				case 14:
					teamController.borrarRegion();
					break;
				case 15:
					teamController.borrarArmas();
					break;
				case 16:
					teamController.borrartodo();
					break;
				case 17:
					System.exit(0);
					break;
			default:
				System.out.println("Introdueixi una de les opcions anteriors");
				break;

			}
			option = menu.mainMenu();
		}

	}

}
