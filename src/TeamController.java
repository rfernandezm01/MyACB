
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeamController {

	private Connection connection;

	public TeamController(Connection connection) {
		this.connection = connection;
	}

	public void DeleteRegion () throws SQLException {
		//esto borra las regiones dentro de la tabla Regiones

		ArrayList<ArrayList<String>> regiones =  readAllDataAtOnce("src/Regiónes.csv");

		String sql = "DELETE Region (RegionID,Nombre,Habitantes,Elemento,Nombrearconte,Mundo,PersonajeID) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement pst = connection.prepareStatement(sql);

		// Elimina la fila de las cabeceras
		regiones.remove(0);

		for (ArrayList<String> fila : regiones) {

			pst.clearParameters();
			pst.setInt(1, Integer.parseInt(fila.get(0)));
			pst.setString(2, fila.get(1));
			pst.setInt(3, Integer.parseInt(fila.get(2)));
			pst.setString(4, fila.get(3));
			pst.setString(5, fila.get(4));
			pst.setString(6, fila.get(5));
			pst.setInt(8, Integer.parseInt(fila.get(6)));
			pst.executeUpdate();

		}

		pst.close();
	}

	public void DeletePersonajes () throws SQLException {
		//esto borra los Personajes dentro de la tabla Personajes

		ArrayList<ArrayList<String>> personajes =  readAllDataAtOnce("src/personajes.csv");

		String sql = "DELETE Personaje (PersonajeID,Nombre,Numeroestrellas,TipodeArma,Elemento,Sexo,Región,ArmaID,RegiónID) VALUES (?,?,?,?,?,?,?,?,?)";
		PreparedStatement pst = connection.prepareStatement(sql);

		// Elimina la fila de las cabeceras
		personajes.remove(0);

		for (ArrayList<String> fila : personajes) {

			pst.clearParameters();
			pst.setInt(1, Integer.parseInt(fila.get(0)));
			pst.setString(2, fila.get(1));
			pst.setInt(3, Integer.parseInt(fila.get(2)));
			pst.setString(4, fila.get(3));
			pst.setString(5, fila.get(4));
			pst.setString(6, fila.get(5));
			pst.setString(7, fila.get(6));
			pst.setInt(8, Integer.parseInt(fila.get(7)));
			pst.setInt(9, Integer.parseInt(fila.get(8)));
			pst.executeUpdate();

		}

		pst.close();
	}

	public void DeleteArmas() throws SQLException, IOException {
		//esto borra las Armas dentro de la tabla Armas

		ArrayList<ArrayList<String>> armas =  readAllDataAtOnce("src/Armas.csv");

		String sql = "DELETE Armas (ArmaID,Tipodearma,Nombre,Numeroestrellas,PuntosAtaque,PersonajeID) VALUES (?,?,?,?,?,?)";
		PreparedStatement pst = connection.prepareStatement(sql);

		// Elimina la fila de las cabeceras
		armas.remove(0);

		for (ArrayList<String> fila : armas) {

			pst.clearParameters();
			pst.setInt(1, Integer.parseInt(fila.get(0)));
			pst.setString(2, fila.get(1));
			pst.setString(3, fila.get(2));
			pst.setInt(4, Integer.parseInt(fila.get(3)));
			pst.setInt(5, Integer.parseInt(fila.get(4)));
			pst.setInt(6, Integer.parseInt(fila.get(5)));
			pst.executeUpdate();

		}

		pst.close();
	}


	public void borrartodo() throws SQLException, IOException {
		//esto borra toda la base de datos.

		Statement st = connection.createStatement();

		try { st.executeUpdate("ALTER TABLE Personaje DROP CONSTRAINT connectPersonaje");}
		catch (SQLException e) {}
		try { st.executeUpdate("ALTER TABLE Personaje DROP CONSTRAINT connectarma");}
		catch (SQLException e) {}
		try { st.executeUpdate("ALTER TABLE Personaje DROP CONSTRAINT connectRegion");}
		catch (SQLException e) {}
		try { st.executeUpdate("DROP TABLE Personaje");} catch (SQLException e) {}
		try { st.executeUpdate("DROP TABLE Region");} catch (SQLException e) {}
		try { st.executeUpdate("DROP TABLE Armas");} catch (SQLException e) {}


		st.close();
	}

	public void borrarRegion() throws SQLException, IOException {
		//borra la tabla region

		Statement st = connection.createStatement();


		try { st.executeUpdate("DROP TABLE Region");} catch (SQLException e) {}


		st.close();
	}

	public void borrarPersonaje() throws SQLException, IOException {
		//borra la tabla personajes

		Statement st = connection.createStatement();


		try { st.executeUpdate("DROP TABLE Personaje");} catch (SQLException e) {}


		st.close();
	}
	public void borrarArmas() throws SQLException, IOException {
		//borra la tabla armas

		Statement st = connection.createStatement();


		try { st.executeUpdate("DROP TABLE Armas");} catch (SQLException e) {}


		st.close();
	}
	public void mostrarregion() throws SQLException, IOException {
		//muestra la tabla region

		Statement st = connection.createStatement();
		ResultSet rs;

		rs = st.executeQuery("SELECT * FROM Region");

		rs.close();
		st.close();
	}
	public void mostrarpersonaje() throws SQLException, IOException {
		//muestra la tabla personajes
		Statement st = connection.createStatement();
		ResultSet rs;

		rs = st.executeQuery("SELECT * FROM Personaje");

		rs.close();
		st.close();

	}

	public void mostrararmas() throws SQLException, IOException {
		//muestra la tabla armas
		Statement st = connection.createStatement();
		ResultSet rs;

		rs = st.executeQuery("SELECT * FROM Armas");

		rs.close();
		st.close();

	}

	public void crearRegion() throws SQLException, IOException {
		//creamos la tabla region
		Statement st = connection.createStatement();

		st.executeUpdate("CREATE TABLE Region (RegionID integer PRIMARY KEY, Nombre varchar(30), Habitantes integer, Elemento varchar(20), Nombrearconte varchar(30), Mundo varchar(20), PersonajeID integer)" );

		try { st.executeUpdate("ALTER TABLE Personaje ADD CONSTRAINT connectPersonaje + FOREIGN KEY (PersonajeID) REFERENCES Personaje(PersonajeID)");}
		catch (SQLException e) {}

		st.close();

	}

	public void crearPersonaje() throws SQLException, IOException {
		//creamos la tabla personajes
		Statement st = connection.createStatement();

		st.executeUpdate("CREATE TABLE Personaje (PersonajeID integer PRIMARY KEY, Nombre varchar(30),Numeroestrellas integer, TipodeArma varchar(20), Elemento varchar(20), Sexo varchar(20),Región varchar(30), ArmaID Integer, RegiónID integer)" );

		try { st.executeUpdate("ALTER TABLE Personaje ADD CONSTRAINT connectarma + FOREIGN KEY (ArmaID) REFERENCES Armas(ArmaID)");}
		catch (SQLException e) {}
		try { st.executeUpdate("ALTER TABLE Personaje ADD CONSTRAINT connectRegion + FOREIGN KEY (RegionID) REFERENCES Region(RegionID)");}
		catch (SQLException e) {}


		st.close();
	}
	public void crearArma() throws SQLException, IOException {
		//creamos la tabla armas
		Statement st = connection.createStatement();

		st.executeUpdate("CREATE TABLE Armas (ArmaID integer PRIMARY KEY, Tipodearma varchar(20), Nombre varchar(30), Numeroestrellas integer, PuntosAtaque integer, PersonajeID integer)" );
		try { st.executeUpdate("ALTER TABLE Personaje ADD CONSTRAINT connectPersonaje + FOREIGN KEY (PersonajeID) REFERENCES Personaje(PersonajeID)");}
		catch (SQLException e) {}


		st.close();
	}


	public void readPersonajesFromCSVAndInsert () throws SQLException {
		//creamos personajes dentro de la tabla personajes

		ArrayList<ArrayList<String>> personajes = readAllDataAtOnce("src/personajes.csv");

		String sql = "INSERT INTO Personaje (PersonajeID,Nombre,Numeroestrellas,TipodeArma,Elemento,Sexo,Región,ArmaID,RegiónID) VALUES (?,?,?,?,?,?,?,?,?)";
		PreparedStatement pst = connection.prepareStatement(sql);



		for (ArrayList<String> fila : personajes) {

			pst.clearParameters();
			pst.setInt(1, Integer.parseInt(fila.get(0)));
			pst.setString(2, fila.get(1));
			pst.setInt(3, Integer.parseInt(fila.get(2)));
			pst.setString(4, fila.get(3));
			pst.setString(5, fila.get(4));
			pst.setString(6, fila.get(5));
			pst.setString(7, fila.get(6));
			pst.setInt(8, Integer.parseInt(fila.get(7)));
			pst.setInt(9, Integer.parseInt(fila.get(8)));
			pst.executeUpdate();

		}

		pst.close();
	}

	public void readRegionFromCSVAndInsert () throws SQLException {
		//creamos las regiones dentro de la tabla regiones

		ArrayList<ArrayList<String>> regiones = readAllDataAtOnce("src/Regiónes.csv");

		String sql = "INSERT INTO Region (RegionID,Nombre,Habitantes,Elemento,Nombrearconte,Mundo,PersonajeID) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement pst = connection.prepareStatement(sql);



		for (ArrayList<String> fila : regiones) {

			pst.clearParameters();
			pst.setInt(1, Integer.parseInt(fila.get(0)));
			pst.setString(2, fila.get(1));
			pst.setInt(3, Integer.parseInt(fila.get(2)));
			pst.setString(4, fila.get(3));
			pst.setString(5, fila.get(4));
			pst.setString(6, fila.get(5));
			pst.setInt(8, Integer.parseInt(fila.get(6)));
			pst.executeUpdate();

		}

		pst.close();
	}
	//psql -h 192.168.22.126 -U usuario -d ddbb

	public void readArmasFromCSVAndInsert () throws SQLException {
		//creamos las armas dentro de la tabla armas

		ArrayList<ArrayList<String>> armas = readAllDataAtOnce("src/Armas.csv");

		String sql = "INSERT INTO Armas (ArmaID,Tipodearma,Nombre,Numeroestrellas,PuntosAtaque,PersonajeID) VALUES (?,?,?,?,?,?)";
		PreparedStatement pst = connection.prepareStatement(sql);


		for (ArrayList<String> fila : armas) {

			pst.clearParameters();
			pst.setInt(1, Integer.parseInt(fila.get(0)));
			pst.setString(2, fila.get(1));
			pst.setString(3, fila.get(2));
			pst.setInt(4, Integer.parseInt(fila.get(3)));
			pst.setInt(5, Integer.parseInt(fila.get(4)));
			pst.setInt(6, Integer.parseInt(fila.get(5)));
			pst.executeUpdate();

		}

		pst.close();
	}

	// Java code to illustrate reading a
// all data at once
	public ArrayList<ArrayList<String>> readAllDataAtOnce(String file)
	{
		ArrayList<ArrayList<String>> datos = new ArrayList<ArrayList<String>>();

		try {
			// Create an object of file reader
			// class with CSV file as a parameter.
			FileReader filereader = new FileReader(file);

			// create csvReader object and skip first Line
			CSVReader csvReader = new CSVReaderBuilder(filereader)
					.withSkipLines(1)
					.build();
			List<String[]> allData = csvReader.readAll();


			for (String[] row : allData) {
				ArrayList<String> fila = new ArrayList<String>();
				for (String cell : row) {
					fila.add(cell);
					System.out.print(cell + "\t");
				}
				System.out.println();
			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return datos;
	}

}
