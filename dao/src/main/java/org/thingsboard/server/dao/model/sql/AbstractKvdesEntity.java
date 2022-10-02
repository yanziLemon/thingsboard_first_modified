package org.thingsboard.server.dao.model.sql;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
//import org.springframework.data.annotation.Id;

//原因 这里的id包导入错误
import org.thingsboard.server.common.data.Kvdes;
import org.thingsboard.server.common.data.id.KvdesId;
import org.thingsboard.server.dao.model.BaseSqlEntity;
import org.thingsboard.server.dao.model.ModelConstants;
import org.thingsboard.server.dao.model.SearchTextEntity;
import org.thingsboard.server.dao.util.mapping.JsonBinaryType;
import org.thingsboard.server.dao.util.mapping.JsonStringType;

import javax.persistence.*;
import java.util.UUID;

//    @Data
//    @Data注解的主要作用是提高代码的简洁，
//    使用这个注解可以省去实体类中大量的get()、 set()、 toString()等方法。
@Data
//    @EqualsAndHashCode
//    此注解会生成equals(Object other) 和 hashCode()方法。
//1.    callSuper = true，根据子类自身的字段值和从父类继承的字段值
// 来生成hashcode，当两个子类对象比较时，只有子类对象的本身的字段值和继承父类的字段值都相同，equals方法的返回值是true。
//
//2.    callSuper = false，根据子类自身的字段值 来生成hashcode，
// 当两个子类对象比较时，只有子类对象的本身的字段值相同，父类字段值可以不同，equals方法的返回值是true。

//@EqualsAndHashCode(callSuper = true)
//     @TypeDef()
//       在自定义的Type中需要指定对应的属性名称、类型，以及相关取值设置以及格式化方法
//      还是不是很清楚 ？？？
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})


//这里不继承basesqlentity因为里面有绑定字段
@MappedSuperclass
//public abstract class AbstractKvdesEntity<T extends Kvdes> extends BaseSqlEntity<T> implements SearchTextEntity<T> {
public abstract class AbstractKvdesEntity<T extends Kvdes> implements SearchTextEntity<T> {


    //原因 这里的id包导入错误
    //应该是正确的应该是import javax.persistence.Id 而我却导入了org.springframework.data.annotation.Id
    //问题：我明明写了主键@Id但还是在继承类里面报错，现在把包导入包改了已经解决
    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)

    @Column(name = ModelConstants.KVDES_ID_PROPERTY)

    private UUID id;

    @Column(name = ModelConstants.KVDES_TYPE_PROPERTY)
    private String type;

    @Column(name = ModelConstants.KVDES_NAME_PROPERTY)
    private String attName;

    @Column(name = ModelConstants.SEARCH_TEXT_PROPERTY)
    private String searchText;

    @Column(name = ModelConstants.KVDES_ACCURACY_PROPERTY)
    private int attAccuracy;

    @Column(name = ModelConstants.KVDES_UNIT_PROPERTY)
    private String attUnit;

    @Column(name = ModelConstants.ATT_READ_ONLY)
    private Boolean attReadonly;

    @Column(name = ModelConstants.KVDES_DESCRIBE_PROPERTY)
    private String attDescribe;

    @Column(name = ModelConstants.KVDES_GROUP_PROPERTY)
    private String attGroup;

    @Column(name = ModelConstants.KVDES_ENAME_PROPERTY)
    private String eName;

    @Column(name = ModelConstants.KVDES_IDENTIFICATION_PROPERTY)
    private String attIdentification;

    @Column(name = ModelConstants.ATT_TYPE_PROPERTY)
    private String attType;


    public AbstractKvdesEntity() {
        super();
    }

    public AbstractKvdesEntity(Kvdes kvdes) {
        if(kvdes.getAttIdentification() != null) {
            this.attIdentification = kvdes.getAttIdentification();
        }
        if(kvdes.getAttName() != null) {
            this.attName = kvdes.getAttName();
        }
        if(kvdes.getAttType()!= null){
            this.type = kvdes.getAttType();
        }
        this.attAccuracy= kvdes.getAttAccuracy();
        if(kvdes.getAttUnit()!=null)
        {
            this.attUnit = kvdes.getAttUnit();
        }
        if(kvdes.isAttReadOnly()!=null)
        {
            this.attReadonly = kvdes.isAttReadOnly();
        }
        if(kvdes.getAttDescribe()!=null)
        {
            this.attDescribe = kvdes.getAttDescribe();
        }
        if(kvdes.getAttGroup()!=null) {
            this.attGroup = kvdes.getAttGroup();
        }
        if(kvdes.getEName()!=null){
            this.eName = kvdes.getEName();
        }
        if(kvdes.getSearchText()!=null) {
            this.searchText = kvdes.getSearchText();
        }

        if(kvdes.getAttType()!=null){
            this.attType = kvdes.getAttType();
        }
    }


    public AbstractKvdesEntity(KvdesEntity kvdesEntity) {
        this.setId(kvdesEntity.getId());
//        this.setCreatedTime(kvdesEntity.getCreatedTime());
        this.attIdentification = kvdesEntity.getAttIdentification();
        this.attName = kvdesEntity.getAttName();
        this.type = kvdesEntity.getType();
        this.attAccuracy= kvdesEntity.getAttAccuracy();
        this.attUnit = kvdesEntity.getAttUnit();
        this.attReadonly = kvdesEntity.getAttReadonly();
        this.attDescribe = kvdesEntity.getAttDescribe();
        this.attGroup = kvdesEntity.getAttGroup();
        this.eName = kvdesEntity.getEName();
        this.searchText = kvdesEntity.getSearchText();
        this.attType = kvdesEntity.getAttType();
        //searchtext 没有set 只有get， 是从父类继承的一个
    }


    protected Kvdes toKvdes() {
        Kvdes kvdes = new Kvdes(new KvdesId(getUuid()));
        kvdes.setAttIdentification(attIdentification);
        kvdes.setAttName(attName);
        kvdes.setType(type);
        kvdes.setAttAccuracy(attAccuracy);
        kvdes.setAttUnit(attUnit);
        kvdes.setAttReadOnly(attReadonly);
        kvdes.setAttDescribe(attDescribe);
        kvdes.setAttGroup(attGroup);
        kvdes.setEName(eName);
        kvdes.setAttType(attType);

        //searchtext不用set
        return kvdes;
    }


    public UUID getUuid() {
        return id;
    }
    public void setUuid(UUID id) {
        this.id = id;
    }

    @Override
    public String getSearchTextSource() {
        return attName;
    }
    @Override
    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

//    @Override
//    public void setCreatedTime(long createdTime) {
//        this.createdTime = createdTime;
//    }
//
//    @Override
//    public long getCreatedTime() {
//        return this.createdTime;
//    }
}
