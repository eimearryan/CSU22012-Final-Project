 import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
 * 3. Searching for all trips with a given arrival time, returning full details of all trips matching the
 *	criteria (zero, one or more), sorted by trip id
 *	Arrival time should be provided by the user as hh:mm:ss. When reading in stop_times.txt file you
 *	will need to remove all invalid times, e.g., there are times in the file that start at 27/28 hours, so are
 *	clearly invalid. Maximum time allowed is 23:59:59. 
 */

public class ArrivalTimes {
		
	public static boolean checkValidTime(String input) {
		boolean valid;
		String arrivalTime = input;
		String[] str = arrivalTime.split(":");

		int hours = Integer.parseInt(str[0].trim());
		int minutes = Integer.parseInt(str[1].trim());
		int seconds = Integer.parseInt(str[2].trim());

		if (hours <= 23 && minutes <= 59 && seconds <= 59 &&
				hours >= 0 && minutes >= 0 && seconds >= 0){
			valid = true;
		}else {
			valid = false;
		}
		return valid;
	}


	public static void removeInvalidTimes(File filename, ArrayList<String> ArrayList) throws FileNotFoundException{

		Scanner input = new Scanner(new FileReader(filename));
		input.nextLine();
		while(input.hasNextLine())
		{
			String line = input.nextLine();
			String[] tripDetails = line.split(",");
			String arrivalTime = tripDetails[1];

			if(checkValidTime(arrivalTime)) {
				ArrayList.add(line);
			}

		}	

	}
	
	public static void showTripDetails(ArrayList<String> ArrayList) {

		String [][] trips = sortByTripId(ArrayList);	
		for(int i=0; i< trips.length;i++) {

			String tripID = trips[i][0];
			String arrivalTime = trips[i][1];
			String departureTime = trips[i][2];
			String stopID = trips[i][3];
			String stopSequence = trips[i][4];
			String stopHeadsign = trips[i][5];
			String pickupType = trips[i][6];
			String dropOffType = trips[i][7];
			//	String shapeDistTraveled = str[8];

			System.out.println("Trip ID: "+tripID+"\n"+
					"Arrival Time: "+arrivalTime+"\n"+
					"Departure Time: "+departureTime+"\n"+
					"Stop ID: "+stopID+"\n"+
					"Stop Sequence: "+stopSequence+"\n"+
					"Stop Headsign: "+stopHeadsign+"\n"+
					"Pickup Type: "+pickupType+"\n"+
					"Dropoff Type: "+dropOffType+"\n"
					//	"Shape Distance Travelled: "+shapeDistTraveled+"\n\n"
					);

		}
	}


	public static String[][] sortByTripId(ArrayList<String> arrayList) {

		// create the 2D array
		String[][] allValid = new String[arrayList.size()][8];

		for(int row=0; row < arrayList.size();row++) {
			String tripDetails = arrayList.get(row);
			String[] str = tripDetails.split(",");

			for (int col = 0; col < allValid[row].length; col++) {
				allValid[row][col] = str[col];

			}
		}

		// sort the 2D array
		// bubble sort
		boolean sorted = false;
		String[] temp;
		while (!sorted) {
			sorted = true;
			for (int i = 0; i < allValid.length - 1; i++) {
				int currentID = Integer.parseInt(allValid[i][0]);
				int nextID = Integer.parseInt(allValid[i + 1][0]);
				if (currentID > nextID) {
					temp = allValid[i];
					allValid[i] = allValid[i + 1];
					allValid[i + 1] = temp;
					sorted = false;
				}
			}
		}
		return allValid;        	
	}
}
