package org.thingsboard.server.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.thingsboard.server.common.data.Kvdes;
import org.thingsboard.server.common.data.EntityType;
import org.thingsboard.server.common.data.Kvdes;
import org.thingsboard.server.common.data.audit.ActionType;
import org.thingsboard.server.common.data.exception.ThingsboardException;
import org.thingsboard.server.common.data.id.KvdesId;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.PageLink;
import org.thingsboard.server.dao.kvdes.KvdesService;
import org.thingsboard.server.dao.model.sql.KvdesEntity;
import org.thingsboard.server.queue.util.TbCoreComponent;
import org.thingsboard.server.service.security.permission.Operation;
import org.thingsboard.server.service.security.permission.Resource;

import static org.thingsboard.server.controller.ControllerConstants.*;
import static org.thingsboard.server.dao.service.Validator.validateId;


@RestController
@TbCoreComponent
@RequestMapping("/api")
@Slf4j
public class KvdesController extends BaseController {


        //        @Autowired
//        注释，它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作。
//        通过 @Autowired的使用来消除 set ，get方法。在使用@Autowired之前，
//        我们对一个bean配置起属性时，是这用用的
        //不懂
        protected static final String KVDES_ID = "kvdesId";
        protected static final String E_NAME = "eName";
        protected static final String IDENTIFICATION = "identification";
        //        protected static final String ENAME = "eName";
        //不知道为什么要加这个Autowired
        @Autowired
        private KvdesService kvdesService;


        @ApiOperation(value = "Get Kvdes (getKvdesById)",
                notes = "Fetch the Kvdes object based on the provided KvdesId. ")
        @PreAuthorize("hasAnyAuthority('TENANT_ADMIN', 'CUSTOMER_USER')")
        @RequestMapping(value = "/kvdes/{kvdesId}", method = RequestMethod.GET)
        @ResponseBody
        public Kvdes getKvdesById(@ApiParam(value = "写个id")
                                          //value内的内容是对输入id的说明
                                          @PathVariable(KVDES_ID) String strKvdesId) throws ThingsboardException {

                checkParameter(KVDES_ID, strKvdesId);
                try {
                        KvdesId kvdesid = new KvdesId(toUUID(strKvdesId));
                        return checkKvdesId(kvdesid);
                } catch (Exception e) {
                        throw handleException(e);
                        //不懂这个
                }
        }



        Kvdes checkKvdesId(KvdesId kvdesId) throws ThingsboardException {
                validateId(kvdesId, "Incorrect kvdesId " + kvdesId);
                return checkNotNull(kvdesService.findKvdesById(kvdesId));
        }


//        (5/5) 接口
        @ApiOperation(value = "Get Kvdes (getKvdesByIdentification)",
                notes = "Fetch the Kvdes object based on the provided KvdesIdentification. ")
        @PreAuthorize("hasAnyAuthority('TENANT_ADMIN', 'CUSTOMER_USER')")
        @RequestMapping(value = "/kvdes/getbyidentification/{identification}", method = RequestMethod.GET)
        @ResponseBody

        public Kvdes getKvdesByIdentification(@ApiParam(value = "输入Identification")
                                  //value内的内容是对输入id的说明
                                  @PathVariable(IDENTIFICATION) String identification) throws ThingsboardException {

                checkParameter(IDENTIFICATION, identification);
                try {
                        return kvdesService.getKvdesByIdentification(identification);
                } catch (Exception e) {
                        throw handleException(e);
                        //不懂这个
                }
        }





        //getKvdesByEName接口，（1/5)
        @ApiOperation(value = "通过eName获取对应信息 (getKvdesByEName)",
                notes = "Fetch the Kvdes object based on the provided EName ")
        @PreAuthorize("hasAnyAuthority('TENANT_ADMIN', 'CUSTOMER_USER')")
        @RequestMapping(value = "/kvdes/byename/{eName}", method = RequestMethod.GET)
        //最新发现:/kvdes/ 这个/后面的内容要 因为有了两个get,可能他不知道
        //是走哪个路径,所以我们需要后面加个不一样的路径和上面的getid区分开来

