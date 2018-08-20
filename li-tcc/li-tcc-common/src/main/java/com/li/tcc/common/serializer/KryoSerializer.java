package com.li.tcc.common.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.li.tcc.common.enums.SerializeEnum;
import com.li.tcc.common.exception.TccException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * KryoSerializer
 * 
 * @author yuan.li
 */
public class KryoSerializer implements ObjectSerializer {

	@Override
	public byte[] serialize(final Object obj) throws TccException {
		byte[] bytes;
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); Output output = new Output(outputStream)) {
			// 获取kryo对象
			Kryo kryo = new Kryo();
			kryo.writeObject(output, obj);
			bytes = output.toBytes();
			output.flush();
		} catch (IOException ex) {
			throw new TccException("kryo serialize error" + ex.getMessage());
		}
		return bytes;
	}

	@Override
	public <T> T deSerialize(final byte[] param, final Class<T> clazz) throws TccException {
		T object;
		try (ByteArrayInputStream inputStream = new ByteArrayInputStream(param)) {
			Kryo kryo = new Kryo();
			Input input = new Input(inputStream);
			object = kryo.readObject(input, clazz);
			input.close();
		} catch (IOException e) {
			throw new TccException("kryo deSerialize error" + e.getMessage());
		}
		return object;
	}

	@Override
	public String getScheme() {
		return SerializeEnum.KRYO.getSerialize();
	}
}
