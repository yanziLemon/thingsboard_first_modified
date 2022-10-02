package org.thingsboard.server.dao.kvdes;
import org.thingsboard.server.common.data.Kvdes;
import org.thingsboard.server.common.data.id.KvdesId;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.PageLink;

public interface KvdesService {

    //增.增改一体
    //(2/5)接口
    Kvdes saveKvdes(Kvdes kvdes);
    //查
    Kvdes findKvdesById(KvdesId Id);

    Kvdes getKvdesByIdentification(String identification);

//    删除
    void deleteKvdes(KvdesId Id);


//    (4/5)接口
    void deleteKvdesByIdentificaition(String identification);

    //通过
    //(3/5)接口
    PageData<Kvdes> getAllKvdeses(PageLink pageLink);


    //(1/5)接口
    Kvdes getKvdesByEName(String eName);
}
