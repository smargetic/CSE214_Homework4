import java.util.Scanner;

public class Simulator {
    public static void main(String[] args) throws IllegalArgumentException, EmptyQueueException{
        //I create the array of 100 names
        String[] names = {"Fermin" , "Sabrina", "Wei", "Mo", "Clemencia", "Lilly", "Lu", "Lyla", "Tom", "Danielle", "Blaine", "Duff", "Ernesto", "Jessica",
                "Cicely", "Zack", "Brianna", "Tyler", "Idalia", "Margetic", "Malik", "Milan", "Son", "April", "Samatha", "Alex", "Marvin", "Ya",
                "Solange", "Heymi", "Sherlene", "Stef", "Merissa", "Recon", "Nikia", "Conrad", "Rolland", "Theresa", "Mercy", "Annie", "Marine",  "Anna",
                "Ardella", "Anne", "Kristine", "Anthony", "Giovanna", "George", "Latasha", "Jackson", "Wilhemina", "James", "Ria", "Stefanos",
                "Aubrey", "Micheal", "Sha", "Charlie", "Danny", "Jack", "Silas", "Harry", "Chi", "Noah", "Dottie", "Pam", "Erasmo", "Oliver", "Candy", "Sara",
                "Hung", "Grace", "Cythia", "North", "Ha", "Henry", "Denna", "Aleksandra", "Eileen", "Will", "Ruth", "Isa", "Dave", "Emily", "Kathlene", "Olivia",
                "Keena", "Mia", "Theo", "Amelia", "Larissa", "Marissa", "Kathyrn", "Jer", "Keisha", "Kesha", "Wendi", "Barack", "Joe", "Bob"};

        String[] flightClass = {"First", "Business", "Premium", "Economy"};

        String[] locations;

        Scanner scanner = new Scanner(System.in);
        int numFlights, numArrived;
        double arrivalProbability;

        Airplane[] airplanes;


        System.out.println("Welcome to ARK Private International Airport!\n");
        System.out.println("Enter the number of airplanes waiting to take off: ");
        String tempNumFlights = scanner.nextLine().trim();
        numFlights = Integer.parseInt(tempNumFlights);
        airplanes = new Airplane[numFlights];
        for(int i=1; i<=numFlights; i++){
            System.out.println("\nEnter the destination of flight " + i);
            String destination = scanner.nextLine().trim();
            int tempTime = 30;
            Airplane tempAirplane = new Airplane(destination, tempTime);
            airplanes[i-1] = tempAirplane;

        }

        System.out.println("Enter the arrival probability: ");
        String tempArrivalProbability = scanner.nextLine().trim();
        arrivalProbability = Double.parseDouble(tempArrivalProbability);
        if ((arrivalProbability>1.0) || (arrivalProbability<0.0)){
            throw new IllegalArgumentException();
        }


        System.out.println("Enter the duration of the simulation: ");
        String tempDuration = scanner.nextLine().trim();
        int duration = Integer.parseInt(tempDuration);

        //there cannot be any passengers at minute 0, because it takes 30 seconds for a person to arrive
        System.out.println("\nMinute 0:");
        System.out.println("\nThere are " + numFlights + " airplanes at the airport.");
        for(int i=0;i<numFlights;i++){
            int tempNum = i+1;
            System.out.print("\nFlight " + tempNum + " is to " + airplanes[i].getDestinationCity() + " with no passengers.");
        }

        int i =0;
        int flightsArrived =0;
        int tempTime=0;

        while(i<duration) {
            //the passengers are added before the minute presented, therefore the data presented happens before
            int tempMin = i +1;
            System.out.print("\n\nMinute " + tempMin);

            //20 minutes have passed, a flight has arrived
            if (((i + 1)%20)==0) {
                System.out.print("\nA new flight has arrived. It will be refueling for now!");
                flightsArrived++;
                tempTime = i+1;
            }

            //the airplane has been fueled
            if((flightsArrived==1)&&((i+1)==(tempTime+10))){
                //numFlights++;
                System.out.print("\n\nThe flight that returned at " + tempTime + "minutes is ready to depart.");
                int tick =0;

                //I ask till I receive a valid answer
                while(tick==0) {
                    System.out.print("\nWould you like to set a destination for it? :");
                    String tempAnswer = scanner.nextLine().trim();
                    String answer = tempAnswer.toLowerCase();
                    if (answer.equals("y")) {
                        System.out.print("\nWhere would you like for it to go? : ");
                        String newDestination = scanner.nextLine().trim();

                        int time = 30;

                        //I expand the array of airplanes and include the new airplane
                        Airplane[] tempAirplanes = new Airplane[numFlights+1];
                        for(int m=0; m<numFlights;m++){
                            tempAirplanes[m] = airplanes[m];
                        }
                        tempAirplanes[numFlights] = new Airplane(newDestination, time);
                        airplanes = tempAirplanes;

                        numFlights++;
                        tick = 1;
                        flightsArrived--;
                    }

                    if (answer.equals("n")) {
                        tick=1;
                    }
                }
            }

            if((flightsArrived==1)&&(((i+1)-tempTime)>14)&&(((i+1)-tempTime)%5==0)){
                System.out.print("\n\nThe flight that returned at " + tempTime + "minutes is ready to depart.");
                int tick =0;

                //I ask till I receive a valid answer
                while(tick==0) {
                    System.out.print("\nWould you like to set a destination for it? :");
                    String tempAnswer = scanner.nextLine().trim();
                    String answer = tempAnswer.toLowerCase();
                    if (answer.equals("y")) {
                        System.out.print("\nWhere would you like for it to go? : ");
                        String newDestination = scanner.nextLine().trim();

                        int time = 30;

                        //I expand the array of airplanes and include the new airplane
                        Airplane[] tempAirplanes = new Airplane[numFlights+1];
                        for(int m=0; m<numFlights;m++){
                            tempAirplanes[m] = airplanes[m];
                        }
                        tempAirplanes[numFlights] = new Airplane(newDestination, time);
                        airplanes = tempAirplanes;

                        numFlights++;
                        tick = 1;
                        flightsArrived--;
                    }

                    if (answer.equals("n")) {
                        tick=1;
                    }
                }
            }
            //I decrement the time till take off
            for(int m=0; m<numFlights; m++){
                airplanes[m].setTimeToTakeOff(airplanes[m].getTimeToTakeOff()-1);
            }

            int numberTakeOff=0;

            //I check to see if any of the flights take off
            for(int j=0; j<numFlights;j++){
                if(airplanes[j].getTimeToTakeOff()==0){
                    numberTakeOff++;
                }
            }
            //I tell the user how many are taking off
            if (numberTakeOff>0){
                System.out.print("\nThere are " + numberTakeOff + " flights are taking off.");
            }

            int tempNumFlights2 = numFlights;

            //I remove the airplane
            for(int j=0; j<tempNumFlights2;j++){
                int tempJ = j+1;
                if(airplanes[j].getTimeToTakeOff()==0){
                    System.out.print("\nFlight " + tempJ + " is going to " + airplanes[j].getDestinationCity() + " with " + airplanes[j].getLength() + " passengers.");
                    tempNumFlights2--;
                    Airplane[] tempAirplanes = new Airplane[tempNumFlights2];
                    //the array of airplanes is ordered by time, therefore I can just remove from the beginning case
                    for(int m=0;m<tempNumFlights2;m++){
                        tempAirplanes[m] = airplanes[m+1];
                    }
                    airplanes = tempAirplanes;
                    j--;
                }
            }
            numFlights = tempNumFlights2; //DOUBLE CHECK IF NECESSARY


            System.out.print("\nThere are " + numFlights + " airplanes at the airport");

            //I see if there are people to board each airplane
            for (int j = 0; j < numFlights; j++) {
                //every 30 seconds a passenger can come
                if ((airplanes[j].arrival(arrivalProbability)) && (airplanes[j].getPassengerCapacity() != 35)) {
                    //I create the person with corresponding values

                    //passenger name
                    int tempNameInt1 = (int)(Math.random()*100);
                    String tempName1 = names[tempNameInt1];


                    //passenger class
                    int tempClassInt1 = (int)(Math.random()*4);
                    String tempClass1 = flightClass[tempClassInt1];

                    //destination
                    String tempDestination1 = airplanes[j].getDestinationCity();

                    double tempTime1 = (double)i + .5;


                    Passenger tempPassenger1 = new Passenger(tempDestination1, tempTime1, tempClass1, tempName1);

                    airplanes[j].enqueue(tempPassenger1);

                }
                if ((airplanes[j].arrival(arrivalProbability)) && (airplanes[j].getPassengerCapacity() != 35)) {
                    //I create the person with corresponding values

                    //passenger name
                    int tempNameInt2 = (int)(Math.random()*100);
                    String tempName2 = names[tempNameInt2];


                    //passenger class
                    int tempClassInt2 = (int)(Math.random()*4);
                    String tempClass2 = flightClass[tempClassInt2];

                    //destination
                    String tempDestination2 = airplanes[j].getDestinationCity();

                    double tempTime2 = (double)i + 1.0;

                    Passenger tempPassenger2 = new Passenger(tempDestination2, tempTime2, tempClass2, tempName2);


                    airplanes[j].enqueue(tempPassenger2);

                }
                int tempJ = j+1;
                System.out.print("\nFlight " + tempJ + " is to " + airplanes[j].getDestinationCity() + " with " + airplanes[j].getLength() + " passengers");
            }

            System.out.println("\n");

            //Once everyone has been added, I print the people
            for (int j = 0; j < numFlights; j++) {
                int tempJ = j+1;
                System.out.print("\n" + tempJ + " (" + airplanes[j].getDestinationCity() + "): ");
                //I print out all the passengers in the queue
                for (int m = 0; m < airplanes[j].getLength(); m++) {
                    Passenger newP = airplanes[j].peekTraversal(m); //= new Passenger();

                    System.out.print("[" + newP.getName() + ", " + newP.getTravelClass() + ", " + newP.getTime() + "] ");
                }
            }


            //If time is up, the program ends
            if((i+1) == duration){
                System.out.print("\n\n Thank you for working with us. If you need more updates, please let us know!");
                System.out.print("\nGood Bye!");
                System.exit(0);
            }

            i++;


        }

    }
}
