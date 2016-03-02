/**
 * 
 */
package pan.mas.console.output.web.dojo;

/**
 * @author panqingrong
 *
 */
public class DojoUtils {
	public static PaginationInfo getPaginationInfo(String paginationInfo){
		assert paginationInfo != null && !paginationInfo.equals("");
		int i = paginationInfo.indexOf('=');
		assert i >= 0;
	
		String range = paginationInfo.substring(i + 1);
		String[] rangeData = range.split("-");
		
		assert rangeData.length == 2;
		
		PaginationInfo pInfo = new PaginationInfo();
		pInfo.setStart(Integer.valueOf(rangeData[0]));
		pInfo.setCount(Integer.valueOf(rangeData[1]) - Integer.valueOf(rangeData[0]) + 1);
		pInfo.setPageNum(((Integer.valueOf(rangeData[1]) + 1) / pInfo.getCount() - 1)); 
		
		return pInfo;
	}
}
