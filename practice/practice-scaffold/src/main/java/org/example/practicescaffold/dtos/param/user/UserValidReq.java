package org.example.practicescaffold.dtos.param.user;

import org.example.practicescaffold.dtos.param.common.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ApiModel(value = "用户实体类")
@Data
public class UserValidReq {

    @ApiModelProperty("用户ID")
    @NotNull(message = "更新操作id不能为空", groups = Update.class)
    private Long id;

    @ApiModelProperty("用户姓名")
    @NotBlank(message = "姓名不能为空")
    private String name;

    @Min(value = 18, message = "年龄必须大于18")
    @NotNull(message = "年龄不能为空")
    private int age;

    @Email(message = "非法邮箱格式")
    private String email;

    @Length(min = 11, max = 11, message = "非法手机格式")
    @Pattern(regexp = "^1([34578]\\d{9})$", message = "非法手机范围")
    @NotBlank(message = "手机号不能为空")
    private String phone;
}
