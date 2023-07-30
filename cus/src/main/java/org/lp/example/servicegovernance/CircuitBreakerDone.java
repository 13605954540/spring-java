package org.lp.example.servicegovernance;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class CircuitBreakerDone implements Serializable {

    //状态 1：未开启 2：已开启 3：半开启
    private volatile Integer state;

    //请求次数
    private AtomicInteger requests;

    //请求错误次数
    private AtomicInteger errors;

    //指定的错误率多少达到熔断
    private Integer rate;

    //熔断时长
    private long circuitTime;

    //上次请求接收时间
    private long lastAccessTime;

    public CircuitBreakerDone(Integer rate) {
        this.state = 0;
        this.requests = new AtomicInteger(0);
        this.errors = new AtomicInteger(0);
        this.rate = rate;
        this.circuitTime = 0;
        this.lastAccessTime = 0L;
    }

    public boolean isDoneBefore() {
        switch (this.state) {
            case 1:
                return true;
            case 2:
                return false;
            case 3:
                long now = System.currentTimeMillis();
                if(now - this.lastAccessTime >= circuitTime) {
                    this.state = 0;
                    return true;
                }
                return false;
            default:
                throw new RuntimeException("Unknow circuit state！");
        }
    }

    public void afterDone(Integer status) {
        boolean isSuccess = status == 200 ? true : false;
        requests.incrementAndGet();
        if(!isSuccess) {
            this.errors.incrementAndGet();
        }
        if((this.errors.intValue() / this.requests.intValue()) >= rate) {
            this.state = 1;
        }
    }

    public void doRequest() {
        if(this.state == 3) {
            int requests = this.requests.incrementAndGet();
            int errors = this.errors.incrementAndGet();
            int rate = errors / requests;
            if (rate > this.rate) {
                this.state = 2;
            }
        }
    }
}
