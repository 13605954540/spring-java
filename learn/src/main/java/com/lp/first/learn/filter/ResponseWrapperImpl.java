package com.lp.first.learn.filter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;

/**
 * @author LP
 * @date 2019/4/6
 */
public class ResponseWrapperImpl extends HttpServletResponseWrapper {

    private ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    private HttpServletResponse response;
    private PrintWriter pwrite;

    public ResponseWrapperImpl(HttpServletResponse response) {
        super(response);
        this.response = response;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return new OutputStreamImpl(bytes); // 将数据写到 byte 中
    }

    /**
     * 重写父类的 getWriter() 方法，将响应数据缓存在 PrintWriter 中
     */
    @Override
    public PrintWriter getWriter() throws IOException {
        try{
            pwrite = new PrintWriter(new OutputStreamWriter(bytes, "utf-8"));
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return pwrite;
    }

    /**
     * 获取缓存在 PrintWriter 中的响应数据
     * @return
     */
    public byte[] getBytes() {
        if(null != pwrite) {
            pwrite.close();
            return bytes.toByteArray();
        }

        if(null != bytes) {
            try {
                bytes.flush();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        return bytes.toByteArray();
    }
}
