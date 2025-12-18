package sprites;

import java.util.LinkedList;
import java.util.stream.Collectors;

import model.ListModelDeJoc;

public abstract class Gun extends Sprite {

    public Gun(int x, int y, int width, int height, int lives, String imageString, boolean isEnemy,
            boolean isHittable) {
        super(x, y, width, height, lives, imageString, isEnemy, isHittable);
    }

    public void checkCollision() {
        int minX = getX();
        int minY = getY();
        int maxX = getX() + getWidth();
        int maxY = getY() + getHeight();

        LinkedList<Sprite> hittableSprites =
        ListModelDeJoc.getInstancia().vEntes.stream()
            .filter(Sprite::isHittable)
            .collect(Collectors.toCollection(LinkedList::new));

        hittableSprites.addAll(
                ListModelDeJoc.getInstancia().balas.stream()
                    .filter(Sprite::isHittable)
                    .toList()
        );

        for (Sprite sprite : hittableSprites) {

            if (this.isEnemy != sprite.isEnemy()) {
                boolean overlap = sprite.getX() < maxX &&
                        sprite.getX() + sprite.getWidth() > minX &&
                        sprite.getY() < maxY &&
                        sprite.getY() + sprite.getHeight() > minY;

                if (overlap) {
                    onCollision(sprite);
                    return;
                }
            }
        }
    }


    public abstract void onCollision(Sprite sprite);

}
