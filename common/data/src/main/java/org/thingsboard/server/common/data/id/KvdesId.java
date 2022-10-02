/**
 * Copyright © 2016-2022 The Thingsboard Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.thingsboard.server.common.data.id;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.thingsboard.server.common.data.EntityType;

@ApiModel
public class KvdesId extends UUIDBased implements EntityId {


    //继承了UUIDBased，实现了EntityId

    private static final long serialVersionUID = 1L;

//    @JsonCreator
//    自定义反序列化，先放着
//    https://blog.csdn.net/fan1989800/article/details/119023852
    @JsonCreator
//    @JsonProperty
//    在反序列化的时候给属性重命名(多一个名字来识别)

//    该方法对父类的uuid 用 UUID id进行赋值
    public KvdesId(@JsonProperty("id") UUID id) {
        super(id);
    }


    //UUID.fromString()
    //根据标准字符串表示形式创建UUID
    //类似于UUID.fromString("46400000-8cc0-11bd-b43e-10d46e4ef14d");

    public static KvdesId fromString(String kvdesId) {
        return new KvdesId(UUID.fromString(kvdesId));
    }

    @Override
    @ApiModelProperty(position = 2, required = true, value = "string", example = "ATT", allowableValues = "ATT")
    public EntityType getEntityType() {
        return EntityType.KVDES;
    }
}
