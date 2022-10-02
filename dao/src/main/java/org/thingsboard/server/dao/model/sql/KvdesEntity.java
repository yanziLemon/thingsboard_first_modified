package org.thingsboard.server.dao.model.sql;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.thingsboard.server.common.data.Kvdes;
import org.thingsboard.server.dao.model.ModelConstants;
import org.thingsboard.server.dao.util.mapping.JsonBinaryType;
import org.thingsboard.server.dao.util.mapping.JsonStringType;

import javax.persistence.Entity;
import javax.persistence.Table;


//绑定表
//KVDES_COLUMN_FAMILY_NAME在ModelConsants 声明为 Kvdes
//    @Table(name = ModelConstants.KVDES_COLUMN_FAMILY_NAME)


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})

@Table(name = ModelConstants.KVDES_COLUMN_FAMILY_NAME)
public final class KvdesEntity extends AbstractKvdesEntity<Kvdes>{

        public KvdesEntity(){
                super();
        }

        public KvdesEntity(Kvdes kvdes) {
                super(kvdes);
        }


        @Override
        public Kvdes toData() {
                return super.toKvdes();
        }

        @Override
        public long getCreatedTime() {
                return 0;
        }

        @Override
        public void setCreatedTime(long createdTime) {

        }
}

//}
