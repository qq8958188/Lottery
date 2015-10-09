package com.grey_zoo.lottery.net.protocal;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.xmlpull.v1.XmlSerializer;

public class Leaf {

	//值
	private String value;
	//节点名称
	private String name;

	public Leaf(String name) {
		
		this.name=name;
	}


	public void serializate(XmlSerializer serializer)
	{
		try {
			serializer.startTag(null, name);
			serializer.text(getValue());
			serializer.endTag(null, name);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public boolean isValueEmpty()
	{
		return StringUtils.isEmpty(getValue());
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}
	
	
}
