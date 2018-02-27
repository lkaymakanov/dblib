package net.is_bg.ltf.db.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import net.is_bg.ltf.db.common.DBTransactionMarkConstants;
import net.is_bg.ltf.db.common.interfaces.IAbstractModel;


class SqlLogFileReader {
	final int BUFFER_SIZE;            //the size of the buffer to be allocated (if this size is less than file size this field is set to file size)
	private FileBuffer fb;            //each file buffer to be processed
	final String FILE_ENCODING;       //encoding of characters in the file
	private InputStream fileStream;   //the file stream used to read the file content
	private int fileOffset = 0;       //offset in the current file 
	private FileInfo fileInfo;        //filename & size
	private ITokenProcessor tokenProcessor = new TokenProcessor();     //processor for each token
	
	private SqlLogFileReader(File f, int bufferSizeInBytes, String encoding) throws IOException{
	  FILE_ENCODING = encoding;
	  fileInfo = new FileInfo();
	  fileInfo.fileName = f.getName();
	  fileStream = new FileInputStream(f);
	  fileInfo.fileSize = (int)f.length();
	  BUFFER_SIZE = fileInfo.fileSize  < bufferSizeInBytes ? fileInfo.fileSize  : bufferSizeInBytes;
	  fb = new FileBuffer();
	  fb.buffer = new byte [BUFFER_SIZE];    //allocate memory buffer
	  fileInfo.fileBufferSize = BUFFER_SIZE;
	  System.out.println("File name = " + fileInfo.fileName + " size:" + fileInfo.fileSize + " bytes " );
	}
	
	private SqlLogFileReader(String file, int bufferSizeInBytes, String encoding) throws IOException{
		this(new File(file), bufferSizeInBytes, encoding);
	}
	
	/***
	 * Try to read as many bytes as the buffer size  
	 * if fb.lastEndTokenIndex is greater than zero the bytes between lastEndTokenIndex & end of buffer are transfered in front of buffer at index 0!
	 * @param is
	 * @throws IOException
	 */
	private void fillBuffer(InputStream is) throws IOException{
		fb.size = is.read(fb.buffer, 0, (BUFFER_SIZE - (0)));   
		if(fb.size  > 0) {
			fb.fileOffset = fileOffset;
			fileOffset += fb.size;
		}
	}
	
	
	/***
	 * Copies the bytes after the end delimiter!
	 * @return
	 */
	private int copybufferBytesAfterEndDelimiter(){
		if(fb.startIndexOfLastDelimiter <0) return 0; 
		int startReadingIndex = fb.startIndexOfLastDelimiter + 1;
		return 0;
	}
	
	/***
	 * Closes the file input  stream when file processing is done
	 * @throws IOException
	 */
	private void closeFileStream() throws IOException{
		if(fileStream != null) fileStream.close();
	}
	
	/***
	 * Searches for a token in the buffer starting at startIndex! If token is fount returns true!
	 * @param buffer - buffer to search the token in
	 * @param token - the bytes of token we are searching for
	 * @param startIndex - index at which searching must start!
	 * @return
	 */
	private boolean searchToken(byte [] buffer,  byte[] token, int startIndex){
		int offset = 0;
		int len = token.length;
		while(offset < len &&  buffer[startIndex + offset] == token[offset++]){}
		return (offset == token.length);
	}
	
