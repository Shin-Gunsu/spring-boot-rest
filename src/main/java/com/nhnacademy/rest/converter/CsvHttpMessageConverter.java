package com.nhnacademy.rest.converter;

import com.nhnacademy.rest.domain.Member;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;

import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.AbstractJsonHttpMessageConverter;
import org.springframework.lang.Nullable;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CsvHttpMessageConverter extends AbstractHttpMessageConverter<Member> {

    public static final Charset DEFAULT_CHARSET;

    public CsvHttpMessageConverter() {
        super(new MediaType("text", "csv"));
    }


    @Override
    protected boolean supports(Class<?> clazz) {
        return Member.class.isAssignableFrom(clazz);
    }

    @Override
    protected Member readInternal(Class<? extends Member> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    protected void writeInternal(Member member, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {

        outputMessage.getHeaders().setContentType(MediaType.valueOf("text/csv; charset=UTF-8"));
        try (Writer writer = new OutputStreamWriter(outputMessage.getBody())) {
            StringBuilder sb = new StringBuilder();
            sb.append(member.getName());
            sb.append(",");
            sb.append(member.getAge());
            sb.append(",");
            sb.append(member.getRole());
            sb.append(",");
            sb.append(member.getClazz());
            writer.write(sb.toString());
            writer.flush();
        }
    }

    private static Writer getWriter(HttpOutputMessage outputMessage) throws IOException {
        return new OutputStreamWriter(outputMessage.getBody(), getCharset(outputMessage.getHeaders()));
    }

    private static Reader getReader(HttpInputMessage inputMessage) throws IOException {
        return new InputStreamReader(inputMessage.getBody(), getCharset(inputMessage.getHeaders()));
    }

    private static Charset getCharset(HttpHeaders headers) {
        Charset charset = headers.getContentType() != null ? headers.getContentType().getCharset() : null;
        return charset != null ? charset : DEFAULT_CHARSET;
    }

    static {
        DEFAULT_CHARSET = StandardCharsets.UTF_8;
    }
}
