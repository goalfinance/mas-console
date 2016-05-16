/**
 * 
 */
package pan.mas.console.output.web.dojo;

import org.springframework.data.domain.Page;

/**
 * @author panqingrong
 *
 */
public class DojoUtils {
	public static final int DEFAULT_COUNT_PER_PAGE = 25;
	
	public static PaginationInfo getPaginationInfo(String paginationInfo){
		assert paginationInfo != null && !paginationInfo.equals("");
		int i = paginationInfo.indexOf('=');
		assert i >= 0;
	
		String range = paginationInfo.substring(i + 1);
		
		//when use the client-side filter, the pagination info only include the start record number, like 'item=0-', so do some special process. 
		int spliterIdx = range.indexOf("-");
		if (spliterIdx == range.length() - 1){
			String[] temp = range.split("-");
			int end = Integer.valueOf(temp[0]) + DEFAULT_COUNT_PER_PAGE - 1; 
			range +=  String.valueOf(end);
		}
		String[] rangeData = range.split("-");
		
		assert rangeData.length == 2;
	
		PaginationInfo pInfo = new PaginationInfo();
		pInfo.setStart(Integer.valueOf(rangeData[0]));
		pInfo.setCount(Integer.valueOf(rangeData[1]) - Integer.valueOf(rangeData[0]) + 1);
		pInfo.setPageNum(((Integer.valueOf(rangeData[1]) + 1) / pInfo.getCount() - 1)); 
		
		return pInfo;	
		
	}
	
	public static String getHttpContentRange(PaginationInfo pInfo, Page<?> page) {
		String contentRange = "items " + pInfo.getStart() + "-"
				+ (pInfo.getStart() + (page.getContent().size() < pInfo.getCount()
						? page.getContent().size() : pInfo.getCount()))
				+ "/" + page.getTotalElements();
		return contentRange;
	}
}
