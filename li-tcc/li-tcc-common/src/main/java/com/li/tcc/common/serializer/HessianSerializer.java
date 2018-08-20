package com.li.tcc.common.serializer;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.li.tcc.common.enums.SerializeEnum;
import com.li.tcc.common.exception.TccException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * HessianSerializer
 * 
 * @author yuan.li
 */
@SuppressWarnings("unchecked")
public class HessianSerializer implements ObjectSerializer {

	@Override
	public byte[] serialize(final Object obj) throws TccException {
		Hessian2Output hos;
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			hos = new Hessian2Output(bos);
			hos.writeObject(obj);
			hos.flush();
			return bos.toByteArray();
		} catch (IOException ex) {
			throw new TccException("Hessian serialize error " + ex.getMessage());
		}
	}

	@Override
	public <T> T deSerialize(final byte[] param, final Class<T> clazz) throws TccException {
		ByteArrayInputStream bios;
		try {
			bios = new ByteArrayInputStream(param);
			Hessian2Input his = new Hessian2Input(bios);
			return (T) his.readObject();
		} catch (IOException e) {
			throw new TccException("Hessian deSerialize error " + e.getMessage());
		}
	}

	@Override
	public String getScheme() {
		return SerializeEnum.HESSIAN.getSerialize();
	}
}
