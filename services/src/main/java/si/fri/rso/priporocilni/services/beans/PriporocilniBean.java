package si.fri.rso.priporocilni.services.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;

import si.fri.rso.priporocilni.lib.Priporocilni;
import si.fri.rso.priporocilni.models.converters.PriporocilniConverter;
import si.fri.rso.priporocilni.models.entities.PriporocilniEntity;


@RequestScoped
public class PriporocilniBean {

    private Logger log = Logger.getLogger(PriporocilniBean.class.getName());

    @Inject
    private EntityManager em;

    public List<Priporocilni> getKomentar() {

        TypedQuery<PriporocilniEntity> query = em.createNamedQuery(
                "PriporocilniEntity.getAll", PriporocilniEntity.class);

        List<PriporocilniEntity> resultList = query.getResultList();

        return resultList.stream().map(PriporocilniConverter::toDto).collect(Collectors.toList());

    }

    public List<Priporocilni> getKomentarFilter(UriInfo uriInfo) {

        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0)
                .build();

        return JPAUtils.queryEntities(em, PriporocilniEntity.class, queryParameters).stream()
                .map(PriporocilniConverter::toDto).collect(Collectors.toList());
    }

    public Priporocilni getKomentar(Integer id) {

        PriporocilniEntity priporocilniEntity = em.find(PriporocilniEntity.class, id);

        if (priporocilniEntity == null) {
            throw new NotFoundException();
        }

        Priporocilni priporocilni = PriporocilniConverter.toDto(priporocilniEntity);

        return priporocilni;
    }
    public List<Priporocilni> getKomentarByDestinacija(Integer id) {

        TypedQuery<PriporocilniEntity> query = em.createNamedQuery(
                "PriporocilniEntity.getByLokacijaId", PriporocilniEntity.class).setParameter("lokacija_id", id);

        List<PriporocilniEntity> resultList = query.getResultList();

        return resultList.stream().map(PriporocilniConverter::toDto).collect(Collectors.toList());

    }
    public List<Priporocilni> getKomentarByUser(Integer id) {

        TypedQuery<PriporocilniEntity> query = em.createNamedQuery(
                "PriporocilniEntity.getByUserId", PriporocilniEntity.class).setParameter("user_id", id);

        List<PriporocilniEntity> resultList = query.getResultList();

        return resultList.stream().map(PriporocilniConverter::toDto).collect(Collectors.toList());

    }
    public Priporocilni createKomentar(Priporocilni komentar) {

        PriporocilniEntity priporocilniEntity = PriporocilniConverter.toEntity(komentar);

        try {
            beginTx();
            em.persist(priporocilniEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        if (priporocilniEntity.getId() == null) {
            throw new RuntimeException("Entity was not persisted");
        }

        return PriporocilniConverter.toDto(priporocilniEntity);
    }

    public Priporocilni putKomentar(Integer id, Priporocilni komentar) {

        PriporocilniEntity c = em.find(PriporocilniEntity.class, id);

        if (c == null) {
            return null;
        }

        PriporocilniEntity updatedPriporocilniEntity = PriporocilniConverter.toEntity(komentar);

        try {
            beginTx();
            updatedPriporocilniEntity.setId(c.getId());
            updatedPriporocilniEntity = em.merge(updatedPriporocilniEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        return PriporocilniConverter.toDto(updatedPriporocilniEntity);
    }

    public boolean deleteKomentar(Integer id) {

        PriporocilniEntity imageMetadata = em.find(PriporocilniEntity.class, id);

        if (imageMetadata != null) {
            try {
                beginTx();
                em.remove(imageMetadata);
                commitTx();
            }
            catch (Exception e) {
                rollbackTx();
            }
        }
        else {
            return false;
        }

        return true;
    }

    private void beginTx() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}
