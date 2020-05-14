package com.ldy.core.model;

import java.util.Objects;

/**
 * 表示网络的一个端点
 * @Author songxulin
 * @Date 2020/4/9 20:22
 **/
public class Peer {
    /**
     * 地址
     */
    private String host;
    /**
     * 端口
     */
    private int port;


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Peer peer = (Peer) o;
        return port == peer.port &&
                Objects.equals(host, peer.host);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, port);
    }
}
