package eg.edu.alexu.csd.oop.game67;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class MyGameWorld implements World {
    private int score = 0;
    private final int width;
    private final int height;
    private final List<GameObject> constant = new LinkedList<GameObject>();
    private List<GameObject> moving = new LinkedList<GameObject>();
    private final List<GameObject> control = new LinkedList<GameObject>();
    flyweightGObject fwo = flyweightGObject.getInstances();

    public MyGameWorld(int screenWidth, int screenHeight) {
        width = screenWidth;
        height = screenHeight;
        MyGameObject oo = new MyGameObject(0, 0, true, "/mickey2.png");
        constant.add(oo);
        // control objects (hero)
        control.add(new MyGameObject(screenWidth / 3, (int) (screenHeight * .82), true, "/mickey.png"));
        // moving objects (enemy)


        // constants objects (gold)
       /* for(int i=0; i<5; i++)
            constant.add(new ImageObject((int)(screenWidth*0.9*Math.random()), (int)(screenHeight*0.9*Math.random()), "/astronaut.png"));*/
    }

    @Override
    public List<GameObject> getConstantObjects() {
        return constant;
    }

    @Override
    public List<GameObject> getMovableObjects() {
        return moving;
    }

    @Override
    public List<GameObject> getControlableObjects() {
        return control;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    private boolean intersect(GameObject o1, GameObject o2) {
        return (Math.abs((o1.getX() + o1.getWidth() / 2) - (o2.getX() + o2.getWidth() / 2)) <= o1.getWidth()) && (Math.abs((o1.getY() + o1.getHeight() / 2) - (o2.getY() + o2.getHeight() / 2)) <= o1.getHeight());
    }

    @Override
    public boolean refresh() {
        moving = fwo.check_add(moving, height, width);
        GameObject clown = control.get(0);
        if(control.get(control.size() - 1).getY()==height)
        {
            for (GameObject x : control ){
                ((MyGameObject)x).setVisible(false);
            }
            return false;}
        for (GameObject m : moving) {
            if ((((MyGameObject) m).isBomb()) && (m.getY() >= control.get(0).getY() -20&& m.getX() > clown.getX() -20&& m.getX() < clown.getX() + clown.getWidth())){
               for (GameObject x : control ){
                   ((MyGameObject)x).setVisible(false);
               }
                return false;
            }
            m.setY((m.getY() + 1));
            if (m.getY() == getHeight()) {
                    // reuse the star in another position
                    m.setY(-1 * (int) (Math.random() * getHeight()));
                    m.setX((int) (Math.random() * getWidth()));
                }
            m.setX(m.getX() + (Math.random() > 0.5 ? 1 : -1));
            if (intersect(m, clown)) {
                control.add(m);
                if (((MyGameObject)m).isBomb() && control.size() == 2){
                    for (GameObject x : control ){
                        ((MyGameObject)x).setVisible(false);
                    }
                    return false;
                }
                m.setY(clown.getY() - (control.size() - 1) * control.get(1).getHeight() + 30);
                m.setX(clown.getX());
                ((MyGameObject) m).setType(1);
            }
        }

        for (GameObject b : control) {
            if (moving.contains(b)) {
                moving.remove(b);
            }
        }
        int matchedObjects = 1;
        if (control.size() > 3) {
            for (int i = control.size() - 1; i > control.size() - 3; i--) {
                BufferedImage img1 = control.get(i).getSpriteImages()[0];
                BufferedImage img2 = control.get(i - 1).getSpriteImages()[0];
                if (bufferedImagesEqual(img1, img2)) {
                    matchedObjects++;
                }
            }
        }
        if (matchedObjects == 3) {
            int size = control.size();
            score++;
            for (int i = size - 1; i > size - 4; i--) {
                ((MyGameObject) control.get(i)).setVisible(false);
                control.remove(i);
                fwo.check_add(moving, height, width);
            }
        }
        for (GameObject b : control) {
            if (control.size() > 1) {
                b.setY(clown.getY() - ((control.size() - 1) * control.get(1).getHeight() + 40));
                b.setX(clown.getX());
            }
        }
        return true;
    }

    @Override
    public String getStatus() {
        return "Score : " + score;
    }

    @Override
    public int getSpeed() {
        return 10;
    }

    @Override
    public int getControlSpeed() {
        return 10;
    }

    private boolean bufferedImagesEqual(BufferedImage img1, BufferedImage img2) {
        if (img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight()) {
            for (int x = 0; x < img1.getWidth(); x++) {
                for (int y = 0; y < img1.getHeight(); y++) {
                    if (img1.getRGB(x, y) != img2.getRGB(x, y))
                        return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }
}