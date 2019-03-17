package cn.dazhiyy.ad.common.advice;

import cn.dazhiyy.ad.common.annotation.IgnoreCommonResponse;
import cn.dazhiyy.ad.common.vo.CommonResponseVO;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.common.advice
 * @className CommonResponceDateAdvice
 * @description 统一的返回信息 如果不需要统一返回 只需要标注注解IgnoreCommonResponse 忽视
 * @date 2019/3/16 15:58
 */
@RestControllerAdvice
public class CommonResponceDateAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (returnType.getDeclaringClass().isAnnotationPresent(IgnoreCommonResponse.class)) {
            return Boolean.FALSE;
        } else if (returnType.getMethod() != null && returnType.getMethod().isAnnotationPresent(IgnoreCommonResponse.class)){
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof CommonResponseVO) {
            return body;
        }
        return CommonResponseVO.successData(body);
    }
}
