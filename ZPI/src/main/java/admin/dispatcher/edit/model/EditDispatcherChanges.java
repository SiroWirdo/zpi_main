package admin.dispatcher.edit.model;

import java.util.List;

import model.Dispatcher;


public class EditDispatcherChanges {
	int flag;
	List<Dispatcher> dispatchers;

	public EditDispatcherChanges(List<Dispatcher> dispatchers, int flag){
		this.dispatchers = dispatchers;
		this.flag = flag;
	}

	public List<Dispatcher> getDispatchers(){
		return dispatchers;
	}

	public void setDispatchers(List<Dispatcher> dispatchers){
		this.dispatchers = dispatchers;
	}

	public int getFlag(){
		return flag;
	}

	public void setFlag(int flag){
		this.flag = flag;
	}
}
