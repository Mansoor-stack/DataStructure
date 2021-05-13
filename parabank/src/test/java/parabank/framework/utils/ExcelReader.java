package parabank.framework.utils;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class ExcelReader {
	
	Fillo fillo;
	Connection conn;
	Recordset rs;
	int records;
	
	public ExcelReader(String testDataPath) {
		fillo = new Fillo();
		try {
			conn = fillo.getConnection(testDataPath);
		} catch (FilloException e) {
			e.printStackTrace();
		}
	}
	
	public void getTestData(String query) {
		try {
			rs = conn.executeQuery(query);
		} catch (FilloException e) {
			e.printStackTrace();
		}
	}
}
