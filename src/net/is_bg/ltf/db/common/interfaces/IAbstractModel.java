package net.is_bg.ltf.db.common.interfaces;

public interface IAbstractModel {

	public long getId() ;
	public void setId(long id) ;
	
	/**
	 * get the index of model*/
	public  long getIndex();
	

	/**
	 * set the index of model*/
	public void setIndex(long index);
}
