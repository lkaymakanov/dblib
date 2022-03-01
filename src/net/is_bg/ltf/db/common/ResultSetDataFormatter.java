package net.is_bg.ltf.db.common;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.is_bg.ltf.db.common.ResultSetDataFormatter.HtmlElementProp.HtmlElementPropBuilder;
import net.is_bg.ltf.db.common.customsql.ColumnMetaData;
import net.is_bg.ltf.db.common.customsql.IResultSetData;

class ResultSetDataFormatter {
	
	private static HtmlElementPropBuilder tableStyle = new HtmlElementPropBuilder();
	private static HtmlElementPropBuilder headerStyle = new HtmlElementPropBuilder();
	private static final String df = "dd.MM.yyyy'T'HH:mm:ss.SSSZ";
	private static final SimpleDateFormat dff = new SimpleDateFormat(df);
	private static String style = "  body {\n"+
			" 	background-color:rgb(15, 15, 15);\n"+
			" 	background-image: url('blood.png');\n"+
			" 	background-repeat: no-repeat;\n"+
			"     background-position: top right;\n"+
			" }\n"+
			" \n"+
			" h1{\n"+
			" 	color:white;\n"+
			" }\n"+
			" \n"+
			" h5 {\n"+
			" 	font-size:12px; margin:0px; color:gold;\n"+
			" }\n"+
			" \n"+
			" span.expandbuttontitle {\n"+
			" 	font-size:12px; margin:0px; color:gold;\n"+
			" 	position:relative;\n"+
			" 	top:-5px;\n"+
			" }\n"+
			" \n"+
			" input.search{\n"+
			" 	border:solid 4px blue;\n"+
			" 	border-radius:3px;\n"+
			" 	padding:3px;\n"+
			" 	font-size:23px;\n"+
			" 	background-image: url('search.png');\n"+
			" 	background-position: right;\n"+
			" 	height:48px;\n"+
			" 	width:250px;\n"+
			" 	background-repeat: no-repeat;\n"+
			" }\n"+
			" \n"+
			" a.wlink{\n"+
			" 	text-decoration:none; \n"+
			" 	color:white;\n"+
			" }\n"+
			" \n"+
			" table.register{\n"+
			" 	border-collapse: collapse;\n"+
			" }\n"+
			" \n"+
			" table.register tr td {\n"+
			"    color:white;\n"+
			"    border: solid 1px;\n"+
			" }\n"+
			" \n"+
			" table.register tr td  a{\n"+
			"    color:white;\n"+
			" }\n"+
			" table.register  tr.header  td {\r\n" + 
			"	font-size:22px;\r\n" + 
			"	color:#74640e;\r\n" + 
			"	background-color:black;\r\n" + 
			"	padding:8px;\r\n" + 
			"	border: solid 1px white;\r\n" + 
			"} " +
			" \n"+
			" table.register  tr.header  td {\n"+
			" 	font-size:22px;\n"+
			" 	color:gold;\n"+
			" 	background-color:black;\n"+
			" 	padding:8px;\n"+
			" 	border: solid 1px white;\n"+
			" }\n"+
			" table.register  tr.header  td  a{\n"+
			" 	text-decoration:underline;\n"+
			" 	color:gold;\n"+
			" }\n"+
			" \n"+
			" table.register tr.odd{\n"+
			"  background-color:grey;\n"+
			" }\n"+
			" \n"+
			" table.register tr:hover {\n"+
			"  background-color:green;\n"+
			"  color:red;\n"+
			" }\n";
	
	private static final String script= "var toggle = function(elId){\r\n" + 
			"			var el = document.getElementById(elId);  \r\n" + 
			"			if(el.style.display=='none'){\r\n" + 
			"				el.style.display='block';\r\n" + 
			"			}else{\r\n" + 
			"				el.style.display='none';\r\n" + 
			"			}\r\n" + 
			"		}";
	static {
		tableStyle.setClazz("register");
		headerStyle.setClazz("header");
	}
	
	
	static class HtmlElementProp{
		private String style;
		private String clazz;
		
		static class HtmlElementPropBuilder{
			private String style;
			private String clazz;
			
