public interface Observer {
     void update();
     void moveIt(String carName, int x, int y);
     void notifyCarAdd(String modelName, String registrationNumber);
     void notifyCarRemove();

}
