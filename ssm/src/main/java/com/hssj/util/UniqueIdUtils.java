package com.hssj.util;

import java.util.UUID;

/**
 * 账号邮箱激活码
 * @author 20153
 *
 */
public final class UniqueIdUtils {
	public static String getUniqueid() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
