public class Airplane extends Passenger{
    //    String[] names = {"John", "Chris", "Tom", "Bob"};

    private String departureCity, destinationCity;
    private int timeToTakeOff, capacity;
   // private final int CAPACITY;

    //getters and setters so that these values can be accessed
    public void setDepartureCity(String departureCity){
        this.departureCity = departureCity;
    }

    public void setDestinationCity(String destinationCity){
        this.destinationCity = destinationCity;
    }

    public void setTimeToTakeOff(int timeToTakeOff){
        this.timeToTakeOff = timeToTakeOff;
    }

//    public void setCapacity(int capacity){
//        this.capacity = capacity;
//    }

    public String getDepartureCity(){
        return this.departureCity;
    }

    public String getDestinationCity(){
        return this.destinationCity;
    }

    public int getTimeToTakeOff(){
        return this.timeToTakeOff;
    }

//    public int getCapacity(){
//        return this.capacity;
//    }

   // private final int CAPACITY = getCapacity();
    private final int CAPACITY = 35;

    //the airplane has a queue of passengers
    private Passenger[] passengers;
    private int front, rear;
    private int passengerCapacity;

    public int getPassengerCapacity(){
        return this.passengerCapacity;
    }


    public Airplane(){
//        front = -1;
//        rear = -1;
//        passengerCapacity = 1;
//        passengers = new Passenger[passengerCapacity];
    }

    public Airplane(String destinationCity, int timeToTakeOff){
        this.destinationCity = destinationCity;
        this.timeToTakeOff = timeToTakeOff;
        this.front = -1;
        this.rear = -1;
        this.passengerCapacity =1;
        this.passengers = new Passenger[passengerCapacity];
    }
//    public Airplane(String departureCity, int capacity) {
//        //this.CAPACITY = capacity;
//        this.departureCity = departureCity;
//    }

    private int numFirst, numBusiness, numPremium, numEconomy;
    private int tempPassengerCapacity = passengerCapacity;

