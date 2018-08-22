package com.dekuofa.config;

import com.dekuofa.constant.Constants;
import com.dekuofa.utils.IpKit;
import com.dekuofa.utils.SecurityUtil;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * @author gx <br>
 * @date 2018-08-22 <br>
 */
@ControllerAdvice
public class MvcControllerAdvice {

    @ModelAttribute
    public void addAttributed(Model model, HttpServletRequest request) {
        String token = request.getHeader(Constants.TOKEN_KEY);
        if (!StringUtils.isEmpty(token)) {
            SecurityUtil.getCurrentUserInfo()
                    .ifPresent(userInfo -> model.addAttribute("userInfo", userInfo));
        }
        String ip = IpKit.getClientIpAddress(request);
        model.addAttribute("ip", ip);
    }
}
