package utils;

import java.util.List;

/**
 * EasyUI tree模型
 */
public class EasyuiTree {
	private Integer id;						//节点的ID
	private String text;		//节点显示的文字
	private Integer pid;
	private String state = "open";		//默认open,当为‘closed’时说明此节点下有子节点，否则此节点为叶子节点
	private boolean checked = false;	//指示节点是否被选中
	private Object attributes;			//给一个节点追加的自定义属性
	private List<EasyuiTree> children;		//定义了一些子节点的节点数组
	private String iconCls;				//定义该节点的样式


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Object getAttributes() {
		return attributes;
	}

	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}

	public List<EasyuiTree> getChildren() {
		return children;
	}

	public void setChildren(List<EasyuiTree> children) {
		this.children = children;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
}
