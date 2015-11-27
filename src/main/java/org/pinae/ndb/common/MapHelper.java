package org.pinae.ndb.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Map对象工具集
 * 
 * @author Huiyugeng
 *
 */
public class MapHelper {

	/**
	 * Map对象拷贝
	 * 
	 * @param src 源Map对象
	 * @param propertyRef 键值映射
	 * 
	 * @return 拷贝后的Map对象
	 */
	public static Map<?, ?> copy(Map<?, ?> src, Map<?, ?> propertyRef) {
		Map<Object, Object> dst = new HashMap<Object, Object>();

		Set<?> entrySet = src.entrySet();
		for (Object object : entrySet) {
			if (object != null && object instanceof Entry<?, ?>) {
				Entry<?, ?> entry = (Entry<?, ?>) object;
				Object key = entry.getKey();
				Object value = entry.getValue();

				if (key != null) {
					Object refKey = propertyRef.get(key);
					if (refKey != null) {
						dst.put(refKey, value);
					}
				}
			}
		}

		return dst;

	}

	/**
	 * 将数组输出为Map，其中键与值在位置上一一对应
	 * 
	 * @param keys 键集合
	 * @param values 值集合
	 * 
	 * @return 生成的Map对象
	 */
	public static Map<Object, Object> toMap(Object keys[], Object values[]) {
		Map<Object, Object> dst = new HashMap<Object, Object>();

		if (keys != null && values != null && keys.length <= values.length) {
			for (int i = 0; i < keys.length; i++) {
				Object key = keys[i];
				Object value = values[i];
				if (key != null) {
					dst.put(key, value);
				}
			}
		}

		return dst;
	}

	/**
	 * 将数组输出为Map
	 * 
	 * @param valuePairs 键值集合，其中第一个元素为键，第二个元素为值
	 * 
	 * @return 生成的Map对象
	 */
	public static Map<Object, Object> toMap(Object[][] valuePairs) {
		Map<Object, Object> dst = new HashMap<Object, Object>();
		if (valuePairs != null) {
			for (int i = 0; i < valuePairs.length; i++) {
				Object[] valuePair = valuePairs[i];
				if (valuePair != null && valuePair.length == 2) {
					Object key = valuePair[0];
					Object value = valuePair[1];

					if (key != null) {
						dst.put(key, value);
					}
				}
			}
		}
		return dst;
	}

	/**
	 * Map格式转换: Object转换为String
	 * 
	 * @param src 需要进行转换的Map
	 * 
	 * @return 转换后的Map
	 */
	public static Map<String, String> toStringMap(Map<?, ?> src) {
		Map<String, String> dst = new HashMap<String, String>();

		Set<?> entrySet = src.entrySet();
		for (Object object : entrySet) {
			if (object != null && object instanceof Entry<?, ?>) {
				Entry<?, ?> entry = (Entry<?, ?>) object;
				Object key = entry.getKey();
				Object value = entry.getValue();

				if (key != null) {
					if (value == null) {
						value = "";
					}
					dst.put(key.toString(), value.toString());
				}
			}
		}

		return dst;
	}

	/**
	 * Map格式转换: String转换为Object
	 * 
	 * @param src 需要转换的Map
	 * 
	 * @return 转换后Map
	 */
	@SuppressWarnings("unchecked")
	public static Map<Object, Object> toObjectMap(Map<String, String> src) {
		Map<Object, Object> dst = new HashMap<Object, Object>();

		Set<?> entrySet = src.entrySet();
		for (Object object : entrySet) {
			if (object != null && object instanceof Entry<?, ?>) {
				Entry<Object, Object> entry = (Entry<Object, Object>) object;
				Object key = entry.getKey();
				Object value = entry.getValue();

				if (key != null) {
					dst.put(key, value);
				}
			}
		}

		return dst;
	}

	/**
	 * Map转换为字符串 根据format格式对Map中的值进行映射，format中需要替换的值使用{}进行包装
	 * 
	 * @param src 需要转换的Map
	 * @param format 字符串格式，例如"{firstname} {lastname}"
	 * 
	 * @return 转换后的字符串
	 */
	public static String toString(Map<?, ?> src, String format) {
		if (src != null && src.size() > 0) {
			Set<?> keySet = src.keySet();
			for (Object key : keySet) {
				if (key != null) {
					Object value = src.get(key);
					if (value != null) {
						format = format.replaceAll("\\{" + key.toString() + "\\}", value.toString());
					}
				}
			}
		} else {
			return null;
		}
		return format;
	}

	/**
	 * 合并两个Map, 将源Map合并至目标Map
	 * 
	 * @param dst 目标Map
	 * @param src 源Map
	 */
	@SuppressWarnings("unchecked")
	public static void join(Map<?, ?> dst, Map<?, ?> src) {
		
		Map<Object, Object> dstMap = (Map<Object, Object>)dst;
		Map<Object, Object> srcMap = (Map<Object, Object>)src;
		
		Set<Object> srcKeySet = srcMap.keySet();

		for (Object srcKey : srcKeySet) {

			Object srcValue = src.get(srcKey);
			Object dstValue = dst.get(srcKey);

			if (srcValue != null) {
				if (dstValue != null) {
					if (dstValue instanceof List) {
						List<Object> valueList = (List<Object>) dstValue;
						valueList.add(srcValue);
						dstMap.put(srcKey, valueList);
					} else {
						dstMap.put(srcKey, srcValue);
					}
				} else {
					dstMap.put(srcKey, srcValue);
				}
			}
		}
	}
	

}
