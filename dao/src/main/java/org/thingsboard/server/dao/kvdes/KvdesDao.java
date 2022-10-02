package org.thingsboard.server.dao.kvdes;

import org.thingsboard.server.common.data.Kvdes;
import org.thingsboard.server.common.data.id.KvdesId;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.PageLink;
import org.thingsboard.server.dao.model.sql.KvdesEntity;

import java.util.UUID;
import java.util.Optional;

public interface KvdesDao {

    Kvdes saveAndFlush(Kvdes kvdes);

    Optional<Kvdes> findKvdesById(UUID Id);

    Optional<Kvdes> getKvdesByIdentification(String identification);

    void deleteKvdes(KvdesId Id);

    void deleteKvdesByIdentificaition(String identification);

    Optional<Kvdes> getKvdesByEName(String eName);

    PageData<Kvdes> getAllKvdeses(PageLink pageLink);
}
