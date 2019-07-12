package com.AIfntech.service.pojo;

import java.io.Serializable;
import java.util.List;

public class ForceChart implements Serializable{

	public List<ForceNode> getForceNodeList() {
		return forceNodeList;
	}
	public void setForceNodeList(List<ForceNode> forceNodeList) {
		this.forceNodeList = forceNodeList;
	}
	public List<ForceLink> getForceLinkList() {
		return forceLinkList;
	}
	public void setForceLinkList(List<ForceLink> forceLinkList) {
		this.forceLinkList = forceLinkList;
	}
	private List<ForceNode> forceNodeList;
	private List<ForceLink> forceLinkList;
}
