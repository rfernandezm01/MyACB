
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TeamController {

	private Connection connection;

	public TeamController(Connection connection) {
		this.connection = connection;
	}

	public void mostrarregion() throws SQLException, IOException {

		Statement st = connection.createStatement();
		ResultSet rs;

		rs = st.executeQuery("SELECT * FROM Region");
		while (rs.next()) {
			System.out.println("Name: " + rs.getString("name") + " " +
							   "Type: " + rs.getString("type") + " " +
							   "Country: " + rs.getString("country") + " " +
							   "City: " + rs.getString("city") + " " +
							   "Court name: " + rs.getString("court_name"));
		}

		rs.close();
		st.close();
	}
	public void mostrarpersonaje() throws SQLException, IOException {
		Statement st = connection.createStatement();
		ResultSet rs;

		rs = st.executeQuery("SELECT * FROM Personaje");
		while (rs.next()) {
			System.out.println("Name: " + rs.getString("name") + " " +
					"Type: " + rs.getString("type") + " " +
					"Country: " + rs.getString("country") + " " +
					"City: " + rs.getString("city") + " " +
					"Court name: " + rs.getString("court_name"));
		}

		rs.close();
		st.close();

	}

	public void mostrararmas() throws SQLException, IOException {
		Statement st = connection.createStatement();
		ResultSet rs;

		rs = st.executeQuery("SELECT * FROM Armas");
		while (rs.next()) {
			System.out.println("Name: " + rs.getString("name") + " " +
					"Type: " + rs.getString("type") + " " +
					"Country: " + rs.getString("country") + " " +
					"City: " + rs.getString("city") + " " +
					"Court name: " + rs.getString("court_name"));
		}

		rs.close();
		st.close();

	}

	public void crearRegion() throws SQLException, IOException {
		Statement st = connection.createStatement();
		ResultSet rs;

		st.executeUpdate("CREATE TABLE Region (RegionID integer PRIMARY KEY, Nombre varchar(30), Habitantes integer, Elemento varchar(20), Nombrearconte varchar(30), Mundo varchar(20), PersonajeID integer)" );

		st.executeUpdate("ALTER TABLE Personaje ADD CONSTRAINT connectPersonaje “+ “FOREIGN KEY (PersonajeID) REFERENCES Personaje(PersonajeID)");
		st.close();

	}

	public void crearPersonaje() throws SQLException, IOException {
		Statement st = connection.createStatement();
		ResultSet rs;

		st.executeUpdate("CREATE TABLE Personaje (PersonajeID integer PRIMARY KEY, Nombre varchar(30), Estado varchar(10), Numeroestrellas smallint, TipodeArma varchar(20), Elemento varchar(20), Sexo varchar(20), Cumpleaños date, Región varchar(30), ArmaID Integer, RegiónID integer)" );

		st.executeUpdate("ALTER TABLE Personaje ADD CONSTRAINT connectarma “+ “FOREIGN KEY (ArmaID) REFERENCES Armas(ArmaID)" );
		st.executeUpdate("ALTER TABLE Personaje ADD CONSTRAINT connectRegion “+ “FOREIGN KEY (RegionID) REFERENCES Region(RegionID)" );


		st.close();
	}
	public void crearArma() throws SQLException, IOException {
		Statement st = connection.createStatement();
		ResultSet rs;

		st.executeUpdate("CREATE TABLE Armas (ArmaID integer PRIMARY KEY, Tipodearma varchar(20), Nombre varchar(30), Numeroestrellas smallint, PuntosAtaque smallint, Personaje varchar(30), PersonajeID integer)" );

		st.executeUpdate("ALTER TABLE Personaje ADD CONSTRAINT connectPersonaje “+ “FOREIGN KEY (PersonajeID) REFERENCES Personaje(PersonajeID)");

		st.close();
	}

}
