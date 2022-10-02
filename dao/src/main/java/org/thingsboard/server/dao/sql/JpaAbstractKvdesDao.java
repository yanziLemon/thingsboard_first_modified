package org.thingsboard.server.dao.sql;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.thingsboard.server.dao.Dao;
import org.thingsboard.server.dao.DaoUtil;
import org.thingsboard.server.dao.model.BaseEntity;

import java.util.UUID;


//这个文件是我自己写的
//因为JpaAbstractDao里面的save方法是有tenantid的，所以我在这里
//创建一个新文件然后把它去掉
@Slf4j
public abstract  class JpaAbstractKvdesDao <E extends BaseEntity<D>, D>
        extends JpaAbstractDaoListeningExecutorService
        implements Dao<D> {


    protected abstract Class<E> getEntityClass();

    protected abstract CrudRepository<E, UUID> getCrudRepository();

    protected void setSearchText(E entity) {
    }


    @Transactional
    public D save(D domain) {
        E entity;
        try {
            entity = getEntityClass().getConstructor(domain.getClass()).newInstance(domain);
        } catch (Exception e) {
            log.error("Can't create entity for domain object {}", domain, e);
            throw new IllegalArgumentException("Can't create entity for domain object {" + domain + "}", e);
        }
        setSearchText(entity);
        log.debug("Saving entity {}", entity);
        if (entity.getUuid() == null) {
            UUID uuid = Uuids.timeBased();
            entity.setUuid(uuid);
//            entity.setCreatedTime(Uuids.unixTimestamp(uuid));
        }
        entity = getCrudRepository().save(entity);
        return DaoUtil.getData(entity);
    }



    @Transactional
    public boolean removeById(UUID id) {
        getCrudRepository().deleteById(id);
        log.debug("Remove request: {}", id);
        return !getCrudRepository().existsById(id);
    }

}
