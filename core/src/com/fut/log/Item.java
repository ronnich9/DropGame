package com.fut.log;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by alex on 8/10/18.
 */

public class Item extends Rectangle {

    int rand;

    public Item(float x, float y, float width, float height, int rand) {
        super(x, y, width, height);
        this.rand = rand;
    }

}
