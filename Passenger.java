public class Passenger {
    private double time;
    private String departingLocation, destinationLocation, travelClass, name;

    public Passenger(String destinationLocation, double time, String travelClass, String name){
 //       this.departingLocation = departingLocation;
        this.destinationLocation = destinationLocation;
        this.time = time;
        this.travelClass = travelClass;
        this.name = name;
    }

    public Passenger(){}

    //getters & setters
    public void setDepartingLocation(String departingLocation){
        this.departingLocation = departingLocation;
    }

    public void setDestinationLocation(String destinationLocation){
        this.destinationLocation = destinationLocation;
    }

    public void setTime(double time){
        this.time = time;
    }

    public void setTravelClass(String travelClass){
        this.travelClass = travelClass;
    }

    public String getDepartingLocation(){
        return this.departingLocation;
    }

    public String getDestinationLocation(){
        return this.destinationLocation;
    }

    public double getTime(){
        return this.time;
    }

    public String getTravelClass(){
        return this.travelClass;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public boolean passangersEqual(Passenger p){
        if(!this.name.equals(p.getName())){
            return false;
        }
//        if(!this.destinationLocation.equals(p.getDestinationLocation())){
//            return false;
//        }
        if(!this.departingLocation.equals(p.getDepartingLocation())){
            return false;
        }
        if(this.time != p.getTime()){
            return false;
        }
        if(!this.travelClass.equals(p.getTravelClass())){
            return false;
        }
        return true;
    }


}