	/***
	 * Searches for tokens in the byte buffer delimited by begin & end token delimiters 
	 * @param buffer
	 * @param beginTokenDelimiter
	 * @param endTokenDelimiter
	 * @throws UnsupportedEncodingException
	 */
	private void searchTokens(FileBuffer fb, String beginTokenDelimiter, String endTokenDelimiter) throws UnsupportedEncodingException{
		int j = 0;
		byte [] beginTokenBytes = beginTokenDelimiter.getBytes(FILE_ENCODING);
		int beginTokenLen = beginTokenBytes.length;
		byte [] endTokenBytes = endTokenDelimiter.getBytes(FILE_ENCODING);
		int endTokenLen = endTokenBytes.length;
		int startTokenIndex = -1; int endTokenIndex = -1;
		int tokenIndex = 0;   //the number of tokens
		
		while(j < fb.size){
			while (j < (fb.size - beginTokenLen) ) {
				if(searchToken(fb.buffer, beginTokenBytes, j)){
					//System.out.println("start token fount at  " + j );
					j+=beginTokenLen;
					startTokenIndex = j;    //start index fount
					break;
				}
				j++;
			}
			
			//find end delimiter now
			while (j < (fb.size - endTokenLen) ) {
				//search for end token 
				if(searchToken(fb.buffer, endTokenBytes, j)){
					//System.out.println("end token fount at  " + j );
					endTokenIndex = j;      //end index fount
					j+=endTokenLen;
					fb.startIndexOfLastDelimiter = endTokenIndex;
					fb.endIndexOfLastDelimiter = endTokenIndex + endTokenLen;
					tokenProcessor.processToken(new String(fb.buffer, startTokenIndex, endTokenIndex - startTokenIndex, FILE_ENCODING), startTokenIndex, endTokenIndex, tokenIndex++);
					//System.out.println((new String(fb.buffer, stratTokenIndex, endTokenIndex - stratTokenIndex, FILE_ENCODING )));
					//System.out.println();
					//System.out.println("token start  " + stratTokenIndex + " token end " +endTokenIndex );
					//tokenProcessor.processToken("", stratTokenIndex, endTokenIndex, tokenIndex);
					//processToken(fb, stratTokenIndex, endTokenIndex, tokenIndex++);
					break;
				}
				//search for end begin token & indicate error if fount
				if(searchToken(fb.buffer, beginTokenBytes, j)){
					System.out.println("Start token fount at  " + j );
					j+=beginTokenLen;
					System.out.println("Error: Begin token fount when end token is expected... ");
					break;
				}
				j++;
			}
			j++;
		}
		fb.numberOfTokensInBuffer = tokenIndex;
	}
	
	/***
	 * Searches tokens delimited by begin & end token delimiters
	 * @param beginTokenDelimiter
	 * @param endTokenDelimiter
	 * @throws IOException 
	 */
	private void searchTokens(String beginTokenDelimiter, String endTokenDelimiter) throws IOException{
		fb.size = 1;
		while(fb.size > 0){
			fillBuffer(fileStream);
			fb.startIndexOfLastDelimiter = -1;
			searchTokens(fb, beginTokenDelimiter, endTokenDelimiter);
			System.out.println(" buffer size = " + fb.size + ", buffer offset in file " + fb.fileOffset + ", Buffer end delimiter last index " + fb.endIndexOfLastDelimiter + 
					", Number of tokens " + fb.numberOfTokensInBuffer) ;
		}
	}
	
	public static void main(String [] args) throws IOException{
		SqlLogFileReader  s = new SqlLogFileReader(new File("D:\\sher\\tomcat.log"), 16*1024, "Windows-1251");
		s.searchTokens(DBTransactionMarkConstants.TRANSACTION_BEGIN + "\r\n", DBTransactionMarkConstants.TRANSACTION_END+"\r\n");
	}
	
	
	private class FileInfo{
		private String fileName;                      //the name of log file
		private int fileSize;					      //the size of log file in bytes
		private int fileBufferSize;                   //the size of buffer used to read the log file
	}
	
	private class FileBuffer{
		private int size;   						   //the number of bytes in buffer 
		private byte [] buffer;						   //the buffer
		private int fileOffset = -1;   		   		   //the offset of buffer in file
		private int startIndexOfLastDelimiter = -1;    //the start index of the last end  delimiter in  buffer
		private int endIndexOfLastDelimiter = -1;      //the end index the last end  delimiter in  buffer
		private int numberOfTokensInBuffer;            //the number of tokens fount in buffer
	}
	
	private class DbStatementInfo{
		private String classs;                        //then name of statement class
		private String sql;                           //the statement sql
		private long startTime;                       //the start time of statement execution
		private long endTime;						  //the end time of statement execution
		private long duration;						  //duration of statement execution in milliseconds
	}
	
	/***
	 * interface for processing tokens
	 * @author lubo
	 *
	 */
	public static interface ITokenProcessor{
		public void processToken(String token, int stratTokenIndex, int endTokenIndex, int tokenIndex);
	}
	
