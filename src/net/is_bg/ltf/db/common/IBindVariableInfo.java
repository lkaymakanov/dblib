package net.is_bg.ltf.db.common;

import java.io.Serializable;

public interface IBindVariableInfo extends Serializable {
	 Object 	getValue();
	 int		getType();
	 int 	    getPosition();
	 boolean    IsOutputParam();
}
