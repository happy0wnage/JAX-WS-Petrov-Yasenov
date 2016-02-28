package ua.nure.petryasya.dao.jdbcImpl;

import ua.nure.petryasya.constants.DBFields;
import ua.nure.petryasya.dao.DAO;
import ua.nure.petryasya.dao.MovieDao;
import ua.nure.petryasya.dao.MySqlConnection;
import ua.nure.petryasya.dao.UserDAO;
import ua.nure.petryasya.exception.DBLayerException;
import ua.nure.petryasya.model.Movie;
import ua.nure.petryasya.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDAOJDBCImpl extends DAO implements UserDAO {

    private static final String GET_USER = "SELECT * FROM user";
    private static final String GET_BY_ID = "SELECT * FROM user WHERE id = ?";
    private static final String DELETE_USER = "DELETE FROM user WHERE id = ?";
    private static final String INSERT_USER = "INSERT INTO user (email, password) values (?,?)";
    private static final String UPDATE_USER = "UPDATE USER set email = ?, password = ? WHERE id = ?";
    private static final String ADD_MOVIE = "INSERT INTO movie_user (id_movie, id_user) VALUES (?,?);";
    private static final String DELETE_MOVIE = "DELETE FROM movie_user WHERE id_movie = ? AND id_user = ?";

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try {
            con = MySqlConnection.getWebConnection();
            pst = con.prepareStatement(GET_USER);
            rs = pst.executeQuery();
            while (rs.next()) {
                User user = extractUser(rs);
                MovieDao movieDao = new MovieDaoJDBCImpl();
                Set<Movie> set = new HashSet<>(movieDao.getByUser(user.getId()));
                user.setMovieSet(set);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            rollBack(con);
            throw new DBLayerException("Failed to get users", e);
        } finally {
            commit(con);
        }
    }

    @Override
    public User get(int id) {
        try {
            con = MySqlConnection.getWebConnection();
            pst = con.prepareStatement(GET_BY_ID);
            pst.setInt(FIRST, id);
            rs = pst.executeQuery();
            rs.relative(FIRST);
            User user = extractUser(rs);
            MovieDao movieDao = new MovieDaoJDBCImpl();
            Set<Movie> set = new HashSet<>(movieDao.getByUser(user.getId()));
            user.setMovieSet(set);
            return user;
        } catch (SQLException e) {
            rollBack(con);
            throw new DBLayerException("Failed to get user with id = " + id, e);
        } finally {
            commit(con);
        }
    }

    @Override
    public void delete(int id) {
        con = MySqlConnection.getWebConnection();
        try {
            pst = con.prepareStatement(DELETE_USER);
            pst.setInt(FIRST, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            rollBack(con);
            throw new DBLayerException("Failed to delete user with id = " + id, e);
        } finally {
            commit(con);
        }
    }

    @Override
    public void insert(User user) {
        try {
            con = MySqlConnection.getWebConnection();
            int k = 1;
            pst = con.prepareStatement(INSERT_USER);
            pst.setString(k++, user.getEmail());
            pst.setString(k++, user.getPassword());
            pst.executeUpdate();
        } catch (SQLException e) {
            rollBack(con);
            throw new DBLayerException("Failed to insert user " + user, e);
        } finally {
            commit(con);
        }
    }

    @Override
    public void update(User user) {
        try {
            con = MySqlConnection.getWebConnection();
            int k = 1;
            pst = con.prepareStatement(UPDATE_USER);
            pst.setString(k++, user.getEmail());
            pst.setString(k++, user.getPassword());
            pst.setInt(k++, user.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            rollBack(con);
            throw new DBLayerException("Failed to update user " + user, e);
        } finally {
            commit(con);
        }
    }

    @Override
    public void addMovie(int idMovie, int idUser) {
        try {
            con = MySqlConnection.getWebConnection();
            int k = 1;
            pst = con.prepareStatement(ADD_MOVIE);
            pst.setInt(k++, idMovie);
            pst.setInt(k++, idUser);
            pst.executeUpdate();
        } catch (SQLException e) {
            rollBack(con);
            throw new DBLayerException("Failed to insert movie_user " + idMovie, e);
        } finally {
            commit(con);
        }
    }

    @Override
    public void deleteMovie(int idMovie, int idUser) {
        con = MySqlConnection.getWebConnection();
        try {
            int k = 1;
            pst = con.prepareStatement(DELETE_MOVIE);
            pst.setInt(k++, idMovie);
            pst.setInt(k++, idUser);
            pst.executeUpdate();
        } catch (SQLException e) {
            rollBack(con);
            throw new DBLayerException("Failed to delete movie_user with id = " + idMovie, e);
        } finally {
            commit(con);
        }
    }


    private User extractUser(ResultSet rs) {
        User user = new User();
        try {
            user.setId(rs.getInt(DBFields.ID));
            user.setEmail(rs.getString(DBFields.User.EMAIL));
            user.setPassword(rs.getString(DBFields.User.PASSWORD));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
