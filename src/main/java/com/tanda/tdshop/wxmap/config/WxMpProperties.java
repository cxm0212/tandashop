package com.tanda.tdshop.wxmap.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * wechat mp properties
 *
 * @author cxm
 */
@Data
public class WxMpProperties {

    private final String appId="wxdc2f14090ffdcc10";
    private final String secret="aebdaadd1ea2199808ab26b21ec0e4ac";
    private final String token= "wxfcxm";
    private final String aesKey= "5duPyUlqgWReXmX5EQvTR2RjxKcgYS0K381ZT4Sqc8u";

}
