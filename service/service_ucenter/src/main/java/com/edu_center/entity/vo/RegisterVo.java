package com.edu_center.entity.vo;
//封装注册的实体类,包含了用户实体+验证码
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RegisterVo {
    @ApiModelProperty(value = "昵称")
    private String nickname;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "验证码")
    private String code;
}
