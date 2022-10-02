package org.thingsboard.server.dao.kvdes;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.thingsboard.server.common.data.Kvdes;
import org.thingsboard.server.common.data.id.KvdesId;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.PageLink;
import org.thingsboard.server.dao.device.DeviceDao;
import org.thingsboard.server.dao.model.sql.KvdesEntity;

import java.util.Optional;

import static org.thingsboard.server.dao.service.Validator.validatePageLink;
import static org.thingsboard.server.dao.service.Validator.validateString;


//？？
@Service
@Slf4j
public class KvdesServiceImpl implements KvdesService{

//    https://baike.baidu.com/item/@Autowired/4428719
    @Autowired
    private KvdesDao kvdesDao;

    public static final String ERROR_IDENTIFICATION = "error identification";

//    2/5
    @Override
    public Kvdes saveKvdes(Kvdes kvdes) {
        return doSaveKvdes(kvdes);
    }

    @Override
    public Kvdes findKvdesById(KvdesId Id) {
        return dofindKvdesById(Id);
    }


//    5/5
    //Optional是一个容器用来判空
    @Override
    public Kvdes getKvdesByIdentification(String identification) {
        log.trace("Executing getAllKvdeses, pageLink [{}]", identification);
        validateString(identification, ERROR_IDENTIFICATION + identification);
        Optional<Kvdes> kvdesOpt = kvdesDao.getKvdesByIdentification(identification);
        return kvdesOpt.orElse(null);
    }


    //    (1/5)
    @Override
    public Kvdes getKvdesByEName(String eName) {
        return doGetKvdesByEName(eName);
    }

//    (3/5)
    public PageData<Kvdes> getAllKvdeses(PageLink pageLink){
        log.trace("Executing getAllKvdeses, pageLink [{}]", pageLink);
        validatePageLink(pageLink);
        return kvdesDao.getAllKvdeses(pageLink);
    }


    @Override
    public void deleteKvdes(KvdesId Id) {
        dodeleteKvdesById(Id);
    }

//    （4/5>
    @Override
    public void deleteKvdesByIdentificaition(String identification) {
        log.trace("Executing getAllKvdeses, pageLink [{}]", identification);
        validateString(identification, ERROR_IDENTIFICATION + identification);
        kvdesDao.deleteKvdesByIdentificaition(identification);
    }


    public Kvdes doSaveKvdes(Kvdes kvdes){
        return kvdesDao.saveAndFlush(kvdes);
    }


    //加了Optional判空
//    采用Optional的最终目的是避免程序中出现null对象异常的情况，所以我们封装方法的时候可以采用Optional 作为方法的返回值类型，
//      具体情况还要学习，还没看明白，先把crud写完再睡
    public Kvdes dofindKvdesById(KvdesId Id){
        log.info("Executing findKvdesByKvdesId [{}]", Id);
        Optional<Kvdes> kvdesOpt = kvdesDao.findKvdesById(Id.getId());
        return kvdesOpt.orElse(null);
    }

    public Kvdes doGetKvdesByEName(String eName){
        log.info("Executing findKvdesByEname [{}]", eName);
        Optional<Kvdes> kvdesOpt = kvdesDao.getKvdesByEName(eName);
       return kvdesOpt.orElse(null);
    }

    public void dodeleteKvdesById(KvdesId Id){
        kvdesDao.deleteKvdes(Id);
    }

}