			HtmlElementPropBuilder setStyle(String style) {
				this.style = style;
				return this;
			}
			public HtmlElementPropBuilder setClazz(String clazz) {
				this.clazz = clazz;
				return this;
			}
			
			public HtmlElementProp build(){
				HtmlElementProp el =  new HtmlElementProp();
				el.clazz = clazz;
				el.style = style;
				return el;
			}
		}
		
		public String getStyle() {
			return style;
		}
		
		public String getClazz() {
			return clazz;
		}
	}
	
	
	private enum HTML_KEYWORDS {
		 HTML("html"),
		 H1("h1"),
		 H2("h2"),
		 H3("h3"),
		 H4("h4"),
		 HEAD("head"),
		 STYLE("style"),
		 BODY("body"),
		 TABLE("table"),
		 HR("hr"),
		 TR("tr"),
		 TD("td"), 
		 SCRIPT("script");
		 private String word;
		 
		 HTML_KEYWORDS(String key) {
			this.word = key;
		 }
		 
		 public  String begin() {
			 return "<" + word+ ">";
		 }
		 public  String begin(HtmlElementProp p) {
			 String style = " style=\"" + (p.getStyle() != null ? p.getStyle() + "\"" : "\"");
			 String clazzz = " class=\"" + (p.getClazz() != null ? p.getClazz() + "\"" : "\"");
			 return "<" + word+  style +  clazzz + ">";
		 }
		 public String end() {
			 return "</" + word+ ">";
		 }
	}
	
	
	private static String asCell(Object o) {
		if(o == null) o = "";
		StringBuilder res = new StringBuilder();
	    res.append(HTML_KEYWORDS.TD.begin());
		res.append(o.toString());
		res.append(HTML_KEYWORDS.TD.end());
		return res.toString();
	}
	
	
	static String asHtml(List<IResultSetData> datal) {
		HtmlElementPropBuilder b = new HtmlElementPropBuilder();
		b.setClazz("myclass"); b.setStyle("mystyle");
		
		StringBuilder res = new StringBuilder();
		
		res.append(HTML_KEYWORDS.HTML.begin());
		
		res.append(HTML_KEYWORDS.HEAD.begin());
		res.append(HTML_KEYWORDS.STYLE.begin());
		res.append(style);
		res.append(HTML_KEYWORDS.STYLE.end());
		res.append(HTML_KEYWORDS.SCRIPT.begin());
		res.append(script);
		res.append(HTML_KEYWORDS.SCRIPT.end());
		res.append(HTML_KEYWORDS.HEAD.end());
		
		res.append(HTML_KEYWORDS.BODY.begin());
		//tabl s themselves
		//String res = "";
		
		for(IResultSetData data:datal) {
			res.append(HTML_KEYWORDS.TABLE.begin(tableStyle.build()));
			res.append(HTML_KEYWORDS.TR.begin(headerStyle.build()));
			//header
			for(ColumnMetaData md: data.getColumnMetaData()) {
				res.append(asCell(md.getColumnLabel()));
			}
			res.append(HTML_KEYWORDS.TR.end());
			
			for(Object [] c: data.getResult()) {
				res.append(HTML_KEYWORDS.TR.begin());
				for(Object o : c) {
					res.append(asCell(o));
				}
				res.append(HTML_KEYWORDS.TR.end());
			}
			res.append(HTML_KEYWORDS.TABLE.end());
		}
		res.append(HTML_KEYWORDS.BODY.end());
		res.append(HTML_KEYWORDS.HTML.end());
		return res.toString();
		
		
	}
	
	static String asHtmlSql(List<ResultSetDataSql> datal, long f) {
		return wrapAsBody(asHtmlTable(datal, f));
	}
	
