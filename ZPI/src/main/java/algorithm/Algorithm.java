package algorithm;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.Car;
import model.Driver;
import model.Order;

import org.parse4j.ParseException;
import org.parse4j.ParseGeoPoint;
import org.parse4j.ParseObject;
import org.parse4j.ParsePointer;
import org.parse4j.ParsePush;
import org.parse4j.ParseQuery;

public class Algorithm implements Runnable {
    List<Driver> drivers;
    Order order;

    public boolean chooseDriver(Order order){
        // TODO  tutaj stworzenie listy wszystkich dostêpnych kierowców. Wywo³anie metod sortuj¹cych i rozpoczêcie powiadamiania kierowców.
        ParseQuery<Driver> query = ParseQuery.getQuery(Driver.class);
        query.whereEqualTo("status", 0);
        try {
            drivers = query.find();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //Mo¿na zrobiæ coœ bardziej wydajnego ni¿ to chyba
        Collections.sort(drivers, new Comparator<Driver>(){

            @Override
            public int compare(Driver arg0, Driver arg1) {
                //nie jestem pewien czy to zadzia³a ale chyba tak
                return Double.compare(countDistanceInStraightLine(arg0.getCar()), countDistanceInStraightLine(arg1.getCar()));
            }

        });

        // TODO tutaj wybieramy kilka pierwszych z listy i ich sortujemy wed³ug drogi rzeczywistej

        ArrayList<Driver> sortedDrivers = new ArrayList<Driver>();
        for(int i = 0; i < 6; i++){
            sortedDrivers.add(drivers.get(i));
        }

        this.order = order;

        this.run();

        return true;
    }

    public double countDistanceInStraightLine(Car car){
        ParseGeoPoint driverPosition = car.getCurrentPosition();
        ParseGeoPoint clientPosition = order.getPickupAddressGeo();

        double distance = driverPosition.distanceInKilometersTo(clientPosition);
        return distance;
    }

    public void notifyDriver(Driver driver){
        //Nie wiem czy trzeba tu doda inicjalizacj bazy
        ParsePush push = new ParsePush();
        //ArrayList<String> channels = new ArrayList<String>();
        //sprawdziæ jakie potrzebne s¹ do listy rzeczy czy: ["",id] czy [id] czy inaczej
        ParseObject pointer = (ParseObject) driver.getParseObject("userId");
        String userId = (String)pointer.getObjectId();
        String channel = "user_" + userId;
        push.setOrderMessage(order.getObjectId());
        push.setChannel(channel);
        // nie wiem czy nie lepiej sendInBackground
        try {
            push.send();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        boolean assigned = false;
        int i = 0;
        while(!assigned && i < drivers.size()){
            notifyDriver(drivers.get(i));

            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            ParsePointer pointer = (ParsePointer) order.get("driver");

            // nie wiem czy na pewno zwróci null jak nie jest przypisany
            if(pointer != null){
                assigned = true;
            }else{
                i++;
            }
        }

        if(!assigned){
            // co jeœli nikt nie odpowie
        }
    }


}
