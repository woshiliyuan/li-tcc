package com.li.tcc.common.bean.adapter;

import org.bson.types.ObjectId;

import java.io.Serializable;

/**
 * MongoAdapter
 * 
 * @author yuan.li
 */
public class MongoAdapter extends CoordinatorRepositoryAdapter implements Serializable {

	private static final long serialVersionUID = 7920817865031921102L;

	private ObjectId id;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

}
