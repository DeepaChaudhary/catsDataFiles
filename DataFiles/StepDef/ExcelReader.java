package cats.selenium.bdd.stepdef;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.common.usermodel.Hyperlink;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;




public class ExcelReader {

	public  String path;
	public  FileInputStream fis = null;
	public  FileOutputStream fileOut =null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row   =null;
	private XSSFCell cell = null;
	private HashMap<String,HashMap<String, Integer>> sheetMap=null;
	private HashMap<String, Integer> mapping=null;

	public ExcelReader(String path) {

		this.path=path;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
			sheetMap=new HashMap<String,HashMap<String,Integer>>();
		} catch (Exception e) {

			e.printStackTrace();
		} 

	}


	/*  Function to get number of rows for given sheet name in current workbook.
	 *  It returns 0 if sheet does'nt exists
	 *  @param name of sheet
	 *  @return the row count in the sheet
	 */
	public int getRowCount(String sheetName){
			sheet = workbook.getSheet(sheetName);
			if(sheet!=null){
				int number=sheet.getLastRowNum()+1;
				return number;
			}
			return 0;

	}



	/*Function to get cell data in a given cell for a given sheet.
	 * The column no are mapped against uppermost row of the sheet
	 * It returns empty string if cell does'nt exist or is empty
	 * @param Sheetname for given workbook
	 * @param Column name for cell (starts with 0)
	 * @param Row no for cell (starts with 0)
	 * @return the data from a cell in String format (empty String if cell or sheet or cell data does'nt exist)
	 */
	public String getCellData(String sheetName,String colName,int rowNum){
		if(rowNum <=0){
			return "";
		}
		int index = workbook.getSheetIndex(sheetName);
		int colNumber=-1;
		if(index==-1){
			return "";
		}
		sheet = workbook.getSheetAt(index);
		if(!sheetMap.containsKey(sheetName)||(sheetMap.get(sheetName)==null)){
			mapSheet(sheet,sheetName);
		}
		mapping=sheetMap.get(sheetName);
		if(!mapping.containsKey(colName)||(mapping.get(colName)==null)){
			return "";
		}
		colNumber=mapping.get(colName);
		return this.getCellData(sheetName, colNumber, rowNum);
			
	}
	/*Function to get cell data in a given cell for a given sheet.
	 * It returns empty string if cell does'nt exist or is empty
	 * @param Sheetname for given workbook
	 * @param Column no for cell (starts with 0)
	 * @param Row no for cell (starts with 0)
	 * @return the data from a cell in String format (empty String if cell or sheet or cell data does'nt exist)
	 */
	public String getCellData(String sheetName,int colNum,int rowNum){
		try{
			if(rowNum <0){
				return "";
			}
			int index = workbook.getSheetIndex(sheetName);

			if(index==-1){
				return "";
			}

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum);
			if(row==null){
				return "";
			}
			cell = row.getCell(colNum);
			if(cell==null){
				return "";
			}
			if(cell.getCellType()==Cell.CELL_TYPE_STRING){
				return cell.getStringCellValue().trim();
			}
			
			else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC || cell.getCellType()==Cell.CELL_TYPE_FORMULA ){

				String cellText  = String.valueOf(cell.getNumericCellValue());
				if (DateUtil.isCellDateFormatted(cell)) {
					// format in form of MM/DD/YY
					double d = cell.getNumericCellValue();

					Calendar cal =Calendar.getInstance();
					cal.setTime(DateUtil.getJavaDate(d));
					cellText =
							(String.valueOf(cal.get(Calendar.YEAR))).substring(2);
					cellText = cal.get(Calendar.MONTH)+1 + "/" +
							cal.get(Calendar.DAY_OF_MONTH) + "/" +
							cellText;
				}
				return cellText.trim();
			}else if(cell.getCellType()==Cell.CELL_TYPE_BLANK){
				return "";
			}
			else {
				return String.valueOf(cell.getBooleanCellValue()).trim();
			}
		}
		catch(Exception e){

			e.printStackTrace();
			return "";
		}
	}


	/*Function to set cell data in a given cell for a given sheet.
	 * The column no are mapped against uppermost row of the sheet
	 * It returns false if writing failed or colNum does'nt exist
	 * @param Sheetname for given workbook
	 * @param Column name for cell (starts with 0)
	 * @param Row no for cell (starts with 0)
	 * @param Data to be wrote to cell
	 * @return true if data is set successfully else false
	 */
	public boolean setCellData(String sheetName,String colName,int rowNum, String data){
		try{
			fis = new FileInputStream(path); 
			workbook = new XSSFWorkbook(fis);

			if(rowNum<=0){
				return false;
			}
			int index = workbook.getSheetIndex(sheetName);
			int colNum=-1;
			if(index==-1){
				return false;
			}

			sheet = workbook.getSheetAt(index);

			if(!sheetMap.containsKey(sheetName)||(sheetMap.get(sheetName)==null)){
				mapSheet(sheet,sheetName);
			}
			mapping=sheetMap.get(sheetName);
			if(!mapping.containsKey(colName)||(mapping.get(colName)==null)){
				return false;
			}
			colNum=mapping.get(colName);
			
			sheet.autoSizeColumn(colNum); 
			row = sheet.getRow(rowNum);
			if (row == null){
				row = sheet.createRow(rowNum);
			}
			cell = row.getCell(colNum);	
			if (cell == null){
				cell = row.createCell(colNum);
			}

			cell.setCellValue(data);

			fileOut = new FileOutputStream(path);

			workbook.write(fileOut);

			fileOut.close();	
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/*Function to set cell data and URL in a given cell for a given sheet.
	 * The column no are mapped against uppermost row of the sheet
	 * It returns false if writing failed or colNum does'nt exist
	 * @param Sheetname for given workbook
	 * @param Column name for cell (starts with 0)
	 * @param Row no for cell (starts with 0)
	 * @param Data to be wrote to cell
	 * @param URL to be added to 
	 * @return true if data is set successfully else false
	 */
	public boolean setCellData(String sheetName,String colName,int rowNum, String data,String url){

		try{
			fis = new FileInputStream(path); 
			workbook = new XSSFWorkbook(fis);

			if(rowNum<=0){
				return false;
			}
			int index = workbook.getSheetIndex(sheetName);
			int colNum=-1;
			if(index==-1){
				return false;
			}

			sheet = workbook.getSheetAt(index);

			if(!sheetMap.containsKey(sheetName)||(sheetMap.get(sheetName)==null)){
				mapSheet(sheet,sheetName);
			}
			mapping=sheetMap.get(sheetName);
			if(!mapping.containsKey(colName)||(mapping.get(colName)==null)){
				return false;
			}
			colNum=mapping.get(colName);
			sheet.autoSizeColumn(colNum); 
			row = sheet.getRow(rowNum);
			if (row == null){
				row = sheet.createRow(rowNum);
			}
			cell = row.getCell(colNum);	
			if (cell == null){
				cell = row.createCell(colNum);
			}
			cell.setCellValue(data);
			XSSFCreationHelper createHelper = workbook.getCreationHelper();

			//cell style for hyperlinks

			CellStyle hlinkstyle = workbook.createCellStyle();
			XSSFFont hlinkfont = workbook.createFont();
			hlinkfont.setUnderline(Font.U_SINGLE);
			hlinkfont.setColor(IndexedColors.BLUE.getIndex());
			hlinkstyle.setFont(hlinkfont);

			XSSFHyperlink link = createHelper.createHyperlink(Hyperlink.LINK_FILE);
			link.setAddress(url);
			cell.setHyperlink(link);
			cell.setCellStyle(hlinkstyle);

			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);

			fileOut.close();	

		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/*Function to add sheet to current workbook with given sheetname
	 * @param Sheet name
	 * @return true if sheet is created successfully else false
	 */
	public boolean addSheet(String  sheetname){		

		FileOutputStream fileOut;
		try {
			workbook.createSheet(sheetname);	
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();		    
		} catch (Exception e) {			
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/*
	 * Function to remove sheet of given name from current workbook
	 * @param sheet name
	 * @return true if sheet is removed successfully else false if sheet does not exist or not removed
	 */
	public boolean removeSheet(String sheetName){		
		int index = workbook.getSheetIndex(sheetName);
		if(index==-1){
			return false;
		}
		FileOutputStream fileOut;
		try {
			workbook.removeSheetAt(index);
			sheetMap.remove(sheetName);
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();		
			
		} catch (Exception e) {			
			e.printStackTrace();
			return false;
		}
		mapping.remove(sheetName);
		return true;
	}
	/* Function adds column to given sheet with given name
	 * @param Sheet name 
	 * @param Column name
	 * @return true if column is created successfully false otherwise
	 */
	public boolean addColumn(String sheetName,String colName){
		try{				
			fis = new FileInputStream(path); 
			workbook = new XSSFWorkbook(fis);
			int index = workbook.getSheetIndex(sheetName);
			if(index==-1){
				return false;
			}
			XSSFCellStyle style = workbook.createCellStyle();
			style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);

			sheet=workbook.getSheetAt(index);

			row = sheet.getRow(0);
			if (row == null){
				row = sheet.createRow(0);
			}

			if(row.getLastCellNum() == -1){
				cell = row.createCell(0);
			}
			else{
				int colNum=row.getLastCellNum();
				cell = row.createCell(row.getLastCellNum());
			cell.setCellValue(colName);
			cell.setCellStyle(style);
			sheetMap.get(sheetName).put(colName, colNum);
			}
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();	
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}

		return true;
	}
	/* Function removes column to given sheet with given column no
	 * @param Sheet name 
	 * @param Column no
	 * @return true if column is removed successfully false otherwise
	 */
	public boolean removeColumn(String sheetName, int colNum) {
		try{
			if(!isSheetExist(sheetName)){
				return false;
			}
			fis = new FileInputStream(path); 
			workbook = new XSSFWorkbook(fis);
			sheet=workbook.getSheet(sheetName);
			XSSFCellStyle style = workbook.createCellStyle();
			style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
			style.setFillPattern(CellStyle.NO_FILL);



			for(int i =0;i<getRowCount(sheetName);i++){
				row=sheet.getRow(i);	
				if(row!=null){
					cell=row.getCell(colNum);
					if(cell!=null){
						cell.setCellStyle(style);
						row.removeCell(cell);
					}
				}
			}
			removeColumnMapping(sheetName, colNum);
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;

	}
	/* Function removes column to given sheet with given column name
	 * @param Sheet name 
	 * @param Column name
	 * @return true if column is removed successfully false otherwise
	 */
	public boolean removeColumn(String sheetName, String colName) {
			try{
				if(!isSheetExist(sheetName)){
					return false;
				}
				fis = new FileInputStream(path); 
				workbook = new XSSFWorkbook(fis);
				sheet=workbook.getSheet(sheetName);
				XSSFCellStyle style = workbook.createCellStyle();
				style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
				style.setFillPattern(CellStyle.NO_FILL);
				int colNum;
				if(!sheetMap.containsKey(sheetName)||(sheetMap.get(sheetName)==null)){
					mapSheet(sheet,sheetName);
				}
				mapping=sheetMap.get(sheetName);
				if(!mapping.containsKey(colName)||(mapping.get(colName)==null)){
					return false;
				}
				colNum=mapping.get(colName);

				for(int i =0;i<getRowCount(sheetName);i++){
					row=sheet.getRow(i);	
					if(row!=null){
						cell=row.getCell(colNum);
						if(cell!=null){
							cell.setCellStyle(style);
							row.removeCell(cell);
						}
					}
				}
				sheetMap.get(sheetName).remove(colName);
				fileOut = new FileOutputStream(path);
				workbook.write(fileOut);
				fileOut.close();
			}
			catch(Exception e){
				e.printStackTrace();
				return false;
			}
			return true;

	}
	/* Function to find that a sheet exists with given name in current workbook
	 * @param Sheet name 
	 * @return true if sheet exists false otherwise
	 */	
	public boolean isSheetExist(String sheetName){
		int index = workbook.getSheetIndex(sheetName);
		if(index==-1){
			index=workbook.getSheetIndex(sheetName.toUpperCase());
			if(index==-1)	{
				return false;
			}
			else	{
				return true;
			}
			}
			else	{
			return true;
			}
	}

	/** Function to count no column in given sheet
	 * @param Sheet name 
	 * @return no of column or -1 sheet is empty or does'nt exists
	 */
	public int getColumnCount(String sheetName){
		// check if sheet exists
		if(!isSheetExist(sheetName)){
			return -1;
		}
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(0);

		if(row==null){
			return -1;
		}
		return row.getLastCellNum();

	}
	/** Function to attach URL and to given cell 
	 * @param Sheet name
	 * @param Column name 
	 * @param index as row no 
	 * @param URL
	 * @return true if column is removed successfully false otherwise
	 */
	@Deprecated
	public boolean addHyperLink(String sheetName,String screenShotColName,String testCaseName,int index,String url,String message){


		url=url.replace('\\', '/');
		if(!isSheetExist(sheetName)){
			return false;
		}
		sheet = workbook.getSheet(sheetName);

		for(int i=1;i<getRowCount(sheetName);i++){
			if(getCellData(sheetName, 0, i).equalsIgnoreCase(testCaseName)){

				setCellData(sheetName, screenShotColName, i+index, message,url);
				break;
			}
		}

		return true; 
	}
	/** Function gives first row no containing a given value in given column
	 * @param Sheet name 
	 * @param Column name
	 * @param Cell value
	 * @return First encountered row no with given value or -1 otherwise
	 */
	public int getCellRowNum(String sheetName,String colName,String cellValue){

		for(int i=1;i<getRowCount(sheetName);i++){
			if(getCellData(sheetName,colName , i).equalsIgnoreCase(cellValue)){
				return i;
			}
		}
		return -1;

	}
	
	/** Function gives first row no containing a given value in given column, it checks only contains not exact value
	 * @param Sheet name 
	 * @param Column name
	 * @param Cell value
	 * @return First encountered row no with given value or -1 otherwise
	 */
	public int getCellRowNumIfContains(String sheetName,String colName,String cellValue){

		for(int i=1;i<getRowCount(sheetName);i++){
			if(getCellData(sheetName,colName , i).contains(cellValue)){
				return i;
			}
		}
		return -1;

	}
	
	/*
	 * Adjusts all columns width to fit the contents in given sheet.
	 * This process can be relatively slow on large sheets, so this should normally only be called once per column, at the end of your processing.
	 * @param sheetName
	 */
	public void beautify(String sheetName){
		if(!isSheetExist(sheetName)){
			System.out.println("Sheet does'nt exists");
		}
		else{
			sheet=workbook.getSheet(sheetName);
			row=sheet.getRow(0);
			for(int i=0;i<row.getLastCellNum();i++){
				sheet.autoSizeColumn(i);
			}
		}
	}
	/*
	 * An internel function to add map column no against Column headers,
	 * i.e name written in row[0] of that column.
	 * It also map these maps to sheetNames
	 * @param sheet 
	 * @param sheetName
	 */
	protected void mapSheet(XSSFSheet sheet,String sheetName){
		XSSFRow mappingRow = sheet.getRow(0);
		mapping=new HashMap<String,Integer>();
		if(mappingRow==null){
			sheetMap.put(sheetName, mapping);
			return ;
		}
		for(int i=0;i<mappingRow.getLastCellNum();i++){
			mapping.put(mappingRow.getCell(i).getStringCellValue().trim(),i);		
		}
		sheetMap.put(sheetName, mapping);
		return ;
	}
	/*
	 * An internel function to remove key, value pair of column no against Column headers from sheet map.
	 * @param sheetName
	 * @param colNum
	 */
	private void removeColumnMapping(String sheetName, int colNum) {
		Map<String,Integer> mp = sheetMap.get(sheetName);
		for(Entry<String, Integer> et:mp.entrySet()){
			if(et.getValue()==colNum){
				mp.remove(et.getKey());
			}
		}
		
	}
}