        //RequestMappingvalue /kvdes/ 这个/后面的内容要和 pathvariable小括号里面的
        //内容相同,E_NAME变量在类开头定义了E_NAME = eName
        @ResponseBody
        public Kvdes getKvdesByEName(@ApiParam(value = "这里写eName")
                                  //value内的内容是对输入id的说明
                            @PathVariable(E_NAME) String eName) throws ThingsboardException {

                checkParameter(E_NAME, eName);
                //判空

                return kvdesService.getKvdesByEName(eName);

        }


//        //getAllKvdes接口，（2/5)
@ApiOperation(value = "Get All Kvdeses",
        notes = "Returns a page of kvdeses " )
@PreAuthorize("hasAnyAuthority('TENANT_ADMIN', 'CUSTOMER_USER')")
@RequestMapping(value = "/all/kvdeses", params = {"pageSize", "page"}, method = RequestMethod.GET)
@ResponseBody
        public PageData<Kvdes> getAllKvdeses(
                //等会把这些字符串换成常量名
                @ApiParam(value = PAGE_SIZE_DESCRIPTION, required = true)
                @RequestParam int pageSize,
                @ApiParam(value = PAGE_NUMBER_DESCRIPTION, required = true)
                @RequestParam int page,
                @ApiParam(value = "The case insensitive 'substring' filter based on the kvdes name.")
                @RequestParam(required = false) String textSearch,
                @ApiParam(value = SORT_PROPERTY_DESCRIPTION, allowableValues = "type, att_type")
                //这里记得去修改，还没想好怎么填
                @RequestParam(required = false) String sortProperty,
                @ApiParam(value = SORT_ORDER_DESCRIPTION, allowableValues = SORT_ORDER_ALLOWABLE_VALUES)
                @RequestParam(required = false) String sortOrder) throws ThingsboardException {

        PageLink pageLink = createPageLink(pageSize, page, textSearch, sortProperty, sortOrder);

//                if (type != null && type.trim().length() > 0) {
        //这里等会记得判断加上
                        return checkNotNull(kvdesService.getAllKvdeses(pageLink));
//                }
        }

        //是的，多学点总归好，对的
//        就是这点可能炸
//        别的还挺好的
//        ok 好像报错了


//        //save接口，(3/5)

        @ApiOperation(value = "Create Or Update Kvdes (saveKvdes)",
                notes = "Create Or Update Kvdes (saveKvdes)")
        @PreAuthorize("hasAnyAuthority('TENANT_ADMIN', 'CUSTOMER_USER')")
        @RequestMapping(value = "/kvdes", method = RequestMethod.POST)
//        @RequestMapping
//               用于方法上，表示在类的父路径下追加方法上注解中的地址将会访问到该方法
        @ResponseBody
//        @ResponseBody
//              @RequestBody和@RequestParam主要是用来接收前端传给后端的json数据。
//        一般来说，@RequestBody注解一般使用在post请求中，因为前端将json数据放在了请求体中。
//        在后端接收的方法里@RequestBody可以和@RequestParam同时使用，但一个方法里
//        ，@RequestBody只能有一个，@RequestParam可以有多个
//        。@RequestParam使用在GET请求方式中，可以接收普通元素、数组、集合、对象等。
//        @PathVariable
//              @RequestParam 和 @PathVariable 注解是用于从request中接收请求的，两个都可以接收参数，
//        关键点不同的是@RequestParam 是从request里面拿取值，而 @PathVariable 是从一
//        个URI模板里面来填充
        public Kvdes saveKvdes(@ApiParam(value = "这里面是描述kvdes的json") @RequestBody Kvdes kvdes) throws ThingsboardException {


                Kvdes savedKvdes = kvdesService.saveKvdes(checkNotNull(kvdes));
                return savedKvdes;
        }


//        //deletebyIdentification接口，(3/5)
        @ApiOperation(value = "Delete kvdes by identification",
                notes = "Deletes the kvdes.")
        @RequestMapping(value = "/kvdes/identification/{identification}", method = RequestMethod.DELETE)
        @PreAuthorize("hasAnyAuthority('TENANT_ADMIN', 'CUSTOMER_USER')")
        @ResponseStatus(value = HttpStatus.OK)

        public void deleteKvdesByIdentificaition(@ApiParam(value = "输入Identification")
                                                @PathVariable(IDENTIFICATION) String identification) throws ThingsboardException {

                checkParameter(IDENTIFICATION, identification);

                kvdesService.deleteKvdesByIdentificaition(identification);

        }










        @ApiOperation(value = "Delete kvdes (deleteKvdes)",
                notes = "Deletes the kvdes.")
        @RequestMapping(value = "/kvdes/{kvdesId}", method = RequestMethod.DELETE)
        @PreAuthorize("hasAnyAuthority('TENANT_ADMIN', 'CUSTOMER_USER')")
        @ResponseStatus(value = HttpStatus.OK)
        public void deleteKvdesById(@ApiParam(value = "写个id")
                                @PathVariable(KVDES_ID) String strKvdesId) throws ThingsboardException {
                KvdesId kvdesid = new KvdesId(toUUID(strKvdesId));
                kvdesService.deleteKvdes(kvdesid);
        }


}