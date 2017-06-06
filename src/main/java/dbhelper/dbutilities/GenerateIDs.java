package dbhelper.dbutilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GenerateIDs {
	
	static public int generateIntlInstruIDs(String databaseTable) {
		int intlInstruID = 0;

		try {
			Connection c = MySql.connect();
			String sql = "SELECT MAX(cast(InstrumentID AS UNSIGNED)) + 1 AS NEWID FROM "+databaseTable+"  ";
						
			
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				intlInstruID = rs.getInt("NEWID");
			}
			rs.close();
			pst.close();

			MySql.close(c);


		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return intlInstruID;

	}
}
