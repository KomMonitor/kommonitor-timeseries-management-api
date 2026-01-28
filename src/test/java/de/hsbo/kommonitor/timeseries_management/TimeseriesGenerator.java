package de.hsbo.kommonitor.timeseries_management;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Random;

public class TimeseriesGenerator {

	private final String LINE_SEPARATOR = System.getProperty("line.separator");

	private Random random;

	private ZonedDateTime startDate;

	private ZonedDateTime currentDataDate;

	private DateTimeFormatter dateTimeFormatter;

	public TimeseriesGenerator() {
		this("2007-12-03T10:15:30+01:00[Europe/Paris]");
	}

	public TimeseriesGenerator(String isoZonedDateTime) {
		random = new Random();
		startDate = ZonedDateTime.parse(isoZonedDateTime);
		dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY-MM-dd'T'hh:mm:ssZ");
	}

	public String generateTimeseriesArray(int count) {
		StringBuffer stringBuffer = new StringBuffer();
		currentDataDate = startDate;
		stringBuffer.append("[" + LINE_SEPARATOR);
		int currentCounter = 0;
		while (currentCounter < count) {
			currentCounter++;
			stringBuffer.append(generateTimeseriesData(5, ChronoUnit.MINUTES, (currentCounter < count)));
		}
		stringBuffer.append("]");
		return stringBuffer.toString();
	}

	public String generateTimeseriesData(long amountToAdd, TemporalUnit unit, boolean addComma) {
		currentDataDate = currentDataDate.plus(amountToAdd, unit);
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("   {" + LINE_SEPARATOR);
		stringBuffer.append("      \"value\": " + random.nextDouble() + "," + LINE_SEPARATOR);
		stringBuffer.append("      \"timestamp\": \"" + currentDataDate.format(dateTimeFormatter) + "\"" + LINE_SEPARATOR);
		stringBuffer.append("   }" + (addComma ? "," : "") + LINE_SEPARATOR);
		return stringBuffer.toString();
	}

	public static void main(String[] args) {
		System.out.println(new TimeseriesGenerator().generateTimeseriesArray(500));
	}

}
