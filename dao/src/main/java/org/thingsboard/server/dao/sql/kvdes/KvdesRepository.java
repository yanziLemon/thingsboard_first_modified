package org.thingsboard.server.dao.sql.kvdes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.thingsboard.server.common.data.Kvdes;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.PageLink;
import org.thingsboard.server.dao.model.sql.KvdesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.thingsboard.server.dao.model.sql.KvdesEntity;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public interface KvdesRepository extends JpaRepository<KvdesEntity, UUID> {


    //delete和update去用 CRUD

    //用@Param的方法来
    @Query("SELECT d from KvdesEntity d where d.id = :id")
    KvdesEntity findKvdesEntityById(@Param("id") UUID id);


//    5/5 接口/*/
    @Query("SELECT d from KvdesEntity d where d.attIdentification = :attIdentification")
    KvdesEntity getKvdesByIdentification(@Param("attIdentification") String attIdentification);


//    1/5 接口
    @Query("SELECT d from KvdesEntity d where d.eName = :eName")
    KvdesEntity getKvdesByEName(@Param("eName") String eName);

//    2/5接口调用了crud repository
//    3/5接口
    @Query("SELECT d from KvdesEntity d")
    Page<KvdesEntity> getAllKvdeses(
            Pageable pageable);
    //这里改成page，记得去看为什么？？

//    4/5接口

    @Transactional
    @Modifying
    @Query("Delete from KvdesEntity d where d.attIdentification = :attIdentification")
    void deleteKvdesByIdentificaition(@Param("attIdentification") String attIdentification);

}

//
//    在@Qurey中使用DELETE UPDATE 语句
//
//1、可用通过自定义JPQL来完成Update 和 Delete 操作,注意JPQL不支持Insert
//
//2、在使用@Query注解的时候,必须使用@Modifying注解从而来通知SpringData,这是一个Update 或者 Delete操作
//
