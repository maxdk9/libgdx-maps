package no.mehl.libgdx.map.myexample;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class MapTestSceneDesktop {

    public static void main(String[] args) {
        LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
        configuration.width=800;
        configuration.height = 480;
        new LwjglApplication(new MapTest(),configuration);
    }
}
