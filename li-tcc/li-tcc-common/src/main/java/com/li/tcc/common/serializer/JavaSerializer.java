package com.li.tcc.common.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import com.li.tcc.common.enums.SerializeEnum;
import com.li.tcc.common.exception.TccException;

/**
 * JavaSerializer
 * 
 * @author yuan.li
 */
@SuppressWarnings("unchecked")
public class JavaSerializer implements ObjectSerializer {

	@Override
	public byte[] serialize(final Object obj) throws TccException {
		try (ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
				ObjectOutput objectOutput = new ObjectOutputStream(arrayOutputStream)) {
			objectOutput.writeObject(obj);
			objectOutput.flush();
			return arrayOutputStream.toByteArray();
		} catch (IOException e) {
			throw new TccException("java serialize error " + e.getMessage());
		}
	}

	@Override
	public <T> T deSerialize(final byte[] param, final Class<T> clazz) throws TccException {
		try (ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(param);
				ObjectInput input = new ObjectInputStream(arrayInputStream)) {
			return (T) input.readObject();
		} catch (IOException | ClassNotFoundException e) {
			throw new TccException("java deSerialize error " + e.getMessage());
		}
	}

	@Override
	public String getScheme() {
		return SerializeEnum.JDK.getSerialize();
	}
}