	static String wrapAsBody(String t) {
		HtmlElementPropBuilder b = new HtmlElementPropBuilder();
		b.setClazz("myclass"); b.setStyle("mystyle");
		
		StringBuilder res = new StringBuilder();
		res.append(HTML_KEYWORDS.HTML.begin());
		
		res.append(HTML_KEYWORDS.HEAD.begin());
		res.append(HTML_KEYWORDS.STYLE.begin());
		res.append(style);
		res.append(HTML_KEYWORDS.STYLE.end());
		res.append(HTML_KEYWORDS.SCRIPT.begin());
		res.append(script);
		res.append(HTML_KEYWORDS.SCRIPT.end());
		res.append(HTML_KEYWORDS.HEAD.end());
		res.append(HTML_KEYWORDS.BODY.begin());
		//tables themselves
		//String res = "";
		
		res.append(t);
		
		res.append(HTML_KEYWORDS.BODY.end());
		res.append(HTML_KEYWORDS.HTML.end());
		return res.toString();
	}
	
	static String asHtmlTable(ResultSetDataSql data, long f) {
		List<ResultSetDataSql> l = new ArrayList<ResultSetDataSql>();
		l.add(data);
		return asHtmlTable(l, f);
		
	}
	
	private static String createToggleBtn(String id, String content) {
		return " <span onclick=\"toggle('"+ id+"')\">"+ content+"</span>";
	}
	
	static String asHtmlTable(List<ResultSetDataSql> datal, long f) {
		StringBuilder res = new StringBuilder();
		for(ResultSetDataSql datasql:datal) {
			IResultSetData data = datasql.resultSetData;
			String sql = datasql.sql;
			String className = datasql.className;
			
			if((f&1l)!=0) {
				res.append("</br>");
				res.append("</br>");
				res.append(HTML_KEYWORDS.TABLE.begin(tableStyle.build()));
				res.append(HTML_KEYWORDS.TR.begin(headerStyle.build()));
				//header
				res.append(asCell(className + " " + dff.format(new Date( datasql.timeStamp)) + "  type =  " + datasql.type));
				res.append(HTML_KEYWORDS.TR.end());
				res.append(HTML_KEYWORDS.TR.begin());
				res.append(asCell(sql ));
				res.append(HTML_KEYWORDS.TR.end());
				res.append(HTML_KEYWORDS.TABLE.end());
				//header
			}
			
			if((f&4)!= 0) {
				res.append("</br>");
				res.append(HTML_KEYWORDS.TABLE.begin(tableStyle.build()));
				res.append(HTML_KEYWORDS.TR.begin(headerStyle.build()));
				String id = "stack_"+ datasql.timeStamp;
				res.append(asCell(createToggleBtn(id,"Stack: >>")));
				res.append(HTML_KEYWORDS.TR.end());
				res.append(HTML_KEYWORDS.TR.begin());
				res.append(asCell("<span id=\"" + id+"\" style=\"display:none;\">" +  datasql.stack + "</span>"));
				res.append(HTML_KEYWORDS.TR.end());
				res.append(HTML_KEYWORDS.TABLE.end());
			}
			
			if((f&2)!=0) {
				res.append("</br>");
				res.append(HTML_KEYWORDS.TABLE.begin(tableStyle.build()));
				res.append(HTML_KEYWORDS.TR.begin(headerStyle.build()));
				
				for(ColumnMetaData md: data.getColumnMetaData()) {
					res.append(asCell(md.getColumnLabel()));
				}
				res.append(HTML_KEYWORDS.TR.end());
				
				for(Object [] c: data.getResult()) {
					res.append(HTML_KEYWORDS.TR.begin());
					for(Object o : c) {
						res.append(asCell(o));
					}
					res.append(HTML_KEYWORDS.TR.end());
				}
				res.append(HTML_KEYWORDS.TABLE.end());
			}
		}
		return res.toString();
	}
	
	
	static String asHtmlSql(List<ResultSetDataSql> datal) {
		return asHtmlSql(datal, -1);
	}
	
	
	static String asHtmlSql(ResultSetDataSql datal, long f) {
		List<ResultSetDataSql> l = new ArrayList<ResultSetDataSql>();
		l.add(datal);
		return asHtmlSql(l, -1);
	}
	
	
	
	static String asHtml(IResultSetData data){
		List<IResultSetData> l = new ArrayList<IResultSetData>();
		l.add(data);
		return asHtml(l);
	}
}
