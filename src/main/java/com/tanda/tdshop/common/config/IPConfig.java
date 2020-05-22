package com.tanda.tdshop.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * create by
 * 三和智控: cxm on 2020/5/20
 */

public class IPConfig {

    private final static int port=8000;

    public static String uriPrefix(){
        return "http://"+getLocalHostIP()+":"+port;
    }



    public static String getLocalHostIP() {
        // 本地IP，如果没有配置外网IP则返回它
        String localIp = null;
        // 外网IP
        String netIp = null;
        try {
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            // 是否找到外网IP
            boolean finded = false;
            while (netInterfaces.hasMoreElements() && ! finded) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> address = ni.getInetAddresses();
                while (address.hasMoreElements()) {
                    ip = address.nextElement();
                    // 外网IP
                    if (! ip.isSiteLocalAddress() && ! ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == - 1) {
                        netIp = ip.getHostAddress();
                        finded = true;
                        break;
                    } else if (ip.isSiteLocalAddress() && ! ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == - 1) {
                        // 内网IP
                        localIp = ip.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.getMessage();
        }
        if (netIp != null && ! "".equals(netIp)) {
            return netIp;
        } else {
            return localIp;
        }
    }
}
