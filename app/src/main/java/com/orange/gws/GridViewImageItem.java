package com.orange.gws;

import android.graphics.Bitmap;

/**
 * Created by a on 20/11/15.
 */
public class GridViewImageItem {
    private Bitmap image;
    private String title;

    public GridViewImageItem(Bitmap image, String title) {
        super();
        this.image = image;
        this.title = title;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
