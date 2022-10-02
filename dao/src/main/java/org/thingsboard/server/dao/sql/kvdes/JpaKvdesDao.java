package org.thingsboard.server.dao.sql.kvdes;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.thingsboard.server.common.data.Kvdes;
import org.thingsboard.server.common.data.id.KvdesId;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.PageLink;
import org.thingsboard.server.dao.Dao;
import org.thingsboard.server.dao.DaoUtil;
import org.thingsboard.server.dao.kvdes.KvdesDao;
import org.thingsboard.server.dao.model.sql.KvdesEntity;
import org.thingsboard.server.dao.sql.JpaAbstractDao;
import org.thingsboard.server.dao.sql.JpaAbstractKvdesDao;
import org.thingsboard.server.dao.sql.JpaAbstractSearchTextDao;

import java.util.*;

//??
@Component
@Slf4j
public class JpaKvdesDao extends JpaAbstractKvdesDao<KvdesEntity, Kvdes> implements KvdesDao {

    @Autowired
    private KvdesRepository kvdesRepository;


//    (3/5)
    @Override
    @Transactional
    //@Transactional注解在代码执行出错的时候能够进行事务的回滚。
    public Kvdes saveAndFlush(Kvdes kvdes) {
        Kvdes result = this.save(kvdes);
        kvdesRepository.flush();
        return result;
    }

    @Override
            //调用DaoUtil的getData方法，实参kvdesEntity
    //去调用自己的toData方法，然后再
    //会去调用abstractKvdesEntity的toKvdes方法
    public Optional<Kvdes> findKvdesById(UUID id) {
        Kvdes kvdes = DaoUtil.getData(kvdesRepository.findKvdesEntityById(id));
        return Optional.ofNullable(kvdes);
//          return DaoUtil.getData(kvdesRepository.findKvdesEntityById(id));
    }

    @Override
    public Optional<Kvdes> getKvdesByIdentification(String identification) {
        Kvdes kvdes = DaoUtil.getData(kvdesRepository.getKvdesByIdentification(identification));
        return Optional.ofNullable(kvdes);
    }

    //    (2/5)
    @Override
    public Optional<Kvdes> getKvdesByEName(String eName){
        Kvdes kvdes = DaoUtil.getData(kvdesRepository.getKvdesByEName(eName));
       return Optional.ofNullable(kvdes);
    }

//    (1/5)
    @Override
    public PageData<Kvdes> getAllKvdeses(PageLink pageLink) {
        return DaoUtil.toPageData(
                kvdesRepository.getAllKvdeses(
                        DaoUtil.toPageable(pageLink)));
    }

    @Override
    public void deleteKvdes(KvdesId Id) {
        this.removeById(Id.getId());
    }


//    (4/5)
    @Override
    public void deleteKvdesByIdentificaition(String identification) {
        kvdesRepository.deleteKvdesByIdentificaition(identification);
    }


    @Override
    //这个应该是获取 实体的class
    protected Class<KvdesEntity> getEntityClass() {
        return KvdesEntity.class;
    }

    @Override
    protected CrudRepository<KvdesEntity, UUID> getCrudRepository() {
        return kvdesRepository;
    }

    @Override
    public List<Kvdes> find(TenantId tenantId) {
        return null;
    }

    @Override
    public Kvdes findById(TenantId tenantId, UUID id) {
        return null;
    }

    @Override
    public ListenableFuture<Kvdes> findByIdAsync(TenantId tenantId, UUID id) {
        return null;
    }

    @Override
    public boolean existsById(TenantId tenantId, UUID id) {
        return false;
    }

    @Override
    public ListenableFuture<Boolean> existsByIdAsync(TenantId tenantId, UUID id) {
        return null;
    }

    @Override
    public Kvdes save(TenantId tenantId, Kvdes kvdes) {
        return null;
    }

    @Override
    public boolean removeById(TenantId tenantId, UUID id) {
        return false;
    }

    @Override
    public void removeAllByIds(Collection<UUID> ids) {

    }
}
