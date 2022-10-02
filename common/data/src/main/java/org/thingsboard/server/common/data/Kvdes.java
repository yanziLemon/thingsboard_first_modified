package org.thingsboard.server.common.data;

//这里需要org.thingsboard.server.common.data里面写个Kvdes包

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.thingsboard.server.common.data.SearchTextBasedWithAdditionalInfo;
import org.thingsboard.server.common.data.id.KvdesId;
import org.thingsboard.server.common.data.validation.Length;
import org.thingsboard.server.common.data.validation.NoXss;

import java.util.Optional;
import java.util.UUID;

//--------@EqualsAndHashCode(callSuper = true)
//根据子类自身的字段值和从父类继承的字段值 来生成hashcode
// ，当两个子类对象比较时，只有子类对象的本身的字段值
// 和继承父类的字段值都相同，equals方法的返回值是true。
//https://blog.csdn.net/dj1955/article/details/123822789
@ApiModel
@EqualsAndHashCode(callSuper = true)
@Slf4j
public class Kvdes extends SearchTextBased<KvdesId> {



  private static final long serialVersionUID = 2807343040519543363L;
//  @NoXss
//    给字符串注解，防止网络攻击


    private UUID id;
  @NoXss
//  @Length(min=, max=) 验证字符串长度是否在给定的范围之内
  //长度要小于等于""内这个字段的长度

  @Length(fieldName = "attIdentification")
  private String attIdentification;
  @NoXss
  @Length(fieldName = "attName")
  private String attName;
  @NoXss
  @Length(fieldName = "attType")
  private String attType;
  //这里我给他改了一下，去掉了注解
//  @Length(fieldName = "")
  private int attAccuracy;
  @NoXss
  @Length(fieldName = "attUnit")
  private String attUnit;

  private Boolean attReadOnly;
  @NoXss
  @Length(fieldName = "attDescribe")
  private String attDescribe;
  @NoXss
  @Length(fieldName = "attGroup")
  private String attGroup;
  @NoXss
  @Length(fieldName = "eName")
  private String eName;
  @NoXss
  @Length(fieldName = "type")
  private String type;

  @NoXss
  @Length(fieldName = "searchText")
  private String searchText;

// long createdTime;

  public Kvdes()  { super();}


  //对IDbase里面的id进行初始化
  public Kvdes(KvdesId id)  { super(id);}

  public Kvdes(Kvdes kvdes) {
      super(kvdes);
      //设置一些附加信息
      //初始化BaseData的createdtime和IDbase里面的id

      this.attIdentification = kvdes.getAttIdentification();
      this.attName = kvdes.getAttName();
      this.attType = kvdes.getAttType();
      this.attAccuracy = kvdes.getAttAccuracy();
      this.attUnit = kvdes.getAttUnit();
      this.attReadOnly = kvdes.isAttReadOnly();
      this.attDescribe = kvdes.getAttDescribe();
      this.attGroup = kvdes.getAttGroup();
      this.eName = kvdes.getEName();
      this.type = kvdes.getType();
      this.searchText = kvdes.getSearchText();
  }


  public Kvdes updateKvdes(Kvdes kvdes) {
    this.attIdentification = kvdes.getAttIdentification();
    this.attName = kvdes.getAttName();
    this.attType = kvdes.getAttType();
    this.attAccuracy = kvdes.getAttAccuracy();
    this.attUnit = kvdes.getAttUnit();
    this.attReadOnly = kvdes.isAttReadOnly();
    this.attDescribe = kvdes.getAttDescribe();
    this.attGroup = kvdes.getAttGroup();
    this.eName = kvdes.getEName();
    this.type = kvdes.getType();
    this.searchText = kvdes.getSearchText();
    return this;
  }


  @Override
  public KvdesId getId() {
    return super.getId();
  }


  @Override
  public String getSearchText() {
    return getAttName();
  }

  public void setSearchText(String searchText)   { this.searchText = searchText;}


  public String getAttIdentification() {
    return attIdentification;
  }

  public void setAttIdentification(String attIdentification) {
    this.attIdentification = attIdentification;
  }


  public String getAttName() {
    return attName;
  }

  public void setAttName(String attName) {
    this.attName = attName;
  }


  public String getAttType() {
    return attType;
  }

  public void setAttType(String attType) {
    this.attType = attType;
  }


  public int getAttAccuracy() {
    return attAccuracy;
  }

  public void setAttAccuracy(int attAccuracy) {
    this.attAccuracy = attAccuracy;
  }


  public String getAttUnit() {
    return attUnit;
  }

  public void setAttUnit(String attUnit) {
    this.attUnit = attUnit;
  }


  public Boolean isAttReadOnly() {
    return attReadOnly;
  }

  public void setAttReadOnly(Boolean attReadOnly) {
    this.attReadOnly = attReadOnly;
  }


  public String getAttDescribe() {
    return attDescribe;
  }

  public void setAttDescribe(String attDescribe) {
    this.attDescribe = attDescribe;
  }



  public String getAttGroup() {
    return attGroup;
  }

  public void setAttGroup(String attGroup) {
    this.attGroup = attGroup;
  }


  public String getEName() {
    return eName;
  }

  public void setEName(String eName) {
    this.eName = eName;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  @Override
  public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("Kvdes [att_identification=");
      builder.append(attIdentification);
      builder.append(", att_name=");
      builder.append(attName);
      builder.append(", att_type=");
      builder.append(attType);
      builder.append(", att_accuracy=");
      builder.append(attAccuracy);
      builder.append(", att_unit=");
      builder.append(attUnit);
      builder.append(", att_read_only=");
      builder.append(attReadOnly);
      builder.append(", att_describe=");
      builder.append(attDescribe);
      builder.append(", id=");
      builder.append(id);
      builder.append(", att_Group=");
      builder.append(attGroup);
      builder.append(", e_name=");
      builder.append(eName);
      builder.append(", type=");
      builder.append(type);
      builder.append("]");
      return builder.toString();
  }

}
