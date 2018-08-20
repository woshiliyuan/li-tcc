package com.li.tcc.spring.boot.starter.parent.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.li.tcc.common.config.TccConfig;

/**
 * TccConfigProperties starter
 *
 * @author yuan。li
 */
@Component
@ConfigurationProperties(prefix = "li.tcc")
public class TccConfigProperties extends TccConfig {
}