package pers.yangchang.TaxiDataAnalysis.controller;

import pers.yangchang.TaxiDataAnalysis.bean.vo.ResultVO;
import pers.yangchang.TaxiDataAnalysis.bean.vo.UserVO;
import pers.yangchang.TaxiDataAnalysis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author yangchang
 */
@Slf4j
@RestController
@RequestMapping(value = "/service/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登陆成功，需要把用户的token信息存到header中，
     *
     * @param user
     * @return
     */
    @PostMapping("login")
    public ResultVO login(@Validated @RequestBody UserVO user, HttpServletRequest request) {
        UserVO resultUser = userService.login(user);
        // 如果不存在，会创建一个session
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("user", resultUser);
        return ResultVO.success(resultUser, "用户登录成功");
    }

    /**
     * 查询用户信息
     * @param httpSession 存放在分布式redis中的session
     * @return
     */
    @GetMapping("info")
    public ResultVO info(HttpSession httpSession) {
        Object userSession = httpSession.getAttribute("user");
        if (userSession != null) {
            UserVO userVO = (UserVO) userSession;
            log.info("当前请求存在用户信息：" + userVO);
        } else {
            log.info("当前请求不存在用户信息");
        }
        UserVO resultUser = userService.getUserInfo();
        return ResultVO.success(resultUser, "查询用户信息成功");
    }
}
