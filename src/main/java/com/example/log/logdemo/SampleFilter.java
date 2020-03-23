package com.example.log.logdemo;

import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class SampleFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestIdKey = "X-REQUEST-ID";
        String uuid = UUID.randomUUID().toString();

        // どこからでもリクエストIDが取得できるように設定しておく
        request.setAttribute(requestIdKey, uuid);

        try {
            // ログに出力できるように設定
            MDC.put(requestIdKey, uuid);
            filterChain.doFilter(request, response);

        } finally {
            MDC.remove(requestIdKey);
        }
    }
}
