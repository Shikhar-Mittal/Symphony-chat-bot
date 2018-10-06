import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;

public class getIntent {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static ArrayList getCSV(String quant, String[] param, String company, String action, String market)
			throws NumberFormatException, JSONException {

		ArrayList extract = new ArrayList();
		try {

			if (StringUtils.isBlank(quant) && !StringUtils.isBlank(company) && StringUtils.isBlank(market)) 
			{
				extract = CSVReader.securityResut(company, action);
			}
			else if (!StringUtils.isBlank(quant) && StringUtils.isBlank(company) && param.length == 0
					&& StringUtils.isBlank(market))
			{
				extract = CSVReader.TotalValueCheck("Notional", quant);
			} 
			else if ((((param.length == 0 || StringUtils.isBlank(quant)) && StringUtils.isBlank(company))
					&& StringUtils.isBlank(market)) || (param.length == 0 && StringUtils.isBlank(quant))) 
			{
				extract.add(
						"Sorry! Request couldn't be processed;" + "\n" + "Please try one of the following commands:");
				extract.add(HTMLTable.createTable());
			}
			else if (Arrays.asList(param).contains("Units") && StringUtils.isBlank(market)) 
			{
				if (StringUtils.isNotBlank(company)) {
					extract = CSVReader.ValueCheckSecurity("Units", company, quant);
				} else {
					extract = CSVReader.TotalValueCheck("Units", quant);
				}
			}
			else if (!StringUtils.isBlank(market))
				extract = CSVReader.TotalLossGain(market, quant);
			else 
			{
				if (StringUtils.isNotBlank(company)) {
					extract = CSVReader.ValueCheckSecurity("Notional", company, quant);
				} else {
					extract = CSVReader.TotalValueCheck("Notional", quant);
				}
			}

		} catch (Exception ex) {
			System.out.println(ex);
			extract.add("Sorry! Request couldn't be processed;" + "\n" + "Please try one of the following commands:");
			extract.add(HTMLTable.createTable());
		}
		return extract;
	}

}