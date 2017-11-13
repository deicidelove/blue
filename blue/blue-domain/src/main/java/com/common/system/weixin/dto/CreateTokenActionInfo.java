package com.common.system.weixin.dto;

import java.io.Serializable;

public class CreateTokenActionInfo implements Serializable {
	private static final long serialVersionUID = 61004010672056643L;
	
	private CreateTokenActionScene scene;

	public CreateTokenActionScene getScene() {
		return scene;
	}

	public void setScene(CreateTokenActionScene scene) {
		this.scene = scene;
	}
	
}