package com.variation4.dbviewer.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.variation4.dbviewer.Command;
import com.variation4.dbviewer.Dao;

public abstract class AbstractAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	/** DIフィールド */
	private List<Dao> daoList;

	private List<Command> commandList = new ArrayList<Command>();

	/** DIフィールド */
	public void setDaoList(List<Dao> daoList) {
		this.daoList = daoList;
	}

	public List<Command> getCommandList() {
		return commandList;
	}

	protected void addCommand(Command command){
		commandList.add(command);
	}

	@Override
	public String execute() throws Exception {
		Dao dao = daoList.get(0);

		doScript(dao);

		return super.execute();
	}

	protected abstract void doScript(Dao dao);
}
