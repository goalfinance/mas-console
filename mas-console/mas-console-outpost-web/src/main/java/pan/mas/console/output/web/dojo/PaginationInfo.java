/**
 * 
 */
package pan.mas.console.output.web.dojo;

import lombok.Data;

/**
 * @author panqingrong
 *
 */
@Data
public class PaginationInfo {
	private int start;
	private int count;
	//start from zero.
	private int pageNum;
}
