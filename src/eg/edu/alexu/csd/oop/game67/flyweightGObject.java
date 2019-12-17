package eg.edu.alexu.csd.oop.game67;

import eg.edu.alexu.csd.oop.game.GameObject;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class flyweightGObject {
    int max_size = 20;
    int count = 0;
    private static flyweightGObject instance;
    String path[] = {"/plate1.png", "/green.png","/bomb.png","/orange.png","/black.png"};
    Color color[]={Color.PINK,Color.orange,Color.red,Color.CYAN,Color.green};
    List<GameObject> moving = new LinkedList<GameObject>();

    private flyweightGObject() {
    }

    public static flyweightGObject getInstances() {
        if (instance == null) {
            instance = new flyweightGObject();
        }
        return instance;
    }

    public List<GameObject> check_add(List<GameObject> moving, int screenHeight, int screenWidth) {
        this.moving = moving;
if(this.moving.size()>max_size)
{}
 else if (this.moving.size() == max_size) {
            for (int i = 0; i < moving.size(); i++) {
                if (moving.get(i).getY() == screenHeight) {
                  GameObject game_object=  moving.remove(i);
                    moving.add(game_object);

                }
            }

        } else if (moving.size() < max_size) {
            for (int i = 0; i < max_size - moving.size(); i++) {
                moving.add(new MyGameObject((int) (Math.random() * screenWidth), -1 * (int) (Math.random() * screenHeight), false, path[(int) (Math.random() * 5)]));
                 System.out.println(moving.size());
            }
        }

        return moving;
    }

}

