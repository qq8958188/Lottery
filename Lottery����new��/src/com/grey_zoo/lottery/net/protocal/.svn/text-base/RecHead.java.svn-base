package com.grey_zoo.lottery.net.protocal;

public class RecHead {

	//时间戳
	private Leaf timestamp;
	//时间戳+六位的随机数
	private Leaf messengerid;
	//数据摘要：MD5
	//MD5的原始数据的组成：时间戳+代理的密码
	private Leaf digest;
	//请求的唯一的标示
	private Leaf transactiontype;
	//代理ID
	private Leaf agenterid;
	//加密算法类型
	private Leaf compress;
	
	public Leaf getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Leaf timestamp) {
		this.timestamp = timestamp;
	}
	
	public Leaf getMessengerid() {
		return messengerid;
	}
	
	public void setMessengerid(Leaf messengerid) {
		this.messengerid = messengerid;
	}
	
	public Leaf getDigest() {
		return digest;
	}
	
	public void setDigest(Leaf digest) {
		this.digest = digest;
	}
	
	public Leaf getTransactiontype() {
		return transactiontype;
	}
	
	public void setTransactiontype(Leaf transactiontype) {
		this.transactiontype = transactiontype;
	}
	
	public Leaf getAgenterid() {
		return agenterid;
	}
	
	public void setAgenterid(Leaf agenterid) {
		this.agenterid = agenterid;
	}
	
	public Leaf getCompress() {
		return compress;
	}
	
	public void setCompress(Leaf compress) {
		this.compress = compress;
	}
}
