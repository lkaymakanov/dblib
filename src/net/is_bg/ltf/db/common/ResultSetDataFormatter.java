package net.is_bg.ltf.db.common;



import java.util.ArrayList;
import java.util.List;

import net.is_bg.ltf.db.common.ResultSetDataFormatter.HtmlElementProp.HtmlElementPropBuilder;
import net.is_bg.ltf.db.common.customsql.ColumnMetaData;
import net.is_bg.ltf.db.common.customsql.IResultSetData;

class ResultSetDataFormatter {
	
	private static HtmlElementPropBuilder tableStyle = new HtmlElementPropBuilder();
	private static HtmlElementPropBuilder headerStyle = new HtmlElementPropBuilder();
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
		 TD("td");
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
		String res = "";
	    res+=HTML_KEYWORDS.TD.begin();
		res+=o.toString();
		res+=HTML_KEYWORDS.TD.end();
		return res;
	}
	
	
	static String asHtml(List<IResultSetData> datal) {
		HtmlElementPropBuilder b = new HtmlElementPropBuilder();
		b.setClazz("myclass"); b.setStyle("mystyle");
		
		String res = HTML_KEYWORDS.HTML.begin();
		
		res+=HTML_KEYWORDS.HEAD.begin();
		res+= HTML_KEYWORDS.STYLE.begin();
		res+=style;
		res+= HTML_KEYWORDS.STYLE.end();
		res+= HTML_KEYWORDS.HEAD.end();
		
		res+=HTML_KEYWORDS.BODY.begin();
		//tabl s themselves
		//String res = "";
		
		for(IResultSetData data:datal) {
			res+=HTML_KEYWORDS.TABLE.begin(tableStyle.build());
			res+=HTML_KEYWORDS.TR.begin(headerStyle.build());
			//header
			for(ColumnMetaData md: data.getColumnMetaData()) {
				res+=asCell(md.getColumnLabel());
			}
			res+=HTML_KEYWORDS.TR.end();
			
			for(Object [] c: data.getResult()) {
				res+=HTML_KEYWORDS.TR.begin();
				for(Object o : c) {
					res+=asCell(o);
				}
				res+=HTML_KEYWORDS.TR.end();
			}
			res+=HTML_KEYWORDS.TABLE.end();
		}
		res+=HTML_KEYWORDS.BODY.end();
		res+=HTML_KEYWORDS.HTML.end();
		return res;
		
		
	}
	
	static String asHtmlSql(List<ResultSetDataSql> datal) {
		HtmlElementPropBuilder b = new HtmlElementPropBuilder();
		b.setClazz("myclass"); b.setStyle("mystyle");
		
		String res = HTML_KEYWORDS.HTML.begin();
		
		res+=HTML_KEYWORDS.HEAD.begin();
		res+= HTML_KEYWORDS.STYLE.begin();
		res+=style;
		res+= HTML_KEYWORDS.STYLE.end();
		res+= HTML_KEYWORDS.HEAD.end();
		
		res+=HTML_KEYWORDS.BODY.begin();
		//tabl s themselves
		//String res = "";
		
		for(ResultSetDataSql datasql:datal) {
			IResultSetData data = datasql.resultSetData;
			String sql = datasql.sql;
			String className = datasql.className;
			
			res+="</br>";
			res+="</br>";
			
			res+=HTML_KEYWORDS.TABLE.begin(tableStyle.build());
			res+=HTML_KEYWORDS.TR.begin(headerStyle.build());
			//header
			res+=asCell(className);
			res+=HTML_KEYWORDS.TR.end();
			res+=HTML_KEYWORDS.TR.begin();
			res+=asCell(sql);
			res+=HTML_KEYWORDS.TR.end();
			res+=HTML_KEYWORDS.TABLE.end();
			
			res+=HTML_KEYWORDS.TABLE.begin(tableStyle.build());
			res+=HTML_KEYWORDS.TR.begin(headerStyle.build());
			//header
			for(ColumnMetaData md: data.getColumnMetaData()) {
				res+=asCell(md.getColumnLabel());
			}
			res+=HTML_KEYWORDS.TR.end();
			
			for(Object [] c: data.getResult()) {
				res+=HTML_KEYWORDS.TR.begin();
				for(Object o : c) {
					res+=asCell(o);
				}
				res+=HTML_KEYWORDS.TR.end();
			}
			res+=HTML_KEYWORDS.TABLE.end();
		}
		res+=HTML_KEYWORDS.BODY.end();
		res+=HTML_KEYWORDS.HTML.end();
		return res;
		
		
	}
	
	
	
	
	static String asHtml(IResultSetData data){
		List<IResultSetData> l = new ArrayList<IResultSetData>();
		l.add(data);
		return asHtml(l);
	}
}
