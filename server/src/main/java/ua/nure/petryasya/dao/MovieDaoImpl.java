package ua.nure.petryasya.dao;

import ua.nure.petryasya.exception.DBLayerException;
import ua.nure.petryasya.model.Film;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDaoImpl extends DAO implements MovieDao {

    private static final String GET_FILM_BY_ID = "SELECT * FROM film WHERE id = ?";
    private static final String GET_FILMS = "SELECT * FROM film";
    private static final String DELETE_FILM = "DELETE FROM film where id = ?";
    private static final String INSERT_FILM = "INSERT INTO Film (name,description, year, viewed) values (?,?,?,?) ";
    private static final String UPDATE_FILM = "UPDATE Film set name = ?, description = ?, year = ?, viewed = ? WHERE id = ?";

    @Override
    public List<Film> getAll() {

        List<Film> films = new ArrayList<>();
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
    public Film get(int id) {
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
    public void insert(Film movie) {
        try {
            con = MySqlConnection.getWebConnection();
            int k = 1;
            pst = con.prepareStatement(INSERT_FILM);
            pst.setString(k++, movie.getName());
            pst.setString(k++, movie.getDescription());
            pst.setInt(k++, movie.getYear());
            pst.setBoolean(k++, movie.isViewed());
            pst.executeUpdate();
        } catch (SQLException e) {
            rollBack(con);
            throw new DBLayerException("Failed to insert film " + movie, e);
        } finally {
            commit(con);
        }
    }

    @Override
    public void update(Film movie) {
        try {
            con = MySqlConnection.getWebConnection();
            int k = 1;
            pst = con.prepareStatement(UPDATE_FILM);
            pst.setString(k++, movie.getName());
            pst.setString(k++, movie.getDescription());
            pst.setInt(k++, movie.getYear());
            pst.setBoolean(k++, movie.isViewed());
            pst.setInt(k++, movie.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            rollBack(con);
            throw new DBLayerException("Failed to insert film " + movie, e);
        } finally {
            commit(con);
        }
    }

    private Film extractFilm(ResultSet rs) {
        Film film = new Film();
        try {
            film.setId(rs.getInt("id"));
            film.setName(rs.getString("name"));
            film.setDescription(rs.getString("description"));
            film.setYear(rs.getInt("year"));
            film.setViewed(rs.getBoolean("viewed"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return film;
    }


    /*private Session session = HibernateUtil.getSessionFactory().openSession();

    @SuppressWarnings("unchecked")
    public List<Film> getAll() {
        Criteria criteria = session.createCriteria(Film.class);
        return criteria.list();
    }

    public Film get(int id) {
        return (Film) session.get(Film.class, id);
    }

    public void delete(int id) {
        Film movie = get(id);
        if (movie != null) {
            session.delete(movie);
        }
    }

    public void insert(Film movie) {
        session.persist(movie);
    }

    public void update(Film movie) {
        session.update(movie);
    }*/
}