    public void enqueue(Passenger p){
        //I check if people have been added to the first spot of the array
        int atCapacity =0;
        if(isEmpty()){
            this.front = 0;
            this.rear = 0;
            passengers[front] = p;
            this.numFirst =0;
            this.numBusiness =0;
            this.numPremium =0;
            this.numEconomy =0;
            if(p.getTravelClass().equals("First")){
                numFirst=1;
            }
            if(p.getTravelClass().equals("Business")){
                numBusiness=1;
            }
            if(p.getTravelClass().equals("Premium")){
                numPremium=1;
            }
            if(p.getTravelClass().equals("Economy")){
                numEconomy=1;
            }
        } else {
            //I create a temp array with the necessary size

            int tick = 1;
            //If the person is in first class, I add them after all other first class people
            if(p.getTravelClass().equals("First")){

                if(this.numFirst == 5){
                   // throw new FullQueueException("There are too many people in this class");
                    System.out.println("There are too many people in this class");

                    atCapacity =1;
                }
                if(atCapacity==0) {
                    passengerCapacity++;
                    Passenger[] temp = new Passenger[passengerCapacity];
                    for (int i = 0; i <= rear; i++) {
                        boolean temp1 = passengers[i].getTravelClass().equals("First");

                        if (temp1) {
                            temp[i] = passengers[i];
                        } else {
                            if (tick == 1) {
                                temp[i] = p;
                                temp[i+1] = passengers[i];
                                tick = 0;
                            } else {
                                temp[i+1] = passengers[i];
                            }
                        }
                    }
                    if (tick == 1) {
                        temp[rear + 1] = p;
                    }
                    this.numFirst++;
                    passengers = temp;
                    rear++;
                }
            }
            //if the person is in business class, I add after all before
            if(p.getTravelClass().equals("Business")){
                if(this.numBusiness==5){
                  //  throw new FullQueueException("There are too many people in this class");
                    System.out.println("There are too many people in this class");
                    atCapacity =1;
                }
                if (atCapacity==0) {
                    passengerCapacity++;
                    Passenger[] temp = new Passenger[passengerCapacity];
                    for (int i = 0; i <= rear; i++) {

                        boolean temp1 = passengers[i].getTravelClass().equals("First");
                        boolean temp2 = passengers[i].getTravelClass().equals("Business");


                        if (temp1 || temp2) {
                            temp[i] = passengers[i];
                        } else {
                            if (tick == 1) {
                                temp[i] = p;
                                temp[i+1] = passengers[i];
                                tick = 0;
                            } else {
                                temp[i+1] = passengers[i];
                            }
                        }
                    }
                    if (tick == 1) {
                        temp[rear + 1] = p;
                    }
                    this.numBusiness++;
                    passengers = temp;
                    rear++;
                }

            }
            //If the person is in premium class, I add after all before
            if(p.getTravelClass().equals("Premium")){
                if(this.numPremium==10){
                  //  throw new FullQueueException("There are too many people in this class");
                    System.out.println("There are too many people in this class");
                    atCapacity =1;
                }
                if(atCapacity==0) {
                    passengerCapacity++;
                    Passenger[] temp = new Passenger[passengerCapacity];
                    for (int i = 0; i <= rear; i++) {
                        boolean temp1 = passengers[i].getTravelClass().equals("First");
                        boolean temp2 = passengers[i].getTravelClass().equals("Business");
                        boolean temp3 = passengers[i].getTravelClass().equals("Premium");

                        if (temp1 || temp2 || temp3) {
                            temp[i] = passengers[i];
                        } else {
                            if (tick == 1) {
                                temp[i] = p;
                                temp[i+1] = passengers[i];
                                tick = 0;
                            } else {
                                temp[i+1] = passengers[i];
                            }
                        }
                        if (tick == 1) {
                            temp[rear + 1] = p;
                        }
                    }
                    this.numPremium++;
                    passengers = temp;
                    rear++;
                }

            }
            //if the person is in economy class, I add to the end of the queue
            if(p.getTravelClass().equals("Economy")){
                if(this.numEconomy==15){
                    //throw new FullQueueException("There are too many people in this class");
                    System.out.println("There are too many people in this class");
                    atCapacity =1;
                }
                if(atCapacity==0) {
                    passengerCapacity++;
                    Passenger[] temp = new Passenger[passengerCapacity];
                    for (int i = 0; i <= rear; i++) {
                        temp[i] = passengers[i];
                    }
                    temp[rear + 1] = p;
                    this.numEconomy++;
                    passengers = temp;
                    rear++;
                }

            }


        }
    }

    public Passenger dequeue() throws EmptyQueueException{
        //I check if the array is empty
        if(isEmpty()){
            throw new EmptyQueueException();
        }
        //if the array is not empty, I take the first person
        Passenger p = passengers[front];
        //if the first person is found at the first index, I remove and readjust the array to be empty
        if(front == rear){
            front = -1;
            rear = -1;
        } else {
            //I copy over the array to another temp array and readjust the size
            Passenger temp[] = new Passenger[rear];
            for(int i=0; i<rear; i++){
                temp[i] = passengers[i+1];
            }
            passengers = temp;
        }
        return p;
    }

    public Passenger peek() throws EmptyQueueException{
        if(isEmpty()){
            throw new EmptyQueueException();
        }
        Passenger p = passengers[front];

        return p;
    }

    public Passenger peekTraversal(int location) {
        Passenger p = passengers[location];
        return p;

    }

    public int size(){
        return (rear +1);
    }

    public boolean isEmpty(){
        if (front == -1){
            return true;
        }
        return false;
    }

    public int unload(String arrivedDestinationCity){
        int number = -1;
        if(arrivedDestinationCity.equals(destinationCity)){
            Passenger temp[] = new Passenger[1];
            number = passengers.length;
            passengers = temp;
        }
        return number;
    }

    public boolean arrival(double probability){
        return (Math.random()<probability);
    }

    private int length;

    public int getLength(){
        this.length = passengers.length;
       // System.out.println("THIS IS THE LENGTH: " + length);
        return length;
    }

}
