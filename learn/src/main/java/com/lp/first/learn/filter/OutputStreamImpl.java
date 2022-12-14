package com.lp.first.learn.filter;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author LP
 * @date 2019/4/6
 */
public class OutputStreamImpl extends ServletOutputStream {

    private ByteArrayOutputStream ostream ;

    public OutputStreamImpl(ByteArrayOutputStream ostream) {
        this.ostream = ostream;
    }

    @Override
    public void write(int b) throws IOException {
        ostream.write(b); // 将数据写到 stream　中
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {

    }
}
