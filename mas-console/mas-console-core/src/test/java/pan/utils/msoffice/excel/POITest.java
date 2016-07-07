/**
 * 
 */
package pan.utils.msoffice.excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author panqingrong
 *
 */
public class POITest {
	
	private void createSomeElements(Workbook workbook){
		Sheet sheet = workbook.createSheet("test sheet");
		Row row0 = sheet.createRow(0);
		Cell cell0 = row0.createCell(0);
		cell0.setCellValue("test1");
		
		Cell cell1 = row0.createCell(1);
		cell1.setCellValue("test2");
	}
	
	private void verifyElements(Workbook workbook){
		Sheet sheet = workbook.getSheetAt(0);
		assertThat("test sheet", equalTo(sheet.getSheetName()));
		assertThat(sheet.getPhysicalNumberOfRows(), equalTo(1));
		
		Row row0 = sheet.getRow(0);
		assertThat(row0.getPhysicalNumberOfCells(), equalTo(2));
		
		Cell cell0 = row0.getCell(0); 
		assertThat(cell0.getStringCellValue(), equalTo("test1"));
		
		Cell cell1 = row0.getCell(1);
		assertThat(cell1.getStringCellValue(), equalTo("test2"));
		
		
	}
	
	@Test
	public void getStarted(){
		Workbook wb = new HSSFWorkbook();
		FileOutputStream fileOut = null;
		File tmpFile = null;
		try {
			tmpFile = File.createTempFile("tmp", ".xls");
			tmpFile.deleteOnExit();
			fileOut = new FileOutputStream(tmpFile);
			createSomeElements(wb);
			verifyElements(wb);
			wb.write(fileOut);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (fileOut != null){
				try {
					fileOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
