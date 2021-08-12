package ir.maktab56.jpa.base.repository;

import ir.maktab56.jpa.base.domain.BaseEntity;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public interface BaseEntityRepository<E extends BaseEntity<ID>, ID extends Serializable> {

    E save(E e);

    E findById(ID id);

    List<E> findAll();

    void delete(E e);

    boolean existsById(ID id);

    Long countAll();

    EntityManager getEntityManager();
}
