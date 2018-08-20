package com.li.tcc.common.serializer;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.li.tcc.common.enums.SerializeEnum;
import com.li.tcc.common.exception.TccException;

import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * ProtostuffSerializer
 * 
 * @author yuan.li
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProtostuffSerializer implements ObjectSerializer {

	private static final SchemaCache CACHED_SCHEMA = SchemaCache.getInstance();

	private static final Objenesis OBJENESIS = new ObjenesisStd(true);

	private static <T> Schema<T> getSchema(final Class<T> cls) {
		return (Schema<T>) CACHED_SCHEMA.get(cls);
	}

	@Override
	public byte[] serialize(final Object obj) throws TccException {
		Class cls = obj.getClass();
		LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			Schema schema = getSchema(cls);
			ProtostuffIOUtil.writeTo(outputStream, obj, schema, buffer);
			return outputStream.toByteArray();
		} catch (IOException e) {
			throw new TccException(e.getMessage(), e);
		} finally {
			buffer.clear();
		}
	}

	@Override
	public <T> T deSerialize(final byte[] param, final Class<T> clazz) throws TccException {
		T object;
		try (ByteArrayInputStream inputStream = new ByteArrayInputStream(param)) {
			object = OBJENESIS.newInstance(clazz);
			Schema schema = getSchema((Class) clazz);
			ProtostuffIOUtil.mergeFrom(inputStream, object, schema);
			return object;
		} catch (IOException e) {
			throw new TccException(e.getMessage(), e);
		}
	}

	@Override
	public String getScheme() {
		return SerializeEnum.PROTOSTUFF.getSerialize();
	}
}
