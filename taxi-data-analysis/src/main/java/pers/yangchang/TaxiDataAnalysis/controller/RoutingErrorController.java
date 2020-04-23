package pers.yangchang.TaxiDataAnalysis.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;

/**
 * error路径配置
 * @author yangchang
 * @since 2020-01-13
 */
@Controller
public class RoutingErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    /**
     * 因为我使用的是thymeleaf,所以这里返回index，
     * 系统就默认去寻找templates文件夹下的index.html
     *
     * @return HTML文件名
     */
    public String handleError() {
        return "index";
    }
}
