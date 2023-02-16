package entity.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Vector;

import org.springframework.jdbc.core.RowMapper;

public class StringListRowMapper implements RowMapper<List<String>> {
	private List<String> columnNames = new Vector<>();
	
	@Override
	public List<String> mapRow(ResultSet rs, int rowNum) throws SQLException {
	    int columnCount = rs.getMetaData().getColumnCount();
	    List<String> rowData = new Vector<>(columnCount);
	    
	    for (int i = 1; i <= columnCount; i++) {
	        if (rowNum == 0) {
	            columnNames.add(rs.getMetaData().getColumnName(i));
	        }
	        
	        int columnType = rs.getMetaData().getColumnType(i);
	        String data = rs.getString(i);
	        if(data == null) {
	        	rowData.add("null");
	        	continue;
	        }
	        
	        switch (columnType) {
	        	case Types.NUMERIC:
	            case Types.INTEGER:
	            case Types.FLOAT:
	            case Types.DOUBLE:
	                rowData.add(format(data));
	        		break;
	            default:
	                rowData.add(data);
	        }
	    }
	    return rowData;
	}
	
	private String format(String ds){
		double d = Math.round(Double.parseDouble(ds)*100)/100.0; //소수 2자리 반올림
		if (Math.floor(d) == d && !Double.isInfinite(d)) return String.format("%d",(long)d);
	    else return String.valueOf(d);
	}
	
	public List<String> getColumnNames() {
		return columnNames;
	}
}