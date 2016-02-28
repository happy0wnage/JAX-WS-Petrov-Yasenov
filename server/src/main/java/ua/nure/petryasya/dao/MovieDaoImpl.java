package ua.nure.petryasya.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.nure.petryasya.configuration.HibernateUtil;
import ua.nure.petryasya.model.Movie;

import java.util.List;

public class MovieDaoImpl implements MovieDao{

    private Session getCurrentSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    @SuppressWarnings("unchecked")
    public List<Movie> getAll() {
        Criteria criteria =  getCurrentSession().createCriteria(Movie.class);
        return criteria.list();
    }

    public Movie get(int id) {
        return (Movie) getCurrentSession().get(Movie.class, id);
    }

    public void delete(int id) {
        Movie movie = get(id);
        if (movie != null)
            getCurrentSession().delete(movie);
    }

    public void insert(Movie movie) {
        getCurrentSession().persist(movie);
    }

    public void update(Movie movie) {
        getCurrentSession().update(movie);
    }
}
