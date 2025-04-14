package com.nhnacademy.rest.resolver;

import com.nhnacademy.rest.domain.Requester;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

public class PageableResolver implements HandlerMethodArgumentResolver {

    int defaultPage = 0;
    int defaultSize = 10;
    int maxSize = 50;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(Pageable.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        int size = defaultSize;
        int page = defaultPage;

        try {
            String sizeParam = request.getParameter("size");
            if (sizeParam != null && !sizeParam.isEmpty()) {
                size = Integer.parseInt(sizeParam);
                if (size < 1) {
                    size = defaultSize;
                } else if (size > maxSize) {
                    size = maxSize;
                }
            }

            String pageParam = request.getParameter("page");
            if (pageParam != null && !pageParam.isEmpty()) {
                page = Integer.parseInt(pageParam);
                if (page < 0) {
                    page = defaultPage;
                }
            }
        } catch (NumberFormatException e) {

        }

        return PageRequest.of(page, size);
    }
}