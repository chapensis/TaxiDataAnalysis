package pers.yangchang.TaxiDataAnalysis.bean.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author yangchang
 */
@Data
public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "用户名不能为空")
    private String username;

    @NotNull(message = "密码不能为空")
    private String password;

    private List<String> roles;

    private String token;

    private String introduction;

    private String avatar;

    private String name;
}
