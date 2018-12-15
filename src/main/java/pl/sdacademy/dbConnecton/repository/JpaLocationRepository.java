package pl.sdacademy.dbConnecton.repository;

import org.hibernate.Hibernate;
import pl.sdacademy.dbConnecton.model.Location;
import pl.sdacademy.dbConnecton.service.repository.LocationRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class JpaLocationRepository implements LocationRepository {
    @Override
    public Optional<Location> find(String rackSymbol, String shelfSymbol, Long position) {
        EntityManager entityManager = HibernateEntityManagerFactory.get().createEntityManager();
        entityManager.getTransaction().begin();
        TypedQuery<Location> locationQuery =
                entityManager.createQuery("SELECT loc FROM Location loc " +
                "WHERE loc.rackSymbol =:rackSymbol " + "AND loc.shelfSymbol = :shelfSymbol " +
                        "AND loc.position = :myPosition",
                         Location.class);
        locationQuery.setParameter("rackSymbol", rackSymbol);
        locationQuery.setParameter("shelfSymbol", shelfSymbol);
        locationQuery.setParameter("myPosition", position);
        locationQuery.setMaxResults(1);
        Location foundLocation = locationQuery.getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return Optional.ofNullable(foundLocation);
    }

    @Override
    public Optional<Location> findById(Long id) {
        EntityManager entityManager =
                HibernateEntityManagerFactory.get().createEntityManager();
        entityManager.getTransaction().begin();

        Location foundLocation = entityManager.find(Location.class, id);


        entityManager.getTransaction().commit();
        entityManager.close();
        return Optional.ofNullable(foundLocation);
    }

    @Override
    public Location save(Location entity) {
        return null;
    }

    @Override
    public Location update(Location entity) {
        return null;
    }
}
