package net.is_bg.ltf.db.common;

public interface ILogEncrypter {
	public String encrypt(String encrypt);
	public byte [] encrypt(byte[] b);
}
