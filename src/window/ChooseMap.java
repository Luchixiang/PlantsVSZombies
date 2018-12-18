package window;

import java.util.ArrayList;

public class ChooseMap {

    private static int mapNumber = 1;
    private static ArrayList<String> plants = new ArrayList<String>();

    public int getMapNumber() {
        return mapNumber;
    }

    public void setMapNumber(int mapNumber) {
        ChooseMap.mapNumber = mapNumber;
    }

    public void setPlants(ArrayList<String> p){
        this.plants = p;
    }

    public ArrayList<String> getPlants(int n){
//        ArrayList<String> plants = new ArrayList<String>();
//
//        switch (n){
//            case 1:{
//                plants.add("sunFlower");
//                plants.add("onePeaShooter");
//                plants.add("wallNut");
//                plants.add("potatoMine");
//                plants.add("twoPeaShooter");
//                plants.add("eatFlower");
//                plants.add("tallNut");
//            }break;
//            case 2:{
//                plants.add("sunShroom");
//                plants.add("onePeaShooter");
//                plants.add("wallNut");
//                plants.add("potatoMine");
//                plants.add("twoPeaShooter");
//                plants.add("eatFlower");
//                plants.add("tallNut");
//            }break;
//            case 3:{
//                plants.add("lilyPad");
//                plants.add("sunFlower");
//                plants.add("onePeaShooter");
//                plants.add("wallNut");
//                plants.add("potatoMine");
//                plants.add("twoPeaShooter");
//                plants.add("eatFlower");
//                plants.add("tallNut");
//            }
//            break;
//            case 4:{
//                plants.add("lilyPad");
//                plants.add("sunShroom");
//                plants.add("onePeaShooter");
//                plants.add("wallNut");
//                plants.add("potatoMine");
//                plants.add("twoPeaShooter");
//                plants.add("eatFlower");
//                plants.add("tallNut");
//            }
//        }

        return plants;
    }
}
