package ua.nure.petryasya.dao.jdbcImpl;

import ua.nure.petryasya.constants.DBFields;
import ua.nure.petryasya.dao.DAO;
import ua.nure.petryasya.dao.MovieDao;
import ua.nure.petryasya.dao.MySqlConnection;
import ua.nure.petryasya.exception.DBLayerException;
import ua.nure.petryasya.model.Movie;
import ua.nure.petryasya.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDaoJDBCImpl extends DAO implements MovieDao {

    private static final String GET_FILM_BY_ID = "SELECT * FROM movie WHERE id = ?";
    private static final String GET_FILMS = "SELECT * FROM movie";
    private static final String DELETE_FILM = "DELETE FROM movie where id = ?";
    private static final String INSERT_FILM = "INSERT INTO movie (name,description, year, viewed) values (?,?,?,?) ";
    private static final String UPDATE_FILM = "UPDATE movie set name = ?, description = ?, year = ?, viewed = ? WHERE id = ?";
    private static final String GET_MOVIE_USER = "SELECT * FROM movie WHERE id IN (SELECT id_movie FROM movie_user WHERE id_user = ?)";

    @Override
    public List<Movie> getAll() {

        List<Movie> films = new ArrayList<>();
        try {
            con = MySqlConnection.getWebConnection();
            pst = con.prepareStatement(GET_FILMS);
            rs = pst.executeQuery();
            while (rs.next()) {
                films.add(extractFilm(rs));
            }
            return films;
        } catch (SQLException e) {
            rollBack(con);
            throw new DBLayerException("Failed to get films", e);
        } finally {
            commit(con);
        }
    }

    @Override
    public List<Movie> getByUser(int idUser) {
        List<Movie> movies = new ArrayList<>();
        try {
            con = MySqlConnection.getWebConnection();
            pst = con.prepareStatement(GET_MOVIE_USER);
            pst.setInt(FIRST, idUser);
            rs = pst.executeQuery();
            while (rs.next()) {
                movies.add(extractFilm(rs));
            }
            return movies;
        } catch (SQLException e) {
            rollBack(con);
            throw new DBLayerException("Failed to get movies with idUser = " + idUser, e);
        } finally {
            commit(con);
        }
    }

    @Override
    public Movie get(int id) {
        try {
            con = MySqlConnection.getWebConnection();
            pst = con.prepareStatement(GET_FILM_BY_ID);
            pst.setInt(FIRST, id);
            rs = pst.executeQuery();
            rs.relative(FIRST);
            return extractFilm(rs);
        } catch (SQLException e) {
            rollBack(con);
            throw new DBLayerException("Failed to get film with id = " + id, e);
        } finally {
            commit(con);
        }
    }

    @Override
    public void delete(int id) {
        try {
            con = MySqlConnection.getWebConnection();
            pst = con.prepareStatement(DELETE_FILM);
            pst.setInt(FIRST, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            rollBack(con);
            throw new DBLayerException("Failed to delete film with id = " + id, e);
        } finally {
            commit(con);
        }
    }

    @Override
    public void insert(Movie movie) {
        try {
            con = MySqlConnection.getWebConnection();
            int k = 1;
            pst = con.prepareStatement(INSERT_FILM);
            pst.setString(k++, movie.getName());
            pst.setString(k++, movie.getDescription());
            pst.setInt(k++, movie.getYear());
            pst.executeUpdate();
        } catch (SQLException e) {
            rollBack(con);
            throw new DBLayerException("Failed to insert film " + movie, e);
        } finally {
            commit(con);
        }
    }

    @Override
    public void update(Movie movie) {
        try {
            con = MySqlConnection.getWebConnection();
            int k = 1;
            pst = con.prepareStatement(UPDATE_FILM);
            pst.setString(k++, movie.getName());
            pst.setString(k++, movie.getDescription());
            pst.setInt(k++, movie.getYear());
            pst.setInt(k++, movie.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            rollBack(con);
            throw new DBLayerException("Failed to insert film " + movie, e);
        } finally {
            commit(con);
        }
    }

    private Movie extractFilm(ResultSet rs) {
        Movie film = new Movie();
        try {
            film.setId(rs.getInt(DBFields.ID));
            film.setName(rs.getString(DBFields.Movie.NAME));
            film.setDescription(rs.getString(DBFields.Movie.DESCRIPTION));
            film.setYear(rs.getInt(DBFields.Movie.YEAR));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return film;
    }

}
