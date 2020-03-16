package pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "ResultIdCard对象",description = "身份证识别返回对象ResultIdCard对象")
public class ResultIdCard {

   @ApiModelProperty(value = "返回状态码")
    private String code;

    @ApiModelProperty(value = "返回身份证信息")
    private IdCardVo data;

    @ApiModelProperty(value = "返回调用情况描述")
    private String desc;

    @ApiModelProperty(value = "返回sid")
    private String sid;
}
