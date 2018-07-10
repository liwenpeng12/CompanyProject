package com.lwp.cp.http;

import com.lwp.cp.util.LogUtils;

import java.io.EOFException;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;
import okio.Buffer;
import okio.BufferedSource;

/**
 * liwenpeng
 * 2018/7/6 0:18
 * CompanyProject
 * Descrobe:
 */
public class HnRequestLogInterceptor implements Interceptor { private static final Charset UTF8 = Charset.forName("UTF-8");

    private PrintLevel mPrintLevel = PrintLevel.STANDARD;

    public HnRequestLogInterceptor() {
    }

    public HnRequestLogInterceptor(PrintLevel printLevel) {
        mPrintLevel = printLevel;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        //请求行信息
        Connection connection = chain.connection();
        Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;
        String requestStartMessage = request.method() + ' ' + request.url() + ' ' + protocol;
        LogUtils.d("<-----请求行：" + requestStartMessage);

        LogUtils.d("<-----请求头：");
        Headers reqHeaders = request.headers();
        for (int i = 0, count = reqHeaders.size(); i < count; i++) {
            LogUtils.d(reqHeaders.name(i) + ": " + reqHeaders.value(i));
        }

        LogUtils.d("<-----请求体：");
        if ("POST".equalsIgnoreCase(request.method())) {
            RequestBody requestBody = request.body();
            if (requestBody.contentType() != null) {
                if (mPrintLevel == PrintLevel.ALL_REQUEST || mPrintLevel == PrintLevel.ALL_REQUEST_RESPONSE)
                    LogUtils.d("Content-Type: " + requestBody.contentType());
            }
            if (requestBody.contentLength() != -1) {
                if (mPrintLevel == PrintLevel.ALL_REQUEST || mPrintLevel == PrintLevel.ALL_REQUEST_RESPONSE)
                    LogUtils.d("Content-Length: " + requestBody.contentLength());
                if (!(requestBody.contentType() != null && requestBody.contentType().type() != null && requestBody.contentType().type().equals("multipart"))) {
                    Buffer buffer = new Buffer();
                    requestBody.writeTo(buffer);
                    LogUtils.d(URLDecoder.decode(buffer.readUtf8()));
                }
            }
        }

        Response response = chain.proceed(request);
        //响应行
        if (mPrintLevel == PrintLevel.ALL_RESPONSE || mPrintLevel == PrintLevel.ALL_REQUEST_RESPONSE)
            LogUtils.d("<-----响应行：" + response.code() + ' ' + response.message() + ' '
                    + response.request().url());

        //响应头
        if (mPrintLevel == PrintLevel.ALL_RESPONSE || mPrintLevel == PrintLevel.ALL_REQUEST_RESPONSE) {
            Headers resHeaders = response.headers();
            for (int i = 0, count = resHeaders.size(); i < count; i++) {
                LogUtils.d(resHeaders.name(i) + ": " + resHeaders.value(i));
            }
        }

        //响应体内容
        if (HttpHeaders.hasBody(response)) {
            ResponseBody responseBody = response.body();
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();

            Charset charset = UTF8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(UTF8);
                } catch (UnsupportedCharsetException e) {
                    return response;
                }
            }
            if (!isPlaintext(buffer)) {
                return response;
            }
            if (responseBody.contentLength() != 0) {
                LogUtils.json(buffer.clone().readString(charset));
            }
        }
        return response;
    }

    static boolean isPlaintext(Buffer buffer) {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }

    public enum PrintLevel {
        STANDARD, ALL_REQUEST, ALL_RESPONSE, ALL_REQUEST_RESPONSE
    }
}
