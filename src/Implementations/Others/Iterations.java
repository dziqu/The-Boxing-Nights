package Implementations.Others;

import java.io.File;
import java.math.BigInteger;
import java.util.List;

import Enums.DefaultControlSettings;
import Enums.Names;
import Interfaces.Collator;

public interface Iterations {
	
	public static BigInteger incrementNumberOdRunOfProgram() {
		List <String> dataList = IO.scan(new File(Names.SETTINGS_DIRECTORY.getLocation()));
		int dataListSize = dataList.size();
//		TODO: IO.printL(dataListSize);
		String numberStr = "";
		if (Collator.collateTo(dataListSize, 0) != 0) {
			numberStr = dataList.get(DefaultControlSettings.NUMBER_OF_RUN_THE_PROGRAM.getLineNumber());
		}
		BigInteger number = null;
		if (!Collator.regexNumber(numberStr) || Collator.collateTo(numberStr, "") == 0) {
			number = new BigInteger(DefaultControlSettings.NUMBER_OF_RUN_THE_PROGRAM.getDefaultValue());
		}
		else number = new BigInteger(numberStr);
		
		number = number.add(BigInteger.valueOf(1));
		IO.print(new File(Names.SETTINGS_DIRECTORY.getLocation()), String.valueOf(number));
		return number;
	}

}
