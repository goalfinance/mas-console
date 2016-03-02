/**
 * 
 */
package pan.mas.console.core.settings;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author panqingrong
 *
 */
@Component
@ConfigurationProperties(prefix="masconsolecore.jpa-hibernate")
public class JPASettings4Hibernate {
	private boolean generateDdl;
	private boolean showSql;
	private List<String> packagesToScan;
	public boolean getGenerateDdl() {
		return generateDdl;
	}
	public void setGenerateDdl(boolean generateDdl) {
		this.generateDdl = generateDdl;
	}
	public boolean getShowSql() {
		return showSql;
	}
	public void setShowSql(boolean showSql) {
		this.showSql = showSql;
	}
	public List<String> getPackagesToScan() {
		return packagesToScan;
	}
	public void setPackagesToScan(List<String> packagesToScan) {
		this.packagesToScan = packagesToScan;
	}
}
