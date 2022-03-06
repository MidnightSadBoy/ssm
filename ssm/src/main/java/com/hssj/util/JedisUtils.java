package com.hssj.util;

import java.io.IOException;
import java.util.Properties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtils {
	private static JedisPoolConfig jedisPoolConfig;
	private static JedisPool jedisPool;
	private static Properties pro;
	static {

		try {
			pro = new Properties();
			jedisPoolConfig = new JedisPoolConfig();
			pro.load(JedisUtils.class.getClassLoader().getResourceAsStream("jedis.properties"));
			jedisPoolConfig.setMaxTotal(Integer.parseInt(pro.getProperty("maxTotal")));
			jedisPoolConfig.setMaxIdle(Integer.parseInt(pro.getProperty("maxIdle")));
			jedisPool = new JedisPool(jedisPoolConfig, pro.getProperty("host"),
					Integer.parseInt(pro.getProperty("port")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Jedis getJedis() {
		return jedisPool.getResource();
	}
}
