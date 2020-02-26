package cats.selenium.bdd.stepdef;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.sapient.qa.cats.core.framework.CATSCucumberConfig;


public class CustomRules extends CATSCucumberConfig {
	
	/**
	 * 
	 * @param locator "this required locator path in cats manner ex. SheetName.PageName.LocatorName (YWW.Login.email)"
	 * @param ormData "this would need object of orm file"
	 * @return this will return the locator path by checking if locator is site specific or common.
	 */
	public static String locatorPresentInSite(String locator, ExcelReader ormData ){	
		  String[] locatorFormat= locator.split("\\.");
		  String sheet=locatorFormat[0];
		  String page=locatorFormat[1];
		  String locatorName=locatorFormat[2];
		  int rowCount= ormData.getRowCount(sheet);
		  String locatorRepo="Common";		  
		  int rowNumberPage= ormData.getCellRowNum(sheet, "PAGE", page);
		  
		  for(int rowCounter=rowNumberPage; rowCounter<=rowCount;rowCounter++){
			  String currentPageName= ormData.getCellData(sheet, "PAGE", rowCounter+1);
			  String currentLocatorName=ormData.getCellData(sheet, "ELEMENTNAME", rowCounter);
			 if(currentLocatorName.equalsIgnoreCase(locatorName)){
				 return locator;
			 }else if(!currentPageName.equals("")){
				  return locatorRepo+"."+page+"."+locatorName;
			 }
		  }
		  return locatorRepo+"."+page+"."+locatorName;
	  }

	public static String isFilePresent(String downloadPath){
		File dir = new File(downloadPath);
		  File[] dirContents = dir.listFiles();
		  String filename="null";
		  for (int i = 0; i < dirContents.length; i++) {
	    	  if(dirContents[i].getName().endsWith(".pdf")){
	    		  filename=dirContents[i].getName();
	    	  }          
		  
		  }
		      return filename;
		  }
	
	
	public static boolean isFileDownloaded(String downloadPath, String fileName) {
		  File dir = new File(downloadPath);
		  File[] dirContents = dir.listFiles();
		  System.out.println("dirContents::::"+dirContents);
		  List<String> pdf=new ArrayList<>();
		  for (int i = 0; i < dirContents.length; i++) {
	    	  System.out.println("dir content:::::::"+dirContents[i].getName());
	    	  if(dirContents[i].getName().endsWith(".pdf")){
	    		  pdf.add(dirContents[i].getName());
	    		  System.out.println("PDFtime::::::"+ dirContents[i].lastModified());
	    	  }          
		  
		  }
		      return false;
		  }
	
	public static void delAllFiles(String dirPath){
		File dir = new File(dirPath);
		  File[] dirContents = dir.listFiles();
		  
		  for (int i = 0; i < dirContents.length; i++) {
			  String pdfName=dirContents[i].getName();
			  if(pdfName.endsWith(".pdf") || pdfName.endsWith(".PDF")){
	    	  System.out.println("dir content:::::::"+dirContents[i].getName());
	    	  dirContents[i].delete();
			  }
		  }
		  
	}
	
	public static String getLatestFilefromDir(String dirPath){
	    File dir = new File(dirPath);
	    File[] files = dir.listFiles();
	    if (files == null || files.length == 0) {
	        return null;
	    }

	    File lastModifiedFile = files[0];
	    for (int i = 1; i < files.length; i++) {
	       if (lastModifiedFile.lastModified() < files[i].lastModified()) {
	           lastModifiedFile = files[i];
	       }
	    }
	    return lastModifiedFile.getName();
	}
	
}
