import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlHeading5;
import com.gargoylesoftware.htmlunit.html.HtmlHeading6;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;


public class TCATGrabberMain {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws MalformedURLException 
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		final WebClient webClient = new WebClient();
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
	    final HtmlPage page = webClient.getPage("http://www.tcatbus.com/");
	    System.out.println("Title "+page.getTitleText());
	    HtmlSelect select = (HtmlSelect) page.getElementById("mrnid");
	    List<HtmlOption> options =select.getOptions();
	    for(HtmlOption option : options){
	    	System.out.println(option.getText()+" "+option.getValueAttribute());
	    	HtmlPage routePage;
	    	try{
	    	String newURL = "http://tcat.nextinsight.com/routes/"+option.getValueAttribute();
	    	System.out.println("URL : "+newURL);
	    	routePage = webClient.getPage(newURL);
	    	}
	    	catch(Exception e){
	    		continue;
	    	}
	    	HtmlDivision contentDiv = (HtmlDivision) routePage.getElementById("page");
	    	DomNodeList tables;
	    	try{
	    		tables = contentDiv.getElementsByTagName("table");
	    	}
	    	catch(Exception e){
	    		continue;
	    	}
	    	DomNodeList headers = contentDiv.getElementsByTagName("h5");
	    	List<HtmlHeading5> h5s = new ArrayList<HtmlHeading5>();
	    	for(int i=0;i<headers.size();i++){
	    		h5s.add((HtmlHeading5)headers.get(i));
	    		//System.out.println(h5s.get(i).asText());
	    	}
	    	
	    	for(int i=0;i<h5s.size();i++){
	    		HtmlTable table = (HtmlTable) tables.get(i);
	    		
	    		
	    		File file = new File(h5s.get(i).asText());
	    		String fileName = h5s.get(i).asText();
	    		//if(file.exists()){
	    		DomNodeList h6Headers = table.getElementsByTagName("h6");
	    		HtmlHeading6 h6Header = (HtmlHeading6) h6Headers.get(0);
	    		fileName = fileName+"_"+h6Header.asText();
	    		//}
	    		FileWriter fw = new FileWriter("dumped/"+fileName/*h5s.get(i).asText()*/);
	    		int j=0;
		    	for(HtmlTableRow row : table.getRows()){
		    		List<HtmlTableCell> cells = row.getCells();
		    		for(int k = 0;k<cells.size();k++){
		    			HtmlTableCell cell = cells.get(k);
		    			String str=(k==(cells.size()-1))?(cell.asText()):(cell.asText()+"~");
		    			fw.write(str);
		    		}
		    		fw.write("\n");
		    	}
	    	fw.close();
	    	}
	    }
	    }

}