	private class DbTransactionInfo implements IAbstractModel{
		long transactionId;
		int statementCount;
		String userName;
		long userId;
		long userTrno;
		long duration;
		String dbUrl;
		List<DbStatementInfo> statements = new ArrayList<DbStatementInfo>();
		@Override
		public long getId() {return 0;}
	}
	
	
	private class TokenProcessor implements ITokenProcessor{
		@Override
		public void processToken(String token, int startTokenIndex, int endTokenIndex, int tokenIndex) {
			//String token = (new String(fb.buffer, startTokenIndex, endTokenIndex - startTokenIndex, FILE_ENCODING ));
			int classesBeginIndex = 8;
			int classesEndIndex = 8;
			List<Integer> beginStIndex = new ArrayList<Integer>();    //list storing statement begin indexes
			List<Integer> endStIndex = new ArrayList<Integer>();      //list storing statement end indexes
			
			//get transaction details
			String [] tokenRows = token.split("\r\n");
			DbTransactionInfo dbtansaction = processTransaction(tokenRows);
			classesEndIndex+=dbtansaction.statementCount;
			
			//find statements indexes
			for(int i = classesEndIndex + 1; i < tokenRows.length; i++){
				if(tokenRows[i].startsWith(DBTransactionMarkConstants.STATEMENT_BEGIN) ) {beginStIndex.add(i);}
				else if(tokenRows[i].startsWith(DBTransactionMarkConstants.STATEMENT_END) ){
					endStIndex.add(i);
				}
			}
			
			//process each statement by the statement begin & end row indexes
			for(int i =0 ; i < beginStIndex.size(); i++){
				DbStatementInfo di = processStatement(tokenRows, beginStIndex.get(i), endStIndex.get(i));
				di.classs = tokenRows[classesBeginIndex + i];
				dbtansaction.statements.add( di);
			}
			
			//here end up with processed token
		}
		
		
		private DbTransactionInfo processTransaction(String [] tokenRows){
			DbTransactionInfo dbtansaction = new DbTransactionInfo();
			dbtansaction.transactionId = Long.valueOf(tokenRows[0].substring(DBTransactionMarkConstants.TRANSACTION_ID.length()));
			dbtansaction.statementCount  = Integer.valueOf(tokenRows[1].substring(DBTransactionMarkConstants.STATEMENT_COUNT.length()));
			dbtansaction.userName = tokenRows[2].substring(DBTransactionMarkConstants.STATEMENT_COUNT.length());
			dbtansaction.userId =  Long.valueOf(tokenRows[3].substring(DBTransactionMarkConstants.USER_ID.length()));
			dbtansaction.userTrno = Long.valueOf(tokenRows[4].substring(DBTransactionMarkConstants.USER_TRNO.length()));
			dbtansaction.duration = Long.valueOf(tokenRows[5].substring(DBTransactionMarkConstants.DURATION.length()));
			dbtansaction.dbUrl = tokenRows[6].substring(DBTransactionMarkConstants.DB_URL.length());
			return dbtansaction;
		}
		
		private DbStatementInfo processStatement(String [] tokenRows, int beginIndex, int endIndex){
			DbStatementInfo dbStatement = new DbStatementInfo();
			//get statement duration, start & end  times - (the last 3 rows)
			dbStatement.duration = Long.valueOf( tokenRows[endIndex-1].substring(DBTransactionMarkConstants.DURATION.length()));     //duration
			dbStatement.endTime = Long.valueOf( tokenRows[endIndex-2].substring(DBTransactionMarkConstants.END_TIME.length()));      //end time
			dbStatement.startTime = Long.valueOf( tokenRows[endIndex-3].substring(DBTransactionMarkConstants.START_TIME.length()));  //start time
			
			StringBuilder bd = new StringBuilder();
			
			//get the statement sql all the rows above the last three up to the statement begin delimiter
			for(int i = endIndex-4; i > beginIndex; i--){
				bd.append(tokenRows[i]);
				bd.append("\n");
			}
			dbStatement.sql = bd.toString();
			return dbStatement;
		}
	}
	
}
