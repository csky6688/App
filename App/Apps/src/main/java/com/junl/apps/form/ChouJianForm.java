package com.junl.apps.form;


import com.junl.apps.common.BaseForm;

/**
 * 
 * @author dfj
 * @date 2016年8月19日下午2:52:45 
 * @description
 *
 */
public class ChouJianForm extends BaseForm {

	/**
	 * 状态（>）
	 */
	private int _state=-1;
	/**
	 * 状态(=)
	 */
	private int state=-1;
	/**
	 * 报验表ids
	 */
	private String byIds;
	
	
	
	public String getByIds() {
		return byIds;
	}
	public void setByIds(String byIds) {
		this.byIds = byIds;
	}
	public int get_state() {
		return _state;
	}
	public void set_state(int _state) {
		this._state = _state;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	
	
	
	
	
	
}
